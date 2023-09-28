package abstractions;

public class Digit_Class implements IAbstraction{

	@Override
	public String toString() {
		return getRepresentation();
	}

	@Override
	public String getRepresentation() {
		
		return "<D>";
	}
}
