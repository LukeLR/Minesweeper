/**
 * 
 */
package gui;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import meta.HighscoreList;

public class HighscoreTableView<S> extends TableView<S> {
	private HighscoreList list;
	private boolean isCustomHighscoreTable = false;
	private TableColumn number, name, startTime, duration, moves;
	private TableColumn fieldWidth, fieldHeight, mines;
	
	public HighscoreTableView(){
		this(null);
	}
	
	public HighscoreTableView(HighscoreList list, boolean isCustomHighscoreTable){
		super();
		this.list = list;
		this.isCustomHighscoreTable = isCustomHighscoreTable;
		this.initColumns();
	}
	
	public HighscoreTableView(HighscoreList list){
		this(list, false);
	}
	
	public HighscoreTableView(boolean isCustomHighscoreTable){
		this(null, isCustomHighscoreTable);
	}
	
	private void initColumns(){
		number = new TableColumn("#");
		name = new TableColumn("Name:");
		startTime = new TableColumn("Start time:");
		duration = new TableColumn("Duration:");
		moves = new TableColumn("Moves:");
		this.getColumns().addAll(number, name, startTime, duration, moves);
		if (this.isCustomHighscoreTable){
			fieldWidth = new TableColumn("Field width:");
			fieldHeight = new TableColumn("Field heighth:");
			mines = new TableColumn ("Mines:");
			this.getColumns().addAll(fieldWidth, fieldHeight, mines);
		}
	}
	
	public void fillWithData(){
		if (list == null){}//TODO: Fehlermeldung!
		else {
			
		}
	}
	
	public void fillWithData(HighscoreList list){
		this.list = list;
		fillWithData();
	}
}