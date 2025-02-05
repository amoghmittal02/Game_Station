package Interface.MonstersAndHeroes;

import Entity.MonstersAndHeroes.Item;

import java.util.List;

/**
 * Item factory interface-factory pattern
 */
public interface ItemFactory {
    public abstract Item createItem(String itemType); // 创建特定类型的物品

    public abstract List<? extends Item> createItems(String filePath);
}
