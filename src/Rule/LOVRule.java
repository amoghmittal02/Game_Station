package Rule;

import Controller.LOVController;
import Controller.MHController;
import Entity.Board;
import Entity.Cell;
import Entity.ChessPiece;
import Entity.LegendsOfValor.*;
import Entity.MonstersAndHeroes.*;
import Interface.Impl.*;
import Interface.MonstersAndHeroes.ChoiceStrategy;

import java.util.*;

import static Config.LOVConfig.ROUND_GENERATE_MONSTER;
import static Config.MHConfig.*;
import static Entity.Color.*;
import static Entity.Color.BLUE;
import static java.lang.System.exit;

/**
 * LOVRule class implements all detailed rules, examinations, function implementations in the game
 */
public class LOVRule extends MHRule{
    protected static final char[] actions = {'c', 'a', 'u', 'm', 'r', 't', 'i', 'q', 's', 'p', 'C', 'A', 'U', 'M', 'R', 'T', 'I', 'Q', 'S', 'P'};

    public boolean chooseAction(Board board, Hero hero) {
        ChessPiece heroPlace = getHeroPlace(hero);
        System.out.println(BLUE + "It's hero "+hero.getMark()+"'s turn! Here is its information" + RESET);
        System.out.println(BLUE + "Hero "+hero.getMark()+" is in space ("+heroPlace.getRow()+", "+heroPlace.getCol()+")" + RESET);
        System.out.println(hero);
        System.out.println("Input your actions:");
        System.out.println(BLUE + "C/c change equipments; U/u use a potion; A/a attack" + RESET);
        System.out.println(BLUE + "S/s shift(move); T/t teleport; R/r recall; P/p pass" + RESET);
//        System.out.println(BLUE + "W/w go up; A/a go left; S/s go down; D/d go right" + RESET);
        System.out.println(GREEN + "I/i check info; M/m enter market(When heroes stand on their nexus, they can go in)" + RESET);
        System.out.println(RED + "Q/q quit game" + RESET);
        char input = ' ';
        boolean flag = false;
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
                        LOVController.getMusicPlayer().stopMusic();
                        return false;
                    }else{
                        System.out.println(GREEN+"Input other choices!"+RESET);
                    }
                    break;
                case 'S':
                case 's':
                    System.out.println("move logic");
                    flag = executeMoveLogic(board, hero);
                    break;
                case 'I':
                case 'i':
                    System.out.println("Check hero info");
                    System.out.println(hero);
                    hero.printInventory();
                    continue;
                case 'M':
                case 'm':
                    System.out.println("market logic");
                    if (isAMarket(board, hero)) {
                        executeMarketLogic(board, hero);
//                        flag = true;
                    } else {
                        System.out.println(RED + "No market here!" + RESET);
                    }
                    break;
                case 'C':
                case 'c':
                    System.out.println("change equipment logic");
                    changeEquipmentLogic(board, hero);
                    break;
                case 'U':
                case 'u':
                    System.out.println("use potion logic");
                    usePotionLogic(board, hero);
                    break;
                case 'A':
                case 'a':
                    System.out.println("attack logic");
                    flag = attackLogic(board, hero);
                    break;
                case 'T':
                case 't':
                    System.out.println("teleport logic");
                    flag = teleportLogic(board, hero);
                    break;
                case 'R':
                case 'r':
                    System.out.println("recall logic");
                    flag = recallLogic(board, hero);
                    break;
                case 'P':
                case 'p':
                    System.out.println("pass logic");
                    flag = passLogic(board, hero);
                    break;
            }
            if (flag) {
                break;
            }else{
                System.out.println(YELLOW + "Please choose a action again!" + RESET);
            }
        }
        return true;

    }
    void executeMarketLogic(Board board) {
        ChessPiece player = MHController.getPlayer();
        MarketSpace currentSpace = (MarketSpace) board.getBoard()[player.getRow() - 1][player.getCol() - 1].getSpace();
        currentSpace.interact("LOV");
    }

    private boolean passLogic(Board board, Hero hero) {
        return true;
    }

    private boolean recallLogic(Board board, Hero hero) {
        // Retrieve the original spawn positions for all heroes
        ArrayList<ChessPiece> originalHeroPlaces = LOVController.getOriginalHeroPlaces();

        // Find the specific original position for this hero
        int heroIndex = Integer.parseInt(hero.getMark().substring(1)) - 1;
        ChessPiece originalPlace = originalHeroPlaces.get(heroIndex);

        // Get the hero's current position
        ChessPiece currentPlace = getHeroPlace(hero);

        // Get target row and column for the recall
        int targetRow = originalPlace.getRow();
        int targetCol = originalPlace.getCol();

        // Debug: Print the current and original positions
        System.out.println(YELLOW + "Hero " + hero.getMark() + " current position: ("
                + currentPlace.getRow() + "," + currentPlace.getCol() + ")" + RESET);
        System.out.println(YELLOW + "Hero " + hero.getMark() + " original position: ("
                + targetRow + "," + targetCol + ")" + RESET);

        // Ensure the target position is within bounds and accessible
        if (!isValidToGo(board, targetRow, targetCol)) {
            System.out.println(RED + "Recall failed! The original position is inaccessible or occupied." + RESET);
            return false;
        }

        // Check if the target position is occupied by another hero
        for (ChessPiece heroPlace : LOVController.getHeroPlaces()) {
            if (heroPlace.getRow() == targetRow && heroPlace.getCol() == targetCol) {
                System.out.println(RED + "Recall failed! Another hero is already at the original position." + RESET);
                return false;
            }
        }
        // Move the hero to the original spawn position
        moveHero(board, targetRow, targetCol, hero);

// Print the movement details
        System.out.println(GREEN + "Hero " + hero.getMark() + " moved back to it's original spawn position ("
                + targetRow + "," + targetCol + ")." + RESET);

        return true;

    }

//    private boolean teleportLogic(Board board, Hero hero) {
//        System.out.println(BLUE + "Choose a target hero to teleport near. Here are the available heroes:" + RESET);
//
//        // Display available target heroes (excluding the hero itself)
//        ArrayList<Hero> playerHeroes = LOVController.getPlayerHeroes();
//        for (int i = 0; i < playerHeroes.size(); i++) {
//            if (!playerHeroes.get(i).equals(hero)) {
//                System.out.println((i + 1) + ". " + playerHeroes.get(i).getMark());
//            }
//        }
//
//        // Input choice for the target hero
//        int targetHeroIndex = InputRule.InputInteger(1, playerHeroes.size()) - 1;
//        Hero targetHero = playerHeroes.get(targetHeroIndex);
//
//        // Ensure the chosen target is not the current hero
//        if (targetHero.equals(hero)) {
//            System.out.println(RED + "Invalid choice: You cannot teleport to yourself." + RESET);
//            return false;
//        }
//
//        // Get the target hero's current position
//        ChessPiece targetHeroPlace = getHeroPlace(targetHero);
//        int targetRow = targetHeroPlace.getRow();
//        int targetCol = targetHeroPlace.getCol();
//
//        // Determine the lane of the target hero
//        int targetLane = getLane(targetCol);
//
//        System.out.println(BLUE + "Choose an adjacent position to teleport to:" + RESET);
//        System.out.println("1. Left of target (" + targetRow + ", " + (targetCol - 1) + ")");
//        System.out.println("2. Right of target (" + targetRow + ", " + (targetCol + 1) + ")");
//        System.out.println("3. Below target (" + (targetRow + 1) + ", " + targetCol + ")");
//
//        // Input choice for the adjacent position
//        int choice = InputRule.InputInteger(1, 3);
//        int newRow = targetRow, newCol = targetCol;
//
//        switch (choice) {
//            case 1: // Left
//                newCol = targetCol - 1;
//                // Ensure the left position stays within the target hero's lane
//                if (getLane(newCol) != targetLane) {
//                    System.out.println(RED + "Invalid teleport: Left position is outside the target hero's lane." + RESET);
//                    return false;
//                }
//                break;
//            case 2: // Right
//                newCol = targetCol + 1;
//                // Ensure the right position stays within the target hero's lane
//                if (getLane(newCol) != targetLane) {
//                    System.out.println(RED + "Invalid teleport: Right position is outside the target hero's lane." + RESET);
//                    return false;
//                }
//                break;
//            case 3: // Below
//                newRow = targetRow + 1;
//                break;
//            default:
//                System.out.println(RED + "Invalid choice!" + RESET);
//                return false;
//        }
//
//        // Validate the selected teleport destination
//        if (!isValidTeleport(board, hero, newRow, newCol)) {
//            System.out.println(RED + "Teleport failed: Invalid target position." + RESET);
//            return false;
//        }
//
//        // Perform the teleport
//        moveHero(board, newRow, newCol, hero);
//        System.out.println(GREEN + "Hero " + hero.getMark() + " successfully teleported to ("
//                + newRow + ", " + newCol + ")." + RESET);
//        return true;
//    }

    private boolean teleportLogic(Board board, Hero hero) {
        System.out.println(BLUE + "Choose a target hero to teleport near. Here are the available heroes:" + RESET);

        // Display available target heroes (excluding the hero itself)
        ArrayList<Hero> playerHeroes = LOVController.getPlayerHeroes();
        ArrayList<Hero> validTargetHeroes = new ArrayList<>();

        for (Hero h : playerHeroes) {
            if (!h.equals(hero)) {
                validTargetHeroes.add(h);
                System.out.println((validTargetHeroes.size()) + ". " + h.getMark());
            }
        }

        if (validTargetHeroes.isEmpty()) {
            System.out.println(RED + "No valid heroes to teleport near!" + RESET);
            return false;
        }

        // Input choice for the target hero
        int targetHeroIndex = InputRule.InputInteger(1, validTargetHeroes.size()) - 1;
        Hero targetHero = validTargetHeroes.get(targetHeroIndex);

        // Get the target hero's current position
        ChessPiece targetHeroPlace = getHeroPlace(targetHero);
        int targetRow = targetHeroPlace.getRow();
        int targetCol = targetHeroPlace.getCol();

        // Determine the lane of the target hero
        int targetLane = getLane(targetCol);

        // Determine valid adjacent positions for teleportation
        ArrayList<int[]> validPositions = new ArrayList<>();

        // Check each adjacent position
        int[][] potentialPositions = {
                {targetRow, targetCol - 1}, // Left
                {targetRow, targetCol + 1}, // Right
                {targetRow + 1, targetCol}  // Below
        };

        for (int[] pos : potentialPositions) {
            // Ensure the position is valid and within the same lane
            if (isValidTeleport(board, hero, pos[0], pos[1]) && getLane(pos[1]) == targetLane) {
                validPositions.add(pos);
            }
        }

        // If no valid positions are available
        if (validPositions.isEmpty()) {
            System.out.println(RED + "No valid positions available to teleport to near Hero " + targetHero.getMark() + "!" + RESET);
            return false;
        }

        // Display valid positions to the user
        System.out.println(BLUE + "Possible teleport positions:" + RESET);
        for (int i = 0; i < validPositions.size(); i++) {
            int[] pos = validPositions.get(i);
            System.out.println((i + 1) + ". (" + pos[0] + ", " + pos[1] + ")");
        }

        // Input choice for the position
        int choice = InputRule.InputInteger(1, validPositions.size()) - 1;
        int[] selectedPosition = validPositions.get(choice);
        int newRow = selectedPosition[0];
        int newCol = selectedPosition[1];

        // Perform the teleport
        moveHero(board, newRow, newCol, hero);
        System.out.println(GREEN + "Hero " + hero.getMark() + " successfully teleported to ("
                + newRow + ", " + newCol + ")." + RESET);
        return true;
    }




    private boolean isValidTeleport(Board board, Hero hero, int targetRow, int targetCol) {
        // Get the current position of the hero
        ChessPiece currentPlace = getHeroPlace(hero);
        int currentRow = currentPlace.getRow();
        int currentCol = currentPlace.getCol();

        // Determine the lanes for current and target positions
        int currentLane = getLane(currentCol);
        int targetLane = getLane(targetCol);

        // Ensure teleport is to a different lane
        if (currentLane == targetLane) {
            //System.out.println(RED + "Invalid teleport: You cannot teleport within the same lane." + RESET);
            return false;
        }

        // Check if the target position is within bounds
        if (targetRow < 1 || targetRow > board.getRow() || targetCol < 1 || targetCol > board.getCol()) {
            //System.out.println(RED + "Invalid teleport: Target position is out of bounds." + RESET);
            return false;
        }

        // Ensure the target space is not occupied by another hero
        for (ChessPiece heroPlace : LOVController.getHeroPlaces()) {
            if (heroPlace.getRow() == targetRow && heroPlace.getCol() == targetCol) {
                //System.out.println(RED + "Invalid teleport: Space is occupied by another hero." + RESET);
                return false;
            }
        }

        // General validity checks (e.g., accessibility)
        if (!isValidToGo(board, targetRow, targetCol)) {
            //System.out.println(RED + "Invalid teleport: Space is not accessible." + RESET);
            return false;
        }

        // All checks passed
        return true;
    }

    private int getLane(int column) {
        if (column == 1 || column == 2) return 1; // Top lane
        if (column == 3 || column == 4) return 2; // Middle lane
        if (column == 5 || column == 6) return 3; // Bottom lane
        return -1; // Invalid column
    }


    private boolean attackLogic(Board board, Hero hero) {
        ChessPiece heroPlace = getHeroPlace(hero);
        ArrayList<Integer[]> attackRange = calculateAttackRange(board, heroPlace.getRow(), heroPlace.getCol());
        boolean hasMonsterToAttack = false;
        for (Integer[] attack : attackRange) {
            int row = attack[0];
            int col = attack[1];
            for (ChessPiece monsterPlace: LOVController.getMonsterPlaces()){
                if (monsterPlace != null &&monsterPlace.getRow() == row && monsterPlace.getCol() == col){
                    hasMonsterToAttack = true;
                    break;
                }
            }
            if (hasMonsterToAttack){
                break;
            }
        }
        if (!hasMonsterToAttack){
            System.out.println(YELLOW+"There is no monster in your attack range!"+RESET);
            return false;
        }
        System.out.println(BLUE+"Please input the row and col of the monster you want to attack, split by space"+RESET);
        while (true){
            Integer[] space = InputRule.InputSpace(board);
            int row = space[0];
            int col = space[1];
            for (Integer[] place: attackRange){
                ChessPiece monsterPlace = null;
                if (place[0] == row && place[1] == col){
                    boolean hasMonsterInThatSpace = false;
                    for (ChessPiece oneMonsterPlace: LOVController.getMonsterPlaces()){
                        if (oneMonsterPlace != null && oneMonsterPlace.getRow()==row && oneMonsterPlace.getCol()==col){
                            monsterPlace = oneMonsterPlace;
                            hasMonsterInThatSpace = true;
                        }
                    }
                    //attack
                    if (hasMonsterInThatSpace){
                        heroAttackMonster(board, hero, monsterPlace);
                        return true;
                    }else{
                        System.out.println(YELLOW+"No monster in this space!"+RESET);
                        return false;
                    }

                }
            }
            System.out.println(YELLOW+"There is no monster in that space or you cannot attack that hero! Please input again!"+RESET);
        }

    }

    private void heroAttackMonster(Board board, Hero hero, ChessPiece monsterPlace) {
        Monster monster = getMonsterFromPlace(monsterPlace);
        int monsterIndex = Integer.parseInt(hero.getMark().substring(1))-1;
        int totalDamage = heroAttack(hero);
//                        System.out.println(GREEN+"The hero deals "+totalDamage+" damage!"+RESET);
        boolean ifDodge = dodge(monster.getDodgeChance());
        int monsterDefense = (int) (monster.getDefense()*MONSTER_DEFENSE_MULTIPLIER);

        if (ifDodge) {
            System.out.println(RED + "Monster "+monster.getMark()+" dodges the attack!!!" + RESET);
            heroAttackBeDodged(hero);
        } else {
            executeAttack(hero);
            if (totalDamage - monsterDefense <= 0){
                System.out.println(RED + "Monster "+monster.getMark()+" defends all damage!" + RESET);

            }else{
                System.out.println(RED + "Monster "+monster.getMark()+" defences "+ monsterDefense + " damage!" + RESET);
                totalDamage -= monsterDefense;
            }
            if (monster.getHp() - totalDamage <= 0) {
                System.out.println(GREEN + "Monster "+monster.getMark()+" is killed!" + RESET);
                // set null
                LOVController.getMonsterPlaces().set(monsterIndex, null);
                LOVController.getGameMonsters().set(monsterIndex, null);
            } else {
                System.out.println(GREEN + "Monster "+monster.getMark()+" has " + (monster.getHp() - totalDamage) + " hp left." + RESET);
                monster.setHp(monster.getHp() - totalDamage);
            }
        }
    }

    private Monster getMonsterFromPlace(ChessPiece monsterPlace) {
        int index = 0;
        for (ChessPiece monsterPiece: LOVController.getMonsterPlaces()){
            if (monsterPiece != null && monsterPiece.getRow() == monsterPlace.getRow() && monsterPiece.getCol() == monsterPlace.getCol()){
                return LOVController.getGameMonsters().get(index);
            }
            index++;
        }
        return null;
    }

    private boolean usePotionLogic(Board board, Hero hero) {
        boolean ifHavePotion = false;
        for (int i = 0; i < hero.getInventory().getItems().size(); i++) {
            Commodity commodity = hero.getInventory().getItems().get(i);
            if (commodity.getItem().getType().equals("Potion")){
                ifHavePotion = true;
                break;
            }
        }
        if (ifHavePotion){
            ChoiceStrategy cs;
            cs = new PotionUse();
            cs.executeChoice(hero);
            return true;
        }else {
            System.out.println(YELLOW+"You don't have any potion in your inventory."+RESET);
            return false;
        }

    }

    private boolean changeEquipmentLogic(Board board, Hero hero) {
        ChoiceStrategy cs;
        int action = heroChooseAnAction(hero);
        switch (action) {
            case 1:
                cs = new EquipSpell();
                cs.executeChoice(hero);
                break;
            case 2:
                cs = new EquipWeapon();
                cs.executeChoice(hero);
                break;
            case 3:
                cs = new EquipArmor();
                cs.executeChoice(hero);
                break;
            default:
                break;
        }
        return true;
    }

    private boolean executeMoveLogic(Board board, Hero hero) {
        System.out.println("Input your actions:");
        System.out.println(BLUE + "W/w go up; A/a go left; S/s go down; D/d go right" + RESET);
        char input = ' ';
        boolean flag = false;
        ChessPiece heroPlace = getHeroPlace(hero);
        char[] actions = {'w', 'a', 's', 'd', 'W', 'A', 'S', 'D'};
            while (true) {
                input = InputRule.ChooseActionByCharacter(actions);
                if (input != ' ') {
                    break;
                } else {
                    System.out.println(YELLOW + "Invalid input! Please input again!" + RESET);
                }
            }
            switch (input) {
                case 'A':
                case 'a':
                    if (isValidToGo(board, heroPlace.getRow(), heroPlace.getCol()-1)) {
                        if (isAccessibleNearby(board, heroPlace.getRow(), heroPlace.getCol(), LEFT)){
                            flag = executeObstacle(board, heroPlace.getRow(), heroPlace.getCol()-1, hero);
                        }
                    } else {
                        System.out.println(RED + "You can't go left!" + RESET);
                    }
                    break;
                case 'W':
                case 'w':
                    if (isValidToGo(board, heroPlace.getRow()-1, heroPlace.getCol()) && isValidToGoAbove(board, heroPlace.getRow(), heroPlace.getCol())) {
                        flag = executeObstacle(board, heroPlace.getRow()-1, heroPlace.getCol(), hero);
                    } else {
                        System.out.println(RED + "You can't go up!" + RESET);
                    }
                    break;
                case 'S':
                case 's':
                    if (isValidToGo(board, heroPlace.getRow()+1, heroPlace.getCol())) {
                        flag = executeObstacle(board, heroPlace.getRow()+1, heroPlace.getCol(), hero);
                    } else {
                        System.out.println(RED + "You can't go down!" + RESET);
                    }
                    break;
                case 'D':
                case 'd':
                    if (isValidToGo(board, heroPlace.getRow(), heroPlace.getCol()+1)) {
                        if (isAccessibleNearby(board, heroPlace.getRow(), heroPlace.getCol(), RIGHT)){
                            flag = executeObstacle(board, heroPlace.getRow(), heroPlace.getCol()+1, hero);
                        }
                    } else {
                        System.out.println(RED + "You can't go right!" + RESET);
                    }
                    break;
            }

        return flag;
    }

    private boolean executeObstacle(Board board, int row, int col, Hero hero) {
        if(isObstacle(board, row, col)){
            System.out.println(YELLOW+"It's an obstacle space, input Y/y to remove it, input others to choose another action."+RESET);
            while (true){
                char ifMove = InputRule.ChooseActionByCharacter(new char[]{'Y', 'y'});
                if (ifMove == ' '){
                    System.out.println(YELLOW+"Please choose another action."+RESET);
                    return false;
                }else {
                    changeSpaceToCommonSpace(board, row, col);
                    return true;
                }
            }
        }else{
            // moveHero
            moveHero(board, row, col, hero);
            return true;
        }
    }

    private void changeSpaceToCommonSpace(Board board, int row, int col) {
        board.getBoard()[row-1][col-1].setSpace(new CommonSpace());
    }

    private boolean isObstacle(Board board, int row, int col) {
        Space space = board.getBoard()[row-1][col-1].getSpace();
        if (space.getDescription().equals("O")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidToGoAbove(Board board, int row, int col) {
        for (ChessPiece piece: LOVController.getMonsterPlaces()){
            // now together with a monster
            if (piece != null && piece.getRow() == row && piece.getCol() == col){
                return false;
            }
            if (piece != null && col % 2 ==  0 && piece.getRow() == row && piece.getCol() == col-1){
                return false;
            }
            if (piece != null && col % 2 ==  1 && piece.getRow() == row && piece.getCol() == col+1){
                return false;
            }
        }
        return true;
    }

    private int heroChooseAnAction(Hero hero) {
        System.out.println(YELLOW+"If you choose to equip a new spell or armor, the old one will be put back to the inventory automatically."+RESET);
        System.out.println(YELLOW+"If you choose to equip a new weapon and the hero has no more hands to hold the weapon, you should choose to put some old weapons back to the inventory."+RESET);
        System.out.println("0. Print heroes' information");
        System.out.println("1. Cast spell");
        System.out.println("2. Equip weapon");
        System.out.println("3. Equip Armor");
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
            int choice = InputRule.InputInteger(0, 3);
            if(choice == 0){
                printHeroesInfo();
                System.out.println("Make another choices of 1-4");
                System.out.println("1. Cast spell");
                System.out.println("2. Equip weapon");
                System.out.println("3. Equip Armor");
            }else if (choice != -1){
                switch (choice){
                    case 1:
                        boolean spellFlag = false;
                        for (int i = 0; i < inventory.getItems().size(); i++){
                            Commodity commodity = inventory.getItems().get(i);
                            if (commodity.getItem().getType().equals("Spell")){
                                spellFlag = true;
                                break;
                            }
                        }
                        if (spellFlag){
                            return 1;
                        }else {
                            System.out.println(YELLOW+"You don't have any spell in your inventory. Please input another choice"+RESET);
                            continue;
                        }
                    case 2:
                        int weaponHands = 0;
                        if (weapons.size() == 0) {
                            if (weaponsInInventory.size()<=0){
                                System.out.println(YELLOW+"You don't have any weapon to equip! Please input another choice"+RESET);
                                continue;
                            }else{
                                return 2;
                            }
                        }else{
                            if (weaponsInInventory.size()<=0){
                                System.out.println(YELLOW+"You don't have any weapon to equip! Please input another choice"+RESET);
                            }else{
                                return 2;
                            }

                            continue;
                        }
                    case 3:
                        boolean armorFlag = false;
                        for (int i = 0; i < inventory.getItems().size(); i++) {
                            Commodity commodity = inventory.getItems().get(i);
                            if (commodity.getItem().getType().equals("Armor")){
                                armorFlag = true;
                                break;
                            }
                        }
                        if (armorFlag){
                            return 3;
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

    private void moveHero(Board board, int newRow, int newCol, Hero hero) {
        ChessPiece heroPlace = getHeroPlace(hero);
        updateHeroAttributeValue(board, hero, heroPlace.getRow(), heroPlace.getCol(), "OUT");
        heroPlace.setRow(newRow);
        heroPlace.setCol(newCol);
        updateHeroAttributeValue(board, hero, heroPlace.getRow(), heroPlace.getCol(), "IN");
    }

    private void updateHeroAttributeValue(Board board, Hero hero, int row, int col, String type) {
        if (type.equals("IN")){
            Space space = board.getBoard()[row-1][col-1].getSpace();
            switch (space.getDescription()){
                case "B":
                    space.initializeAttributeNum(hero);
                    hero.setDexterity(hero.getDexterity() + space.getAddNum());
                    break;
                case "C":
                    space.initializeAttributeNum(hero);
                    hero.setAgility(hero.getAgility() + space.getAddNum());
                    break;
                case "K":
                    space.initializeAttributeNum(hero);
                    hero.setStrength(hero.getStrength() + space.getAddNum());
                    break;
            }
        }else if (type.equals("OUT")){
            Space space = board.getBoard()[row-1][col-1].getSpace();
            switch (space.getDescription()){
                case "B":
                    hero.setDexterity(hero.getDexterity()-space.getAddNum());
                    break;
                case "C":
                    hero.setAgility(hero.getAgility() - space.getAddNum());
                    break;
                case "K":
                    hero.setStrength(hero.getStrength() - space.getAddNum());
                    break;
            }
        }
    }

    private boolean isAccessibleNearby(Board board, int row, int col, int direction) {
        if (direction == LEFT){
            if (col % 2 != 0){
                // it's inaccessible place nearby
                return false;
            }
        }else if(direction == RIGHT){
            if (col % 2 == 0){
                // it's inaccessible place nearby
                return false;
            }
        }
        return true;
    }

    public Board initializeSpace(Board board) {
        int totalCells = board.getCol() * board.getRow();
        int bushCount = (int) Math.round(totalCells * 0.20);
        int caveCount = (int) Math.round(totalCells * 0.20);
        int koulouCount = (int) Math.round(totalCells * 0.20);
        int obstacleCount = (int) Math.round(totalCells * 0.10);
        int commonCount = totalCells - bushCount - caveCount - koulouCount - obstacleCount - board.getCol()*2;
        int marketCount = board.getCol();
        int nexusCount = board.getCol();
        Random random = new Random();
        int playerPlace = random.nextInt(commonCount);

        List<Space> spaces = new ArrayList<>();
        for (int i = 0; i < bushCount; i++) spaces.add(new BushSpace());
        for (int i = 0; i < caveCount; i++) spaces.add(new CaveSpace());
        for (int i = 0; i < koulouCount; i++) spaces.add(new KoulouSpace());
        for (int i = 0; i < commonCount; i++) spaces.add(new CommonSpace());
        for (int i = 0; i < obstacleCount; i++) spaces.add(new ObstacleSpace());

        Collections.shuffle(spaces);
        Cell[][] cells = board.getBoard();

        // allocate market(nexus of heroes) spaces and nexus spaces of monsters
        for (int i = 0; i < board.getCol(); i++){
            cells[0][i].setSpace(new NexusSpace());
            cells[board.getRow()-1][i].setSpace(new MarketSpace());
        }
        // allocate spaces
        int index = 0;
        for (int i = 1; i < board.getRow()-1; i++) {
            for (int j = 0; j < board.getCol(); j++) {
                cells[i][j].setSpace(spaces.get(index));
                index++;
            }
        }


        // create heroes
        int result = random.nextInt(2);


        board.setBoard(cells);
        return board;
    }

    public void printBoard(Board board) {
        String top = "  ";
        for (int i = 0; i < board.getCol(); i++){
            top += "    "+(i+1)+"       ";
            if ((i + 1) % 2 == 0 && i != board.getCol() - 1) {
                // add inaccessible output
                top += "            ";
            }
        }
        System.out.println(top);
        for (int i = 0; i < board.getRow(); i++) {
            String up = "  ";
            String middle = (i+1)+".";
            String down = "  ";
            for (int j = 0; j < board.getCol(); j++) {

                Space space = board.getBoard()[i][j].getSpace();
                // up
                up += space.getColor()+space.getDescription() + " - " + space.getDescription() + " - " + space.getDescription() + "   "+RESET;

                // middle
                middle += space.getColor()+"| "+RESET;
                if(!printHeroMark(i+1, j+1).equals("")){
                    middle += printHeroMark(i+1, j+1);
                }else{
                    middle += "   ";
                }
                if(!printMonsterMark(i+1, j+1).equals("")){
                    middle += printMonsterMark(i+1, j+1);
                }else{
                    middle += space.getColor()+"   "+RESET;
                }
                middle+=space.getColor()+"|" + "   "+RESET;

                // down
                down += space.getColor()+space.getDescription() + " - " + space.getDescription() + " - " + space.getDescription() + "   "+RESET;
                if ((j + 1) % 2 == 0 && j != board.getCol() - 1) {
                    // add inaccessible output
                    up += RED+"I - I - I   "+RESET;
                    middle += RED+"| X X X |   "+RESET;
                    down += RED+"I - I - I   "+RESET;
                }
            }
            System.out.println(up);
            System.out.println(middle);
            System.out.println(down);
            System.out.println();
        }
    }

    private String printMonsterMark(int row, int col) {
        String output = "";
        ArrayList<ChessPiece> monsterPlaceList = LOVController.getMonsterPlaces();
        ArrayList<Monster> monsters = LOVController.getGameMonsters();
        // print heroes' mark
        for (ChessPiece monsterPlace : monsterPlaceList){
            if (monsterPlace != null && monsterPlace.getRow() == row && monsterPlace.getCol() == col){
                int index = monsterPlaceList.indexOf(monsterPlace);
                if (index < 9){
                    output += monsters.get(index).getMark()+" ";
                }else{
                    output += monsters.get(index).getMark();
                }

            }
        }
        return output;
    }

    public String printHeroMark(int row, int col){
        String output = "";
        ArrayList<ChessPiece> heroPlaceList = LOVController.getHeroPlaces();
        ArrayList<Hero> heroes = LOVController.getPlayerHeroes();
        // print heroes' mark
        for (ChessPiece heroPlace : heroPlaceList){
            if (heroPlace.getRow() == row && heroPlace.getCol() == col){
                int index = heroPlaceList.indexOf(heroPlace);
                output += heroes.get(index).getMark()+" ";
            }
        }
        return output;
    }


    public ArrayList<Hero> initializePlayerHeroes(ArrayList<Hero> allHeroes) {
        ArrayList<Hero> playerHeroes = new ArrayList<>();
        System.out.println(BLUE+"Here are all heroes you can choose:"+RESET);
        for (int i = 0; i < allHeroes.size(); i++) {
            System.out.println(i + 1 + ". " + allHeroes.get(i).toString());
        }
        System.out.println(BLUE+"Please choose three heroes."+RESET);
        System.out.println(BLUE+"Three heroes will be set sequentially as H1, H2, and H3."+RESET);
        System.out.println(BLUE+"Please enter all the required hero numbers. Split by space"+RESET);
        while (true) {
            List<Integer> heroIndexList = InputRule.InputHeroIndex(allHeroes.size(), 3);
            if (heroIndexList != null) {
                for (int j = 0; j < heroIndexList.size(); j++) {
                    Hero hero= new Hero(allHeroes.get(heroIndexList.get(j) - 1));
                    LOVController.setHeroNum(LOVController.getHeroNum()+1);
                    hero.setMark("H"+(LOVController.getHeroNum()));
                    playerHeroes.add(hero);
                    LOVController.getHeroIndexInAllHeroes().add(heroIndexList.get(j)-1);
                }
                break;
            }else{
                System.out.println(YELLOW+"invalid input, please input again"+RESET);
            }
        }

        return playerHeroes;
    }

    public ArrayList<ChessPiece> setHeroPlacesIntoBoard(Board board, int num) {
        ArrayList<ChessPiece> original = LOVController.getOriginalHeroPlaces();
        Random random = new Random();
        ArrayList<ChessPiece> heroPlaces = new ArrayList<>();
        for (int i = 0; i < num; i++){
            int result = random.nextInt(2);
            ChessPiece heroPlace = new ChessPiece(board.getRow(), i*2+result+1, -1);
            original.add(new ChessPiece(heroPlace));
            board.setPieceAt(heroPlace.getRow(), heroPlace.getCol(), heroPlace);
            heroPlaces.add(new ChessPiece(heroPlace));
        }
        return heroPlaces;
    }

    public ArrayList<Monster> generateGameMonsters(ArrayList<Monster> allMonsters, int num) {
        int highestLevel = 0;
        for (int i = 0; i < LOVController.getPlayerHeroes().size(); i++) {
            highestLevel = Math.max(highestLevel, LOVController.getPlayerHeroes().get(i).getLevel());
        }
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < num; i++){
            ArrayList<Monster> systemMonsters = new ArrayList<>();
            for (int j = 0; j < MHController.getMonsters().size(); j++) {
//                System.out.println(MHController.getMonsters().get(j));
                if (MHController.getMonsters().get(j).getLevel() == highestLevel) {
                    systemMonsters.add(new Monster(MHController.getMonsters().get(j)));
                }
            }
            // random choose a monster with level = highestLevel
            int index = (int) (Math.random() * systemMonsters.size());
            Monster monster = new Monster(allMonsters.get(index));
            LOVController.setMonsterNum(LOVController.getMonsterNum()+1);
            monster.setMark("M"+(LOVController.getMonsterNum()));
            monsters.add(monster);
        }
        return monsters;
    }

    public ArrayList<ChessPiece> setMonsterPlacesIntoBoard(Board board, int num) {
        Random random = new Random();
        ArrayList<ChessPiece> monsterPlaces = new ArrayList<>();
        for (int i = 0; i < num; i++){
            int result = random.nextInt(2);
            ChessPiece monsterPlace = new ChessPiece(1, i*2+result+1, -1);
            board.setPieceAt(1, monsterPlace.getCol(), monsterPlace);
            monsterPlaces.add(monsterPlace);
        }
        return monsterPlaces;
    }

    public boolean isValidToGo(Board board, int row, int col) {
        int indexRow = row - 1;
        int indexCol = col - 1;
        if(indexRow < 0 || indexRow >= board.getRow() || indexCol < 0 || indexCol >= board.getCol()){
            return false;
        }
        for (ChessPiece heroPlace: LOVController.getHeroPlaces()){
            if (heroPlace.getRow() == row && heroPlace.getCol() == col){
                return false;
            }
        }
        return true;
    }

    public ChessPiece getHeroPlace(Hero hero){
        int number = Integer.parseInt(hero.getMark().substring(1));
        return LOVController.getHeroPlaces().get(number-1);
    }

    boolean isAMarket(Board board, Hero hero) {
        ChessPiece heroPlace = getHeroPlace(hero);
        int row = heroPlace.getRow();
        int col = heroPlace.getCol();
        return board.getBoard()[row - 1][col - 1].getSpace() instanceof MarketSpace;
    }
    void executeMarketLogic(Board board, Hero hero) {
        ChessPiece heroPlace = getHeroPlace(hero);
        int row = heroPlace.getRow();
        int col = heroPlace.getCol();
        MarketSpace currentSpace = (MarketSpace) board.getBoard()[row - 1][col - 1].getSpace();
        currentSpace.interact("LOV");
    }

    public void actMonster(Board board, Monster monster) {
        ChessPiece monsterPlace = getMonsterPlace(monster);
        ArrayList<Integer[]> attackRange = calculateAttackRange(board, monsterPlace.getRow(), monsterPlace.getCol());
        boolean isAttack = false;
        boolean isKilled = false;
        for (Integer[] attackPos: attackRange){
            for (ChessPiece heroPlace: LOVController.getHeroPlaces()){
                if (heroPlace.getRow() == attackPos[0] && heroPlace.getCol() == attackPos[1]){
                    // attackHero
                    if(monsterAttackHero(board, monster, heroPlace)){
                        isKilled = true;
                    }
                    isAttack = true;
                    break;
                }
            }
            if (isAttack){
                break;
            }
        }
        if (!isAttack || isKilled){
            //move forward
            // blocked
            if (checkIfSpaceHasSameTypeCharacter(board, monsterPlace.getRow()+1, monsterPlace.getCol(), "M")){
                return;
            }
            else if(isObstacle(board, monsterPlace.getRow()+1, monsterPlace.getCol())){
                return;
            }
            else{
                monsterPlace.setRow(monsterPlace.getRow()+1);
            }
        }
    }

    private boolean checkIfSpaceHasSameTypeCharacter(Board board, int row, int col, String type) {
        if (type.equals("M")){
            for (ChessPiece monsterPlace: LOVController.getMonsterPlaces()){
                if (monsterPlace != null && monsterPlace.getRow() == row && monsterPlace.getCol() == col){
                    return true;
                }
            }
        }else if (type.equals("H")){
            for (ChessPiece heroPlace: LOVController.getHeroPlaces()){
                if (heroPlace.getRow() == row && heroPlace.getCol() == col){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean monsterAttackHero(Board board, Monster monster, ChessPiece heroPlace) {
        Hero hero = getHeroFromPlace(heroPlace);
        System.out.println(YELLOW+"Monster "+monster.getMark()+" attacks hero "+hero.getMark()+"!"+RESET);
        System.out.println(RED+"Monster "+monster.getName()+" is attacking!"+RESET);

        int totalDamage = (int) (monster.getBaseDamage() * MONSTER_DAMAGE_MULTIPLIER);
        System.out.println(RED+"Monster "+monster.getName()+" may cause "+totalDamage+" damage!"+RESET);
        boolean ifDodge = dodge(hero.getAgility());
        if (ifDodge) {
            System.out.println(GREEN + "The hero dodges the attack!!!" + RESET);
        } else {
            Hero returnHero = heroGetAttacked(hero, totalDamage);
            // Hero is killed
            if (returnHero == null){
                respawnHero(hero);
                return true;
            }
        }
        return false;
    }

    private void respawnHero(Hero hero) {
        System.out.println(RED+"Hero "+hero.getMark()+" is dead, respawning..."+RESET);
        int index = Integer.parseInt(hero.getMark().substring(1))-1;
        int indexOfAllHeroes = LOVController.getHeroIndexInAllHeroes().get(index);
        System.out.println(LOVController.getAllHeroes().get(indexOfAllHeroes));
        String mark = hero.getMark();
        LOVController.getPlayerHeroes().set(index, new Hero(LOVController.getAllHeroes().get(indexOfAllHeroes)));
        LOVController.getPlayerHeroes().get(index).setMark(mark);
        LOVController.getHeroPlaces().set(index, new ChessPiece(LOVController.getOriginalHeroPlaces().get(index)));
    }

    private Hero getHeroFromPlace(ChessPiece heroPlace) {
        int index = 0;
        for (ChessPiece heroPlace1: LOVController.getHeroPlaces()){
            if (heroPlace1.getRow() == heroPlace.getRow() && heroPlace1.getCol() == heroPlace.getCol()){
                return LOVController.getPlayerHeroes().get(index);
            }
            index++;
        }
        return null;
    }

    private ArrayList<Integer[]> calculateAttackRange(Board board, int row, int col) {
        ArrayList<Integer[]> attackRange = new ArrayList<>();
        attackRange.add(new Integer[]{row, col});
        //right lane
        if (col % 2 == 0){
            if (ifSpaceExist(board, row-1, col)){
                attackRange.add(new Integer[]{row-1, col});
            }
            if (ifSpaceExist(board, row-1, col-1)){
                attackRange.add(new Integer[]{row-1, col-1});
            }
            if (ifSpaceExist(board, row, col-1)){
                attackRange.add(new Integer[]{row, col-1});
            }
            if (ifSpaceExist(board, row+1, col-1)){
                attackRange.add(new Integer[]{row+1, col-1});
            }
            if (ifSpaceExist(board, row+1, col)){
                attackRange.add(new Integer[]{row+1, col});
            }
            return attackRange;
        }
        //left lane
        else{
            if (ifSpaceExist(board, row-1, col)){
                attackRange.add(new Integer[]{row-1, col});
            }
            if (ifSpaceExist(board, row-1, col+1)){
                attackRange.add(new Integer[]{row-1, col+1});
            }
            if (ifSpaceExist(board, row, col+1)){
                attackRange.add(new Integer[]{row, col+1});
            }
            if (ifSpaceExist(board, row+1, col+1)){
                attackRange.add(new Integer[]{row+1, col+1});
            }
            if (ifSpaceExist(board, row+1, col)){
                attackRange.add(new Integer[]{row+1, col});
            }
            return attackRange;
        }
    }
    private boolean ifSpaceExist(Board board, int row, int col){
        if (row > 0 && row <= board.getRow() && col > 0 && col <= board.getCol()){
            return true;
        }
        return false;
    }


    public ChessPiece getMonsterPlace(Monster monster){
        int number = Integer.parseInt(monster.getMark().substring(1));
        return LOVController.getMonsterPlaces().get(number-1);
    }

    public boolean checkMonsterGeneratePlaceAndAddMonster(int place) {
        boolean isEmpty1 = true;
        boolean isEmpty2 = true;
        for (int i=0; i<LOVController.getMonsterPlaces().size();i++){
            if (LOVController.getMonsterPlaces().get(i) != null && LOVController.getMonsterPlaces().get(i).getRow() == 1 && LOVController.getMonsterPlaces().get(i).getCol() == place*2-1){
                isEmpty1 = false;
            }
            if (LOVController.getMonsterPlaces().get(i) != null && LOVController.getMonsterPlaces().get(i).getRow() == 1 && LOVController.getMonsterPlaces().get(i).getCol() == place*2){
                isEmpty2 = false;
            }

        }
        if (isEmpty1){
            LOVController.getMonsterPlaces().add(new ChessPiece(1, place*2-1, -1));
            return true;
        }else if(!isEmpty1 && isEmpty2){
            LOVController.getMonsterPlaces().add(new ChessPiece(1, place*2, -1));
            return true;
        }else{
            return false;
        }

    }

    public int CheckWin() {
        // Check if any monster has reached the last row (row 8)
        for (ChessPiece monsterPlace : LOVController.getMonsterPlaces()) {
            if (monsterPlace != null && monsterPlace.getRow() == 8) {
                System.out.println(RED + "Monsters have reached the heroes' nexus! Monsters win!" + RESET);
                return 0; // Monsters win
            }
        }

        // Check if any hero has reached the first row (row 1)
        for (ChessPiece heroPlace : LOVController.getHeroPlaces()) {
            if (heroPlace != null && heroPlace.getRow() == 1) {
                System.out.println(GREEN + "Heroes have reached the monsters' nexus! Heroes win!" + RESET);
                return 1; // Heroes win
            }
        }

        // No winner yet
        return -1;
    }

    public void setDifficulty() {
        System.out.println("Please choose game difficulty:");
        System.out.println("1. easy\n2. medium\n3. difficult");
        while(true){
            int difficulty = InputRule.InputInteger(1, 3);
            if (difficulty == 1){
                ROUND_GENERATE_MONSTER = 8;
                break;
            }
            else if(difficulty == 2){
                ROUND_GENERATE_MONSTER = 6;
                break;
            }else if(difficulty == 3){
                ROUND_GENERATE_MONSTER = 3;
                break;
            }else {
                System.out.println(YELLOW+"Invalid input, please input again!"+RESET);
            }
        }
    }
}
