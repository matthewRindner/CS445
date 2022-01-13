package cs445.a5;
import java.util.Iterator;

public class tester1{

  public static void main(String [] args) {
    TernaryTree<Integer> tree1 = new TernaryTree<Integer>(1);
    TernaryTree<Integer> tree2 = new TernaryTree<Integer>(2);
    TernaryTree<Integer> tree3 = new TernaryTree<Integer>(3);

    TernaryTree<Integer> subtree1 = new TernaryTree<Integer>(6);
    TernaryTree<Integer> subtree2 = new TernaryTree<Integer>(7);
    TernaryTree<Integer> subtree3 = new TernaryTree<Integer>(8);

    TernaryTree<Integer> subtree4 = new TernaryTree<Integer>(9);
    TernaryTree<Integer> subtree5 = new TernaryTree<Integer>(10);
    TernaryTree<Integer> subtree6 = new TernaryTree<Integer>(11);

    TernaryTree<Integer> subtree7 = new TernaryTree<Integer>(12);
    TernaryTree<Integer> subtree8 = new TernaryTree<Integer>(13);
    TernaryTree<Integer> subtree9 = new TernaryTree<Integer>(14);



    TernaryTree<Integer> sub1 = new TernaryTree<Integer>(100,subtree1, subtree2, subtree3);
    TernaryTree<Integer> sub2 = new TernaryTree<Integer>(101,subtree4, subtree5, subtree6);
    TernaryTree<Integer> sub3 = new TernaryTree<Integer>(102,subtree7, subtree8, subtree9);

    TernaryTree<Integer> tree4 = new TernaryTree<Integer>(103, sub1, sub2, sub3);

    Iterator testPreorder = tree4.getPreorderIterator();

    System.out.println("PreOrder");
    while(testPreorder.hasNext()){
      System.out.println(testPreorder.next());
    }

    System.out.println("\n");

    Iterator testLevel = tree4.getLevelOrderIterator();

    System.out.println("LevelOrder");
    while(testLevel.hasNext()){
      System.out.println(testLevel.next());
    }

    System.out.println("\n");

    Iterator testPost = tree4.getPostorderIterator();
    System.out.println("PostOrder");
    while(testPost.hasNext()){
      System.out.println(testPost.next());
    }
  }

  public void testIT(TernaryTree<Integer> tree){

  }

}
