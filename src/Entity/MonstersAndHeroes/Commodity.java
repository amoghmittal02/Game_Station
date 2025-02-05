package Entity.MonstersAndHeroes;

public class Commodity {
    Item item;
    int num;
    public Commodity(Item item, int num)
    {
        this.item = item;
        this.num = num;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return item + "\tnum=" + num;
    }
}
