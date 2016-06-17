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

public class HighscoreStage extends Stage {
	
	private BorderPane borderPane;
	private Scene s;
	private TabPane tabPane;
	private Tab easy, intermediate, hard, custom;
	private TableView tableEasy, tableIntermediate, tableHard, tableCustom;

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
		
		tableEasy = new TableView();
		tableIntermediate = new TableView();
		tableHard = new TableView();
		tableCustom = new TableView();
		
		TableColumn number = new TableColumn("#");
		TableColumn name = new TableColumn("Name");
		TableColumn startTime = new TableColumn("Start Time");
		TableColumn duration = new TableColumn("Duration");
		TableColumn moves = new TableColumn("Moves");
		
		TableColumn fieldWidth = new TableColumn("Width");
		TableColumn fieldHeight = new TableColumn("Height");
		TableColumn mines = new TableColumn("Mines");
		
		tableEasy.getColumns().addAll(number, name, startTime, duration, moves);
		tableIntermediate.getColumns().addAll(number, name, startTime, duration, moves);
		tableHard.getColumns().addAll(number, name, startTime, duration, moves);
		tableCustom.getColumns().addAll(number, name, startTime, duration, moves, fieldWidth, fieldHeight, mines);
		
		easy.setContent(tableEasy);
		intermediate.setContent(tableIntermediate);
		hard.setContent(tableHard);
		custom.setContent(tableCustom);
		
		borderPane.setCenter(tabPane);
		
		this.setScene(s);
		this.show();
	}
}
