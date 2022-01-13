package cs445.a5;
import java.util.Iterator;


public class TernaryTreeTester {

    public static void main(String[] args) {

        //TernaryNode<Integer> newNode1 = new TernaryNode<Integer>(1);
        //TernaryNode<Integer> newNode2 = new TernaryNode<Integer>(2);
        //TernaryNode<Integer> newNode3 = new TernaryNode<Integer>(3);

        TernaryNode<Integer> newNode4 = new TernaryNode<Integer>(4);
        TernaryNode<Integer> newNode5 = new TernaryNode<Integer>(5);
        TernaryNode<Integer> newNode6 = new TernaryNode<Integer>(6);

        TernaryNode<Integer> newNode7 = new TernaryNode<Integer>(7);
        TernaryNode<Integer> newNode8 = new TernaryNode<Integer>(8);
        TernaryNode<Integer> newNode9 = new TernaryNode<Integer>(9);

        //TernaryNode<Integer> sub1 = new TernaryNode<Integer>(100, newNode1, newNode2, newNode3);
        TernaryNode<Integer> sub2 = new TernaryNode<Integer>(101, newNode4, newNode5, newNode6);
        TernaryNode<Integer> sub3 = new TernaryNode<Integer>(102, newNode7, newNode8, newNode9);

        TernaryTree<Integer> newTree = new TernaryTree<Integer>(103, null, sub2, sub3);

        System.out.println("root = " + newTree.getRootData());
         System.out.println("isEmpty() " + newTree.isEmpty());
        System.out.println("Height = " + newTree.getHeight());
        System.out.println("number of nodes = " + newTree.getNumberOfNodes());
        System.out.println("does tree contain 5 " + newTree.contains(5));             //true
        System.out.println("does tree contain 2345678 " + newTree.contains(2345678)); //false
        System.out.println("is this tree balanced? " + newTree.isBalanced());
        //newTree.clear();
        //System.out.println("clear() and getHeight() to test "  + newTree.getHeight());
        System.out.println("\n");
        //////////////////////////////////////////////////////

        System.out.println("test Pre-Order Iterator");
        Iterator testPreorder = newTree.getPreorderIterator();
        while(testPreorder.hasNext()){
            System.out.println(testPreorder.next());
        }
        System.out.println("\n");
        /////////////////////////////////////////////////////
        System.out.println("test Post-Order Iterator");
        Iterator testPostorder = newTree.getPostorderIterator();
        while(testPostorder.hasNext()){
            System.out.println(testPostorder.next());
        }
        System.out.println("\n");
        /////////////////////////////////////////////////////
        System.out.println("test Level-Order Iterator");
        Iterator testLevelorder = newTree.getLevelOrderIterator();
        while(testLevelorder.hasNext()){
            System.out.println(testLevelorder.next());
        }


        

    }

    /*
    // Prints the tree in a sideways indented format.
    public void printSideways() {
     printSideways(root, "");

    }

    private void printSideways(TernaryTree<E> root, String indent) {
        
        if (root != null) {
            printSideways(root.getRightChild(), indent + " ");
            System.out.println(indent + root.getData());
            printSideways(root.getMiddleChild(), indent + "");
            printSideways(root.left, indent + " ");
        }
    } 
    */
}

