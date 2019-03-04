public class HandleCipher {

    public static void decideMethodToCryptograph(CipherData cipherData) {
        if (cipherData.getOption().equals("-e")) {
            Enciper.chooseCipher(cipherData);
        } else {
            Decipher.chooseCipher(cipherData);
        }
    }
}
