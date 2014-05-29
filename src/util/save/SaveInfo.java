package util.save;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SaveInfo {

	private int size = 0;
	private ArrayList<Integer> info = new ArrayList<Integer>();
	
	public void addInt(int a){
		size++;
		info.add(a);
	}
	
	
	public int saveInfo(FileOutputStream out) throws IOException{
		out.write(size);
		
		for (int i = 0; i < size; i++){	
			out.write(info.get(i));
		}
		
		return size;
	}
	
	public void loadInfo(FileInputStream in) throws IOException{
		size = in.read();
		
		for (int i = 0; i < size; i++){
			addInt(in.read());
		}
	}
}
