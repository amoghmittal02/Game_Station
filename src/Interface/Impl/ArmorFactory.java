package Interface.Impl;

import Entity.MonstersAndHeroes.Armor;
import Entity.MonstersAndHeroes.Item;
import Interface.MonstersAndHeroes.ItemFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArmorFactory implements ItemFactory {
    @Override
    public Item createItem(String itemType) {
        return null;
    }

    @Override
    public List<? extends Item> createItems(String filePath) {
        List<Item> armory = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // 跳过标题行

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 4) {
                    String name = parts[0];
                    int cost = Integer.parseInt(parts[1]);
                    int level = Integer.parseInt(parts[2]);
                    int damageReduction = Integer.parseInt(parts[3]);

                    Item armor = new Armor(name, cost, level, "Armor", damageReduction);
//                    System.out.println(armor);
                    armory.add(armor);
                }
            }
        } catch (IOException e) {
            System.err.println("读取dragon文件时出错: " + e.getMessage());
        }
        return armory;
    }
}
