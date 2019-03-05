import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Trifid {

    public static void decrypt(String sentence) {
//        System.out.println(createAlgorithmBy3Squares());
//        System.out.println(getNumbersFromSentence(sentence));
        System.out.println(getDeryptedNumbers(5, sentence));
    }

    private static HashMap<String, Integer> createAlgorithmBy3Squares() {
        HashMap<String, Integer> squaresCipher = new HashMap<>();
        char[] value = "EPSDUCVWYM.ZLKXNBTFGORIJHAQ".toCharArray();

        int numberOfSquares = 3;
        int numberOfRows = 3;
        int numberOfColumns = 3;
        int i = 0;
        for (int square = 1; square <= numberOfSquares; square++) {
            for (int rows = 1; rows <= numberOfRows; rows++) {
                for (int columns = 1; columns <= numberOfColumns; columns++) {
                    int keyToPut = Integer.parseInt("" + square + rows + columns);
                    squaresCipher.put("" + value[i], keyToPut);
                    i++;
                }
            }
        }
        return squaresCipher;
    }

    private static ArrayList<String> getNumbersFromSentence(String sentence) {
        HashMap<String, Integer> squaresCipher = createAlgorithmBy3Squares();
        ArrayList<String> getNumbersFromSentence = new ArrayList<>();
        System.out.println(sentence);
        char[] sentenceCharacters = sentence.replaceAll("\\s", "").toCharArray();
        System.out.println(sentenceCharacters);

        for (char character : sentenceCharacters) {
            getNumbersFromSentence.add(squaresCipher.get("" + character) + "");
        }

        return getNumbersFromSentence;
    }

    private static ArrayList<String> getDeryptedNumbers(int period, String sentence) {
        ArrayList<String> getNumbersFromSentence = getNumbersFromSentence(sentence);
        ArrayList<String> getDecryptedNumbers = new ArrayList<>();

        for (int from = 0; from <= getNumbersFromSentence.size(); from+=5) {
            List<String> subListGetDecryptedNumbers = getNumbersFromSentence.subList(from, period);
            period += 5;
            if (period > getNumbersFromSentence.size()) {
                period = getNumbersFromSentence.size();
            }
//            System.out.println(subListGetDecryptedNumbers.toString());
            int iterator = 0;
            String result = "";
            for (int i = 0; i < 3; i++) {

                int ii = 0;
                while (ii < subListGetDecryptedNumbers.size()) {
                    char[] charNumber = subListGetDecryptedNumbers.get(ii).toCharArray();
                    if (result.length() != 3) {
                        result += charNumber[iterator];
                        ii++;
                    }
                    if (result.length() == 3){
                        getDecryptedNumbers.add(result);
                        result = "";
                    }
                }
                iterator += 1;
//                if (result.length() == 3) {
//                    getDecryptedNumbers.add(result);
//                }
            }
        }
        return getDecryptedNumbers;
    }




}
