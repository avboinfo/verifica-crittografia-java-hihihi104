import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CriptazioneFile {

    public static String cesareEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                result.append((char) ((((ch - base + shift) % 26 + 26) % 26) + base));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String cesareDecrypt(String text, int shift) {
        return cesareEncrypt(text, 26 - shift);
    }

    public static void main(String[] args) {
        try {
            File file = new File("static/data.txt");
            Scanner scanner = new Scanner(file);
            StringBuilder contents = new StringBuilder();
            while (scanner.hasNextLine()) {
                contents.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            System.out.println("Menu:");
            System.out.println("1. Cripta il contenuto");
            System.out.println("2. Decripta il contenuto");
            System.out.println("3. Stampa il contenuto");
            System.out.print("Scelta: ");
            Scanner inputScanner = new Scanner(System.in);
            int choice = inputScanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Inserire lo shift per la cifratura di Cesare: ");
                    int shift = inputScanner.nextInt();
                    String encrypted = cesareEncrypt(contents.toString(), shift);
                    FileWriter writerEncrypt = new FileWriter(file);
                    writerEncrypt.write(encrypted);
                    writerEncrypt.close();
                    System.out.println("Contenuto criptato con successo.");
                    break;
                case 2:
                    System.out.print("Inserire lo shift per la decifratura di Cesare: ");
                    int decryptShift = inputScanner.nextInt();
                    String decrypted = cesareDecrypt(contents.toString(), decryptShift);
                    FileWriter writerDecrypt = new FileWriter(file);
                    writerDecrypt.write(decrypted);
                    writerDecrypt.close();
                    System.out.println("Contenuto decriptato con successo.");
                    break;
                case 3:
                    System.out.println("Contenuto del file:");
                    System.out.println(contents.toString());
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }

            inputScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato.");
        } catch (IOException e) {
            System.out.println("Errore di I/O.");
        }
    }
}