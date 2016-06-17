package gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import meta.Data;

public class GamePane extends GridPane {
	private Field[][] fields;
	private int xFields;
	private int yFields;
	private int mines;
	
	public GamePane(int xFields, int yFields, int mines){
		this.xFields = xFields;
		this.yFields = yFields;
		this.mines = mines;
		newGame();
	}
	
	public void newGame(int xFields, int yFields, int mines){
		this.xFields = xFields;
		this.yFields = yFields; 
		this.mines = mines;
		newGame();
	}

	public void newGame() {
		super.getChildren().clear();
		fields = new Field[xFields][yFields];
		for (int i = 0; i < fields.length; i++){
			for (int j = 0; j < fields[i].length; j++){
				fields[i][j] = new Field();
				fields[i][j].setPrefSize((double)Data.getWidth()/(double)xFields, (double)Data.getHeight()/(double)yFields);
				super.add(fields[i][j], i, j);
				super.setVgrow(fields[i][j], Priority.SOMETIMES);
				super.setHgrow(fields[i][j], Priority.SOMETIMES);
			}
		}
		linkFields();
		setMines();
	}
	
	private void linkFields(){
		for (int i = 0; i < fields.length; i++){
			for (int j = 0; j < fields[i].length; j++){
				if ((i - 1) >= 0){
					fields[i][j].setMl(fields[i-1][j]);
					if ((j - 1) >= 0){
						fields[i][j].setTl(fields[i-1][j-1]);
					}
					if ((j + 1) < fields[i].length){
						fields[i][j].setBl(fields[i-1][j+1]);
					}
				}
				if ((i + 1) < fields.length){
					fields[i][j].setMr(fields[i+1][j]);
					if ((j - 1) >= 0){
						fields[i][j].setTr(fields[i+1][j-1]);
					}
					if ((j + 1) < fields[i].length){
						fields[i][j].setBr(fields[i+1][j+1]);
					}
				}
				if ((j - 1) >= 0){
					fields[i][j].setTc(fields[i][j-1]);
				}
				if ((j + 1) < fields[i].length){
					fields[i][j].setBc(fields[i][j+1]);
				}
			}
		}
	}
	
	public void setMines(){
		setMines(mines);
	}
	
	public void setMines(int mines){
		int x, y;
		if (mines > xFields * yFields){
			mines = (int)(xFields * yFields * 0.5);
		}
		for (int i = 0; i < mines; i++){
			do {
				x = (int)(Math.random()*xFields);
				y = (int)(Math.random()*yFields);
			} while (fields[x][y].isMine() || !fields[x][y].isHidden());
			fields[x][y].setMine();
		}
	}
	
	public void lost(){
		for (int i = 0; i < fields.length; i++){
			for (int j = 0; j < fields[i].length; j++){
				if (fields[i][j] != null && fields[i][j].isHidden()) fields[i][j].open();
			}
		}
		Data.mainWindow().lost();
	}
	
	public void won(){
		for (int i = 0; i < fields.length; i++){
			for (int j = 0; j < fields[i].length; j++){
				if (fields[i][j] != null && fields[i][j].isHidden()) fields[i][j].open();
			}
		}
		Data.mainWindow().won();
	}
}
