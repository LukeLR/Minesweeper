package gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import meta.Data;

public class GamePane extends GridPane {
	Field[][] fields;
	
	public GamePane(int xFields, int yFields, int mines){
		newGame(xFields, yFields, mines);
	}

	public void newGame(int xFields, int yFields, int mines) {
		super.getChildren().clear();
		fields = new Field[xFields][yFields];
		for (int i = 0; i < fields.length; i++){
			for (int j = 0; j < fields[i].length; j++){
				fields[i][j] = new Field();
				fields[i][j].setPrefSize(Data.width/xFields, Data.height/yFields);
				super.add(fields[i][j], i, j);
				super.setVgrow(fields[i][j], Priority.SOMETIMES);
				super.setHgrow(fields[i][j], Priority.SOMETIMES);
			}
		}
	}
}
