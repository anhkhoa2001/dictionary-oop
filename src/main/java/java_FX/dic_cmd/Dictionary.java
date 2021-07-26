package java_FX.dic_cmd;

import java.util.ArrayList;

public class Dictionary {
    private static ArrayList<Word> words = new ArrayList<>();
    public ArrayList<Word> getWords() {
        return words;
    }
    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
    public void addWord(Word word) {
        int count = 0;
        for(int i=0; i<words.size(); i++) {
            if(words.get(i).toString().equals(word.toString())){
                count++;
            }
        }
        if(count==0){
            words.add(word);
        }
    }
}
