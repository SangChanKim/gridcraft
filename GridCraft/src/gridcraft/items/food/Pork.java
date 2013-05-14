package gridcraft.items.food;

public class Pork extends Food{
	
	public Pork(){
		super(125); 
	}
	
	public Pork(double heartValue){
		super(heartValue); 
	}
	
	public String getType(){
		return "Pork";
	}

}
