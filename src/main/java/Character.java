public abstract class Character {

    public String name;
    public int maxHp, hp, xp;
    public abstract int attack();
    public abstract int defend();

    public Character(String name, int maxHp, int xp) {
        this.name = name;
        this.maxHp = maxHp;
        this.xp = xp;
        this.hp = maxHp;
    }
}
