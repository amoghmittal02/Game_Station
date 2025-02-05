package Interface.Impl;

import Entity.MonstersAndHeroes.*;
import Interface.MonstersAndHeroes.ChoiceStrategy;
import Rule.InputRule;

import java.util.ArrayList;
import java.util.HashMap;

import static Entity.Color.*;

public class EquipWeapon implements ChoiceStrategy {

    @Override
    public Hero executeChoice(Hero hero) {
        int weaponHandsRequired = 0;
        for (int i = 0; i < hero.getWeapon().size(); i++) {
            weaponHandsRequired += hero.getWeapon().get(i).getHandsRequired();
        }
        if (hero.getWeapon().size() == 2){
            System.out.println(YELLOW+"You don't have enough hands to equip more weapons! Please drop some of them!"+RESET);
        }else if(hero.getWeapon().size() == 1){
            System.out.println(YELLOW+"You can only equip weapons that need one hand, otherwise you need to drop the old one."+RESET);
        }
        // list all weapons in hero's inventory
        int num = 1;
        HashMap<Integer, Item> weapons = new HashMap<>();
        System.out.println(BLUE+"Now the hero has these weapons in the inventory"+RESET);
        for (Commodity commodity : hero.getInventory().getItems()){
            if (commodity.getItem().getType().equals("Weapon")){
                Item item = commodity.getItem();
                System.out.println(num + ". " + item + " num:"+commodity.getNum());
                weapons.put(num, item);
                num++;
            }
        }
        if (hero.getWeapon() != null && hero.getWeapon().size() != 0){
            System.out.println(BLUE+"Here are weapons the hero equipped now"+RESET);
            int numOfWeaponNow = 1;
            for (Weapon weapon : hero.getWeapon()){
                System.out.println((numOfWeaponNow++)+". "+weapon);
            }
            while (true){
                System.out.println(BLUE+"Choose all weapons you want to drop. Split by space. Enter 0 to pass this part."+RESET);
                ArrayList<Integer> dropNum = null;
                int flag = 0;
                while (true){
                    dropNum = InputRule.InputNumbers(0, numOfWeaponNow-1);
                    if (dropNum == null){
                        System.out.println("Please input again");
                    }else {
                        if (dropNum.contains(0)){
                            flag = 1;
                            break;
                        }else {
                            for (int i = 0; i < dropNum.size(); i++){
                                Weapon weaponNow = hero.getWeapon().get(dropNum.get(i)-1);
                                for (int j = 0; j < hero.getWeapon().size(); j++) {
                                    if (weaponNow.getName().equals(hero.getWeapon().get(j).getName())){
                                        // put weapon back to the inventory
                                        hero.putItemInInventory(weaponNow, 1);
                                        hero.getWeapon().remove(j);
                                    }
                                }
                            }
                            flag = 1;
                            break;
                        }
                    }
                }
                if (flag==1){
                    break;
                }
            }

        }
        if (hero.getWeapon().size() > 0){
            System.out.println(BLUE+"After dropping, the hero has these weapon left"+RESET);
            for (Weapon weapon : hero.getWeapon()){
                System.out.println(weapon);
            }
        }else{
            System.out.println(YELLOW+"The hero has no weapon now!"+RESET);
        }
        weaponHandsRequired = 0;
        for (int i = 0; i < hero.getWeapon().size(); i++) {
            weaponHandsRequired += hero.getWeapon().get(i).getHandsRequired();
        }
        System.out.println(BLUE+"Choose an weapon to equip, input 0 to quit."+RESET);
        num = 1;
        for (Commodity commodity : hero.getInventory().getItems()){
            if (commodity.getItem().getType().equals("Weapon")){
                Item item = commodity.getItem();
                System.out.println(num + ". " + item + " num:"+commodity.getNum());
                weapons.put(num, item);
                num++;
            }
        }
        int index = -1;
        int quitFlag = 0;
        while (true){
            index = InputRule.InputInteger(0, num-1);
            if (index == -1){
                System.out.println(YELLOW+"Invalid input, please try again"+RESET);
            }
            else if(index == 0){
                quitFlag = 1;
                break;
            }else{
                Weapon addWeapon = (Weapon) weapons.get(index);
                if(weaponHandsRequired + addWeapon.getHandsRequired() > 2){
                    System.out.println(YELLOW+"You don't have enough hands to equip this weapon! Please input another weapon!"+RESET);
                }else{
                    break;
                }
            }
        }
        if(quitFlag == 1){
            return hero;
        }
        Item item = weapons.get(index);

        hero.addWeapon((Weapon) item);
        // reduce the item in hero's inventory
        hero.reduceItemInInventory(item);
        System.out.println(BLUE+"Now the hero equip these weapons"+RESET);
        for (Weapon weapon : hero.getWeapon()){
            System.out.println(weapon);
        }
        return hero;
    }
}
