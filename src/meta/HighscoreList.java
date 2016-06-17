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
	
	public int size(){
		return entries.size();
	}
	
	public HighscoreEntry get(long timestamp){
		HighscoreEntry dummy = new HighscoreEntry();
		dummy.setTime(timestamp);
		return entries.get(entries.lastIndexOf(dummy));
	}
	
	public String getAllNumbers(){
		String result = "";
		for (int i = 0; i < entries.size(); i++){
			result = result + String.valueOf(i) + System.lineSeparator();
		}
		return result;
	}
	
	public String getAllNames(){
		String result = "";
		for (HighscoreEntry h:entries){
			result = result + h.getName() + System.lineSeparator();
		}
		return result;
	}
	
	public String getAllTimes(){
		String result = "";
		for (HighscoreEntry h:entries){
			result = result + h.formatStartTime() + System.lineSeparator();
		}
		return result;
	}
	
	public String getAllDurations(){
		String result = "";
		for (HighscoreEntry h:entries){
			result = result + h.formatDuration() + System.lineSeparator();
		}
		return result;
	}
}
