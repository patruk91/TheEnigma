import java.util.*;

public class Trifid {

    public static void decrypt(String sentence) {
        System.out.println("Decrypted sentence: " + decryptSentence(5, sentence));
    }

    public static void encrypt(String sentence) {
        System.out.println(encryptSentenceToNumbers(5, sentence));
        System.out.println("Encrypted sentence: " + encryptSentence(5, sentence));
    }

    private static HashMap<String, Integer> calculateTrigrams() {
        HashMap<String, Integer> alphabetNumberTrigrams = new HashMap<>();
        char[] alphabetEncryption = "EPSDUCVWYM.ZLKXNBTFGORIJHAQ".toCharArray();

        int numberOfSquares = 3;
        int numberOfRows = 3;
        int numberOfColumns = 3;
        int getNextLetter = 0;
        for (int square = 1; square <= numberOfSquares; square++) {
            for (int rows = 1; rows <= numberOfRows; rows++) {
                for (int columns = 1; columns <= numberOfColumns; columns++) {
                    int number = Integer.parseInt("" + square + rows + columns);
                    alphabetNumberTrigrams.put("" + alphabetEncryption[getNextLetter], number);
                    getNextLetter++;
                }
            }
        }
        return alphabetNumberTrigrams;
    }

    private static ArrayList<String> getNumbersFromAlphabet(String sentence) {
        HashMap<String, Integer> alphabetNumberTrigrams = calculateTrigrams();
        ArrayList<String> getNumbersFromAlphabet = new ArrayList<>();
        char[] sentenceCharacters = sentence.replaceAll("\\s", "").toCharArray();

        for (char character : sentenceCharacters) {
            getNumbersFromAlphabet.add(alphabetNumberTrigrams.get("" + character) + "");
        }
        return getNumbersFromAlphabet;
    }

    private static ArrayList<Integer> encryptSentenceToNumbers(int period, String sentence) {
        ArrayList<String> getNumbersFromAlphabet = getNumbersFromAlphabet(sentence);
        ArrayList<Integer> encryptSentenceToNumbers = new ArrayList<>();
        int lengthOfEncryptWord = getNumbersFromAlphabet.size();
        //without whitespaces

        int startSubstring = 0;
        int endOfSublist = 0;
        int[] getIntervals = getIntervals(lengthOfEncryptWord ,period);
        for (int interval = 0; interval < getIntervals.length; interval++) {
            List<String> sublistEncryptedNumbers = getNumbersFromAlphabet.subList(startSubstring, endOfSublist+=getIntervals[interval]);
            startSubstring += getIntervals[interval];

            int iterator = 0;
            String result = "";
            for (int i = 0; i < 3; i++) {

                int ii = 0;
                while (ii < sublistEncryptedNumbers.size()) {
                    char[] charNumber = sublistEncryptedNumbers.get(ii).toCharArray();
                    result += charNumber[iterator];
                    ii++;

                    if (result.length() == 3){
                        encryptSentenceToNumbers.add(Integer.parseInt(result));
                        result = "";
                    }
                }
                iterator += 1;

            }
        }
        return encryptSentenceToNumbers;
    }

    private static int[] getIntervals(int lengthOfEncryptWord, int period) {
        int encryptIntervals = (int)Math.ceil(lengthOfEncryptWord / (double)period);

        int[] getIntervals = new int[encryptIntervals];
        for (int num = 0; num < getIntervals.length; num++) {
            if (num != getIntervals.length-1) {
                getIntervals[num] = period;
            } else {
                getIntervals[num] = lengthOfEncryptWord % period;
            }
        }
        return getIntervals;
    }

    private static StringBuilder encryptSentence(int period, String sentence) {
        Map<String, Integer> alphabetNumberTrigrams = calculateTrigrams();
        List<Integer> encryptSentenceToNumbers = encryptSentenceToNumbers(period, sentence);
        StringBuilder decryptSentence = new StringBuilder();

        for (int number : encryptSentenceToNumbers) {
            for (Map.Entry<String, Integer> entry : alphabetNumberTrigrams.entrySet()) {
                if(number == entry.getValue()) {
                    decryptSentence.append(entry.getKey());
                }
            }
        }
        return decryptSentence;
    }

    private static ArrayList<Integer> decryptNumbers(int period, String sentence) {
        ArrayList<String> getNumbersFromAlphabet = getNumbersFromAlphabet(sentence);
        ArrayList<Integer> decryptNumbers = new ArrayList<>();

        for (int from = 0; from <= getNumbersFromAlphabet.size(); from+=5) {
            List<String> sublistEncryptedNumbers = getNumbersFromAlphabet.subList(from, period);
            period += 5;
            if (period > getNumbersFromAlphabet.size()) {
                period = getNumbersFromAlphabet.size();
            }

            System.out.println(sublistEncryptedNumbers);
            char[] result = String.join("", sublistEncryptedNumbers).toCharArray();
            int i = 0;
            int sizeOfArr = sublistEncryptedNumbers.size();
            while (i != sublistEncryptedNumbers.size()){
                String value = "" +  result[i] + result[sizeOfArr + i] + result[sizeOfArr * 2 + i];
                decryptNumbers.add(Integer.parseInt(value));
                i++;
            }

        }
        return decryptNumbers;
    }


    private static StringBuilder decryptSentence(int period, String sentence) {
        Map<String, Integer> alphabetNumberTrigrams = calculateTrigrams();
        List<Integer> decryptNumbers = decryptNumbers(period, sentence);
        StringBuilder decryptSentence = new StringBuilder();

        for (int number : decryptNumbers) {
            for (Map.Entry<String, Integer> entry : alphabetNumberTrigrams.entrySet()) {
                if(number == entry.getValue()) {
                    decryptSentence.append(entry.getKey());
                }
            }
        }
        return decryptSentence;
    }
}
