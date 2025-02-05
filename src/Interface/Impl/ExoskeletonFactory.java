package Interface.Impl;

import Entity.MonstersAndHeroes.Exoskeleton;
import Entity.MonstersAndHeroes.Monster;
import Interface.MonstersAndHeroes.MonsterFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExoskeletonFactory implements MonsterFactory {
    @Override
    public Monster createMonster() {
        return null;
    }

    @Override
    public List<Monster> createMonsters(String filePath) {
        List<Monster> exoskeletons = new ArrayList<>();
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

                    Monster exoskeleton = new Exoskeleton(name, level, hp, damage, defense, dodgeChance, "Exoskeleton");
//                    System.out.println(exoskeleton.toString());
                    exoskeletons.add(exoskeleton);
                }
            }
        } catch (IOException e) {
            System.err.println("fail to read exoskeleton input file: " + e.getMessage());
        }
        return exoskeletons;
    }
}
