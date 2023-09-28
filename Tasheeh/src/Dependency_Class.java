import java.util.ArrayList;
import java.util.List;

public class Dependency_Class {

	 private int set_conflict_value; 
	 private int left_hand_side ;
	 private List<Integer >right_hand_side = new ArrayList<>();
	 private boolean seen_left_hand_side = false;
	 
	public Dependency_Class(int left_hand_side, List<Integer> right_hand_side) {
		super();
		this.left_hand_side = left_hand_side;
		this.right_hand_side = right_hand_side;
	}
	 

	public void reset()
	 {
		 seen_left_hand_side = false;
	 }
	 
	public void set_conflict_value(int value)
	{
		set_conflict_value = value;
	}
	
	public int get_conflict_value()
	{
		return set_conflict_value;
	}
	 public boolean add(int x)
	 {
		 if (x == left_hand_side)
			 seen_left_hand_side = true;
		 else if(right_hand_side.stream().anyMatch(element -> x == element) && !seen_left_hand_side)
		 {
			 set_conflict_value(left_hand_side);
			 return false;
		 }
			  
		return true;	 
	 }
}
