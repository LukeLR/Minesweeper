package meta;

import gui.MainWindow;

public class DataManager {
	private static Data data;
	private static int i = 0;
	public static String defaultPath = "savegame.dat";
	
	private static void check(){
		if (data == null){ data = new Data(); System.out.println("Created new data object (" + i + ")"); i++;}
	}

	public static Data getData(){
		check(); return data;
	}
	
	public static Data resetData(){
		data = null;
		return getData();
	}
	
	public static void save(){
		save(defaultPath);
	}
	
	public static void save(String path){
		
	}
	
	public static void load(){
		load(defaultPath);
	}
	
	public static void load(String path){
		
	}
}