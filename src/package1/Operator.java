package package1;

public class Operator extends Token {
	
	String operator;
	int precedence = -2;

	public Operator(String operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return operator;
	}

	@Override
	public String getType() {
		return "Operator";
	}

}
