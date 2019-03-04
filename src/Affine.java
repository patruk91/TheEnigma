import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Affine {
    private static int alphabetLength = 26;
    private static Scanner reader = new Scanner(System.in);

    public static void encrypt(String sentence) {
        System.out.println(checkFirstKey());

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

    public static int checkFirstKey() {
        ArrayList<Integer> functionParameterA = createListParametersA();

        int firstKey = 0;
        boolean keyInFunction = false;


        System.out.print("The key need to be in values:" );
        for (int number : functionParameterA) {
            System.out.print(number + ", ");
        }
        System.out.println();

        while (!keyInFunction) {

            System.out.print("Please provide first key to encrypt: ");
            firstKey = Integer.parseInt(reader.nextLine());
            if (functionParameterA.contains(firstKey)) {
                keyInFunction = true;
            }
        }
        return firstKey;
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


    private static ArrayList<Integer> findAllDivisors() {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 2; i <= alphabetLength; i++)
            if (alphabetLength % i==0) {
                divisors.add(i);
            }
        return divisors;
    }




}
