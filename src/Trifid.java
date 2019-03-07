import java.util.*;

public class Trifid {

    public static void decrypt(String sentence, CipherData cipherData) {
        sentence = sentence.toUpperCase();
        if (!isInAlphabet(sentence, cipherData)) {
            sentence = askForCorrectSentence(sentence, cipherData);
        }

        int period = getPeriodFromUser(sentence);
        System.out.println("Decrypted sentence: " + decryptSentence(period, sentence));
    }

    public static void encrypt(String sentence, CipherData cipherData) {
        sentence = sentence.toUpperCase();
        if (!isInAlphabet(sentence, cipherData)) {
            sentence = askForCorrectSentence(sentence, cipherData);
        }

        int period = getPeriodFromUser(sentence);
        System.out.println("Encrypted sentence: " + encryptSentence(period, sentence));
    }

    private static Map<String, Integer> calculateTrigrams() {
        Map<String, Integer> alphabetNumberTrigrams = new HashMap<>();
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

    private static List<String> getNumbersFromAlphabet(String sentence) {
        Map<String, Integer> alphabetNumberTrigrams = calculateTrigrams();
        ArrayList<String> getNumbersFromAlphabet = new ArrayList<>();
        char[] sentenceCharacters = sentence.replaceAll("\\s", "").toCharArray();

        for (char character : sentenceCharacters) {
            getNumbersFromAlphabet.add(alphabetNumberTrigrams.get("" + character) + "");
        }
        return getNumbersFromAlphabet;
    }

    private static List<Integer> encryptSentenceToNumbers(int period, String sentence) {
        List<String> getNumbersFromAlphabet = getNumbersFromAlphabet(sentence);
        List<Integer> encryptSentenceToNumbers = new ArrayList<>();
        int lengthOfEncryptWord = getNumbersFromAlphabet.size();
        //without whitespaces

        int startSubstring = 0;
        int endOfSublist = 0;
        int[] getIntervals = getIntervals(lengthOfEncryptWord ,period);
        for (int interval = 0; interval < getIntervals.length; interval++) {
            List<String> sublistEncryptedNumbers = getNumbersFromAlphabet.subList(
                    startSubstring, endOfSublist+=getIntervals[interval]);
            startSubstring += getIntervals[interval];


            int actualSublist = 0;
            String encryptNumber = "";
            int lengthOfNumber = 3;
            for (int number = 0; number < lengthOfNumber; number++) {

                int elementFromSublist = 0;
                while (elementFromSublist < sublistEncryptedNumbers.size()) {
                    char[] charNumber = sublistEncryptedNumbers.get(elementFromSublist).toCharArray();
                    encryptNumber += charNumber[actualSublist];
                    elementFromSublist++;

                    if (encryptNumber.length() == 3){
                        encryptSentenceToNumbers.add(Integer.parseInt(encryptNumber));
                        encryptNumber = "";
                    }
                }
                actualSublist += 1;
            }
        }
        return encryptSentenceToNumbers;
    }

    private static int[] getIntervals(int lengthOfSentence, int period) {
        int intervals = (int)Math.ceil(lengthOfSentence / (double)period);

        int[] getIntervals = new int[intervals];
        for (int num = 0; num < getIntervals.length; num++) {
            if (num != getIntervals.length-1) {
                getIntervals[num] = period;
            } else {
                getIntervals[num] = lengthOfSentence % period;
            }
        }
        return getIntervals;
    }

    private static StringBuilder encryptSentence(int period, String sentence) {
        Map<String, Integer> alphabetNumberTrigrams = calculateTrigrams();
        List<Integer> encryptNumbers = encryptSentenceToNumbers(period, sentence);
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

    private static List<Integer> decryptNumbers(int period, String sentence) {
        List<String> getNumbersFromAlphabet = getNumbersFromAlphabet(sentence);
        List<Integer> decryptNumbers = new ArrayList<>();
        int lengthOfDecryptWord = getNumbersFromAlphabet.size();
        //without whitespaces

        int startSubstring = 0;
        int endOfSublist = 0;
        int[] getIntervals = getIntervals(lengthOfDecryptWord ,period);

        for (int interval = 0; interval < getIntervals.length; interval++) {
            List<String> sublistDecryptedNumbers = getNumbersFromAlphabet.subList(
                    startSubstring, endOfSublist+=getIntervals[interval]);
            startSubstring += getIntervals[interval];

            char[] result = String.join("", sublistDecryptedNumbers).toCharArray();
            int i = 0; //element of sublist
            int sizeOfSublist = sublistDecryptedNumbers.size();

            while (i != sizeOfSublist){
                String value = "" +  result[i] + result[sizeOfSublist + i] + result[sizeOfSublist * 2 + i];
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


    private static int getPeriodFromUser(String sentence) {
        Scanner reader = new Scanner(System.in);

        String userInput;
        int secondKey = 0;
        boolean correctKey = false;
        sentence = sentence.replaceAll("\\s", "");
        while (!correctKey) {
            System.out.print("Please provide period to encode/decode sentence"
                    + " (range between: 5 - 20, excluding: " + sentence.length() +"): ");
            userInput = reader.nextLine();

            if (Affine.isNumeric(userInput)) {
                secondKey = Integer.parseInt(userInput);
                if (5 <= secondKey && secondKey <= 20 && secondKey != sentence.length()) {
                    correctKey = true;
                }
            }
        }
        return secondKey;
    }

    public static List<String> createAlphabet(CipherData cipherData) {
        int alphabetLength = 26;
        List<String> alphabet = new ArrayList<>();
        for (int i = 0; i < alphabetLength; i++) {
            String alphabetLetter = Character.toString(97 + i);
            alphabet.add(alphabetLetter);
        }
        if (cipherData.getCipherName().equals("Trifid")) {
            alphabet.add(".");

        }
            alphabet.add(" ");

        return alphabet;
    }

    public static boolean isInAlphabet(String sentence, CipherData cipherData) {
        List<String> alphabet = createAlphabet(cipherData);
        char[] charOfSentence = sentence.toCharArray();
        for (char character : charOfSentence) {
            if (!alphabet.contains(character+"")) {
                return false;
            }
        }
        return true;
    }

    public static String askForCorrectSentence(String sentence, CipherData cipherData) {
        List<String> alphabet = createAlphabet(cipherData);

        while (!isInAlphabet(sentence.toLowerCase(), cipherData)) {
            String alphabetAsCharacters = String.join("", alphabet);
            System.out.print("Letters in sentence need to be in following characters: " + alphabetAsCharacters + "\n");
            sentence = EnigmaEngine.getSentenceFromUser();
        }
        return sentence.toUpperCase();
    }
}
