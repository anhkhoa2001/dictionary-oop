package dic_GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private TextField input1;

    @FXML
    private WebView output;

    @FXML
    private TextField addEnglish, removeWord;

    @FXML
    private TextArea addVietnamese;

    @FXML
    private Button btnAdd, btnRemove;

    @FXML
    private Button btnSearch;

    @FXML
    private ListView<String> myList;

    @FXML
    private Button btnExit;

    private ArrayList<String> arrayTarget = new ArrayList<>();

    private ArrayList<String> arrayExplain = new ArrayList<>();

    private ObservableList<String> observableList = FXCollections.observableList(arrayTarget);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insertFromFile();// lay tu
        getWordtolist();// them tu tieng anh vao myList
        btnSearch.setDisable(true);
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        handle();
    }
    public void addWordtoFile() {
        try {
            Files.write(Paths.get("dictionaries.txt"), (arrayTarget.get(arrayTarget.size()-1) + "\t" + arrayExplain.get(arrayExplain.size()-1) + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void removeWordtoFile() {
        try {
            File file = new File("dictionaries.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for(int i=0; i<arrayTarget.size(); i++) {
                String str = arrayTarget.get(i) + "\t" +arrayExplain.get(i);
                bw.write(str);
                bw.newLine();
            }
            bw.close();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loadHTMLtoWebView() {
        WebEngine webEngine = output.getEngine();
        String str = input1.getText();
        for(int i=0; i<arrayTarget.size(); i++) {
            if(str.equals(arrayTarget.get(i))) {
                webEngine.loadContent(arrayExplain.get(i));
            }
        }
    }
    public void getWordtolist() {
        myList.setItems(observableList);
    }

    public void insertFromFile() {
        try {
            File file = new File("dictionaries.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                String[] w = str.split("\t");
                arrayTarget.add(w[0]);
                arrayExplain.add(w[1]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void eventADD() {
        addEnglish.textProperty().addListener((observableValue, s, t1) -> {
            btnAdd.setDisable(t1.trim().isEmpty());
        });
        addVietnamese.textProperty().addListener((observableValue, s, t1) -> {
            btnAdd.setDisable(t1.trim().isEmpty());
        });
    }

    @FXML
    public void eventBtnAdd() {
        arrayTarget.add(addEnglish.getText());
        arrayExplain.add(addVietnamese.getText());
        addWordtoFile();
        addEnglish.clear();
        addVietnamese.clear();
    }

    @FXML
    public void eventCancel() {
        addEnglish.clear();
        addVietnamese.clear();
        removeWord.clear();
    }

    @FXML
    public void eventBtnRemove() {
        String str = removeWord.getText();
        for(int i=0; i<arrayTarget.size(); i++) {
            if(str.equals(arrayTarget.get(i))){
                arrayTarget.remove(i);
                arrayExplain.remove(i);
            }
        }
        removeWordtoFile();
        removeWord.clear();
    }

    @FXML
    public void eventRemove() {
        removeWord.textProperty().addListener((observableValue, s, t1) -> {
            btnRemove.setDisable(t1.trim().isEmpty());
        });
    }

    @FXML
    public void handle() {
        input1.textProperty().addListener((observableValue, oldValue, newValue) -> {
            btnSearch.setDisable(newValue.trim().isEmpty());
            ArrayList<String> arrayWord = new ArrayList<String>();
            if(!newValue.trim().isEmpty()) {
                for(int i=0; i<arrayTarget.size(); i++) {
                    if(arrayTarget.get(i).toLowerCase().contains(newValue)) {
                        arrayWord.add(arrayTarget.get(i));
                    }
                }
                ObservableList<String> obser = FXCollections.observableList(arrayWord);
                myList.setItems(obser);
            }
            else if(newValue.equals(" ") || newValue.equals("\t")) {
                //
            }
            else {
                getWordtolist();
            }
        });
        input1.clear();
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
