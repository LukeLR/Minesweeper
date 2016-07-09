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
	}
	
	public void add(HighscoreEntry entry){
		entries.add(entry);
	}
	
	public HighscoreEntry get(int i){
		return entries.get(i);
	}
	
	public int size(){
		return entries.size();
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
	
	public ObservableList<HighscoreEntry> getObservableList(){
		ObservableList<HighscoreEntry> data = FXCollections.observableArrayList();
		data.addAll(entries);
		return data;
	}

	public Iterator<HighscoreEntry> iterator() {
		return entries.iterator();
	}
}
