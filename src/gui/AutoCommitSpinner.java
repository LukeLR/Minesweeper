package gui;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

/**
 * This class is a workaround for a known bug in the JavaFX Spinner class. When
 * the Spinner field is editable, and the user uses his keyboard to enter values,
 * they only get applied to the ValueFactory if the Spinner buttons are used
 * afterwards. The expected behaviour is that after entering a value in the Spinner,
 * it is immediately applied to its ValueFactory.
 * 
 * In my Project, Spinners are used to let the user chose his own custom game settings,
 * but, due to this bug, after entering custom mine numbers (for example) without
 * using the Spinner buttons, the newly entered custom mine number is ignored, and
 * the previous value is used (since the typed value was not appliedt to the
 * value factory). The bug is filed under
 * https://javafx-jira.kenai.com/browse/RT-39655?page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel
 * 
 * This workaround adds a listener to the editor property of each Spinner by default,
 * so when the editor text is changed, it is manuall applied to the ValueFactory.
 * 
 * This solution was inspired by Jonathan Fortin on StackOverflow
 * (http://stackoverflow.com/users/3179017/jonathan-fortin) in question #27433899:
 * "Spinner Control Value" (http://stackoverflow.com/questions/27433899/spinner-control-value)
 * 
 * His code is licensed under the Creative Commons Attribution, Share-Alike, and
 * is therefore eligible to be used in this project. Thanks to Jonathan for sharing
 * his solution!
 * 
 * @author Lukas
 * @author Jonathan Forin
 *
 * @param <T> The type of value this AutoCommitSpinner should save
 */

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
