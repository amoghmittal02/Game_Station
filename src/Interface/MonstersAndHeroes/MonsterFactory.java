package Interface.MonstersAndHeroes;

import Entity.MonstersAndHeroes.Monster;

import java.util.List;

/**
 * Monster factory interface-factory pattern
 */
public interface MonsterFactory {
    public Monster createMonster();

    public List<Monster> createMonsters(String filePath);
}
