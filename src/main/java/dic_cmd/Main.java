package dic_cmd;

import com.darkprograms.speech.translator.GoogleTranslate;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = GoogleTranslate.translate("en", "vi", "hello");
        System.out.println(str);
    }
}
