package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import meta.Data;

public class Field extends Button {
	private boolean isMine = false;
	private boolean flagged = false;
	private boolean hidden = true;
	
	private Field tl = null; //Top Left
	private Field tc = null; //Top Center
	private Field tr = null; //Top Right
	private Field ml = null; //Middle Left
	private Field mr = null; //Middle Right
	private Field bl = null; //Bottom Left
	private Field bc = null; //Bottom Center
	private Field br = null; //Bottom Right
	
	public Field(){
		super();
		this.setOnMouseClicked((event) -> {
			if(event.getButton().equals(MouseButton.SECONDARY)){
				if (flagged) unflag();
				else flag();
			} else {
				open();
			}
		});
	}
	
	public void setMine(){
		isMine = true;
	}
	
	public void open(){
		hidden = false;
		if (isMine){
			if (Data.firstClick){
				Data.firstClick = false;
				System.out.println("First Click was a Mine!");
				isMine = false;
				((GamePane)(this.getParent())).setMines(1);
			} else {
				ImageView bomb = new ImageView(new Image(getClass().getResourceAsStream("/bomb.png")));
				bomb.preserveRatioProperty().set(true);
				bomb.setFitWidth(this.getWidth()-0.25*this.getWidth());
				this.setGraphic(bomb);
				this.disableProperty().set(true);
			}
		}
	}
	
	public void flag(){
		ImageView flag = new ImageView(new Image(getClass().getResourceAsStream("/flag.png")));
		flag.preserveRatioProperty().set(true);
		flag.setFitHeight(this.getHeight()-0.25*this.getHeight());
		flag.minHeight(0);
		flag.minWidth(0);
		this.setGraphic(flag);
		flagged = true;
		Data.setFlagsSet(Data.getFlagsSet() + 1);
	}
	
	public void unflag(){
		this.setGraphic(null);
		flagged = false;
		Data.setFlagsSet(Data.getFlagsSet() - 1);
	}
	
	public boolean isMine(){
		return isMine;
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