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
	
}
