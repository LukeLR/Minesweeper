package meta;

import gui.MainWindow;

public class DataManager {
	private static Data data;
	private static int i = 0;
	
//	public static Data getData(){
//		check();
//		return data;
//	}
//	
//	public static void setData(Data newData){
//		data = newData;
//	}
	
	private static void check(){
		if (data == null){ data = new Data(); System.out.println("Created new data object (" + i + ")"); i++;}
	}
	
	public static void setMode(int newMode){check(); data.setMode(newMode);}
	
	public static void setMode(int newMode, int xFieldsNew, int yFieldsNew, int minesNew){
		check();
		data.setMode(newMode, xFieldsNew, yFieldsNew, minesNew);
	}
	
	public static void setXFields(int xFieldsNew){check(); data.setXFields(xFieldsNew);}
	
	public static void setYFields(int yFieldsNew){check(); data.setYFields(yFieldsNew);}
	
	public static void setMines(int minesNew){check(); data.setMines(minesNew);}
	
	public static void determineGameMode(){check(); data.determineGameMode();}
	
	public static void setMainWindow(MainWindow mwnew){check(); data.setMainWindow(mwnew);}
	
	public static MainWindow mainWindow(){check(); return data.mainWindow();}
	
	public static void setFlagsSet(int flagsSetNew){check(); data.setFlagsSet(flagsSetNew);}
	
	public static int getFlagsSet(){check(); return data.getFlagsSet();}

	public static int getXFields() {check(); return data.getXFields();}

	public static int getYFields() {check(); return data.getYFields();}

	public static int getMines() {check(); return data.getMines();}

	public static int getWidth() {check(); return data.getWidth();}

	public static void setWidth(int width) {check(); data.setWidth(width);}

	public static int getHeight() {check(); return data.getHeight();}

	public static void setHeight(int height) {check(); data.setHeight(height);}

	public static int getMode() {check(); return data.getMode();}
	
	public static void resetFlagsSet(){check(); data.resetFlagsSet();}
	
	public static void resetFirstClick(){check(); data.resetFirstClick();}

	public static int getHiddenFields() {check(); return data.getHiddenFields();}

	public static void setHiddenFields(int coveredFields) {check(); data.setHiddenFields(coveredFields);}
	
	public static void resetHiddenFields(){check(); data.resetHiddenFields();}
	
	public static HighscoreList getHighscoresEasy() {check(); return data.getHighscoresEasy();}

	public static void setHighscoresEasy(HighscoreList easy) {check(); data.setHighscoresEasy(easy);}

	public static HighscoreList getHighscoresIntermediate() {check(); return data.getHighscoresIntermediate();	}

	public static void setHighscoresIntermediate(HighscoreList intermediate) {check(); data.setHighscoresIntermediate(intermediate);}

	public static HighscoreList getHighscoresHard() {check(); return data.getHighscoresHard();}

	public static void setHighscoresHard(HighscoreList hard) {check(); data.setHighscoresHard(hard);}

	public static HighscoreList getHighscoresCustom() {check(); return data.getHighscoresCustom();}

	public static void setHighscoresCustom(HighscoreList custom) {check(); data.setHighscoresCustom(custom);}
	
	public static boolean firstClick(){check(); return data.firstClick();}
	
	public static void firstClicked(){check(); data.firstClicked();}
	
	public static void addHighscoreEntry(HighscoreEntry entry){check(); data.addHighscoreEntry(entry);}
	
	public static int getMoves(){check(); return data.getMoves();}
	
	public static void addMove(){check(); data.addMove();}
	
	public static void resetMoves(){check(); data.resetMoves();}
	
	public static void lost(){check(); data.lost();}
	
	public static void won(){check(); data.won();}
}
