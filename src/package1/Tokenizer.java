package package1;

import java.util.ArrayList;

public class Tokenizer {
	
	final char[] digits = "0123456789".toCharArray();
	final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	final char[] operators = "+-/*".toCharArray();
	final char[] other = ";=".toCharArray();
	Utility ut = new Utility();
	
	public ArrayList<Token> tokenize(String term) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		char current;
		for(int i = 0; i < term.length(); i++) {
			current = term.charAt(i);
			if(current == ' ') {
				continue;
			} else if(ut.contains(digits, current)) {
				String num = getNum(term.substring(i));
				tokens.add(new Number(Integer.parseInt(num)));
				i += num.length() - 1;
			} else if(ut.contains(operators, current)) {	
				Operator toAdd = new Operator(Character.toString(current));
				assignPrec(toAdd);
				System.out.println("Precedence of '" + toAdd.operator + "': " + toAdd.precedence);
				tokens.add(toAdd);
			} else if(ut.contains(other, current)) {
				tokens.add(new Other(Character.toString(current)));
			} else {
				System.err.print("One (or more) of the tokens are incorrect.\nPlease check your code and try again");
				System.exit(0);
			}
		}
		return tokens;
	}
	
	/**
	 * @param op (an Operator)
	 * Used to assign a precedence rating to an operator.
	 * @return precedence value of operator. This is '-1' if the operator is not supported.
	 * Precedence value will be '-2' if unassigned.
	 */
	public void assignPrec(Operator op) {
		String type = op.toString();
		if(type.equals("/")) {
			op.precedence = 3;
		} else if(type.equals("*")) {
			op.precedence = 2;
		} else if(type.equals("+")) {
			op.precedence = 1;
		} else if(type.equals("-")) {
			op.precedence = 0;
		} else {
			System.err.println("The operator, " + op 
					+ ", is not supported. Please check your submitted source code.");
		}
	}
	
	public String getNum(String number) {
		int i = 0;
		String num = "";
		char cur;
		while(ut.contains(digits, cur = number.charAt(i))) {
			num = num + Character.toString(cur);
			i++;
		}
		return num;
	}
	
	public static void main(String[] args) {
		Tokenizer tknz = new Tokenizer();
		System.out.println(tknz.tokenize("12 - 4;"));
	}

}
