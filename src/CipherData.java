import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return this.sentence = askForCorrectSentence(sentence);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    private List<String> createAlphabet() {
        int alphabetLength = 26;
        List<String> alphabet = new ArrayList<>();
        for (int i = 0; i < alphabetLength; i++) {
            String alphabetLetter = Character.toString(97 + i);
            alphabet.add(alphabetLetter);
        }
        if (getCipherName().equals("Trifid")) {
            alphabet.add(".");
        }
        return alphabet;
    }

    private boolean isInAlphabet(String sentence) {
        List<String> alphabet = createAlphabet();
        char[] charOfSentence = sentence.toCharArray();
        for (char character : charOfSentence) {
            if (!alphabet.contains(character+"")) {
                return false;
            }
        }
        return true;
    }

    private String askForCorrectSentence(String sentence) {
        List<String> alphabet = createAlphabet();


        while (!isInAlphabet(sentence.toLowerCase())) {

            String delimiter = ",";
            String alphabetAsCharacters = String.join(delimiter, Arrays.asList(alphabet.toString()));
            System.out.print("Letters in sentence need to be in following characters: " + alphabetAsCharacters + "\n");
            sentence = EnigmaEngine.getSentenceFromUser();
        }
        return sentence.toLowerCase();
    }
}
