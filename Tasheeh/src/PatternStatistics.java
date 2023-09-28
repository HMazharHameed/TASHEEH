
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public class PatternStatistics {

	public List<List<Object>> patternReprsentation;
	public List<Integer> patternIndices;
	public int size;
	
	public PatternStatistics(List<List<Object>> abstrcationInstances, List<Integer> patternIndices, int size) {
		super();
		this.patternReprsentation = abstrcationInstances;
		this.patternIndices = patternIndices;
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "PatternStatistics [patternReprsentation=" + patternReprsentation + ", patternIndices=" + patternIndices
				+ ", size=" + size + "]";
	}
}
