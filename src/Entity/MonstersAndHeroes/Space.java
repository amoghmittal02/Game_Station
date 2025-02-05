package Entity.MonstersAndHeroes;

import static Config.LOVConfig.SPACE_INCREASE_MULTIPLIER;

public abstract class Space {
    // 公共属性
    protected String description; // 描述空间的作用，如“市场区域”或“战斗区域”
    protected boolean accessible; // 是否可进入

    protected String color;
    protected String addAttribute = null;
    protected int addNum = 0;

    // 构造方法
    public Space(String description, boolean accessible, String color) {
        this.description = description;
        this.accessible = accessible;
        this.color = color;
    }

    // 公共方法
    public String getDescription() {
        return description;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public String getColor() {
        return color;
    }

    // 抽象方法，由子类实现具体行为
    public abstract void interact(String type); // 英雄与空间互动

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAddAttribute() {
        return addAttribute;
    }

    public void setAddAttribute(String addAttribute) {
        this.addAttribute = addAttribute;
    }

    public int getAddNum() {
        return addNum;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    public void initializeAttributeNum(Hero hero){
        if (this.addAttribute != null){
            switch (this.addAttribute){
                case "dexterity":
                    this.addNum = (int) (hero.getDexterity() * SPACE_INCREASE_MULTIPLIER);
                    break;
                case "agility":
                    this.addNum = (int) (hero.getAgility() * SPACE_INCREASE_MULTIPLIER);
                    break;
                case "strength":
                    this.addNum = (int) (hero.getStrength() * SPACE_INCREASE_MULTIPLIER);
                    break;
                default:
                    break;
            }
        }
    }
}
