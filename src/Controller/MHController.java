// MHController class controls the whole process of the Monsters and Heroes.
package Controller;

import Entity.ChessPiece;
import Entity.MonstersAndHeroes.Hero;
import Entity.MonstersAndHeroes.Item;
import Entity.MonstersAndHeroes.Monster;
import Entity.MonstersAndHeroes.Space;
import Interface.GameFlow;
import Rule.InputRule;
import Rule.MHRule;

import java.util.ArrayList;
import java.util.List;

import static Entity.Color.RESET;
import static Entity.Color.YELLOW;

public class MHController extends NormalController implements GameFlow {
    private static MHRule mhRule = new MHRule();

    private static ArrayList<Hero> heroes = new ArrayList<>();
    private static ArrayList<Monster> monsters = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();

    private static ArrayList<Hero> playerHeroes = new ArrayList<>();

    private static ChessPiece player;



    public MHController() {
    }
    @Override
    public int init() {
        // get heroes
        this.setHeroes(mhRule.initializeHeros());
//        for (int i = 0; i < heroes.size(); i++) {
//            System.out.println(heroes.get(i).toString());
//        }
//        System.exit(-1);

        // get monsters
        this.setMonsters(mhRule.initializeMonsters());
//        for (int i = 0; i < monsters.size(); i++) {
//            System.out.println(monsters.get(i).toString());
//        }
//        System.exit(-1);
        // get items
        this.setItems(mhRule.initializeItems());

        // choose heroes
        this.setPlayerHeroes(mhRule.initializePlayerHeroes(heroes));
        for (int i = 0; i < playerHeroes.size(); i++) {
            System.out.println(playerHeroes.get(i).toString());
        }


        // random init board cell and hero place
        while (true){
            // init the board
            this.setBoard(this.createBoard(5));

            this.setBoard(mhRule.initializeSpace(this.getBoard()));
            mhRule.printBoard(this.getBoard());
            System.out.println("Is this board ok? Input Y/y to continue, input others to reset the board");
            char input = InputRule.ChooseActionByCharacter(new char[]{'Y', 'y'});
            if (input != ' '){
                break;
            }else {
                System.out.println(YELLOW+"Invalid input, please input again"+RESET);
            }
        }


        // set market items into the market space
        this.setBoard(mhRule.initializeMarketSpaceItems(this.getBoard(), items));
//        System.exit(-1);
        return 0;
    }

    @Override
    public void start(int beginRole) {
        while (true){
            // print board
            mhRule.printBoard(this.getBoard());

            // choose action
            if(!mhRule.chooseAction(this.getBoard())){
                // quit
                break;
            };
            // get player's place
            Space currentSpace = this.getBoard().getBoard()[player.getRow()-1][player.getCol()-1].getSpace();
            if(mhRule.promptSpaceAction(currentSpace)){
                //encounter monsters
                ArrayList<Monster> monsters = mhRule.generateRandomMonsters(playerHeroes);
                mhRule.executeFight(monsters);
            }
        }

    }

    @Override
    public Integer winCheck() {
        return -1;
    }

    public MHRule getMhRule() {
        return mhRule;
    }

    public void setMhRule(MHRule mhRule) {
        this.mhRule = mhRule;
    }

    public static ChessPiece getPlayer() {
        return player;
    }

    public static void setPlayer(ChessPiece player) {
        MHController.player = player;
    }

    public ArrayList<? extends Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public static ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }


    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public static ArrayList<Hero> getPlayerHeroes() {
        return playerHeroes;
    }

    public void setPlayerHeroes(ArrayList<Hero> playerHeroes) {
        this.playerHeroes = playerHeroes;
    }
}
