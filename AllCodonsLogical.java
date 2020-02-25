public class AllCodonsLogical {

    public int findStopCodon (String dna, int startIndex, String codon) {

        int currIndex = dna.indexOf(codon , startIndex);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(codon , currIndex + 1);
            }
        }
        return -1;
    }

    public String findingGene (String dna) {

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        /*int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length()) {
            System.out.println("No Stop Codon Found.");
            return "";
        }*/

        if (taaIndex == -1 || (tgaIndex !=-1 && tagIndex<taaIndex)) {
            minIndex = tagIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tgaIndex !=-1 && tgaIndex<minIndex)) {
            minIndex = tgaIndex;
        }

        if (minIndex==-1) {
            return "";
        }

        return dna.substring(startIndex, minIndex+3);
    }

    public void testSimpleGene (){

        String dna1 = "AATGCATATATAACTGAGCATA";
        String dna2 = "ATACGCTACTAA";
        String dna3 = "AATGCATAATTAGGACTGAGCATA";
        String dna4 = "ATGCTATCTATCTGCTAA";
        String dna5 = "ATGCTAGTCTCATTAA";

        System.out.println("DNA strand is = "+dna1);
        System.out.println("Gene strand is = "+findingGene(dna1));

        System.out.println("DNA strand is = "+dna2);
        System.out.println("Gene strand is = "+findingGene(dna2));

        System.out.println("DNA strand is = "+dna3);
        System.out.println("Gene strand is = "+findingGene(dna3));

        System.out.println("DNA strand is = "+dna4);
        System.out.println("Gene strand is = "+findingGene(dna4));

        System.out.println("DNA strand is = "+dna5);
        System.out.println("Gene strand is = "+findingGene(dna5));
    }

    public static void main (String[] args) {

        AllCodonsLogical p5 = new AllCodonsLogical();
        p5.testSimpleGene();
    }
}
