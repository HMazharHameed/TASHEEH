import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.accessibility.AccessibleBundle;

import abstractions.Arithmetic_Oprt_Class;
import abstractions.Brackets_Class;
import abstractions.Candidate_Delimiter_Class;
import abstractions.Date_Class;
import abstractions.Digit_Class;
import abstractions.EmptyValues_Class;
import abstractions.Full_Text_Class;
import abstractions.Line_Break_Class;
import abstractions.Lower_Letter_Class;
import abstractions.MissingValues_Class;
import abstractions.NotASCII_Class;
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



public class Abstraction_Primitives {
  
	private static final Number_Class NUMBER_CLASS = new Number_Class();
	private static final MissingValues_Class MISSING_VALUES_CLASS = new MissingValues_Class();
	private static final EmptyValues_Class EMPTY_VALUES_CLASS = new EmptyValues_Class();
	private static final Line_Break_Class LINE_BREAK_CLASS = new Line_Break_Class();
	private static final NotASCII_Class NOT_ASCII_CLASS = new NotASCII_Class();
	private static final Full_Text_Class FULL_TEXT_CLASS = new Full_Text_Class();
	private static final Text_Class TEXT_CLASS = new Text_Class();
	private static final Date_Class DATE_CLASS = new Date_Class();
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
	static StringBuilder stringBuilder_abstraction_premitive; 
	static List<Object> list_Abstraction_Primitives = new ArrayList<>();
	static Map<Integer, Object> hashMap_abstraction_premitive = new HashMap<>();
	boolean flag_abstraction_premitive_ = false;
	
	public List<Object> get_abstraction_result()
	{	
        List<Object> mapTOlist = new ArrayList<Object>();
		
		for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet())
    	{
    	  mapTOlist.add(entry.getValue());
    	}
		
		return mapTOlist;
	}
	
	
	public void set_stringBuilder_abstraction_premitive(String stringCheckValues)
	{ 
	    if(stringCheckValues==null)
	    {
	    	hashMap_abstraction_premitive.clear();
	    	hashMap_abstraction_premitive.put(0, Arrays.asList(EMPTY_VALUES_CLASS));
	    }
	    else
	    {
	    	stringBuilder_abstraction_premitive = new StringBuilder(stringCheckValues);
			Abstraction_Primitives set_stringBuilder_abstraction_premitive_object = new Abstraction_Primitives();
			set_stringBuilder_abstraction_premitive_object.set_hashMap_abstraction_premitive();	
	    }
		
			
	}
	
	
	public void set_hashMap_abstraction_premitive()
	{
		 hashMap_abstraction_premitive.clear();
		    for(int i = 0; i <stringBuilder_abstraction_premitive.length(); i++)
		    {
		    	hashMap_abstraction_premitive.put(i, stringBuilder_abstraction_premitive.charAt(i));
		    }
	}
	
	
    public void upper_letter_Check()
	{
    	
    	for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
    		
    		if(entry.getValue() instanceof Character ){
	    		if((char)entry.getValue() >= 65 && (char) entry.getValue() <= 90)
	 			{
	 	    		hashMap_abstraction_premitive.put(entry.getKey() , UPPER_LETTER_CLASS);
	 			}
    		}
    	}
	}
	
    public void lower_letter_Check()
	{
    	
         for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
    		
    		if((entry.getValue() instanceof Character)){
	    		if((char)entry.getValue() >= 97 && (char) entry.getValue() <= 122)
	 			{
	 	    		hashMap_abstraction_premitive.put(entry.getKey() , LOWER_LETTER_CLASS);
	 			}
    		}
         }
    	
	}
    
    public void digits_Check()
	{
    	 for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
     		
     		if((entry.getValue() instanceof Character)){
 	    		if((char)entry.getValue() >= 48 && (char) entry.getValue() <= 57)
 	 			{
 	 	    		hashMap_abstraction_premitive.put(entry.getKey() , DIGIT_CLASS);
 	 			}
     		}
          }
	}
    
    public void space_Check()
	{
    	
       for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
     		
     		if((entry.getValue() instanceof Character)){
 	    		if((char)entry.getValue() ==32)
 	 			{
 	 	    		hashMap_abstraction_premitive.put(entry.getKey() , SPACE_CLASS);
 	 			}
     		}
          }
	}
    
    public void quotation_Check()
  	{
    	
        for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
     		
     		if((entry.getValue() instanceof Character)){
 	    		if((char)entry.getValue() == 34)
 	 			{
 	 	    		hashMap_abstraction_premitive.put(entry.getKey() , QUOTATION_CLASS);
 	 			}
     		}
          }
  	}
    
    
    public void candidate_delimiter_Check( )
	  {
    
    	for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) { 
			if ((entry.getValue() instanceof Character)){
			  if((char)entry.getValue() == 44 || (char)entry.getValue() == 59 || (char)entry.getValue() == 9 || (char)entry.getValue() == 58
					  || (char)entry.getValue() == 124)	
	 			{
	 	    		
	 	    		hashMap_abstraction_premitive.put(entry.getKey() , CANDIDATE_DELIMITER_CLASS);
	 			}
			}
    	}
  	
	 }
    
    
    public void brackets_Check()
	{
    	for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
     		
     		if((entry.getValue() instanceof Character )){
     			
 	    		if((char)entry.getValue() ==40  || (char)entry.getValue() ==91 || (char)entry.getValue() ==123 || (char)entry.getValue() == 41 
 	    				|| (char)entry.getValue() ==93 || (char)entry.getValue() == 125)
 	    			{
	    		    hashMap_abstraction_premitive.put(entry.getKey() , BRACKETS_CLASS);
 	    		    
 	    			}
     		   }
          }
    	

	}
    
    public void arithmetic_opr_Check()
	{
    	
    	for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
     		
     		if((entry.getValue() instanceof Character)){
 	    		if((char)entry.getValue() == 42 ||(char)entry.getValue() ==43 || (char)entry.getValue() ==45 || (char)entry.getValue() ==47
 	    				|| (char)entry.getValue() == 37| (char)entry.getValue() ==60 || (char)entry.getValue() == 61 || (char)entry.getValue() == 62)
 	 			{
 	 	    		hashMap_abstraction_premitive.put(entry.getKey() , ARITHMETIC_OPRT_CLASS);
 	 			}
     		 }
          }
	}
    
    
    public void symbol_Check()
	{
    	for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
    		if((entry.getValue() instanceof Character)){
    		if ( (char)entry.getValue() == 36|| (char)entry.getValue() == 35 || (char)entry.getValue() == 46 ||
    				(char)entry.getValue()== 92	|| (char)entry.getValue() == 94  || 
    						(char)entry.getValue()== 63	|| (char)entry.getValue() == 64 ||
    								(char)entry.getValue()== 95	|| (char)entry.getValue()== 96 ||
    										(char)entry.getValue() == 126	|| (char)entry.getValue() == 38 ||
    												(char)entry.getValue()== 39	|| (char)entry.getValue() == 33 ||
    														(char)entry.getValue() == 44 ||(char)entry.getValue() == 59 || (char)entry.getValue()== 124 ||
    																(char)entry.getValue() == 58	|| (char)entry.getValue() == 9) {
    	   
 	    		hashMap_abstraction_premitive.put(entry.getKey() , SYMBOL_CLASS);
    	     }
    		}
    	   }	
    	
	}
    
    public void missing_values_check()
	{
    	
    	hashMap_abstraction_premitive.clear();
		hashMap_abstraction_premitive.put(0, MISSING_VALUES_CLASS);
//       List<Object> mapTOlist = new ArrayList<Object>();
//	   StringBuilder mapToListSB = new StringBuilder();	
//	   for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet())
//   	    {
//			if((entry.getValue() instanceof Character))
//			{
//				mapToListSB.append(entry.getValue());
//			}
//			else
//   	        mapTOlist.add(entry.getValue());
//   	   }
//		
//		for(int index = 0 ; index< mapTOlist.size(); index++)
//    	{
//			if(mapTOlist.get(index) == WHITE_SPACES_CLASS || mapTOlist.get(index) == SEQUENCE_WHITE_SPACES || mapTOlist.get(index) == QUOTATION_CLASS||
//					mapTOlist.get(index).toString().matches("\t") )
//			{
//			
//				hashMap_abstraction_premitive.clear();
//				hashMap_abstraction_premitive.put(index, MISSING_VALUES_CLASS);
//			}
//    	}
//		
//		
//		if( mapToListSB.toString().contains("NULL") || mapToListSB.toString().contains("null") ||
//				mapToListSB.toString().contains("n,a") || mapToListSB.toString().contains("NA") || mapToListSB.toString().contains("NaN"))
//		{
//			hashMap_abstraction_premitive.clear();
//			hashMap_abstraction_premitive.put(0, MISSING_VALUES_CLASS);
//		}
    }
    
    public void text_format_Check()   
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
    		//Object next_mapValue = hashMap_abstraction_premitive.get(entry.getKey()+1);
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
     }
    
    public void number_Check()
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
    		//Object next_mapValue = hashMap_abstraction_premitive.get(entry.getKey()+1);
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
    	
    }
    
    
    public void fullText_format_Check()
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
    		//Object next_mapValue = hashMap_abstraction_premitive.get(entry.getKey()+1);
    		
			if((mapTOlist.get(index) == UPPER_LETTER_CLASS || mapTOlist.get(index)== LOWER_LETTER_CLASS || 
					mapTOlist.get(index)== SEQUENCE_UPPER_LETTER_CLASS || mapTOlist.get(index) == SEQUENCE_LOWER_LETTER_CLASS || 
							mapTOlist.get(index) == SPACE_CLASS || mapTOlist.get(index) == WHITESPACE_CLASS  || mapTOlist.get(index) == SEQUENCE_DIGIT_CLASS||
							mapTOlist.get(index) == TEXT_CLASS || mapTOlist.get(index)== ARITHMETIC_OPRT_CLASS || mapTOlist.get(index) == DIGIT_CLASS ||
							 mapTOlist.get(index)== SYMBOL_CLASS || mapTOlist.get(index) == QUOTATION_CLASS || mapTOlist.get(index)  instanceof Brackets_Class
							 || mapTOlist.get(index) == NOT_ASCII_CLASS))
   
			{
    			if(flag_for_seqeunce) {
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
		  hashMap_abstraction_premitive.put(i,FULL_TEXT_CLASS);	
		}
     }
    
    
//    public void date_format_Check()
//    {
//    	
//        Abstraction_Primitives date_formate_check_object = new Abstraction_Primitives();
//    	
//    	ArrayList<Object> known_patterns_1 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_2 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_3 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_4 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_5 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_6 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_7 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_8 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_9 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_10 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_11 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_12 = new ArrayList<Object>();
//    	ArrayList<Object> known_patterns_13 = new ArrayList<Object>();
//    	
//    	List<Integer>[] output_list = new List[2];
//    	
//    	known_patterns_1.add(DIGIT_CLASS);
//    	known_patterns_1.add(DIGIT_CLASS);
//    	known_patterns_1.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_1.add(DIGIT_CLASS);
//    	known_patterns_1.add(DIGIT_CLASS);
//    	known_patterns_1.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_1.add(DIGIT_CLASS);
//    	known_patterns_1.add(DIGIT_CLASS);
//    	known_patterns_1.add(DIGIT_CLASS);
//    	known_patterns_1.add(DIGIT_CLASS);
//    	
//    	known_patterns_2.add(DIGIT_CLASS);
//    	known_patterns_2.add(DIGIT_CLASS);
//    	known_patterns_2.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_2.add(DIGIT_CLASS);
//    	known_patterns_2.add(DIGIT_CLASS);
//    	known_patterns_2.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_2.add(DIGIT_CLASS);
//    	known_patterns_2.add(DIGIT_CLASS);
//
//    	known_patterns_3.add(DIGIT_CLASS);
//    	known_patterns_3.add(DIGIT_CLASS);
//    	known_patterns_3.add(DIGIT_CLASS);
//    	known_patterns_3.add(DIGIT_CLASS);
//    	known_patterns_3.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_3.add(DIGIT_CLASS);
//    	known_patterns_3.add(DIGIT_CLASS);
//    	known_patterns_3.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_3.add(DIGIT_CLASS);
//    	known_patterns_3.add(DIGIT_CLASS);
//    	
//    
//    	known_patterns_4.add(DIGIT_CLASS);
//    	known_patterns_4.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_4.add(DIGIT_CLASS);
//    	known_patterns_4.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_4.add(DIGIT_CLASS);
//    	known_patterns_4.add(DIGIT_CLASS);
//    	
//    	known_patterns_5.add(DIGIT_CLASS);
//    	known_patterns_5.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_5.add(DIGIT_CLASS);
//    	known_patterns_5.add(DIGIT_CLASS);
//    	known_patterns_5.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_5.add(DIGIT_CLASS);
//    	known_patterns_5.add(DIGIT_CLASS);
//    	
//    	known_patterns_6.add(DIGIT_CLASS);
//    	known_patterns_6.add(DIGIT_CLASS);
//    	known_patterns_6.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_6.add(DIGIT_CLASS);
//    	known_patterns_6.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_6.add(DIGIT_CLASS);
//    	known_patterns_6.add(DIGIT_CLASS);
//    	
////    	known_patterns_7.add(DIGIT_CLASS);
////    	known_patterns_7.add(DIGIT_CLASS);
////    	known_patterns_7.add(ARITHMETIC_OPRT_CLASS);
////    	known_patterns_7.add(SEQUENCE_UPPER_LETTER_CLASS);
////    	known_patterns_7.add(ARITHMETIC_OPRT_CLASS);
////    	known_patterns_7.add(DIGIT_CLASS);
////    	known_patterns_7.add(DIGIT_CLASS);
////    	
////    	known_patterns_8.add(DIGIT_CLASS);
////    	known_patterns_8.add(DIGIT_CLASS);
////    	known_patterns_8.add(ARITHMETIC_OPRT_CLASS);
////    	known_patterns_8.add(SEQUENCE_LOWER_LETTER_CLASS);
////    	known_patterns_8.add(ARITHMETIC_OPRT_CLASS);
////    	known_patterns_8.add(DIGIT_CLASS);
////    	known_patterns_8.add(DIGIT_CLASS);
//    	
//    	
//    	
////    	known_patterns_9.add(DIGIT_CLASS);
////    	known_patterns_9.add(DIGIT_CLASS);
////    	known_patterns_9.add(ARITHMETIC_OPRT_CLASS);
////    	known_patterns_9.add(TEXT_CLASS);
////    	known_patterns_9.add(ARITHMETIC_OPRT_CLASS);
////    	known_patterns_9.add(DIGIT_CLASS);
////    	known_patterns_9.add(DIGIT_CLASS);
////    	
//    	known_patterns_10.add(DIGIT_CLASS);
//    	known_patterns_10.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_10.add(DIGIT_CLASS);
//    	known_patterns_10.add(DIGIT_CLASS);
//    	known_patterns_10.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_10.add(DIGIT_CLASS);
//    	known_patterns_10.add(DIGIT_CLASS);
//    	known_patterns_10.add(DIGIT_CLASS);
//    	known_patterns_10.add(DIGIT_CLASS);
//    	
//    	known_patterns_11.add(DIGIT_CLASS);
//    	known_patterns_11.add(DIGIT_CLASS);
//    	known_patterns_11.add(DIGIT_CLASS);
//    	known_patterns_11.add(DIGIT_CLASS);
//    	known_patterns_11.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_11.add(DIGIT_CLASS);
//    	known_patterns_11.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_11.add(DIGIT_CLASS);
//    	
//    	known_patterns_12.add(DIGIT_CLASS);
//    	known_patterns_12.add(DIGIT_CLASS);
//    	known_patterns_12.add(DIGIT_CLASS);
//    	known_patterns_12.add(DIGIT_CLASS);
//    	known_patterns_12.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_12.add(DIGIT_CLASS);
//    	known_patterns_12.add(DIGIT_CLASS);
//    	known_patterns_12.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_12.add(DIGIT_CLASS);
//    	
//    	
//    	known_patterns_13.add(DIGIT_CLASS);
//    	known_patterns_13.add(DIGIT_CLASS);
//    	known_patterns_13.add(DIGIT_CLASS);
//    	known_patterns_13.add(DIGIT_CLASS);
//    	known_patterns_13.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_13.add(DIGIT_CLASS);
//    	known_patterns_13.add(ARITHMETIC_OPRT_CLASS);
//    	known_patterns_13.add(DIGIT_CLASS);
//    	known_patterns_13.add(DIGIT_CLASS);
//    	
//    	
//    	
//    	
//    	List<ArrayList<Object>> known_patterns = new ArrayList<ArrayList<Object>>();
//    	
//    	known_patterns.add(known_patterns_10);
//    	known_patterns.add(known_patterns_11);
//    	known_patterns.add(known_patterns_12);
//    	known_patterns.add(known_patterns_13);
//    	known_patterns.add(known_patterns_1);
//    	known_patterns.add(known_patterns_2);
//    	known_patterns.add(known_patterns_4);
//    	known_patterns.add(known_patterns_5);
//    	known_patterns.add(known_patterns_6);
////    	known_patterns.add(known_patterns_7);
////    	known_patterns.add(known_patterns_8);
////    	known_patterns.add(known_patterns_9);
//    	
//    	
//    	
//    	
//    		
//    	for( ArrayList<Object> test_pattern: known_patterns)
//    	{
//        		output_list = date_formate_check_object.search_patterns(test_pattern);	
//        		
//        		for(Object i : output_list[0])
//        		{
//        			hashMap_abstraction_premitive.remove(i);
//        		}
//            	
//        		for (Object i :output_list[1]) 
//            	{
//            	   hashMap_abstraction_premitive.put((Integer) i , DATE_CLASS);
//           		}
//    	}
//    	  	
//    }
//    
//    public List<Integer>[] search_patterns(List date_patterns) 
//    { 
//    	boolean flag_for_seqeunce = false;   
//        boolean flag_if_clause = false;
//		Iterator iterator_search_pattern = hashMap_abstraction_premitive.entrySet().iterator();
//		List<Integer> list_search_pattern = new ArrayList<Integer>();
//		List<Integer> list_store_index= new ArrayList<Integer>();
//		
//		List<Integer> list_search_pattern_test = new ArrayList<Integer>();
//		List<Integer> list_store_index_test= new ArrayList<Integer>();
//		
//		int index = 0, pattern_len = date_patterns.size();
//		while(iterator_search_pattern.hasNext()) {
//		  
//		   Map.Entry mapEntry_search_pattern= (Map.Entry)iterator_search_pattern.next(); 
//		   if(index < pattern_len && mapEntry_search_pattern.getValue() == date_patterns.get(index) ) {
//			   index++;
//			   if(flag_for_seqeunce) {
//				  list_store_index.add((int) mapEntry_search_pattern.getKey());
//				   if(flag_if_clause) {
//					   list_search_pattern.add((int)mapEntry_search_pattern.getKey() - 1);
//					   flag_if_clause = false;
//				   }
//			   } 
//			   else {
//				   flag_for_seqeunce = true;
//				   flag_if_clause = true;
//			   }
//			   
//		   } 
//		   
//		   else  { 
//			   if(index < pattern_len)
//			   {
//				   flag_for_seqeunce = false;
//				   flag_if_clause = false;
//				   list_search_pattern.clear();
//				   list_store_index.clear();
//				   index= 0;
//			   }
//			   
//			   else
//			   {
//					index = 0;
//					list_search_pattern_test.addAll(list_search_pattern);
//					list_store_index_test.addAll(list_store_index);
//					list_search_pattern.clear();
//					list_store_index.clear();
//					flag_for_seqeunce = false;
//					flag_if_clause = false;
//					
//		      } 
//		  }
//		   
//		}   
//		if(index == pattern_len && flag_for_seqeunce ==true )
//		 {
//			   list_search_pattern_test.addAll(list_search_pattern);
//				list_store_index_test.addAll(list_store_index);
//		  }
//		
//		return new List[] {list_store_index_test , list_search_pattern_test};
//    } 
  
    public void notASCII_Check()
	{
    	for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
    		if((entry.getValue() instanceof Character)){
    			
    			if ( ((char)entry.getValue() < 65 || (char)entry.getValue() > 90) &&
    					((char)entry.getValue() < 97 || (char)entry.getValue() > 122) &&
    		    		   ((char)entry.getValue() < 48 || (char)entry.getValue() >57) &&
    		    		   (char)entry.getValue() != 36 && (char)entry.getValue()!= 35 && 
    		    		   (char)entry.getValue() != 46 && 
    		    		   (char)entry.getValue() != 92	&& (char)entry.getValue() != 94  && 
    		    		   (char)entry.getValue() != 63	&& (char)entry.getValue() != 64 &&
    		    		   (char)entry.getValue() != 95	&& (char)entry.getValue() != 96 &&
    		    		   (char)entry.getValue() != 126 && (char)entry.getValue() != 38 &&
    		   			   (char)entry.getValue() != 39	&& (char)entry.getValue() != 33 &&
  						   (char)entry.getValue()!= 42 && (char)entry.getValue() != 43 && 
  						   (char)entry.getValue() != 45 && (char)entry.getValue() != 47	&&
    					   (char)entry.getValue() != 37  && (char)entry.getValue() != 60 &&
    					   (char)entry.getValue() != 61	&& (char)entry.getValue() != 62 &&
    					   (char)entry.getValue() != 40 && (char)entry.getValue() != 41 && 
 						   (char)entry.getValue() != 91 &&
						   (char)entry.getValue() != 93	&& (char)entry.getValue() != 123  && 
						   (char)entry.getValue() != 125 && (char)entry.getValue() != 34 &&
						   (char)entry.getValue() != 44 && (char)entry.getValue() != 59 && 
						   (char)entry.getValue() != 124 && (char)entry.getValue() != 32 &&
						   (char)entry.getValue() != 58	&& (char)entry.getValue() != 9  )
    		    	     {
    		    	    	 hashMap_abstraction_premitive.put(entry.getKey(), NOT_ASCII_CLASS);
    		    	     }
    			
    		}
    	}
    	
//    	for(int i=0; i<=stringBuilder_abstraction_premitive.length()-1; i++) {
//    		if (((int)stringBuilder_abstraction_premitive.charAt(i) < 65 || (int)stringBuilder_abstraction_premitive.charAt(i) > 90)&&
//    		   ((int)stringBuilder_abstraction_premitive.charAt(i) < 97 || (int)stringBuilder_abstraction_premitive.charAt(i) > 122)&&
//    		   ((int)stringBuilder_abstraction_premitive.charAt(i) < 48 || (int)stringBuilder_abstraction_premitive.charAt(i) >57 ) &&
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 36 && (int)stringBuilder_abstraction_premitive.charAt(i) != 35 && 
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 46 && 
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 92	&& (int)stringBuilder_abstraction_premitive.charAt(i) != 94  && 
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 63	&& (int)stringBuilder_abstraction_premitive.charAt(i) != 64 &&
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 95	&& (int)stringBuilder_abstraction_premitive.charAt(i) != 96 &&
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 126 && (int)stringBuilder_abstraction_premitive.charAt(i) != 38 &&
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 39	&& (int)stringBuilder_abstraction_premitive.charAt(i) != 33 &&
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 42 && (int)stringBuilder_abstraction_premitive.charAt(i) != 43 && 
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 45 && (int)stringBuilder_abstraction_premitive.charAt(i) != 47	&&
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 37  && (int)stringBuilder_abstraction_premitive.charAt(i) != 60 &&
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 61	&& (int)stringBuilder_abstraction_premitive.charAt(i) != 62 &&
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 40 && (int)stringBuilder_abstraction_premitive.charAt(i) != 41 && 
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 91 &&
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 93	&& (int)stringBuilder_abstraction_premitive.charAt(i) != 123  && 
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 125 && (int)stringBuilder_abstraction_premitive.charAt(i) != 34 &&
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 44 && (int)stringBuilder_abstraction_premitive.charAt(i) != 59 && 
//    		   (int)stringBuilder_abstraction_premitive.charAt(i) != 124 && (int)stringBuilder_abstraction_premitive.charAt(i) != 32 &&
// 			   (int)stringBuilder_abstraction_premitive.charAt(i) != 58	&& (int)stringBuilder_abstraction_premitive.charAt(i) != 9)
//    	     {
//    	    	 hashMap_abstraction_premitive.put(i, LEFT_OUT_CHARACTER_CLASS);
//    	     }
//    	}
	}
    
    public void lineBreak()
    {
    	for(Entry<Integer, Object> entry: hashMap_abstraction_premitive.entrySet()) {
    		if((entry.getValue() instanceof Character)){
    			
    			if ( (char)entry.getValue() ==10 || (char)entry.getValue() == 13)
    		    	     {
    		    	    	 hashMap_abstraction_premitive.put(entry.getKey(), LINE_BREAK_CLASS);
    		    	     }
    		}
    	}
    		
    }
    public void sequenceofUpperCaseLetters_Check()
    {
            boolean flag_sequenceofUpperCaseLetters = false;   
            boolean flag_if_clause_UpperCaseLetters = false;
    		Iterator iterator_hashmap_sequenceofUpperCaseLetters = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofUpperCaseLetters = new ArrayList<>();
    		while(iterator_hashmap_sequenceofUpperCaseLetters.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofUpperCaseLetters= (Map.Entry)iterator_hashmap_sequenceofUpperCaseLetters.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofUpperCaseLetters.getValue() == UPPER_LETTER_CLASS) {
    			   if(flag_sequenceofUpperCaseLetters) {
    				   // second, third, ... upper case letter
    				   iterator_hashmap_sequenceofUpperCaseLetters.remove();
    				   // second upper case letter
    				   if(flag_if_clause_UpperCaseLetters) {
    					   list_sequenceofUpperCaseLetters.add((int)mapEntry_hashmapper_sequenceofUpperCaseLetters.getKey() - 1); 
    					   flag_if_clause_UpperCaseLetters = false;
    				   }
    			   } 
    			   else {
    				   flag_if_clause_UpperCaseLetters = true;
    				   flag_sequenceofUpperCaseLetters = true;
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
    		
     }
    
    public void updated_sequenceofUpperCaseLetters_Check()
    {
            boolean flag_sequenceofUpperCaseLetters = false;   
            boolean flag_if_clause_UpperCaseLetters = false;
    		Iterator iterator_hashmap_sequenceofUpperCaseLetters = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofUpperCaseLetters = new ArrayList<>();
    		while(iterator_hashmap_sequenceofUpperCaseLetters.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofUpperCaseLetters= (Map.Entry)iterator_hashmap_sequenceofUpperCaseLetters.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofUpperCaseLetters.getValue() == UPPER_LETTER_CLASS) {
    			  
    			   if(flag_sequenceofUpperCaseLetters) {
    				   // second, third, ... upper case letter
    				   iterator_hashmap_sequenceofUpperCaseLetters.remove();
    				   
    				   // second upper case letter
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
    		
     }

    public void sequenceofLowerCaseLetters_Check()
    {
            boolean flag_sequenceofLowerCaseLetters = false;   
            boolean flag_if_clause_LowerCaseLetters = false;
    		Iterator iterator_hashmap_sequenceofLowerCaseLetters = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofLowerCaseLetters = new ArrayList<>();
    		while(iterator_hashmap_sequenceofLowerCaseLetters.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofLowerCaseLetters= (Map.Entry)iterator_hashmap_sequenceofLowerCaseLetters.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofLowerCaseLetters.getValue() == LOWER_LETTER_CLASS) {
    			   if(flag_sequenceofLowerCaseLetters) {
    				   // second, third, ... lower case letter
    				   iterator_hashmap_sequenceofLowerCaseLetters.remove();
    				   // second lower case letter
    				   if(flag_if_clause_LowerCaseLetters) {
    					   list_sequenceofLowerCaseLetters.add((int)mapEntry_hashmapper_sequenceofLowerCaseLetters.getKey() - 1); 
    					   flag_if_clause_LowerCaseLetters = false;
    				   }
    			   } 
    			   else {
    				   flag_if_clause_LowerCaseLetters = true;
    				   flag_sequenceofLowerCaseLetters = true;
    				   
    			   }
    			   
    		   } 
    		   else {
    			   flag_if_clause_LowerCaseLetters = false;
    			   flag_sequenceofLowerCaseLetters = false;
    		   }
    		}
    		   
    		for (int i :list_sequenceofLowerCaseLetters) 
	    		{
	    		  hashMap_abstraction_premitive.put(i,SEQUENCE_LOWER_LETTER_CLASS);	
	    		}
     }
    
    public void updated_sequenceofLowerCaseLetters_Check()
    {
            boolean flag_sequenceofLowerCaseLetters = false;   
            boolean flag_if_clause_LowerCaseLetters = false;
    		Iterator iterator_hashmap_sequenceofLowerCaseLetters = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofLowerCaseLetters = new ArrayList<>();
    		while(iterator_hashmap_sequenceofLowerCaseLetters.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofLowerCaseLetters= (Map.Entry)iterator_hashmap_sequenceofLowerCaseLetters.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofLowerCaseLetters.getValue() == LOWER_LETTER_CLASS) {
    			   if(flag_sequenceofLowerCaseLetters) {
    				   // second, third, ... lower case letter
    				   iterator_hashmap_sequenceofLowerCaseLetters.remove();
    				   // second lower case letter
    				   if(flag_if_clause_LowerCaseLetters) {
    					   list_sequenceofLowerCaseLetters.add((int)mapEntry_hashmapper_sequenceofLowerCaseLetters.getKey() - 1); 
    					   flag_if_clause_LowerCaseLetters = false;
    				   }
    			   } 
    			   else {
    				   flag_if_clause_LowerCaseLetters = true;
    				   flag_sequenceofLowerCaseLetters = true;
    				   list_sequenceofLowerCaseLetters.add((int)mapEntry_hashmapper_sequenceofLowerCaseLetters.getKey());

    			   }
    			   
    		   } 
    		   else {
    			   flag_if_clause_LowerCaseLetters = false;
    			   flag_sequenceofLowerCaseLetters = false;
    		   }
    		}
    		   
    		for (int i :list_sequenceofLowerCaseLetters) 
	    		{
	    		  hashMap_abstraction_premitive.put(i,SEQUENCE_LOWER_LETTER_CLASS);	
	    		}
     }
    
    public void sequenceofdigits_Check()
    {
            boolean flag_sequenceofdigits = false;   
            boolean flag_if_clause_digits = false;
    		Iterator iterator_hashmap_sequenceofdigits = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofdigits = new ArrayList<>();
    		while(iterator_hashmap_sequenceofdigits.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofdigits= (Map.Entry)iterator_hashmap_sequenceofdigits.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofdigits.getValue() == DIGIT_CLASS) {
    			   if(flag_sequenceofdigits) {
    				   // second, third, ... digit  
    				   iterator_hashmap_sequenceofdigits.remove();
    				   // second digit  
    				   if(flag_if_clause_digits) {
    					   list_sequenceofdigits.add((int)mapEntry_hashmapper_sequenceofdigits.getKey() - 1); 
    					   flag_if_clause_digits = false;
    				   }
    			   } 
    			   else {
    				   flag_if_clause_digits = true;
    				   flag_sequenceofdigits = true;
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
    }
    
    public void updated_sequenceofdigits_Check()
    {
            boolean flag_sequenceofdigits = false;   
            boolean flag_if_clause_digits = false;
    		Iterator iterator_hashmap_sequenceofdigits = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofdigits = new ArrayList<>();
    		while(iterator_hashmap_sequenceofdigits.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofdigits= (Map.Entry)iterator_hashmap_sequenceofdigits.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofdigits.getValue() == DIGIT_CLASS) {
    			   if(flag_sequenceofdigits) {
    				   // second, third, ... digit  
    				   iterator_hashmap_sequenceofdigits.remove();
    				   // second digit  
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
    }
    
    public void whiteSpace_Check()
    {
            boolean flag_sequenceofWhiteSpaces = false;   
            boolean flag_if_clause_WhiteSpaces = false;
    		Iterator iterator_hashmap_sequenceofWhiteSpaces = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofWhiteSpaces = new ArrayList<>();
    		while(iterator_hashmap_sequenceofWhiteSpaces.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofWhiteSpaces= (Map.Entry)iterator_hashmap_sequenceofWhiteSpaces.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofWhiteSpaces.getValue() == SPACE_CLASS) {
    			   if(flag_sequenceofWhiteSpaces) {
    				   // second, third, ... whitespace
    				   iterator_hashmap_sequenceofWhiteSpaces.remove();
    				   // second whitespace 
    				   if(flag_if_clause_WhiteSpaces) {
    					   list_sequenceofWhiteSpaces.add((int)mapEntry_hashmapper_sequenceofWhiteSpaces.getKey() - 1); 
    					   flag_if_clause_WhiteSpaces = false;
    				   }
    			   } 
    			   else {
    				   flag_if_clause_WhiteSpaces = true;
    				   flag_sequenceofWhiteSpaces = true;
    			   }
    			   
    		   } 
    		   else {
    			   flag_if_clause_WhiteSpaces = false;
    			   flag_sequenceofWhiteSpaces = false;
    		   }
    		}
    		   
    		for (int i :list_sequenceofWhiteSpaces) 
	    		{
	    		  hashMap_abstraction_premitive.put(i, WHITESPACE_CLASS);	
	    		}
    }
    
    public void updated_WhiteSpaces_Check()
    {
            boolean flag_sequenceofWhiteSpaces = false;   
            boolean flag_if_clause_WhiteSpaces = false;
    		Iterator iterator_hashmap_sequenceofWhiteSpaces = hashMap_abstraction_premitive.entrySet().iterator();
    		List<Integer> list_sequenceofWhiteSpaces = new ArrayList<>();
    		while(iterator_hashmap_sequenceofWhiteSpaces.hasNext()) {
    		   Map.Entry mapEntry_hashmapper_sequenceofWhiteSpaces= (Map.Entry)iterator_hashmap_sequenceofWhiteSpaces.next();
    		   
    		   if(mapEntry_hashmapper_sequenceofWhiteSpaces.getValue() == SPACE_CLASS) {
    			   if(flag_sequenceofWhiteSpaces) {
    				   // second, third, ... whitespace
    				   iterator_hashmap_sequenceofWhiteSpaces.remove();
    				   // second whitespace 
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
	    		  hashMap_abstraction_premitive.put(i, WHITESPACE_CLASS);	
	    		}
    }
    		 
}
