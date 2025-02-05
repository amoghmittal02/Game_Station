package Entity.MonstersAndHeroes;

public class Spell extends Item{
    public Spell(String name, int price, int level, String type, int damage, int manaCost, String spellType) {
        super(name, price, level, type);
        this.damage = damage;
        this.manaCost = manaCost;
        this.spellType = spellType;
    }

    private int damage;
    private int manaCost;
    private String spellType; // 法术类型，如 "Ice", "Fire", "Lightning"

    public void cast(Hero hero, Monster target) // 施放法术
    {

    }

    @Override
    public void use(Hero hero) {

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getSpellType() {
        return spellType;
    }

    public void setSpellType(String spellType) {
        this.spellType = spellType;
    }

    @Override
    public String toString() {
        return "Spell's name='" + name + "'\n" +
                "\ttype='" + type + '\'' +
                "\tprice=" + price +
                "\tlevel=" + level +
                "\tdamage=" + damage +
                "\tmanaCost=" + manaCost +
                "\tspellType='" + spellType + '\'';
    }
}
