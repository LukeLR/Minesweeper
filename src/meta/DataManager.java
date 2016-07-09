package meta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		} catch (FileNotFoundException ex){
			System.out.println("File " + path + " not found or no access permission. No loading.");
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (FileNotFoundException ex){
			System.out.println("File " + path + " not found or no access permission. No loading.");
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}