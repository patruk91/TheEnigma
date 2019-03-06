import java.util.Scanner;

public class EnigmaEngine {
    private static String[] ciphers = {"Affine", "Caesar", "ROT13", "PlayFair", "Trifid", "Vigenere"};

    public static void startEnigmaEngine(String[] args) {
        if (checkCommandLineArguments(args)) {
            constructCipherData(args);
        }
    }

    private static boolean checkCommandLineArguments(String[] args) {
        boolean result = false;
        if (args.length < 1 || args[0].equals("--help")) {
            handleHelpMenu();
        } else if (args[1].equals("Caesar") || args[1].equals("Vigenere")) {
            if (args.length > 2) {
                result = true;
            } else {
                System.out.println("Enter KEY after name of cipher");
                result = false;
            };
        } else if (args[0].equals("-l")) {
            nameOfCiphers();
        } else if (args[0].equals("-e") || args[0].equals("-d")) {
            if (args.length < 2){
                System.out.println("Options '-e' or '-d' required additional parameter: name of cipher");
            } else {
                if (checkIfInCiphers(args[1])) {
                    result = true;
                } else {
                    System.out.println("Please provide a correct cipher name");
                    result = false;
                }
            }
        }
        return result;
    }

    private static void constructCipherData(String[] args) {
        String userSentenceToProcess = getSentenceFromUser();
            if (args.length == 3) {
                CipherData cipherData = new CipherData(args[0], args[1], args[2], userSentenceToProcess);
                HandleCipher.decideMethodToCryptograph(cipherData);
            } else {
                CipherData cipherData = new CipherData(args[0], args[1], userSentenceToProcess);
                HandleCipher.decideMethodToCryptograph(cipherData);

            }
    }
    private static void nameOfCiphers() {
        System.out.println("Ciphers:");
        for (String cipher : ciphers) {
            System.out.println(cipher);
        }
    }

    private static boolean validateInput(String userSentenceToProcess) {
        return !userSentenceToProcess.isEmpty();
    }

    private static boolean checkIfInCiphers(String cipherName) {
        for (String cipher : ciphers ) {
            if (cipher.equals(cipherName)) {
                return true;
            }
        }
        return false;
    }

    private static void handleHelpMenu() {
        System.out.println("Enigma syntax: Enigma OPTION CIPHERNAME [KEY]");
        System.out.println("'--help': display this help");
        System.out.println("OPTION: '-e': encrypt; '-d': decrypt; '-l' show available ciphers");
        System.out.println("CIPHERNAME: 'Affine', 'Caesar', 'ROT13', 'PlayFair', 'Trifid', 'Vigenere'");
        System.out.println("KEY: Optional key to be use with the cipher");
    }

    private static String getSentenceFromUser() {
        Scanner reader = new Scanner(System.in);
        String userSentenceToProcess = "";
        boolean isAnswerCorrect = false;
            while(!isAnswerCorrect) {
                System.out.println("Please sentence to encrypt or decrypt: ");
                userSentenceToProcess = reader.nextLine();
                isAnswerCorrect = validateInput(userSentenceToProcess);
            }
        return userSentenceToProcess;
    }

}