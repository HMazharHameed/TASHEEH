import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexMatrix {

	public static Map<Integer, Integer> indicesGenerator(List<Integer> illFormedIndicies)
	{
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int counter = 1;
		for(int in: illFormedIndicies)
		{
			map.put(counter, in);
			counter++;
		}
		
		return map;
	}
}
