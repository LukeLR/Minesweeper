package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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

	public HighscoreStage(){
		super.setTitle("Highscore");
		borderPane = new BorderPane();
		borderPane.setPadding(new Insets(25, 25, 25, 25));
		s = new Scene(borderPane, 500, 500);
		
		tabPane = new TabPane();
		easy = new Tab("Easy");
		intermediate = new Tab("Intermediate");
		hard = new Tab("Hard");
		custom = new Tab("Custom");
		
		tabPane.getTabs().addAll(easy, intermediate, hard, custom);
		
		tableEasy = new TableView<HighscoreEntry>();
		tableIntermediate = new TableView<HighscoreEntry>();
		tableHard = new TableView<HighscoreEntry>();
		tableCustom = new TableView<HighscoreEntry>();
		
		TableColumn<HighscoreEntry, Integer> number = new TableColumn<HighscoreEntry, Integer>("#");
		TableColumn<HighscoreEntry, String> name = new TableColumn<HighscoreEntry, String>("Name");
		TableColumn<HighscoreEntry, String> startTime = new TableColumn<HighscoreEntry, String>("Start Time");
		TableColumn<HighscoreEntry, String> duration = new TableColumn<HighscoreEntry, String>("Duration");
		TableColumn<HighscoreEntry, Integer> moves = new TableColumn<HighscoreEntry, Integer>("Moves");
		
		TableColumn<HighscoreEntry, Integer> fieldWidth = new TableColumn<HighscoreEntry, Integer>("Width");
		TableColumn<HighscoreEntry, Integer> fieldHeight = new TableColumn<HighscoreEntry, Integer>("Height");
		TableColumn<HighscoreEntry, Integer> mines = new TableColumn<HighscoreEntry, Integer>("Mines");
		
		tableEasy.getColumns().addAll(number, name, startTime, duration, moves);
		tableIntermediate.getColumns().addAll(number, name, startTime, duration, moves);
		tableHard.getColumns().addAll(number, name, startTime, duration, moves);
		tableCustom.getColumns().addAll(number, name, startTime, duration, moves, fieldWidth, fieldHeight, mines);
		
		easy.setContent(tableEasy);
		intermediate.setContent(tableIntermediate);
		hard.setContent(tableHard);
		custom.setContent(tableCustom);
		
		borderPane.setCenter(tabPane);
		
		fillWithData();
		
		this.setScene(s);
		this.show();
	}
	
	private void fillWithData(){
				
	}
}
