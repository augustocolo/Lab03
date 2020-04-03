package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.LanguageChoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class FXMLController {
	
	private Dictionary dictionary = new Dictionary();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<LanguageChoice> languageDropDown;

    @FXML
    private TextArea insertTxtArea;

    @FXML
    private Button spellCheckBtn;

    @FXML
    private TextArea wrongTxtArea;

    @FXML
    private Label numErrorsLabel;

    @FXML
    private Button clearBtn;

    @FXML
    private Label timeLabel;

    @FXML
    void doClearText(ActionEvent event) {
    	numErrorsLabel.setText("");
    	timeLabel.setText("");
    	
    	wrongTxtArea.clear();
    	insertTxtArea.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	// CLEAR TUTTO
    	numErrorsLabel.setText("");
    	
    	//CARICA LA LINGUA
    	if (!this.dictionary.getLoadedLanguage().equals(this.languageDropDown.getValue())) {
    		this.dictionary.loadDictionary(this.languageDropDown.getValue());
    	}
    	
    	
    	long start = System.nanoTime();
    	//FAI SPELL CHECK
    	ArrayList<String> resCheck = (ArrayList<String>) this.dictionary.spellCheckText(this.insertTxtArea.getText());
    	long end = System.nanoTime();
    	
    	//VIEW
    	// NUM ERRORS LABEL
    	String msgLen = "";
    	String msgErr = "";
    	String msgTime = "";

    	int lenErrors = resCheck.size();
    	if (lenErrors == 0) {
    		numErrorsLabel.setTextFill(Color.GREEN);
    		msgLen = "The text doesn't contain errors";
    	} else {
    		numErrorsLabel.setTextFill(Color.RED);
    		msgLen = "The text contains " + lenErrors + " errors";
    		// WRONG TXT AREA
        	for (String error: resCheck) {
        		msgErr += error + "\n";
        	}
    	}
    	
    	end = end - start;
    	double time = end/(Math.pow(10, 9));
    	
    	msgTime = "Check completed in " + time + " s";
    	
    	numErrorsLabel.setText(msgLen);
    	wrongTxtArea.setText(msgErr);
    	timeLabel.setText(msgTime);
    	
    	
    }

    @FXML
    void initialize() {
        assert languageDropDown != null : "fx:id=\"languageDropDown\" was not injected: check your FXML file 'Scene.fxml'.";
        assert insertTxtArea != null : "fx:id=\"insertTxtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert spellCheckBtn != null : "fx:id=\"spellCheckBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert wrongTxtArea != null : "fx:id=\"wrongTxtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert numErrorsLabel != null : "fx:id=\"numErrorsLabel\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clearBtn != null : "fx:id=\"clearBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert timeLabel != null : "fx:id=\"timeLabel\" was not injected: check your FXML file 'Scene.fxml'.";
        
        languageDropDown.getItems().add(new LanguageChoice("English", "src/main/resources/English.txt"));
        languageDropDown.getItems().add(new LanguageChoice("Italiano", "src/main/resources/Italian.txt"));


    }
}
