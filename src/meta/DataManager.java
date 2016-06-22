package meta;

import gui.MainWindow;

public class DataManager {
	private static Data data;
	
	public static Data getData(){
		check();
		return data;
	}
	
	public static void setData(Data newData){
		data = newData;
	}
	
	private static void check(){
		if (data == null) data = new Data();
	}
	
	public void setMode(int newMode){check(); data.setMode(newMode);}
	
	public void setMode(int newMode, int xFieldsNew, int yFieldsNew, int minesNew){
		check();
		data.setMode(newMode, xFieldsNew, yFieldsNew, minesNew);
	}
	
	public void setXFields(int xFieldsNew){check(); data.setXFields(xFieldsNew);}
	
	public void setYFields(int yFieldsNew){check(); data.setYFields(yFieldsNew);}
	
	public void setMines(int minesNew){check(); data.setMines(minesNew);}
	
	public void determineGameMode(){check(); data.determineGameMode();}
	
	public void setMainWindow(MainWindow mwnew){check(); data.setMainWindow(mwnew);}
	
	public MainWindow mainWindow(){check(); return data.mainWindow();}
	
	public void setFlagsSet(int flagsSetNew){check(); data.setFlagsSet(flagsSetNew);}
	
	public int getFlagsSet(){check(); return data.getFlagsSet();}

	public int getXFields() {check(); return data.getXFields();}

	public int getYFields() {check(); return data.getYFields();}

	public int getMines() {check(); return data.getMines();}

	public int getWidth() {check(); return data.getWidth();}

	public void setWidth(int width) {check(); data.setWidth(width);}

	public int getHeight() {check(); return data.getHeight();}

	public void setHeight(int height) {check(); data.setHeight(height);}

	public int getMode() {check(); return data.getMode();}
	
	public void resetFlagsSet(){check(); data.resetFlagsSet();}
	
	public void resetFirstClick(){check(); data.resetFirstClick();}

	public int getHiddenFields() {check(); return data.getHiddenFields();}

	public void setHiddenFields(int coveredFields) {check(); data.setHiddenFields(coveredFields);}
	
	public void resetHiddenFields(){check(); data.resetHiddenFields();}
	
	public HighscoreList getHighscoresEasy() {check(); return data.getHighscoresEasy();}

	public void setHighscoresEasy(HighscoreList easy) {check(); data.setHighscoresEasy(easy);}

	public HighscoreList getHighscoresIntermediate() {check(); return data.getHighscoresIntermediate();	}

	public void setHighscoresIntermediate(HighscoreList intermediate) {check(); data.setHighscoresIntermediate(intermediate);}

	public HighscoreList getHighscoresHard() {check(); return data.getHighscoresHard();}

	public void setHighscoresHard(HighscoreList hard) {check(); data.setHighscoresHard(hard);}

	public HighscoreList getHighscoresCustom() {check(); return data.getHighscoresCustom();}

	public void setHighscoresCustom(HighscoreList custom) {check(); data.setHighscoresCustom(custom);}
}
