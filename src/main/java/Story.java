public class Story {

    public static void printIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("Сюжет");
        GameLogic.printSeparator(30);
        System.out.println("Вы последний человек, оставшийся в живых после того, как на вашу деревню напали приспешники злого императора");
        System.out.println("Все до единого твои друзья, родственники и соседи были убиты. Вы стоите в горящих руинах этого некогда прерасного города");
        System.out.println("Все, чего вы сейчас хотите, это мести, поэтому вы начинаете свое путешествие, чтобы победить злого императора и освободить землю");
        GameLogic.anythingToContinue();
    }

    public static void printFirstActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("Глава 1 - Вступление");
        GameLogic.printSeparator(30);
        System.out.println("Когда вы начинаете свое путешествие, вы начинаете путешествовать по близлежащим лесам, чтобы достичь вечных гор");
        System.out.println("Вечные горы - очень опасное место. Говорят, что оттуда никто не вернулся живым");
        System.out.println("\nПосле долгого дня ходьбы по лесу вы, наконец, достигаете вечных гор");
        System.out.println("Для вас не имеет значения опасны эти горы или нет. Для вас важна только месть");
        GameLogic.anythingToContinue();
    }

    public static void printFirstActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        System.out.println("Глава 1 - Конец");
        GameLogic.printSeparator(30);
        System.out.println("Ты сделал это! Ты пересек вечные горы и остался жив");
        System.out.println("Спускаясь с последнего холма, вы более чем счастливы снова почувствовать твердую почву под ногами");
        System.out.println("Вы чувствуете себя наделенным силой, и приобретенный вами опыт позволяет вам усвоить еще одну черту характера");
        GameLogic.anythingToContinue();
    }

    public static void printSecondActIntro() {

    }

    public static void printSecondActOutro() {

    }

    public static void printThirdActIntro() {

    }

    public static void printThirdActOutro() {

    }

    public static void printFourthActIntro() {

    }
}
