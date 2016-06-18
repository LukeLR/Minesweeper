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
	
	private static int xFields = xFieldsDefaultEasy;
	private static int yFields = yFieldsDefaultEasy;
	private static int mines = minesDefaultEasy;
	private static int flagsSet = 0;
	private static int width = 1000;
	private static int height = 1000;
	private static int mode = 0;
	private static int hiddenFields = xFields * yFields;
	public static boolean firstClick = true;
	
	private static transient MainWindow mw;
	private static HighscoreList easy;
	private static HighscoreList intermediate;
	private static HighscoreList hard;
	private static HighscoreList custom;
	
	public static void setMode(int newMode){
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
	
	public static void setMode(int newMode, int xFieldsNew, int yFieldsNew, int minesNew){
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
	
	public static void setXFields(int xFieldsNew){
		xFields = xFieldsNew;
		hiddenFields = xFields * yFields;
		determineGameMode();
	}
	
	public static void setYFields(int yFieldsNew){
		yFields = yFieldsNew;
		hiddenFields = xFields * yFields;
		determineGameMode();
	}
	
	public static void setMines(int minesNew){
		mines = minesNew;
		hiddenFields = xFields * yFields;
		determineGameMode();
	}
	
	public static void determineGameMode(){
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
	
	public static void setMainWindow(MainWindow mwnew){
		mw = mwnew;
	}
	
	public static MainWindow mainWindow(){
		return mw;
	}
	
	public static void setFlagsSet(int flagsSetNew){
		if (flagsSet != flagsSetNew){
			flagsSet = flagsSetNew;
			mw.updateMineNum();
		}
	}
	
	public static int getFlagsSet(){
		return flagsSet;
	}

	public static int getXFields() {
		return xFields;
	}

	public static int getYFields() {
		return yFields;
	}

	public static int getMines() {
		return mines;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Data.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Data.height = height;
	}

	public static int getMode() {
		return mode;
	}
	
	public static void resetFlagsSet(){
		flagsSet = 0;
	}
	
	public static void resetFirstClick(){
		firstClick = true;
	}

	public static int getHiddenFields() {
		return hiddenFields;
	}

	public static void setHiddenFields(int coveredFields) {
		Data.hiddenFields = coveredFields;
	}
	
	public static void resetHiddenFields(){
		hiddenFields = xFields * yFields;
	}
	
	public static HighscoreList getHighscoresEasy() {
		if (easy == null) easy = new HighscoreList();
		return easy;
	}

	public static void setHighscoresEasy(HighscoreList easy) {
		Data.easy = easy;
	}

	public static HighscoreList getHighscoresIntermediate() {
		if (intermediate == null) intermediate = new HighscoreList();
		return intermediate;
	}

	public static void setHighscoresIntermediate(HighscoreList intermediate) {
		Data.intermediate = intermediate;
	}

	public static HighscoreList getHighscoresHard() {
		if (hard == null) hard = new HighscoreList();
		return hard;
	}

	public static void setHighscoresHard(HighscoreList hard) {
		Data.hard = hard;
	}

	public static HighscoreList getHighscoresCustom() {
		if (custom == null) custom = new HighscoreList();
		return custom;
	}

	public static void setHighscoresCustom(HighscoreList custom) {
		Data.custom = custom;
	}
}
