import java.util.*;

public class Affine {
    private static int alphabetLength = 26;
    private static Scanner reader = new Scanner(System.in);

    public static void decrypt(String sentence) {
        int firstKey = getFirstKey();
        int secondKey = getSecondKey();
        ArrayList<Integer> cParameters = getCParameters(firstKey, secondKey, sentence);
        StringBuilder decryptSentence = decrypt(cParameters);
        System.out.println("Decrypted sentence: " + decryptSentence);
    }

    public static void encrypt(String sentence) {
        int firstKey = getFirstKey();
        int secondKey = getSecondKey();
        int multiInverse = multiplicativeInverse(firstKey, secondKey);
        ArrayList<Integer> PParameters = getPParameters(firstKey, secondKey, sentence, multiInverse);
    }


    private static ArrayList<Integer> getPParameters(int firstKey, int secondKey, String sentence, int multiInverse) {
        HashMap<String, Integer> lettersAndNumbers = convertAlphabetToNumbers();
        ArrayList<Integer> decryptedNumbers = new ArrayList<>();

        char[] sentenceCharacters = sentence.replaceAll("\\W","").toCharArray();
        for (char character : sentenceCharacters) {
            int cParameter = (firstKey * lettersAndNumbers.get("" + character) + secondKey) % alphabetLength;
            decryptedNumbers.add(cParameter);
        }
        return decryptedNumbers;

    }


    public static int multiplicativeInverse(int firstKey, int secondKey) {
        firstKey = firstKey % secondKey;
        for (int x = 1; x < secondKey; x++)
            if ((firstKey * x) % secondKey == 1)
                return x;
        return 1;
    }



    private static StringBuilder decrypt(ArrayList<Integer> cParameters) {
        Map<String, Integer> lettersAndNumbers = convertAlphabetToNumbers();
        StringBuilder decryptedString = new StringBuilder();

        for (int number : cParameters) {
            for (Map.Entry<String, Integer> entry : lettersAndNumbers.entrySet()) {
                if(number == entry.getValue()) {
                    decryptedString.append(entry.getKey());
                }
            }
        }
        return decryptedString;
    }

    private static HashMap<String, Integer> convertAlphabetToNumbers() {
        HashMap<String, Integer> lettersAndNumbers = new HashMap<>();
        for (int i = 0; i < alphabetLength; i++) {
            String alphabetLetter = Character.toString(97 + i);
            lettersAndNumbers.put(alphabetLetter, i);
        }
        return lettersAndNumbers;
    }

    private static ArrayList<Integer> getCParameters(int firstKey, int secondKey, String sentence) {
        HashMap<String, Integer> lettersAndNumbers = convertAlphabetToNumbers();
        ArrayList<Integer> decryptedNumbers = new ArrayList<>();

        char[] sentenceCharacters = sentence.replaceAll("\\W","").toCharArray();
        for (char character : sentenceCharacters) {
            int cParameter = (firstKey * lettersAndNumbers.get("" + character) + secondKey) % alphabetLength;
            decryptedNumbers.add(cParameter);
        }
        return decryptedNumbers;

    }

    private static int getFirstKey() {
        ArrayList<Integer> functionParameterA = createListParametersA();

        String delimiter = ",";
        System.out.print("First key need to be in values:" );
        String result = String.join(delimiter, Arrays.asList(functionParameterA.toString()));
        System.out.println(result);

        String userInput;
        int firstKey = 0;
        boolean keyInFunction = false;

        while (!keyInFunction) {
            System.out.print("Please provide first key to encrypt: ");
            userInput = reader.nextLine();

            if (isNumeric(userInput)) {
                firstKey = Integer.parseInt(userInput);
                if (functionParameterA.contains(firstKey)) {
                    keyInFunction = true;
                }
            }
        }
        return firstKey;
    }
    private static int getSecondKey() {
        ArrayList<Integer> functionParameterB = createListParametersB();
        System.out.print("Second key need to be between: 0 - " + (alphabetLength - 1) + "\n");

        String userInput;
        int secondKey = 0;
        boolean keyInFunction = false;

        while (!keyInFunction) {
            System.out.print("Please provide first key to encrypt: ");
            userInput = reader.nextLine();

            if (isNumeric(userInput)) {
                secondKey = Integer.parseInt(userInput);
                if (functionParameterB.contains(secondKey)) {
                    keyInFunction = true;
                }
            }
        }
        return secondKey;
    }



    private static ArrayList<Integer> createListParametersA() {
        ArrayList<Integer> parametersA = new ArrayList<>();
        ArrayList<Integer> divisors = findAllDivisors();

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

    private static ArrayList<Integer> createListParametersB() {
        ArrayList<Integer> parametersB = new ArrayList<>();

        for (int number = 0; number < alphabetLength; number++) {
            parametersB.add(number);
        }
        return parametersB;
    }


    private static ArrayList<Integer> findAllDivisors() {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 2; i <= alphabetLength; i++)
            if (alphabetLength % i==0) {
                divisors.add(i);
            }
        return divisors;
    }

    private static boolean isNumeric(String number) {
        return number != null && number.matches("[-+]?\\d*\\.?\\d+");
    }




}
