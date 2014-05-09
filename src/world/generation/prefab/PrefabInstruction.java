package world.generation.prefab;

import java.util.ArrayList;

public class PrefabInstruction {

	private int header;
	private int instruction;
	private ArrayList<Integer> details = new ArrayList<Integer>();
	
	public PrefabInstruction(int instruction){
		header = 1;
	}
	
	public void addDetail(int detail){
		details.add(detail);
		header++;
	}
	
}
