package Interface.MonstersAndHeroes;

import Entity.MonstersAndHeroes.Hero;

/**
 * Hero choice strategy interface
 */
public interface ChoiceStrategy {
    Hero executeChoice(Hero hero);
}
