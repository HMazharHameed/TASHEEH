import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import abstractions.EmptyValues_Class;
import abstractions.Full_Text_Class;
import abstractions.MissingValues_Class;
import abstractions.Padded_Class;




public class PatternGeneration {

private static final Full_Text_Class FULL_TEXT_CLASS = new Full_Text_Class();
private static final Padded_Class PADDED_CLASS = new Padded_Class();
final static Abstraction_Primitives abstraction_primitive_object_csvtesting = new Abstraction_Primitives();	
private static final MissingValues_Class MISSING_VALUES_CLASS = new MissingValues_Class();
private static final EmptyValues_Class EMPTY_VALUES_CLASS = new EmptyValues_Class();	


static List<Dependency_Class> dependencies;
static {
	dependencies  = new ArrayList<Dependency_Class>();
    
    dependencies.add(new Dependency_Class(1, Arrays.asList(1,2,3,4,5,6,7,8,9,10,12,13,14,15,16)));
    dependencies.add(new Dependency_Class(2, Arrays.asList(3)));
    dependencies.add(new Dependency_Class(3, Arrays.asList(15)));
    dependencies.add(new Dependency_Class(4, Arrays.asList(5)));
    dependencies.add(new Dependency_Class(5, Arrays.asList(15)));	
    dependencies.add(new Dependency_Class(6, Arrays.asList(7,8)));
    dependencies.add(new Dependency_Class(7, Arrays.asList(16)));  
    dependencies.add(new Dependency_Class(8, Arrays.asList(7)));
    dependencies.add(new Dependency_Class(9, Arrays.asList(10)));
    dependencies.add(new Dependency_Class(10, Arrays.asList(15)));
    dependencies.add(new Dependency_Class(11, Arrays.asList(1,16)));   
    dependencies.add(new Dependency_Class(12, Arrays.asList(16)));
    dependencies.add(new Dependency_Class(13, Arrays.asList(8,16)));
    dependencies.add(new Dependency_Class(14, Arrays.asList(16)));
    dependencies.add(new Dependency_Class(15, Arrays.asList(16)));
   
 
}


static List<Dependency_Class> paired_dependencies;
static {
	paired_dependencies  = new ArrayList<Dependency_Class>();
	paired_dependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	paired_dependencies.add(new Dependency_Class(2, Arrays.asList(4)));
	paired_dependencies.add(new Dependency_Class(3, Arrays.asList(4)));   
}

static List<Dependency_Class> missingValues_dependencies;
static {
	missingValues_dependencies  = new ArrayList<Dependency_Class>();
	missingValues_dependencies.add(new Dependency_Class(1, Arrays.asList(9)));
	missingValues_dependencies.add(new Dependency_Class(2, Arrays.asList(3)));
	missingValues_dependencies.add(new Dependency_Class(3, Arrays.asList(9))); 
	missingValues_dependencies.add(new Dependency_Class(4, Arrays.asList(5)));
	missingValues_dependencies.add(new Dependency_Class(5, Arrays.asList(9)));
	missingValues_dependencies.add(new Dependency_Class(6, Arrays.asList(7)));
	missingValues_dependencies.add(new Dependency_Class(7, Arrays.asList(9)));
	missingValues_dependencies.add(new Dependency_Class(8, Arrays.asList(9)));
	 
}


static List<Dependency_Class> text_class_dependencies;
static {
	text_class_dependencies  = new ArrayList<Dependency_Class>();
	text_class_dependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	text_class_dependencies.add(new Dependency_Class(2, Arrays.asList(8)));     
	text_class_dependencies.add(new Dependency_Class(3, Arrays.asList(4)));
	text_class_dependencies.add(new Dependency_Class(4, Arrays.asList(8)));  
	text_class_dependencies.add(new Dependency_Class(5, Arrays.asList(6)));
	text_class_dependencies.add(new Dependency_Class(6, Arrays.asList(8)));  
	text_class_dependencies.add(new Dependency_Class(7, Arrays.asList(9))); 
	text_class_dependencies.add(new Dependency_Class(8, Arrays.asList(9)));
}

static List<Dependency_Class> alphaNumeric_class_dependencies;
static {
	alphaNumeric_class_dependencies  = new ArrayList<Dependency_Class>();
	alphaNumeric_class_dependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(2, Arrays.asList(10)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(3, Arrays.asList(4)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(4, Arrays.asList(10)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(5, Arrays.asList(6)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(6, Arrays.asList(10)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(7, Arrays.asList(8)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(8, Arrays.asList(10)));
	alphaNumeric_class_dependencies.add(new Dependency_Class(9, Arrays.asList(10)));  
}

static List<Dependency_Class> number_class_dependencies;
static {
	number_class_dependencies  = new ArrayList<Dependency_Class>();
	number_class_dependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	number_class_dependencies.add(new Dependency_Class(2, Arrays.asList(4)));
}

static List<Dependency_Class> allASCIIdependencies;
static {
	allASCIIdependencies  = new ArrayList<Dependency_Class>();
    
	allASCIIdependencies.add(new Dependency_Class(1, Arrays.asList(2)));
	allASCIIdependencies.add(new Dependency_Class(2, Arrays.asList(13)));
	allASCIIdependencies.add(new Dependency_Class(3, Arrays.asList(4)));
	allASCIIdependencies.add(new Dependency_Class(4, Arrays.asList(13)));
	allASCIIdependencies.add(new Dependency_Class(5, Arrays.asList(6)));
	allASCIIdependencies.add(new Dependency_Class(6, Arrays.asList(15,17)));
	allASCIIdependencies.add(new Dependency_Class(7, Arrays.asList(8)));	
	allASCIIdependencies.add(new Dependency_Class(8, Arrays.asList(13)));
	allASCIIdependencies.add(new Dependency_Class(10, Arrays.asList(15)));
	allASCIIdependencies.add(new Dependency_Class(11, Arrays.asList(15)));
	allASCIIdependencies.add(new Dependency_Class(12, Arrays.asList(15)));
	allASCIIdependencies.add(new Dependency_Class(13, Arrays.asList(15,16)));
	allASCIIdependencies.add(new Dependency_Class(14, Arrays.asList(15,16)));
	allASCIIdependencies.add(new Dependency_Class(17, Arrays.asList(15,16)));  
	

}


static List<ArrayList<Integer>> temp_permutation_results_list = new ArrayList<ArrayList<Integer>>();
static List<Dependency_Class> specified_depdendencies = new ArrayList<Dependency_Class>(); 

 public Map<Integer, List<List<Object>>> patternComputation(List<String> columnList, int listSize, String univocityDetetced_delimiter)
 {
	 
	    
	    
		List<Object> listOFdigits = new ArrayList<Object>();
		List<Object> listOFUpperAlphabets = new ArrayList<Object>();
		List<Object> listOFLowerAlphabets = new ArrayList<Object>();
		List<Object> listOFLetters = new ArrayList<Object>();
		List<Object> listOFblankspaces = new ArrayList<Object>();	
		List<Object> listOFDelimiter = new ArrayList<Object>();
		List<Object> listOFAlphaNumeric = new ArrayList<Object>();
		List<Object> listOFNumber = new ArrayList<Object>();
		List<Object> listOFallASCII = new ArrayList<Object>();
		List<Object> listofEmptyValues = new ArrayList<Object>();
		List<List<Object>> listoflist = new ArrayList<List<Object>>();

		Set<Integer> set_for_dependency_list = new LinkedHashSet<Integer>();
		
		
		List<ArrayList<Integer>> store_temp_permutation_digits = new ArrayList<ArrayList<Integer>>(); 
		List<ArrayList<Integer>> store_temp_permutation_nulls = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> store_temp_permutation_UpperLetter = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> store_temp_permutation_LowerLetter = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> store_temp_permutation_BlankSpace = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> store_temp_permutation_text = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> store_temp_permutation_AlphaNumeric = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> store_temp_permutation_fullText = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> store_temp_permutation_number = new ArrayList<ArrayList<Integer>>();
		
		Map<Object, ArrayList<Object>> digit_class_map = new HashMap<Object, ArrayList<Object>>();
		Map<Object, ArrayList<Object>> null_class_map = new HashMap<Object, ArrayList<Object>>();
		Map<Object, ArrayList<Object>> lower_Letter_class_map = new HashMap<Object, ArrayList<Object>>();
		Map<Object, ArrayList<Object>> upper_Letter_class_map = new HashMap<Object, ArrayList<Object>>();
		Map<Object, ArrayList<Object>> blankSpace_class_map = new HashMap<Object, ArrayList<Object>>();
		Map<Object, ArrayList<Object>> text_class_map = new HashMap<Object, ArrayList<Object>>();
		Map<Object, ArrayList<Object>> alphaNumeric_class_map = new HashMap<Object, ArrayList<Object>>();
		Map<Object, ArrayList<Object>> fullText_class_map = new HashMap<Object, ArrayList<Object>>();
		Map<Object, ArrayList<Object>> number_class_map = new HashMap<Object, ArrayList<Object>>();
		
		Map<Integer, List<List<Object>>> map_Combined_listoflist_results = new LinkedHashMap<Integer, List<List<Object>>>();
		
		for (int i = 0; i < columnList.size(); i++) 
			   {
				
				if(!(columnList.get(i) == null) && !isAllASCII(columnList.get(i)))				
				{
					System.out.println("Please use a file that contains ASCII characters!");
					System.exit(0);
				}
				
				if(!(columnList.get(i) == null) && (columnList.get(i).matches(univocityDetetced_delimiter) || columnList.get(i).matches("[/|]")) ) // updated this code on 08 December 2020
			    {
					listOFDelimiter.clear();
					abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
					abstraction_primitive_object_csvtesting.candidate_delimiter_Check();
					listOFDelimiter.add(abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results 
					listoflist.add(new ArrayList<Object>(listOFDelimiter));    			    	
//					    for(String index: listOFDelimiter)
//					   	{
//					  		System.out.println(index);
//					   	}
					   
			     }  
				else if(columnList.get(i) == null || columnList.get(i).matches("\\p{Blank}+") || columnList.get(i).matches("\"\\p{Blank}+\"") ||
						 columnList.get(i).matches("NA") || columnList.get(i).matches("NaN") || columnList.get(i).matches("nan") || columnList.get(i).matches("null") ||
						 columnList.get(i).matches("NULL") ||columnList.get(i).matches("\t") ||  columnList.get(i).matches("\"\"") ||columnList.get(i).matches("na") ||
						 columnList.get(i).matches("n/a") || columnList.get(i).matches("N/A") || columnList.get(i).matches("None") || columnList.get(i).matches("NONE"))

				 {
					   if(columnList.get(i) == null)
					   {
						   listofEmptyValues.clear();
						   listofEmptyValues.add(Arrays.asList(EMPTY_VALUES_CLASS));
						   listofEmptyValues.add(Arrays.asList(MISSING_VALUES_CLASS));
					   }
					   else
					   {
						   listofEmptyValues.clear();
						   if(store_temp_permutation_nulls.isEmpty())
						   {
							   temp_permutation_results_list.clear();
								set_for_dependency_list.clear();
								//System.out.println(columnList.get(i));
							    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
							    specified_depdendencies = missingValues_dependencies;
							   	
							   	permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
							   	set_for_dependency_list.addAll(permuteLIST);
					    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
					    		store_temp_permutation_nulls.addAll(temp_permutation_results_list);
//							   	for(List<Integer> index: temp_permutation_results_list)
//							   	{
//							   		System.out.println(index);
//							   	} 
						   }
					    
						   if(null_class_map.containsKey(columnList.get(i)))
						   	{
							   listofEmptyValues.addAll(null_class_map.get(columnList.get(i)));
						    
						   	}
						   	else
						   	{
						   		for(List<Integer> list : store_temp_permutation_nulls)
					         	  {	
							   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
							   		
								   	 for (Integer index : list)
								   	 {	
								   		if(index == 1)
									   	 {
											abstraction_primitive_object_csvtesting.quotation_Check();
											
									   	 }
								   		if(index ==2)
									   	 {
								   		    abstraction_primitive_object_csvtesting.space_Check();	
								   		    
										 }  
								   		if(index ==3)
									   	 {
								   		    abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();	
								   		   
										 } 
								   		if(index == 4)
									   	 {
											abstraction_primitive_object_csvtesting.upper_letter_Check();
											
									   	 }
								   		if(index ==5)
									   	 {
								   		    abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();	
								   		    
										 }  
								   		if(index ==6)
									   	 {
								   		    abstraction_primitive_object_csvtesting.lower_letter_Check();	
								   		   
										 } 
								   		if(index ==7)
									   	 {
								   		    abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();
								   		    
										 } 
								   		if(index ==8)
									   	 {
								   		    abstraction_primitive_object_csvtesting.symbol_Check();
								   		    
										 } 
								   		if(index ==9)
									   	 {
								   		    abstraction_primitive_object_csvtesting.missing_values_check();
								   		    
										 }
								   	  }
								   	 
								   	if(!listofEmptyValues.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
								   	 {
								   		listofEmptyValues.add(abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
								   	 }
								 }
						   		null_class_map.put(columnList.get(i),new ArrayList<Object>(listofEmptyValues));
						   	}
					   	
					   }
					   	Collections.reverse(listofEmptyValues);
					   	listoflist.add(new ArrayList<Object>(listofEmptyValues));  
					   	
//						    for(Object index: listofEmptyValues)
//						   	{
//						  		System.out.println(index);
//						   	}
						   
				 }  
				 
				 else if(columnList.get(i).matches("[0-9]+") || columnList.get(i).matches("\"[0-9]+\""))
					{
					
					 listOFdigits.clear();
					if(store_temp_permutation_digits.isEmpty())
					{
						temp_permutation_results_list.clear();
						set_for_dependency_list.clear();
						//System.out.println(columnList.get(i));
					    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
					    specified_depdendencies = paired_dependencies;
					 	permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
					   	set_for_dependency_list.addAll(permuteLIST);
			    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
			    		
			    		store_temp_permutation_digits.addAll(temp_permutation_results_list);
//					   	for(List<Integer> index: temp_permutation_results_list)
//					   	{
//					   		System.out.println(index);
//					   	}
					}

				   	if(digit_class_map.containsKey(columnList.get(i)))
				   	{
				    listOFdigits.addAll(digit_class_map.get(columnList.get(i)));
				    
				   	}
				   	else
				   	{
				   		for(List<Integer> list : store_temp_permutation_digits)
			         	  {	
					   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
						   	 for (Integer index : list)
						   	 {	
						   		if(index == 1)
							   	 {
									abstraction_primitive_object_csvtesting.digits_Check();
									
							   	 }
						   		if(index ==2)
							   	 {
						   		    abstraction_primitive_object_csvtesting.updated_sequenceofdigits_Check();	
						   		    
								 }  
						   		if(index ==3)
							   	 {
						   		    abstraction_primitive_object_csvtesting.quotation_Check();	
						   		   
								 } 
						   		if(index ==4)
							   	 {
						   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();
						   		    
								 } 
						   	  }
						
						   	if(!listOFdigits.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
						   	 {
						   		listOFdigits.add( abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
						   	 }	
						  }
				   		
				   		digit_class_map.put(columnList.get(i), new ArrayList<Object>(listOFdigits));
				   	}
				   
				   	
				   	Collections.reverse(listOFdigits);
				   	listoflist.add(new ArrayList<Object>(listOFdigits));  
				   	
//					    for(String index: listOFdigits)
//					   	{
//					  		System.out.println(index);
//					   	}
					   
			        }
				
					
					else if(columnList.get(i).matches("[A-Z]+") || columnList.get(i).matches("\"[A-Z]+\""))
				    {
						listOFUpperAlphabets.clear();
						if(store_temp_permutation_UpperLetter.isEmpty())
						{
							temp_permutation_results_list.clear();
							set_for_dependency_list.clear();
							//System.out.println(columnList.get(i));
						    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
						    specified_depdendencies = paired_dependencies;
						     permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
						   	set_for_dependency_list.addAll(permuteLIST);
				    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
				    		store_temp_permutation_UpperLetter.addAll(temp_permutation_results_list);
//						   	for(List<Integer> index: temp_permutation_results_list)
//						   	{
//						   		System.out.println(index);
//						   	}	
						}
						
					   	if(upper_Letter_class_map.containsKey(columnList.get(i)))
					   	{
					   		listOFUpperAlphabets.addAll(upper_Letter_class_map.get(columnList.get(i)));
					   	}
					   	else
					   	{
					   		for(List<Integer> list : store_temp_permutation_UpperLetter)
				         	  {	
						   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
							   	 for (Integer index : list)
							   	 {	
							   		if(index == 1)
								   	 {
										abstraction_primitive_object_csvtesting.upper_letter_Check();
								   	 }
							   		if(index == 2)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();	
									 }    
							   		if(index ==3)
								   	 {
							   		    abstraction_primitive_object_csvtesting.quotation_Check();	
									 } 
							   		if(index ==4)
								   	 {
							   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	
									 } 
							   	  }
							   	if(!listOFUpperAlphabets.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
							   	 {
							   		listOFUpperAlphabets.add( abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
							   	 }					 	
				         	  }
					   		upper_Letter_class_map.put(columnList.get(i), new ArrayList<Object>(listOFUpperAlphabets));
					   	}
						
						Collections.reverse(listOFUpperAlphabets);
						listoflist.add(new ArrayList<Object>(listOFUpperAlphabets)); 	
//						    for(String index: listOFUpperAlphabets)
//						   	{
//						  		System.out.println(index);
//						   	}
						   
				        
				    }
					
					
					else if(columnList.get(i).matches("[a-z]+") || columnList.get(i).matches("\"[a-z]+\""))
				    {
						listOFLowerAlphabets.clear();
						if(store_temp_permutation_LowerLetter.isEmpty())
						{
							temp_permutation_results_list.clear();
							set_for_dependency_list.clear();
							//System.out.println(columnList.get(i));
						    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
						    specified_depdendencies= paired_dependencies;
						   	permuteLIST = rearrange_list_with_dependencies(permuteLIST);  
						   	set_for_dependency_list.addAll(permuteLIST);
				    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
				    		store_temp_permutation_LowerLetter.addAll(temp_permutation_results_list);
//						   	for(List<Integer> index: temp_permutation_results_list)
//						   	{
//						   		System.out.println(index);
//						   	}
						}
						if(lower_Letter_class_map.containsKey(columnList.get(i)))
					   	{
							listOFLowerAlphabets.addAll(lower_Letter_class_map.get(columnList.get(i)));
							
					   	}
					   	else
					   	{
					   		for(List<Integer> list : store_temp_permutation_LowerLetter)
				         	  {	
						   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
							   	 for (Integer index : list)
							   	 {	
							   		if(index == 1)
								   	 {
										abstraction_primitive_object_csvtesting.lower_letter_Check();
								   	 }
							   		if(index == 2)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();	
									 } 
							   		if(index ==3)
								   	 {
							   		    abstraction_primitive_object_csvtesting.quotation_Check();	
									 } 
							   		if(index ==4)
								   	 {
							   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	
									 } 
							   	  }
							   	if(!listOFLowerAlphabets.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
							   	 {
							   		listOFLowerAlphabets.add( abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
							   	 }
							 }
					   		
					   		lower_Letter_class_map.put(columnList.get(i), new ArrayList<Object>(listOFLowerAlphabets));
					   	}
										   	
						
						Collections.reverse(listOFLowerAlphabets);
					   	listoflist.add(new ArrayList<Object>(listOFLowerAlphabets)); 
				    	
//						    for(String index: listOFLowerAlphabets)
//						   	{
//						  		System.out.println(index);
//						   	}
						   
				    }
				 

					else if(columnList.get(i).matches("\\p{Blank}+") || columnList.get(i).matches("\"\\p{Blank}+\""))
				    {
						listOFblankspaces.clear();
						if(store_temp_permutation_BlankSpace.isEmpty())
						{
							temp_permutation_results_list.clear();
							set_for_dependency_list.clear();
							//System.out.println(columnList.get(i));
						    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
						    specified_depdendencies= paired_dependencies;
						   	permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
						   	set_for_dependency_list.addAll(permuteLIST);
				    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
				    		store_temp_permutation_BlankSpace.addAll(temp_permutation_results_list);
//						   	for(List<Integer> index: temp_permutation_results_list)
//						   	{
//						   		System.out.println(index);
//						   	}
						}
						
					   	if(blankSpace_class_map.containsKey(columnList.get(i)))
					   	{
					   		listOFblankspaces.addAll(blankSpace_class_map.get(columnList.get(i)));
					   	}
					   	else
					   	{
					   		for(List<Integer> list : store_temp_permutation_BlankSpace)
				         	  {	
						   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
							   	 for (Integer index : list)
							   	 {	
							   		if(index == 1)
								   	 {
										abstraction_primitive_object_csvtesting.space_Check();
								   	 }
							   		if(index == 2)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();	
									 }  
							   		if(index ==3)
								   	 {
							   		    abstraction_primitive_object_csvtesting.quotation_Check();	
									 } 
							   		if(index ==4)
								   	 {
							   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	 
									 } 
							   		
							   	  }
							   	if(!listOFblankspaces.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
							   	 {
							   		listOFblankspaces.add( abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
							   	 }
							 }
					   		blankSpace_class_map.put(columnList.get(i), new ArrayList<Object>(listOFblankspaces));
					   	  }
						
						    Collections.reverse(listOFblankspaces);
							listoflist.add(new ArrayList<Object>(listOFblankspaces));   			    	
//						    for(String index: listOFblankspaces)
//						   	{
//						  		System.out.println(index);
//						   	}
						   
				     }
					
					

					else if( columnList.get(i).matches("\"(^[a-z]|[A-Z])[a-zA-Z\\p{Blank}]+\"") || 
							columnList.get(i).matches("\"(^[a-z]|[A-Z]|\\p{Blank})[a-zA-Z\\p{Blank}]+\"") || columnList.get(i).matches("(^[a-z]|[A-Z])[a-zA-Z\\p{Blank}]+")
							|| columnList.get(i).matches("(^[a-z]|[A-Z]|\\p{Blank})[a-zA-Z\\p{Blank}]+"))		
				    {
						listOFLetters.clear();
						if(store_temp_permutation_text.isEmpty())
						{
							temp_permutation_results_list.clear();
							set_for_dependency_list.clear();
							//System.out.println(columnList.get(i));
						    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
						    specified_depdendencies= text_class_dependencies;
						    permuteLIST = rearrange_list_with_dependencies(permuteLIST);  
						   	set_for_dependency_list.addAll(permuteLIST);
				    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
				    		store_temp_permutation_text.addAll(temp_permutation_results_list);
//						   	for(List<Integer> index: temp_permutation_results_list)
//						   	{
//						   		System.out.println(index);
//						   	}
						}
						
						if(text_class_map.containsKey(columnList.get(i)))
						{
							listOFLetters.addAll(text_class_map.get(columnList.get(i)));
						}
						else
						{
							for(List<Integer> list : store_temp_permutation_text)
				         	  {	
						   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
							   	 for (Integer index : list)
							   	 {	
							   		if(index == 1)
								   	 {
										abstraction_primitive_object_csvtesting.upper_letter_Check();
								   	 }
							   		if(index == 2)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();	
									 }  
							   		if(index == 3)
								   	 {
										abstraction_primitive_object_csvtesting.lower_letter_Check();
								   	 }
							   		if(index == 4)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();	
									 }  
							   		if(index == 5)
								   	 {
										abstraction_primitive_object_csvtesting.space_Check();
								   	 }
							   		if(index == 6)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();	
									 }  
							   		if(index == 7)
								   	 {
							   		    abstraction_primitive_object_csvtesting.quotation_Check();	
									 } 
							   		if(index == 8)
								   	 {
							   		    abstraction_primitive_object_csvtesting.text_format_Check();	
									 } 
							   		if(index == 9)
								   	 {
							   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	
									 } 
							   	  }
							   	 if(!listOFLetters.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
							   	 {
							   		listOFLetters.add( abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
							   	 }
							 }
							text_class_map.put(columnList.get(i), new ArrayList<Object>(listOFLetters));
						  }
						
						    Collections.reverse(listOFLetters);
							listoflist.add(new ArrayList<Object>(listOFLetters));   			    	
//						    for(String index: listOFLetters)
//						   	{
//						  		System.out.println(index);
//						   	}   
						   
				    }
				
					else if(columnList.get(i).matches("[0-9.+-]+") || columnList.get(i).matches("\"[0-9.+-]+\""))
					{
						listOFNumber.clear();
						if(store_temp_permutation_number.isEmpty())
						{
							temp_permutation_results_list.clear();
							set_for_dependency_list.clear();
							//System.out.println(columnList.get(i));
						    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
						    specified_depdendencies= number_class_dependencies;
						   	permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
						   	set_for_dependency_list.addAll(permuteLIST);
				    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
				    		store_temp_permutation_number.addAll(temp_permutation_results_list);
//						   	for(List<Integer> index: temp_permutation_results_list)
//						   	{
//						   		System.out.println(index);
//						   	}
						 
						}
						
						if(number_class_map.containsKey(columnList.get(i)))
						{
							listOFNumber.addAll(number_class_map.get(columnList.get(i)));
						}
						else
						{
							for(List<Integer> list : store_temp_permutation_number)
				         	  {	
						   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
							   	 for (Integer index : list)
							   	 {	
							   		if(index == 1)
								   	 {
							   			abstraction_primitive_object_csvtesting.digits_Check();
								   	 }
							   		if(index == 2)
								   	 {
							   			abstraction_primitive_object_csvtesting.updated_sequenceofdigits_Check();
									 }  
									if(index == 3)
									{
										abstraction_primitive_object_csvtesting.quotation_Check();	
									}
							   		if(index == 4)
								   	 {
							   			abstraction_primitive_object_csvtesting.number_Check();
								   	 }
							   		if(index == 5)
								   	 {
							   			abstraction_primitive_object_csvtesting.arithmetic_opr_Check();
								   	 }
							   		if(index == 6)
								   	 {
							   			abstraction_primitive_object_csvtesting.symbol_Check();
								   	 }
							   	  }
							   	 if(!listOFNumber.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
							   	 {
							   		listOFNumber.add( abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
							   	 }
							 }
							number_class_map.put(columnList.get(i), new ArrayList<Object>(listOFNumber));
						  }
						
						    Collections.reverse(listOFNumber);
							listoflist.add(new ArrayList<Object>(listOFNumber));   			    	
//						    for(String index: listOFNumber)
//						   	{
//						  		System.out.println(index);
//						   	}   
					}
				
					else if( columnList.get(i).matches("[a-zA-Z0-9\\p{Blank}]+") || columnList.get(i).matches("\"[a-zA-Z0-9\\p{Blank}]+\""))		
				    {
						listOFAlphaNumeric.clear();
						if(store_temp_permutation_AlphaNumeric.isEmpty())
						{
							temp_permutation_results_list.clear();
							set_for_dependency_list.clear();
							//System.out.println(columnList.get(i));
						    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
						    specified_depdendencies= alphaNumeric_class_dependencies;
						    permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
						   	set_for_dependency_list.addAll(permuteLIST);
				    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
				    		store_temp_permutation_AlphaNumeric.addAll(temp_permutation_results_list);
//						   	for(List<Integer> index: temp_permutation_results_list)
//						   	{
//						   		System.out.println(index);
//						   	}
						 
						}
						
						if(alphaNumeric_class_map.containsKey(columnList.get(i)))
						{
						listOFAlphaNumeric.addAll(alphaNumeric_class_map.get(columnList.get(i)));
						}
						else
						{
							for(List<Integer> list : store_temp_permutation_AlphaNumeric)
				         	  {	
						   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
							   	 for (Integer index : list)
							   	 {	
							   		if(index == 1)
								   	 {
										abstraction_primitive_object_csvtesting.upper_letter_Check();
								   	 }
							   		if(index == 2)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();	
									 }  
							   		if(index == 3)
								   	 {
										abstraction_primitive_object_csvtesting.lower_letter_Check();
								   	 }
							   		if(index == 4)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();	
									 }  
							   		if(index == 5)
									{
										abstraction_primitive_object_csvtesting.digits_Check();
									}
									if(index == 6)
									{
										abstraction_primitive_object_csvtesting.updated_sequenceofdigits_Check();
									}
							   		if(index == 7)
								   	 {
										abstraction_primitive_object_csvtesting.space_Check();
								   	 }
							   		if(index == 8)
								   	 {
							   		    abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();	
									 }  
							   		if(index == 9)
								   	 {
							   		    abstraction_primitive_object_csvtesting.quotation_Check();	
									 } 
							   		if(index == 10)
								   	 {
							   		    //abstraction_primitive_object_csvtesting.fullText_format_Check();	
									 } 
							   	  }
							   	 if(!listOFAlphaNumeric.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
							   	 {
							   		listOFAlphaNumeric.add(abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
							   	 }
							 }
							alphaNumeric_class_map.put(columnList.get(i), new ArrayList<Object>(listOFAlphaNumeric));
						  }
						
						    Collections.reverse(listOFAlphaNumeric);
							listoflist.add(new ArrayList<Object>(listOFAlphaNumeric));   			    	
//						    for(String index: listOFAlphaNumeric)
//						   	{
//						  		System.out.println(index);
//						   	}   
						   
				    }
					
					else if(columnList.get(i).matches("[\\x00-\\x7F]+") ) // remove [^\r\n] if line breaks are required
				    {
						listOFallASCII.clear();
						if(columnList.get(i).length() > 50 && !(StringUtils.isAlphanumeric(columnList.get(i))) ) 
						{
							
							if(!listOFallASCII.contains(FULL_TEXT_CLASS))
						   	 {
						   		listOFallASCII.add(Arrays.asList(FULL_TEXT_CLASS));   
						   	 }	
						}
						else
						{
							listOFallASCII.clear();
							if(store_temp_permutation_fullText.isEmpty())
							{
								//System.out.println(columnList.get(i));
								temp_permutation_results_list.clear();
								set_for_dependency_list.clear();
							    List<Integer> permuteLIST = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17));
							    specified_depdendencies= allASCIIdependencies;
							    permuteLIST = rearrange_list_with_dependencies(permuteLIST); 
							   	set_for_dependency_list.addAll(permuteLIST);
					    		temp_permutation_results_list.addAll(PatternGeneration.pruning_Set(PatternGeneration.powerSet(set_for_dependency_list)));
					    		store_temp_permutation_fullText.addAll(temp_permutation_results_list);
//							   	for(List<Integer> index: temp_permutation_results_list)
//							   	{
//							   		System.out.println(index);
//							   	}
							   	
							}
							
							if(fullText_class_map.containsKey(columnList.get(i)))
							{
								listOFallASCII.addAll(fullText_class_map.get(columnList.get(i)));
							}
							else
							{
								for(List<Integer> list : store_temp_permutation_fullText)
					         	  {	
							   		abstraction_primitive_object_csvtesting.set_stringBuilder_abstraction_premitive(columnList.get(i));
								   	 for (Integer index : list)
								   	 {	
								   		if(index ==1)
								   		{
								   			abstraction_primitive_object_csvtesting.upper_letter_Check();
								   		}
								   		else if(index==2)
								   		{
								   			abstraction_primitive_object_csvtesting.updated_sequenceofUpperCaseLetters_Check();
								   		}
								   		else if(index==3)
								   		{
								   			abstraction_primitive_object_csvtesting.lower_letter_Check();
								   		}
								   		else if(index ==4)
								   		{
								   			abstraction_primitive_object_csvtesting.updated_sequenceofLowerCaseLetters_Check();
								   		}
								   		else if(index == 5)
								   		{
								   			abstraction_primitive_object_csvtesting.digits_Check();
								   		}
								   		else if(index == 6)
								   		{
								   			abstraction_primitive_object_csvtesting.updated_sequenceofdigits_Check();
								   		}	
								   		else if(index == 7)
								   		{
								   			abstraction_primitive_object_csvtesting.space_Check();
								   		}
								   		else if(index == 8) 
								   		{
								   			abstraction_primitive_object_csvtesting.updated_WhiteSpaces_Check();
								   		}
								   		else if(index == 9)
								   		{
								   			abstraction_primitive_object_csvtesting.quotation_Check();
								   		}
								   		else if(index == 10)
								   		{
								   			abstraction_primitive_object_csvtesting.brackets_Check();
								   		}
								   		else if(index == 11)
								   		{
								   			abstraction_primitive_object_csvtesting.arithmetic_opr_Check();
								   		}	
								   		else if(index == 12)
								   		{
								   			abstraction_primitive_object_csvtesting.symbol_Check();
								   		}
								   		else if(index == 13)
								   		{
								   			abstraction_primitive_object_csvtesting.text_format_Check();
								   		}
								   		else if(index == 14)
								   		{
								   			//abstraction_primitive_object_csvtesting.date_format_Check();   // removed from abstraction list on 5th April 2022
								   		}
								   		else if(index == 15)
								   		{
								   			//abstraction_primitive_object_csvtesting.fullText_format_Check();
								   		}
								   		else if(index == 16)
								   		{															
								   			abstraction_primitive_object_csvtesting.lineBreak();    
								   		}  
								   		else if(index == 17)
								   		{															
								   			abstraction_primitive_object_csvtesting.number_Check();    
								   		}  

								   	  }
								   	if(!listOFallASCII.contains(abstraction_primitive_object_csvtesting.get_abstraction_result()) )
								   	 {
								   		listOFallASCII.add( abstraction_primitive_object_csvtesting.get_abstraction_result());  // get abstraction results  
								   	 }
								 }
								fullText_class_map.put(columnList.get(i), new ArrayList<Object>(listOFallASCII));
							  }
						  }
							
						
						    Collections.reverse(listOFallASCII);
						    listoflist.add(new ArrayList<Object>(listOFallASCII));
//						    for(String index: listOFallASCII)
//						   	{
//						  		System.out.println(index);
//						   	}   
				     }
					
				 
					if(columnList.get(i) == "\n")
				    {
						
						int listSizeDifference = 0;
						if(listSize == 0)
						{
							listSize =listoflist.size();	
						}															
						else if(listSize >listoflist.size())						
						{
					     listSizeDifference = listSize-listoflist.size();		
						}
						
						if(listSizeDifference >0)
						{
							for(int k = 0; k<listSizeDifference ; k++)
							{																	 
								List appendNullList = new ArrayList<>();						
																								
								appendNullList.add(Arrays.asList(PADDED_CLASS));
								listoflist.add(listoflist.size()-1,appendNullList);
							}
						}
						
				    	for(int index = 0; index<listoflist.size(); index++)
					   	{
				    		if(map_Combined_listoflist_results.containsKey(index))
				    		{
				    			map_Combined_listoflist_results.get(index).add(listoflist.get(index));
				    		}
				    		else
				    		{
				    			map_Combined_listoflist_results.computeIfAbsent(index, k -> new ArrayList()).add(listoflist.get(index));	
				    		}

						}
						
				    	listoflist.clear();
			     }
				    
			   }
	
	return map_Combined_listoflist_results;
	 
  }

 
 private static boolean isAllASCII(String input) {
	    boolean isASCII = true;
	    for (int i = 0; i < input.length(); i++) {
	        int c = input.charAt(i);
	        if (c > 0x7F) {
	            isASCII = false;
	            break;
	        }
	    }
	    return isASCII;
  }
 
 public String getStringRepresentation(Map<Integer, Object> input ) 
 {
 		StringBuilder str_builder_getStringRepresentation = new StringBuilder();
 		for(Object o : input.values()) 
 			str_builder_getStringRepresentation.append(o.toString());
 		return str_builder_getStringRepresentation.toString();
 	
 }

 public static boolean dependent_permutation_check(List<Integer> permutation_combintaion)
 {
 	
 	for(Dependency_Class d: specified_depdendencies)
 	{
 		d.reset();
 	}
      
     for(int i:permutation_combintaion)
     {
     	for(Dependency_Class d: specified_depdendencies)
     	{
     		if(!d.add(i))
     			return false;
     	}
     	
     }
     return true;
     
 }

 public static List<ArrayList<Integer>> pruning_Set(Set<Set<Integer>> input_set)
 {
 	List<ArrayList<Integer>> rearrange_set= new ArrayList<ArrayList<Integer>>();
 	for(Set<Integer> set :input_set)
 	{
 		if(dependent_permutation_check(new ArrayList<Integer>(set)))
 		{
 			rearrange_set.add(new ArrayList<Integer>(set));
 		}
 	}
 	
 	return rearrange_set;
 }

 public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
 	
     Set<Set<T>> sets = new LinkedHashSet<Set<T>>();
     if (originalSet.isEmpty()) {
         sets.add(new LinkedHashSet<T>());
         return sets;
     }
     List<T> list = new ArrayList<T>(originalSet);
     T head = list.get(0);
     Set<T> rest = new LinkedHashSet<T>(list.subList(1, list.size())); 
     for (Set<T> set : powerSet(rest)) {
         Set<T> newSet = new LinkedHashSet<T>();
         newSet.add(head);
         newSet.addAll(set);
         sets.add(newSet);
         sets.add(set);
     }       
     return sets;
 }  


 public List<Integer> rearrange_list_with_dependencies(List<Integer> list_rearrange_data)
 {
 	for(Dependency_Class d: specified_depdendencies)
 	{
 		d.reset();
 	}											
      											
     for(int i:list_rearrange_data)
     {
     	for(Dependency_Class d: specified_depdendencies)
     	{
     		if(!d.add(i))
     		{
     			swap(list_rearrange_data, list_rearrange_data.indexOf(i),  list_rearrange_data.indexOf(d.get_conflict_value()) );
     			rearrange_list_with_dependencies(list_rearrange_data);
     		}	
     	}
     }
     return list_rearrange_data;
 }


 public List<Integer> swap(List<Integer> list_rearrange_data, int i, int j) 
 { 
 	Collections.swap(list_rearrange_data, i, j);
 	return list_rearrange_data;
 } 


 
}
