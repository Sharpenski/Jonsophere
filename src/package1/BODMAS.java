package package1;

import java.util.ArrayList;

/**
 * @author tobydobbs
 * The BODMAS class is being used to test an idea for incorporating BODMAS rules
 * into the parsing of arithmetic expressions. 
 */
public class BODMAS {
	
	int i = 0;
	Token current;
	Operator last = null;
	ArrayList<Token> theTokens;
	final int MAX;
	
	public BODMAS(ArrayList<Token> theTokens) {
		this.theTokens = theTokens;
		current = theTokens.get(0); //set the current token to be the first in the stream
		MAX = theTokens.size();
	}
	
	//method to traverse the tree
	public void traverseTree(Tree t) {
		if(t != null) {
			current = t.root;
			String type = current.getType();
			if(type.equals("Operator")) {
				/*System.out.println("The operator, " + current + ", was assigned the priority rank: " 
										+ ((Operator) current).precedence);*/
				traverseTree(t.left);
				traverseTree(t.right);
			} else if(type.equals("Other")) {
				System.out.println("Declaration made.");
				traverseTree(t.left);
				traverseTree(t.right);
			} else if(type.equals("Number")) {
				System.out.println("Reached the leaf: " + current + " (The tree does not branch any further)");
			}
		} else {
			System.out.println("\nThe branch was found to be null so there is nothing to traverse.\n");
		}
	}
	
	/**
	 * @param token
	 * @param type
	 * @return boolean value to express whether the token is correct or otherwise
	 * Used to check whether a token is the expected token type in the statement
	 */
	public boolean advance(Token token, String type) {
		if(token.getType().equals(type)) {
			System.out.println("advance(): " + token.toString() + " - success!");
			return true;
		} else {
			System.err.println("advance(): The token, " + token.toString() 
					+ ", does not match the expected type. Please check your code and try again.");
			return false;
		}
	}
	
	/**
	 * Steps into the next token
	 */
	public void step() {
		if(i < MAX - 1) {
			current = theTokens.get(++i);
		} else {
			System.err.println("There are no more tokens to step into.");
		}
	}
	
	/**
	 * @return boolean value to describe whether there are any more tokens to step into
	 */
	public String look() {
		if(i < MAX - 1) {
			return theTokens.get(i+1).getType();
		}
		return null;
	}
	
	/////
	
	public Tree Term() {
		Tree tree = new Tree(); //the tree to be returned
		Tree temp1 = new Tree(), temp2 = new Tree();
		
		temp1 = Number(); // move to the Number production rule
		step();
		temp2 = NextTerm(); // check for the extended term
		step();
		advance(current, "Other"); // check for a terminating symbol
		tree.root = current; // the root of the tree should be the terminator
		temp2.left = temp1;
		tree.left = temp2;
		if(look() != null) {
			step();
			tree.right = Term();
		}
		
		return tree; // the newly constructed tree is returned
	}
	
	/**
	 * @return a leaf node to represent the number
	 */
	public Tree Number() {
		advance(current, "Number");
		return new Tree((Number) current);
	}
	
	public Tree NextTerm() {
		Tree nt = new Tree();
		@SuppressWarnings("unused")
		Tree temp = new Tree();
		advance(current, "Operator");
		last = (Operator) current;
		nt.root = current;
		step();
		nt.right = Number();
		if(!look().equals("Other")) {
			step();
			NextTerm();
			/*nt.right = NextTerm();
			nt.right.left = temp;*/
		}
		return nt;
	}
	
	/////
	
	public static void main(String[] args) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		tokens.add(new Number(2));
		tokens.add(new Operator("*"));
		tokens.add(new Number(3));
		tokens.add(new Operator("/"));
		tokens.add(new Number(4));
		tokens.add(new Other(";"));
		
		BODMAS bd = new BODMAS(tokens);
		Tree temp;
		Tree.printTree((temp = bd.Term()), "");
		bd.traverseTree(temp);
	}

}
