import java.util.*;

public class Trifid {

    public static void decrypt(String sentence) {
        System.out.println("Decrypted sentence: " + decryptSentence(5, sentence));
    }

    public static void encrypt(String sentence) {
        System.out.println("Encrypted sentence: " + encryptSentence(5, sentence));
    }

    private static HashMap<String, Integer> calculateTrigrams() {
        HashMap<String, Integer> alphabetNumberTrigrams = new HashMap<>();
        char[] value = "EPSDUCVWYM.ZLKXNBTFGORIJHAQ".toCharArray();

        int numberOfSquares = 3;
        int numberOfRows = 3;
        int numberOfColumns = 3;
        int i = 0;
        for (int square = 1; square <= numberOfSquares; square++) {
            for (int rows = 1; rows <= numberOfRows; rows++) {
                for (int columns = 1; columns <= numberOfColumns; columns++) {
                    int keyToPut = Integer.parseInt("" + square + rows + columns);
                    alphabetNumberTrigrams.put("" + value[i], keyToPut);
                    i++;
                }
            }
        }
        return alphabetNumberTrigrams;
    }

    private static ArrayList<String> getNumbersFromSentence(String sentence) {
        HashMap<String, Integer> alphabetNumberTrigrams = calculateTrigrams();
        ArrayList<String> getNumbersFromSentence = new ArrayList<>();
        System.out.println(sentence);
        char[] sentenceCharacters = sentence.replaceAll("\\s", "").toCharArray();
        System.out.println(sentenceCharacters);

        for (char character : sentenceCharacters) {
            getNumbersFromSentence.add(alphabetNumberTrigrams.get("" + character) + "");
        }

        return getNumbersFromSentence;
    }

    private static ArrayList<Integer> getDecryptedNumbers(int period, String sentence) {
        ArrayList<String> getNumbersFromSentence = getNumbersFromSentence(sentence);
        ArrayList<Integer> getDecryptedNumbers = new ArrayList<>();
        int numberOfLoops = getNumbersFromSentence.size() / period;
        int startSubstring = 0;
        int endSubstring = period;
        Integer[] dupa;

        for (int ij = 0; ij <= numberOfLoops; ij++) {
            List<String> subListGetDecryptedNumbers = getNumbersFromSentence.subList(startSubstring, endSubstring);
            int sizeOfSubList = subListGetDecryptedNumbers.size();

            startSubstring += sizeOfSubList;
            endSubstring += sizeOfSubList;
            if (endSubstring > getNumbersFromSentence.size()) {
                endSubstring = getNumbersFromSentence.size();
            }
//            System.out.println(subListGetDecryptedNumbers.toString());
            int iterator = 0;
            String result = "";
            for (int i = 0; i < 3; i++) {

                int ii = 0;
                while (ii < subListGetDecryptedNumbers.size()) {
                    char[] charNumber = subListGetDecryptedNumbers.get(ii).toCharArray();
                    result += charNumber[iterator];
                    ii++;

                    if (result.length() == 3){
                        getDecryptedNumbers.add(Integer.parseInt(result));
                        result = "";
                    }
                }
                iterator += 1;

            }
        }
        return getDecryptedNumbers;
    }

    private static StringBuilder decryptSentence(int period, String sentence) {
        Map<String, Integer> alphabetNumberTrigrams = calculateTrigrams();
        List<Integer> getDecryptedNumbers = getDecryptedNumbers(period, sentence);
        StringBuilder decryptSentence = new StringBuilder();

        for (int number : getDecryptedNumbers) {
            for (Map.Entry<String, Integer> entry : alphabetNumberTrigrams.entrySet()) {
                if(number == entry.getValue()) {
                    decryptSentence.append(entry.getKey());
                }
            }
        }
        return decryptSentence;
    }

    private static ArrayList<Integer> encryptNumbers(int period, String sentence) {
        ArrayList<String> getNumbersFromSentence = getNumbersFromSentence(sentence);
        ArrayList<Integer> encryptNumbers = new ArrayList<>();

        for (int from = 0; from <= getNumbersFromSentence.size(); from+=5) {
            List<String> subListGetDecryptedNumbers = getNumbersFromSentence.subList(from, period);
            period += 5;
            if (period > getNumbersFromSentence.size()) {
                period = getNumbersFromSentence.size();
            }

            System.out.println(subListGetDecryptedNumbers);
            char[] result = String.join("", subListGetDecryptedNumbers).toCharArray();
            int i = 0;
            int sizeOfArr = subListGetDecryptedNumbers.size();
            while (i != subListGetDecryptedNumbers.size()){
                String value = "" +  result[i] + result[sizeOfArr + i] + result[sizeOfArr * 2 + i];
                encryptNumbers.add(Integer.parseInt(value));
                i++;
            }

        }
        return encryptNumbers;
    }


    private static StringBuilder encryptSentence(int period, String sentence) {
        Map<String, Integer> alphabetNumberTrigrams = calculateTrigrams();
        List<Integer> encryptNumbers = encryptNumbers(period, sentence);
        StringBuilder encryptSentence = new StringBuilder();

        for (int number : encryptNumbers) {
            for (Map.Entry<String, Integer> entry : alphabetNumberTrigrams.entrySet()) {
                if(number == entry.getValue()) {
                    encryptSentence.append(entry.getKey());
                }
            }
        }
        return encryptSentence;
    }
}
