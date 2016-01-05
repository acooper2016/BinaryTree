/**
*Binary Tree data structure.  Contains a single object, and pointers to two other binary trees, called left and right.  Navigated
*Recursively
* 
*@author Aaron Cooper
*@version 1.0
*/

import java.util.Iterator;

public class PostorderIterator<E> implements Iterator<E>
{
	private Queue<E> order;
	private int index;
	
	/**
	*Constructs a new PostOrder iterator with a linked list containing contents of Binary Tree "in postorder"
	*upon construction.  Postorder adds values recursively according to the following priorities: Left branch, right branch, root value
	*
	*@return new postorder iterator
	*@param root Binary tree that is the root of the contents of the iterator
	*/
	public PostorderIterator(BinaryTree<E> root)
	{
		index = 0;
		order = new LinkedList<E>();
		getOrder(root, order);
	}
	
	/**
	*Helper method that constructs the linked list in the constructor
	*
	*@param branch current tree being added recursively
	*@param order current contents of Linked List
	*/
	private void getOrder(BinaryTree<E> branch, Queue<E> order)
	{
		if(branch.isLeaf())
			order.offer(branch.getValue());
		else if(branch.getRight() == null)
		{
			getOrder(branch.getLeft(), order);
			order.offer(branch.getValue());			
		}
		else if(branch.getLeft() == null)
		{
			getOrder(branch.getRight(), order);		
			order.offer(branch.getValue());
		}				
		else
		{
			getOrder(branch.getLeft(), order);
			getOrder(branch.getRight(), order);
			order.offer(branch.getValue());
		}
	}
	
	/**
	*Returns whether or not this iterator has a next value
	*
	*@return whether or not this iterator has a next value
	*/
	public boolean hasNext()
	{
		if(order.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	*Next value of the iterator
	*
	*@return next value
	*/
	public E next()
	{
		return order.poll();
	}
}
