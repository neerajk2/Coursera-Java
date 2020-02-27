import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class lowestHumidity {

    public CSVRecord findLowestHumidity () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowHumidity = lowestHumid(parser);
        return lowHumidity;
    }

    public CSVRecord lowestHumid (CSVParser parser) {
        CSVRecord lowHumidity = null;
        for (CSVRecord record : parser) {
            lowHumidity = getSmallestOfTwo(record, lowHumidity);
        }
        return lowHumidity;
    }

    public void testFindLowestHumidity () {
        CSVRecord lowHumidity = findLowestHumidity();
        System.out.println("TemperatureF: "+lowHumidity.get("TemperatureF")+" DateUTC: "+lowHumidity.get("DateUTC"));
        System.out.println("Conditions: "+lowHumidity.get("Conditions")+" Humidity: "+lowHumidity.get("Humidity"));
    }

    public CSVRecord getSmallestOfTwo (CSVRecord record, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) smallestSoFar = record;
        else {
            double lowestHumid = Double.parseDouble(smallestSoFar.get("Humidity"));
            double currHumid = Double.parseDouble(record.get("Humidity"));
            if (lowestHumid > currHumid && record.get("Humidity")!="N/A") {
                smallestSoFar = record;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord lowestHumidityOfManyDays () {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord record : parser) {
                smallestSoFar = getSmallestOfTwo(record, smallestSoFar);
            }
        }
        return smallestSoFar;
    }

    public void testLowestHumidityOfManyDays () {
        CSVRecord lowHumidity = lowestHumidityOfManyDays();
        System.out.println("TemperatureF: "+lowHumidity.get("TemperatureF")+" DateUTC: "+lowHumidity.get("DateUTC"));
        System.out.println("Conditions: "+lowHumidity.get("Conditions")+" Humidity: "+lowHumidity.get("Humidity"));
    }


    public String fileWithLowestHumidity () {
        CSVRecord smallestSoFar = null;
        String lowHumidFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord record : parser) {
                if (smallestSoFar == null) smallestSoFar = record;
                else {
                    double lowestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                    double currTemp = Double.parseDouble(record.get("Humidity"));
                    if (lowestTemp > currTemp && record.get("Humidity")!="N/A") {
                        smallestSoFar = record;
                        lowHumidFile = f.getName();
                    }
                }
            }
        }
        return lowHumidFile;
    }


    public static void main (String[] args) {

        lowestHumidity day = new lowestHumidity();
        day.testLowestHumidityOfManyDays();
        System.out.println(day.fileWithLowestHumidity());
        day.testFindLowestHumidity();
    }
}
