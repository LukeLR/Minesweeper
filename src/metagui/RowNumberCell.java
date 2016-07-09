package metagui;

import javafx.scene.control.TableCell;

public class RowNumberCell<S, T> extends TableCell<S, T> {
	@Override
	protected void updateItem(Object content, boolean empty){
		this.setText(String.valueOf(this.getIndex()));
	}
}
