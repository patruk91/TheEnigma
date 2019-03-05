public class Enciper {

    public static void chooseCipher(CipherData cipherData) {
        switch(cipherData.getCipherName()) {
            case "Affine":
                 Affine.encrypt(cipherData.getSentence());
                break;

            case "Caesar":
                // Caesar.encrypt(cipherData.getSentence());
                break;

            case "ROT13":
                ROT13.encryptDecrypt(cipherData.getSentence());
                break;

            case "PlayFair":
                // PlayFair.encrypt(cipherData.getSentence());
                break;

            case "Trifid":
                // Trifid.encrypt(cipherData.getSentence());
                break;

            case "Vigenere":
                // Vigenere.encrypt(cipherData);
                break;

        }
    }
}
