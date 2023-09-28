package abstractions;
public class Candidate_Delimiter_Class implements IAbstraction{

	@Override
	public String toString() {
		return getRepresentation();
	}

	@Override
	public String getRepresentation() {
		
		return "<DEL>";
	}

}
