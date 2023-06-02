package shapov.cointrack;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Optional;
import java.io.IOException;

/**

 Класс ImageHelper с методами для работы с изображениями.
 <p>
 Данный класс предоставляет статические методы для преобразования изображения в формат Image,
 получения пути к открытому изображению, выбора файла изображения, и сохранения изображения.
 <p>
 Автор: [Ваше имя]
 Версия: 1.0
 */
public class ImageHelper {
    /**
    Метод mat2Image для преобразования матрицы изображения Mat в объект Image.
    @param frame матрица изображения Mat
    @param width ширина изображения
    @param height высота изображения
    @return объект Image, представляющий изображение
    */
    public static Image mat2Image(Mat frame, int width, int height) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()), width, height, true, false);
    }

    /**
     Метод getOpenImagePath для получения пути к открытому изображению.
     @return объект Optional, содержащий путь к изображению, если изображение выбрано, или пустое значение Optional, если изображение не выбрано
     @throws IOException если произошла ошибка ввода-вывода
     */
    public static Optional<String> getOpenImagePath() throws IOException {
        Optional<File> fileImage = getFileImage();
        return fileImage.isPresent() ? Optional.of(fileImage.get().getCanonicalPath()) : Optional.empty();
    }

    /**
    Метод getFileImage для выбора файла изображения.
    @return объект Optional, содержащий выбранный файл изображения, если файл выбран, или пустое значение Optional, если файл не выбран
    */
    public static Optional<File> getFileImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif", "*.jpeg", "*.tif"));

        File file = fileChooser.showOpenDialog(MainApplication.getPrimaryStage());
        if (file != null)
            return Optional.of(file);
        return Optional.empty();
    }

    /**
    Метод SaveImage для сохранения изображения.
    @param mat матрица изображения Mat
    @throws IOException если произошла ошибка ввода-вывода
    */
    public static void SaveImage(Mat mat) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save image File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);
        if (file != null)
            Imgcodecs.imwrite(file.getCanonicalPath().split("\\.")[0] + ".png", mat);
    }
}
