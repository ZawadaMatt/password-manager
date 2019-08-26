import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        List<PasswordEntry> passwordEntries = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        String appPass = "haslo";

        String checkAppPass2;

//        if (appPass == null) {
//            System.out.println("Prosze zdefiniowac hasło dla aplikacji");
//            String appPass1 = scanner.nextLine();
//            System.out.println("Ponowanie podaj swoje hasło");
//            String checkAppPass1 = scanner.nextLine();
//            if (!appPass1.equalsIgnoreCase(checkAppPass1)) {
//                System.out.println("Hasła nie są takie same");
//                System.exit(0);
//            } else {
//                checkAppPass1 = appPass; //Tu jest problem.
//            }
//
//        }

        System.out.println("Sprawdzamy haslo: " + appPass);
        System.out.println("Prosze podać hasł do aplikacji");
        String antytThief = scanner.nextLine();

        if (!antytThief.equals(appPass)) {
            System.out.println("Dzwonie po policje!");
            System.exit(0);
        }

        while (true) {
            System.out.println("1. Dodaj kolejny folder");
            System.out.println("2. Usun folder");
            System.out.println("3. Pokaz hasła :");
            System.out.println("4. Wygeneruj losowe hasło");
            System.out.println("5. Wyjdź");

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
                    System.out.println("Wybierz hasło :");
                    int interator = 1;
                    for (PasswordEntry passwordEntry : passwordEntries) {
                        System.out.println(interator + " " + passwordEntry.getDescription());
                        interator++;
                    }
                    int choice = Integer.parseInt(scanner.nextLine());
                    System.out.println("Login: " + passwordEntries.get(choice - 1).getLog());
                    System.out.println("Hasło: " + passwordEntries.get(choice - 1).getPass());
                    break;

                case "4":
                    System.out.println("Podaj liczbę znaków hasła");
                    sb = new StringBuilder();
                    int howMany = scanner.nextInt();
                    for (int i = 0; i < howMany; i++) {
                        char c = (char) random.nextInt((93) + 33);
                        sb.append(c);
                    }
                    System.out.println(sb);
                    System.out.println("W pyte grubę i mocne hasło!");

                case "5":
                    System.out.println("Do widzenia");
                    try {
                        saveToFile(passwordEntries, appPass);
                    } catch (IOException e) {
                        System.out.println("Nie udało się zapisać do pliku !");
                    }
                    System.exit(0);
            }

        }

    }

    public static void saveToFile(List<PasswordEntry> passwordEntries, String appPass) throws IOException {

        CSVWriter writer = new CSVWriter(new FileWriter("logs.csv"),
                ':',
                '"',
                '\\',
                "\n");

        //writer.writeNext(appPass.split(";"));

        writer.writeAll(passwordEntries.stream()
                .map(pass -> new String[]{pass.getDescription(), pass.getLog(), pass.getPass()})
                .collect(Collectors.toList()));
        writer.close();
    }

    public static List<PasswordEntry> readingFromFile() throws FileNotFoundException {

        return null;
    }

}
