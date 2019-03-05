public class Caesar {

    private static String alfabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    private static String toFind;
    private static int ind;
    public static void encrypt(CipherData cipherData) {
        int key = Integer.parseInt(cipherData.getKey());
        String sentence = cipherData.getSentence().toUpperCase();
        System.out.println("Key: " + key);
        System.out.println("Sentence: " + sentence);
        // System.out.println(alfabet.indexOf(sentence.charAt(1)));
        for (int i=0; i < sentence.length(); i++) {
            ind = alfabet.indexOf(sentence.charAt(i));
            System.out.println("Current index: " + sentence.charAt(ind) + " in alfabet: " + ind);
            System.out.println(alfabet.charAt(ind + key));
        }

    }

    public static void decrypt(CipherData cipherData) {
        System.out.println("Decryption in process");
        System.out.println("Key: " + cipherData.getKey());
        System.out.println("Sentence: " + cipherData.getSentence());
    }
}