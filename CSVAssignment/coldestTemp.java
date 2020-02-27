import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class coldestTemp {

    public CSVRecord findColdestTemp () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldTemp = smallestTemp(parser);
        return coldTemp;
    }

    public CSVRecord smallestTemp (CSVParser parser) {
        CSVRecord minTemp = null;
        for (CSVRecord record : parser) {
            minTemp = getSmallestOfTwo(record, minTemp);
        }
        return minTemp;
    }

    public void testFindColdestTemp () {
        CSVRecord coldTemp = findColdestTemp();
        System.out.println("PrecipitationIn: "+coldTemp.get("PrecipitationIn")+" TemperatureF: "+coldTemp.get("TemperatureF")+" DateUTC: "+coldTemp.get("DateUTC"));
        System.out.println("Conditions: "+coldTemp.get("Conditions")+" Humidity: "+coldTemp.get("Humidity"));
    }

    public CSVRecord getSmallestOfTwo (CSVRecord record, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) smallestSoFar = record;
        else {
            double lowestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (lowestTemp > currTemp && currTemp != -9999) {
                smallestSoFar = record;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord coldestOfManyDays () {
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

    public String fileWithColdestTemp () {
        CSVRecord smallestSoFar = null;
        String coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord record : parser) {
                if (smallestSoFar == null) smallestSoFar = record;
                else {
                    double lowestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                    double currTemp = Double.parseDouble(record.get("TemperatureF"));
                    if (lowestTemp > currTemp && currTemp != -9999) {
                        smallestSoFar = record;
                        coldestFile = f.getName();
                    }
                }
            }
        }
        return coldestFile;
    }

    public void testColdestOfManyDays () {
        CSVRecord coldTemp = coldestOfManyDays();
        System.out.println("PrecipitationIn: "+coldTemp.get("PrecipitationIn")+" TemperatureF: "+coldTemp.get("TemperatureF")+" DateUTC: "+coldTemp.get("DateUTC"));
        System.out.println("Conditions: "+coldTemp.get("Conditions")+" Humidity: "+coldTemp.get("Humidity"));
    }


    public double averageTemperatureInAFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double sum = 0, num = 0;
        for (CSVRecord record : parser) {
            sum += Double.parseDouble(record.get("TemperatureF"));
            num++;
        }
        return sum/num;
    }

    public void testAverageTemp () {
        System.out.println("The average temp in file is: "+averageTemperatureInAFile());
    }

    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double sum = 0, num = 0;
        for (CSVRecord record : parser) {

            if (Integer.parseInt(record.get("Humidity")) >= value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                num++;
            }
        }
        if (num>0) {
            return sum/num;
        }
        else {
            System.out.println("No temperatures with that humidity");
            return 0;
        }
    }

    public void testAverageTemperatureWithHighHumidityInFile (int value) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser,value);
        if (avg != 0) {
            System.out.println("The average temperature when humidity is higher than "+value+" is "+avg);
        }
    }


    public static void main (String[] args) {

        coldestTemp day = new coldestTemp();
        /*
        day.testColdestOfManyDays();
        System.out.println(day.fileWithColdestTemp());
        day.testFindColdestTemp();
         */
        day.testAverageTemp();
        day.testAverageTemperatureWithHighHumidityInFile(80);
    }
}
