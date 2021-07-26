package java_FX.dic_GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private TextField input;

    @FXML
    private TextField input1;

    @FXML
    private Button btnSearch;

    @FXML
    private ListView<String> myList;

    @FXML
    private Button btnExit;

    private String[] w = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten","one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myList.getItems().addAll(w);
        btnSearch.setDisable(true);
    }
    public void handle() {
        input1.textProperty().addListener((observableValue, oldValue, newValue) -> {
            btnSearch.setDisable(newValue.trim().isEmpty());
            ArrayList<String> arrayWord = new ArrayList<String>();
            if(!newValue.trim().isEmpty()) {
                for(int i=0; i<w.length; i++) {
                    if(w[i].contains(newValue)) {
                        myList.getItems().clear();
                        arrayWord.add(w[i]);
                    }
                }
                myList.getItems().addAll(arrayWord);
            }
            else if(newValue.equals(" ") || newValue.equals("\t")) {
                //
            }
            else {
                myList.getItems().addAll(w);
            }
        });
    }

    @FXML
    public void eventSearch() {
    }

    @FXML
    public void eventExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
