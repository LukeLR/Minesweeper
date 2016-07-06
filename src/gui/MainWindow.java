package gui;

import java.time.Duration;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import meta.Data;
import meta.DataManager;
import meta.HighscoreEntry;
import meta.HighscoreList;

public class MainWindow extends Application {
	BorderPane root;
	GridPane menues, mainMenu, difficultyMenu, stats, lostPane, wonPane;
	Scene mainScene, lost, won;
	GamePane gp;
	private Label difficultyLabel;
	private Label nameLabel;
	private ComboBox difficulty;
	private TextField name;
	private Button newGame, highscores;
	private CheckBox proportional;
	private Label yTilesLabel;
	private Label xTilesLabel;
	private Label minesLabel;
	private Spinner<Integer> xTilesSpinner;
	private Spinner<Integer> yTilesSpinner;
	private Spinner<Integer> minesSpinner;
	private Text mineNum;
	private TimerText timerText;
	private Stage primaryStage;
	private FacePane fp;

	public static void main(String args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		DataManager.setMainWindow(this);
		primaryStage.setTitle("Minesweeper w/ JavaFX");
		// AnchorPane aP = new AnchorPane();
		root = new BorderPane();
		menues = new GridPane();
		mainMenu = new GridPane();
		difficultyMenu = new GridPane();
		stats = new GridPane();
		mainScene = new Scene(root, DataManager.getWidth(), DataManager.getHeight());

		menues.setAlignment(Pos.CENTER);
		menues.setHgap(10d);
		menues.setVgap(10d);
		mainMenu.setAlignment(Pos.CENTER);
		mainMenu.setHgap(10d);
		mainMenu.setVgap(10d);
		difficultyMenu.setAlignment(Pos.CENTER);
		difficultyMenu.setHgap(10d);
		difficultyMenu.setVgap(10d);
		stats.setAlignment(Pos.CENTER);
		stats.setHgap(10d);
		stats.setVgap(10d);
		root.setPadding(new Insets(25, 25, 25, 25));
		
//		lostPane = new GridPane();
//		lostPane.setAlignment(Pos.CENTER);
//		lostPane.setHgap(10d);
//		lostPane.setVgap(10d);
//		wonPane = new GridPane();
//		wonPane.setAlignment(Pos.CENTER);
//		wonPane.setHgap(10d);
//		wonPane.setVgap(10d);
//		lost = new Scene(lostPane, DataManager.getWidth(), DataManager.getHeight());
//		won = new Scene(wonPane, DataManager.getWidth(), DataManager.getHeight());
//		Canvas lostCanvas = new Canvas(DataManager.getWidth(), DataManager.getHeight());
//		GraphicsContext gc = lostCanvas.getGraphicsContext2D();
//		gc.setFill(Color.RED);
//		gc.fillRect(0, 0, DataManager.getWidth(), DataManager.getHeight());
//		Text gameOver = new Text("Game Over");
//		gameOver.setFont(Font.font("Verdana", 100));
//		lostPane.add(lostCanvas, 0, 0);
//		lostPane.add(gameOver, 0, 0);

		mineNum = new Text();
		updateMineNum();
		mineNum.setFont(Font.font("Courier New", 50));
		mineNum.setFill(Color.RED);
		stats.add(mineNum, 0, 0);
		
		timerText = new TimerText();
		timerText.setFont(Font.font("Courier New", 50));
		timerText.setFill(Color.YELLOWGREEN);
		stats.add(timerText, 1, 0);
		
		difficultyLabel = new Label("Difficulty:");
		nameLabel = new Label("Your name:");

		difficulty = new ComboBox();
		difficulty.getItems().addAll("Easy", "Intermediate", "Hard", "Custom");
		difficulty.setValue("Easy");

		name = new TextField("Max Mustermann");

		newGame = new Button("New Game");
		highscores = new Button("Highscores");

		proportional = new CheckBox("Proportional");

		xTilesLabel = new Label("Width:");
		yTilesLabel = new Label("Height:");
		minesLabel = new Label("Mines:");

		xTilesSpinner = new Spinner<Integer>(1, 100, DataManager.getXFields());
		yTilesSpinner = new Spinner<Integer>(1, 100, DataManager.getYFields());
		minesSpinner = new Spinner<Integer>(1, 100, DataManager.getMines());

		xTilesSpinner.setEditable(true);
		yTilesSpinner.setEditable(true);
		minesSpinner.setEditable(true);

		proportional.selectedProperty().addListener((ov, old, current) -> {
			if (current) {
				yTilesSpinner.getValueFactory().setValue(DataManager.getXFields());
			}
		});

		xTilesSpinner.valueProperty().addListener((ov, old, current) -> {
			DataManager.setXFields(xTilesSpinner.getValueFactory().getValue());
			if (proportional.isSelected()) {
				yTilesSpinner.getValueFactory().setValue(DataManager.getXFields());
			}
		});

		yTilesSpinner.valueProperty().addListener((ov, old, current) -> {
			DataManager.setYFields(yTilesSpinner.getValueFactory().getValue());
			if (proportional.isSelected()) {
				xTilesSpinner.getValueFactory().setValue(DataManager.getYFields());
			}
		});

		minesSpinner.valueProperty().addListener((ov, old, current) -> {
			DataManager.setMines(minesSpinner.getValueFactory().getValue());
		});

		difficultyMenu.add(proportional, 0, 0);
		difficultyMenu.add(xTilesLabel, 1, 0);
		difficultyMenu.add(xTilesSpinner, 2, 0);
		difficultyMenu.add(yTilesLabel, 3, 0);
		difficultyMenu.add(yTilesSpinner, 4, 0);
		difficultyMenu.add(minesLabel, 5, 0);
		difficultyMenu.add(minesSpinner, 6, 0);

		difficultyMenu.setVisible(false);
		
		difficulty.valueProperty().addListener((ov, old, current) -> {
			switch(current.toString()){
			case "Easy":
				DataManager.setMode(Data.EASY);
				difficultyMenu.setVisible(false);
				newGame();
				break;
			case "Intermediate":
				DataManager.setMode(Data.INTERMEDIATE);
				difficultyMenu.setVisible(false);
				newGame();
				break;
			case "Hard":
				DataManager.setMode(Data.HARD);
				difficultyMenu.setVisible(false);
				newGame();
				break;
			case "Custom":
				DataManager.setMode(Data.CUSTOM);
				difficultyMenu.setVisible(true); 
				break;
			default: break;
			}
		});

		mainMenu.add(difficultyLabel, 0, 0);
		mainMenu.add(difficulty, 1, 0);
		mainMenu.add(nameLabel, 2, 0);
		mainMenu.add(name, 3, 0);
		mainMenu.add(newGame, 4, 0);
		mainMenu.add(highscores, 5, 0);

		menues.add(mainMenu, 0, 0, 1, 1);
		menues.add(difficultyMenu, 0, 1, 1, 1);
		HBox topBox = new HBox();
		fp = new FacePane();
		fp.setFace(FacePane.SLEEPY_FACE);
		topBox.getChildren().add(fp);
		topBox.getChildren().add(menues);
		root.setTop(topBox);
		root.setBottom(stats);

		newGame.setOnAction((event) -> {
			newGame();
		});
		
		highscores.setOnAction((event) -> {
			HighscoreList scores = DataManager.getHighscoresEasy();
//			scores.add("asdf", System.currentTimeMillis(), 84217, 34);
//			scores.add("foo", System.currentTimeMillis(), 8751324, 27);
//			scores.add("bar", System.currentTimeMillis(), 87425, 345);
			new HighscoreStage();
		});

		newGame();

		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public void newGame() {
		timerText.stop();
		timerText.reset();
		timerText.update();
		fp.setFace(FacePane.SLEEPY_FACE);
		if (gp == null) {
//			ScrollPane sp = new ScrollPane();
			gp = new GamePane(DataManager.getXFields(), DataManager.getYFields(), DataManager.getMines());
//			gp.setMaxWidth(DataManager.getWidth());
//			gp.setMaxHeight(DataManager.getHeight()-menues.getHeight()-stats.getHeight());
//			sp.setFitToWidth(true);
//			sp.setFitToHeight(true);
//			sp.setContent(gp);
//			sp.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
//			sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
			root.setCenter(gp);
		} else {
			gp.newGame(DataManager.getXFields(), DataManager.getYFields(), DataManager.getMines());
		}
		DataManager.resetFlagsSet();
		updateMineNum();
		DataManager.resetFirstClick();
		DataManager.resetHiddenFields();
	}
	
	public GamePane getGamePane(){
		return gp;
	}
	
	public void updateMineNum(){
		mineNum.setText("Mines: " + (DataManager.getMines() - DataManager.getFlagsSet()));
	}
	
	public void startTimer(){
		System.out.println("Start!");
		timerText.start();
		this.fp.setFace(FacePane.HAPPY_FACE);
	}
	
	public void lost(){
		timerText.stop();
		this.fp.setFace(FacePane.SAD_FACE);
	}
	
	public void won(){
		timerText.stop();
		this.fp.setFace(FacePane.LOVING_FACE);
		DataManager.getHighscoresEasy().add(new HighscoreEntry(1, name.getText(), LocalDateTime.now(), Duration.ofMinutes(234), 1234, 1234, 1234, 1234));
	}
}
