package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import meta.DataManager;

public class Field extends GridPane {
	public static final int COLOR_ORANGE = 0;
	public static final int COLOR_RED = 1;
	public static final int COLOR_GREEN = 2;
	
	private Button button = new Button();
	private boolean mine = false;
	private boolean flagged = false;
	private boolean hidden = true;
	private int neighbourMines = 0;
	
	private Field tl = null; //Top Left
	private Field tc = null; //Top Center
	private Field tr = null; //Top Right
	private Field ml = null; //Middle Left
	private Field mr = null; //Middle Right
	private Field bl = null; //Bottom Left
	private Field bc = null; //Bottom Center
	private Field br = null; //Bottom Right
	private ImageView bomb;
	private ImageView flag;
	private Text text;
	
	public Field(){
		super();
		this.setHgap(10d);
		this.setVgap(10d);
		this.setAlignment(Pos.CENTER);
		this.add(button, 0, 0);
		GridPane.setVgrow(button, Priority.ALWAYS);
		GridPane.setHgrow(button, Priority.ALWAYS);
		button.setPrefSize(DataManager.getData().getWidth()/DataManager.getData().getXFields(), DataManager.getData().getHeight()/DataManager.getData().getYFields());
		button.setOnMouseClicked((event) -> {
			if(event.getButton().equals(MouseButton.SECONDARY)){
				if (flagged) unflag();
				else flag(COLOR_ORANGE, true);
			} else {
				clicked();
			}
		});
	}
	
	public void setMine(){
		mine = true;
	}
	
	public void clicked(){
		if (hidden){
			if (!DataManager.getData().gameOver()) DataManager.getData().addMove();
			open();
		}
	}
	
	public void open(){
		if (hidden){
			hidden = false;
			this.disableProperty().set(true);
			if (mine){
				if (DataManager.getData().firstClick()){
					DataManager.getData().firstClicked();
					DataManager.getData().mainWindow().startTimer();
					//DataManager.getData().resetFirstClick(); //TODO: Reset first click if it was mine?
					System.out.println("First Click was a Mine!");
					mine = false;
					((GamePane)(this.getParent())).setMines(1);
					hidden = true;
					open();
				} else {
					bomb = new ImageView(new Image(getClass().getResourceAsStream("bomb.png")));
					bomb.preserveRatioProperty().set(true);
					bomb.setFitWidth(this.getWidth()-0.25*this.getWidth());
					GridPane.setHalignment(bomb, HPos.CENTER);
					GridPane.setValignment(bomb, VPos.CENTER);
					this.add(bomb, 0, 0);
					GridPane.setVgrow(bomb, Priority.ALWAYS);
					GridPane.setHgrow(bomb, Priority.ALWAYS);
					if (!DataManager.getData().isLost()) DataManager.getData().mainWindow().getGamePane().lost();
					if (flagged) flag(COLOR_GREEN, false);
				}
			} else {
				if (DataManager.getData().firstClick()){
					DataManager.getData().firstClicked();
					DataManager.getData().mainWindow().startTimer();
				}
				DataManager.getData().setHiddenFields(DataManager.getData().getHiddenFields() -  1);
				if (DataManager.getData().getHiddenFields()-DataManager.getData().getMines() == 0 && !DataManager.getData().isLost()) DataManager.getData().mainWindow().getGamePane().won();
				countNeighbourMines();
				if (flagged && DataManager.getData().isLost()) flag(COLOR_RED, false);
				else unflag();
				if (neighbourMines == 0){
					if (tl != null && tl.isHidden()) tl.open();
					if (tc != null && tc.isHidden()) tc.open();
					if (tr != null && tr.isHidden()) tr.open();
					if (ml != null && ml.isHidden()) ml.open();
					if (mr != null && mr.isHidden()) mr.open();
					if (bl != null && bl.isHidden()) bl.open();
					if (bc != null && bc.isHidden()) bc.open();
					if (br != null && br.isHidden()) br.open();
				} else {
					displayMineNum();
				}
			}	
		}
	}
	
	public void flag(int color, boolean fullSize){
		if (!flagged){
			switch(color){
			case COLOR_ORANGE: flag = new ImageView(new Image(getClass().getResourceAsStream("flag-orange.png"))); break;
			case COLOR_RED: flag = new ImageView(new Image(getClass().getResourceAsStream("flag-red.png"))); break;
			case COLOR_GREEN: flag = new ImageView(new Image(getClass().getResourceAsStream("flag-green.png"))); break;
			default: flag = new ImageView(new Image(getClass().getResourceAsStream("flag-blue.png"))); break;
			}
			flag.preserveRatioProperty().set(true);
			flag.minHeight(0);
			flag.minWidth(0);
			if (fullSize){
				flag.setFitHeight(0.75*this.getHeight());
				this.add(flag, 0, 0);
				GridPane.setVgrow(flag, Priority.ALWAYS);
				GridPane.setHgrow(flag, Priority.ALWAYS);
				GridPane.setHalignment(flag, HPos.CENTER);
				GridPane.setValignment(flag, VPos.CENTER);
			} else {
				flag.setFitHeight(0.5*this.getHeight());
				this.add(flag, 0, 0);
				GridPane.setVgrow(flag, Priority.NEVER);
				GridPane.setHgrow(flag, Priority.NEVER);
				GridPane.setHalignment(flag, HPos.RIGHT);
				GridPane.setValignment(flag, VPos.BOTTOM);
			}
			flagged = true;
			DataManager.getData().increaseFlagsSet();
		} else {
			unflag();
			flag(color, fullSize);
		}
	}
	
	public void unflag(){
		if (flagged){
			this.getChildren().remove(flag);
			flagged = false;
			DataManager.getData().decreaseFlagsSet();
		}
	}
	
	public void countNeighbourMines(){
		if (tl != null && tl.isMine()) neighbourMines ++;
		if (tc != null && tc.isMine()) neighbourMines ++;
		if (tr != null && tr.isMine()) neighbourMines ++;
		if (ml != null && ml.isMine()) neighbourMines ++;
		if (mr != null && mr.isMine()) neighbourMines ++;
		if (bl != null && bl.isMine()) neighbourMines ++;
		if (bc != null && bc.isMine()) neighbourMines ++;
		if (br != null && br.isMine()) neighbourMines ++;
	}
	
	public void displayMineNum(){
		text = new Text(String.valueOf(neighbourMines));
		text.setFont(Font.font("Courier New", this.getWidth()*0.5));
		switch(neighbourMines){
		case 1: text.setFill(Color.YELLOWGREEN); break;
		case 2: text.setFill(Color.DODGERBLUE); break;
		case 3: text.setFill(Color.YELLOW); break;
		case 4: text.setFill(Color.ORANGE); break;
		case 5: text.setFill(Color.RED); break;
		case 6: text.setFill(Color.DARKRED); break;
		case 7: text.setFill(Color.MEDIUMVIOLETRED); break;
		case 8: text.setFill(Color.BLUEVIOLET); break;
		}
		this.add(text, 0, 0);
		GridPane.setHalignment(text, HPos.CENTER);
		GridPane.setValignment(text, VPos.CENTER);
	}
	
	//--------------------------------------------------------------------------\\
	//------------------ Getter and Setter Methods start here ------------------\\
	//--------------------------------------------------------------------------\\
	
	public boolean isMine(){
		return mine;
	}
	
	public boolean isHidden(){
		return hidden;
	}

	public Field getTl() {
		return tl;
	}

	public void setTl(Field tl) {
		this.tl = tl;
	}

	public Field getTc() {
		return tc;
	}

	public void setTc(Field tc) {
		this.tc = tc;
	}

	public Field getTr() {
		return tr;
	}

	public void setTr(Field tr) {
		this.tr = tr;
	}

	public Field getMl() {
		return ml;
	}

	public void setMl(Field ml) {
		this.ml = ml;
	}

	public Field getMr() {
		return mr;
	}

	public void setMr(Field mr) {
		this.mr = mr;
	}

	public Field getBl() {
		return bl;
	}

	public void setBl(Field bl) {
		this.bl = bl;
	}

	public Field getBc() {
		return bc;
	}

	public void setBc(Field bc) {
		this.bc = bc;
	}

	public Field getBr() {
		return br;
	}

	public void setBr(Field br) {
		this.br = br;
	}
}