package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class Field extends Button {
	private boolean isMine = false;
	private boolean flagged = false;
	
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
		if (isMine){
			ImageView bomb = new ImageView(new Image(getClass().getResourceAsStream("/bomb.png")));
			bomb.preserveRatioProperty().set(true);
			bomb.setFitWidth(this.getWidth()-0.25*this.getWidth());
			this.setGraphic(bomb);
			this.disableProperty().set(true);
		}
	}
	
	public void flag(){
		ImageView flag = new ImageView(new Image(getClass().getResourceAsStream("/flag.png")));
		flag.preserveRatioProperty().set(true);
		flag.setFitHeight(this.getHeight()-0.25*this.getHeight());
		this.setGraphic(flag);
		flagged = true;
	}
	
	public void unflag(){
		this.setGraphic(null);
		flagged = false;
	}
}