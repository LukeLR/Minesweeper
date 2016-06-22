package meta;

import java.io.Serializable;

import gui.MainWindow;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;

public class Data implements Serializable {
	public static final int EASY = 0;
	public static final int INTERMEDIATE = 1;
	public static final int HARD = 2;
	public static final int CUSTOM = 3;
	
	private static final int xFieldsDefaultEasy = 10;
	private static final int yFieldsDefaultEasy = 10;
	private static final int minesDefaultEasy = 10;
	
	private static final int xFieldsDefaultIntermediate = 20;
	private static final int yFieldsDefaultIntermediate = 20;
	private static final int minesDefaultIntermediate = 50;
	
	private static final int xFieldsDefaultHard = 30;
	private static final int yFieldsDefaultHard = 30;
	private static final int minesDefaultHard = 100;
	
	private int xFields = xFieldsDefaultEasy;
	private int yFields = yFieldsDefaultEasy;
	private int mines = minesDefaultEasy;
	private int flagsSet = 0;
	private int width = 1000;
	private int height = 1000;
	private int mode = 0;
	private int hiddenFields = xFields * yFields;
	public boolean firstClick = true;
	
	private transient MainWindow mw;
	private HighscoreList easy;
	private HighscoreList intermediate;
	private HighscoreList hard;
	private HighscoreList custom;
	
	public Data(){
		setMode(EASY);
	}
	
	public void setMode(int newMode){
		mode = newMode;
		switch(newMode){
		case 0:
			xFields = xFieldsDefaultEasy;
			yFields = yFieldsDefaultEasy;
			setMines(minesDefaultEasy);
			hiddenFields = xFields * yFields;
			break;
		case 1:
			xFields = xFieldsDefaultIntermediate;
			yFields = yFieldsDefaultIntermediate;
			setMines(minesDefaultIntermediate);
			hiddenFields = xFields * yFields;
			break;
		case 2:
			xFields = xFieldsDefaultHard;
			yFields = yFieldsDefaultHard;
			setMines(minesDefaultHard);
			hiddenFields = xFields * yFields;
			break;
		case 3: break;
		default: break;
		}
	}
	
	public void setMode(int newMode, int xFieldsNew, int yFieldsNew, int minesNew){
		mode = newMode;
		switch(newMode){
		case 0:
			xFields = xFieldsDefaultEasy;
			yFields = yFieldsDefaultEasy;
			setMines(minesDefaultEasy);
			hiddenFields = xFields * yFields;
			break;
		case 1:
			xFields = xFieldsDefaultIntermediate;
			yFields = yFieldsDefaultIntermediate;
			setMines(minesDefaultIntermediate);
			hiddenFields = xFields * yFields;
			break;
		case 2:
			xFields = xFieldsDefaultHard;
			yFields = yFieldsDefaultHard;
			setMines(minesDefaultHard);
			hiddenFields = xFields * yFields;
			break;
		case 3: xFields = xFieldsNew; yFields = yFieldsNew; setMines(minesNew); break;
		default: break;
		}
	}
	
	public void setXFields(int xFieldsNew){
		xFields = xFieldsNew;
		hiddenFields = xFields * yFields;
		determineGameMode();
	}
	
	public void setYFields(int yFieldsNew){
		yFields = yFieldsNew;
		hiddenFields = xFields * yFields;
		determineGameMode();
	}
	
	public void setMines(int minesNew){
		mines = minesNew;
		hiddenFields = xFields * yFields;
		determineGameMode();
	}
	
	public void determineGameMode(){
		if (xFields == xFieldsDefaultEasy && yFields == yFieldsDefaultEasy && getMines() == minesDefaultEasy){
			mode = EASY;
		} else if (xFields == xFieldsDefaultIntermediate && yFields == yFieldsDefaultIntermediate && getMines() == minesDefaultIntermediate){
			mode = INTERMEDIATE;
		} else if (xFields == xFieldsDefaultHard && yFields == yFieldsDefaultHard && getMines() == minesDefaultHard){
			mode = HARD;
		} else {
			mode = CUSTOM;
		}
	}
	
	public void setMainWindow(MainWindow mwnew){
		mw = mwnew;
	}
	
	public MainWindow mainWindow(){
		return mw;
	}
	
	public void setFlagsSet(int flagsSetNew){
		if (flagsSet != flagsSetNew){
			flagsSet = flagsSetNew;
			mw.updateMineNum();
		}
	}
	
	public int getFlagsSet(){
		return flagsSet;
	}

	public int getXFields() {
		return xFields;
	}

	public int getYFields() {
		return yFields;
	}

	public int getMines() {
		return mines;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		height = height;
	}

	public int getMode() {
		return mode;
	}
	
	public void resetFlagsSet(){
		flagsSet = 0;
	}
	
	public void resetFirstClick(){
		firstClick = true;
	}

	public int getHiddenFields() {
		return hiddenFields;
	}

	public void setHiddenFields(int coveredFields) {
		hiddenFields = coveredFields;
	}
	
	public void resetHiddenFields(){
		hiddenFields = xFields * yFields;
	}
	
	public HighscoreList getHighscoresEasy() {
		if (easy == null) easy = new HighscoreList();
		return easy;
	}

	public void setHighscoresEasy(HighscoreList easy) {
		easy = easy;
	}

	public HighscoreList getHighscoresIntermediate() {
		if (intermediate == null) intermediate = new HighscoreList();
		return intermediate;
	}

	public void setHighscoresIntermediate(HighscoreList intermediate) {
		intermediate = intermediate;
	}

	public HighscoreList getHighscoresHard() {
		if (hard == null) hard = new HighscoreList();
		return hard;
	}

	public void setHighscoresHard(HighscoreList hard) {
		hard = hard;
	}

	public HighscoreList getHighscoresCustom() {
		if (custom == null) custom = new HighscoreList();
		return custom;
	}

	public void setHighscoresCustom(HighscoreList custom) {
		custom = custom;
	}
}
