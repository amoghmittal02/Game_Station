package Interface.Impl;

import Entity.MonstersAndHeroes.*;
import Interface.MonstersAndHeroes.ChoiceStrategy;
import Rule.InputRule;

import java.util.HashMap;

import static Entity.Color.*;

public class EquipSpell implements ChoiceStrategy {

    @Override
    public Hero executeChoice(Hero hero) {
        // list all spells in hero's inventory
        int num = 1;
        HashMap<Integer, Item> spells = new HashMap<>();
        for (Commodity commodity : hero.getInventory().getItems()){
            if (commodity.getItem().getType().equals("Spell")){
                Item item = commodity.getItem();
                System.out.println(num + ". " + item+" num:"+commodity.getNum());
                spells.put(num, item);
                num++;
            }
        }
        System.out.println(BLUE+"Choose an spell to equip, input 0 to quit."+RESET);
        int index = -1;
        int quitFlag = -1;
        while (true){
            index = InputRule.InputInteger(0, num-1);
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
        if (quitFlag == 1){
            return hero;
        }

        Item item = spells.get(index);
        hero.putItemInInventory(hero.getSpell(), 1);
        hero.setSpell((Spell) item);
        // reduce the item in hero's inventory
        hero.reduceItemInInventory(item);
        return hero;
    }
}
