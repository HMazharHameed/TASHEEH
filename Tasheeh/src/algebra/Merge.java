package algebra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Merge{

	public List<Object> apply(List<Object> input, List<Object> output) {
		
		List<Object> results = new ArrayList<Object>();
		return results;
	}

	public String reQuote_reEscape (String input, String univocityDetetced_QUOTE, String univocityDetetced_ESCAPE)
	{
		
		StringBuilder sb = new StringBuilder();
		boolean flagQuoteAppear = false;
				
		if(univocityDetetced_ESCAPE.contentEquals(univocityDetetced_QUOTE))
		{
			if(input.toString().matches("^[-\"%.+,0-9]+$")) 
			{
				sb.append("\""); 
				for(int i = 1; i<input.length()-1; i++)
				{
				   if(input.charAt(i) != univocityDetetced_QUOTE.charAt(0))
					{
						sb.append(input.charAt(i));
					}
				}
				sb.append("\""); 
			}
			else 
			{
				sb.append("\""); 
				for(int i = 1; i<input.length()-1; i++)
				{
					if(flagQuoteAppear == false && input.charAt(i) == univocityDetetced_QUOTE.charAt(0))
					{
						sb.append("\"");
						sb.append("\"");
						flagQuoteAppear = true;
					}
					else if(input.charAt(i) != univocityDetetced_QUOTE.charAt(0))
					{
						flagQuoteAppear = false;
						sb.append(input.charAt(i));
					}
				}
				sb.append("\""); 
			}
			
		}
		
		else
		{
			if(input.toString().matches("^[-\"%.+,0-9]+$")) 
			{
				if(univocityDetetced_ESCAPE.length()>1)
				{
					sb.append("\""); 
					for(int i = 1; i<input.length()-1; i++)
					{
					   if(i+1 <input.length()-1 && input.charAt(i) != univocityDetetced_ESCAPE.charAt(0) && input.charAt(i+1) != univocityDetetced_ESCAPE.charAt(1))
						{
							sb.append(input.charAt(i));
						}
					}
					sb.append("\"");
				}
				else
				{
					sb.append("\""); 
					for(int i = 1; i<input.length()-1; i++)
					{
					   if(input.charAt(i) != univocityDetetced_ESCAPE.charAt(0))
						{
							sb.append(input.charAt(i));
						}
					}
					sb.append("\""); 
				}
					
			}
			else 
			{
				
				if(univocityDetetced_ESCAPE.length()>1)
				{
					sb.append("\"");
					for(int i = 1; i<input.length()-1; i++)
					{
						if(i+1 <input.length()-1 && flagQuoteAppear == false && input.charAt(i) == univocityDetetced_ESCAPE.charAt(0) && input.charAt(i+1) == univocityDetetced_ESCAPE.charAt(1))
						{
							sb.append("\"");
							sb.append("\"");
							flagQuoteAppear = true;
						}
						else if(i+1 <input.length()-1 && input.charAt(i) != univocityDetetced_ESCAPE.charAt(0) && input.charAt(i+1) != univocityDetetced_ESCAPE.charAt(1))
						{
							flagQuoteAppear = false;
							sb.append(input.charAt(i));
						}
					}
					sb.append("\""); 
				}
				else
				{
					sb.append("\""); 
					for(int i = 1; i<input.length()-1; i++)
					{
						if(flagQuoteAppear == false && input.charAt(i) == univocityDetetced_ESCAPE.charAt(0))
						{
							sb.append("\"");
							sb.append("\"");
							flagQuoteAppear = true;
						}
						else if(input.charAt(i) != univocityDetetced_ESCAPE.charAt(0))
						{
							flagQuoteAppear = false;
							sb.append(input.charAt(i));
						}
					}
					sb.append("\""); 
				}	
			}
		}
		
		return sb.toString();
	}
}
