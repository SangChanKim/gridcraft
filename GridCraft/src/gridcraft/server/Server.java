package gridcraft.server;

import gridcraft.OverWorld;
import info.gridworld.grid.Location;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Server implements Runnable{
	
	public ArrayList<ServerPlayer> players = new ArrayList<ServerPlayer>();  
	private final int port;
	private DatagramSocket socket; 
	public boolean running = false; 
	
	private int id = 0; 
	public Thread run, managePlayers, send, receive;
	
	private OverWorld world; 
	
	
	
	public Server(int port){
		this.port = port;
		try{
			socket = new DatagramSocket(port);
		} catch (SocketException e){
			e.printStackTrace();
			return; 
		}
		
		world = new OverWorld();
		
		run = new Thread(this, "Server");
		run.start(); 
	}

	@Override
	public void run() {
		running = true;
		managePlayers();
		receive();
	}
	
	public void managePlayers(){
		managePlayers = new Thread("manageplayers"){
			public void run(){
				while(running){
					
				}
			}
		}; 
		managePlayers.start(); 
	}
	
	public void send(final byte[] data, final InetAddress address, final int port){
		send = new Thread("Send"){
			public void run(){
				DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}; 
		
		send.start(); 	
	}
	
	

	public void receive(){
		receive = new Thread("receive"){
			public void run(){
				while(running){
					byte[] data = new byte[1024];
					DatagramPacket packet = new DatagramPacket(data, data.length);
					try{
						socket.receive(packet);
					} catch(IOException e){
						e.printStackTrace(); 
					}
					process(packet); 
				}
			}
		}; 
		receive.start(); 
	}
	
	// EX: /c/sang/x/5/y/7
	public void process(DatagramPacket packet){
		
		String data = new String(packet.getData()); 
		
		if(data.startsWith("/c/")){
			System.out.println("Client connected from " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
			String name = data.substring(3); 
			for(int i = 0; i < players.size(); i++){
				if(name.equals(players.get(i).name)){
					System.out.println("Client's name matches an already connected client's name");
					System.out.println("Client failed to join the server");
					String reply = "/f/"; 
					send(reply.getBytes(), packet.getAddress(), packet.getPort());
					return; 
				}
			}
			
			Location loc = new Location(world.spawnY,world.spawnX);
			ServerPlayer connectedPlayer = new ServerPlayer(name, loc, packet.getAddress(), packet.getPort(), this.id);
			players.add(connectedPlayer);
			
			//Update all the other players
			for(int i = 0; i < players.size(); i++){
				String update = "/u/" + connectedPlayer.name +"/x/" + connectedPlayer.loc.getCol() + "/y/" + 
			     connectedPlayer.loc.getRow();
				if(!connectedPlayer.name.equals(players.get(i).name)) {
					send(update.getBytes(), players.get(i).address, players.get(i).port); 
				}
			}
			
			id++; 
					
		}
		
		else if(data.startsWith("/u/")){ //update location
			
		}
		
	}
}
