import java.io.*;
import java.util.Scanner;
import java.util.Iterator;

/**
*Functional TwentyQuestions game.  Runs through a preset decision tree of questions organized in a BinaryTree.  Uses recursive algorithms
*to save and later load that decision tree from session to session, allowing the program to work better and better with each 
*play.
*
*@author Aaron Cooper
*@version 1.1
*/
public class TwentyQuestions1
{
	
	/*
	*Shell that runs the game.  Prompts user whether they want to play, then runs playGame method if they do want to play.
	*/
	public static void main(String[]args)
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to 20 questions!");
		while(true)									
		{
			System.out.println("would you like to play?");
			String answer = reader.nextLine();
			if(answer.equals("y"))
				playGame(loadTree());
			else if(answer.equals("n"))
				break;
			else
				System.out.println("Please enter y or n");
			System.out.println("Play again, if you wish.");
		}
		System.out.println("goodbye!");
	}
	/*
	*Runs the game.  Takes in a BinaryTree of data, called using the loadTree() method in main, as the decision tree for 
	*guesses.  Leaf branches are guesses, and other branches are questions.  Yes answers navigate to the right, no answers to the left.
	*If the game guesses incorrectly, will prompt user for a new question, and adds a branch with that question to the tree, with the wrong
	*guess and the item the player was thinking of as its leaves.  Then, saves data to a text document.
	*/
	private static void playGame(BinaryTree<String> data)
	{
		Scanner reader = new Scanner(System.in);
		BinaryTree<String> currentNode = data;
		
		//Navigates through the tree by prompting user for question answers
		while(true)
		{
			if(!currentNode.isLeaf())
			{
				System.out.println(currentNode.getValue());
				System.out.println("y/n");
				String answer = reader.nextLine();
				if(answer.equals("y"))
				{
					currentNode = currentNode.getRight();
				}
				else if(answer.equals("n"))	
				{
					currentNode = currentNode.getLeft();
				}
				else
					System.out.println("Please type y or n");
			}
			else
			{
				break;
			}	
		}
		//Makes a guess when the game hits a leaf.  Prompts user for correctness.
		System.out.println("Is it a " + currentNode.getValue() + "?");
		String answer = reader.nextLine();
		while(true)
		{
			if(answer.equals("y"))
			{
				System.out.println("haha, I win!");
				return;
			}
			if(answer.equals("n"))
				break;
			else
			{
				System.out.println("Please type y or n");
			}
		}
		
		
		//Takes in the data to expand the tree to accommodate for the right answer.
		System.out.println("You beat me!");		
		System.out.println("What were you thinking of?");
		String wrongAnswer = currentNode.getValue();
		String rightAnswer = reader.nextLine();
		
		System.out.println("What is a good question that separates your answer from my guess?");
		String newQuestion = reader.nextLine();
		
		System.out.println("Is the answer to this question for your object y or n?");
		String response;
		while(true)
		{
			response = reader.nextLine();
			if(response.equals("y"))
				break;
			if(response.equals("n"))
				break;
			else
				System.out.println("please answer y or n");	
		}
		
		//Alters the tree, replacing the current leaf with the input question, and placing the correct answer and the
		//previously stored answer in the leaf as the branches of the new question
		if(response.equals("y"))
		{
			currentNode.setValue(newQuestion);
			currentNode.setLeft(new BinaryTree<String>(wrongAnswer));
			currentNode.setRight(new BinaryTree<String>(rightAnswer));
		}
		if(response.equals("n"))
		{
			currentNode.setValue(newQuestion);
			currentNode.setLeft(new BinaryTree<String>(rightAnswer));
			currentNode.setRight(new BinaryTree<String>(wrongAnswer));
		}
		
		
		saveTree(data);	
	}
	
	/**
	*Uses the PrintWriter class to write the BinaryTree containing 20 questions's data into a text document
	*/
	private static void saveTree(BinaryTree<String> root)
	{
		PrintWriter writer = null;
		try
		{
    		writer = new PrintWriter("SaveData.twq");
		}
		catch(FileNotFoundException ex )
		{
			System.out.println("File could not be created");
			System.exit(1);
		}
		
		
		String s = write(root);
		writer.println(s + ";");
		writer.close();
	}
	
	/**
	*Recursively converts the BinaryTree containing the game's data into a String, using "punctuation markers" to 
	*indicate the structure of the tree.
	*/
	private static String write(BinaryTree<String> branch)
	{
		String s = "";
		s += branch.getValue();
		if(branch.isLeaf())
			s += ";";
		else
		{
			s += ",";
			s += write(branch.getLeft());
			s += write(branch.getRight());
		}
		return s;
	}
	
	/*write algorithm is simple recursion.  Base Case is a leaf node, appending the node's data with a ';' character.  Non Leaves
	*Get a ',' character after them.  The String is essentially the preorder of the Binary Tree.  Because all branches have two
	*children or none, never just one on the left or right, all that needs to be indicated is whether or not a given piece of data
	*is a leaf in order to convert the Linear stream of data into a fully fleshed out tree.*/
	
	/**
	*Uses the Scanner class to convert the document containing the saved data into a String.  Then, uses the read method to
	*convert that String into the original saved BinaryTree
	*/
	private static BinaryTree<String> loadTree()
	{
		String pathname = "SaveData.twq";
		File file = new File(pathname);
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch(FileNotFoundException ex)
		{
			BinaryTree<String> newRoot = new BinaryTree<String>("Is it alive?");
			newRoot.setLeft(new BinaryTree<String>("toaster"));
			newRoot.setRight(new BinaryTree<String>("lizard"));
			return newRoot;
		}
		
		String data = "";
		String[] dataString = new String[1];
		dataString[0] = data;
		
		while(input.hasNextLine())
		{
			dataString[0] += input.nextLine();
		}
		
		return read(dataString);
	}
	/**
	*Reads the text document with the saved data, converting it back into the original BinaryTree, recursively.
	*Essentially reverses the SaveData algorithm.
	*/
	private static BinaryTree<String> read(String[] s)
	{
	
		BinaryTree<String> root = new BinaryTree<String>(nextWord(s));
		if(s[0].charAt(0) == ';')
			s[0] = s[0].substring(1);
		else 
		{
			s[0] = s[0].substring(1);
			root.setLeft(read(s));
			root.setRight(read(s));
		}
		return root;
	}
	
	/**
	*Returns the next word from in the data String, conantenating the beginning of the data String up until the next punctuation marker
	*/ 
	private static String nextWord(String[] s)
	{
		String last = "";
		while(s[0].charAt(0) != ';' && s[0].charAt(0) != ',')
		{
			last += s[0].charAt(0);
			s[0] = s[0].substring(1);
		}
		return last;
	}
	
	
}
