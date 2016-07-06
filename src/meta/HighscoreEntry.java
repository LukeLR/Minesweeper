package meta;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalField;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class HighscoreEntry {
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
}
