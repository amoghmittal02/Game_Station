package Entity.MonstersAndHeroes;

import java.util.ArrayList;


public class Inventory {
    private ArrayList<Commodity> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Commodity item) {
        for (Commodity commodity : items){
            if (commodity.getItem().getName().equals(item.getItem().getName())){
                commodity.setNum(commodity.getNum()+item.getNum());
                return;
            }
        }
        this.items.add(item);
    }

    public void removeItem(Commodity item) {
        this.items.remove(item);
    }

    public Commodity getItem(int index) {
        return this.items.get(index);
    }

    public boolean updateItem(Commodity item, int num) {
        for(Commodity commodity : items) {
            if(commodity.getItem().getName().equals(item.getItem().getName())) {
                commodity.setNum(num);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Commodity> getItems() {
        return items;
    }

    public void setItems(ArrayList<Commodity> items) {
        this.items = items;
    }

    public void removeEmptyItem() {
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getNum() <= 0){
                items.remove(i);
            }
        }
    }
}
