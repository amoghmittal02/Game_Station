package Entity.MonstersAndHeroes;

import static Config.MHConfig.ARMOR_DEFENSE_MULTIPLIER;

public class Armor extends Item{
    private int damageReduction;

    public Armor(String name, int price, int level, String type, int damageReduction) {
        super(name, price, level, type);
        this.damageReduction = damageReduction;
    }

    public void equip(Hero hero) // 装备护甲
    {

    }

    @Override
    public void use(Hero hero) {

    }

    public int getDamageReduction() {
        return (int) (damageReduction*ARMOR_DEFENSE_MULTIPLIER);
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    @Override
    public String toString() {
        return "Armor's name='" + name + "'\n" +
                "\ttype='" + type + '\''+
                "\tprice=" + price +
                "\tlevel=" + level +
                "\tdamageReduction=" + damageReduction
                ;
    }
}
