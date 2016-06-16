package meta;

import java.io.Serializable;

import gui.MainWindow;

public class Data implements Serializable {
	public static final int EASY = 0;
	public static final int INTERMEDIATE = 1;
	public static final int HARD = 2;
	public static final int CUSTOM = 3;
	
	private static final int xFieldsDefaultEasy = 10;
	private static final int yFieldsDefaultEasy = 10;
	private static final int minesDefaultEasy = 20;
	
	private static final int xFieldsDefaultIntermediate = 20;
	private static final int yFieldsDefaultIntermediate = 20;
	private static final int minesDefaultIntermediate = 100;
	
	private static final int xFieldsDefaultHard = 30;
	private static final int yFieldsDefaultHard = 30;
	private static final int minesDefaultHard = 300;
	
	private static int xFields = 10;
	private static int yFields = 10;
	private static int mines = 20;
	private static int minesInGame = 20;
	private static int flagsSet = 0;
	private static int width = 1000;
	private static int height = 1000;
	private static int mode = 0;
	public static boolean firstClick = true;
	
	private static transient MainWindow mw;
	
	public static void setMode(int newMode){
		mode = newMode;
		switch(newMode){
		case 0:
			xFields = xFieldsDefaultEasy;
			yFields = yFieldsDefaultEasy;
			setMines(minesDefaultEasy);
			minesInGame = minesDefaultEasy;
			break;
		case 1:
			xFields = xFieldsDefaultIntermediate;
			yFields = yFieldsDefaultIntermediate;
			setMines(minesDefaultIntermediate);
			minesInGame = minesDefaultIntermediate;
			break;
		case 2:
			xFields = xFieldsDefaultHard;
			yFields = yFieldsDefaultHard;
			setMines(minesDefaultHard);
			minesInGame = minesDefaultHard;
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
			minesInGame = minesDefaultEasy;
			break;
		case 1:
			xFields = xFieldsDefaultIntermediate;
			yFields = yFieldsDefaultIntermediate;
			setMines(minesDefaultIntermediate);
			minesInGame = minesDefaultIntermediate;
			break;
		case 2:
			xFields = xFieldsDefaultHard;
			yFields = yFieldsDefaultHard;
			setMines(minesDefaultHard);
			minesInGame = minesDefaultHard;
			break;
		case 3: xFields = xFieldsNew; yFields = yFieldsNew; setMines(minesNew); minesInGame = minesNew; break;
		default: break;
		}
	}
	
	public static void setXFields(int xFieldsNew){
		xFields = xFieldsNew;
		determineGameMode();
	}
	
	public static void setYFields(int yFieldsNew){
		yFields = yFieldsNew;
		determineGameMode();
	}
	
	public static void setMines(int minesNew){
		mines = minesNew;
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

	public static int getMinesInGame() {
		return minesInGame;
	}

	public static void setMinesInGame(int minesInGame) {
		Data.minesInGame = minesInGame;
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
}
