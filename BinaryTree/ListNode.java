public class ListNode<E>
{
	private E item;
	private ListNode<E> next;
	
	public ListNode(E i)
	{
		item = i;
		next = null;
	}
	
	public ListNode(E i, ListNode<E> pointer)
	{
		item = i; 
		next = pointer;
	}
	
	public E getItem()
	{
		return item;
	}
	
	public ListNode<E> getNext()
	{
		return next;
	}
	
	public void setItem(E i)
	{	
		item = i;
	}
	
	public void setNext(ListNode<E> pointer)
	{
		next = pointer;
	}
	
	public String toString()
	{
		if(getNext() == null)
			return "\n" + item.toString();
		return "\n" + item + getNext().toString();
	}
	
	
}