package gui;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import meta.Data;
import meta.DataManager;
import meta.HighscoreEntry;
import meta.HighscoreList;

public class HighscoreStage extends Stage {
	
	private HighscoreTableView easyTable;
	private HighscoreTableView intermediateTable;
	private HighscoreTableView hardTable;
	private HighscoreTableView customTable;
	private Tab easyTab;
	private Tab intermediateTab;
	private Tab hardTab;
	private Tab customTab;

	public HighscoreStage(){
		super.setTitle("Highscore");
		super.setScene(createScene());
		super.showAndWait();
	}
	
	private Scene createScene(){
		Scene scene = new Scene(createRoot(), 500, 500);
		return scene;
	}
	
	private BorderPane createRoot(){
		BorderPane borderPane = new BorderPane();
//		borderPane.setPadding(new Insets(10, 10, 10, 10));
		
		borderPane.setCenter(createCenter());
		borderPane.setBottom(createBottom());
		
		return borderPane;
	}
	
	private GridPane createCenter(){
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10d);
		gridPane.setHgap(10d);
		
		Text title = new Text("Highscore tables:");
		title.setFont(Font.font("Verdana", 26));
		gridPane.add(title, 0, 0);
		gridPane.setMargin(title, new Insets(10, 10, 10, 10));
		
		TabPane tabPane = createTabPane();
		gridPane.add(tabPane, 0, 1);
		gridPane.setHgrow(tabPane, Priority.ALWAYS);
		gridPane.setVgrow(tabPane, Priority.ALWAYS);
		
		return gridPane;
	}
	
	private TabPane createTabPane(){
		TabPane tabPane = new TabPane();
		
		easyTab = new Tab("Easy");
		intermediateTab = new Tab("Intermediate");
		hardTab = new Tab("Hard");
		customTab = new Tab("Custom");
		
		easyTab.setClosable(false);
		intermediateTab.setClosable(false);
		hardTab.setClosable(false);
		customTab.setClosable(false);
		
		tabPane.getTabs().addAll(easyTab, intermediateTab, hardTab, customTab);
		
		easyTable = new HighscoreTableView(false);
		intermediateTable = new HighscoreTableView(false);
		hardTable = new HighscoreTableView(false);
		customTable = new HighscoreTableView(true);
		
		easyTab.setContent(easyTable);
		intermediateTab.setContent(intermediateTable);
		hardTab.setContent(hardTable);
		customTab.setContent(customTable);
		
		loadData();
		return tabPane;
	}
	
	private void loadData(){
		easyTable.fillWithData(DataManager.getHighscoresEasy());
		intermediateTable.fillWithData(DataManager.getHighscoresIntermediate());
		hardTable.fillWithData(DataManager.getHighscoresHard());
		customTable.fillWithData(DataManager.getHighscoresCustom());
	}
	
	private HBox createBottom(){
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER_RIGHT);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		
		Button ok = new Button("OK");
		hBox.getChildren().add(ok);
		ok.setOnAction((event) -> {
			close();
		});
		
		Button reset = new Button("Reset Highscores");
		hBox.getChildren().addAll(reset);
		reset.setOnAction((event) -> {
			DataManager.resetHighscores();
			loadData();
		});
		
		return hBox;
	}
	
}
