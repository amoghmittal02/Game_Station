
package Rule;

import Controller.MHController;
import Entity.Board;
import Entity.Cell;
import Entity.ChessPiece;
import Entity.MonstersAndHeroes.*;
import Interface.Impl.*;
import Interface.MonstersAndHeroes.ChoiceStrategy;
import Interface.PrintInterface;

import java.util.*;

import static Config.MHConfig.*;
import static Entity.Color.*;

/**
 * MHRule class implements all detailed rules, examinations, function implementations in the Monsters and Heroes
 */
public class MHRule extends NormalRule implements PrintInterface {
    protected static final int UP = 1;
    protected static final int DOWN = 2;
    protected static final int LEFT = 3;
    protected static final int RIGHT = 4;
    protected static final char[] actions = {'w', 'a', 's', 'd', 'i', 'm', 'q', 'W', 'A', 'S', 'D', 'I', 'M', 'Q'};

    @Override
    int roleRule() {
        return 0;
    }

    public Board initializeSpace(Board board) {
        int totalCells = board.getCol() * board.getRow();
        int inaccessibleCount = (int) Math.round(totalCells * 0.20);
        int marketCount = (int) Math.round(totalCells * 0.30);
        int commonCount = totalCells - inaccessibleCount - marketCount;
        Random random = new Random();
        int playerPlace = random.nextInt(commonCount);

        List<Space> spaces = new ArrayList<>();
        for (int i = 0; i < inaccessibleCount; i++) spaces.add(new InaccessibleSpace());
        for (int i = 0; i < marketCount; i++) spaces.add(new MarketSpace());
        for (int i = 0; i < commonCount; i++) spaces.add(new CommonSpace());

        Collections.shuffle(spaces);
        // create players
        ChessPiece player = new ChessPiece(0, 0, 1);

        // allocate spaces
        Cell[][] cells = board.getBoard();
        int index = 0;
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                cells[i][j].setSpace(spaces.get(index));
                if (spaces.get(index) instanceof CommonSpace && --playerPlace <= 0) {
                    player.setRow(i + 1);
                    player.setCol(j + 1);
                    cells[i][j].setChessPiece(player);
                    MHController.setPlayer(player);
                    playerPlace = totalCells;
                }
                index++;
            }
        }
        board.setBoard(cells);
        return board;
    }

    @Override
    public void printBoard(Board board) {
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                System.out.print("+---");
            }
            System.out.print("+\n");

            for (int j = 0; j < board.getCol(); j++) {
                if (board.getBoard()[i][j].getChessPiece() == null && board.getBoard()[i][j].getSpace() == null) {
                    System.out.print("|   ");
                } else if (board.getBoard()[i][j].getChessPiece() == null) {
                    System.out.print("| " + board.getBoard()[i][j].getSpace().getColor() + board.getBoard()[i][j].getSpace().getDescription() + RESET + " ");
                } else if (board.getBoard()[i][j].getChessPiece() != null) {
                    System.out.print("| " + YELLOW + "H" + RESET + " ");
                }
            }
            System.out.print("|\n");
        }
        for (int j = 0; j < board.getCol(); j++) {
            System.out.print("+---");
        }
        System.out.print("+\n");
    }

    public boolean chooseAction(Board board) {
        System.out.println("Input your actions:");
        System.out.println(BLUE + "W/w go up; A/a go left; S/s go down; D/d go right" + RESET);
        System.out.println(GREEN + "I/i check info; M/m enter market(When you stand on the M space, you can go in)" + RESET);
        System.out.println(RED + "Q/q quit game" + RESET);
        char input = ' ';
        boolean flag = true;
        while (true) {
            while (true) {
                input = InputRule.ChooseActionByCharacter(actions);
                if (input != ' ') {
                    break;
                }else{
                    System.out.println(YELLOW+"Invalid input! Please input again!"+RESET);
                }
            }
            switch (input) {
                case 'Q':
                case 'q':
                    System.out.println(YELLOW + "Quit game? Input 'y' to quit!" + RESET);
                    char quit = InputRule.InputChar('y', 'y');
                    if (quit == 'y') {
                        System.out.println("Bye!");
                        return false;
                    }else{
                        System.out.println(GREEN+"Input other choices!"+RESET);
                    }
                    break;
                case 'A':
                case 'a':
                    if (isValidToGo(board, LEFT)) {
                        MHController.setPlayer(board.moveOnePiece(MHController.getPlayer().getRow(), MHController.getPlayer().getCol() - 1, MHController.getPlayer()));
                        flag = false;
                    } else {
                        System.out.println(RED + "You can't go left!" + RESET);
                    }
                    break;
                case 'W':
                case 'w':
                    if (isValidToGo(board, UP)) {
                        MHController.setPlayer(board.moveOnePiece(MHController.getPlayer().getRow() - 1, MHController.getPlayer().getCol(), MHController.getPlayer()));
                        flag = false;
                    } else {
                        System.out.println(RED + "You can't go up!" + RESET);
                    }
                    break;
                case 'S':
                case 's':
                    if (isValidToGo(board, DOWN)) {
                        MHController.setPlayer(board.moveOnePiece(MHController.getPlayer().getRow() + 1, MHController.getPlayer().getCol(), MHController.getPlayer()));
                        flag = false;
                    } else {
                        System.out.println(RED + "You can't go down!" + RESET);
                    }
                    break;
                case 'D':
                case 'd':
                    if (isValidToGo(board, RIGHT)) {
                        MHController.setPlayer(board.moveOnePiece(MHController.getPlayer().getRow(), MHController.getPlayer().getCol() + 1, MHController.getPlayer()));
                        flag = false;
                    } else {
                        System.out.println(RED + "You can't go right!" + RESET);
                    }
                    break;
                case 'I':
                case 'i':
                    printHeroesInfo();
                    break;
                case 'M':
                case 'm':
                    if (isAMarket(board)) {
                        executeMarketLogic(board);
                        flag = false;
                    } else {
                        System.out.println(RED + "No market here!" + RESET);
                    }
                    break;
            }
            if (!flag) {
                break;
            }
        }
        return true;

    }

    void printHeroesInfo() {
        System.out.println(BLUE+"Your heroes:"+RESET);
        int num = 1;
        for (Hero hero : MHController.getPlayerHeroes()) {
            System.out.println((num++)+". "+hero);
            hero.printInventory();
        }
    }

    void executeMarketLogic(Board board) {
        ChessPiece player = MHController.getPlayer();
        MarketSpace currentSpace = (MarketSpace) board.getBoard()[player.getRow() - 1][player.getCol() - 1].getSpace();
        currentSpace.interact("MH");
    }

    boolean isAMarket(Board board) {
        int row = MHController.getPlayer().getRow();
        int col = MHController.getPlayer().getCol();
        return board.getBoard()[row - 1][col - 1].getSpace() instanceof MarketSpace;
    }

    public boolean isValidToGo(Board board, int direction) {
        int row = MHController.getPlayer().getRow();
        int col = MHController.getPlayer().getCol();
        boolean flag = false;
        switch (direction) {
            case UP:
                // check bound, accessible
                if (!ifOutOfBound(row - 1, col, board) && !ifInaccessible(row - 1, col, board)) {
                    flag = true;
                } else {
                    flag = false;
                }
                break;
            case DOWN:
                // check bound, accessible
                if (!ifOutOfBound(row + 1, col, board) && !ifInaccessible(row + 1, col, board)) {
                    flag = true;
                } else {
                    flag = false;
                }
                break;
            case LEFT:
                // check bound, accessible
                if (!ifOutOfBound(row, col - 1, board) && !ifInaccessible(row, col - 1, board)) {
                    flag = true;
                } else {
                    flag = false;
                }
                break;
            case RIGHT:
                // check bound, accessible
                if (!ifOutOfBound(row, col + 1, board) && !ifInaccessible(row, col + 1, board)) {
                    flag = true;
                } else {
                    flag = false;
                }
                break;

        }
        return flag;
    }

    public boolean ifInaccessible(int row, int col, Board board) {
        boolean flag = (board.getBoard()[row - 1][col - 1].getSpace() instanceof InaccessibleSpace);
        return flag;
    }


    public ArrayList<Hero> initializeHeros() {

        ArrayList<Hero> Warriors = (ArrayList<Hero>) new WarriorFactory().createHeroes(WarriorInputFile);
        ArrayList<Hero> Paladins = (ArrayList<Hero>) new PaladinFactory().createHeroes(PaladinInputFile);
        ArrayList<Hero> Sorcerers = (ArrayList<Hero>) new SorcererFactory().createHeroes(SorcererInputFile);
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.addAll(Warriors);
        heroes.addAll(Paladins);
        heroes.addAll(Sorcerers);
        return heroes;
    }

    public ArrayList<Monster> initializeMonsters() {
        ArrayList<Monster> Dragons = (ArrayList<Monster>) new DragonFactory().createMonsters(DragonInputFile);
        ArrayList<Monster> Exoskeletons = (ArrayList<Monster>) new ExoskeletonFactory().createMonsters(ExoskeletonInputFile);
        ArrayList<Monster> Spirits = (ArrayList<Monster>) new SpiritFactory().createMonsters(SpiritInputFile);
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.addAll(Dragons);
        monsters.addAll(Exoskeletons);
        monsters.addAll(Spirits);
        return monsters;
    }

    public ArrayList<Item> initializeItems() {
        ArrayList<Item> Armory = (ArrayList<Item>) new ArmorFactory().createItems(ArmorInputFile);
        ArrayList<Item> Potions = (ArrayList<Item>) new PotionFactory().createItems(PotionInputFile);
        ArrayList<Item> FireSpells = (ArrayList<Item>) new SpellFactory().createItems(FireSpellInputFile);
        ArrayList<Item> IceSpells = (ArrayList<Item>) new SpellFactory().createItems(IceSpellInputFile);
        ArrayList<Item> LightingSpells = (ArrayList<Item>) new SpellFactory().createItems(LightningSpellInputFile);
        ArrayList<Item> Weapons = (ArrayList<Item>) new WeaponFactory().createItems(WeaponInputFile);
        ArrayList<Item> items = new ArrayList<>();
        items.addAll(Armory);
        items.addAll(Potions);
        items.addAll(FireSpells);
        items.addAll(IceSpells);
        items.addAll(LightingSpells);
        items.addAll(Weapons);
        return items;
    }

    public ArrayList<Hero> initializePlayerHeroes(ArrayList<Hero> heroes) {
        ArrayList<Hero> playerHeroes = new ArrayList<>();
        System.out.println(BLUE+"Here are all heroes you can choose:"+RESET);
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println(i + 1 + ". " + heroes.get(i).toString());
        }
        System.out.println(BLUE+"How many heroes you want to choose? Please input a number larger than 0."+RESET);
        while (true) {
            int num = InputRule.InputInteger(1, heroes.size());
            if (num != -1) {
                System.out.println(BLUE+"Please enter all the required hero numbers. Split by space"+RESET);
                while (true) {
                    List<Integer> heroIndexList = InputRule.InputHeroIndex(heroes.size(), num);
                    if (heroIndexList != null) {
                        for (int j = 0; j < heroIndexList.size(); j++) {

                            playerHeroes.add(new Hero(heroes.get(heroIndexList.get(j) - 1)) {
                            });
                        }
                        break;
                    }
                }
                break;
            }else{
                System.out.println(YELLOW+"invalid input, please input again"+RESET);
            }
        }
        return playerHeroes;
    }

    public boolean promptSpaceAction(Space currentSpace) {
        String description = currentSpace.getDescription();
        if (description.equals("M")) {
            System.out.println(GREEN + "You can walk in a market space now! Input M/m to enter!" + RESET);
            return false;
        } else if (description.equals(" ")) {
            System.out.println(GREEN + "You can walk in a common space now! You may encounter monsters!" + RESET);
            // player will have 60% chance to encounter a monster
            if (Math.random() < 0.6) {
//                System.out.println(RED + "You meet a monster!" + RESET);
                return true;
            } else
                return false;
        } else return false;
    }

    public Board initializeMarketSpaceItems(Board board, ArrayList<Item> items) {
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if (board.getBoard()[i][j].getSpace().getDescription().equals("M")) {
                    MarketSpace marketSpace = (MarketSpace) board.getBoard()[i][j].getSpace();
                    marketSpace.setMarket(randomChooseCommidity(items));
                    board.getBoard()[i][j].setSpace(marketSpace);
                }
            }
        }
        return board;
    }

    private Market randomChooseCommidity(ArrayList<Item> items) {
        ArrayList<Commodity> commodities = new ArrayList<>();

        // Group items by type
        Map<String, List<Item>> itemsByType = new HashMap<>();
        for (Item item : items) {
            itemsByType.computeIfAbsent(item.getType(), k -> new ArrayList<>()).add(item);
        }

        List<Item> selectedItems = new ArrayList<>();
        Random random = new Random();

        // Select items randomly
        for (List<Item> oneTypeItems : itemsByType.values()) {

            int itemCountToPick = Math.min(ITEM_NUM_FOR_EACH_TYPE, oneTypeItems.size());
            Collections.shuffle(oneTypeItems);

            for (int i = 0; i < itemCountToPick; i++) {
                selectedItems.add(oneTypeItems.get(i));
            }
        }
        for (int i = 0; i < selectedItems.size(); i++) {
            int numItem = (int) (Math.random() * NUM_FOR_EACH_ITEM) + 1;
            commodities.add(new Commodity(selectedItems.get(i), numItem));
        }
        return new Market(commodities);
    }

    public ArrayList<Monster> generateRandomMonsters(ArrayList<Hero> playerHeroes) {
        ArrayList<Monster> monsters = new ArrayList<>();

        // get level list of playerHeroes
        ArrayList<Integer> levelList = new ArrayList<>();
        for (int i = 0; i < playerHeroes.size(); i++) {
            levelList.add(playerHeroes.get(i).getLevel());
        }
        // get max level of playerHeroes
//        int maxLevel = 0;
//        for (int i = 0; i < playerHeroes.size(); i++) {
//            if (playerHeroes.get(i).getLevel() > maxLevel)
//                maxLevel = playerHeroes.get(i).getLevel();
//        }
        // get monsters level leq to maxLevel
//        for (int i = 0; i < MHController.getMonsters().size(); i++) {
//            if (MHController.getMonsters().get(i).getLevel() <= maxLevel)
//                systemMonsters.add(MHController.getMonsters().get(i));
//        }

        int num = playerHeroes.size();
        double aveLevel = calculateAveLevel(playerHeroes);
        for (int i = 0; i < num; i++) {
            // choose monster with level equal to level list of playerHeroes
            int tempLevel = levelList.get(i);
            ArrayList<Monster> systemMonsters = new ArrayList<>();
            for (int j = 0; j < MHController.getMonsters().size(); j++) {
//                System.out.println(MHController.getMonsters().get(j));
                if (MHController.getMonsters().get(j).getLevel() == tempLevel) {
                    systemMonsters.add(new Monster(MHController.getMonsters().get(j)));
                }
            }
            // random choose a monster with level = tempLevel
            int index = (int) (Math.random() * systemMonsters.size());
            Monster monster = systemMonsters.get(index);
            monsters.add(monster);
        }
        return monsters;
    }

    private double calculateAveLevel(ArrayList<Hero> playerHeroes) {
        double sum = 0;
        for (int i = 0; i < playerHeroes.size(); i++) {
            sum += playerHeroes.get(i).getLevel();
        }
        return sum * 1.0 / playerHeroes.size();
    }

    public void executeFight(ArrayList<Monster> monsters) {
        ChoiceStrategy cs;
        ArrayList<Hero> heroes = new ArrayList<>(MHController.getPlayerHeroes());
        ArrayList<Hero> originalHeroes = new ArrayList<>();
        for (int i = 0; i < heroes.size(); i++) {
            originalHeroes.add(new Hero(heroes.get(i)));
        }
//        System.out.println("1"+System.identityHashCode(heroes));
//        System.out.println("2"+System.identityHashCode(MHController.getPlayerHeroes()));
        System.out.println(RED + "You meet several monsters!" + RESET);
        boolean heroTurn = true;
        while (heroes.size() > 0 && monsters.size() > 0) {
            if(heroTurn){
                System.out.println(GREEN + "Your turn!" + RESET);
                System.out.println(BLUE+"Here are monsters you meet."+RESET);
                for (int i = 0; i < monsters.size(); i++) {
                    Monster monster = monsters.get(i);
                    System.out.println(i + 1 + ". " + monster.toString());
                }

                int heroIndex = chooseOneHero(heroes)-1;
                int heroAction = heroChooseAnAction(heroes.get(heroIndex));
                Hero currentHero = heroes.get(heroIndex);
                switch (heroAction) {
                    case 1: // attack
                        int monsterIndex = chooseOneMonster(monsters)-1;
                        int totalDamage = heroAttack(heroes.get(heroIndex));
//                        System.out.println(GREEN+"The hero deals "+totalDamage+" damage!"+RESET);
                        boolean ifDodge = dodge(monsters.get(monsterIndex).getDodgeChance());
                        int monsterDefense = (int) (monsters.get(monsterIndex).getDefense()*MONSTER_DEFENSE_MULTIPLIER);

                        if (ifDodge) {
                            System.out.println(RED + "The monster dodges the attack!!!" + RESET);
                            Hero hero = heroAttackBeDodged(heroes.get(heroIndex));
                            heroes.set(heroIndex, hero);
                        } else {

                            Hero hero = executeAttack(heroes.get(heroIndex));
                            heroes.set(heroIndex, hero);
                            Monster monsterBeAttacked = monsters.get(monsterIndex);
                            if (totalDamage - monsterDefense <= 0){
                                System.out.println(RED + "The monster defends all damage!" + RESET);

                            }else{
                                System.out.println(RED + "The monster defends "+ monsterDefense + " damage!" + RESET);
                                totalDamage -= monsterDefense;
                            }
                            if (monsterBeAttacked.getHp() - totalDamage <= 0) {
                                System.out.println(GREEN + "The monster is killed!" + RESET);
                                monsters.remove(monsterIndex);
                            } else {
                                System.out.println(GREEN + "The monster has " + (monsterBeAttacked.getHp() - totalDamage) + " hp left." + RESET);
                                monsterBeAttacked.setHp(monsterBeAttacked.getHp() - totalDamage);
                                monsters.set(monsterIndex, monsterBeAttacked);
                            }
                        }
                        break;
                    case 2:
                        cs = new EquipSpell();
                        currentHero = cs.executeChoice(currentHero);
                        heroes.set(heroIndex, currentHero);
                        break;
                    case 3:
                        cs = new PotionUse();
                        currentHero = cs.executeChoice(currentHero);
                        heroes.set(heroIndex, currentHero);
                        break;
                    case 4:
                        cs = new EquipWeapon();
                        currentHero = cs.executeChoice(currentHero);
                        heroes.set(heroIndex, currentHero);
                        break;
                    case 5:
                        cs = new EquipArmor();
                        currentHero = cs.executeChoice(currentHero);
                        heroes.set(heroIndex, currentHero);
                        break;
                    default:
                        break;
                }
                heroTurn = !heroTurn;
            }else{
                System.out.println(YELLOW+"Monster's attack turn!"+RESET);
                // randomly choose a monster in monsters
                int monsterIndex = (int) (Math.random() * monsters.size());
                Monster monster = monsters.get(monsterIndex);
//                System.out.println(monsterIndex);
//                System.out.println(monster);
                System.out.println(RED+"Monster "+monster.getName()+" is attacking!"+RESET);
                // randomly choose a hero to attack
                int heroIndex = (int) (Math.random() * heroes.size());
                System.out.println(RED+"Monster "+monster.getName()+" attacks hero "+heroes.get(heroIndex).getName()+"!"+RESET);
                int totalDamage = (int) (monster.getBaseDamage() * MONSTER_DAMAGE_MULTIPLIER);
                System.out.println(RED+"Monster "+monster.getName()+" may cause "+totalDamage+" damage!"+RESET);
                boolean ifDodge = dodge(heroes.get(heroIndex).getAgility());
                if (ifDodge) {
                    System.out.println(GREEN + "The hero dodges the attack!!!" + RESET);
                } else {
                    Hero beAttackedHero = heroGetAttacked(heroes.get(heroIndex), totalDamage);
                    if (beAttackedHero == null){
                        heroes.remove(heroIndex);
                    }else{
                        heroes.set(heroIndex,beAttackedHero);
                    }
                }
                heroTurn = !heroTurn;
            }

        }
        for(int i = 0; i <MHController.getPlayerHeroes().size();i++){
            Hero originalHero = originalHeroes.get(i);
            MHController.getPlayerHeroes().get(i).setMp(originalHero.getMp());
            MHController.getPlayerHeroes().get(i).setHp(originalHero.getHp());
            MHController.getPlayerHeroes().get(i).setAgility(originalHero.getAgility());
            MHController.getPlayerHeroes().get(i).setDexterity(originalHero.getDexterity());
            MHController.getPlayerHeroes().get(i).setStrength(originalHero.getStrength());
        }
        // win the fight
        if (monsters.size() == 0){
            System.out.println(GREEN+"You win the fight! Heroes will get experience and golds!"+RESET);
            ArrayList<Hero> playerHeroes = MHController.getPlayerHeroes();
            for (int i = 0; i < playerHeroes.size(); i++){
                playerHeroes.get(i).updateExperience(playerHeroes.get(i).getExperience());
                playerHeroes.get(i).updateGold(playerHeroes.get(i).getGold());
            }
        }else {
            System.out.println(RED+"You lose the fight! Please prepare more and win the fight next time!"+RESET);
        }

    }

    public Hero heroGetAttacked(Hero hero, int totalDamage) {
        if (hero.getArmor()!= null){
            if (totalDamage - hero.getArmor().getDamageReduction() <= 0){
                System.out.println(BLUE+hero.getName()+ " uses "+hero.getArmor().getName()+"to reduce "+totalDamage+" damage!"+RESET);
                totalDamage = 0;
            }else{
                System.out.println(BLUE+hero.getName()+ " uses "+hero.getArmor().getName()+"to reduce "+hero.getArmor().getDamageReduction()+" damage!"+RESET);
                totalDamage -= hero.getArmor().getDamageReduction();
            }

            System.out.println(YELLOW+"The hero get "+totalDamage+" damage."+RESET);
            System.out.println(YELLOW+"The hero has "+(hero.getHp()-totalDamage)+" hp left."+RESET);

        }else{
            System.out.println(YELLOW+"The hero get "+totalDamage+" damage."+RESET);
            System.out.println(YELLOW+"The hero has "+(hero.getHp()-totalDamage)+" hp left."+RESET);
        }
        hero.setHp(hero.getHp()-totalDamage);
        if (hero.getHp()<=0){
            System.out.println(RED+"The hero is killed!"+RESET);
            return null;
        }
        else {
            return hero;
        }
    }


    public Hero heroAttackBeDodged(Hero hero) {
        int damage = hero.getLevel() * HERO_BASIC_DAMAGE;
        if (hero.getSpell()!= null){
            if (hero.getMp() >= hero.getSpell().getManaCost()){
                damage += hero.getSpell().getDamage()*hero.getDexterity()*HERO_SPELL_DAMAGE_MULTIPLIER;
                hero.setMp(hero.getMp()-hero.getSpell().getManaCost());
//                System.out.println(BLUE+hero.getName()+ " uses "+hero.getSpell().getName()+"to increase extra "+hero.getSpell().getDamage()*hero.getDexterity()*HERO_SPELL_DAMAGE_MULTIPLIER+" damage!"+RESET);
            }
        }
        if (hero.getWeapon()!= null){
            for (int i = 0; i < hero.getWeapon().size(); i++) {
                damage += hero.getWeapon().get(i).getDamage()*hero.getStrength()*HERO_WEAPON_DAMAGE_MULTIPLIER;
//                System.out.println(BLUE+hero.getName()+ " uses "+hero.getWeapon().get(i).getName()+"to increase extra "+hero.getWeapon().get(i).getDamage()*hero.getStrength()*HERO_WEAPON_DAMAGE_MULTIPLIER+" damage!"+RESET);
            }
        }
        return hero;
    }

    public boolean dodge(int dodge) {
        int dodgeChance = (int) (Math.random()*100);
        if (dodgeChance <= dodge*MONSTER_DODGE_MULTIPLIER){
            return true;
        }else {
            return false;
        }
    }

    public int heroAttack(Hero hero) {
        int damage = calculateTotalDamage(hero, (int)(hero.getLevel()*HERO_BASIC_DAMAGE));
        return damage;
    }

    private int calculateTotalDamage(Hero hero, int damage) {
        if (hero.getSpell()!= null){
            if (hero.getMp() >= hero.getSpell().getManaCost()){
                damage += hero.getSpell().getDamage()*hero.getDexterity()*HERO_SPELL_DAMAGE_MULTIPLIER;
            }
        }
        if (hero.getWeapon()!= null){
            if (hero.getWeapon().size() == 1 && hero.getWeapon().get(0).getHandsRequired() == 1){
                damage += hero.getWeapon().get(0).getDamage()*hero.getStrength()*HERO_WEAPON_DAMAGE_MULTIPLIER*WEAPON_FOR_ONE_HAND_MULTIPLIER;
            }else{
                for (int i = 0; i < hero.getWeapon().size(); i++) {
                    damage += hero.getWeapon().get(i).getDamage()*hero.getStrength()*HERO_WEAPON_DAMAGE_MULTIPLIER;
                }
            }
        }
        return damage;
    }

    public Hero executeAttack(Hero hero) {
        int damage = hero.getLevel() * HERO_BASIC_DAMAGE;
        System.out.println(BLUE+hero.getName()+ " has "+damage+" basic damage!"+RESET);
        if (hero.getSpell()!= null){
            if (hero.getMp() >= hero.getSpell().getManaCost()){
                damage += hero.getSpell().getDamage()*hero.getDexterity()*HERO_SPELL_DAMAGE_MULTIPLIER;
                hero.setMp(hero.getMp()-hero.getSpell().getManaCost());
                System.out.println(BLUE+hero.getName()+ " uses "+hero.getSpell().getName()+" to increase extra "+(int)(hero.getSpell().getDamage()*hero.getDexterity()*HERO_SPELL_DAMAGE_MULTIPLIER)+" damage!"+RESET);
            }
        }
        if (hero.getWeapon()!= null){
            if (hero.getWeapon().size() == 1 && hero.getWeapon().get(0).getHandsRequired() == 1){
                int additionalDamage = (int)(hero.getWeapon().get(0).getDamage()*hero.getStrength()*HERO_WEAPON_DAMAGE_MULTIPLIER*WEAPON_FOR_ONE_HAND_MULTIPLIER);
                damage += additionalDamage;
                System.out.println(BLUE+hero.getName()+ " uses "+hero.getWeapon().get(0).getName()+" for single hand to increase extra "+additionalDamage+" damage!"+RESET);
            }else{
                for (int i = 0; i < hero.getWeapon().size(); i++) {
                    int additionalDamage = (int)(hero.getWeapon().get(i).getDamage()*hero.getStrength()*HERO_WEAPON_DAMAGE_MULTIPLIER*WEAPON_FOR_ONE_HAND_MULTIPLIER);
                    damage += additionalDamage;
                    System.out.println(BLUE+hero.getName()+ " uses "+hero.getWeapon().get(i).getName()+" to increase extra "+additionalDamage+" damage!"+RESET);
                }
            }
        }
        System.out.println(BLUE+"The hero deals total "+damage+" damage!"+RESET);
        return hero;
    }

    private int chooseOneMonster(ArrayList<Monster> monsters) {
        System.out.println(BLUE+"Choose a monster to attack. Input 0 to print heroes' information."+RESET);
        for (int i = 0; i < monsters.size(); i++) {
            System.out.println(i+1+". "+monsters.get(i).toString());
        }
        while (true){
            int index = InputRule.InputInteger(0, monsters.size());
            if(index == 0){
                printHeroesInfo();
            }
            else if (index != -1){
                return index;
            }else {
                System.out.println(YELLOW+"invalid input, please input again"+RESET);
            }
        }
    }

    private int chooseOneHero(ArrayList<Hero> heroes) {
        System.out.println(BLUE+"Choose a hero to fight."+RESET);
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println(i+1+". "+heroes.get(i).toString());
        }
        while (true){
            int index = InputRule.InputInteger(1, heroes.size());
            if (index != -1){
                return index;
            }else{
                System.out.println(YELLOW+"invalid input, please input again"+RESET);
            }
        }
    }


    private int heroChooseAnAction(Hero hero) {
        System.out.println(YELLOW+"If you choose to equip a new spell or armor, the old one will be put back to the inventory automatically."+RESET);
        System.out.println(YELLOW+"If you choose to equip a new weapon and the hero has no more hands to hold the weapon, you should choose to put some old weapons back to the inventory."+RESET);
        System.out.println("0. Print heroes' information");
        System.out.println("1. Attack");
        System.out.println("2. Cast spell");
        System.out.println("3. Use potion");
        System.out.println("4. Equip weapon");
        System.out.println("5. Equip Armor");
        Inventory inventory =hero.getInventory();
        ArrayList<Weapon> weapons = hero.getWeapon();
        ArrayList<Item> weaponsInInventory = new ArrayList<>();
        if (inventory != null){
            for (Commodity commodity : inventory.getItems()){
                if (commodity.getItem().getType().equals("Weapon")){
                    weaponsInInventory.add(commodity.getItem());
                }
            }
        }
        while (true){
            int choice = InputRule.InputInteger(0, 5);
            if(choice == 0){
                printHeroesInfo();
                System.out.println("Make another choices of 1-5");
                System.out.println("1. Attack");
                System.out.println("2. Cast spell");
                System.out.println("3. Use potion");
                System.out.println("4. Equip weapon");
                System.out.println("5. Equip Armor");
            }else if (choice != -1){
                switch (choice){
                    case 1:
                        break;
                    case 2:
                        boolean spellFlag = false;
                        for (int i = 0; i < inventory.getItems().size(); i++){
                            Commodity commodity = inventory.getItems().get(i);
                            if (commodity.getItem().getType().equals("Spell")){
                                spellFlag = true;
                                break;
                            }
                        }
                        if (spellFlag){
                            return 2;
                        }else {
                            System.out.println(YELLOW+"You don't have any spell in your inventory. Please input another choice"+RESET);
                            continue;
                        }
                    case 3:
                        boolean potionFlag = false;
                        for (int i = 0; i < inventory.getItems().size(); i++) {
                            Commodity commodity = inventory.getItems().get(i);
                            if (commodity.getItem().getType().equals("Potion")){
                                potionFlag = true;
                                break;
                            }
                        }
                        if (potionFlag){
                            return 3;
                        }else {
                            System.out.println(YELLOW+"You don't have any potion in your inventory. Please input another choice"+RESET);
                            continue;
                        }
                    case 4:
                        int weaponHands = 0;
                        if (weapons.size() == 0) {
                            if (weaponsInInventory.size()<=0){
                                System.out.println(YELLOW+"You don't have any weapon to equip! Please input another choice"+RESET);
                                continue;
                            }else{
                                return 4;
                            }
                        }else{
                            if (weaponsInInventory.size()<=0){
                                System.out.println(YELLOW+"You don't have any weapon to equip! Please input another choice"+RESET);
                            }else{
                                return 4;
                            }

                            continue;
                        }
                    case 5:
                        boolean armorFlag = false;
                        for (int i = 0; i < inventory.getItems().size(); i++) {
                            Commodity commodity = inventory.getItems().get(i);
                            if (commodity.getItem().getType().equals("Armor")){
                                armorFlag = true;
                                break;
                            }
                        }
                        if (armorFlag){
                            return 5;
                        }else {
                            System.out.println(YELLOW+"You don't have any armor in your inventory. Please input another choice"+RESET);
                            continue;
                        }
                }
                return choice;
            }else {
                System.out.println("Please input again");
            }
        }
    }

}
