package Entity.MonstersAndHeroes;

public class Potion extends Item{

    public Potion(String name, int price, int level, String type, String affectedAttribute, int attributeIncrease) {
        super(name, price, level, type);
        this.affectedAttribute = affectedAttribute;
        this.attributeIncrease = attributeIncrease;
    }

    private String affectedAttribute; // 增加的属性类型，如 "HP", "MP"
    private int attributeIncrease;

    public void execute(Hero hero) // 使用药水
    {

    }

    @Override
    public void use(Hero hero) {

    }

    public String getAffectedAttribute() {
        return affectedAttribute;
    }

    public void setAffectedAttribute(String affectedAttribute) {
        this.affectedAttribute = affectedAttribute;
    }

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public void setAttributeIncrease(int attributeIncrease) {
        this.attributeIncrease = attributeIncrease;
    }

    @Override
    public String toString() {
        return "Potion's name='" + name + "'\n" +
                "\ttype='" + type + '\'' +
                "\tprice=" + price +
                "\tlevel=" + level +
                "\taffectedAttribute='" + affectedAttribute + '\'' +
                "\tattributeIncrease=" + attributeIncrease;
    }
}
