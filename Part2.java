public class Part2 {

    public String findSimpleGene (String dna, String startCodon, String stopCodon) {

        String newDna = dna.toUpperCase(); //Creating a duplicate string to handle case exceptions
        int startIndex = newDna.indexOf(startCodon);
        if (startIndex == -1) return "";
        int stopIndex = newDna.indexOf(stopCodon, startIndex+3);
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
        String dna5 = "atgtaa";

        System.out.println("DNA strand is = "+dna1);
        System.out.println("Gene strand is = "+findSimpleGene(dna1,"ATG", "TAA"));

        System.out.println("DNA strand is = "+dna2);
        System.out.println("Gene strand is = "+findSimpleGene(dna2,"ATG", "TAA"));

        System.out.println("DNA strand is = "+dna3);
        System.out.println("Gene strand is = "+findSimpleGene(dna3,"ATG", "TAA"));

        System.out.println("DNA strand is = "+dna4);
        System.out.println("Gene strand is = "+findSimpleGene(dna4,"ATG", "TAA"));

        System.out.println("DNA strand is = "+dna5);
        System.out.println("Gene strand is = "+findSimpleGene(dna5,"ATG", "TAA"));
    }

    public static void main (String[] args) {

        Part2 p2 = new Part2();
        p2.testSimpleGene();
    }
}
