import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class FileRead {

    public static void main(String args[])  {
        try {
            String content = Files.readString(Paths.get("/Users/jagannathan/test.txt"));
            Files.writeString(Paths.get("/Users/jagannathan/at.txt"), "This is an example");
            log.info(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
