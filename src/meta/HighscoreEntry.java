package meta;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class HighscoreEntry implements Serializable{
	private SimpleStringProperty name;
	private SimpleIntegerProperty number, moves, fieldWidth, fieldHeight, mines;
	private SimpleObjectProperty<LocalDateTime> startTime;
	private SimpleObjectProperty<Duration> duration;
	
	public HighscoreEntry(int number, String name, LocalDateTime startTime, Duration duration,
                          int moves, int fieldWidth, int fieldHeight, int mines){
		this.number = new SimpleIntegerProperty (number);
		this.name = new SimpleStringProperty (name);
		this.startTime = new SimpleObjectProperty<LocalDateTime> (startTime);
		this.duration = new SimpleObjectProperty<Duration> (duration);
		this.moves = new SimpleIntegerProperty (moves);
		this.fieldWidth = new SimpleIntegerProperty (fieldWidth);
		this.fieldHeight = new SimpleIntegerProperty (fieldHeight);
		this.mines = new SimpleIntegerProperty (mines);
	}
	
	public HighscoreEntry(String name, LocalDateTime startTime, Duration duration,
            int moves, int fieldWidth, int fieldHeight, int mines){
		this(0, name, startTime, duration, moves, fieldWidth, fieldHeight, mines);
	}
	
	public HighscoreEntry(int number, String name, LocalDateTime startTime, Duration duration, int moves){
		this(number, name, startTime, duration, moves, 0, 0, 0);
	}
	
	public HighscoreEntry(String name, LocalDateTime startTime, Duration duration, int moves){
		this(0, name, startTime, duration, moves, 0, 0, 0);
	}
	
	public int getNumber(){
		return number.get();
	}
	
	public void setNumber(int number){
		this.number.setValue(number);
	}
	
	public String getName(){
		return name.get();
	}
	
	public void setName(String name){
		this.name.setValue(name);
	}
	
	public String getStartTime(){
		return startTime.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
	}
	
	public void setStartTime(String startTime){
		//TODO: Is it needed?
	}
	
	public String getDuration(){
		return String.format("%d:%02d.%03d", duration.getValue().getSeconds() / 60, (duration.getValue().getSeconds() % 60), duration.getValue().getNano() / 1000000);
	}
	
	public void setDuration(String duration){
		//TODO: Is it needed?
	}
	
	public int getMoves(){
		return moves.get();
	}
	
	public void setMoves(int moves){
		this.moves.setValue(moves);
	}
	
	public int getFieldWidth(){
		return fieldWidth.get();
	}
	
	public void setFieldWidth(int fieldWidth){
		this.fieldWidth.setValue(fieldWidth);
	}
	
	public int getFieldHeight(){
		return fieldHeight.get();
	}
	
	public void setFieldHeight(int fieldHeight){
		this.fieldHeight.setValue(fieldHeight);
	}
	
	public int getMines(){
		return mines.get();
	}
	
	public void setMines(int mines){
		this.mines.setValue(mines);
	}
}
