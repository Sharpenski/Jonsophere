package package1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Luther {
	
	public String username;
	Utility ut = new Utility();
	Tokenizer tknz = new Tokenizer();
	
	public Luther(String username) {
		this.username = username;	
	}
	
	public ArrayList<String> readFile(String filename) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		ArrayList<String> lines = new ArrayList<String>();
		while((line = br.readLine()) != null) {
			lines.add(line);
		}
		br.close();
		return lines;
	}
	
	public ArrayList<Token> createStream(ArrayList<String> lines) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		for(String line : lines) {
			tokens.addAll(tknz.tokenize(line));
		}
		return tokens;
	}

	public static void main(String[] args) {
		Luther calc = new Luther(args[0]);
		System.out.println("Welcome " + calc.username + "!");
		System.out.println("This program is a parser for Jonsophere, "
				+ "a programming language for doing basic arithmetic.");
		System.out.println("Enter the name of the text file (written in Jonsophere) you wish to parse.");

		Scanner input = new Scanner(System.in);
		ArrayList<String> lines = new ArrayList<String>();
		try {
			lines = calc.readFile(input.nextLine());
		} catch (Exception e) {
			System.err.println("Sorry, the file was not found.");
		}
		
		System.out.println("The tokens:");
		ArrayList<Token> tokens = new ArrayList<Token>();
		tokens = calc.createStream(lines);
		System.out.println(tokens);
		
		Jonsopherer j = new Jonsopherer(tokens);
		Tree t = j.Term();
		Tree.printTree(t, "");
	}
	
}
