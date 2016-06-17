package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import meta.Data;

public class Field extends GridPane {
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
		this.setVgrow(button, Priority.ALWAYS);
		this.setHgrow(button, Priority.ALWAYS);
		button.setPrefSize(Data.getWidth()/Data.getXFields(), Data.getHeight()/Data.getYFields());
		button.setOnMouseClicked((event) -> {
			if(event.getButton().equals(MouseButton.SECONDARY)){
				if (flagged) unflag();
				else flag();
			} else {
				open();
			}
		});
	}
	
	public void setMine(){
		mine = true;
	}
	
	public void open(){
		hidden = false;
		this.disableProperty().set(true);
		if (mine){
			if (Data.firstClick){
				Data.mainWindow().startTimer();
				Data.firstClick = false;
				System.out.println("First Click was a Mine!");
				mine = false;
				((GamePane)(this.getParent())).setMines(1);
				hidden = true;
				open();
			} else {
				bomb = new ImageView(new Image(getClass().getResourceAsStream("/bomb.png")));
				bomb.preserveRatioProperty().set(true);
				bomb.setFitWidth(this.getWidth()-0.25*this.getWidth());
				this.setHalignment(bomb, HPos.CENTER);
				this.setValignment(bomb, VPos.CENTER);
				this.add(bomb, 0, 0);
				this.setVgrow(bomb, Priority.ALWAYS);
				this.setHgrow(bomb, Priority.ALWAYS);
				Data.mainWindow().getGamePane().lost();
			}
		} else {
			if (Data.firstClick){
				Data.mainWindow().startTimer();
				Data.firstClick = false;
			}
			Data.setHiddenFields(Data.getHiddenFields() -  1);
			if (Data.getHiddenFields()-Data.getMines() == 0) Data.mainWindow().getGamePane().won();
			countNeighbourMines();
			if (flagged) unflag();
			if (neighbourMines == 0){
//				if (tl != null && tl.isHidden()) tl.open();
				if (tc != null && tc.isHidden()) tc.open();
//				if (tr != null && tr.isHidden()) tr.open();
				if (ml != null && ml.isHidden()) ml.open();
				if (mr != null && mr.isHidden()) mr.open();
//				if (bl != null && bl.isHidden()) bl.open();
				if (bc != null && bc.isHidden()) bc.open();
//				if (br != null && br.isHidden()) br.open();
			} else {
				displayMineNum();
			}
		}
	}
	
	public void flag(){
		if (!flagged){
			flag = new ImageView(new Image(getClass().getResourceAsStream("/flag.png")));
			flag.preserveRatioProperty().set(true);
			flag.setFitHeight(this.getHeight()-0.25*this.getHeight());
			flag.minHeight(0);
			flag.minWidth(0);
			this.add(flag, 0, 0);
			this.setVgrow(flag, Priority.ALWAYS);
			this.setHgrow(flag, Priority.ALWAYS);
			this.setHalignment(flag, HPos.CENTER);
			this.setValignment(flag, VPos.CENTER);
			flagged = true;
			Data.setFlagsSet(Data.getFlagsSet() + 1);
		}
	}
	
	public void unflag(){
		if (flagged){
			this.getChildren().remove(flag);
			flagged = false;
			Data.setFlagsSet(Data.getFlagsSet() - 1);
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
		this.setHalignment(text, HPos.CENTER);
		this.setValignment(text, VPos.CENTER);
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