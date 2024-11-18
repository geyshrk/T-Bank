import lombok.AllArgsConstructor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@AllArgsConstructor
public class ImageDownloader extends Thread {
    String folder;
    String imageURL;
    //Проверки наличия картинок?
    public void run(){
        try(BufferedInputStream in = new BufferedInputStream(new URL(imageURL).openStream())){
            Files.copy(in, Paths.get(folder));
        } catch (IOException e) {
            // handle IOException
        }
    }
}
