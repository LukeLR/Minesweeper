package meta;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalField;

public class HighscoreEntry implements Serializable {
	private String name;
	private long duration;
	private LocalDateTime startTime;
	private int moves;
	private int xTiles;
	private int yTiles;
	private int mines;
	
	public HighscoreEntry(){
		
	}
	
	public HighscoreEntry(String name, long startTime, long duration, int moves){
		this.name = name;
		this.startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault());
		this.duration = duration;
		this.moves = moves;
	}
	
	public HighscoreEntry(String name, long startTime, long duration, int moves, int xTiles, int yTiles, int mines){
		this(name, startTime, duration, moves);
		this.xTiles = xTiles;
		this.yTiles = yTiles;
		this.moves = moves;
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
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault());
;
	}
	
	public boolean isEqual(Object anObject){
		try{
			HighscoreEntry another = (HighscoreEntry)anObject;
			return another.getStartTime() == getStartTime();
		} catch (Exception ex){
			return false;
		}
	}
	
	public String formatStartTime(){
		return startTime.getDayOfMonth() + "." + startTime.getMonth() + "." + startTime.getYear() + " " + startTime.getHour() + ":" + startTime.getMinute() + ":" + startTime.getSecond();
	}
	
	public String formatDuration(){
		int seconds = (int)((duration % (1000 * 60))/1000);
		int minutes = (int)((duration % (1000 * 60 * 60))/(1000*60));
		int millis = (int)(duration % 1000);
		return String.valueOf(minutes) + ":" + String.valueOf(seconds) + "." + String.valueOf(millis);
	}

	public int getxTiles() {
		return xTiles;
	}

	public void setxTiles(int xTiles) {
		this.xTiles = xTiles;
	}

	public int getyTiles() {
		return yTiles;
	}

	public void setyTiles(int yTiles) {
		this.yTiles = yTiles;
	}

	public int getMines() {
		return mines;
	}

	public void setMines(int mines) {
		this.mines = mines;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}
}
