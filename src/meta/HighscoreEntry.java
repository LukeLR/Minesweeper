package meta;

import java.io.Serializable;

public class HighscoreEntry implements Serializable {
	private String name;
	private long duration;
	private long startTime;
	
	public HighscoreEntry(){
		
	}
	
	public HighscoreEntry(String name, long startTime, long duration){
		this.name = name;
		this.startTime = startTime;
		this.duration = duration;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getTime() {
		return duration;
	}
	
	public void setTime(long time) {
		this.duration = time;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public boolean isEqual(Object anObject){
		try{
			HighscoreEntry another = (HighscoreEntry)anObject;
			return another.getStartTime() == getStartTime();
		} catch (Exception ex){
			return false;
		}
	}
}
