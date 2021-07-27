package dic_cmd;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    public Scanner sc = new Scanner(System.in);
    private String sFile = "dictionaries_cmd.txt";
    public String getsFile() {
        return sFile;
    }
    public void setsFile(String sFile) {
        this.sFile = sFile;
    }
    public void dictionaryLookup() {
        int count = -1;
        System.out.print("Nhập từ bạn muốn tra cứu: ");
        sc.nextLine();
        String str = sc.nextLine();
        for(int i=0; i<super.getWords().size(); i++) {
            if(str.equals(super.getWords().get(i).getWord_target())) {
                count = i;
            }
        }
        if(count>=0) {
            System.out.printf("%-5s|%-24s|%-24s|\n", "No", "Vietnamese", "English");
            System.out.printf("%-5s|%-24s|%-24s|\n", "1", super.getWords().get(count).getWord_target(), super.getWords().get(count).getWord_explain());
        }
        else {
            System.out.println("Từ khóa không có trong từ điển!!");
        }
    }
    public void dictionarySearch() {
        ArrayList<Word> w = new ArrayList<>();
        System.out.print("Nhập từ bạn muốn tìm kiếm: ");
        sc.nextLine();
        String str = sc.nextLine();
        for(int i=0; i<super.getWords().size(); i++) {
            if(super.getWords().get(i).getWord_target().contains(str)) {
                w.add(super.getWords().get(i));
            }
        }
        if(w.size()>0) {
            System.out.printf("%-5s|%-24s|%-24s|\n", "No", "Vietnamese", "English");
            for(int i=0; i<w.size(); i++) {
                System.out.printf("%-5s|%-24s|%-24s|\n", i+1, w.get(i).getWord_target(), w.get(i).getWord_explain());
            }
        }
        else {
            System.out.println("Từ khóa không có trong từ điển!!");
        }
    }
    public void insertFromFile(String strFile) {
        try {
            File file = new File(strFile);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                String[] word = str.split("\t");
                Word w = new Word(word[0], word[1]);
                super.addWord(w);
            }
        } catch (Exception e ){
            System.out.println(e);
        }
    }
    public void updateWordtoFile(ArrayList<Word> words, String strFile) {
        try {
            File file = new File(strFile);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            ArrayList<String> array = new ArrayList<>();
            for(int i=0; i<words.size(); i++) {
                array.add(words.get(i).toString());
            }
            Collections.sort(array);
            for(String str:array) {
                bw.write(str);
                bw.newLine();
            }
            bw.close();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteWordfromList() {
        int countWord = super.getWords().size();
        String str;
        System.out.print("Nhập từ tiếng anh muốn xóa: ");
        sc.nextLine();
        str = sc.nextLine();
        for(int i=0; i<super.getWords().size(); i++) {
            if(str.equals(super.getWords().get(i).getWord_target())) {
                super.getWords().remove(i);
            }
        }
        if(super.getWords().size()<countWord) {
            System.out.println("Bạn đã xóa từ thành công!!");
        }
        else {
            System.out.println("Bạn đã xóa từ thất bại!!");
        }
        updateWordtoFile(super.getWords(), sFile);
    }
    public void addWordfromFile() {
        int countWord = super.getWords().size();
        System.out.print("Nhập từ tiếng anh: ");
        sc.nextLine();
        String wt = sc.nextLine();
        System.out.print("Nhập từ tiếng việt: ");
        String we = sc.nextLine();
        String s = wt + "\t" + we;
        Word w = new Word(wt, we);
        super.addWord(w);
        if(super.getWords().size()>countWord) {
            System.out.println("Bạn đã thêm từ thành công!!");
        }
        else System.out.println("Bạn đã thêm từ thất bại!!");
        updateWordtoFile(super.getWords(), sFile);
    }
}
