import java.awt.Stroke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.HandlerBase;

import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import abstractions.Arithmetic_Oprt_Class;
import abstractions.Brackets_Class;
import abstractions.Candidate_Delimiter_Class;
import abstractions.Digit_Class;
import abstractions.EmptyValues_Class;
import abstractions.Full_Text_Class;
import abstractions.Line_Break_Class;
import abstractions.Lower_Letter_Class;
import abstractions.MissingValues_Class;
import abstractions.Number_Class;
import abstractions.Quotation_Class;
import abstractions.Sequence_Digit_Class;
import abstractions.Sequence_LowerLetter_Class;
import abstractions.Sequence_UpperLetter_Class;
import abstractions.Space_Class;
import abstractions.Symbol_Class;
import abstractions.Text_Class;
import abstractions.Upper_Letter_Class;
import abstractions.WhiteSpace_Class;


public class Main_Class {
	
static int row_T = 1;
static int col_T = 20;
	
static int listSize;
static List<Integer> all_rows_list = new ArrayList<Integer>();
static float rowCount;
static String univocityDetetced_delimiter;
static String univocityDetetced_QUOTE;
static String univocityDetetced_ESCAPE;
static String univocityDetetced_Line;
static Map<Integer, String> recordWithIndices = new HashMap<Integer, String>();
static Map<Integer, List<String>> recordWithIndicesList = new HashMap<Integer, List<String>>();

static Map<Integer, String[]> indicesForTransformation = new HashMap<Integer, String[]>();

private static final EmptyValues_Class EMPTY_VALUES_CLASS = new EmptyValues_Class();
private static final Line_Break_Class LINE_BREAK_CLASS = new Line_Break_Class();
private static final Candidate_Delimiter_Class CANDIDATE_DELIMITER_CLASS = new Candidate_Delimiter_Class();


   public static void main(String[] args) {
		
		Main_Class main_method_object = new Main_Class();
		
		String inputFile = args[0];
		String cleanedFile = args[1];
		
		try {
			main_method_object.programsInitiator(inputFile, cleanedFile);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void programsInitiator(String inputFile, String cleanedFile) throws UnsupportedEncodingException, FileNotFoundException
	{
		 Main_Class programsInitiator_Object = new Main_Class();
		 PatternClassification patClassifiaction_Object = new PatternClassification();
		 PatternTransformation patTransformation_Object = new PatternTransformation();

		 List<String> columns_recordList = new ArrayList<>();
		 List<ComparePatternCombinations> wellFormed_Patterns = new ArrayList<>(); 
		 List<PatternStatistics> incremantal_Patterns = new ArrayList<PatternStatistics>();
		 List<PatternAlignmentResult> patterns_for_classification_results = new ArrayList<PatternAlignmentResult>();
		 
		 Set<Integer> wellFormed= new HashSet<Integer>();
		 Set<Integer> wanted= new HashSet<Integer>();
		 Set<Integer> unwanted= new HashSet<Integer>();
		 
		 columns_recordList = programsInitiator_Object.parseData(inputFile);        
		 
		 wellFormed_Patterns = programsInitiator_Object.dataManipulation(columns_recordList);  
	    
		 for(ComparePatternCombinations obj : wellFormed_Patterns)
		 {
			 wellFormed.addAll(obj.intersection);  
		 } 
		 
		 incremantal_Patterns =  programsInitiator_Object.patternManipulation(wellFormed_Patterns,  recordWithIndicesList);
		 
		 patterns_for_classification_results = patClassifiaction_Object.patternReciever(wellFormed_Patterns, incremantal_Patterns); 
	     
		 List<PatternAlignmentResult> patterns_to_Transform = new ArrayList<PatternAlignmentResult>();
		 
		 for(PatternAlignmentResult obj : patterns_for_classification_results)
		 {
			 if(obj.pairs_score <= 0.30)
			 {
				 wanted.addAll(obj.row_indices);  
				 patterns_to_Transform.add(obj);
			 }
			 else
			 {
				 unwanted.addAll(obj.row_indices); 
			 }	 
		 }
				 
		 patTransformation_Object.patternReciever(cleanedFile, patterns_to_Transform , Main_Class.indicesForTransformation, new ArrayList<Integer>(wanted), new ArrayList<Integer>(wellFormed));
		 
		 System.out.println("The program has executed successfully!");
	}
	
	public List<ComparePatternCombinations> dataManipulation(List<String> columnList)
	{
		   PatternGeneration patternGeneration_object = new PatternGeneration();  
		   PruningPatterns  pruningPatterns_object = new PruningPatterns();     
		   PatternSchema patternSchema_Object = new PatternSchema();        
		   
		   List<ComparePatternCombinations> outputPatterns = new ArrayList<>(); 
		   
		   Map<Integer, List<List<Object>>> map_Combined_listoflist_results = new LinkedHashMap<Integer, List<List<Object>>>(); 
		   map_Combined_listoflist_results = patternGeneration_object.patternComputation(columnList, listSize, univocityDetetced_delimiter); 
		   
		   
		   List<List<Object>> optimal_pattern = new ArrayList<List<Object>>(); 
		   optimal_pattern = pruningPatterns_object.patternWeights_patternPruning(map_Combined_listoflist_results, col_T, row_T, rowCount); 
		   
		   outputPatterns = patternSchema_Object.schemaGeneration(row_T, all_rows_list.size(), optimal_pattern);  
		   
			
		   return outputPatterns;
	}	
    
	public List<PatternStatistics> patternManipulation(List<ComparePatternCombinations> cpcList, Map<Integer, List<String>> recordWithIndicesList)
	{
		 List<PatternStatistics> patternStatistics = new ArrayList<PatternStatistics>();
//		 for(ComparePatternCombinations cp: cpcList)
//		 {
//			 patternStatistics.add(new PatternStatistics(cp.abstrcationInstances, cp.intersection, cp.intersection.size()));
//		 }
		 
		RecordClassifier rc_Object = new RecordClassifier();
		List<Integer> illFormedIndicies = rc_Object.ill_well_classification(cpcList, all_rows_list);
		
		boolean flag_illFormed = false;
		while(illFormedIndicies.size() > 0 &&  flag_illFormed == false)
		{
			Map<Integer,Integer> indexMatrix = new HashMap<Integer, Integer>();
			indexMatrix = IndexMatrix.indicesGenerator(illFormedIndicies);
			
			List<ComparePatternCombinations> testList = new ArrayList<ComparePatternCombinations>();
			testList = PatternRegeneration.patternRegeneration(Main_Class.recordWithIndicesList, illFormedIndicies);
			
			if(testList == null || testList.isEmpty())
			{
				flag_illFormed = true;
			}
			else
			{
				for(ComparePatternCombinations cp: testList)
				{
					List<Integer> indexer = new ArrayList<Integer>();
					List<Integer> updatedParsed = new ArrayList<Integer>();
					
					indexer.addAll(cp.intersection);
					for(int i : indexer)
					{
						updatedParsed.add(indexMatrix.get(i));
					}
					
					cp.intersection.removeAll(cp.intersection);
					cp.intersection.addAll(updatedParsed);
					
					patternStatistics.add(new PatternStatistics(cp.abstrcationInstances, cp.intersection, cp.intersection.size()));
				}
				
				illFormedIndicies = rc_Object.ill_well_classification(testList, illFormedIndicies);
			}
			
		}
	    
		Map<Integer, List<String>> uniquelybrokenRecords = new LinkedHashMap<Integer, List<String>>();
		uniquelybrokenRecords = patCounter(illFormedIndicies, Main_Class.recordWithIndicesList);  
		
		
		class ListComparator implements Comparator<PatternStatistics> {

			  @Override
			  public int compare(PatternStatistics o1, PatternStatistics o2) {					
			      
			    return Integer.compare(o2.size, o1.size);
			  }

			}   
		
     	Collections.sort(patternStatistics, new ListComparator());
//		for(PatternStatistics ps: patternStatistics)
//		 {
//			Collections.sort(ps.patternIndices);
//			System.out.println(PatternStatistics.getStringRepresentation(ps)+""+ ps.patternIndices+ "   "+ps.patternIndices.size());
//		   
//		 }
		
		return patternStatistics;
	}
	
	public static Map<Integer, List<String>> patCounter (List<Integer> rowList, Map<Integer, List<String>> rowIndices)
	{
		Map<Integer, List<String>> extractedRecords = new LinkedHashMap<Integer, List<String>>();
		extractedRecords = PatternRegeneration.extractRecords(rowIndices, rowList);
	
		return  extractedRecords;
	}
	
	public List<String> parseData(String inputFile)
	{
		  List<String> recordList = new ArrayList<>();
		  List<String> columns_recordList = new ArrayList<>();
		  
		  listSize = 0;
		  all_rows_list.clear();
		  
		   CsvParserSettings settings = new CsvParserSettings();
		   settings.detectFormatAutomatically();
		   settings.setIgnoreLeadingWhitespaces(false);
		   settings.setIgnoreTrailingWhitespaces(false);
		   settings.setKeepQuotes(true);
		   settings.setKeepEscapeSequences(true);
		   settings.setQuoteDetectionEnabled(false);
		   settings.setSkipEmptyLines(false);
		   settings.setCommentProcessingEnabled(false);
		   settings.setDelimiterDetectionEnabled(true, ',',';','|',':','\t');  
		   
		   CsvParser parser = new CsvParser(settings);
		   List<String[]> rows = parser.parseAll(new File(inputFile));
		   
		   CsvFormat format = parser.getDetectedFormat(); 
		   
		   univocityDetetced_delimiter = format.getDelimiterString();
		   univocityDetetced_QUOTE = Character.toString(format.getQuote());
		   univocityDetetced_ESCAPE = Character.toString(format.getQuoteEscape());
		   univocityDetetced_Line = String.valueOf(format.getLineSeparator());
		   List<Record> allRecords = parser.parseAllRecords(new File(inputFile));
		   
	//	   for(Record record : allRecords){
	//		   //recordList.add(record.toString());
	//		   System.out.println(record);
	//	   }
	      
		  rowCount= allRecords.size();  
		  
		   int rowNumber = 0;
		  
		   for(String[] valueNew : rows){  
			   
			   // pattern transformation
			   List<String[]> recordsForCleaning = new ArrayList<>();
			   String[] valuesforTransformation ;
			   
			   List<String> temp = new ArrayList<String>();
			   StringBuilder tempStoreRecords = new StringBuilder();
			   int counter = 0;
			  
			   if(valueNew.length > listSize)
			   {										
				   listSize = valueNew.length;
			   }
			   for(String value : valueNew){
				   if(counter !=valueNew.length-1)
				   {
					   // row based approach
					   tempStoreRecords.append(value + format.getDelimiter());
					   // column based approach
					   columns_recordList.add(value);
					   columns_recordList.add(format.getDelimiterString());
					  
					   temp.add(value);
					   temp.add(format.getDelimiterString());
					   
					   counter++;
				   }
				   else
				   {   
					   // row based approach
					    tempStoreRecords.append(value);
					   // column based approach
						columns_recordList.add(value); 
					    columns_recordList.add("\n");  
						
					    temp.add(value);
						temp.add("\n");
				   }
				 
			   }
			   recordList.add(tempStoreRecords.toString()); // row based approach
			   rowNumber++;
			   all_rows_list.add(rowNumber);
			   recordWithIndicesList.put(rowNumber, temp);
			   
			   // list of records with their indices for transformation

				String[] testArr = new String[temp.size()];
				testArr = temp.toArray(new String[0]);
				
				indicesForTransformation.put(rowNumber, testArr);
			}
		   
		   listSize = listSize+ listSize; 
		   
		   // row based records printing
		   int counterForMap = 0;
		   for(String record : recordList){
			 //System.out.println("new row "+ record);
			   recordWithIndices.put(counterForMap+1, record);
			   counterForMap++;
		   }
		   
		   // column based record printing
	//	   for(String column : columns_recordList){
	//			 System.out.println("column value  "+column);
	//		   }
		   
		  for(int i = 0; i < columns_recordList.size(); i++)
		  {
			  //System.out.print(columns_recordList.get(i));
	//		  for(int j = 0; j< columns_recordList.get(i).length(); j++)
	//		  {
	//			  char ch = columns_recordList.get(i).charAt(j);	// to print column values character by character 
	//			  System.out.println((int)ch);
	//		  }
			  
		  }
			  
	 return columns_recordList;
	 
	}
	
	
	
} 
