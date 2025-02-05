package Interface.Impl;

import Entity.MonstersAndHeroes.Spirit;
import Entity.MonstersAndHeroes.Monster;
import Interface.MonstersAndHeroes.MonsterFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpiritFactory implements MonsterFactory {
    @Override
    public Monster createMonster() {
        return null;
    }

    @Override
    public List<Monster> createMonsters(String filePath) {
        List<Monster> spirits = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // 跳过标题行

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 6) {
                    String name = parts[0];
                    int level = Integer.parseInt(parts[1]);
                    int damage = Integer.parseInt(parts[2]);
                    int defense = Integer.parseInt(parts[3]);
                    int dodgeChance = Integer.parseInt(parts[4]);
                    int hp = Integer.parseInt(parts[5]);

                    Monster spirit = new Spirit(name, level, hp, damage, defense, dodgeChance, "Spirit");
//                    System.out.println(spirit.toString());
                    spirits.add(spirit);
                }
            }
        } catch (IOException e) {
            System.err.println("fail to read spirit input file: " + e.getMessage());
        }
        return spirits;
    }
}
