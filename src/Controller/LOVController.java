//LOVController class controls the whole process of the Legends of Valor.

        package Controller;

import Entity.ChessPiece;
import Entity.MonstersAndHeroes.Hero;
import Entity.MonstersAndHeroes.Item;
import Entity.MonstersAndHeroes.Monster;
import Entity.MusicPlayer;
import Interface.GameFlow;
import Rule.LOVRule;
import Rule.MHRule;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static Config.LOVConfig.HERO_RECOVERY_MULTIPLIER_EACH_ROUND;
import static Config.LOVConfig.ROUND_GENERATE_MONSTER;
import static Entity.Color.*;

/**
 * Special Controller for Legends of Valor
 */
public class LOVController extends MHController implements GameFlow {
    private static int monsterNum = 0;
    private static int heroNum = 0;
    private static LOVRule lovRule = new LOVRule();

    private static ArrayList<Hero> playerHeroes = new ArrayList<>();
    private static ArrayList<Monster> gameMonsters = new ArrayList<>();

    private static ArrayList<ChessPiece> heroPlaces = new ArrayList<>();
    // this variable won't change! Used in recall action
    private static ArrayList<ChessPiece> originalHeroPlaces = new ArrayList<>();
    private static ArrayList<ChessPiece> monsterPlaces = new ArrayList<>();

    private static ArrayList<Hero> AllHeroes = new ArrayList<>();
    private static ArrayList<Monster> AllMonsters = new ArrayList<>();

    private static int currentHeroIndex = -1;
    private static int currentMonsterIndex = -1;

    private static ArrayList<Integer> heroIndexInAllHeroes = new ArrayList<>();

    private static MusicPlayer musicPlayer = new MusicPlayer();

    @Override
    public int init() {
        monsterNum = 0;
        heroNum = 0;
        ArrayList<Hero> playerHeroes = new ArrayList<>();
        gameMonsters = new ArrayList<>();
        heroPlaces = new ArrayList<>();
        originalHeroPlaces = new ArrayList<>();
        monsterPlaces = new ArrayList<>();
        heroIndexInAllHeroes = new ArrayList<>();
        // choose game difficulty
        lovRule.setDifficulty();

        // set all heroes
        this.setAllHeroes(lovRule.initializeHeros());
        // set all monsters
        this.setAllMonsters(lovRule.initializeMonsters());

        // set all items
        this.setItems(lovRule.initializeItems());

        // initialize board
        this.setBoard(this.createBoard(6));

        // initialize spaces
        this.setBoard(lovRule.initializeSpace(this.getBoard()));

        // initialize heroes
        this.setPlayerHeroes(lovRule.initializePlayerHeroes(this.getAllHeroes()));
        this.setHeroPlaces(lovRule.setHeroPlacesIntoBoard(this.getBoard(), 3));

        // initialize monsters
        this.setGameMonsters(lovRule.generateGameMonsters(this.getAllMonsters(), 3));
        this.setMonsterPlaces(lovRule.setMonsterPlacesIntoBoard(this.getBoard(), 3));

        // set market items into the market space
        this.setBoard(lovRule.initializeMarketSpaceItems(this.getBoard(), super.getItems()));

        return 0;
    }

    @Override
    public void start(int beginRole) {
        // Start background music
        musicPlayer.playMusic("InputFile/background_music.wav");
        int round = 1;
        while (true){
            // print board
//            lovRule.printBoard(this.getBoard());
//            System.exit(0);

            // choose action
            for (Hero hero: playerHeroes){
                currentHeroIndex++;
                lovRule.printBoard(this.getBoard());
                if(!lovRule.chooseAction(this.getBoard(), hero)){
                    musicPlayer.stopMusic();
                    // quit

                    return;
                }
                // check win
                int winResult = winCheck();
                if(winResult==-1){
                    continue;
                } else if (winResult == 0) {
                    System.out.println(RED + "Monsters have won! Game over." + RESET);
                    lovRule.printBoard(this.getBoard());
                    musicPlayer.stopMusic();
                    return; // end game

                    // monster win
                }else {
                    // hero win
                    System.out.println(GREEN + "Heroes have won! Game over." + RESET);
                    lovRule.printBoard(this.getBoard());
                    musicPlayer.stopMusic();
                    return; //end game
                }
            }
            currentHeroIndex=-1;
            for(Monster monster:gameMonsters){
                if(monster != null){
                    System.out.println("Monster "+monster.getMark()+"'s turn!");
                    lovRule.actMonster(this.getBoard(), monster);

                    int winResult = winCheck();
                    if(winResult==-1){
                        continue;
                    } else if (winResult == 0) {
                        // monster win
                        System.out.println(RED + "Monsters have won! Game over." + RESET);
                        lovRule.printBoard(this.getBoard());
                        musicPlayer.stopMusic();
                        return; // end game
                    }else {
                        // hero win
                        System.out.println(GREEN + "Heroes have won! Game over." + RESET);
                        lovRule.printBoard(this.getBoard());
                        musicPlayer.stopMusic();
                        return; //end game
                    }
                }

            }
            for (Hero hero: playerHeroes){
                currentHeroIndex++;
                // add mp and hp
                hero.addMpAndHp(HERO_RECOVERY_MULTIPLIER_EACH_ROUND);
            }
            currentHeroIndex=-1;


//            System.exit(0);
            // generate new monsters
//            System.out.println(ROUND_GENERATE_MONSTER);
            if ((round % ROUND_GENERATE_MONSTER) == 0){
                for (int i = 0; i < 3; i++){
                    if (lovRule.checkMonsterGeneratePlaceAndAddMonster(i+1)) {
                        this.getGameMonsters().addAll(lovRule.generateGameMonsters(this.getAllMonsters(), 1));
                    }
                }

//                this.getMonsterPlaces().addAll(lovRule.setMonsterPlacesIntoBoard(this.getBoard(), 3));
            }
            round++;
        }

    }

    @Override
    public Integer winCheck() {
        return lovRule.CheckWin();
    }

    public static LOVRule getLovRule() {
        return lovRule;
    }

    public static void setLovRule(LOVRule lovRule) {
        LOVController.lovRule = lovRule;
    }


    public static ArrayList<Hero> getPlayerHeroes() {
        return playerHeroes;
    }

    public void setPlayerHeroes(ArrayList<Hero> playerHeroes) {
        LOVController.playerHeroes = playerHeroes;
    }

    public static ArrayList<ChessPiece> getHeroPlaces() {
        return heroPlaces;
    }

    public static void setHeroPlaces(ArrayList<ChessPiece> heroPlaces) {
        LOVController.heroPlaces = heroPlaces;
    }

    public static ArrayList<ChessPiece> getMonsterPlaces() {
        return monsterPlaces;
    }

    public static void setMonsterPlaces(ArrayList<ChessPiece> monsterPlaces) {
        LOVController.monsterPlaces = monsterPlaces;
    }

    public static ArrayList<Hero> getAllHeroes() {
        return AllHeroes;
    }

    public static void setAllHeroes(ArrayList<Hero> allHeroes) {
        AllHeroes = allHeroes;
    }

    public static ArrayList<Monster> getAllMonsters() {
        return AllMonsters;
    }

    public static void setAllMonsters(ArrayList<Monster> allMonsters) {
        AllMonsters = allMonsters;
    }

    public static ArrayList<Monster> getGameMonsters() {
        return gameMonsters;
    }

    public static void setGameMonsters(ArrayList<Monster> gameMonsters) {
        LOVController.gameMonsters = gameMonsters;
    }

    public static int getMonsterNum() {
        return monsterNum;
    }

    public static void setMonsterNum(int monsterNum) {
        LOVController.monsterNum = monsterNum;
    }

    public static int getHeroNum() {
        return heroNum;
    }

    public static void setHeroNum(int heroNum) {
        LOVController.heroNum = heroNum;
    }

    public static ArrayList<ChessPiece> getOriginalHeroPlaces() {
        return originalHeroPlaces;
    }

    public static void setOriginalHeroPlaces(ArrayList<ChessPiece> originalHeroPlaces) {
        LOVController.originalHeroPlaces = originalHeroPlaces;
    }

    public static int getCurrentHeroIndex() {
        return currentHeroIndex;
    }

    public static void setCurrentHeroIndex(int currentHeroIndex) {
        LOVController.currentHeroIndex = currentHeroIndex;
    }

    public static int getCurrentMonsterIndex() {
        return currentMonsterIndex;
    }

    public static void setCurrentMonsterIndex(int currentMonsterIndex) {
        LOVController.currentMonsterIndex = currentMonsterIndex;
    }

    public static ArrayList<Integer> getHeroIndexInAllHeroes() {
        return heroIndexInAllHeroes;
    }

    public static void setHeroIndexInAllHeroes(ArrayList<Integer> heroIndexInAllHeroes) {
        LOVController.heroIndexInAllHeroes = heroIndexInAllHeroes;
    }

    public static MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

}
