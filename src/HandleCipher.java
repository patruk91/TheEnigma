public class HandleCipher {
    CipherData cipherData;

    public HandleCipher(CipherData cipherData) {
        this.cipherData = cipherData;
    }

    public void decideMethodToCryptograph() {
        if (cipherData.getOption().equals("-e")) {
            //encrypt
        } else {
            //decrypt
        }
    }
}
