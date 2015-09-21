package package1;

public class Tree {
	
	public Token root;
	public Tree left;
	public Tree right;

	public Tree(Token root, Tree left, Tree right) {
		this.root = root;
		this.left = left;
		this.right = right;
	}
	
	public Tree(Number root) {
		this.root = root;
		left = null;
		right = null;
	}
	
	public Tree() {
		root = null;
		left = null;
		right = null;
	}
	
	public static void printTree(Tree tree, String indent) {
		if(tree != null) {
			System.out.println(indent + tree.root);
			printTree(tree.left, indent + "l>");
			printTree(tree.right, indent + "r>");
		} 
	}

	public static void main(String[] args) {
		System.out.println("Tree - main method:");
		Tree test = new Tree(new Operator("+"), new Tree(new Number(1), null, null), new Tree(new Number(2), null, null));
		printTree(test, "");
		Dready dready = new Dready();
		dready.traverseTree(test);
	}
}
