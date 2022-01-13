package cs445.a5;
import  cs445.StackAndQueuePackage.*;
import java.util.*; 
import java.lang.*;


public class TernaryTree<E> implements TernaryTreeInterface<E> {
//public class TernaryTree<E extends Comparable<? Super E>> implements TernaryTreeInterface<E>{

	private TernaryNode<E> root;


	public TernaryTree(){
		root = null;
	}

	public TernaryTree(E rootData){
		root = new TernaryNode<E>(rootData);
	}

	public TernaryTree(E rootData, TernaryNode<E> leftTree, TernaryNode<E> middleTree, 
														TernaryNode<E> rightTree){
		root = new TernaryNode<E>(rootData, leftTree, middleTree, rightTree);
	}

	public void setTree(E rootData){
		 root = new TernaryNode<>(rootData);
	}

	public void setTree(E rootData, TernaryTreeInterface<E> leftTree, TernaryTreeInterface<E> middleTree,
    								TernaryTreeInterface<E> rightTree){
		privateSetTree(rootData, (TernaryTree<E>) leftTree, (TernaryTree<E>) middleTree,
                       (TernaryTree<E>) rightTree);
	}

	private void privateSetTree(E rootData, TernaryTree<E> leftTree, TernaryTree<E> middleTree,
										TernaryTree<E> rightTree){
		TernaryNode<E> root = new TernaryNode<>(rootData);

		if ((leftTree != null) && !leftTree.isEmpty()) {
            root.setLeftChild(leftTree.root);
        }

        if ((middleTree != null) && !middleTree.isEmpty()) {
            if (middleTree != leftTree) {
                root.setMiddleChild(middleTree.root);
            } else {
                root.setMiddleChild(middleTree.root.copy());
            }
        }

        if ((rightTree != null) && !rightTree.isEmpty()) {
            if (rightTree != middleTree) {
                root.setRightChild(rightTree.root);
            } else {
                root.setRightChild(rightTree.root.copy());
            }
        }

        this.root = root;

        if ((leftTree != null) && (leftTree != this)) {
            leftTree.clear();
        }

        if ((middleTree != null) && (middleTree != this)) {
            middleTree.clear();
        }

        if ((rightTree != null) && (rightTree != this)) {
            rightTree.clear();
        }
		
	}
	public E getRootData(){
		if(isEmpty()){
			throw new EmptyTreeException();
		}
		else{
			return root.getData();
		}
	}
	/*
	public int getHeight(){
		int height = 0;
        if (!isEmpty()) {
            height = root.getHeight();
        }
        return height;
	}
	*/

	public int getHeight(){
		return getHeight(root);
	}
	private int getHeight(TernaryNode<E> node){
		if(node == null) return 0;
		else return 1 + Math.max(Math.max(getHeight(node.getLeftChild()), getHeight(node.getMiddleChild())) , getHeight(node.getRightChild()));
	}

	public int getNumberOfNodes(){
		int numberOfNodes = 0;
        if (!isEmpty()) {
            numberOfNodes = root.getNumberOfNodes();
        }
        return numberOfNodes;
	}

	public boolean isEmpty(){
		return (root == null);
	}

	public void clear(){
		root = null;
	}
	
	public boolean contains(E elem){
		return contains(root, elem);
	}

	private boolean contains(TernaryNode<E> cur, E elem){
		if(cur == null){
			return false;	//base case: cur not found here
		}
		boolean result = elem.equals(cur.getData());
		if(result) return true;
		else if(!result) return contains(cur.getLeftChild(), elem) ||
									contains(cur.getMiddleChild(), elem) ||
									contains(cur.getRightChild(), elem);
		else return false;
	}
	
	public boolean isBalanced(){
		return isBalanced(root);
	}
	private boolean isBalanced(TernaryNode<E> root){
		if(root == null) return true;

		int leftTreeHeight;
		int middleTreeHeight;
		int rightTreeheight;

		leftTreeHeight = getHeight(root.getLeftChild());
		middleTreeHeight = getHeight(root.getMiddleChild());
		rightTreeheight = getHeight(root.getRightChild());

		int diff1 = leftTreeHeight - middleTreeHeight;
		int diff2 = leftTreeHeight - rightTreeheight;
		int diff3 = middleTreeHeight - rightTreeheight;

		if(Math.abs(diff1) > 1 || Math.abs(diff2) > 1 || Math.abs(diff3) > 1)
			return false;	
		else
			return isBalanced(root.getLeftChild()) &&
					isBalanced(root.getMiddleChild()) && isBalanced(root.getRightChild());
	}
	
	//Creates an iterator to traverse the tree in preorder fashion
	public Iterator<E> getPreorderIterator(){
		return new PreorderIterator();
	}

    //Creates an iterator to traverse the tree in postorder fashion
  	public Iterator<E> getPostorderIterator(){
  		return new PostorderIterator();
  	}

 	//Creates an iterator to traverse the tree in inorder fashion
 	//do not need to implement, just throw unsuppportedOperationException
 	//because there is not fefined order for the middle child, its is arbraitary 
 	//whether the inorder traversal is leftChild, Parent, MiddleChild, rightChild or
 	// leftChild, MiddleChild, Parent, rightChild
    public Iterator<E> getInorderIterator(){
    	throw new UnsupportedOperationException();
    }

  	//Creates an iterator to traverse the tree in level-order fashion
    public Iterator<E> getLevelOrderIterator(){
    	return new LevelOrderIterator();
    }



    private class PreorderIterator implements Iterator<E> {
    	private StackInterface<TernaryNode<E>> nodeStack;

    	public PreorderIterator(){
    		nodeStack = new LinkedStack<>();
    		if(root != null){
    			nodeStack.push(root);
    		}
    	}

    	public boolean hasNext(){
    		return !nodeStack.isEmpty();
    	}

    	public E next(){
    		
    		//System.out.println("State of node in next() "+ nodeStack.peek().getData());

    		TernaryNode<E> nextNode;

    		if(hasNext()){

    			nextNode = nodeStack.pop();
    			TernaryNode<E> leftChild = nextNode.getLeftChild();
    			TernaryNode<E> middleChild = nextNode.getMiddleChild();
    			TernaryNode<E> rightChild = nextNode.getRightChild();
    			// Push onto stack in reverse order of recursive calls
    			if(rightChild != null){
    				//System.out.println("rightChild =" + rightChild.getData());
    				nodeStack.push(rightChild);
    			}
    			if(middleChild != null){
    				//System.out.println("middlechild =" + middleChild.getData());
    				nodeStack.push(middleChild);
    			}
    			if(leftChild != null){
    				//System.out.println("leftChild =" + leftChild.getData());
    				nodeStack.push(leftChild);
    			}
    			///////////////////////////////////////
				//System.out.println(nodeStack.contains(4));  
				/////////////////////////////////////// 
    		}
    		else{
    			throw new NoSuchElementException();
    		}
    		return nextNode.getData();
    	}
         
    	public void remove() {
            throw new UnsupportedOperationException();
        }
    }
	
	private class PostorderIterator implements Iterator<E>{
		private StackInterface<TernaryNode<E>> nodeStack;
        private TernaryNode<E> currentNode;

        public PostorderIterator(){
        	nodeStack = new LinkedStack<>();
        	currentNode = root;
        }

        public boolean hasNext(){
        	return !nodeStack.isEmpty() || (currentNode != null);
        }

        public E next(){
        	boolean foundNext = false;
        	TernaryNode<E> leftChild; 
        	TernaryNode<E> middleChild = null; 
        	TernaryNode<E> rightChild;
        	TernaryNode<E> nextNode = null;

            // Find leftmost leaf
            while (currentNode != null) {
                nodeStack.push(currentNode);
                leftChild = currentNode.getLeftChild();
                //System.out.println("leftChild = " + leftChild);
                if (leftChild == null) {
                    currentNode = currentNode.getMiddleChild();
                    //System.out.println("middleChild currentNode = " + currentNode);
                } 
                else if (middleChild == null) {
                    currentNode = currentNode.getLeftChild();
                    //System.out.println("rightChild" + currentNode);
                }
                else{
                	currentNode = leftChild;
                }
            }

            // Stack is not empty either because we just pushed a node, or
       		// it wasn'E empty to begin with since hasNext() is true.
        	// But Iterator specifies an exception for next() in case
        	// hasNext() is false.

            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                // nextNode != null since stack was not empty before pop

                TernaryNode<E> parent = null;
                if (!nodeStack.isEmpty()) {
                    parent = nodeStack.peek();
                    if (nextNode == parent.getLeftChild()) {
                        currentNode = parent.getMiddleChild();
                    } 
                    else if (nextNode == parent.getMiddleChild()) {
                        currentNode = parent.getRightChild();
                    } else {
                        currentNode = null;
                    }
                } else {
                    currentNode = null;
                }
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.getData();
		}	

		public void remove() {
            throw new UnsupportedOperationException();
        }
   	} 

   	 private class LevelOrderIterator implements Iterator<E> {
        private QueueInterface<TernaryNode<E>> nodeQueue;

        public LevelOrderIterator() {
            nodeQueue = new LinkedQueue<>();
            if (root != null) {
                nodeQueue.enqueue(root);
            }
        }

        public boolean hasNext() {
            return !nodeQueue.isEmpty();
        }

        public E next() {
            TernaryNode<E> nextNode;

            if (hasNext()) {
                nextNode = nodeQueue.dequeue();
                TernaryNode<E> leftChild = nextNode.getLeftChild();
                TernaryNode<E> middleChild = nextNode.getMiddleChild();
                TernaryNode<E> rightChild = nextNode.getRightChild();

                // Add to queue in order of recursive calls
                if (leftChild != null) {
                    nodeQueue.enqueue(leftChild);
                }

                if (middleChild != null){
                	nodeQueue.enqueue(middleChild);
                }

                if (rightChild != null) {
                    nodeQueue.enqueue(rightChild);
                }
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


     private static class Node<T>{
      	private T data;
      	private Node<T> next;

     	 public Node(T data, Node<T> next)
      	{
         this.data = data;
         this.next = next;
      	}
  	}


}