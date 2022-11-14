public class Player extends Character {

    public int numAttackUpgrades;
    public int numDefenceUpgrades;

    int gold, restsLeft, pots;

    public String[] attackUpgrades = {"Strength", "Power", "Might", "Godlike Strength"};
    public String[] defenceUpgrades = {"Heavy Bones", "Stone Skin", "Scale Armor", "Holy Aura"};

    public Player(String name) {
        super(name, 100, 0);
        this.numAttackUpgrades = 0;
        this.numDefenceUpgrades = 0;
        this.gold = 5;
        this.restsLeft = 1;
        this.pots = 0;
        //игрок выбирает черту при создании нового персонажа
        chooseTrait();

    }

    @Override
    public int attack() {
        return (int) (Math.random() * (xp / 4 + numAttackUpgrades * 3 + 3) + xp / 10 + numAttackUpgrades * 2 + numDefenceUpgrades + 1);
    }

    @Override
    public int defence() {
        return (int) (Math.random() * (xp / 4 + numDefenceUpgrades * 3 + 3) + xp / 10 + numDefenceUpgrades * 2 + numAttackUpgrades + 1);
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
