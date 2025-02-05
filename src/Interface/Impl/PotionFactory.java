package Interface.Impl;

import Entity.MonstersAndHeroes.Item;
import Entity.MonstersAndHeroes.Potion;
import Interface.MonstersAndHeroes.ItemFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PotionFactory implements ItemFactory {
    @Override
    public Item createItem(String itemType) {
        return null;
    }

    @Override
    public List<? extends Item> createItems(String filePath) {
        List<Item> potions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // 跳过标题行

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 5) {
                    String name = parts[0];
                    int cost = Integer.parseInt(parts[1]);
                    int level = Integer.parseInt(parts[2]);
                    int attributeIncrease = Integer.parseInt(parts[3]);
                    String affectedAttribute = parts[4];

                    Item potion = new Potion(name, cost, level, "Potion", affectedAttribute, attributeIncrease);
//                    System.out.println(potion);
                    potions.add(potion);
                }
            }
        } catch (IOException e) {
            System.err.println("fail to read potion input file: " + e.getMessage());
        }
        return potions;
    }
}
