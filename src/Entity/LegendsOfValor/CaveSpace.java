package Entity.LegendsOfValor;

import Entity.MonstersAndHeroes.Hero;
import Entity.MonstersAndHeroes.Space;
import Interface.LegendOfValor.SpaceExtraIncrease;

import static Config.LOVConfig.SPACE_INCREASE_MULTIPLIER;
import static Entity.Color.BLUE;
import static Entity.Color.PURPLE;

public class CaveSpace extends Space implements SpaceExtraIncrease {
    public CaveSpace() {
        super("C", true, PURPLE);
        this.addAttribute = "agility";
    }

    @Override
    public void interact(String type) {

    }

    @Override
    public void initializeAttributeNum(Hero hero) {
        if (this.addAttribute != null){
            switch (this.addAttribute){
                case "dexterity":
                    this.addNum = (int) (hero.getDexterity() * SPACE_INCREASE_MULTIPLIER);
                    break;
                case "agility":
                    this.addNum = (int) (hero.getAgility() * SPACE_INCREASE_MULTIPLIER);
                    break;
                case "strength":
                    this.addNum = (int) (hero.getStrength() * SPACE_INCREASE_MULTIPLIER);
                    break;
                default:
                    break;
            }
        }
    }
}
