package gridcraft.server;

import info.gridworld.grid.Location;

import java.net.InetAddress;

public class ServerPlayer {
	
	public String name;
	public InetAddress address;
	public int port;
	private final int ID;
	public Location loc; 
	
	public ServerPlayer(String name, Location loc, InetAddress address, int port, final int ID){
		this.ID  = ID;
		this.name = name;
		this.address = address;
		this.port = port; 
		this.loc = new Location(loc.getCol(), loc.getRow());
	}
	
	public int getID(){
		return ID; 
	}
}
