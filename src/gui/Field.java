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
}