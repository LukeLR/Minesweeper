package meta;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class HighscoreEntrySerializable implements Serializable {
	private String name;
	private int number, moves, fieldWidth, fieldHeight, mines;
	private LocalDateTime startTime;
	private Duration duration;
	
	public HighscoreEntrySerializable(int number, String name, LocalDateTime startTime,
                                      Duration duration, int moves, int fieldWidth, int fieldHeight,
                                      int mines){
		this.number = number;
		this.name = name;
		this.startTime = startTime;
		this.duration = duration;
		this.moves = moves;
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		this.mines = mines;
	}
	
	public Object readResolve() throws ObjectStreamException {
		return new HighscoreEntry(number, name, startTime, duration, moves, 
				fieldWidth, fieldHeight, mines);
	}
}
