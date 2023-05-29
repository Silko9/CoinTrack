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

public class ImageHelper {
    public static Image mat2Image(Mat frame, int width, int height) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()), width, height, true, false);
    }

    public static Optional<String> getOpenImagePath() throws IOException {
        Optional<File> fileImage = getFileImage();
        return fileImage.isPresent() ? Optional.of(fileImage.get().getCanonicalPath()) : Optional.empty();
    }

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
