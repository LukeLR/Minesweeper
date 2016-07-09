/**
 * 
 */
package gui;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import meta.HighscoreEntry;
import meta.HighscoreList;
import metagui.RowNumberCell;

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
		
		number.setCellFactory(new Callback<TableColumn<HighscoreEntry, Integer>, TableCell<HighscoreEntry, Integer>>() {
		    @Override public TableCell<HighscoreEntry, Integer> call(TableColumn<HighscoreEntry, Integer> param) {
		        return new RowNumberCell<HighscoreEntry, Integer>();
		    }
		});
		
//		number.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("number"));
		name.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("name"));
		startTime.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("startTime"));
		duration.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("duration"));
		moves.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("moves"));
		if (this.isCustomHighscoreTable){
			fieldWidth.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("fieldWidth"));
			fieldHeight.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("fieldHeight"));
			mines.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("mines"));
		}
	}
	
	public void fillWithData(){
		this.setItems(list.getObservableList());
		autoSort();
	}
	
	public void fillWithData(HighscoreList list){
		this.list = list;
		fillWithData();
	}
	
	public void autoSort(){
		duration.setSortType(TableColumn.SortType.ASCENDING);
		this.getSortOrder().add(duration);
		this.sort();
	}
}