import java.util.Scanner;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);

    static Player player;
    public static boolean isRunning;

    public static String[] places = {"Бесконечные горы", "Близлежайшие леса", "Замок Императора", "Тронный зал"};
    public static String[] encounters = {"Battle", "Battle", "Battle", "Rest", "Rest"};
    public static String[] enemies = {"Ogre", "Ogre", "Goblin", "Goblin", "Stone Elemental"};

    public static int act = 1;
    public static int place = 0;

    public static int readInt(String prompt, int userChoices) {    //метод получения пользовательского ввода из консоли
        int input;
        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception ex) {
                input = -1;
                System.out.println("Вы ввели некорректное значение, пожалуйста введите число");
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

    public static void clearConsole() {    //метод имитации очистки консоли
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static void printSeparator(int n) {    //метод печати разделителя
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printHeading(String title) {    //метод печати заголовка
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    public static void anythingToContinue() {    //останавливаем игру до тех пор, пока пользователь не введет что-нить
        System.out.println("\nВведите что-нибудь, что бы продолжить...");
        scanner.next();
    }

    public static void startGame() {
        boolean nameSet = false;
        String name;

        clearConsole();
        printSeparator(40);
        printSeparator(30);
        System.out.println("Age of the Evil Emperor");
        printSeparator(30);
        printSeparator(40);
        anythingToContinue();

        do {
            clearConsole();
            printHeading("Как зовут вашего персонажа?");
            name = scanner.next();
            clearConsole();
            printHeading("Вашего персонажа зовут " + name + ".\nВы уверены в своем выборе?");
            System.out.println("(1) Да");
            System.out.println("(2) Нет, я хочу изменить имя");
            int input = readInt("-> ", 2);
            if (input == 1) {
                nameSet = true;
            }
        } while (!nameSet);

        Story.printIntro();
        player = new Player(name);    //создане нового персонажа с заданым именем
        Story.printFirstActIntro();
        isRunning = true;             //isRunning должен быть равен true, чтобы игровой цикл мог работать
        gameLoop();                   //запуск основного игрового цикла
    }

    private static void gameLoop() {  //основной игровой цикл
        while (isRunning) {
            printMenu();
            int input = readInt("-> ", 3);

            if (input == 1)
                continueJourney();
            else if (input == 2)
                characterInfo();
            else
                isRunning = false;
        }
    }

    private static void printMenu() {
        clearConsole();
        printHeading(places[place]);
        System.out.println("Выберите действие");
        printSeparator(20);
        System.out.println("(1) Продолжить путешествие");
        System.out.println("(2) Информация о персонаже");
        System.out.println("(3) Выйти из игры");

    }

    private static void continueJourney() {
        checkAct();
        if (act != 4) {
            randomEncounter();
        }
    }

    private static void checkAct() {
        if (player.xp >= 10 && act == 1) {
            act = 2;
            place = 1;
            Story.printFirstActOutro();
            player.chooseTrait();        //персонаж апнул лвл
            Story.printSecondActIntro();
        } else if (player.xp >= 50 && act == 2) {
            act = 3;
            place = 2;
            Story.printSecondActOutro();
            player.chooseTrait();        //персонаж апнул лвл
            Story.printThirdActIntro();
        } else if (player.xp >= 100 && act == 3) {
            act = 4;
            place = 3;
            Story.printThirdActOutro();
            player.chooseTrait();        //персонаж апнул лвл
            Story.printFourthActIntro();
        }
    }

    private static void characterInfo() {
        clearConsole();
        printHeading("Информация о персонаже");
        System.out.println(player.name + "\tHP: " + player.hp + "/" + player.maxHp);
        printSeparator(20);
        System.out.println("XP: " + player.xp);
        printSeparator(20);

        if (player.numAttackUpgrades > 0) {
            System.out.println("Атакующие перки: " + player.attackUpgrades[player.numAttackUpgrades - 1]);
        }
        if (player.numDefenceUpgrades > 0) {
            System.out.println("Защитные перки: " + player.defenceUpgrades[player.numDefenceUpgrades - 1]);
        }
    }
}
