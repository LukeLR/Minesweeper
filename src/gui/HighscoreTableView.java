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
		
		/*
		 * The source code of the TableColumn class in OpenJDK declares a Cell Factory as follows:
		 * 
		 * public static final Callback<TableColumn<?,?>, TableCell<?,?>> DEFAULT_CELL_FACTORY = new Callback<TableColumn<?,?>, TableCell<?,?>>() {
		 * 	@Override public TableCell<?,?> call(TableColumn<?,?> param) {
		 * 		return new TableCell() {
		 * 			@Override protected void updateItem(Object item, boolean empty) {
		 * 				if (item == getItem()) return;
		 * 
		 * 				super.updateItem(item, empty);
		 * 				if (item == null) {
		 * 					super.setText(null);
		 * 					super.setGraphic(null);
		 * 				} else if (item instanceof Node) {
		 * 					super.setText(null);
		 * 					super.setGraphic((Node)item);
		 * 				} else {
		 * 					super.setText(item.toString());
		 * 					super.setGraphic(null);
		 * 				}
		 * 			}
		 * 		};
		 * 	}
		 * };
		 * (http://hg.openjdk.java.net/openjfx/2.2/master/rt/file/e71070b0e0c0/javafx-ui-controls/src/javafx/scene/control/TableColumn.java)
		 * 
		 * This was taken as an inspiration on coding the Highscore row numbers as in the following lines:
		 * 
		 * Similiar problems are discussed in these StackOverflow questions:
		 * http://stackoverflow.com/questions/19245822/custom-java-fx-cellfactory-messes-up-the-setcellvaluefactory
		 * http://stackoverflow.com/questions/16384879/auto-numbered-table-rows-javafx
		 * http://stackoverflow.com/questions/13449707/automatic-row-numbering-in-javafx-table and
		 * http://stackoverflow.com/questions/10519534/cell-factory-in-javafx
		 */
		
		number.setCellFactory(new Callback<TableColumn<HighscoreEntry, Integer>, TableCell<HighscoreEntry, Integer>>() {
		    @Override public TableCell<HighscoreEntry, Integer> call(TableColumn<HighscoreEntry, Integer> param) {
		        return new RowNumberCell<HighscoreEntry, Integer>();
		    }
		}); //TODO: Highscore Entry Numbers are wrong when the user changes the sorting of the tables (the values aren't sorted as well)
		
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