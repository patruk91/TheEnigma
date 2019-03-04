import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Affine {
    private static int alphabetLength = 26;
    private Scanner reader = new Scanner(System.in);

    public static void encrypt(String sentence) {
        Random random = new Random();
        System.out.println(createListParametersA());
        System.out.println(findAllDivisors());

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

    public int checkFirstKey() {
        int[] functionParameterA = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
        int firstKey = 0;

        boolean keyInFunction = false;
        while (!keyInFunction) {
            System.out.print("Please provide first key to encrypt: ");
            firstKey = Integer.parseInt(reader.nextLine());

        }


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
