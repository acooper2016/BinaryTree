import java.lang.Iterable;
import java.util.Iterator;

public class BinaryTree<E> implements Iterable
{
	protected E value;
	protected BinaryTree<E> left;
	protected BinaryTree<E> right;
	
	/**
	*Constructs a new binary tree containing a given item
	*
	*@param v value of the new BinaryTree
	*/
	public BinaryTree(E v)
	{
		value = v;
		left = null;
		right = null;
	}
	
	/**
	*Constructs an empty BinaryTree
	*/
	public BinaryTree()
	{
		value = null;
		left = null;
		right = null;
	}
	
	/**
	*Sets the left branch of the BinaryTree
	*
	*@param node New left branch of the BinaryTree
	*/
	public void setLeft(BinaryTree<E> node)
	{
		left = node;
	}
	
	/**
	*Sets the right branch of the BinaryTree
	*
	*@param node New right branch of the BinaryTree
	*/

	public void setRight(BinaryTree<E> node)
	{
		right = node;
	}
	
	/**
	*Sets the value of a given BinaryTree to a given new value
	*
	*@param v New value of the BinaryTree
	*/
	public void setValue(E v)
	{
		value = v;
	}	
	
	/**
	*Returns the contents of a given BinaryTree
	*
	*@return Value of the BinaryTree
	*/
	public E getValue()
	{
		return value;
	}
	
	/**
	*Returns the right branch of the BinaryTree
	*
	*@return value of the right branch of the BinaryTree
	*/
	public BinaryTree<E> getRight()
	{
		return right;
	}
	
	/**
	*Returns the left branch of the BinaryTree
	*
	*@return value of the left branch of the BinaryTree
	*/
	public BinaryTree<E> getLeft()
	{
		return left;
	}
	/**
	*Returns whether or not a given BinaryTree has no branches
	*
	*@return whether or not this BinaryTree has no branches.
	*/
	public boolean isLeaf()
	{
		if(left == null && right == null)
			return true;
		else
			return false;
	}
	
	/**
	*Return true if adding a node to tree would increase its height
	*
	*@return whether or not tree is full
	*/
	public boolean isFull()
	{
		if(isLeaf())
			return true;
		
		if(left == null || right == null)
			return false;
		
		return(left.isFull() && right.isFull() && right.height() == left.height());
		
	}
	
	/**
	*Return length of longest possible path traceable from a given root
	*
	*@return length of longest possible path
	*/
	public int height()
	{
		if(isLeaf())
			return 1;
		
		if(left == null)
			return right.height() + 1;
		
		if(right == null)
			return left.height() + 1;
			
		return( Math.max(left.height(), right.height()) + 1);
	}
	
	/**
	*Returns total number of nodes connected to a root tree
	*
	*@return total number of nodes
	*/
	public int size()
	{
		if(isLeaf())
			return 1;
		
		if(left == null)
			return right.size() + 1;
		
		if(right == null)
			return left.size() + 1;
		
		return(left.size() + right.size() + 1);		
	}
	
	/**
	*Return true if tree has minimal height and any holes in the tree //appear in the last level to the right
	*
	*@return whether or not tree is complete
	*/
	public boolean isComplete()
	{
		if(isLeaf())
			return true;
			
		else if(left == null)
			return false;
		
		else if(right == null)
			return left.isLeaf();
		
		else if(Math.abs(left.height() - right.height()) == 1)
			return(left.isComplete() && right.isFull());
		
		else if(left.height() == right.height())
			return(left.isFull() && right.isComplete());
		else
			return false;
	}
	/**
	*Return true if the difference of heights of subtrees at every node 
	*is no greater than one
	*
	*@return whether or not tree is balanced
	*/
	public boolean isBalanced()
	{
		if(isLeaf()) 
			return true;
		if(left == null)
		{
			if(right.isLeaf())
				return true;
			else
				return false;
		}
		
		if(right == null)
		{
			if(left.isLeaf())
				return true;
			else
				return false;
		}
		
		
		if(left.height() - right.height() > 1 || left.height() - right.height() < -1)
			return false;
		
		return(left.isBalanced() && right.isBalanced());
	}
	
	/**
	*Returns String representing BinaryTree according to an Inorder iterator
	*
	*@return String representing tree
	*/
	public String toString()
	{
		Iterator<E> printer = iterator();
		String endString = "";
		while(printer.hasNext())
			endString += printer.next();
		return endString;
	}
		
	
	/**
	*Returns a delicious recipe for Strudel
	*Note: Method uses unchecked or unsafe strudel recipe.  Recompile in kitchen for details
	*
	*@return tasty recipe for apple strudel
	*/
	public String strudelRecipe()
	{
		return "Ingredients\nApple Strudel:\n1/4 cup bourbon or apple juice\n1/2 cup golden raisins\n2 to 3 Granny Smith apples (about 1 pound), peeled, cored, halved, and thinly sliced\n1/2 lemon, juiced\n1 tablespoon lemon zest, finely chopped\n1 teaspoon ground cinnamon, plus more for sprinkling\n1/2 cup brown sugar, packed\n1/2 cup crushed shortbread cookies\n1/4 cup chopped pecans\n2 tablespoons butter, cut into pieces\n5 sheets phyllo dough from 1 pound package of frozen dough\n2 tablespoons butter, melted, for brushing phyllo sheets, plus more if needed\n1 tablespoon granulated sugar\nConfectioners' sugar\nCaramel sauce, purchased\nGlaze:\n2 cups confectioners' sugar\n3 1/2 tablespoons milk\n\n DirectionsFor the Strudel:\nPreheat the oven to 350 degrees F. Line a baking sheet with parchment paper.\nIn a small bowl, pour the bourbon or apple juice over the raisins and microwave on high for 45 seconds. Let sit for 15 minutes.\nCombine the raisins, apples, lemon juice, lemon zest, cinnamon, brown sugar, cookie crumbs, pecans, and butter in a large bowl.\nRemove the phyllo dough from the box, unfold, and cover with a damp towel. Place 1 sheet of phyllo on the work surface and brush lightly with melted butter. Repeat with the remaining sheets, brushing each with melted butter, stacking when done, being sure to keep the unbuttered phyllo covered.\nPlace the apple mixture on the nearest third of the phyllo stack, being sure to leave a 2-inch border. Gently lift the bottom edge of the phyllo stack to cover the filling and fold the side edges over. Continue to roll the stack away from you until the filling is completely sealed in and the seam is on the bottom. Transfer to the prepared baking sheet. Brush the top with melted butter and sprinkle with granulated sugar.\nBake for 30 minutes, until golden brown. Pour over the glaze and sprinkle with cinnamon and confectioners' sugar. Pass warm caramel sauce, to drizzle over the strudel.\nFor the Glaze:\nMix ingredients thoroughly.\n*Cook's Note: If too thick add a little bit of milk. If too thin add a little bit of confectioners' sugar.";

	}
	
	/**
	*Returns an inorder iterator
	*
	*@return new inorder iterator
	*/
	public Iterator<E>  iterator()
	{
		return new InorderIterator<E>(this);
	}
	
	/**
	*Returns a new preorder Iterator
	*
	*@return preorder iterator
	*/
	public Iterator<E> preorderIterator()
	{
		return new PreorderIterator<E>(this);	
	}
	
	public Iterator<E> inorderIterator()
	{
		return new InorderIterator<E>(this);
	}
	
	/**
	*Returns a new post order iterator
	*
	*@return postorder iterator
	*/
	public Iterator<E> postorderIterator()
	{
		return new PostorderIterator<E>(this);
	}
	

	

}

