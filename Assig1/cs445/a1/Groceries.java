package cs445.a1;
import cs445.a1.GroceryItem;
import cs445.a1.GroceriesInterface;
import cs445.a1.Groceries;
import java.util.Arrays;



public class Groceries implements GroceriesInterface{ 

    Set<GroceryItem> bag;

    public Groceries()
    {
       bag = new Set<GroceryItem>();
    }
    public void addItem(GroceryItem newEntry)
    {
        //bag.add(newEntry);
        if(bag.contains(newEntry))			//if item is already in bag, increase the qauntity
        {
           //GroceryItem temp = bag.remove(newEntry);
           GroceryItem temp = newEntry;

           int numFromBag =  temp.getQuantity();
           int quantOfItem = newEntry.getQuantity();
           int total = numFromBag + quantOfItem;

           newEntry.setQuantity(total);
           bag.add(newEntry);
        }			
        else if(!bag.contains(newEntry))			//if item is not already in the bag, just add item
        {
        	bag.add(newEntry);
        }								
        

    }
   
    public void removeItem(GroceryItem newEntry)
    {
        int numToRemove = newEntry.getQuantity();
        int newTotal;
        if(bag.contains(newEntry))              // if greater than 1 in bag
        {
            
            //GroceryItem temp = bag.remove(newEntry);
            GroceryItem temp = newEntry;
            int numInBag = temp.getQuantity();

            if(numInBag > numToRemove)          //if you can remove stuff from bag
            {
                newTotal = numInBag - numToRemove;
                temp.setQuantity(newTotal);
                bag.add(temp);
            }
            
            if(numInBag <= numToRemove)          //if you want to remove more than amount in bag, remove the whole object
            {
                bag.remove(newEntry);
            }                      
        }

    }
    public int modifyQuantity(GroceryItem newEntry) throws IllegalArgumentException
    {        
        int newTotal = newEntry.getQuantity();
        int oldTotal;
        if(bag.contains(newEntry))              //if item is in the bag, override the quantity
        {
        	/*int index = bag.getIndexOf(newEntry);
        	Object[] newBag = bag.toArray();
        	newBag[index] = null;*/

        	//System.out.println(newEntry);
        	//System.out.println(newEntry.getDescription());
           	GroceryItem temp = bag.remove(newEntry);
     		//System.out.println(temp == null);
            //System.out.println(bag.remove(newEntry) == null);

            //GroceryItem temp = newEntry;
            oldTotal = temp.getQuantity();

            temp.setQuantity(newTotal);
            bag.add(temp);
            //return oldTotal;
        }
        else                                    //if item is not in bag, throw error
            throw new  IllegalArgumentException();

        return oldTotal;
    }
    public void printAll()
    {
        //Prints all groceries. Includes a header "Groceries:" and prints each item on a separate line.
        System.out.println("Groceries:");
        Object[]  arr =  bag.toArray();
        for(int i = 0; i < arr.length; i++)
        {
        	
        	if(arr[i] != null)
        	{
        		System.out.println(arr[i]);
        	}
        	
            
        }

    }

}

