import java.util.Iterator;
public class BinaryTreeTest
{
	public static void main(String [] args)
	{
		BinaryTree<Character> a = new BinaryTree<Character>('a');
		BinaryTree<Character> b = new BinaryTree<Character>('b');
		BinaryTree<Character> c = new BinaryTree<Character>('c');
		BinaryTree<Character> d = new BinaryTree<Character>('d');
		BinaryTree<Character> e = new BinaryTree<Character>('e');
		BinaryTree<Character> f = new BinaryTree<Character>('f');
		BinaryTree<Character> g = new BinaryTree<Character>('g');
		BinaryTree<Character> h = new BinaryTree<Character>('h');
		BinaryTree<Character> i = new BinaryTree<Character>('i');
		
		a.setLeft(b);
		a.setRight(c);
		b.setLeft(d);
		b.setRight(e);
		c.setLeft(f);
		c.setRight(g);
		d.setLeft(h);
		h.setLeft(i);
		
		Iterator<Character> iter = new PostorderIterator<Character>(a);
		while(iter.hasNext())
			System.out.println(iter.next());

		
		
	}
}	