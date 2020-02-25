public class CgRatio {

    public double getCgRatio (String dna) {
        double countCg = 0;
        for (int i = 0; i < dna.length(); i++) {

            if (dna.charAt(i)=='C' || dna.charAt(i)=='G') {
                countCg++;
            }
        }
        return countCg/dna.length();
    }

    public static void main(String[] args) {

        CgRatio p1 = new CgRatio();
        String dna = "AATGCATATATAACTATCTAAATCTACGATATATTAAGGCTGATAGATGGCTAATAGTCATGTACATGACTAATAGTAATGATAG";
        System.out.println(p1.getCgRatio(dna));
    }
}
