package dic_cmd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.write(Paths.get("dictionaries_cmd.txt"),"khoa dep trai    khoas\n".getBytes() , StandardOpenOption.APPEND);
        (new DictionaryCommandLine()).menuDictionary();

    }
}
