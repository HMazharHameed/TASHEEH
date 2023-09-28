import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class CostMatrix {
  
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
	private static final Candidate_Delimiter_Class CANDIDATE_DELIMITER_CLASS = new Candidate_Delimiter_Class();
	private static final Quotation_Class QUOTATION_CLASS = new Quotation_Class();
	private static final Digit_Class DIGIT_CLASS = new Digit_Class();
	private static final Upper_Letter_Class UPPER_LETTER_CLASS = new Upper_Letter_Class();
	private static final Lower_Letter_Class LOWER_LETTER_CLASS = new Lower_Letter_Class();
	
	static List<Object> indices ;
	static
	{
		indices =  new ArrayList<>();
		
		indices.add(CANDIDATE_DELIMITER_CLASS.toString()); // #1
		indices.add(ARITHMETIC_OPRT_CLASS.toString());   // #2 
		indices.add(SYMBOL_CLASS.toString()); // #3
		indices.add(LINE_BREAK_CLASS.toString()); // #4 
		indices.add(QUOTATION_CLASS.toString()); // #5
		indices.add(BRACKETS_CLASS.toString()); // #6
		indices.add(EMPTY_VALUES_CLASS.toString()); // #7 
		indices.add(DIGIT_CLASS.toString()); // #8 
		indices.add(UPPER_LETTER_CLASS.toString()); // #9
		indices.add(LOWER_LETTER_CLASS.toString()); // #10
		indices.add(SPACE_CLASS.toString()); // #11
		
		indices.add(SEQUENCE_DIGIT_CLASS.toString()); // #12
		indices.add(SEQUENCE_UPPER_LETTER_CLASS.toString()); // #13
		indices.add(SEQUENCE_LOWER_LETTER_CLASS.toString()); // #14
		indices.add(WHITESPACE_CLASS.toString()); // #15
		
		indices.add(NUMBER_CLASS.toString()); // #16
		indices.add(TEXT_CLASS.toString()); // #17
		indices.add(MISSING_VALUES_CLASS.toString()); // #18
		
		indices.add(FULL_TEXT_CLASS.toString()); // #19
		
	}

	static int[][] costs = {
				 { 0, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500 },	//Delimiter
				 { 500, 0, 50, 50, 50, 50, 250, 50, 50, 50, 50, 100, 100, 100, 100, 150, 150, 300, 400 },           // Arithmetic
				 { 500, 50, 0, 50, 50, 50, 250, 50, 50, 50, 50, 100, 100, 100, 100, 150, 150, 300, 400 },			// Symbol
				 { 500, 50, 50, 0, 50, 50, 250, 50, 50, 50, 50, 100, 100, 100, 100, 150, 150, 300, 400 },    		// Line break
				 { 500, 50, 50, 50, 0, 50, 250, 50, 50, 50, 50, 100, 100, 100, 100, 150, 150, 300, 400 }, 			// Quotation
				 { 500, 50, 50, 50, 50, 0, 250, 50, 50, 50, 50, 100, 100, 100, 100, 150, 150, 300, 400 },			// Bracket
				 { 500, 250, 250, 250, 250, 250, 0, 250, 250, 250, 250, 250, 250, 250, 250, 250, 250, 300, 400 }, 	// Empty value
				 { 500, 50, 50, 50, 50, 50, 250, 0, 50, 50, 50, 20, 100, 100, 100, 100, 150, 300, 400 },			// Digit
				 { 500, 50, 50, 50, 50, 50, 250, 50, 0, 15, 50, 100, 20, 25, 100, 150, 100, 300, 400 }, 			// Upper letter
				 { 500, 50, 50, 50, 50, 50, 250, 50, 15, 0, 50, 100, 25, 20, 100, 150, 100, 300, 400 },				// Lower letter
				 { 500, 50, 50, 50, 50, 50, 250, 50, 50, 50, 0, 100, 100, 100, 20, 150, 150, 300, 400 },			// Space
				 { 500, 100, 100, 100, 100, 100, 250, 20, 100, 100, 100, 0, 150, 150, 150, 100, 200, 300, 400 },	// Sequence of digits
				 { 500, 100, 100, 100, 100, 100, 250, 100, 20, 25, 100, 150, 0, 45, 150, 200, 100, 300, 400 }, 		// Sequence of upper letters
				 { 500, 100, 100, 100, 100, 100, 250, 100, 25, 20, 100, 150, 45, 0, 150, 200, 100, 300, 400 },		// Sequence of lower letters
				 { 500, 100, 100, 100, 100, 100, 250, 100, 100, 100, 20, 150, 150, 150, 0, 200, 200, 300, 400 },	// Whitespace
				 { 500, 150, 150, 150, 150, 150, 250, 100, 150, 150, 150, 100, 200, 200, 200, 0, 220, 300, 400 },	// Number
				 { 500, 150, 150, 150, 150, 150, 250, 150, 100, 100, 150, 200, 100, 100, 200, 220, 0, 300, 400 },	// Text
				 { 500, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 0, 300 },	// Missing value
				 { 500, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 0 }    // Full text	 
		 };
	
	 
	static Map<Object, List<Object>> patternClassCombinations;
	static
	{
		patternClassCombinations = new HashMap<Object, List<Object>>();
		
		
		patternClassCombinations.put(QUOTATION_CLASS.toString(), Arrays.asList('"'));
		
		patternClassCombinations.put(ARITHMETIC_OPRT_CLASS.toString(), Arrays.asList('*','+','-', '/','%', '=','<','>'));
		
		patternClassCombinations.put(BRACKETS_CLASS.toString(), Arrays.asList('[' , ']' , '{' , '}' , '(' , ')' ));
		
		patternClassCombinations.put(SYMBOL_CLASS.toString(), Arrays.asList('$' , '#' , '.' , '?' , '@' , '\\' , '^' , '\'' , '~' , '_' , '`' , '&' , '!' , '"' , ',' , ';' , ':' ,'\t', '|'));
	
		patternClassCombinations.put(LINE_BREAK_CLASS.toString(), Arrays.asList((char)10, (char)13));
		
		patternClassCombinations.put(DIGIT_CLASS.toString(), Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
		
		patternClassCombinations.put(UPPER_LETTER_CLASS.toString(), Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
		
		patternClassCombinations.put(LOWER_LETTER_CLASS.toString(), Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
		
		patternClassCombinations.put(SPACE_CLASS.toString(), Arrays.asList((char)32));
		
		patternClassCombinations.put(EMPTY_VALUES_CLASS.toString(), Arrays.asList((Object)null));
		patternClassCombinations.put(MISSING_VALUES_CLASS.toString(), Arrays.asList((Object)null));
		
	}
	
	 static int getWeight(Object row, Object col)
	 {
		 int rowIndex = indices.indexOf(row.toString());
		 int colIndex = indices.indexOf(col.toString());
		 
		 
		return costs[rowIndex][colIndex];
	 }
	
	 public static int ping_CostMatrix(Object x, Object y)
	 {
		
	  if(x == null || y == null)    // if value of x or y is null
		{
		  if (x== null && y== null)
			 return 0;
		  else
			  if(x==null)
			  {
				    List<Object> compare = patternClassCombinations.get(y.toString());
				    if(compare != null)
				    {
				    	if(compare.contains(x))
						{
							if(y.toString().equals(EMPTY_VALUES_CLASS.toString()))
								 return 10;
							else if(y.toString().equals(MISSING_VALUES_CLASS.toString()))
								 return 20;	
						}
						else if(!(compare.contains(x)))
						{
							return 250;    // (NULL VALUE SPECIAL CASE) any object from null value will cost 250, if its not empty value or missing value
						}
				    }
				    else
				    	return 250;     // (NULL VALUE SPECIAL CASE) any object from null value will cost 250, if its not empty value or missing value
							
			  }
			  else if(y==null)
			  {
				  List<Object> compare = patternClassCombinations.get(x.toString());
				  if(compare != null)
				  {
					  if(!(compare.isEmpty()) && compare.contains(y)) 
						{
							if(x.toString().equals(EMPTY_VALUES_CLASS.toString()))
								 return 10;
							else if(x.toString().equals(MISSING_VALUES_CLASS.toString()))
								 return 20;	
						}
					  else if(!(compare.contains(y)))
						{
							return 250;    // (NULL VALUE SPECIAL CASE) any object from null value will cost 250, if its not empty value or missing value
						}
				  }
				  else
				    	return 250;      // (NULL VALUE SPECIAL CASE) any object from null value will cost 250, if its not empty value or missing value
			  }
		}
		
		else if(!(indices.contains(x.toString())) && !(indices.contains(y.toString())))   // x and y both are characters ( LITERAL CLASS )
		{
			if(x.toString().equals(y.toString()))
				return 0;
			else
			{
				Object a = getKey(patternClassCombinations, x);
				Object b = getKey(patternClassCombinations, y);
				if(a!= null && b!= null)
				{
					if(a.toString().equals(b.toString()))  // if both characters belong to same class  e.g., both are digits
						 return 10;
					else if( (a.toString().equals(UPPER_LETTER_CLASS.toString()) && b.toString().equals(LOWER_LETTER_CLASS.toString())  )  ||   // special case for upper and lower case letters 
							 (a.toString().equals(LOWER_LETTER_CLASS.toString()) && b.toString().equals(UPPER_LETTER_CLASS.toString())  )  )
						return 15;
					 else
						 return 50;   // if characters are from different classes 
				}
				else
					return 50;  //   if characters are from different classes, Null case: if abstraction is not listed in patternClassCombinations e.g., FULL TEXT CLASS
			}
				
		}
		
		else if(indices.contains(x.toString()) && indices.contains(y.toString()))  // x and y both are abstractions 
		{
				return getWeight(x, y);
		}
		
		else if(indices.contains(x.toString()) && !(indices.contains(y.toString())))  // x is an abstraction and y is a character
		{
			if(x.toString().equals(CANDIDATE_DELIMITER_CLASS.toString()))
			    return 500;	
			else if(x.toString().equals(FULL_TEXT_CLASS.toString()))
			    return 400;	
			else if(x.toString().equals(MISSING_VALUES_CLASS.toString()))
			    return 300;	
			else if(x.toString().equals(EMPTY_VALUES_CLASS.toString()))
			    return 250;	
			else if(x.toString().equals(TEXT_CLASS.toString()))
				return 150;
			else if(x.toString().equals(NUMBER_CLASS.toString()))
				return 150;
			else if(x.toString().equals(SEQUENCE_DIGIT_CLASS.toString()))
			{
				List<Object> compare = patternClassCombinations.get(DIGIT_CLASS.toString());
				if(compare.contains(y))
					return 20;
				else
					return 100;
			}
			else if(x.toString().equals(SEQUENCE_UPPER_LETTER_CLASS.toString()))
			{
				List<Object> compare = patternClassCombinations.get(UPPER_LETTER_CLASS.toString());
				if(compare.contains(y))
					return 20;
				else if(y.toString().matches("[a-z]"))
					return 45;
				else
					return 100;
			}
			else if(x.toString().equals(SEQUENCE_LOWER_LETTER_CLASS.toString()))
			{
				List<Object> compare = patternClassCombinations.get(LOWER_LETTER_CLASS.toString());
				if(compare.contains(y))
					return 20;
				else if(y.toString().matches("[A-Z]"))
					return 45;
				else
					return 100;
			}
			
			else if(x.toString().equals(WHITESPACE_CLASS.toString()))
			{
				List<Object> compare = patternClassCombinations.get(SPACE_CLASS.toString());
				if(compare.contains(y))
					return 20;
				else
					return 100;
			}
			else
			{
				List<Object> compare = patternClassCombinations.get(x.toString());
				if(compare.contains(y))
					return 10;
				else
					return 50;
			}
			
		}
		
		else if(!(indices.contains(x.toString())) && indices.contains(y.toString()))  // y is an abstraction and x is a character
		{
			if(y.toString().equals(CANDIDATE_DELIMITER_CLASS.toString()))
			    return 500;	
			else if(y.toString().equals(FULL_TEXT_CLASS.toString()))
			    return 400;	
			else if(y.toString().equals(MISSING_VALUES_CLASS.toString()))
			    return 300;	
			else if(y.toString().equals(EMPTY_VALUES_CLASS.toString()))
			    return 250;	
			else if(y.toString().equals(TEXT_CLASS.toString()))
				return 150;
			else if(y.toString().equals(NUMBER_CLASS.toString()))
				return 150;
			else if(y.toString().equals(SEQUENCE_DIGIT_CLASS.toString()))
			{
				List<Object> compare = patternClassCombinations.get(DIGIT_CLASS.toString());
				if(compare.contains(x))
					return 20;
				else
					return 100;
			}
			else if(y.toString().equals(SEQUENCE_UPPER_LETTER_CLASS.toString()))
			{
				List<Object> compare = patternClassCombinations.get(UPPER_LETTER_CLASS.toString());
				if(compare.contains(x))
					return 20;
				else if(x.toString().matches("[a-z]"))
					return 45;
				else
					return 100;
			}
			else if(y.toString().equals(SEQUENCE_LOWER_LETTER_CLASS.toString()))
			{
				List<Object> compare = patternClassCombinations.get(LOWER_LETTER_CLASS.toString());
				if(compare.contains(x))
					return 20;
				else if(x.toString().matches("[A-Z]"))
					return 45;
				else
					return 100;
			}
			
			else if(y.toString().equals(WHITESPACE_CLASS.toString()))
			{
				List<Object> compare = patternClassCombinations.get(SPACE_CLASS.toString());
				if(compare.contains(x))
					return 20;
				else
					return 100;
			}
			else
			{
				List<Object> compare = patternClassCombinations.get(y.toString());
				if(compare.contains(x))
					return 10;
				else
					return 50;
			}
			
		}
		
		return 0;
	 }
	 
	 public static Object getKey(Map<Object, List<Object>> map, Object value)  // to get the keys for the Literals from the patternClassCombinations
	    {
	        for (Object key: map.keySet())
	        {
	        	List<Object> compare = map.get(key.toString());
	            if (compare.contains(value)) {
	                return key;
	            }
	        }
	        return null;
	   }
	 
	 
	 public static boolean objectCheck(Object x, Object y)
	 {
		
	  if(x == null || y == null)    // if value of x or y is null
		{
		  if (x== null && y== null)
			 return true;
		  else
			  if(x==null)
			  {
				  List<Object> compare = patternClassCombinations.get(y.toString());
					if(compare.contains(x))
						return true;
			  }
			  else if(y==null)
			  {
				  List<Object> compare = patternClassCombinations.get(x.toString());
					if(compare.contains(y))
						return true;
			  }
		}
		
		else if(!(indices.contains(x.toString())) && !(indices.contains(y.toString())))   // x and y both are characters
		{
			
			if(x.toString().equals(y.toString()))
				return true;
			else
			{
				Object a = getKey(patternClassCombinations, (Object)x);
				Object b = getKey(patternClassCombinations, (Object)y);
			
				if(a!= null && b!= null)
				{
					if(a.toString().equals(b.toString()))  // if both characters belong to the same class  e.g., both are digits
						 return true;
					else
						return false;
				}
			}
		}
		

		else if(patternClassCombinations.containsKey(x.toString()) && !(indices.contains(y.toString())))  // x is an abstraction and y is a character
		{
			List<Object> compare = patternClassCombinations.get(x.toString());
			if(compare.contains(y))
				return true;
			
		}
		
		else if(!(indices.contains(x.toString())) && patternClassCombinations.containsKey(y.toString()))  // x is a character and y is an abstraction
		{
			List<Object> compare = patternClassCombinations.get(y.toString());
			if(compare.contains(x))
				return true;
		}
		
		return false;
	 }
	 
}
