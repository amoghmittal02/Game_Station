package Interface.Impl;

import Entity.MonstersAndHeroes.Item;
import Entity.MonstersAndHeroes.Spell;
import Interface.MonstersAndHeroes.ItemFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellFactory implements ItemFactory {
    @Override
    public Item createItem(String itemType) {
        return null;
    }

    @Override
    public List<? extends Item> createItems(String filePath) {
        List<Item> spells = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // 跳过标题行

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 6) {
                    String name = parts[0];
                    int cost = Integer.parseInt(parts[1]);
                    int level = Integer.parseInt(parts[2]);
                    int damage = Integer.parseInt(parts[3]);
                    int manaCost = Integer.parseInt(parts[4]);
                    String spellType = parts[5];

                    Item spell = new Spell(name, cost, level, "Spell", damage, manaCost, spellType);
//                    System.out.println(spell);
                    spells.add(spell);
                }
            }
        } catch (IOException e) {
            System.err.println("fail to read spell input file: " + e.getMessage());
        }
        return spells;
    }
}
