package meta;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		System.out.println("Saving Data to file '" + path + "' ...");
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(data);
			objectOutputStream.close();
		} catch (Exception ex){
			ex.printStackTrace();
			//TODO: Better error Handling
		}
	}
	
	public static void load(){
		load(defaultPath);
	}
	
	public static void load(String path){
		System.out.println("Loading Data of file '" + path + "' ...");
		try{
			FileInputStream fileInputStream = new FileInputStream(path);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			data = (Data)objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception ex){
			ex.printStackTrace();
			//TODO: Better error handling
		}
	}
}