public class Enciper {

    public static void chooseCipher(CipherData cipherData) {
        switch(cipherData.getCipherName()) {
            case "Affine":
                 Affine.encrypt(cipherData.getSentence(), cipherData);
                break;

            case "Caesar":
                Caesar.encrypt(cipherData);
                break;

            case "ROT13":
                ROT13.encryptDecrypt(cipherData);
                break;

            case "PlayFair":
                // PlayFair.encrypt(cipherData.getSentence());
                break;

            case "Trifid":
                 Trifid.encrypt(cipherData.getSentence(), cipherData);
                break;

            case "Vigenere":
                Vigenere.encrypt(cipherData);
                break;

        }
    }
}
