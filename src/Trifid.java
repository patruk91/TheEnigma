import java.util.ArrayList;
import java.util.HashMap;

public class Trifid {

    public static void decrypt(String sentence) {
        System.out.println(createAlgorithmBy3Squares());
        System.out.println(getNumbersFromSentence(sentence));
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

    private static ArrayList<Integer> getNumbersFromSentence(String sentence) {
        HashMap<String, Integer> squaresCipher = createAlgorithmBy3Squares();
        ArrayList<Integer> getDecryptedNumbers = new ArrayList<>();
        System.out.println(sentence);
        char[] sentenceCharacters = sentence.replaceAll("\\s", "").toCharArray();
        System.out.println(sentenceCharacters);

        for (char character : sentenceCharacters) {
            getDecryptedNumbers.add(squaresCipher.get("" + character));
        }

        return getDecryptedNumbers;
    }



}
