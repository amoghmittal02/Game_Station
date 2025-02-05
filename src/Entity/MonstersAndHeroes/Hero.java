package Entity.MonstersAndHeroes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Config.MHConfig.*;
import static Entity.Color.*;

public class Hero {
    protected String type;
    protected String name;
    protected int level;
    protected int hp;
    protected int mp;
    protected int strength;
    protected int dexterity;
    protected int agility;
    protected int gold;

    protected int experience;
    protected Inventory inventory; // 英雄的物品列表
    protected String mark;

    protected Spell spell = null;
    protected Armor armor = null;
    protected ArrayList<Weapon> weapon = new ArrayList<>();
    protected int maxHp;
    protected int maxMp;

    public Hero(String name, int level, int hp, int mp, int strength, int dexterity, int agility, int gold, int experience, Inventory inventory, String type) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.mp = mp;
        this.maxHp = hp;
        this.maxMp = mp;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.gold = gold;
        this.experience = experience;
        this.inventory = inventory;
        this.type = type;
    }

    public Hero(Hero hero) {
        this.name = hero.name;
        this.level = hero.level;
        this.hp = hero.hp;
        this.mp = hero.mp;
        this.maxHp = hero.hp;
        this.maxMp = hero.mp;
        this.strength = hero.strength;
        this.dexterity = hero.dexterity;
        this.agility = hero.agility;
        this.gold = hero.gold;
        this.experience = hero.experience;
        this.inventory = hero.inventory;
        this.type = hero.type;
    }

    public Map<String, Integer> parseAttributes(String input) {
        Map<String, Integer> attributes = new HashMap<>();
        String[] pairs = input.split("\t");
        for (String pair : pairs) {
            if (!pair.isEmpty()) {
                String[] keyValue = pair.split(":");
                String key = keyValue[0].trim();
                int value = Integer.parseInt(keyValue[1].trim());
                attributes.put(key, value);
            }
        }
        return attributes;
    }

    public void usePotion(Potion potion)
    {
        String affectedAttribute = potion.getAffectedAttribute();
        String[] attributeArray = affectedAttribute.split("/");

        for (String attribute : attributeArray) {
            updateAttribute(attribute, potion.getAttributeIncrease());
        }
        System.out.println(GREEN+"Hero "+this.name+" has been strengthened! Here is its new condition"+RESET);
        System.out.println(this);
    }

    private void updateAttribute(String attribute, int attributeIncrease) {
        String[] attributeArray = attribute.split("/");
        for (String attributeType : attributeArray){
            switch (attributeType) {
                case "Health":
                    hp += attributeIncrease;
                    break;
                case "Mana":
                    mp += attributeIncrease;
                    break;
                case "Strength":
                    strength += attributeIncrease;
                    break;
                case "Dexterity":
                    dexterity += attributeIncrease;
                    break;
                case "Agility":
                    agility += attributeIncrease;
                    break;
                case "All":
                    hp += attributeIncrease;
                    mp += attributeIncrease;
                    strength += attributeIncrease;
                    dexterity += attributeIncrease;
                    agility += attributeIncrease;
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public String toString() {
        String basic = "Hero's name='" + name + "'\n" +
                "type='" + type + '\'' +
                "\tlevel=" + level +
                "\thp=" + hp +
                "\tmp=" + mp +
                "\tstrength=" + strength +
                "\tdexterity=" + dexterity +
                "\tagility=" + agility +
                "\tgold=" + gold +
                "\texperience=" + experience;

        if (spell != null) {
            basic += "\nThe hero equips spell:\n";
            basic += spell.toString();
        }
        if (armor != null) {
            basic += "\nThe hero equips armor:\n";
            basic += armor.toString();
        }
        if (weapon.size() > 0){
            for (Weapon weapon : weapon) {
                basic += "\nThe hero equips weapon:\n";
                basic += weapon.toString();
            }
        }
        basic+="\n";
        return basic;
    }

    public void printInventory()
    {
        System.out.println("Inventory:");
        int i = 0;
        if (inventory.getItems().size() == 0){
            System.out.println(YELLOW+"Nothing!"+RESET);
        }else {
            for (Commodity item : inventory.getItems()) {
                System.out.println((++i) + ". " + item.toString());
            }
        }
        System.out.println();
    }

    public void addItemToInventory(Item item, int numPurchase) {
        inventory.addItem(new Commodity(item, numPurchase));
    }

    public void updateItemInInventory(Commodity commodity) {
        inventory.updateItem(commodity, commodity.getNum());
        inventory.removeEmptyItem();
    }


    public void addWeapon(Weapon weapon) {
        if (this.weapon == null){
            this.weapon = new ArrayList<>();
        }
        this.weapon.add(weapon);
    }

    public void reduceItemInInventory(Item item) {
        for (int i = 0; i < this.getInventory().getItems().size(); i++) {
            if (this.getInventory().getItem(i).getItem().getName().equals(item.getName())){
                // if num == 1, delete
                if (this.getInventory().getItem(i).getNum() == 1){
                    this.getInventory().removeItem(this.getInventory().getItem(i));
                }else {
                    // num - 1
                    this.getInventory().getItem(i).setNum(this.getInventory().getItem(i).getNum() - 1);
                }
            }
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
        System.out.println(BLUE+"Hero equips chosen spell!"+RESET);
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public ArrayList<Weapon> getWeapon() {
        return weapon;
    }

    public void setWeapon(ArrayList<Weapon> weapon) {
        this.weapon = weapon;
    }

    public void updateExperience(int experience) {
        this.experience = this.experience+(int)((0.5+0.5*Math.random())*EXPERIMENCE_INCREASE_RANGE);
        if (this.experience >= 100){
            this.level += 1;
            this.experience = this.experience - 100;
            updateLevelUp();
            System.out.println(GREEN+"Congratulations! hero "+this.name+" level up! Now it's in level " + this.level+RESET);
        }else {
            System.out.println(GREEN+"Congratulations! hero "+this.name+" has "+this.experience+" experience now! Now it's in level " + this.level+RESET);
        }
    }

    private void updateLevelUp() {
        this.hp = this.hp + HP_INCREASE;
        this.mp = this.mp + MP_INCREASE;
        this.maxHp = this.maxHp + HP_INCREASE;
        this.maxMp = this.maxMp + MP_INCREASE;
        this.hp = this.maxHp;
        this.mp = this.maxMp;
        this.strength = this.strength + STRENGTH_INCREASE;
        this.dexterity = this.dexterity + DEXTERITY_INCREASE;
    }

    public void updateGold(int gold) {
        this.gold = this.gold+(int)((0.5+0.5*Math.random())*GOLD_INCREASE_RANGE);
        System.out.println(GREEN+"Congratulations! hero "+this.name+" has "+this.gold+" gold now!"+RESET);
    }

    public void putItemInInventory(Item itemNow, int num) {
        if (itemNow == null){
            return;
        }
        for (Commodity item : inventory.getItems()) {
            if (item.getItem().getName().equals(itemNow.getName())){
                item.setNum(item.getNum()+num);
                return;
            }
        }
        inventory.addItem(new Commodity(itemNow, num));
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void addMpAndHp(double heroRecoveryMultiplierEachRound) {
        int tempHp = this.hp + (int)(this.hp*heroRecoveryMultiplierEachRound);
        this.hp = maxHp > tempHp?tempHp:maxHp;
        int tempMp = this.mp + (int)(this.mp*heroRecoveryMultiplierEachRound);
        this.mp = maxMp > tempMp?tempMp:maxMp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }
}
