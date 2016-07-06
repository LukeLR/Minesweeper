package meta;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HighscoreList implements Serializable,Iterable<HighscoreEntry> {
	private static final long serialVersionUID = 1328831942175681643L;
	private ArrayList<HighscoreEntry> entries;
	
	public HighscoreList(){
		entries = new ArrayList<HighscoreEntry>();
		entries.add(new HighscoreEntry(1, "asdf", LocalDateTime.now(), Duration.ofMinutes(23), 12, 23, 23, 2));
		entries.add(new HighscoreEntry(2, "foo", LocalDateTime.now(), Duration.ofMinutes(23), 12, 23, 23, 2));
		entries.add(new HighscoreEntry(3, "bar", LocalDateTime.now(), Duration.ofMinutes(23), 12, 23, 23, 2));
	}
	
//	public void add(String name, long startTime, long duration, int moves){
//		entries.add(new HighscoreEntry(name, startTime, duration, moves));
//	}
//	
//	public void add(String name, long startTime, long duration, int moves, int xTiles, int yTiles, int mines){
//		entries.add(new HighscoreEntry(name, startTime, duration, moves, xTiles, yTiles, mines));
//	}
	
	public HighscoreEntry get(int i){
		return entries.get(i);
	}
	
	public int size(){
		return entries.size();
	}
	
//	public HighscoreEntry get(long timestamp){
//		HighscoreEntry dummy = new HighscoreEntry();
//		dummy.setDuration(timestamp);
//		return entries.get(entries.lastIndexOf(dummy));
//	}
	
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
	
//	public String getAllTimes(){
//		String result = "";
//		for (HighscoreEntry h:entries){
//			result = result + h.formatStartTime() + System.lineSeparator();
//		}
//		return result;
//	}
//	
//	public String getAllDurations(){
//		String result = "";
//		for (HighscoreEntry h:entries){
//			result = result + h.formatDuration() + System.lineSeparator();
//		}
//		return result;
//	}
	
//	public ObservableList<HighscoreEntry> getObservableList(){
//		System.out.println("Number of Entries: " + size());
////		ObservableList<HighscoreEntry> oal = FXCollections.emptyObservableList();
////		for (HighscoreEntry i : entries) oal.add(i);
////		System.out.println("oal size: " + oal.size());
////		System.out.println("oal type: " + oal.get(0).getClass().getName());
////		return oal;
////		return FXCollections.observableArrayList(entries);
//		return FXCollections.observableArrayList( //TODO: remove
//			new HighscoreEntry("asdf", System.currentTimeMillis(), 314724, 123),
//			new HighscoreEntry("foo", System.currentTimeMillis(), 23478123, 123),
//			new HighscoreEntry("bar", System.currentTimeMillis(), 32194578, 123)
//		);
//	}
	
	public ObservableList<HighscoreEntry> getObservableList(){
		ObservableList<HighscoreEntry> data = FXCollections.observableArrayList();
		data.addAll(entries);
		return data;
	}

	public Iterator<HighscoreEntry> iterator() {
		return entries.iterator();
	}
}
