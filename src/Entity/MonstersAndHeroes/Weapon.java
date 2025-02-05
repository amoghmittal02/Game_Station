package Entity.MonstersAndHeroes;

public class Weapon extends Item{

    public Weapon(String name, int price, int level, String type, int damage, int handsRequired) {
        super(name, price, level, type);
        this.damage = damage;
        this.handsRequired = handsRequired;
    }

    private int damage;
    private int handsRequired;

    public void equip(Hero hero) // 装备武器
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

    public int getHandsRequired() {
        return handsRequired;
    }

    public void setHandsRequired(int handsRequired) {
        this.handsRequired = handsRequired;
    }

    @Override
    public String toString() {
        return "Weapon's name='" + name + "'\n" +
                "\ttype='" + type + '\'' +
                "\tprice=" + price +
                "\tlevel=" + level +
                "\tdamage=" + damage +
                "\thandsRequired=" + handsRequired;
    }
}
