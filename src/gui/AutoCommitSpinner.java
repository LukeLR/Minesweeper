package gui;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

public class AutoCommitSpinner<T> extends Spinner<T> {
	public AutoCommitSpinner(){
		super();
		addTextListener();
	}
	
	public AutoCommitSpinner (int minValue, int maxValue, int defaultValue){
		super(minValue, maxValue, defaultValue);
		addTextListener();
	}
	
	public AutoCommitSpinner (int minValue, int maxValue, int defaultValue, int step){
		super(minValue, maxValue, defaultValue, step);
		addTextListener();
	}
	
	public AutoCommitSpinner (double minValue, double maxValue, double defaultValue){
		super(minValue, maxValue, defaultValue);
		addTextListener();
	}
	
	public AutoCommitSpinner (double minValue, double maxValue, double defaultValue, double step){
		super(minValue, maxValue, defaultValue, step);
		addTextListener();
	}
	
	private void addTextListener(){
		this.getEditor().textProperty().addListener((event) -> {
			if (this.isEditable()){
				try {
					SpinnerValueFactory<T> spinnerValueFactory = this.getValueFactory();
					if (spinnerValueFactory != null){
						StringConverter<T> stringConverter = spinnerValueFactory.getConverter();
						if (stringConverter != null){
							spinnerValueFactory.setValue(stringConverter.fromString(this.getEditor().getText()));
						}
					}
				} catch (Exception ex){
					ex.printStackTrace();
					//TODO: Error handling?
				}
			}
		});
	}
}
