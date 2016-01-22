public class BinarySearchTree<E extends Comparable> 
{
	private BinaryTree<E> root;
	
	public static void main(String [] args)
	{
		
	}
	
	public BinarySearchTree(E rootItem)
	{
		root = new BinaryTree<E>(rootItem);
	}
	
	public boolean add(E item)
	{
		if(root == null)
		{
			root = new BinaryTree<E>(item);	
			return true;
		}	
		
		return add(item, root);
	}	
	
	private boolean add(E item, BinaryTree<E> branch)
	{
		if(branch.getValue().compareTo(item) == 0)
		{ 
			return false;
		}
		else if(branch.getLeft() == null && item.compareTo(branch.getValue()) < 0)
		{
			branch.setLeft(new BinaryTree<E>(item));
			return true;
		}
		else if(branch.getRight() == null && item.compareTo(branch.getValue()) > 0)
		{
			branch.setLeft(new BinaryTree<E>(item));
			return true;
		}
		else if(item.compareTo(branch.getValue()) <= 0)
		{
			add(item, branch.getLeft());
			return true;
		}
		else
		{
			add(item, branch.getLeft());
			return true;
		}
	}	
	
	public BinaryTree<E> find(E item)
	{
		if(root == null)
			return null;
		else
			return find(item, root);	
	}
	
	private BinaryTree<E> find(E item, BinaryTree<E> branch)
	{
		if(item.equals(branch.getValue()))
			return branch;
		else if(branch.isLeaf())
			return null;
		else if(item.compareTo(branch.getValue()) > 0 && branch.getRight() == null)
			return null;
		else if(item.compareTo(branch.getValue()) < 0 && branch.getLeft() == null)
			return null;
		else if(item.compareTo(branch.getValue()) > 0)
			return find(item, branch.getRight());
		else //if(item.compareTo(branch.getValue()) < 0)
			return find(item, branch.getLeft());
		
			
	}
	
	public String toString()
	{
		return root.toString();
	}
	
	
}
