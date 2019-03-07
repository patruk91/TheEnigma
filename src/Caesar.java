public class Caesar {

    private static String alfabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    private static int ind;
    private static String output = "";
    public static void encrypt(CipherData cipherData) {
        try {
            int key = Integer.parseInt(cipherData.getKey());
        if (key > 26) {
            System.out.println("Key should be taken from numbers 0 - 26");
            return;
        }
        String sentence = cipherData.getSentence().toUpperCase();
        System.out.println("Key: " + key + "    Sentence: " + sentence);
        for (int i=0; i < sentence.length(); i++) {
            ind = alfabet.indexOf(sentence.charAt(i));
            if (alfabet.length()-1 < ind+key) {
                ind = key - (alfabet.length()-ind);
                output = output + alfabet.charAt(ind);
            } else {
                output = output + alfabet.charAt(ind + key);
            }
        }
        System.out.println("Encrypted message: " + output);
        } catch (NumberFormatException exception) {
            System.out.println("Enter valid key (number from 1 - 26) ");
            System.exit(0);
        };
    }

    public static void decrypt(CipherData cipherData) {
        try {
            int key = Integer.parseInt(cipherData.getKey());
        if (key > 26) {
            System.out.println("Key should be taken from numbers 0 - 26");
            return;
        }
        String sentence = cipherData.getSentence().toUpperCase();
        System.out.println("Key: " + key + "    Sentence: " + sentence);
        for (int i=0; i < sentence.length(); i++) {
            ind = alfabet.indexOf(sentence.charAt(i));
            if (ind-key < 0) {
                ind = alfabet.length() - (key - ind );
                output = output + alfabet.charAt(ind);
            } else {
                output = output + alfabet.charAt(ind - key);
            }
        }
        System.out.println("Decrypted message: " + output);
        }
        catch (NumberFormatException exception) {
            System.out.println("Enter valid key (number from 1 - 26) ");
            System.exit(0);
        };
    }
}