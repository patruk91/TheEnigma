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
}
