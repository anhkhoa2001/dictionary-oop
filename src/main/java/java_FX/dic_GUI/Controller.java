package java_FX.dic_GUI;

import java_FX.dic_cmd.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private TextField input1;

    @FXML
    private WebView output;

    @FXML
    private Button btnSearch;

    @FXML
    private ListView<String> myList;

    @FXML
    private Button btnExit;

    private ArrayList<String> array = new ArrayList<>();

    private ArrayList<Word> words = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insertFromFile();
        separationWord();
        getWordtolist();
        btnSearch.setDisable(true);
        handle();
    }

    public void loadHTMLtoWebView() {
        WebEngine webEngine = output.getEngine();
        String str = input1.getText();
        System.out.println(str);
        String out;
        for(int i=0; i<words.size(); i++) {
            if(str.equals(words.get(i).getWord_target())) {
                webEngine.loadContent(words.get(i).getWord_explain());
                System.out.println(words.get(i).getWord_explain());
            }
        }
    }
    public void getWordtolist() {
        for (int i=0; i<words.size(); i++) {
            myList.getItems().add(words.get(i).getWord_target());
        }
    }

    public void separationWord() {
        for(String str:array){
            String[] w = str.split("\t");
            Word word = new Word(w[0], w[1]);
            words.add(word);
        }
    }


    public void insertFromFile() {
        try {
            File file = new File("dictionaries.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                array.add(str);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void handle() {
        input1.textProperty().addListener((observableValue, oldValue, newValue) -> {
            btnSearch.setDisable(newValue.trim().isEmpty());
            ArrayList<String> arrayWord = new ArrayList<String>();
            if(!newValue.trim().isEmpty()) {
                for(int i=0; i<array.size(); i++) {
                    if(words.get(i).getWord_target().contains(newValue)) {
                        myList.getItems().clear();
                        arrayWord.add(words.get(i).getWord_target());
                    }
                }
                myList.getItems().addAll(arrayWord);
            }
            else if(newValue.equals(" ") || newValue.equals("\t")) {
                //
            }
            else {
                myList.getItems().clear();
                getWordtolist();
            }
        });
    }

    @FXML
    public void handleMyList() {
        String str = myList.getSelectionModel().getSelectedItem();
        input1.setText(str);
        loadHTMLtoWebView();
    }

    @FXML
    public void eventExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
