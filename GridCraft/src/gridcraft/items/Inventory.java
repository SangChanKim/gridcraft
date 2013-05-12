package gridcraft.items;


public class Inventory {
	
	//null objects in the array mean that they are empty (no item)
	
	private Item[] inv; 
	
	public Inventory(){
		inv = new Item[9]; 
	}
	
	public Item getItem(int index){
		return inv[index]; 
	}
	
	public void addItem(Item item){
		for(int i = 0; i < inv.length; i++){
			if(inv[i] == null)
				inv[i] = item; 
		}
	}
	
	public void deleteItem(int index){
		if(index >= 0 && index < inv.length)
			inv[index] = null; 
	}
	
}
