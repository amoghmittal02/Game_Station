package Entity.MonstersAndHeroes;

public abstract class Item {
    protected String name;
    protected int price;
    protected int level;
    protected String type;

    public Item(String name, int price, int level, String type) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.type = type;
    }


    public abstract void use(Hero hero); // 使用物品

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Item's name='" + name + "'\n" +
                "\tprice=" + price +
                "\tlevel=" + level +
                "\ttype='" + type + '\'';
    }
}
