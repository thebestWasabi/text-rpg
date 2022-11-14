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
            System.out.print(prompt);
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

    public static void gameLoop() {  //основной игровой цикл
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

    public static void printMenu() {
        clearConsole();
        printHeading(places[place]);
        System.out.println("Выберите действие");
        printSeparator(20);
        System.out.println("(1) Продолжить путешествие");
        System.out.println("(2) Информация о персонаже");
        System.out.println("(3) Выйти из игры");

    }

    public static void continueJourney() {
        checkAct();
        if (act != 4) {
            randomEncounter();
        }
    }

    public static void randomEncounter() {
        int encounter = (int) (Math.random() * encounters.length);
        if (encounters[encounter].equals("Battle")) {
            randomBattle();
        } else if (encounters[encounter].equals("Rest")) {
            takeRest();
        } else {
            shop();
        }
    }

    public static void randomBattle() {
        clearConsole();
        printHeading("Вы столкнулись с врагом, придется с ним сразится");
        anythingToContinue();
        battle(new Enemy(enemies[(int) (Math.random() * enemies.length)], player.xp));
    }

    public static void battle(Enemy enemy) {
        while (true) {
            clearConsole();
            printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHp);
            printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHp);
            System.out.println("Выберите действие");
            printSeparator(20);

            System.out.println("(1) Атаковать \n(2) Использовать зелье \n(3) Сбежать");
            int input = readInt("->", 3);
            if (input == 1) {
                int damage = player.attack() - enemy.defence();
                int damageTook = enemy.attack() - player.defence();
                if (damageTook < 0) {
                    damage -= damageTook / 2;
                    damageTook = 0;
                }
                if (damage < 0) {
                    damage = 0;
                }
                player.hp -= damageTook;
                enemy.hp -= damage;

                clearConsole();
                printHeading("Battle");
                System.out.println("Вы нанесли " + damage + " ед урона персонажу " + enemy.name);
                printSeparator(15);
                System.out.println(enemy.name + " нанес " + damageTook + " ед урона персонажу " + player.name);
                anythingToContinue();

                if (player.hp <= 0) {
                    playerDied();
                    break;
                } else if (enemy.hp <= 0) {
                    clearConsole();
                    printHeading("Вы убили персонажа " + enemy.name);
                    player.xp += enemy.xp;
                    System.out.println("Вы получили " + enemy.xp + " ед опыта");
                    boolean addRest = (Math.random() * 5 + 1 <= 2.25);
                    if (addRest) {
                        player.restsLeft++;
                        System.out.println("Вы получили один комплект для привала");
                    }
                    int goldEarned = (int) (Math.random() * enemy.xp);
                    if (goldEarned > 0) {
                        player.gold += goldEarned;
                        System.out.println("Вы забрали " + goldEarned + " ед золота с безжизненого тела " + enemy.name);
                    }
                    anythingToContinue();
                    break;
                }
            } else if (input == 2) {
                clearConsole();
                if (player.pots > 0 && player.hp < player.maxHp) {
                    printHeading("Вы хотите выпить целебное зелье (осталось " + player.pots);
                    System.out.println("(1) Да \n(2) Нет");
                    input = readInt("->", 2);
                    if (input == 1) {
                        player.hp += 50;
                        clearConsole();
                        printHeading("Вы выпили целебное зелье " + player.hp + "/" + player.maxHp);
                        anythingToContinue();
                    }
                } else if (player.pots <= 0) {
                    printHeading("У вас закончились целебные зелья");
                    anythingToContinue();
                } else {
                    printHeading("У вас полное здоровье");
                    anythingToContinue();
                }
            } else {
                clearConsole();
                if (Math.random() * 10 + 1 <= 3.5) {
                    printHeading("У вас получилось убежать от персонажа " + enemy.name);
                    anythingToContinue();
                    break;
                } else {
                    printHeading("Вам не удалось убежать от персонажа " + enemy.name);
                    int damageTook = enemy.attack();
                    System.out.println("В спешке вы получили " + damageTook + " ед урона");
                    anythingToContinue();
                    if (player.hp <= 0) {
                        playerDied();
                    }
                }
            }
        }
    }

    public static void playerDied() {
        clearConsole();
        printHeading("Вас убили...");
        printHeading("Вы заработали " + player.xp + " ед опыта. Попробуйте заработать больше в следущий раз");
        isRunning = false;
    }

    public static void checkAct() {
        if (player.xp >= 10 && act == 1) {
            act = 2;
            place = 1;
            Story.printFirstActOutro();
            player.chooseTrait();        //персонаж апнул лвл
            Story.printSecondActIntro();
            enemies[0] = "Марадер";
            enemies[1] = "Гоблин";
            enemies[2] = "Волчья стая";
            enemies[3] = "Приспешник Императора";
            enemies[4] = "Скелет";
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
            player.hp = player.maxHp;
        } else if (player.xp >= 50 && act == 2) {
            act = 3;
            place = 2;
            Story.printSecondActOutro();
            player.chooseTrait();        //персонаж апнул лвл
            Story.printThirdActIntro();
            enemies[0] = "Марадер";
            enemies[1] = "Марадер";
            enemies[2] = "Приспешник Императора";
            enemies[3] = "Приспешник Императора";
            enemies[4] = "Приспешник Императора";
            encounters[0] = "Battle";
            encounters[1] = "Battle";
            encounters[2] = "Battle";
            encounters[3] = "Rest";
            encounters[4] = "Shop";
            player.hp = player.maxHp;
        } else if (player.xp >= 100 && act == 3) {
            act = 4;
            place = 3;
            Story.printThirdActOutro();
            player.chooseTrait();        //персонаж апнул лвл
            Story.printFourthActIntro();
            player.hp = player.maxHp;
//            finalBattle();
        }
    }

    public static void characterInfo() {
        clearConsole();
        printHeading("Информация о персонаже");
        System.out.println(player.name + "\tHP: " + player.hp + "/" + player.maxHp);
        printSeparator(20);
        System.out.println("XP: " + player.xp + "\tЗолото: " + player.gold);
        printSeparator(20);
        System.out.println("Количество целебных зелий: " + player.pots);
        printSeparator(20);
        System.out.println("Колмчество комплектов для привала: " + player.restsLeft);
        printSeparator(20);

        if (player.numAttackUpgrades > 0) {
            System.out.println("Атакующие перки: " + player.attackUpgrades[player.numAttackUpgrades - 1]);
        }
        if (player.numDefenceUpgrades > 0) {
            System.out.println("Защитные перки: " + player.defenceUpgrades[player.numDefenceUpgrades - 1]);
        }
        anythingToContinue();
    }

    public static void shop() {
        clearConsole();
        printHeading("Вы встретили странствующего торговца");

        int price = (int) (Math.random() * (10 + player.pots * 2) + 10 + player.pots);
        System.out.println("Лечебное зелье: " + price + " ед золота");
        printSeparator(20);

        System.out.println("Хотите купить? \n (1) Да \n(2) Нет");
        int input = readInt("->", 2);
        if (input == 1) {
            clearConsole();
            if (player.gold >= price) {
                player.gold -= price;
                player.pots++;
                printHeading("Вы купили одно лечебное зелье за " + price + " ед золота");
            } else {
                printHeading("У вас не достаточно золота");
                anythingToContinue();
            }
        }
    }

    public  static void takeRest() {
        clearConsole();

        if (player.restsLeft >= 1) {
            printHeading("Вы хотите использовать комплект для привала? (осталось " + player.restsLeft + " шт)");
            System.out.println("(1) Да \n(2) Нет");
            int input = readInt("-> ", 2);
            if (input == 1) {
                clearConsole();
                if (player.hp < player.maxHp) {
                    int hpRestored = (int) (Math.random() * player.xp / 4 + 1) + 10;
                    player.hp += hpRestored;
                    if (player.hp > player.maxHp) {
                        player.hp = player.maxHp;
                    }
                    player.restsLeft--;
                    System.out.println("Вы отдохнули и востоновили " + hpRestored);
                    System.out.println("HP: " + player.hp + "/" + player.maxHp);
                }
            } else {
                System.out.println("У вас полное здоровье, вам не нужен отдых");
            }
            anythingToContinue();
        }
    }
}
