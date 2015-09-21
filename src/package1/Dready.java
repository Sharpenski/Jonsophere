package package1;

import java.util.Random;

public class Dready {

	int[] registers = new int[10];
	int reg = 0;
	
	public void incR() {
		if(reg < 9) {
			reg++;
		} else {
			System.out.println("You have succeeded the number of available registers.");
		}
	}
	
	public String traverseTree(Tree tree) {
		Token cur = tree.root;
		if(cur.getType().equals("Number")) {
			return newLeaf((Number)cur); //a number is a leaf so has no children
		} else if(cur.getType().equals("Operator")) {
			String L = traverseTree(tree.left);
			String R = traverseTree(tree.right);
			if(!tree.left.root.getType().equals("Number") || !tree.right.root.getType().equals("Number")) {
				return newOp((Operator)cur) + ", R" + (reg - 3) + ", R" + (reg - 2);
			}
			return newOp((Operator)cur) + ", " + L + ", " + R;
		} else {
			System.err.println("An error occured, check your source code and the generated tree.");
			System.exit(0);
		}
		return "hahahahaha";
	}
	
	public String newLeaf(Number cur) {
		return Integer.toString(cur.number);
	}
	
	//remember to evaluate left-side then right-side before performing operation
	public String newOp(Operator cur) {
		String msg = "ADD R" + reg;
		incR();
		return msg;
	}
	
	public String newOther() {
		System.out.println("other");
		return null;
	}

	public static void main(String[] args) {
		Tree t = new Tree(new Operator("+"), new Tree(new Number(2), null, null), new Tree(new Number(3), null, null));
		Tree u = new Tree(new Operator("-"), new Tree(new Number(44), null, null), new Tree(new Number(12), null, null));
		Tree l = new Tree(new Operator("+"), t, u);
		Dready d = new Dready();
		System.out.println(d.traverseTree(l));
		Random r = new Random();
		System.out.println("Random number: " + r.nextInt(5));
	}


}
