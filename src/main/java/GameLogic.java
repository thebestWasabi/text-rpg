import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);

    //метод получения пользовательского ввода из консоли
    public static int readInt(String prompt, int userChoices) {
        int input;
        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception ex) {
                input = -1;
                System.out.println("Вы ввели некорректное значение, пожалуйста введите число");
            }
        }while (input < 1 || input > userChoices);
        return input;
    }

    //метод имитации очистки консоли
    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    //метод печати разделителя
    public static void printSeparator(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    //метод печати заголовка
    public static void printHeading(String title) {
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    //метод останавливающий игру до тех пор, пока пользователь не введет что-нить
    public static void anythingToContinue() {
        System.out.println("\nВведите что-нибудь, что бы продолжить...");
        scanner.next();
    }
}
