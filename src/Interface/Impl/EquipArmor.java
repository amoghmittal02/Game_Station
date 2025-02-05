package Interface.Impl;

import Entity.MonstersAndHeroes.*;
import Interface.MonstersAndHeroes.ChoiceStrategy;
import Rule.InputRule;

import java.util.HashMap;

import static Entity.Color.*;

public class EquipArmor implements ChoiceStrategy {

    @Override
    public Hero executeChoice(Hero hero) {
        // list all armor in hero's inventory
        int num = 1;
        HashMap<Integer, Item> armory = new HashMap<>();
        for (Commodity commodity : hero.getInventory().getItems()){
            if (commodity.getItem().getType().equals("Armor")){
                Item item = commodity.getItem();
                System.out.println(num + ". " + item+" num:"+commodity.getNum());
                armory.put(num, item);
                num++;
            }
        }
        System.out.println(BLUE+"Choose an armor to equip, int 0 to quit."+RESET);
        int index = -1;
        int quitFlag = 0;
        while (true){
            index = InputRule.InputInteger(1, num-1);
            if (index == -1){
                System.out.println(YELLOW+"Invalid input, please try again"+RESET);
            }
            else if(index == 0){
                quitFlag = 1;
                break;
            }else{
                break;
            }

        }
        if (quitFlag == 1) {
            return hero;
        }
        Item item = armory.get(index);
        hero.putItemInInventory(hero.getArmor(), 1);
        hero.setArmor((Armor) item);
        // reduce the item in hero's inventory
        hero.reduceItemInInventory(item);
        return hero;
    }
}
