package Entity.MonstersAndHeroes;

import Rule.InputRule;
import java.util.List;

import static Config.MHConfig.SELL_GOLD_MULTIPLIER;
import static Entity.Color.*;

public class Market {
    List<Commodity> commodities;

    public Market(List<Commodity> commodities)
    {
        this.commodities = commodities;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    public void printAllCommodities(){
        int i = 0;
        for(Commodity commodity:commodities){
            Item item = null;
            switch (commodity.getItem().getType()) {
                case "Weapon":
                    item = (Weapon) commodity.getItem();
                    break;
                case "Armor":
                    item = (Armor) commodity.getItem();
                    break;
                case "Potion":
                    item = (Potion) commodity.getItem();
                    break;
                case "Spell":
                    item = (Spell) commodity.getItem();
                    break;
            }
            System.out.println((++i)+". "+item+"\tnum: "+commodity.getNum());

        }
    }

    public Hero sellItemToPlayer(Hero hero) {

        printAllCommodities();
        System.out.println(BLUE+"Please choose the item you want to purchase. Input 0 to quit."+RESET);
        while (true) {
            int choice = InputRule.InputInteger(0, commodities.size());
            if(choice == 0){
                return hero;
            }
            else if(choice != -1){
                if (commodities.get(choice-1).getItem().getLevel()>hero.getLevel()){
                    System.out.println(YELLOW+"You don't have enough level to buy it! Please input another num."+ RESET);
                    continue;
                }
                int index = choice-1;
                Commodity commodity = commodities.get(index);
                Item item = commodity.getItem();
                int num = commodity.getNum();
                // check if the hero has enough gold
                if(hero.getGold() < item.getPrice()){
                    System.out.println(YELLOW+"You don't have enough gold! Please input another num."+ RESET);
                    continue;
                }
                System.out.println(BLUE+"Please input the number you want to purchase. Input 0 to quit."+RESET);
                while (true){
                    int numPurchase = InputRule.InputInteger(0, num);
                    if (numPurchase == 0){
                        return hero;
                    }else if(numPurchase != -1){
                        if(numPurchase <= num){
                            // check if the hero has enough gold to buy such num of items
                            System.out.println("hero gold: "+ hero.getGold());
                            System.out.println("total cost: "+ item.getPrice()*numPurchase);
                            if(hero.getGold() < item.getPrice()*numPurchase){
                                System.out.println(YELLOW+"You don't have enough gold! Please input another num. Input 0 to quit."+ RESET);
                                continue;
                            }
                            num -= numPurchase;
                            commodity.setNum(num);
                            hero.setGold(hero.getGold()-item.getPrice()*numPurchase);
                            hero.addItemToInventory(item, numPurchase);
                            System.out.println(GREEN+"Purchase successfully!"+ RESET);
                            System.out.println(GREEN+"Hero has "+hero.getGold()+" gold left."+ RESET);
                            break;
                        }
                    }else{
                        System.out.println(YELLOW+"Invalid input! Please input again."+RESET);
                    }
                }
                break;
            }else{
                System.out.println(YELLOW+"Invalid input! Please input again."+RESET);
            }
        }
        // remove commodities
        for (int i = 0; i < commodities.size(); i++) {
            if (commodities.get(i).getNum() <= 0){
                commodities.remove(i);
            }
        }
        return hero;
    }

    public Hero sellItemToMarket(Hero hero) {
        System.out.println(YELLOW+"You can only get part of the sell price"+ RESET);
        printAllInventory(hero);
        System.out.println(BLUE+"Input Q/q to quit, input C/c to continue"+RESET);
        while (true){
            char input = InputRule.ChooseActionByCharacter(new char[]{'C', 'c', 'Q', 'q'});
            if (input == 'Q' || input == 'q'){
                return hero;
            }else if (input == 'C' || input == 'c'){
                break;
            }else{
                System.out.println(YELLOW+"Invalid input, please input again!"+RESET);
            }
        }
        System.out.println(BLUE+"Please choose the item you want to sell. Input 0 to quit"+RESET);
        printAllInventory(hero);
        while (true) {
            int choice = InputRule.InputInteger(0, hero.getInventory().getItems().size());
            if(choice == 0){
                return hero;
            }
            else if(choice != -1){
                int index = choice-1;
                Commodity commodity = hero.getInventory().getItems().get(index);
                Item item = commodity.getItem();
                int num = commodity.getNum();
                System.out.println(BLUE+"Please input the number you want to sell:"+RESET);
                while (true){
                    int numSell = InputRule.InputInteger(1, num);
                    if(numSell != -1){
                        if(numSell <= num){
                            // check if the hero has enough num of items
                            num -= numSell;
                            commodity.setNum(num);
                            hero.setGold((int) (hero.getGold()+ item.getPrice()*numSell*SELL_GOLD_MULTIPLIER));
                            hero.updateItemInInventory(commodity);
                            System.out.println(GREEN+"Sell successfully!"+ RESET);
                            System.out.println(GREEN+"The hero have "+hero.getGold()+" gold now."+ RESET);
                            break;
                        }
                    }
                }
                break;
            }else {
                System.out.println(YELLOW+"Invalid input! Please input again."+RESET);
            }
        }
        // remove commodities
        for (int i = 0; i < hero.getInventory().getItems().size(); i++) {
            if (hero.getInventory().getItems().get(i).getNum() <= 0){
                hero.getInventory().getItems().remove(i);
            }
        }
        return hero;
    }

    private void printAllInventory(Hero hero) {

        System.out.println("Hero inventory:");
        hero.printInventory();
    }



}
