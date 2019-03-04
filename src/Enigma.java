import java.util.Scanner;

public class Enigma {
    private static String[] ciphers = {"Affine", "Caesar", "ROT13", "PlayFair", "Trifid", "Vigenere"};

    public static void main(String[] args) {
        if (args.length < 1 || args[0].equals("--help")) {
            handleHelpMenu();
        } else if (args[0].equals("-l")) {
            nameOfCiphers();
        } else if (args[0].equals("-e") || args[0].equals("-d")) {
            if (args.length < 2){
                System.out.println("Options '-e' or '-d' required additional parameter: name of cipher");
            } else {
                Scanner reader = new Scanner(System.in);
                String userInput = "";
                if (checkIfInCiphers(args[1])) {
                    boolean isAnswerCorrect = false;
                    while(!isAnswerCorrect) {
                        System.out.println("Please sentence to encrypt or decrypt: ");
                        userInput = reader.nextLine();
                        isAnswerCorrect = validateInput(userInput);
                    }
                    if (args.length == 3) {
                        CipherData cipherData = new CipherData(args[0], args[1], args[2], userInput);
                        HandleCipher.decideMethodToCryptograph(cipherData);
                    } else {
                        CipherData cipherData = new CipherData(args[0], args[1], userInput);
                        HandleCipher.decideMethodToCryptograph(cipherData);

                    }
                } else {
                    System.out.println("Please provide a correct cipher name");
                }
            }
        }
    }


    private static void nameOfCiphers() {
        System.out.println("Ciphers:");
        for (String cipher : ciphers) {
            System.out.println(cipher);
        }
    }

    private static boolean validateInput(String userInput) {
        return !userInput.isEmpty();
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



}
