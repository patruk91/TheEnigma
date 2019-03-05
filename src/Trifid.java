import java.util.HashMap;

public class Trifid {

    public static void decrypt(String sentence) {
        System.out.println(getNumbersFromSentence(sentence));
    }

    private static HashMap<Integer, String> createAlgorithmBy3Squares() {
        HashMap<Integer, String> squaresCipher = new HashMap<>();
        char[] value = "EPSDUCVWYM.ZLKXNBTFGORIJHAQ".toCharArray();

        int numberOfSquares = 3;
        int numberOfRows = 3;
        int numberOfColumns = 3;
        int i = 0;
        for (int square = 1; square <= numberOfSquares; square++) {
            for (int rows = 1; rows <= numberOfRows; rows++) {
                for (int columns = 1; columns <= numberOfColumns; columns++) {
                    int keyToPut = Integer.parseInt("" + square + rows + columns);
                    squaresCipher.put(keyToPut, "" + value[i]);
                    i++;
                }
            }
        }
        return squaresCipher;
    }




    }


}
