import java.util.*;

public class Affine {
    private static int alphabetLength = 26;
    private static Scanner reader = new Scanner(System.in);

    public static void encrypt(String sentence, CipherData cipherData) {
        sentence = sentence.toLowerCase();
        if (!Trifid.isInAlphabet(sentence, cipherData)) {
            sentence = Trifid.askForCorrectSentence(sentence, cipherData).toLowerCase();
        }
        int firstKey = getFirstKey();
        int secondKey = getSecondKey();
        ArrayList<Integer> cParameters = getCParameters(firstKey, secondKey, sentence);
        StringBuilder decryptSentence = decryptOrEncrypt(cParameters);
        System.out.println("Encrypted sentence: " + decryptSentence);
    }

    public static void decrypt(String sentence, CipherData cipherData) {
        sentence = sentence.toLowerCase();
        if (!Trifid.isInAlphabet(sentence, cipherData)) {
            sentence = Trifid.askForCorrectSentence(sentence, cipherData).toLowerCase();
        }
        int firstKey = getFirstKey();
        int secondKey = getSecondKey();
        int multiInverse = multiplicativeInverse(firstKey);
        ArrayList<Integer> PParameters = getPParameters(secondKey, sentence, multiInverse);
        StringBuilder decryptSentence = decryptOrEncrypt(PParameters);
        System.out.println("Decrypted sentence: " + decryptSentence);
    }

    private static ArrayList<Integer> getPParameters(int secondKey, String sentence, int multiInverse) {
        Map<String, Integer> lettersAndNumbers = convertAlphabetToNumbers();
        ArrayList<Integer> decryptedNumbers = new ArrayList<>();

        char[] sentenceCharacters = sentence.replaceAll("\\W", "").toCharArray();
        for (char character : sentenceCharacters) {
            int cParameter = (((multiInverse * (lettersAndNumbers.get("" + character)
                    - secondKey)) % alphabetLength) + alphabetLength) % alphabetLength;
            decryptedNumbers.add(cParameter);
        }
        return decryptedNumbers;

    }

    private static int multiplicativeInverse(int firstKey) {
        firstKey = firstKey % alphabetLength;
        for (int x = 1; x < alphabetLength; x++)
            if ((firstKey * x) % alphabetLength == 1)
                return x;
        return 1;
    }

    private static StringBuilder decryptOrEncrypt(ArrayList<Integer> cParameters) {
        Map<String, Integer> lettersAndNumbers = convertAlphabetToNumbers();
        StringBuilder decryptedString = new StringBuilder();

        for (int number : cParameters) {
            for (Map.Entry<String, Integer> entry : lettersAndNumbers.entrySet()) {
                if (number == entry.getValue()) {
                    decryptedString.append(entry.getKey());
                }
            }
        }
        return decryptedString;
    }

    private static Map<String, Integer> convertAlphabetToNumbers() {
        Map<String, Integer> lettersAndNumbers = new HashMap<>();
        for (int i = 0; i < alphabetLength; i++) {
            String alphabetLetter = Character.toString(97 + i);
            lettersAndNumbers.put(alphabetLetter, i);
        }
        return lettersAndNumbers;
    }

    private static ArrayList<Integer> getCParameters(int firstKey, int secondKey, String sentence) {
        Map<String, Integer> lettersAndNumbers = convertAlphabetToNumbers();
        ArrayList<Integer> decryptedNumbers = new ArrayList<>();

        char[] sentenceCharacters = sentence.replaceAll("\\W", "").toCharArray();
        for (char character : sentenceCharacters) {
            int cParameter = (firstKey * lettersAndNumbers.get("" + character) + secondKey) % alphabetLength;
            decryptedNumbers.add(cParameter);
        }
        return decryptedNumbers;

    }

    private static int getFirstKey() {
        List<Integer> availableParametersA = createListParametersA();

        String delimiter = ",";
        System.out.print("First key need to be in values:");
        String ParametersA = String.join(delimiter, Arrays.asList(availableParametersA.toString()));
        System.out.println(ParametersA);

        String userInput;
        int firstKey = 0;
        boolean keyInAvailableParameters = false;

        while (!keyInAvailableParameters) {
            System.out.print("Please provide first key to encrypt: ");
            userInput = reader.nextLine();

            if (isNumeric(userInput)) {
                firstKey = Integer.parseInt(userInput);
                if (availableParametersA.contains(firstKey)) {
                    keyInAvailableParameters = true;
                }
            }
        }
        return firstKey;
    }

    private static int getSecondKey() {
        List<Integer> availableParametersB = createListParametersB();
        System.out.print("Second key need to be between: 0 - " + (alphabetLength - 1) + "\n");

        String userInput;
        int secondKey = 0;
        boolean keyInAvailableParameters = false;

        while (!keyInAvailableParameters) {
            System.out.print("Please provide second key to encrypt: ");
            userInput = reader.nextLine();

            if (isNumeric(userInput)) {
                secondKey = Integer.parseInt(userInput);
                if (availableParametersB.contains(secondKey)) {
                    keyInAvailableParameters = true;
                }
            }
        }
        return secondKey;
    }

    private static List<Integer> createListParametersA() {
        List<Integer> parametersA = new ArrayList<>();
        List<Integer> divisors = findAllDivisors();

        for (int number = 1; number < alphabetLength; number++) {
            int count = 0;
            for (int divisor : divisors) {
                if (number % divisor == 0) {
                    count++;
                }
            }
            if (count == 0) {
                parametersA.add(number);
            }
        }
        return parametersA;
    }

    private static List<Integer> createListParametersB() {
        List<Integer> parametersB = new ArrayList<>();

        for (int number = 0; number < alphabetLength; number++) {
            parametersB.add(number);
        }
        return parametersB;
    }

    private static List<Integer> findAllDivisors() {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 2; i <= alphabetLength; i++)
            if (alphabetLength % i == 0) {
                divisors.add(i);
            }
        return divisors;
    }

    public static boolean isNumeric(String number) {
        return number != null && number.matches("[-+]?\\d*\\.?\\d+");
    }
}
