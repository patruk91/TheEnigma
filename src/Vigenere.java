public class Vigenere {
    private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String encryptedSentence = "";

    public static void encrypt(CipherData cipherData) {
        cipherData.setSentence(cipherData.getSentence().replace(" ", "").toUpperCase());
        int keyLength = cipherData.getKey().length();
        int sentenceLength = cipherData.getSentence().length();
        if (keyLength > sentenceLength) {
            cipherData.setKey((cipherData.getKey().substring(0, sentenceLength)).toUpperCase());
        }
        else {
            int multiplying = sentenceLength / keyLength;
            int remainder = sentenceLength % keyLength;
            cipherData.setKey((cipherData.getKey().repeat(multiplying) + cipherData.getKey().substring(0, remainder)).toUpperCase());
        }
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

    }
}
