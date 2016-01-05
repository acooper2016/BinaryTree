import java.util.ArrayList;
import java.util.List;

public class LinkedListRunner
{
	public static void main(String [] args)
	{
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		l.add(2);
		l.add(3);
		l.add(4);
		
		LinkedList<Integer> m = new LinkedList<Integer>(l);
		System.out.println(m);
		System.out.println(m.size());

	}
}