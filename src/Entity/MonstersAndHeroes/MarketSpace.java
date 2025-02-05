package Entity.MonstersAndHeroes;

import Controller.LOVController;
import Controller.MHController;
import Rule.InputRule;

import java.util.ArrayList;

import static Entity.Color.*;

public class MarketSpace extends Space{
    private Market market;


    public MarketSpace() {
        super("M", true, BLUE);
    }

    @Override
    public void interact(String type) {
        if(type.equals("MH")){
            System.out.println(BLUE+"You are in the market. Input B/b to but items, input S/s to sell items, input Q/q to quit, input I/i to print heroes' information."+RESET);
            while (true){
                char input = InputRule.ChooseActionByCharacter(new char[]{'B', 'b', 'S', 's', 'Q', 'q', 'I', 'i'});
                if (input == 'B' || input == 'b'){
                    buyItem();
                    break;
                } else if (input == 'S' || input == 's') {
                    sellItem();
                    break;
                } else if (input == 'Q' || input == 'q') {
                    break;
                }else if(input == 'I' || input == 'i'){
                    int num = 1;
                    ArrayList<Hero> heroes = MHController.getPlayerHeroes();
                    for (int i = 0; i < heroes.size(); i++) {
                        System.out.println((num++)+". "+heroes.get(i));
                        heroes.get(i).printInventory();
                    }
                }else {
                    System.out.println(YELLOW+"Invalid input, please input again!"+RESET);
                }
            }
        }else if(type.equals("LOV")){
            System.out.println(BLUE+"You are in the market. Input B/b to but items, input S/s to sell items, input Q/q to quit, input I/i to print hero's information."+RESET);
            Hero hero = LOVController.getPlayerHeroes().get(LOVController.getCurrentHeroIndex());
            while (true){
                char input = InputRule.ChooseActionByCharacter(new char[]{'B', 'b', 'S', 's', 'Q', 'q', 'I', 'i'});
                if (input == 'B' || input == 'b'){
                    buyItemForOneHero(hero);
                    break;
                } else if (input == 'S' || input == 's') {
                    if(!sellItemForOneHero(hero)) {
                        System.out.println(YELLOW+"Please input another choice!"+RESET);
                        continue;
                    }
                    break;
                } else if (input == 'Q' || input == 'q') {
                    break;
                }else if(input == 'I' || input == 'i'){
                    System.out.println(hero);
                    hero.printInventory();
                }else {
                    System.out.println(YELLOW+"Invalid input, please input again!"+RESET);
                }
            }
        }



    }

    private void sellItem() {
        System.out.println(BLUE+"Choose a hero to sell related items. Input 0 to quit."+RESET);
        for (int i = 0; i < MHController.getPlayerHeroes().size(); i++) {
            System.out.println(i+1+". "+MHController.getPlayerHeroes().get(i).toString());
            MHController.getPlayerHeroes().get(i).printInventory();
        }
        while (true){
            int num = InputRule.InputInteger(0, MHController.getPlayerHeroes().size());
            if(num == 0){
                return;
            }else if (num != -1){
                if (MHController.getPlayerHeroes().get(num-1).getInventory().getItems().size() == 0){
                    System.out.println(YELLOW+"The hero has no items to sell. Please input another hero. Input 0 to quit."+RESET);
                    continue;
                }
                Hero updatedHero = market.sellItemToMarket(MHController.getPlayerHeroes().get(num-1));
                MHController.getPlayerHeroes().remove(num-1);
                MHController.getPlayerHeroes().add(num-1, updatedHero);
                break;
            }else{
                System.out.println(YELLOW+"Invalid input, please input again!"+RESET);
            }
        }
    }

    public void buyItem(){
        System.out.println(BLUE+"The market has these items!"+RESET);
        market.printAllCommodities();
        System.out.println(BLUE+"Choose a hero to purchase related items. Input 0 to quit."+RESET);
        for (int i = 0; i < MHController.getPlayerHeroes().size(); i++) {
            System.out.println(i+1+". "+MHController.getPlayerHeroes().get(i).toString());
            MHController.getPlayerHeroes().get(i).printInventory();
        }
        while (true){
            int num = InputRule.InputInteger(0, MHController.getPlayerHeroes().size());
            if (num == 0){
                return;
            }
            else if (num != -1){
                int heroLevel = MHController.getPlayerHeroes().get(num-1).getLevel();
                Hero updatedHero = market.sellItemToPlayer(MHController.getPlayerHeroes().get(num-1));
                MHController.getPlayerHeroes().set(num-1, updatedHero);
                break;
            }else{
                System.out.println(YELLOW+"Invalid input, please input again!"+RESET);
            }
        }
    }

    public void buyItemForOneHero(Hero hero){
        System.out.println(BLUE+"The market has these items!"+RESET);
//        market.printAllCommodities();
        while (true){
            market.sellItemToPlayer(hero);
            break;
        }
    }

    private boolean sellItemForOneHero(Hero hero) {
        while (true){
            if (hero.getInventory().getItems().size() == 0){
                System.out.println(YELLOW+"The hero has no items to sell."+RESET);
                return false;
            }
            Hero updatedHero = market.sellItemToMarket(hero);
            break;
        }
        return true;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
