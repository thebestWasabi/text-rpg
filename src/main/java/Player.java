public class Player extends Character {

    public int numAttackUpgrades;
    public int numDefenceUpgrades;

    public String[] attackUpgrades = {"Strength", "Power", "Might", "Godlike Strength"};
    public String[] defenceUpgrades = {"Heavy Bones", "Stone Skin", "Scale Armor", "Holy Aura"};

    public Player(String name) {
        super(name, 100, 0);
        this.numAttackUpgrades = 0;
        this.numDefenceUpgrades = 0;
        //игрок выбирает черту при создании нового персонажа
        chooseTrait();

    }

    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int defend() {
        return 0;
    }

    //позвольте игроку выбрать черту
    public void chooseTrait() {
        GameLogic.clearConsole();
        GameLogic.printHeading("Выберите черту характера:");
        System.out.println("(1) " + attackUpgrades[numAttackUpgrades]);
        System.out.println("(2) " + defenceUpgrades[numDefenceUpgrades]);
        //пусть игрок выбирает
        int input = GameLogic.readInt("-> ", 2);
        GameLogic.clearConsole();

        if (input == 1) {
            GameLogic.printHeading("Ваш выбор " + attackUpgrades[numAttackUpgrades]);
            numAttackUpgrades++;
        } else {
            GameLogic.printHeading("Ваш выбор " + defenceUpgrades[numDefenceUpgrades]);
            numDefenceUpgrades++;
        }
        GameLogic.anythingToContinue();
    }
}
