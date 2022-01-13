package cs445.a1;
import cs445.a1.GroceryItem;
import cs445.a1.GroceriesInterface;
import cs445.a1.Groceries;
import java.util.Arrays;
/**
	A class that implements the SetInterface 
	using a resizable array(dynamic) of type <E>.
	The ArrBag should never be full
	Set == ResizableArrBag
**/
public class Set<E> implements SetInterface<E>
{
	private E[] bag;
	private int numOfEntries;
	private static final int DEFAULT_CAPCITY = 25; //inital capitcity of bag

	public Set()
	{
		this(DEFAULT_CAPCITY); 
		//creates empty bag with intial capcity of 25
		//end of default constructor
	}

	public  Set(int initalCap)
	{
		E[] tempBag = (E[]) new Object[initalCap];
		bag = tempBag;	//just renaming it for simplicity
		numOfEntries = 0;
		//end of constructor
	}
	public  Set(E[] contents)
	{
		bag = Arrays.copyOf(contents, contents.length);
		numOfEntries = contents.length;
		//creates a bag containing given entries
		//end of constructor
	}
	public int getSize()
	{
		return numOfEntries;
	}
	public boolean isEmpty() //returns true if bag is empty, false is not empty
	{
		return numOfEntries == 0;
	}
	public boolean add(E newEntry) throws NullPointerException
	{
		boolean result;

		if(newEntry == null)
		{
			throw new NullPointerException();
		}
		if(isArrFull())		//double capacity
		{	
			doubleCapacity();  
		}
		if(contains(newEntry))
		{
			result = false;
		}
		
		bag[numOfEntries] = newEntry;
		numOfEntries++;
		result = true;

		return result;
	}
	private boolean isArrFull()
	{
		if(numOfEntries >= bag.length)
			return true;
		return false;
	}
	private void doubleCapacity()
	{
		int newLength = 2*bag.length;
		bag = Arrays.copyOf(bag, newLength);
	}
	public E remove() //unspecified
	{
		//System.out.println("test");
		E result = null;
		if(numOfEntries >= 1)
		{
			result = bag[numOfEntries-1];
			int lastIndex = numOfEntries-1;
			bag[lastIndex] = null;
			numOfEntries--;
		}
		

		if(numOfEntries == 0)
		{
			result = null;
		}
		return result;
	}
	public E remove(E entry) throws NullPointerException
	{
		E result = null;
		//System.out.println(contains(entry));

		if(contains(entry))
		{
			//System.out.println("2222");
				
			int index = getIndexOf(entry);
			result = bag[index];
			//System.out.println(result == null);
			bag[index] = bag[bag.length-1];
			bag[bag.length-1] = null;
			numOfEntries--;
			//entry.equals(result)

		}

		else if(!contains(entry))
		{
			result = null;

		}
		else if(entry == null)
		{
			throw new NullPointerException();
		}
		//System.out.println(result == null);
		return result;
	}

	public int getIndexOf(E entry) throws NullPointerException
	{
		int where = -1;    
		boolean found = false;   
		int index = 0;                
		///for(int i = 0; i < numOfEntries && found < 0; i++) 
		while(!found && (index < numOfEntries))
		//goes till end of arr or is found before the end
		{
			///if(bag[found].equals(entry));
			if(entry.equals(bag[index]))
			{
				found = true;
				where = index;
				//index++;
				//System.out.println(where);
				break;
			}
			if(entry == null)
			{
				throw new NullPointerException();
			}
			index++;
			
		}
		return where;
	}
	public void clear()
	{
		while(!isEmpty())
		{
			remove();
		}
	}
	public boolean contains(E entry)
	{
		
		return getIndexOf(entry) > -1;
	}
	public Object[] toArray()
	{
		//make new arr size of numOfEntires	
		//copy all data to new one
		//return new arr
		Object[] finalBag = (E[]) new Object[numOfEntries];
		finalBag = Arrays.copyOf(bag, numOfEntries);
		return finalBag;
	}
}