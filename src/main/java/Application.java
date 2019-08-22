import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        List<PasswordEntry> passwordEntries = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Dodaj kolejny folder");
            System.out.println("2. Usun folder");
            System.out.println("3. Wygeneruj losowe hasło");
            System.out.println("4. Wyjdź");

            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    System.out.println("Podaj opis: ");
                    String desc = scanner.nextLine();
                    System.out.println("Podaj login :");
                    String log = scanner.nextLine();
                    System.out.println("Podaj hasło :");
                    String pass1 = scanner.nextLine();
                    System.out.println("Potwierdź");
                    String pass2 = scanner.nextLine();
                    if (!pass1.equals(pass2)) {
                        System.out.println("Hasło są różne");
                        break;
                    }
                    passwordEntries.add(new PasswordEntry(desc, log, pass1));
                    break;
                case "2":
                    System.out.println("Podaj nazwe folderu który chcesz usunąć :");
                    userInput = scanner.nextLine();
                    for (PasswordEntry passwordEntry : passwordEntries) {
                        if (userInput.equals(passwordEntry.getDescription())) {
                            System.out.println("Podaj hasło");
                            String password = scanner.nextLine();
                            if (password.equals(passwordEntry.getPass())) {
                                passwordEntries.remove(passwordEntry);
                                System.out.println("Usunieto !");
                                break;
                            } else {
                                System.out.println("Nieprawidłowe haslo ");
                                break;
                            }
                        }
                    }
                case "3":


            }

        }

    }

    public static void saveToFile() {

        try {
            CSVWriter writer = new CSVWriter(
                    new FileWriter("loginfo.csv"),
                    ';',
                    '"',
                    '\\',
                    "\n"
            );
            // TODO: 22.08.2019 zrobic zapis
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać pliku");
        }
    }

}
