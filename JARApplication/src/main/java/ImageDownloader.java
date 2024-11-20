import lombok.AllArgsConstructor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
public class ImageDownloader implements Runnable {
    private String folder;
    private String imageURL;
    //Проверки наличия картинок?
    public void run(){
        String imageName = imageURL.substring(imageURL.lastIndexOf('/'));
        //Возможно попробовать
        try(BufferedInputStream in = new BufferedInputStream(new URL(imageURL).openStream())){
            Files.copy(in, Path.of( folder + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("123");
        }
    }
}
