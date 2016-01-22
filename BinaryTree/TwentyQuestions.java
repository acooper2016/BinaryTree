import java.io.*;
import java.util.Scanner;
import java.util.Iterator;

public class TwentyQuestions
{

	public static void main(String[] args)
	{
		/*Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to 20 questions!");
		while(true)
		{
			System.out.println("would you like to play again?");
			String answer = reader.next();
			if(answer.equals("y"))
				playGame(loadData());
			else if(answer.equals("n"))
				break;
			else
				System.out.println("Please enter y or n");
		}
		System.out.println("goodbye!");*/
		
		BinaryTree<String> root = new BinaryTree<String>("is it alive?");
		root.setLeft(new BinaryTree<String>("toaster"));
		root.setRight(new BinaryTree<String>("tiger"));
		playGame(root);
	}

	/**
	*Runs a single session of the game.  Navigates a binary tree of questions, in which the left pointer represents a no 
	*answer and the right pointer represents a yes answer.  Leaves of the tree are objects.  If an incorrect guess is made
	*by the game, will alter the tree to add another question input by the user, accommodating for the wrong guess and the 
	*correct answer.  Takes in an existing BinaryTree of strings containing this data, and writes the tree to a text document upon 
	*completion.
	*
	*@param data BinaryTree of strings, containing each question and final answer.
	*/
	private static void playGame(BinaryTree<String> data)
	{
		System.out.println(data);
		Scanner reader = new Scanner(System.in);
		BinaryTree<String> lastNode;
		BinaryTree<String> currentNode = data;
		while(true)
		{
			if(!currentNode.isLeaf())
			{
				System.out.println(currentNode.getValue());
				System.out.println("y/n");
				String answer = reader.next();
				if(answer.equals("y"))
				{
					lastNode = currentNode;
					currentNode = currentNode.getRight();
				}
				else if(answer.equals("n"))	
				{
					lastNode = currentNode;
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
		
		System.out.println("Is it a " + currentNode.getValue() + "?");
		String answer = reader.next();
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
		String rightAnswer = reader.next();
		
		System.out.println("What is a good question that separates your answer from my guess?");
		String newQuestion = reader.next();
		
		System.out.println("Is the answer to this question for your object y or n?");
		String response;
		while(true)
		{
			response = reader.next();
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
		
		System.out.println(data);
		
		//saveData(data);	
	}
	
	public static BinaryTree<String> loadData()
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
		
		String dataString = "";
		
		while(input.hasNextLine())
		{
			dataString += input.nextLine();
		}
		
		Vector<String> inorderList = new Vector<String>();
		String addend = "";
		
		for(int i = 0; i < dataString.length(); i++)
		{
			if(i < dataString.length() - 5 && dataString.substring(i, i + 6).equals("null&"))
			{
				inorderList.add(null);
				i += 4;
			}
			else if(dataString.charAt(i) == '#')
			{	
				inorderList.add(addend);
				addend = "";
			}
			else
			{
				addend += dataString.charAt(i);
			}
		}
		
		BinaryTree<String> root = new BinaryTree<String>(inorderList.get(inorderList.size() / 2));
				
		constructTree(root, inorderList, inorderList.size() / 2, (inorderList.size() / 4) + 1);		
		
		return root;
	}
	
	private static void constructTree(BinaryTree<String> root, Vector<String> items, int currInd, int iterationSize)
	{
		System.out.println(iterationSize);
		if(iterationSize >= 1)
		{	
			root.setLeft(new BinaryTree<String>(items.get(currInd - iterationSize)));
			root.setRight(new BinaryTree<String>(items.get(currInd + iterationSize)));
			constructTree(root.getLeft(), items, currInd - iterationSize, iterationSize /2);
			constructTree(root.getRight(), items, currInd + iterationSize, iterationSize / 2);
		}
	}

	/**
	*Saves the binary tree used in a single game of Twenty Questions into a document.  This document can be used
	*to start other games of TwentyQuestions with a consistently expanding dataset between sessions.  Uses FillTree to 
	*create a tree that can be consistently stored.  Uses the phrase "null&" to represent a null branch, chosen because it is highly
	*unlikely to be a question or answer.  
	*
	*@param data BinaryTree containing the data from a single game of TwentyQuestions.
	*/
	private static void saveData(BinaryTree<String> data)
	{
		fillTree(data, data.height() - 1); 	
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
		//System.out.println("arrival");
		
		Iterator iterator = data.inorderIterator();
		//System.out.println("get");

		while(iterator.hasNext());
		{
			//System.out.println("loop");
			String writeString;
			if(iterator.next() == null)
				writeString = "null&";
			else
				writeString =(String) iterator.next();
			
			writer.append(writeString);
			writer.append("#");
		}
		
		//System.out.println("done");
	}	
	
	/**
	*Takes in a BinaryTree of strings and adds branches until it is full.  New branches contain null.  
	*Used to easily parse a BinaryTree of 20q data into a document of saved data.
	*
	*@param data Root of the binary tree to be filled.
	*@param numDeep counter used to make sure branches are not added past data's height.
	*/
	private static void fillTree(BinaryTree<String> data, int numDeep)
	{
		if(numDeep > 0)
		{
			if(data.getLeft() == null)
				data.setLeft(new BinaryTree<String>(null));
			if(data.getRight() == null)
				data.setRight(new BinaryTree<String>(null));
			fillTree(data.getLeft(), numDeep - 1);
			fillTree(data.getRight(), numDeep - 1);
		}
		
		
		
	}
}
