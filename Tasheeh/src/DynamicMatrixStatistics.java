import java.util.Arrays;
import java.util.List;

public class DynamicMatrixStatistics {

	public List<List<Object>> dominantPattern ;
	public List<List<Object>> potentialDominantPattern ;
	public List<Integer>  crossponding_Rows; 
	public int[][] dynamicMatrix;
	public float[][] dynamicMatrix_f;
	public int cost;
	public float cost_f;
	public DynamicMatrixStatistics(List<List<Object>> dominantPattern, List<List<Object>> potentialDominantPattern, List<Integer> rows,
			int[][] dynamicMatrix, int cost) {
		super();
		this.dominantPattern = dominantPattern;
		this.potentialDominantPattern = potentialDominantPattern;
		this.crossponding_Rows = rows;
		this.dynamicMatrix = dynamicMatrix;
		this.cost = cost;
	}
	
	public DynamicMatrixStatistics(List<List<Object>> dominantPattern, List<List<Object>> potentialDominantPattern, List<Integer> rows,
			float[][] dynamicMatrix_f, float cost_f) {
		super();
		this.dominantPattern = dominantPattern;
		this.potentialDominantPattern = potentialDominantPattern;
		this.crossponding_Rows = rows;
		this.dynamicMatrix_f = dynamicMatrix_f;
		this.cost_f = cost_f;
	}

	@Override
	public String toString() {
		return "DynamicMatrixStatistics [dominantPattern=" + dominantPattern + ", potentialDominantPattern="
				+ potentialDominantPattern + ", crossponding_Rows=" + crossponding_Rows + ", dynamicMatrix="
				+ Arrays.toString(dynamicMatrix) + ", dynamicMatrix_f=" + Arrays.toString(dynamicMatrix_f) + ", cost="
				+ cost + ", cost_f=" + cost_f + "]";
	}

	
	
}
