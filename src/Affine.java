import java.util.*;

public class Affine {
    private static int alphabetLength = 26;
    private static Scanner reader = new Scanner(System.in);

    public static void encrypt(String sentence) {
        System.out.println(getFirstKey());
        System.out.println(getSecondKey());

//        System.out.print("Please provide second key to encrypt: ");
//        int secondKey = Integer.parseInt(reader.nextLine());

    }


    private HashMap<String, Integer> convertAlphabetToNumbers() {
        HashMap<String, Integer> lettersAndNumbers = new HashMap<>();
        for (int i = 0; i < alphabetLength; i++) {
            String alphabetLetter = Character.toString(97 + i);
            lettersAndNumbers.put(alphabetLetter, i);
        }
        return lettersAndNumbers;
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

    public static boolean isNumeric(String number) {
        return number != null && number.matches("[-+]?\\d*\\.?\\d+");
    }




}
