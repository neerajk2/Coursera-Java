import edu.duke.*;
import java.io.File;

public class Part1 {

    public int findStopCodon(String dna, int startIndex, String codon) {

        int currIndex = dna.indexOf(codon, startIndex);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(codon, currIndex + 1);
            }
        }
        return -1;
    }

    public String findingGene(String dna, int where) {

        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;

        if (taaIndex == -1 || (tgaIndex != -1 && tagIndex < taaIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }

        if (minIndex == -1) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public StorageResource getAllGenes (String dna) {

        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currGene = findingGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            //System.out.println("The Current Gene is " + currGene);
            geneList.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();

        }
        return geneList;
    }

    public void testing (String dna) {

        System.out.println("Testing on "+dna);
        StorageResource gene = getAllGenes(dna);
        for (String g : gene.data()) {
            System.out.println(g);
        }
    }

    public static void main(String[] args) {

        Part1 p1 = new Part1();
        String dna = "AATGCATATATAACTATCTAAATCTACGATATATTAAGGCTGATAGATGGCTAATAGTCATGTACATGACTAATAGTAATGATAG";
        p1.testing(dna);
    }
}
