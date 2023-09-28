import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import abstractions.Candidate_Delimiter_Class;
import abstractions.Line_Break_Class;
import abstractions.Padded_Class;

public class Csv_Writer {

	
	
	public static void cleanCSV (Map<Integer, String[]> printingPatterns, String cleanedFile)
	{
		String _results = cleanedFile;
		
		try (Writer outputWriter = new OutputStreamWriter(new FileOutputStream(new File(_results)),"UTF-8"))
		{
			for (Entry<Integer, String[]> entry : printingPatterns.entrySet()) 
			{
				String[] testing = entry.getValue();
				List<Object> list = new ArrayList<Object>();
				for (int j = 0; j < testing.length; j++) 
				{
					if (testing[j] == null) // for null values
					{
						list.add("");
					}
					else if (!(testing[j].toString().equals(Main_Class.univocityDetetced_Line)) &&!(testing[j].toString().equals(Main_Class.univocityDetetced_delimiter))
							&& !(testing[j].toString().equals(new Padded_Class().toString()))) {
						list.add(testing[j]);
					}
				}
				
				for(int i = 0; i<list.size(); i++)
				{
					outputWriter.write(list.get(i)+"");
					if(i !=list.size()-1)
					  outputWriter.write(Main_Class.univocityDetetced_delimiter);
					if(i ==list.size()-1)
					  outputWriter.write(Main_Class.univocityDetetced_Line);
				}
			}
			outputWriter.flush();
			outputWriter.close();
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	 }
	
	
}
