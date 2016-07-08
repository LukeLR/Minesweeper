/**
 * 
 */
package gui;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import meta.HighscoreEntry;
import meta.HighscoreList;

public class HighscoreTableView extends TableView<HighscoreEntry> {
	private HighscoreList list;
	private boolean isCustomHighscoreTable = false;
	private TableColumn<HighscoreEntry, Integer> number, moves, fieldWidth, fieldHeight, mines;
	private TableColumn<HighscoreEntry, String> name, startTime, duration;
	
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
		number = new TableColumn<HighscoreEntry, Integer>("#");
		name = new TableColumn<HighscoreEntry, String>("Name:");
		startTime = new TableColumn<HighscoreEntry, String>("Start time:");
		duration = new TableColumn<HighscoreEntry, String>("Duration:");
		moves = new TableColumn<HighscoreEntry, Integer>("Moves:");
		this.getColumns().addAll(number, name, startTime, duration, moves);
		if (this.isCustomHighscoreTable){
			fieldWidth = new TableColumn<HighscoreEntry, Integer>("Field width:");
			fieldHeight = new TableColumn<HighscoreEntry, Integer>("Field heighth:");
			mines = new TableColumn<HighscoreEntry, Integer> ("Mines:");
			this.getColumns().addAll(fieldWidth, fieldHeight, mines);
		}
		
		number.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("number"));
		name.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("name"));
		startTime.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("startTime"));
		duration.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("duration"));
		moves.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("moves"));
		if (this.isCustomHighscoreTable){
			fieldWidth.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("fieldWidth"));
			fieldHeight.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("fieldHeight"));
			mines.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("mines"));
		}
		
		duration.setSortType(TableColumn.SortType.DESCENDING);
		this.getSortOrder().add(duration);
		duration.setSortable(true);
		this.sort();
	}
	
	public void fillWithData(){
//		if (list == null){}//TODO: Fehlermeldung!
//		else {
//			for (HighscoreEntry i : list){
//				number.cellValueFactoryProperty().set
//			}
//		}
		this.setItems(list.getObservableList());
	}
	
	public void fillWithData(HighscoreList list){
		this.list = list;
		fillWithData();
	}
}