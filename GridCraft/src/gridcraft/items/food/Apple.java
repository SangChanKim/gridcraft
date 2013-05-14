package gridcraft.items.food;

public class Apple extends Food {
	
	public Apple(){
		super(50); 
	}
	public Apple(double heartValue){
		super(heartValue); 
	}
	
	public String getType(){
		return "Apple"; 
	}
	
}
