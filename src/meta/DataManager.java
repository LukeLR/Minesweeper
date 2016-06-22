package meta;

public class DataManager {
	private static Data data;
	
	public static Data getData(){
		if (data == null) return new Data();
		else return data;
	}
	
	public static void setData(Data newData){
		data = newData;
	}
}
