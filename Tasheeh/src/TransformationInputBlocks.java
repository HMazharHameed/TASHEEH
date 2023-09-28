import java.util.List;

import javafx.util.Pair;

public class TransformationInputBlocks {

	List< Pair<String, Pair<List<Object>, List<Object>>> > blocks;
	List<Pair<Integer, List<Integer>>>  positions;
	List<Integer> row_indices;
	
	
	
	public TransformationInputBlocks(List<Pair<String, Pair<List<Object>, List<Object>>>> blocks,
			List<Pair<Integer, List<Integer>>> position, List<Integer> row_indices) {
		super();
		this.blocks = blocks;
		this.positions = position;
		this.row_indices = row_indices;
	}
	
	public String toString() {
		return "TransformationInputBlocks [blocks=" + blocks + ", positions=" + positions + ", row_indices="
				+ row_indices + "]";
	}
}
