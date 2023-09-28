package algebra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import abstractions.Arithmetic_Oprt_Class;
import abstractions.Brackets_Class;
import abstractions.Candidate_Delimiter_Class;
import abstractions.Digit_Class;
import abstractions.EmptyValues_Class;
import abstractions.Full_Text_Class;
import abstractions.Gap_Class;
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

public class Ignore{

private static final Gap_Class GAP_CLASS = new Gap_Class();
	
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
	
	
	static List<Object> symbol ;
	static
	{
		symbol =  new ArrayList<>();
		
		symbol.add(ARITHMETIC_OPRT_CLASS.toString());   
		symbol.add(SYMBOL_CLASS.toString()); // 
		symbol.add(LINE_BREAK_CLASS.toString()); //  
		symbol.add(QUOTATION_CLASS.toString()); // 
		symbol.add(BRACKETS_CLASS.toString()); // 
		symbol.add(SPACE_CLASS.toString()); // 
		symbol.add(WHITESPACE_CLASS.toString()); // 
		symbol.add(FULL_TEXT_CLASS.toString()); // 
	}
	
	static List<Object> digit;
	static
	{
		digit =  new ArrayList<>();
		
		digit.add(DIGIT_CLASS.toString()); //  
		digit.add(SEQUENCE_DIGIT_CLASS.toString()); // 
		digit.add(NUMBER_CLASS.toString()); // 
	}
	
	static List<Object> letter;
	static
	{
		letter =  new ArrayList<>();
		
		letter.add(UPPER_LETTER_CLASS.toString()); // 
		letter.add(LOWER_LETTER_CLASS.toString()); // 
		letter.add(SEQUENCE_UPPER_LETTER_CLASS.toString()); // 
		letter.add(SEQUENCE_LOWER_LETTER_CLASS.toString()); // 
		letter.add(TEXT_CLASS.toString()); //
	}
	
	public List<Object> apply(List<Object> input, List<Object> output) {

		if (cost(input, output) > 0.3)
			return output;
		else
			return null;
	}

	public static boolean checkMismatch(List<Object> input, List<Object> output)
	{
		List<Object> dominantObjects = new ArrayList<Object>();
		List<Object> potentailObjects = new ArrayList<Object>();
		
		for(int i = 0; i<input.size(); i++)
		{
			 if( input.get(i).toString().matches("[0-9]") || digit.contains(input.get(i).toString()))
			 {
				dominantObjects.add(DIGIT_CLASS);
			 }	
			 else if( input.get(i).toString().matches("[a-zA-Z]") || letter.contains(input.get(i).toString()))
			 {
				 dominantObjects.add(UPPER_LETTER_CLASS);
			 }	
			 else if( input.get(i).toString().matches("\\p{Blank}+") || input.get(i).toString().contains(WHITESPACE_CLASS.toString()) || input.get(i).toString().contains(SPACE_CLASS.toString()))
			 {
				 dominantObjects.add(SPACE_CLASS);
			 }	
			 else if( input.get(i).toString().matches("[^A-Za-z0-9]") || symbol.contains(input.get(i).toString()))
			 {
				 dominantObjects.add(input.get(i));
			 }	
		}
		
		for(int i = 0; i<output.size(); i++)
		{
			 if( output.get(i).toString().matches("[0-9]") || digit.contains(output.get(i).toString()))
			 {
				potentailObjects.add(DIGIT_CLASS);  
			 }	
			 else if( output.get(i).toString().matches("[a-zA-Z]") || letter.contains(output.get(i).toString()))
			 {
				 potentailObjects.add(UPPER_LETTER_CLASS); 
			 }	
			 else if( output.get(i).toString().matches("\\p{Blank}+") || output.get(i).toString().contains(WHITESPACE_CLASS.toString()) || output.get(i).toString().contains(SPACE_CLASS.toString()))
			 {
				 potentailObjects.add(SPACE_CLASS);
			 }	
			 else if( output.get(i).toString().matches("[^A-Za-z0-9]") || symbol.contains(output.get(i).toString()))
			 {
				 potentailObjects.add(output.get(i)); 
			 }		
		}
		
		Map<Integer, Object> inputMap = new LinkedHashMap<Integer, Object>();
		Map<Integer, Object> outputMap = new LinkedHashMap<Integer, Object>();
		
		List<Object> dominantObjects_Check = new ArrayList<Object>();
		List<Object> potentailObjects_Check = new ArrayList<Object>();
		
		for(int i = 0; i<dominantObjects.size();i++)
		{
			inputMap.put(i, dominantObjects.get(i));
		}
		
//		System.out.println("Dominant");
//		System.out.println(inputMap);
		
		outputMap = updated_sequenceofUpperCaseLetters_Check(inputMap);
//		System.out.println("Seq of upper letter  "+outputMap);
		outputMap = updated_sequenceofdigits_Check(inputMap);
//		System.out.println("Seq of digit  "+outputMap);
		outputMap = updated_WhiteSpaces_Check(inputMap);
//		System.out.println("Seq of space  "+outputMap);
		outputMap = text_format_Check(inputMap);
//		System.out.println("Text  "+outputMap);
		outputMap = number_Check(inputMap);
//		System.out.println("Number  "+outputMap);
		outputMap = alphaNumeric_Check(inputMap);
//		System.out.println("AlphaNumeric  "+outputMap);
		
		for(Entry<Integer,Object> entry : outputMap.entrySet())
		{
			dominantObjects_Check.add(entry.getValue());
		}
		
		inputMap.clear();
		outputMap.clear();
		
		for(int i = 0; i<potentailObjects.size();i++)
		{
			inputMap.put(i, potentailObjects.get(i));
		}
		
//		System.out.println("Potentail");
//        System.out.println(inputMap);
		
		outputMap = updated_sequenceofUpperCaseLetters_Check(inputMap);
//		System.out.println("Seq of upper letter  "+outputMap);
		outputMap = updated_sequenceofdigits_Check(inputMap);
//		System.out.println("Seq of digit  "+outputMap);
		outputMap = updated_WhiteSpaces_Check(inputMap);
//		System.out.println("Seq of space  "+outputMap);
		outputMap = text_format_Check(inputMap);
//		System.out.println("Text  "+outputMap);
		outputMap = number_Check(inputMap);
//		System.out.println("Number  "+outputMap);
		outputMap = alphaNumeric_Check(inputMap);  
//		System.out.println("AlphaNumeric  "+outputMap);
		
		for(Entry<Integer,Object> entry : outputMap.entrySet())
		{
			potentailObjects_Check.add(entry.getValue());
		}
		
//		System.out.println("new dom  "+dominantObjects_Check +"    "+input);
//		
//		System.out.println("new pot   "+potentailObjects_Check+"     "+output);
	
		
//		System.out.println("Input pattern  "+input  + " dominant pattern size  "+input.size());
//		System.out.println(dominantObjects_Check +"     "+ dominantObjects_Check.size());
//		
//		System.out.println("output pattern   "+output+ "  potential pattern size  " + output.size());
//		System.out.println(potentailObjects_Check +"     "+ potentailObjects_Check.size());
		
		if(dominantObjects_Check.equals(potentailObjects_Check))
			return true;

		else if(!(dominantObjects_Check.equals(potentailObjects_Check)))
		{
		  if(containsOnlyText(dominantObjects_Check) && containsOnlyLetter(potentailObjects_Check)) 
			  return true;
		  if(containsOnlyText(potentailObjects_Check) && containsOnlyLetter(dominantObjects_Check)) 
			  return true;
		  if(containsOnlyDigit(dominantObjects_Check) && containsOnlyNumber(potentailObjects_Check)) 
			  return true;
		  if(containsOnlyDigit(potentailObjects_Check) && containsOnlyNumber(dominantObjects_Check)) 
			  return true;
		  if(containsOnlyNumbers_and_Symbols(dominantObjects_Check, potentailObjects_Check))
		  	return true;
		  if(containsOnlyLetters_and_Symbols(dominantObjects_Check, potentailObjects_Check))
			  	return true;
		  else
			  return false;
		}
		else
		   return false;
	}
	
	public static boolean containsOnlyNumbers_and_Symbols(List<Object> input, List<Object> output) 
    {
		
		List<Boolean> checkDominantObjects = new ArrayList<Boolean>();
		List<Boolean> checkPotentailObjects = new ArrayList<Boolean>();
		
		for(int i = 0; i<input.size(); i++)
		{
			if(input.get(i) instanceof Character && output.contains(input.get(i)))
				checkDominantObjects.add(true);
			else
			{
				 if( digit.contains(input.get(i).toString()) && ( output.toString().contains(SEQUENCE_DIGIT_CLASS.toString()) || output.toString().contains(NUMBER_CLASS.toString()) ) )
					 checkDominantObjects.add(true);
				 else if( (input.get(i).toString().contains(WHITESPACE_CLASS.toString()) || input.get(i).toString().contains(SPACE_CLASS.toString()))
						 && (output.toString().contains(WHITESPACE_CLASS.toString()) || output.toString().contains(SPACE_CLASS.toString())) )
				 {
					 checkDominantObjects.add(true);
				 }	
				 else
					 checkDominantObjects.add(false); 
			}
		}

		for(int i = 0; i<output.size(); i++)
		{
			if(output.get(i) instanceof Character && input.contains(output.get(i)))
				checkPotentailObjects.add(true);
			else
			{
				 if( digit.contains(output.get(i).toString()) && ( input.toString().contains(SEQUENCE_DIGIT_CLASS.toString()) || input.toString().contains(NUMBER_CLASS.toString()) ) )
					 checkPotentailObjects.add(true);
				 else if( (output.get(i).toString().contains(WHITESPACE_CLASS.toString()) || output.get(i).toString().contains(SPACE_CLASS.toString()))
						 && (input.toString().contains(WHITESPACE_CLASS.toString()) || input.toString().contains(SPACE_CLASS.toString())) )
				 {
					 checkPotentailObjects.add(true);
				 }	
				 else
					 checkPotentailObjects.add(false);
			}
		}
				
		if (!(checkDominantObjects.isEmpty()) && areAllTrue(checkDominantObjects) && !(checkPotentailObjects.isEmpty()) && areAllTrue(checkPotentailObjects)) {
			return true;
		} else {
			return false;
		}
    }
	
	public static boolean containsOnlyLetters_and_Symbols(List<Object> input, List<Object> output) 
    {
		
		List<Boolean> checkDominantObjects = new ArrayList<Boolean>();
		List<Boolean> checkPotentailObjects = new ArrayList<Boolean>();
		
		for(int i = 0; i<input.size(); i++)
		{
			if(input.get(i) instanceof Character && output.contains(input.get(i)))
				checkDominantObjects.add(true);
			else
			{
				 if( letter.contains(input.get(i).toString()) && ( output.toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()) || output.toString().contains(TEXT_CLASS.toString()) ) )
					 checkDominantObjects.add(true);
				 else if( (input.get(i).toString().contains(WHITESPACE_CLASS.toString()) || input.get(i).toString().contains(SPACE_CLASS.toString()))
						 && (output.toString().contains(WHITESPACE_CLASS.toString()) || output.toString().contains(SPACE_CLASS.toString())) )
				 {
					 checkDominantObjects.add(true);
				 }	
				 else
					 checkDominantObjects.add(false); 
			}
		}

		for(int i = 0; i<output.size(); i++)
		{
			if(output.get(i) instanceof Character && input.contains(output.get(i)))
				checkPotentailObjects.add(true);
			else
			{
				 if( letter.contains(output.get(i).toString()) && ( input.toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()) || input.toString().contains(TEXT_CLASS.toString()) ) )
					 checkPotentailObjects.add(true);
				 else if( (output.get(i).toString().contains(WHITESPACE_CLASS.toString()) || output.get(i).toString().contains(SPACE_CLASS.toString()))
						 && (input.toString().contains(WHITESPACE_CLASS.toString()) || input.toString().contains(SPACE_CLASS.toString())) )
				 {
					 checkPotentailObjects.add(true);
				 }	
				 else
					 checkPotentailObjects.add(false);
			}
		}
				
		if (!(checkDominantObjects.isEmpty()) && areAllTrue(checkDominantObjects) && !(checkPotentailObjects.isEmpty()) && areAllTrue(checkPotentailObjects)) {
			return true;
		} else {
			return false;
		}
    }
	
	public static boolean containsOnlyNumber(List<Object> input)
    {
		List<Boolean> flag_dependency_check = new ArrayList<Boolean>();
		
		if(input.toString().contains(NUMBER_CLASS.toString()))  
		{
			for(Object obj: input)
			{
				if(obj.toString().equals("\"") || obj.equals(NUMBER_CLASS)) 
					flag_dependency_check.add(true);
				else
					flag_dependency_check.add(false);
			}
		}

		if (!(flag_dependency_check.isEmpty()) && areAllTrue(flag_dependency_check)) {
			return true;
		} else {
			return false;
		}
    }
	
	public static boolean containsOnlyDigit(List<Object> input)
    {
		List<Boolean> flag_dependency_check = new ArrayList<Boolean>();
		
		if(input.toString().contains(SEQUENCE_DIGIT_CLASS.toString()))  
		{
			for(Object obj: input)
			{
				if(obj.toString().equals("\"") || obj.equals(SEQUENCE_DIGIT_CLASS)) 
					flag_dependency_check.add(true);
				else
					flag_dependency_check.add(false);
			}
		}
//		System.out.println("digit class output  "+flag_dependency_check+"  input   "+input);
		if (!(flag_dependency_check.isEmpty()) && areAllTrue(flag_dependency_check)) {
			return true;
		} else {
			return false;
		}
    }
	
	public static boolean containsOnlyLetter(List<Object> input)
    {
		List<Boolean> flag_dependency_check = new ArrayList<Boolean>();
		
		if(input.toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()))  
		{
			for(Object obj: input)
			{
				if(obj.toString().equals("\"") || obj.equals(SEQUENCE_UPPER_LETTER_CLASS)) 
					flag_dependency_check.add(true);
				else
					flag_dependency_check.add(false);
			}
		}

		if (!(flag_dependency_check.isEmpty()) && areAllTrue(flag_dependency_check)) {
			return true;
		} else {
			return false;
		}
    }
	
	public static boolean containsOnlyText(List<Object> input)
    {
		List<Boolean> flag_dependency_check = new ArrayList<Boolean>();
		
		if(input.toString().contains(TEXT_CLASS.toString())) 
		{
			for(Object obj: input)
			{
				if(obj.toString().equals("\"") || obj.equals(TEXT_CLASS)) 
					flag_dependency_check.add(true);
				else
					flag_dependency_check.add(false);
			}
		}

		if (!(flag_dependency_check.isEmpty()) && areAllTrue(flag_dependency_check)) {
			return true;
		} else {
			return false;
		}
    }
	
	public static boolean areAllTrue(List<Boolean> bool_data)
	 {
	      for(boolean b : bool_data) if(!b) return false;
	      return true;
	 }
	
	public static Map<Integer, Object> alphaNumeric_Check(Map<Integer, Object> hashMap_abstraction_premitive)
    {
		boolean flag_for_seqeunce = false;
		boolean flag_if_clause = false;
		List<Integer> list_search_pattern = new ArrayList<Integer>();
		List<Integer> list_store_index= new ArrayList<Integer>();
		
		List<Object> mapTOlist = new ArrayList<Object>();
		
		for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet())
    	{
    	  mapTOlist.add(entry.getValue());
    	}
		
		if( ( mapTOlist.toString().contains(SEQUENCE_UPPER_LETTER_CLASS.toString()) || mapTOlist.toString().contains(TEXT_CLASS.toString()) ) && 
				( mapTOlist.toString().contains(NUMBER_CLASS.toString()) || mapTOlist.toString().contains(SEQUENCE_DIGIT_CLASS.toString()) ) )
		{
			for(int index = 0 ; index< mapTOlist.size(); index++)
	    	{

				if( mapTOlist.get(index) == TEXT_CLASS || mapTOlist.get(index)== SEQUENCE_DIGIT_CLASS || mapTOlist.get(index)== SEQUENCE_UPPER_LETTER_CLASS || 
						mapTOlist.get(index) == SPACE_CLASS || mapTOlist.get(index) == NUMBER_CLASS)
	    		{
		    			if(flag_for_seqeunce ) {
		  				  list_store_index.add(index);                                                                        
		  				   if(flag_if_clause) {																						
		  					   list_search_pattern.add(index - 1); 
		  					   flag_if_clause = false;
		  				   }
		  			     } 
		  			    else {
		  				   flag_for_seqeunce = true;
		  				   flag_if_clause = true;
		  			     }
		  			   
		  		  } 
		  	    else  
		  		  {
		  				   flag_for_seqeunce = false;
		  				   flag_if_clause = false;
		  		  }
	    	
	    	}
	    	
	    	hashMap_abstraction_premitive.clear();
	    	for(int index = 0 ; index< mapTOlist.size(); index++)
	    	{
	    		hashMap_abstraction_premitive.put(index, mapTOlist.get(index));
	    	}
	    	
	    	for (int i :list_store_index) 
			{
			  hashMap_abstraction_premitive.remove(i);
			}
	    	
	    	for (int i :list_search_pattern) 
			{
			  hashMap_abstraction_premitive.put(i,TEXT_CLASS);	
			}
		}
    	
		return hashMap_abstraction_premitive;
    }
	public static Map<Integer, Object> updated_sequenceofUpperCaseLetters_Check(Map<Integer, Object> hashMap_abstraction_premitive)
    {
            boolean flag_sequenceofUpperCaseLetters = false;   
            boolean flag_if_clause_UpperCaseLetters = false;
    		Iterator iterator_hashmap_sequenceofUpperCaseLetters = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofUpperCaseLetters = new ArrayList<>();
    		while(iterator_hashmap_sequenceofUpperCaseLetters.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofUpperCaseLetters= (Map.Entry)iterator_hashmap_sequenceofUpperCaseLetters.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofUpperCaseLetters.getValue() == UPPER_LETTER_CLASS) {
    			  
    			   if(flag_sequenceofUpperCaseLetters) {

    				   iterator_hashmap_sequenceofUpperCaseLetters.remove();
    				   
    				   if(flag_if_clause_UpperCaseLetters) {
    					   list_sequenceofUpperCaseLetters.add((int)mapEntry_hashmapper_sequenceofUpperCaseLetters.getKey() - 1); 
    					   flag_if_clause_UpperCaseLetters = false;
    				   }
    			   } 
    			   else {
    				   flag_if_clause_UpperCaseLetters = true;
    				   flag_sequenceofUpperCaseLetters = true;
    				   list_sequenceofUpperCaseLetters.add((int)mapEntry_hashmapper_sequenceofUpperCaseLetters.getKey());
    			   }
    			   
    		   } 
    		  
    		   else {
    			   flag_if_clause_UpperCaseLetters = false;
    			   flag_sequenceofUpperCaseLetters = false;
    			  
    		   }
    		}
    		
    		for (int i :list_sequenceofUpperCaseLetters) 
	    		{
	    		  hashMap_abstraction_premitive.put(i,SEQUENCE_UPPER_LETTER_CLASS);	
	    		}
    		
    	return hashMap_abstraction_premitive;	
     }
	
	public static Map<Integer, Object> updated_sequenceofdigits_Check(Map<Integer, Object> hashMap_abstraction_premitive)
    {
            boolean flag_sequenceofdigits = false;   
            boolean flag_if_clause_digits = false;
    		Iterator iterator_hashmap_sequenceofdigits = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofdigits = new ArrayList<>();
    		while(iterator_hashmap_sequenceofdigits.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofdigits= (Map.Entry)iterator_hashmap_sequenceofdigits.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofdigits.getValue() == DIGIT_CLASS) {
    			   if(flag_sequenceofdigits) {
    				   iterator_hashmap_sequenceofdigits.remove();
    				   if(flag_if_clause_digits) {
    					   list_sequenceofdigits.add((int)mapEntry_hashmapper_sequenceofdigits.getKey() - 1); 
    					   flag_if_clause_digits = false;
    				   }
    			   } 
    			   else {
    				   flag_if_clause_digits = true;
    				   flag_sequenceofdigits = true;
    				   list_sequenceofdigits.add((int)mapEntry_hashmapper_sequenceofdigits.getKey());
    			   }
    			   
    		   } 
    		   else {
    			   flag_if_clause_digits = false;
    			   flag_sequenceofdigits = false;
    		   }
    		}
    		   
    		for (int i :list_sequenceofdigits) 
	    		{
	    		  hashMap_abstraction_premitive.put(i,SEQUENCE_DIGIT_CLASS);	
	    		}
       return hashMap_abstraction_premitive;
    }

	
	public static Map<Integer, Object> updated_WhiteSpaces_Check(Map<Integer, Object> hashMap_abstraction_premitive)
    {
            boolean flag_sequenceofWhiteSpaces = false;   
            boolean flag_if_clause_WhiteSpaces = false;
    		Iterator iterator_hashmap_sequenceofWhiteSpaces = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofWhiteSpaces = new ArrayList<>();
    		while(iterator_hashmap_sequenceofWhiteSpaces.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofWhiteSpaces= (Map.Entry)iterator_hashmap_sequenceofWhiteSpaces.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofWhiteSpaces.getValue() == SPACE_CLASS) {
    			   if(flag_sequenceofWhiteSpaces) {
    				   iterator_hashmap_sequenceofWhiteSpaces.remove();
    				   if(flag_if_clause_WhiteSpaces) {
    					   list_sequenceofWhiteSpaces.add((int)mapEntry_hashmapper_sequenceofWhiteSpaces.getKey() - 1); 
    					   flag_if_clause_WhiteSpaces = false;
    				   }
    			   } 
    			   else {
    				   flag_if_clause_WhiteSpaces = true;
    				   flag_sequenceofWhiteSpaces = true;
    				   list_sequenceofWhiteSpaces.add((int)mapEntry_hashmapper_sequenceofWhiteSpaces.getKey());
    			   }
    			   
    		   } 
    		   else {
    			   flag_if_clause_WhiteSpaces = false;
    			   flag_sequenceofWhiteSpaces = false;
    		   }
    		}
    		   
    		for (int i :list_sequenceofWhiteSpaces) 
	    		{
	    		  hashMap_abstraction_premitive.put(i, SPACE_CLASS);	
	    		}
      return hashMap_abstraction_premitive;
    }
	
	public static Map<Integer, Object> text_format_Check(Map<Integer, Object> hashMap_abstraction_premitive)   
    {
    	boolean flag_for_seqeunce = false;
		boolean flag_if_clause = false;
		List<Integer> list_search_pattern = new ArrayList<Integer>();
		List<Integer> list_store_index= new ArrayList<Integer>();
		
		List<Object> mapTOlist = new ArrayList<Object>();
		
		for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet())
    	{
    	  mapTOlist.add(entry.getValue());
    	}
		
		
    	for(int index = 0 ; index< mapTOlist.size(); index++)
    	{
    		if( mapTOlist.get(index) == UPPER_LETTER_CLASS || mapTOlist.get(index)== SEQUENCE_UPPER_LETTER_CLASS || 
					mapTOlist.get(index)== LOWER_LETTER_CLASS|| mapTOlist.get(index) == SEQUENCE_LOWER_LETTER_CLASS)
    		{
	    			if(flag_for_seqeunce ) {
	  				  list_store_index.add(index);                                                                        
	  				   if(flag_if_clause) {																						
	  					   list_search_pattern.add(index - 1); 
	  					   flag_if_clause = false;
	  				   }
	  			     } 
	  			    else {
	  				   flag_for_seqeunce = true;
	  				   flag_if_clause = true;
	  			     }
	  			   
	  		  } 
    		else if((mapTOlist.get(index) == SPACE_CLASS || mapTOlist.get(index) == WHITESPACE_CLASS) || (mapTOlist.get(index) instanceof Character)  &&  
    				( (char)mapTOlist.get(index) == 39 ||(char)mapTOlist.get(index) == 45 || (char)mapTOlist.get(index) == 47|| (char)mapTOlist.get(index) == 92 ||
                     (char)mapTOlist.get(index) == 95 ||(char)mapTOlist.get(index) == 38 ||(char)mapTOlist.get(index) == 46 ) )
    		{ 
    			if(flag_for_seqeunce ) {
	  				  list_store_index.add(index);
	  				   if(flag_if_clause) {
	  					   list_search_pattern.add(index - 1); 
	  					   flag_if_clause = false;
	  				   }
	  			     } 
    				
    		}
    		
	  	    else  
	  		  {
	  				   flag_for_seqeunce = false;
	  				   flag_if_clause = false;
	  		  }
    	
    	}
    	
    	hashMap_abstraction_premitive.clear();
    	for(int index = 0 ; index< mapTOlist.size(); index++)
    	{
    		hashMap_abstraction_premitive.put(index, mapTOlist.get(index));
    	}
    	
    	for (int i :list_store_index) 
		{
		  hashMap_abstraction_premitive.remove(i);
		}
    	
    	for (int i :list_search_pattern) 
		{
		  hashMap_abstraction_premitive.put(i,TEXT_CLASS);	
		}
    	
    	return hashMap_abstraction_premitive;
     }
	
	public static Map<Integer, Object> number_Check(Map<Integer, Object> hashMap_abstraction_premitive)
    {
    	boolean flag_for_seqeunce = false;
		boolean flag_if_clause = false;
		boolean flag_digit_check = false;
		List<Integer> list_search_pattern = new ArrayList<Integer>();
		List<Integer> list_store_index= new ArrayList<Integer>();
		
		List<Object> mapTOlist = new ArrayList<Object>();
		
		for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet())
    	{
    	  mapTOlist.add(entry.getValue());
    	}
		
		
    	for(int index = 0 ; index< mapTOlist.size(); index++)
    	{

    		if((mapTOlist.get(index) instanceof Character) &&
    				((char)mapTOlist.get(index) == 43 ||(char)mapTOlist.get(index) == 45) && !(flag_for_seqeunce))
    		{
    		
     			   list_store_index.add(index);
     			   flag_for_seqeunce = true;
 				   flag_if_clause = true;
    			
    		}
    		else if((mapTOlist.get(index) == DIGIT_CLASS || mapTOlist.get(index)== SEQUENCE_DIGIT_CLASS) )
    		{
    			flag_digit_check =true;
	    			if(flag_for_seqeunce ) {
	  				  list_store_index.add(index);
	  				   if(flag_if_clause) {
	  					   list_search_pattern.add(index - 1); 
	  					   flag_if_clause = false;
	  				   }
	  			     } 
	  			    else {
	  				   flag_for_seqeunce = true;
	  				   flag_if_clause = true;
	  			     }   
	  		  } 
    		else if( (mapTOlist.get(index) instanceof Character) &&
    				((char)mapTOlist.get(index) == 44 || (char)mapTOlist.get(index) == 46))
    		{ 
    			
    			if(flag_for_seqeunce ) {
	  				  list_store_index.add(index);
	  				   if(flag_if_clause) {
	  					   list_search_pattern.add(index - 1); 
	  					   flag_if_clause = false;
	  				   }
	  			     } 	
    			else {
	  				   flag_for_seqeunce = true;
	  				   flag_if_clause = true;
	  			     } 
    		}
	  	    else  
	  		  {
	  				   flag_for_seqeunce = false;
	  				   flag_if_clause = false;
	  		  }
    	
    	}
    	
    	hashMap_abstraction_premitive.clear();
    	for(int index = 0 ; index< mapTOlist.size(); index++)
    	{
    		hashMap_abstraction_premitive.put(index, mapTOlist.get(index));
    	}
    	
    	for (int i :list_store_index) 
		{												 
    		if(!(list_search_pattern.isEmpty()))
		       hashMap_abstraction_premitive.remove(i);
		}
    	
    	if(flag_digit_check)
    	{
    		for (int i :list_search_pattern) 
    		{
    		  hashMap_abstraction_premitive.put(i,NUMBER_CLASS);	
    		}	
    	}
    	
		return hashMap_abstraction_premitive;
    }
	
	public static float cost(List<Object> listONE, List<Object> listTWO)
	{

		int countSymbol_ListONE = 0, countLetters_ListONE = 0, countDigits_ListONE = 0;
		int countSymbol_ListTWO = 0, countLetters_ListTWO = 0, countDigits_ListTWO = 0;

		for (Object obj : listONE) {
			if (obj.toString().matches("[0-9]") || digit.contains(obj.toString()))
				countDigits_ListONE++;
			else if (obj.toString().matches("[a-zA-Z]") || letter.contains(obj.toString()))
				countLetters_ListONE++;
			else
				countSymbol_ListONE++;
		}

		for (Object obj : listTWO) {
			if (obj.toString().matches("[0-9]") || digit.contains(obj.toString()))
				countDigits_ListTWO++;
			else if (obj.toString().matches("[a-zA-Z]") || letter.contains(obj.toString()))
				countLetters_ListTWO++;
			else
				countSymbol_ListTWO++;
		}

		int sum = Math.min(countSymbol_ListONE, countSymbol_ListTWO)
				+ Math.min(countDigits_ListONE, countDigits_ListTWO)
				+ Math.min(countLetters_ListONE, countLetters_ListTWO);
		int size = Math.max(listONE.size(), listTWO.size());
		float out = (float) sum / size;
		return 1 - out;
	}
	
	public static ArrayList<Object> noConsecutiveDups(List<Object> input) 
	 {
		  ArrayList<Object> newList = new ArrayList<Object>();

		  if(!(input.isEmpty()))
		   newList.add(input.get(0));

		  for(int i = 1; i < input.size(); i++) {
		    if(input.get(i-1).toString() != input.get(i).toString()) {
		       newList.add(input.get(i));
		    }
		  }

		  return newList;
	}
}
