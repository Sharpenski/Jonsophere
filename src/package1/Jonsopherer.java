package package1;

import java.util.ArrayList;

public class Jonsopherer {
	
	public ArrayList<Token> tokens;
	public Token current;
	public int i;
	public int MAX;

	public Jonsopherer(ArrayList<Token> tokens) {
		this.tokens = tokens;
		current = tokens.get(0);
		i = 0;
		MAX = tokens.size();
	}
	
	public void advance(String type, Token cur) {
		if(cur.getType().equals(type)) {
			System.out.println("advance(): " + cur.toString() + " - success!");
		} else {
			System.err.println("advance(): The token, " + cur.toString() 
					+ ", does not match the expected type. Please check your code and try again.");
		}
	}
	
	public void step() {
		if(i < MAX - 1) {
			current = tokens.get(++i);
		} else {
			System.err.println("step(): no more tokens to step into");
		}
	}
	
	public boolean look() {
		if(i < MAX - 1) {
			return true;
		}
		return false;
	}
	
	public String nextToken() {
		return tokens.get(i+1).toString();
	}
	
	//number, operator, terminator, equality
	public Tree Term() {	
		Tree root = null, num = null;
		num = Number();
		step();
		if(current.getType().equals("Operator")) {
			root = nextTerm();
			step();
			root.left = num;
		} else {
			root = num;
		}
		advance("Other", current);
		if(look() == false) {
			return new Tree(current, root, null);
		}
		Token tempCurrent = current;
		step();
		return new Tree(tempCurrent, root, Term());
	}
	
	public Tree nextTerm() {
		Token root;
		Tree right = null, temp;
		advance("Operator", current);
		root = current;
		step();
		temp = Number();
		if(look() == true && !nextToken().equals(";")) {
			step();
			if(current.getType().equals("Operator")) {
				right = nextTerm();
				right.left = temp;
				return new Tree(root, temp, right);
			} 
		} 
		return new Tree(root, null, temp);
	}
	
	//numbers will always be leaves
	public Tree Number() {
		advance("Number", current);
		return new Tree(current, null, null);
	}
	
	public static void main(String[] args) {
		System.out.println("In the main method of...The Jonsopherer!");
		
		ArrayList<Token> tokens = new ArrayList<Token>();
		tokens.add(new Number(8));
		tokens.add(new Operator("+"));
		tokens.add(new Number(1));
		tokens.add(new Operator("*"));
		tokens.add(new Number(156));
		tokens.add(new Other(";"));
		
		Jonsopherer jonsy = new Jonsopherer(tokens);
		Tree tree = jonsy.Term();
		Tree.printTree(tree, "");
	}

}
