package meta;

import java.io.Serializable;
import java.util.ArrayList;

public class HighscoreList implements Serializable {
	private ArrayList<HighscoreEntry> entries;
	
	public HighscoreList(){
		entries = new ArrayList<HighscoreEntry>();
	}
	
	public void add(String name, long startTime, long duration){
		entries.add(new HighscoreEntry(name, startTime, duration));
	}
	
	public HighscoreEntry get(int i){
		return entries.get(i);
	}
	
	public HighscoreEntry get(long timestamp){
		HighscoreEntry dummy = new HighscoreEntry();
		dummy.setTime(timestamp);
		return entries.get(entries.lastIndexOf(dummy));
	}
}
