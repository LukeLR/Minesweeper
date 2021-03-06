package meta;

import java.io.Serializable;

import gui.MainWindow;

public class Data implements Serializable {
	private static final long serialVersionUID = 7936837128285584936L;
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
	private int moves = 0;
	private boolean lost = false;
	private boolean won = false;
	private int hiddenFields = xFields * yFields;
	public boolean firstClick = true;
	
	private transient MainWindow mw;
	private HighscoreList easy;
	private HighscoreList intermediate;
	private HighscoreList hard;
	private HighscoreList custom;
	
	public Data(){
		setMode(EASY);
		reset();
		newHighscores();
	}
	
	public void reset(){
		lost = false;
		won = false;
		resetMoves();
		this.resetFlagsSet();
		this.resetFirstClick();
		this.resetHiddenFields();
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
		if (mw == null) mw = new MainWindow();
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
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
		this.easy = easy;
	}

	public HighscoreList getHighscoresIntermediate() {
		if (intermediate == null) intermediate = new HighscoreList();
		return intermediate;
	}

	public void setHighscoresIntermediate(HighscoreList intermediate) {
		this.intermediate = intermediate;
	}

	public HighscoreList getHighscoresHard() {
		if (hard == null) hard = new HighscoreList();
		return hard;
	}

	public void setHighscoresHard(HighscoreList hard) {
		this.hard = hard;
	}

	public HighscoreList getHighscoresCustom() {
		if (custom == null) custom = new HighscoreList();
		return custom;
	}

	public void setHighscoresCustom(HighscoreList custom) {
		this.custom = custom;
	}
	
	public boolean firstClick(){
		return firstClick;
	}
	
	public void firstClicked(){
		firstClick = false;
	}
	
	public void addHighscoreEntry(HighscoreEntry entry){
		switch(getMode()){
			case EASY:
				if (easy == null) easy = new HighscoreList();
				getHighscoresEasy().add(entry);
				System.out.println("Adding Highscore Entry to EASY"); //TODO: Remove
				break;
			case INTERMEDIATE:
				if (intermediate == null) intermediate = new HighscoreList();
				getHighscoresIntermediate().add(entry);
				System.out.println("Adding Highscore Entry to INTERMEDIATE"); //TODO: Remove
				break;
			case HARD:
				if (hard == null) hard = new HighscoreList();
				getHighscoresHard().add(entry);
				System.out.println("Adding Highscore Entry to HARD"); //TODO: Remove
				break;
			case CUSTOM:
				if (custom == null) custom = new HighscoreList();
				getHighscoresCustom().add(entry);
				System.out.println("Adding Highscore Entry to CUSTOM"); //TODO: Remove
				break;
			default:
				System.out.println("Invalid gameMode when adding HighscoreEntry!"); //TODO: Remove
				break;
		}
	}
	
	public int getMoves(){
		return moves;
	}
	
	public void addMove(){
		moves++;
	}
	
	public void resetMoves(){
		moves = 0;
	}
	
	public void lost(){
		this.lost = true;
		this.won = false;
	}
	
	public void won(){
		this.won = true;
		this.lost = false;
	}
	
	public boolean isLost(){
		return lost;
	}
	
	public boolean isWon(){
		return won && !lost;
	}
	
	public void newHighscores(){
		easy = new HighscoreList();
		intermediate = new HighscoreList();
		hard = new HighscoreList();
		custom = new HighscoreList();
	}
	
	public boolean gameOver(){
		return won || lost;
	}
	
	public void increaseFlagsSet(){
		this.flagsSet++;
		this.mainWindow().updateMineNum();
	}
	
	public void decreaseFlagsSet(){
		this.flagsSet--;
		this.mainWindow().updateMineNum();
	}
}
