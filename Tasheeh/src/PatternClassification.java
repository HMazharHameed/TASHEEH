import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import abstractions.Candidate_Delimiter_Class;
import javafx.util.Pair;

public class PatternClassification {
	
	private static final Candidate_Delimiter_Class CANDIDATE_DELIMITER_CLASS = new Candidate_Delimiter_Class();

	public List<PatternAlignmentResult> patternReciever(List<ComparePatternCombinations> cpcList, List<PatternStatistics> psList)
	{
		// cpcList contains valid patterns
		// psList contains patterns that need to be further classified or transformed
		
		List<List<DynamicMatrixStatistics>> globalAlignment= new ArrayList<List<DynamicMatrixStatistics>>();
		List<DynamicMatrixStatistics> globalAlignment_subList = new ArrayList<DynamicMatrixStatistics>();
        
		for(int i = 0; i<cpcList.size(); i++)
		{ 
			globalAlignment_subList.clear();
			
			for(int j = 0; j<psList.size(); j++)
			{
				float seqAlignmentMatrix[][] = PatternAlignment.sequenceAlignment(psList.get(j).patternReprsentation, cpcList.get(i).abstrcationInstances);
				globalAlignment_subList.add(new DynamicMatrixStatistics(cpcList.get(i).abstrcationInstances, psList.get(j).patternReprsentation, psList.get(j).patternIndices, seqAlignmentMatrix, seqAlignmentMatrix[seqAlignmentMatrix.length-1][seqAlignmentMatrix[seqAlignmentMatrix.length-1].length-1]));
			}	
			
			globalAlignment.add(new ArrayList<DynamicMatrixStatistics>(globalAlignment_subList));
			
		}
		
		return PatternClassification.patAlignment(globalAlignment);  // returning wanted and unwanted indices to Main_Class
		
	}
	
	// pattern alignment -------------------------------pattern alignment ----------------------------pattern alignment 
	
	public static List<PatternAlignmentResult> patAlignment(List<List<DynamicMatrixStatistics>> globalAlignment)
	{
		List<List<PatternAlignmentResult>> out_PAR = new LinkedList<List<PatternAlignmentResult>>();
		int dominanPatternSegmentSize = 0;
		if(globalAlignment.size() >1)
		{
			List<DynamicMatrixStatistics> updated_clusters = new ArrayList<DynamicMatrixStatistics>(); 
			int count = globalAlignment.size();
			List<DynamicMatrixStatistics> subLists = new ArrayList<DynamicMatrixStatistics>();
			for(int l = 0; l < globalAlignment.get(0).size(); l++)
			{
			   int counter = 0;
			   subLists.clear();
			   while(count > counter)
			   {
				  subLists.add(globalAlignment.get(counter).get(l));
				  counter++;
			   }
			   updated_clusters.addAll(new ArrayList<DynamicMatrixStatistics>(getMaximumAlignmentMatrix(subLists)));
			}
		
			for(int k = 0; k<updated_clusters.size(); k++)
			{
				//System.out.println(Csv_Writer.getStringRepresentation(updated_clusters.get(k).dominantPattern) +"       "+ Csv_Writer.getStringRepresentation(updated_clusters.get(k).potentialDominantPattern));
				//System.out.println("Pattern alignment score is "+ updated_clusters.get(k).cost_f);
//				for (float[] x : updated_clusters.get(k).dynamicMatrix_f)
//		        {
//		           for(float y : x)
//		           {
//		                System.out.print(y + " ");
//		           }
//		           System.out.println();
//		        }
				
				out_PAR.add(PatternAlignment.shortestPathGraphCreation(updated_clusters.get(k).dynamicMatrix_f, updated_clusters.get(k).potentialDominantPattern, updated_clusters.get(k).dominantPattern, updated_clusters.get(k).crossponding_Rows));
				
				if(updated_clusters.get(k).dominantPattern.size() > dominanPatternSegmentSize)
				 dominanPatternSegmentSize = updated_clusters.get(k).dominantPattern.size();
			}
		}
		else
		{
			for(List<DynamicMatrixStatistics> list : globalAlignment)
			{
				//System.out.println("------------------------------------- pattern alignments--------------------------------------");
				for(int k = 0; k<list.size(); k++)
				{
//					System.out.println(Csv_Writer.getStringRepresentation(list.get(k).dominantPattern) +"       "+ Csv_Writer.getStringRepresentation(list.get(k).potentialDominantPattern));
//					System.out.println("Pattern alignment score is "+ list.get(k).cost_f);
//					for (float[] x : list.get(k).dynamicMatrix_f)
//			        {
//			           for (float y : x)
//			           {
//			                System.out.print(y + " ");
//			           }
//			           System.out.println();
//			        }
					
					out_PAR.add(PatternAlignment.shortestPathGraphCreation(list.get(k).dynamicMatrix_f, list.get(k).potentialDominantPattern, list.get(k).dominantPattern, list.get(k).crossponding_Rows));
					
					dominanPatternSegmentSize = list.get(k).dominantPattern.size();
		       	}
			}
		}
		
		return PatternClassification.patternAlignmentPrePro(out_PAR, dominanPatternSegmentSize); 
	}
    
	// pattern alignment preprocessor -------------------------------pattern alignment preprocessor----------------------------pattern alignment preprocessor
	
	public static List<PatternAlignmentResult> patternAlignmentPrePro(List<List<PatternAlignmentResult>> out_PAR, int dominanPatternSegmentSize)
	{
		List<PatternAlignmentResult> patterns_for_classification = new ArrayList<PatternAlignmentResult>();
		 
		for(List<PatternAlignmentResult> par_list : out_PAR)	
		{
			for(PatternAlignmentResult par: par_list)
			{
				if(par.pairs_of_patterns.size() > dominanPatternSegmentSize)
				{
					patterns_for_classification.add(PatternAlignmentPreprocessor.repairPatterns(par)); 
				}
				else
				{
					patterns_for_classification.add(par);
				}
			}
		}
		
		return patterns_for_classification;
	}
	
	public static List<DynamicMatrixStatistics> getMaximumAlignmentMatrix(List<DynamicMatrixStatistics> subLists) 
	{
		float min = subLists.get(0).cost_f;    // Initialize 
		List<DynamicMatrixStatistics> dmcList= new ArrayList<DynamicMatrixStatistics>();
		dmcList.add(subLists.get(0));   // Initialize 
		for(DynamicMatrixStatistics dmc: subLists)
		{
			if(dmc.cost_f < min)
			{
				min = dmc.cost_f;
				dmcList.set(0, dmc);
			}
		}
		return dmcList;
	}
	
}
