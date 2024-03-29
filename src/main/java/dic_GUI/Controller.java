package dic_GUI;

import com.darkprograms.speech.translator.GoogleTranslate;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private ImageView image_home, image_add, image_remove, image_exit, image_translate, image_sound, image_sound_trans;

    @FXML
    private TextField input1, addEnglish, removeWord;

    @FXML
    private WebView output;

    @FXML
    private TextArea addVietnamese, inputTranslate, outputTranslate;

    @FXML
    private Button btnAdd, btnRemove, btnTranslate;

    @FXML
    private ListView<String> myList;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tab_home, tab_add, tab_remove, tab_translate;

    private ArrayList<String> arrayTarget = new ArrayList<>();

    private ArrayList<String> arrayExplain = new ArrayList<>();

    private ObservableList<String> observableList = FXCollections.observableList(arrayTarget);

    private final String sName = "kevin16";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        renderDic();
    }
    public void renderDic() {
        insertFromFile();// lay tu
        getWordtolist();// them tu tieng anh vao myList
        setDis(); // trang thai cho button area
        setImage(); // them anh
        handle(); // tra tu
    }

    public void setDis() {
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        inputTranslate.setWrapText(true);
        outputTranslate.setWrapText(true);
    }

    public void setImage() {
        exportImage("src/main/resources/img/home_icon_2.png", image_home);
        exportImage("src/main/resources/img/add_icon.png", image_add);
        exportImage("src/main/resources/img/icon_remove.png", image_remove);
        exportImage("src/main/resources/img/icon-exit.png", image_exit);
        exportImage("src/main/resources/img/icon_sound.jpg", image_sound);
        exportImage("src/main/resources/img/translate-icon.png", image_translate);
        exportImage("src/main/resources/img/icon_sound.jpg", image_sound_trans);
    }

    public void exportImage(String str, ImageView image) {
        File file = new File(str);
        try {
            String url1 = file.toURI().toURL().toString();
            Image img = new Image(url1);
            image.setImage(img);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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

    public boolean constainArray(String str) {
        ArrayList<String> array = new ArrayList<>();
        for(int i=0; i<arrayTarget.size(); i++) {
            String s = arrayTarget.get(i) + "\t" + arrayExplain.get(i);
            array.add(s);
        }
        int count = 0;
        for(String s1:array) {
            if(s1.equals(str)) {
                count++;
            }
        }
        if(count==0) {
            return false;
        }
        return true;
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
        String str = addEnglish.getText() + "\t" + addVietnamese.getText();
        if(constainArray(str)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("Thêm từ thất bại!");
            alert.showAndWait();
        }
        else {
            arrayTarget.add(addEnglish.getText());
            arrayExplain.add(addVietnamese.getText());
            addWordtoFile();
            addEnglish.clear();
            addVietnamese.clear();
            System.out.println(arrayTarget.size());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("Thêm từ thành công!");
            alert.showAndWait();
        }
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText("Xóa từ thành công!");
        alert.showAndWait();
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
        Stage stage = (Stage) image_exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void eventImgHome() {
        tabPane.getSelectionModel().select(tab_home);
    }
    @FXML
    public void eventImgAdd() {
        tabPane.getSelectionModel().select(tab_add);
    }
    @FXML
    public void eventImgRemove() {
        tabPane.getSelectionModel().select(tab_remove);
    }
    @FXML
    public void eventImgTranslate() {
        tabPane.getSelectionModel().select(tab_translate);
    }

    @FXML
    public void eventImgSound() {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            VoiceManager vm  = VoiceManager.getInstance();
            Voice voice = vm.getVoice("kevin16");
            voice.allocate();
            voice.speak(input1.getText());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void eventImgSound_Trans() {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            VoiceManager vm  = VoiceManager.getInstance();
            Voice voice = vm.getVoice("kevin16");
            voice.allocate();
            voice.speak(inputTranslate.getText());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void eventTranslateEng(Event event) {
        try {
            outputTranslate.setText(GoogleTranslate.translate("en", "vi", inputTranslate.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
