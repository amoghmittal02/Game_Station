package Entity.MonstersAndHeroes;

import java.util.HashMap;
import java.util.Map;

public class Monster{
    protected String name;
    protected int level;
    protected int hp;
    protected int baseDamage;
    protected int defense;
    protected int dodgeChance;

    protected String type;

    protected String mark;

    public Monster(String name, int level, int hp, int baseDamage, int defense, int dodgeChance, String type) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.baseDamage = baseDamage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
        this.type = type;
    }

    public Monster(Monster monster){
        this.name = monster.name;
        this.level = monster.level;
        this.hp = monster.hp;
        this.baseDamage = monster.baseDamage;
        this.defense = monster.defense;
        this.dodgeChance = monster.dodgeChance;
        this.type = monster.type;
    }

    public Monster() {
    }

    public void updateAllParameters(Monster monster) {
        this.name = monster.name;
        this.level = monster.level;
        this.hp = monster.hp;
        this.baseDamage = monster.baseDamage;
        this.defense = monster.defense;
        this.dodgeChance = monster.dodgeChance;
        this.type = monster.type;
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

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Monster's name='" + name + "'\n" +
                "\ttype='" + type + '\'' +
                "\tlevel=" + level +
                "\thp=" + hp +
                "\tbaseDamage=" + baseDamage +
                "\tdefense=" + defense +
                "\tdodgeChance=" + dodgeChance;
    }
}
