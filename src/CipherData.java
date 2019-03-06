public class CipherData {
    private String option;
    private String cipherName;
    private String key;
    private String sentence;


    public CipherData(String option, String cipherName, String key, String sentence) {
        this.option = option;
        this.cipherName = cipherName;
        this.key = key;
        this.sentence = sentence;
    }

    public CipherData(String option, String cipherName, String sentence) {
        this.option = option;
        this.cipherName = cipherName;
        this.key = "";
        this.sentence = sentence;
    }

    public String getOption() {
        return option;
    }

    public String getCipherName() {
        return cipherName;
    }

    public String getKey() {
        return key;
    }

    public String getSentence() {
        return sentence;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void prepareSentence() {
        if (this.cipherName.equals("Trifid")) {
            this.sentence = this.sentence.toUpperCase().replaceAll("[^a-zA-Z]+", "");
        }
        else {
            this.sentence = this.sentence.toUpperCase().replaceAll("[^a-zA-Z]+", "");
        }
    }

    public void prepareKeyForVigenere() {
        int keyLength = this.key.length();
        int sentenceLength = this.sentence.length();
        if (keyLength > sentenceLength) {
            this.key = (this.key.substring(0, sentenceLength)).toUpperCase();
        }
        else {
            int multiplying = sentenceLength / keyLength;
            int remainder = sentenceLength % keyLength;
            this.key = (this.key.repeat(multiplying) + this.key.substring(0, remainder)).toUpperCase();
        }
    }
}
