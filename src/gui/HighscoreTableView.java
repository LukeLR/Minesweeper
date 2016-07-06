/**
 * 
 */
package gui;

import javafx.scene.control.TableView;
import meta.HighscoreList;

public class HighscoreTableView<S> extends TableView<S> {
	private HighscoreList list;
	private boolean isCustomHighscoreTable = false;
	public HighscoreTableView(){
		super();
	}
	
	public HighscoreTableView(HighscoreList list){
		super();
		this.list = list;
	}
	
	public void fillWithData(){
		if (list == null){}//TODO: Fehlermeldung!
		else {
			
		}
	}
	
	public void fillWithData(HighscoreList list){
		this.list = list;
		fillWithData();
	}
}