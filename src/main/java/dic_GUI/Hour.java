package dic_GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Hour {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int min;
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public Hour(int day, int month, int year, int hour, int min) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.min = min;
    }
    public String toString() {
        return day + "/" + month + "/" + year + ": " + hour + "h" + min;
    }
    private static ArrayList<Hour> arrayList = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        getDatafromFile();
        addData();
        printData();
    }
    public static void getDatafromFile() {
        try {
            File file = new File("C:\\Users\\ADMIN\\Desktop\\New folder\\hour.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while((str = br.readLine()) != null) {
                String[] w = str.split("/");
                String[] w1 = w[2].split(": ");
                String[] w2 = w1[1].split("h");
                Hour hour = new Hour(Integer.parseInt(w[0]),Integer.parseInt(w[1]), Integer.parseInt(w1[0]), Integer.parseInt(w2[0]), Integer.parseInt(w2[1]));
                arrayList.add(hour);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static String getSumHour() {
        int hourNew = 0;
        int minNew = 0;
        for(int i=0; i<arrayList.size(); i++) {
            hourNew += arrayList.get(i).getHour();
            minNew += arrayList.get(i).getMin();
        }
        if(minNew < 60) {
            return hourNew + "h" + minNew;
        }
        else {
            int h = minNew/60;
            hourNew +=h;
            int m = minNew%60;
            return hourNew + "h" + m;
        }
    }

    public static void printData() {
        for(int i=0; i<arrayList.size(); i++) {
            System.out.println(arrayList.get(i).toString());
        }
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Total hour: " + getSumHour());
    }
    public static void addData() {
        System.out.print("Enter day: ");
        int day = sc.nextInt();
        System.out.print("Enter month: ");
        int month = sc.nextInt();
        System.out.print("Enter year: ");
        int year = sc.nextInt();
        System.out.print("Enter hour: ");
        int hour = sc.nextInt();
        System.out.print("Enter minute: ");
        int min = sc.nextInt();
        Hour h = new Hour(day, month, year, hour, min);
        arrayList.add(h);
        try {
            Files.write(Paths.get("C:\\Users\\ADMIN\\Desktop\\New folder\\hour.txt"), (h.toString() + "\n").getBytes(), StandardOpenOption.APPEND);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
