package Entity.LegendsOfValor;

import Entity.MonstersAndHeroes.Hero;
import Entity.MonstersAndHeroes.Space;
import Interface.LegendOfValor.SpaceExtraIncrease;

import static Entity.Color.RED;

public class ObstacleSpace extends Space{
    public ObstacleSpace() {
        super("O", true, RED);
    }

    @Override
    public void interact(String type) {

    }

}
