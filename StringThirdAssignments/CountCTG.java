public class CountCTG {

    public int countCTG (String dna) {
        int count = 0, index = 0;
        while (true) {
            index = dna.indexOf("CTG", index);
            if (index != -1) {
                count++;
            } else break;
            index = dna.indexOf("CTG", index) + 3;
        }
        return count;
    }

    public static void main(String[] args) {

        CountCTG p1 = new CountCTG();
        String dna = "CTGCTG";
        System.out.println(p1.countCTG(dna));
    }
}
