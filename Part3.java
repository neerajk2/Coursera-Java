public class Part3 {

    public boolean twoOccurrences (String stringa, String stringb) {

        int firstOccurrence = stringb.indexOf(stringa);
        int lastOccurrence = stringb.lastIndexOf(stringa);
        if (firstOccurrence == lastOccurrence) { //No occurrence case also covered.
            return false;
        }
        else return true;
    }

    public void testing () {

        String case1b = "A story by Abby Long";
        String case1a = "by";

        System.out.println("twoOccurrences of "+case1a+ " in "+case1b+ " is "+twoOccurrences(case1a,case1b));

        String case2b = "ctgtatgta";
        String case2a = "atg";

        System.out.println("twoOccurrences of "+case2a+ " in "+case2b+ " is "+twoOccurrences(case2a,case2b));

        String a1 = "na";
        String b1 = "Banana";

        lastPart(a1,b1);

        String a2 = "West";
        String b2 = "Ode to the West Wind";

        lastPart(a2,b2);

    }

    public void lastPart (String a, String b) {

        int startIndex = b.indexOf(a);
        if (startIndex != -1) {
            System.out.println("The part of string after " + a + " in " + b + " is " + b.substring(startIndex));
        }
        else {
            System.out.println("The part of string after " + a + " in " + b + " is " + b);
        }
    }

    public static void main (String[] args) {

        Part3 p3 = new Part3();
        p3.testing();
    }
}
