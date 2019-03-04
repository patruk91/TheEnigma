public class ROT13 {
    private static String lertters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void encryptDecrypt(String sentence) {
        String encryptedSentence = "";
        sentence = sentence.toUpperCase();
        for (int i = 0; i < sentence.length(); i++) {
            char chr = sentence.charAt(i);
            int index = lertters.indexOf(chr);
            if (index + 13 > lertters.length() - 1) {
                index = (index + 13)- lertters.length();
            }
            else {
                index = index + 13;
            }
            encryptedSentence += "" + lertters.charAt(index);

        }
        System.out.println(encryptedSentence);
    }

    public static void decrypt(String sentence) {
        
    }
}
