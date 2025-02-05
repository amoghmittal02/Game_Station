package Interface.MonstersAndHeroes;

import Entity.MonstersAndHeroes.Hero;

import java.util.List;

/**
 * Hero factory interface-factory pattern
 */
public interface HeroFactory {

    public Hero createHero();

    public List<Hero> createHeroes(String filePath);
}
