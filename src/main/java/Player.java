public class Player extends Character {

    public int numAttackUpgrades;
    public int numDefenceUpgrades;

    public String[] attackUpgrades = {"Strength", "Power", "Might", "Godlike Strength"};
    public String[] defenceUpgrade = {"Heavy Bones", "Stone Skin", "Scale Armor", "Holy Aura"};

    public Player(String name) {
        super(name, 100, 0);
        this.numAttackUpgrades = 0;
        this.numDefenceUpgrades = 0;
    }

    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int defend() {
        return 0;
    }
}
