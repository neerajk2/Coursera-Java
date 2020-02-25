public class Part2 {

    public int howMany (String a, String b) {
        int startIndex = 0;
        int count = 0;
        while (true) {

            int index = b.indexOf(a, startIndex);
            if (index == -1) {
                break;
            }
            count++;
            startIndex = b.indexOf(a , startIndex) + a.length();
        }
        return count;
    }

    public void testHowMany () {

        String a1 = "AA";
        String b1 = "ATAAAAABAAT";
        System.out.println(howMany(a1,b1));

        String a2 = "AT";
        String b2 = "ATAAAAABAAT";
        System.out.println(howMany(a2,b2));

        String a3 = "AAHDSJSDAIAFG";
        String b3 = "ATAAAAABAAT";
        System.out.println(howMany(a3,b3));

        String a4 = "A";
        String b4 = "ATAAAAABAAT";
        System.out.println(howMany(a4,b4));
    }

    public static void main (String[] args){

        Part2 p2 = new Part2();
        p2.testHowMany();
    }
}
