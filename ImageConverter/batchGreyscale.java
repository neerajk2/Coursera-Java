import edu.duke.*;
import java.io.*;



public class batchGreyscale {

    public ImageResource convertGreyscale (ImageResource image) {
        ImageResource greyImage  = new ImageResource(image.getWidth(), image.getHeight());
        for (Pixel pixel : greyImage.pixels()) {
            Pixel inImage = image.getPixel(pixel.getX(), pixel.getY());
            int average = (inImage.getRed() + inImage.getBlue() + inImage.getGreen())/3;
            pixel.setRed(average);
            pixel.setBlue(average);
            pixel.setGreen(average);
        }
        return greyImage;
    }

    public void setNewName (ImageResource image, String oldName) {
        String newName = "grey-"+oldName;
        image.setFileName(newName);
    }

    public void convertFiles () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String oldName = f.getName();
            ImageResource greyImage = convertGreyscale(image);
            setNewName(greyImage, oldName);
            greyImage.draw();
            greyImage.save();
        }
    }

    public static void main (String[] args) {
        batchGreyscale batch = new batchGreyscale();
        batch.convertFiles();
    }
}
