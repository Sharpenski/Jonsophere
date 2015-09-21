package package1;

public class Number extends Token {
	
	int number;

	public Number(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return Integer.toString(number);
	}

	@Override
	public String getType() {
		return "Number";
	}

}
