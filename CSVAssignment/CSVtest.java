import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVtest {

    public void tester () {
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        //countryInfo(parser, "India");
        //listExportersTwoProducts(parser, "gold","diamonds");
        //System.out.println(numberOfExporters(parser, "gold"));
        bigExporters(parser, "$1,000,000,000");
    }

    public void countryInfo (CSVParser parser, String country) {

        boolean c = true;
        for (CSVRecord record : parser) {
            if (record.get("Country").contains(country)) {
                System.out.println(country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
                c = false;
            }
        }
        if (c) System.out.println("NOT FOUND");
    }

    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {

        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                System.out.println(record.get("Country")+ ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
    }

    public int numberOfExporters (CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                count++;
            }
        }
        return count;
    }

    public void bigExporters (CSVParser parser, String amount) {

        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country")+": "+record.get("Value (dollars)"));
            }
        }
    }

    public static void main (String[] args) {

        CSVtest csv = new CSVtest();
        csv.tester();
    }
}
