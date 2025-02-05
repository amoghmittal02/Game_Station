package Entity;

import Entity.Player;

/**
 * game team
 */
public class Team {
    private String name;

    private int role;

    private int num;

    private Player[] players;

    public Team() {

    }

    public Team(String name, int role, int num) {
        this.name = name;
        this.role = role;
        this.num = num;
        this.players = new Player[num];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
