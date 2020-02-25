import edu.duke.*;


public class Part4 {

    public void findWebLink () {

        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String words : url.words()){

            String lowerWords = words.toLowerCase(); // To make the search case insensitive
            int pos = lowerWords.indexOf("youtube.com");

            if (pos!= -1) {

                int leftQuote = words.indexOf("\"");
                int rightQuote = words.indexOf("\"", leftQuote + 1);
                System.out.println("The required url is : " + words.substring(leftQuote, rightQuote + 1));
            }
        }


    }

    public static void main (String[] args) {

        Part4 p4 = new Part4();
        p4.findWebLink();
    }
}
