package ru.msnigirev.tbank.jar;

import lombok.AllArgsConstructor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

@AllArgsConstructor
public class ImageDownloader implements Runnable {
    private String folder;
    private String imageURL;
    public void run(){
        String imageName = imageURL.substring(imageURL.lastIndexOf('/'));
        try(BufferedInputStream in = new BufferedInputStream(new URL(imageURL).openStream())) {
            Files.copy(in, Path.of(folder + imageName));
        } catch (NoSuchFileException e) {
            System.out.printf("Папка %s не найдена\n", folder);
        } catch (FileAlreadyExistsException e) {
            System.out.printf("Изображение %s уже скачено\n", e.getMessage());
        } catch (UnknownHostException e) {
            System.out.printf("Нет соединения или неправильно указан адрес.\nФайл %s не скачан\n", imageName);
        } catch (ConnectException e) {
            System.out.printf("Соединение было прервано.\nФайл %s не скачан\n", imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
