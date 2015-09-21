package package1;

public class Other extends Token {
	
	String other;

	public Other(String other) {
		this.other = other;
	}
	
	@Override
	public String toString() {
		return other;
	}

	@Override
	public String getType() {
		return "Other";
	}

}
