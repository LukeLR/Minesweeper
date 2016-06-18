package gui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import meta.HighscoreEntry;
 
public class TableViewSample extends Application {
	
	private TableView<HighscoreEntry> table = new TableView<HighscoreEntry>();
    
	private final ObservableList<HighscoreEntry> data =
			FXCollections.observableArrayList(
				new HighscoreEntry("asdf", System.currentTimeMillis(), 12512354, 123),
				new HighscoreEntry("foo", System.currentTimeMillis(), 752174, 1234),
				new HighscoreEntry("bar", System.currentTimeMillis(), 132478, 2134)
			);
   
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(500);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn number = new TableColumn("#");
		TableColumn name = new TableColumn("Name");
		TableColumn startTime = new TableColumn("Start Time");
		TableColumn duration = new TableColumn("Duration");
		TableColumn moves = new TableColumn("Moves");
		
		TableColumn fieldWidth = new TableColumn("Width");
		TableColumn fieldHeight = new TableColumn("Height");
		TableColumn mines = new TableColumn("Mines");
		
		name.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("name"));
		startTime.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("startTimeString"));
		duration.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, String>("durationString"));
		moves.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("moves"));
		
		fieldWidth.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("xTiles"));
		fieldHeight.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("yTiles"));
		mines.setCellValueFactory(new PropertyValueFactory<HighscoreEntry, Integer>("mines"));
 
        table.setItems(data);
        table.getColumns().addAll(name, startTime, duration, moves);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class Person {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
 
        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
    }
} 
