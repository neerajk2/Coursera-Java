public class Part1 {

    public String findSimpleGene (String dna) {

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return "";
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1) return "";
        if ((stopIndex-startIndex)%3 == 0) {
            return dna.substring(startIndex, stopIndex+3);
        }
        return "";
    }

    public void testSimpleGene (){

        String dna1 = "ATGCATGAC";
        String dna2 = "ATACGCTACTAA";
        String dna3 = "ATCTGATCGAT";
        String dna4 = "ATGCTATCTATCTGCTAA";
        String dna5 = "ATGCTAGTCTCATTAA";

        System.out.println("DNA strand is = "+dna1);
        System.out.println("Gene strand is = "+findSimpleGene(dna1));

        System.out.println("DNA strand is = "+dna2);
        System.out.println("Gene strand is = "+findSimpleGene(dna2));

        System.out.println("DNA strand is = "+dna3);
        System.out.println("Gene strand is = "+findSimpleGene(dna3));

        System.out.println("DNA strand is = "+dna4);
        System.out.println("Gene strand is = "+findSimpleGene(dna4));

        System.out.println("DNA strand is = "+dna5);
        System.out.println("Gene strand is = "+findSimpleGene(dna5));
    }

    public static void main (String[] args) {

        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }
}
