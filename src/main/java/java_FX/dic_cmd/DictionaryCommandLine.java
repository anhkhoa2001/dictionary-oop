package java_FX.dic_cmd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class DictionaryCommandLine extends DictionaryManagement {
    public static int countMenu;
    public void showAllWord() {
        System.out.printf("%-5s|%-24s|%-24s|\n", "No", "Vietnamese", "English");
        for(int i=0; i<super.getWords().size(); i++) {
            System.out.printf("%-5d|%-24s|%-24s|\n", i+1, super.getWords().get(i).getWord_target(), super.getWords().get(i).getWord_explain());
        }
    }
    public void menuDictionary() {
        super.insertFromFile();
        System.out.println("---------------MENU---------------");
        System.out.println("1.Thêm từ vựng");
        System.out.println("2.Xóa từ vựng");
        System.out.println("3.Tra cứu từ vựng");
        System.out.println("4.Tìm kiếm từ vựng");
        System.out.println("5.Danh sách từ điển");
        System.out.println("6.Xuất dữ liệu ra file");
        System.out.println("7.Kết thúc");
        System.out.print("Nhập số để chọn chức năng của từ điển: ");
        countMenu = super.sc.nextInt();
        System.out.println("----------------------------------");
        switch (countMenu) {
            case 1:
                super.addWordfromFile();
                menuDictionary();
                break;
            case 2:
                super.deleteWordfromList();
                menuDictionary();
                break;
            case 3:
                super.dictionaryLookup();
                menuDictionary();
                break;
            case 4:
                super.dictionarySearch();
                menuDictionary();
                break;
            case 5:
                showAllWord();
                menuDictionary();
                break;
            case 6:
                dictionaryExportToFile();
                menuDictionary();
                break;
            case 7:
                break;
        }
    }
    //export to output.txt
    public void dictionaryExportToFile() {
        super.insertFromFile();
        try {
            File file = new File("output.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            ArrayList<String> array = new ArrayList<>();
            for(int i=0; i<super.getWords().size(); i++) {
                String str = super.getWords().get(i).toString();
                array.add(str);
            }
            for(String s:array) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            System.out.println("Bạn đã xuất dữ liệu thành công!!");
        } catch (Exception e) {
            System.out.println("Bạn đã xuất dữ liệu thất bại!!");
        }
    }

}
