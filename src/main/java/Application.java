import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        List<PasswordEntry> passwordEntries = new ArrayList<>();
        EntryPass eP = new EntryPass();
        Scanner scanner = new Scanner(System.in);
        String appPass = "";

        //isItNull(appPass, eP);

        while (true) {
            System.out.println("1. Dodaj kolejny folder");
            System.out.println("2. Usun folder");
            System.out.println("3. Pokaz hasła :");
            System.out.println("4. Zaaktualizuj dane");
            System.out.println("5. Wygeneruj losowe hasło");
            System.out.println("6. Wyjdź");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    case1(passwordEntries);
                    break;
                case "2":
                    case2(passwordEntries);
                    break;
                case "3":
                    case3(passwordEntries);
                    break;
                case "4":
                    case4(passwordEntries);
                    break;
                case "5":
                  case5();
                  break;
                case "6":
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

    public static void isItNull(String appPass, EntryPass eP) {
        Scanner scanner = new Scanner(System.in);
        if (appPass == null) {
            System.out.println("Prosze zdefiniowac hasło dla aplikacji");
            String appPass1 = scanner.nextLine();
            System.out.println("Ponowanie podaj swoje hasło");
            String checkAppPass1 = scanner.nextLine();
            if (!appPass1.equalsIgnoreCase(checkAppPass1)) {
                System.out.println("Hasła nie są takie same");
                System.exit(0);
            } else {
                appPass = eP.entPass(checkAppPass1);
            }
        }
        System.out.println("Sprawdzamy haslo: " + appPass);
        System.out.println("Prosze podać hasł do aplikacji");
        String antytThief = scanner.nextLine();

        if (!antytThief.equals(appPass)) {
            System.out.println("Dzwonie po policje!");
            System.exit(0);
        }

    }

    public static void case1(List<PasswordEntry> passwordEntries) {
        Scanner scanner = new Scanner(System.in);
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
        }
        passwordEntries.add(new PasswordEntry(desc, log, pass1));
    }

    public static void case2(List<PasswordEntry> passwordEntries) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe folderu który chcesz usunąć :");
        String userInput = scanner.nextLine();
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
    }

    public static void case3(List<PasswordEntry> passwordEntries) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz hasło :");
        int interator = 1;
        for (PasswordEntry passwordEntry : passwordEntries) {
            System.out.println(interator + " " + passwordEntry.getDescription());
            interator++;
        }
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.println("Login: " + passwordEntries.get(choice - 1).getLog());
        System.out.println("Hasło: " + passwordEntries.get(choice - 1).getPass());
    }

    public static void case4(List<PasswordEntry> passwordEntries) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj które dane chcesz zaaktualizować :");
        for (int i = 0; i < passwordEntries.size(); i++) {
            System.out.println(i + passwordEntries.get(i).getDescription());
        }
        int userInput = Integer.parseInt(scanner.nextLine());
        System.out.println("Podaj stare hasło :");
        String oldPass = scanner.nextLine();
        if (!(oldPass.equals(passwordEntries.get(userInput).getPass()))) {
            System.out.println("Nieprawidłowe hasło");
        } else {
            oldPass = scanner.nextLine();
            passwordEntries.get(userInput).setPass(oldPass);
        }
    }

    public static void case5() {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Podaj liczbę znaków hasła");
        sb = new StringBuilder();
        int howMany = scanner.nextInt();
        for (int i = 0; i < howMany; i++) {
            char c = (char) random.nextInt((93) + 33);
            sb.append(c);
        }
        System.out.println(sb);
        System.out.println("W pyte grubę i mocne hasło!");
    }
}
