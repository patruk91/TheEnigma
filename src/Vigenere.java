public class Vigenere {
    private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String encryptedSentence = "";

    public static void encrypt(CipherData cipherData) {
        prepareKey(cipherData);
        for (int i = 0; i < cipherData.getSentence().length(); i++) {
            int sentenceIndex = letters.indexOf(cipherData.getSentence().charAt(i));
            int keyIndex = letters.indexOf(cipherData.getKey().charAt(i));
            if (sentenceIndex + keyIndex - 1 >= letters.length() - 1){
                int indexOfChar = sentenceIndex + keyIndex - letters.length();
                char charToAdd = letters.charAt(indexOfChar);
                encryptedSentence += "" + charToAdd;
            }
            else {
                int indexOfChar = sentenceIndex + keyIndex;
                char charToAdd = letters.charAt(indexOfChar);
                encryptedSentence += "" + charToAdd;
            }
        }
        System.out.println(encryptedSentence);
    }

    public static void decrypt(CipherData cipherData) {
        prepareKey(cipherData);
        for (int i = 0; i < cipherData.getSentence().length(); i++) {
            int sentenceCharIndex = letters.indexOf(cipherData.getSentence().charAt(i));
            int keyCharIndex = letters.indexOf(cipherData.getKey().charAt(i));
            int indxOfDecodecChar = sentenceCharIndex - keyCharIndex;
            if (indxOfDecodecChar < 0){
                indxOfDecodecChar += 26;
            }
            encryptedSentence += "" + letters.charAt(indxOfDecodecChar);
        }
        System.out.println(encryptedSentence);

    }

    private static void prepareKey(CipherData cipherData) {
        cipherData.prepareSentence();
        cipherData.prepareKeyForVigenere();
    }
}
