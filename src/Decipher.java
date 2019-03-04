public class Decipher {
    private CipherData cipherData;

    public Decipher(CipherData cipherData) {
        this.cipherData = cipherData;
    }



    public void chooseCipher() {
        switch(cipherData.getCipherName()) {
            case "Affine":
                Affine.decrypt(cipherData.getSentence());
                break;

            case "Caesar":
                Caesar.decrypt(cipherData.getSentence());
                break;

            case "ROT13":
                ROT13.decrypt(cipherData.getSentence());
                break;

            case "PlayFair":
                PlayFair.decrypt(cipherData.getSentence());
                break;

            case "Trifid":
                Trifid.decrypt(cipherData.getSentence());
                break;

            case "Vigenere":
                Vigenere.decrypt(cipherData.getSentence());
                break;
        }
    }
}
