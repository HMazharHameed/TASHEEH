package alignment;


import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Vertex implements Comparable<Vertex>
{
    public final Pair<Integer, Integer> name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(Pair<Integer, Integer> argName) 
    { 
  	  name = argName; 
    }
    public String toString() 
    {
		return "Vertex [" + name + "]";
	  }

	  public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}