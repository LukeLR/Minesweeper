package meta;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalField;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class HighscoreEntry implements Serializable {
	private SimpleStringProperty name;
	private SimpleLongProperty duration;
	private SimpleStringProperty durationString;
	private SimpleObjectProperty<LocalDateTime> startTime;
	private SimpleStringProperty startTimeString;
	private SimpleIntegerProperty moves;
	private SimpleIntegerProperty xTiles;
	private SimpleIntegerProperty yTiles;
	private SimpleIntegerProperty mines;
	
	public HighscoreEntry(){
		
	}
	
	public HighscoreEntry(String name, long startTime, long duration, int moves){
		this.name = new SimpleStringProperty(name);
		this.startTime = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()));
		this.duration = new SimpleLongProperty(duration);
		this.moves = new SimpleIntegerProperty(moves);
		
		this.startTime.addListener((ov, oldValue, newValue) -> {
			startTimeString.set(formatStartTime());
		});
		
		this.duration.addListener((ov, oldValue, newValue) -> {
			durationString.set(formatDuration());
		});
	}
	
	public HighscoreEntry(String name, long startTime, long duration, int moves, int xTiles, int yTiles, int mines){
		this(name, startTime, duration, moves);
		this.xTiles = new SimpleIntegerProperty(xTiles);
		this.yTiles = new SimpleIntegerProperty(yTiles);
		this.mines = new SimpleIntegerProperty(mines);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public long getDuration() {
		return duration.get();
	}
	
	public void setDuration(long duration) {
		this.duration.set(duration);
	}
	
	public LocalDateTime getStartTime() {
		return startTime.get();
	}
	
	public void setStartTime(long startTime) {
		this.startTime.set(LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()));
	}
	
	public void setStartTime(LocalDateTime startTime){
		this.startTime.set(startTime);
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
		return getStartTime().getDayOfMonth() + "." + getStartTime().getMonth() + "." + getStartTime().getYear() + " " + getStartTime().getHour() + ":" + getStartTime().getMinute() + ":" + getStartTime().getSecond();
	}
	
	public String formatDuration(){
		int seconds = (int)((getDuration() % (1000 * 60))/1000);
		int minutes = (int)((getDuration() % (1000 * 60 * 60))/(1000*60));
		int millis = (int)(getDuration() % 1000);
		return String.valueOf(minutes) + ":" + String.valueOf(seconds) + "." + String.valueOf(millis);
	}

	public int getxTiles() {
		return xTiles.get();
	}

	public void setxTiles(int xTiles) {
		this.xTiles.set(xTiles);
	}

	public int getyTiles() {
		return yTiles.get();
	}

	public void setyTiles(int yTiles) {
		this.yTiles.set(yTiles);
	}

	public int getMines() {
		return mines.get();
	}

	public void setMines(int mines) {
		this.mines.set(mines);
	}

	public int getMoves() {
		return moves.get();
	}

	public void setMoves(int moves) {
		this.moves.set(moves);
	}
}
