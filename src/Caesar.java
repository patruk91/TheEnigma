public class Caesar {

    private static String alfabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    private static int ind;
    private static String output = "";
    public static void encrypt(CipherData cipherData) {
        int key = Integer.parseInt(cipherData.getKey());
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

    }

    public static void decrypt(CipherData cipherData) {
        System.out.println("Decryption in process");
        System.out.println("Key: " + cipherData.getKey());
        System.out.println("Sentence: " + cipherData.getSentence());
    }
}