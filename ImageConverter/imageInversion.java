import edu.duke.*;
import java.io.*;


public class imageInversion {

    public ImageResource createInversion (ImageResource image) {
        ImageResource invertedImage  = new ImageResource(image.getWidth(), image.getHeight());
        for (Pixel pixel : invertedImage.pixels()) {
            Pixel inImage = image.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inImage.getRed());
            pixel.setBlue(255 - inImage.getBlue());
            pixel.setGreen(255 - inImage.getGreen());
        }
        return invertedImage;
    }

    public void setNewName (ImageResource image, String oldName) {
        String newName = "inverted-"+oldName;
        image.setFileName(newName);
    }

    public void convertFiles () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String oldName = f.getName();
            ImageResource greyImage = createInversion(image);
            setNewName(greyImage, oldName);
            greyImage.draw();
            greyImage.save();
        }
    }

    public static void main (String[] args) {
        imageInversion invert = new imageInversion();
        invert.convertFiles();
    }

}
