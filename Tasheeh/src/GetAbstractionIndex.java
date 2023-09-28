import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import abstractions.Arithmetic_Oprt_Class;
import abstractions.Brackets_Class;
import abstractions.Digit_Class;
import abstractions.EmptyValues_Class;
import abstractions.Full_Text_Class;
import abstractions.Line_Break_Class;
import abstractions.Lower_Letter_Class;
import abstractions.MissingValues_Class;
import abstractions.Number_Class;
import abstractions.Padded_Class;
import abstractions.Quotation_Class;
import abstractions.Sequence_Digit_Class;
import abstractions.Sequence_LowerLetter_Class;
import abstractions.Sequence_UpperLetter_Class;
import abstractions.Space_Class;
import abstractions.Symbol_Class;
import abstractions.Text_Class;
import abstractions.Upper_Letter_Class;
import abstractions.WhiteSpace_Class;

public class GetAbstractionIndex {

	private static final Number_Class NUMBER_CLASS = new Number_Class();
	private static final MissingValues_Class MISSING_VALUES_CLASS = new MissingValues_Class();
	private static final EmptyValues_Class EMPTY_VALUES_CLASS = new EmptyValues_Class();
	private static final Line_Break_Class LINE_BREAK_CLASS = new Line_Break_Class();
	private static final Full_Text_Class FULL_TEXT_CLASS = new Full_Text_Class();
	private static final Text_Class TEXT_CLASS = new Text_Class();
	private static final WhiteSpace_Class WHITESPACE_CLASS= new WhiteSpace_Class();
	private static final Sequence_Digit_Class SEQUENCE_DIGIT_CLASS = new Sequence_Digit_Class();
	private static final Sequence_LowerLetter_Class SEQUENCE_LOWER_LETTER_CLASS = new Sequence_LowerLetter_Class();
	private static final Sequence_UpperLetter_Class SEQUENCE_UPPER_LETTER_CLASS = new Sequence_UpperLetter_Class();
	private static final Symbol_Class SYMBOL_CLASS = new Symbol_Class();
	private static final Arithmetic_Oprt_Class ARITHMETIC_OPRT_CLASS = new Arithmetic_Oprt_Class();
	private static final Brackets_Class BRACKETS_CLASS = new Brackets_Class();
	private static final Space_Class SPACE_CLASS = new Space_Class();
	private static final Quotation_Class QUOTATION_CLASS = new Quotation_Class();
	private static final Digit_Class DIGIT_CLASS = new Digit_Class();
	private static final Upper_Letter_Class UPPER_LETTER_CLASS = new Upper_Letter_Class();
	private static final Lower_Letter_Class LOWER_LETTER_CLASS = new Lower_Letter_Class();
	private static final Padded_Class PADDED_CLASS = new Padded_Class();
	
	
	static List<Object> literalLetters = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
	
	static List<Object> literals_for_Text = Arrays.asList('-','_','\'','\\','/','.','&',' ');
	
	static List<Object> literal_UpperLetters = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
	
	static List<Object> literal_LowerLetters = Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
	
	static List<Object> literalDigits = Arrays.asList('0','1','2','3','4','5','6','7','8','9');
	
	static List<Object> literalNumber = Arrays.asList(',','.','+','-');
	
	static List<Object> literalquotation = Arrays.asList('"');
	
	static List<Object> literalSymbol = Arrays.asList('$' , '#' , '.' , '?' , '@' , '\\' , '^' , '\'' , '~' , '_' , '`' , '&' , '!' , '"' , ',' , ';' , ':' ,'\t', '|');
	
	static List<Object> literalBracket = Arrays.asList('[' , ']' , '{' , '}' , '(' , ')' );
	
	static List<Object> literalArithmatic = Arrays.asList('*','+','-', '/','%', '=','<','>');
	
	static List<Object> literalLineBreak = Arrays.asList((char)10, (char)13);
	
	static List<Object> literalnull = Arrays.asList((Object)null);
	
	public Map<Integer, List<Integer>> abstractionIndex(List<Object> readyto_Transform, List<Object> string_TO_object)
	{
		Map<Integer,List<Integer>> delete_entries_Map = new LinkedHashMap<Integer, List<Integer>>(); // get index positions for the input string based on the abstractions
		for(int i= 0; i<readyto_Transform.size(); i++)
    	{
    		List<Integer> entriesLIST = new ArrayList<Integer>();
    		
    		if(!(readyto_Transform.get(i) instanceof Character)) 
    		{
    			if(readyto_Transform.get(i).toString() == FULL_TEXT_CLASS.toString())
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		            	entriesLIST.add(j);
		            	string_TO_object.set(j, PADDED_CLASS);
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			 }
  
    			if(readyto_Transform.get(i).toString() == NUMBER_CLASS.toString()) 
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if( literalDigits.contains(string_TO_object.get(j)) || literalNumber.contains(string_TO_object.get(j)) )
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			 }
    			
    			if(readyto_Transform.get(i).toString() == EMPTY_VALUES_CLASS.toString()) 
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if( literalnull.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			 }
    			
    			if(readyto_Transform.get(i).toString() == LINE_BREAK_CLASS.toString()) 
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if( literalLineBreak.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			 }
    			
    			if(readyto_Transform.get(i).toString() == ARITHMETIC_OPRT_CLASS.toString())
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if( literalArithmatic.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			 }
    			
    			if(readyto_Transform.get(i).toString() == BRACKETS_CLASS.toString()) 
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if( literalBracket.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			 }
    			
    			if(readyto_Transform.get(i).toString() == SYMBOL_CLASS.toString()) 
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if( literalSymbol.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			 }
    			
    		   if(readyto_Transform.get(i).toString() == TEXT_CLASS.toString()) 
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if( literalLetters.contains(string_TO_object.get(j)) || literals_for_Text.contains(string_TO_object.get(j)) )
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			 }
    			if(readyto_Transform.get(i).toString() == SPACE_CLASS.toString() || readyto_Transform.get(i).toString() == WHITESPACE_CLASS.toString())
    			 {
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if(string_TO_object.get(j).toString().matches("\\p{Blank}"))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			}
    			
    			if(readyto_Transform.get(i).toString() == DIGIT_CLASS.toString() || readyto_Transform.get(i).toString() == SEQUENCE_DIGIT_CLASS.toString())     			 
    			{
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if(literalDigits.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			}
    		   if(readyto_Transform.get(i).toString() == UPPER_LETTER_CLASS.toString() || readyto_Transform.get(i).toString() == SEQUENCE_UPPER_LETTER_CLASS.toString()) 
    			 {
    				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if(literal_UpperLetters.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
    				delete_entries_Map.put(i, entriesLIST);
    			}
    		  if(readyto_Transform.get(i).toString() == LOWER_LETTER_CLASS.toString() || readyto_Transform.get(i).toString() == SEQUENCE_LOWER_LETTER_CLASS.toString()) // === Lower Letters ===
  			  {
  				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if(literal_UpperLetters.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
  				delete_entries_Map.put(i, entriesLIST);
  			  }
    		  if(readyto_Transform.get(i).toString() == QUOTATION_CLASS.toString()) // === Quotation ===
  			  {
  				for(int j = 0; j < string_TO_object.size(); j++)
				    {
		              if(literalquotation.contains(string_TO_object.get(j)))
		              {
		            	  if(entriesLIST.isEmpty())
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
		            	  {
		            		  entriesLIST.add(j);
		            		  string_TO_object.set(j, PADDED_CLASS);
		            	  }
		              }
				    }
  				delete_entries_Map.put(i, entriesLIST);
  			   }
    		}
    		else if(readyto_Transform.get(i) instanceof Character)  
    		{
    			for(int j = 0; j < string_TO_object.size(); j++)
			    {
	              if(string_TO_object.get(j).equals(readyto_Transform.get(i)))
	              {
	            	  if(entriesLIST.isEmpty())
	            	  {
	            		  entriesLIST.add(j);
	            		  string_TO_object.set(j, PADDED_CLASS);
	            	  }
	            	  else if(entriesLIST.get(entriesLIST.size()-1) == j-1) 
	            	  {
	            		  entriesLIST.add(j);
	            		  string_TO_object.set(j, PADDED_CLASS);
	            	  }
	              }
			    }
				delete_entries_Map.put(i, entriesLIST);
    		}
    	}
		
		return delete_entries_Map;
	}
	
}
