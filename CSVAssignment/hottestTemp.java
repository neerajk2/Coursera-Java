import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class hottestTemp {

    public void findHottestTemp () {
        FileResource fr = new FileResource("weather-2012-01-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord hotTemp = largestTemp(parser);
        System.out.println(hotTemp.get("TimeEST")+"  "+hotTemp.get("TemperatureF")+"  "+hotTemp.get("DateUTC"));
    }


    public CSVRecord largestTemp (CSVParser parser) {
        CSVRecord maxTemp = null;
        for (CSVRecord record : parser) {
            maxTemp = getLargestOfTwo(record, maxTemp);
        }
        return maxTemp;
    }

    public CSVRecord getLargestOfTwo (CSVRecord record, CSVRecord largestSoFar) {
        if (largestSoFar == null) largestSoFar = record;
        else {
            double highestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (highestTemp < currTemp) {
                largestSoFar = record;
            }
        }
        return largestSoFar;
    }

    public CSVRecord hottestOfManyDays () {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord record : parser) {
                largestSoFar = getLargestOfTwo(record, largestSoFar);
            }
        }
        return largestSoFar;
    }
    public void testHottestOfManyDays () {
        CSVRecord hotTemp = hottestOfManyDays();
        System.out.println("  "+hotTemp.get("TemperatureF")+"  "+hotTemp.get("DateUTC"));
    }

    public static void main (String[] args) {

        hottestTemp day = new hottestTemp();
        day.testHottestOfManyDays();
    }
}
