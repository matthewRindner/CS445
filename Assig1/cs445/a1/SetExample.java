
package cs445.a1;

import cs445.a1.GroceryItem;
import cs445.a1.SetInterface;
import cs445.a1.Groceries;



public class SetExample {

    public static void main(String[] args) {

        // Create my grocery list
        SetInterface setBag = new Set();
        System.out.print(setBag.getSize() + " ");
        try{

                // Add stuff
                setBag.add("one");
                System.out.print(setBag.getSize() + " ");
                setBag.remove("one");
                System.out.print(setBag.getSize() + " ");


                
                setBag.add("two");
                System.out.print(setBag.getSize() + " ");  //wont print , gets stuck here
                setBag.add("three");
                setBag.add("four");  
                setBag.add("five");
                setBag.add("six");
                System.out.print(setBag.getSize() + " ");
                

                //System.out.print(setBag);

                setBag.remove("six");
                setBag.remove("seven");

                System.out.print(setBag.getSize());
                //System.out.print(setBag.isEmpty());
                System.out.print(setBag.contains("three"));
                System.out.print(setBag.contains("seven"));

            }
                catch(SetFullException E)
            {
                
            }
                

    }

}

