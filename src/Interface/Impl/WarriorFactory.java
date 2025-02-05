package Interface.Impl;

import Entity.MonstersAndHeroes.Hero;
import Entity.MonstersAndHeroes.Inventory;
import Entity.MonstersAndHeroes.Warrior;
import Interface.MonstersAndHeroes.HeroFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarriorFactory implements HeroFactory {
    @Override
    public Hero createHero() {
        return null;
    }

    @Override
    public List<Hero> createHeroes(String filePath) {
        List<Hero> warriors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // 跳过标题行

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 9) {
                    String name = parts[0];
                    int mana = Integer.parseInt(parts[1]);
                    int strength = Integer.parseInt(parts[2]);
                    int agility = Integer.parseInt(parts[3]);
                    int dexterity = Integer.parseInt(parts[4]);
                    int money = Integer.parseInt(parts[5]);
                    int experience = Integer.parseInt(parts[6]);
                    int level = Integer.parseInt(parts[7]);
                    int hp = Integer.parseInt(parts[8]);

                    Hero warrior = new Warrior(name, level, hp, mana, strength, dexterity, agility, money, experience, new Inventory(), "Warrior");
//                    System.out.println(warrior.toString());
                    warriors.add(warrior);
                }
            }
        } catch (IOException e) {
            System.err.println("fail to read warrior input file: " + e.getMessage());
        }
        return warriors;    }
}
