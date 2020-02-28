import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;


public class babyNames {

    public void totalBirths(FileResource fr) {
        int totalBirth = 0, girlnames = 0, boyNames = 0, totalNames = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            totalBirth += Integer.parseInt(record.get(2));
            if (record.get(1).equals("F")) {
                girlnames++;
            } else {
                boyNames++;
            }
        }
        totalNames = girlnames + boyNames;
        System.out.println("Total Birth: " + totalBirth);
        System.out.println("Total Names: " + totalNames);
        System.out.println("Girl Names: " + girlnames);
        System.out.println("Boy Names: " + boyNames);
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender) {
        String na = "yob" + year + ".csv";
        File file = new File(na);
        int rank = 0;
        boolean found = false;
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (record.get(1).contains(gender)) {
                rank++;
                if (record.get(0).equals(name)) {
                    found = true;
                    break;
                }
            }

        }
        if (found) {
            return rank;
        } else {
            return -1;
        }
    }

    public void testGetRank() {
        int year = 2004;
        String name = "Mary";
        String gender = "F";
        int rank = getRank(year, name, gender);
        if (rank != -1) {
            System.out.println("The rank of " + name + " in " + year + " in category " + gender + " is " + rank);
        } else {
            System.out.println("The rank of " + name + " in " + year + " in category " + gender + " is Not Found.");

        }
    }

    public String getName(int year, int rank, String gender) {

        String name = "yob" + String.valueOf(year) + ".csv";
        File file = new File(name);
        FileResource fr = new FileResource(file);
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank--;
                if (rank == 0) return record.get(0);
            }
        }
        return "Not found";
    }

    public void testGetName() {
        int year = 2004;
        int rank = 11;
        String gender = "F";
        String name = getName(year, rank, gender);
        if (rank != -1) {
            System.out.println("The name of rank " + rank + " in " + year + " in category " + gender + " is " + name);
        } else {
            System.out.println("The name of rank " + rank + " in " + year + " in category " + gender + " is Not Found.");

        }
    }



    public void myNameInYear(String name, int year, int newyear, String gender) {
        String nameYear = "yob" + year + "short.csv";
        FileResource fr = new FileResource(nameYear);
        CSVParser parserold = fr.getCSVParser(false);

        String nameNewYear = "yob" + newyear + "short.csv";
        FileResource fr1 = new FileResource(nameNewYear);
        CSVParser parsernew = fr1.getCSVParser(false);

        int rank = 0;
        boolean find = false;
        for (CSVRecord record : parserold) {
            if (record.get(1).equals(gender)) {
                rank += 1;
                if (record.get(0).equals(name)) {
                    find = true;
                    break;
                }
            }
        }

        int ranknew = 0;
        boolean findnew = false;
        if (find == false) System.out.println("NO NAME!");
        else {
            for (CSVRecord record : parsernew) {
                if (record.get(1).equals(gender)) {
                    ranknew += 1;
                    if (ranknew == rank) {
                        findnew = true;
                        System.out.println(name + " born in " + year + " would be " + record.get(0) + " if she/he was born in " + newyear);
                    }

                }
            }
            if (findnew == false) System.out.println("No such rank in year " + newyear);
        }
    }

    public void testMyNameInYear() {
        myNameInYear("Isabella", 2012, 2014, "F");
    }



    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int ranktonow = 0;
        boolean findall = false;
        for (File f : dr.selectedFiles()) {
            int rank = 0;
            boolean find = false;
            String fname = f.getName();
            FileResource fr = new FileResource(fname);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record : parser) {
                if (record.get(1).equals(gender)) {
                    rank += 1;
                    if (record.get(0).equals(name)) {
                        find = true;
                        break;
                    }
                }
            }
            if (find == true) {
                findall = true;
                if (ranktonow == 0) ranktonow = rank;
                else if (ranktonow > rank) ranktonow = rank;
            }

        }
        if (findall == false) return -1;
        else return ranktonow;
    }

    public void testYearOfHighestRank() {
        int ranktonow = yearOfHighestRank("Mason", "M");
        System.out.println("Highest rank is " + ranktonow);
    }


    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double ranktonow = 0;
        int findall = 0;
        for (File f : dr.selectedFiles()) {
            int rank = 0;
            int find = 0;
            String fname = f.getName();
            FileResource fr = new FileResource(fname);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record : parser) {
                if (record.get(1).equals(gender)) {
                    rank += 1;
                    if (record.get(0).equals(name)) {
                        find = 1;
                        break;
                    }
                }
            }
            if (find == 1) {
                findall += 1;
                ranktonow += rank;
            }

        }

        if (findall == 0) return -1;
        else return ranktonow / findall;
    }

    public void testGetAverageRank() {
        double average = getAverageRank("Jacob", "M");
        System.out.println("Average rank is " + average);
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String nameyear = "yob" + year + "short.csv";
        FileResource fr = new FileResource(nameyear);
        CSVParser parser = fr.getCSVParser(false);
        int find = 0;
        int sum = 0;
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    find = 1;
                    break;
                }
                sum += Integer.parseInt(record.get(2));
            }
        }
        if (find == 1) return sum;
        else return -1;
    }

    public void testGetTotalBirthsRankedHigher() {
        int sum = getTotalBirthsRankedHigher(2012, "Ethan", "M");
        System.out.println("The total births higher is " + sum);
    }

    public static void main(String[] args) {

        babyNames baby = new babyNames();
        baby.testGetRank();
        baby.testGetAverageRank();
        baby.testYearOfHighestRank();
        baby.testGetName();
        baby.testGetTotalBirthsRankedHigher();
    }
}
