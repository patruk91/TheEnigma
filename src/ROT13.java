public class ROT13 {
    private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void encryptDecrypt(CipherData cipherData) {
        String encryptedSentence = "";
        cipherData.prepareSentence();
        for (int i = 0; i < cipherData.getSentence().length(); i++) {
            char chr = cipherData.getSentence().charAt(i);
            int index = letters.indexOf(chr);
            if (index + 13 > letters.length() - 1) {
                index = (index + 13)- letters.length();
            }
            else {
                index = index + 13;
            }
            encryptedSentence += "" + letters.charAt(index);

        }
        System.out.println(encryptedSentence);
    }
}
