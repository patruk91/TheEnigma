public class Vigenere {

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
    }

    public static void decrypt(CipherData cipherData) {

    }
}
