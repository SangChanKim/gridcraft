package gridcraft.items;


public class Inventory {
	
	//null objects in the array mean that they are empty (no item)
	
	private Item[] inv; 
	
	private int[] numInv; //stores the number of items in each inventory slot 
	//all empty slots have 0
	
	public Inventory(){
		inv = new Item[9]; 
		numInv = new int[9]; 
	}
	
	public int getNumForSlot(int index){
		return numInv[index];  
	}
	
	public void setInvNum(int index, int num){
		numInv[index] = num;
	}
	
	public Item getItem(int index){
		return inv[index]; 
	}
	
	public void addItem(Item item){
		for(int i = 0; i < inv.length; i++){
			if(inv[i] == null){
				inv[i] = item;
				numInv[i]++; 
				break; 
			}
		}
	}
	
	public void deleteItem(int index){
		if(index >= 0 && index < inv.length){
			inv[index] = null; 
			numInv[index]--; 
		}
	}
	
}
