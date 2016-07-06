package meta;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HighscoreList implements Serializable {
	private ArrayList<HighscoreEntry> entries;
	
	public HighscoreList(){
		entries = new ArrayList<HighscoreEntry>();
	}
	
	public void add(String name, long startTime, long duration, int moves){
		entries.add(new HighscoreEntry(name, startTime, duration, moves));
	}
	
	public void add(String name, long startTime, long duration, int moves, int xTiles, int yTiles, int mines){
		entries.add(new HighscoreEntry(name, startTime, duration, moves, xTiles, yTiles, mines));
	}
	
	public HighscoreEntry get(int i){
		return entries.get(i);
	}
	
	public int size(){
		return entries.size();
	}
	
	public HighscoreEntry get(long timestamp){
		HighscoreEntry dummy = new HighscoreEntry();
		dummy.setDuration(timestamp);
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
	
	public ObservableList<HighscoreEntry> getObservableList(){
		System.out.println("Number of Entries: " + size());
//		ObservableList<HighscoreEntry> oal = FXCollections.emptyObservableList();
//		for (HighscoreEntry i : entries) oal.add(i);
//		System.out.println("oal size: " + oal.size());
//		System.out.println("oal type: " + oal.get(0).getClass().getName());
//		return oal;
//		return FXCollections.observableArrayList(entries);
		return FXCollections.observableArrayList( //TODO: remove
			new HighscoreEntry("asdf", System.currentTimeMillis(), 314724, 123),
			new HighscoreEntry("foo", System.currentTimeMillis(), 23478123, 123),
			new HighscoreEntry("bar", System.currentTimeMillis(), 32194578, 123)
		);
	}
}
