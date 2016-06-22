package meta;

public class DataManager {
	private static Data data;
	
	public Data getData(){
		if (data == null) return new Data();
		else return data;
	}
	
	public void setData(Data data){
		this.data = data;
	}
}
