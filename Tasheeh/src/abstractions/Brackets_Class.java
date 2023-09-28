package abstractions;
public class Brackets_Class implements IAbstraction {
	
	private String result; 
	
	public String getResult() {
		return result;
	}

	public void setResult(char input) {
		
		if((int)input == 40 || (int)input == 91 || (int)input == 123 )
			result = "(";
			
		else if((int)input == 93|| (int)input == 41 || (int)input == 125)
			result = ")";
		
	}

	@Override
	public String toString() {
		
	  return getRepresentation();	
	}
	

	@Override
	public String getRepresentation() {
		
		return "<BRKT>";
	}

}
