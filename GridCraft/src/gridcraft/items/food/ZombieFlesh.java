package gridcraft.items.food;

public class ZombieFlesh extends Food{
	public ZombieFlesh(){
		super(75); 
	}
	
	public ZombieFlesh(double heartValue){
		super(heartValue);
	}
	
	public String getType(){
		return "Zombie flesh"; 
	}
}
