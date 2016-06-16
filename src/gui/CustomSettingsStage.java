package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import meta.Data;

public class CustomSettingsStage extends Stage {
	public CustomSettingsStage() throws Exception{
		super.setTitle("Custom Settings");
		AnchorPane aP = new AnchorPane();
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25, 25, 25, 25));
		
		Scene editMenu = new Scene(aP, 300, 200);
		super.setScene(editMenu);
		
		Label xTilesLabel = new Label("Width:");
		Label yTilesLabel = new Label("Height:");
		Label minesLabel = new Label("Mines:");
		
		Spinner<Integer> xTilesSpinner = new Spinner<Integer>(10, 100, Data.xFields);
		Spinner<Integer> yTilesSpinner = new Spinner<Integer>(10, 100, Data.yFields);
		Spinner<Integer> minesSpinner = new Spinner<Integer>(1, 100, Data.mines);
		
		xTilesSpinner.setEditable(true);
		yTilesSpinner.setEditable(true);
		minesSpinner.setEditable(true);
		
		xTilesSpinner.valueProperty().addListener((observable, old, current) -> {
			if (old != current) Data.xFields = current;
		});
		
		yTilesSpinner.valueProperty().addListener((observable, old, current) -> {
			if (old != current) Data.yFields = current;
		});
		
		minesSpinner.valueProperty().addListener((observable, old, current) -> {
			if (old != current) Data.mines = current;
		});
		
		root.add(xTilesLabel, 0, 0);
		root.add(yTilesLabel, 0, 1);
		root.add(minesLabel, 0, 2);
		root.add(xTilesSpinner, 1, 0);
		root.add(yTilesSpinner, 1, 1);
		root.add(minesSpinner, 1, 2);
		
		Button done = new Button("Done");
		
		done.setOnAction((event) -> {
			this.hide();
		});
		
		aP.getChildren().add(root);
		aP.getChildren().add(done);
		
		aP.setBottomAnchor(done, 10d);
		aP.setRightAnchor(done, 10d);
		
		aP.setTopAnchor(root, 0d);
		aP.setBottomAnchor(root, 40d);
		aP.setLeftAnchor(root, 0d);
		aP.setRightAnchor(root, 0d);
	}
}
