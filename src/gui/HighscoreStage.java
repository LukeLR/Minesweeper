package gui;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import meta.Data;
import meta.HighscoreEntry;
import meta.HighscoreList;

public class HighscoreStage extends Stage {
	
	private BorderPane borderPane;
	private Scene s;
	private TabPane tabPane;
	private Tab easy, intermediate, hard, custom;
	private TableView<HighscoreEntry> tableEasy, tableIntermediate, tableHard, tableCustom;
	
	private final ObservableList<HighscoreEntry> data =
		FXCollections.observableArrayList(
			new HighscoreEntry("asdf", System.currentTimeMillis(), 12512354, 123),
			new HighscoreEntry("foo", System.currentTimeMillis(), 752174, 1234),
			new HighscoreEntry("bar", System.currentTimeMillis(), 132478, 2134)
		);
	
	@SuppressWarnings("unchecked")
	public HighscoreStage(){
		super.setTitle("Highscore");
		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(25, 25, 25, 25));
		s = new Scene(new Group());
		this.setWidth(500);
		this.setHeight(500);
		
		tabPane = new TabPane();
		easy = new Tab("Easy");
		intermediate = new Tab("Intermediate");
		hard = new Tab("Hard");
		custom = new Tab("Custom");
		
		tabPane.getTabs().addAll(easy, intermediate, hard, custom);
		
		tableEasy = new TableView<HighscoreEntry>();
		tableEasy.setEditable(true);
		tableIntermediate = new TableView<HighscoreEntry>();
		tableHard = new TableView<HighscoreEntry>();
		tableCustom = new TableView<HighscoreEntry>();
		
		TableColumn number = new TableColumn("#");
		TableColumn name = new TableColumn("Name");
		TableColumn startTime = new TableColumn("Start Time");
		TableColumn duration = new TableColumn("Duration");
		TableColumn moves = new TableColumn("Moves");
		
		TableColumn fieldWidth = new TableColumn("Width");
		TableColumn fieldHeight = new TableColumn("Height");
		TableColumn mines = new TableColumn("Mines");
		
		name.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("name"));
		startTime.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("startTimeString"));
		duration.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("durationString"));
		moves.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("moves"));
		
		fieldWidth.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("xTiles"));
		fieldHeight.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("yTiles"));
		mines.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("mines"));
		
		System.out.println("1");
		tableEasy.setItems(data);
//		System.out.println("2");
//		tableIntermediate.setItems(Data.getHighscoresIntermediate().getObservableList());
//		System.out.println("3");
//		tableHard.setItems(Data.getHighscoresHard().getObservableList());
//		System.out.println("4");
//		tableCustom.setItems(Data.getHighscoresCustom().getObservableList());
//		System.out.println("5");
		
		tableEasy.getColumns().addAll(name, startTime, duration, moves);
		tableIntermediate.getColumns().addAll(number, name, startTime, duration, moves);
		tableHard.getColumns().addAll(number, name, startTime, duration, moves);
		tableCustom.getColumns().addAll(number, name, startTime, duration, moves, fieldWidth, fieldHeight, mines);
		
		easy.setContent(tableEasy);
		intermediate.setContent(tableIntermediate);
		hard.setContent(tableHard);
		custom.setContent(tableCustom);
		
		borderPane.setCenter(tableEasy);
		
		fillWithData();
		
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableEasy);
		
		((Group) s.getRoot()).getChildren().add(vbox);
		
		this.setScene(s);
		this.show();
	}
	
	private void fillWithData(){
		
	}
}
