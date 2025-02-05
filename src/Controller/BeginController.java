// BeginController class controls the beginning process of each game.
package Controller;

import Entity.MusicPlayer;
import Rule.InputRule;

import static Entity.Color.*;
import static java.lang.System.exit;

/**
 * Control the game startup and select the corresponding game type.
 */
public class BeginController {
    private int gameType;


    public BeginController() {
        chooseGame();
    }

    /**
     * choose the game
     */
    public void chooseGame() {
        System.out.println("Choose game: input 0 to exit, 1 for tic-tac-toe, 2 for order and chaos, 3 for super tic-tac-toe, 4 for quoridor, 5 for Monsters and Heroes, 6 for Legends of Valor");
        while(true){
            gameType = InputRule.InputInteger(0, 6);
            if (gameType == 0){
                exit(0);
            }
            else if(gameType == -1){
                continue;
            }else {
                System.out.println(YELLOW+"Please input a valid game number!"+RESET);
                break;
            }
        }
    }

    /**
     * start the game
     */
    public void beginGame() {
        int beginRole = -1;
        // initialize different types of game
        switch (gameType) {
            case 1:
                System.out.println("Initializing Tic-Tac-Toe...");
                TTTController tttController = new TTTController();
                beginRole = tttController.init();
                tttController.start(beginRole);
                break;
            case 2:
                System.out.println("Initializing Order and Chaos...");
                OACController oacController = new OACController();
                beginRole = oacController.init();
                oacController.start(beginRole);
                break;
            case 3:
                System.out.println("Initializing Super Tic-Tac-Toe...");
                SuperTTTController superTTTController = new SuperTTTController();
                beginRole = superTTTController.init();
                superTTTController.start(beginRole);
                break;
            case 4:
                System.out.println();
                System.out.println(YELLOW + "Initializing Quoridor Game..." + RESET);
                System.out.println();
                System.out.println(RED + "BEFORE WE START:" + RESET);
                System.out.println();
                System.out.println(BLUE + "FENCING DIRECTIONS: By inputting the index, the fence will start at the left corner of the box,then choose the direction of fence placement followed by the prompt. The fence will be placed in length of two in the direction given by player." + RESET);
                System.out.println();
                System.out.println(BLUE + "HOW TO WIN: First to invade opposite oppoents base (First to hit the first column of opposite side of your prawn spawn)" + RESET);
                System.out.println();
                QuoridorController quoridorController = new QuoridorController();
                beginRole = quoridorController.init();
                quoridorController.start(beginRole);
                break;
            case 5:
                System.out.println();
                System.out.println(GREEN + "Initializing Monsters and Heroes Game..." + RESET);
                System.out.println();
                System.out.println(RED + "BEFORE WE START:" + RESET);
                System.out.println();
                System.out.println(BLUE + "GAME REMINDER:" + RESET);
                System.out.println(BLUE + "1. You can choose any number of heroes, unless the limit is exceeded." + RESET);
                System.out.println(BLUE + "2. You can stand on a Market Space, then enter M/m to get in." + RESET);
                System.out.println(BLUE + "3. You may face monsters when you go through a Common Space." + RESET);
                System.out.println(BLUE + "4. Every hero has its own gold, experience and level, it does not share anything with other heroes." + RESET);
                System.out.println(RED + "5. In the game especially during fight processes, please read prompts carefully, you may waste a turn by wrong operations!" + RESET);
                System.out.println();
                System.out.println(YELLOW + "HOW TO WIN: The game does not have a win condition, it will only end when you input Q/q to quit the total game!" + RESET);
                System.out.println();
                System.out.println("Can we get started? Input Y/y to continue! Input others to quit.");
                while (true){
                    char input = InputRule.ChooseActionByCharacter(new char[]{'Y', 'y'});
                    if (input == ' '){
                        return;
                    }else {
                        System.out.println(BLUE + "Enjoy your game!" + RESET);
                        break;
                    }
                }
                MHController mhController = new MHController();
                beginRole = mhController.init();
                mhController.start(beginRole);
                break;
            case 6:
                System.out.println();
                System.out.println(GREEN + "Initializing Monsters and Heroes Game..." + RESET);
                System.out.println();
                System.out.println(RED + "BEFORE WE START:" + RESET);
                System.out.println();
                System.out.println(BLUE + "GAME REMINDER:" + RESET);
                System.out.println(BLUE + "1. You need to choose three heroes." + RESET);
                System.out.println(BLUE + "2. You can choose S/s first, then input W/w, A/a, S/s or D/d to move." + RESET);
                System.out.println(BLUE + "3. When a hero encounters a monster, he cannot cross it." + RESET);
                System.out.println(BLUE + "4. A hero cannot recall when some heroes in its spawn space." + RESET);
                System.out.println(BLUE + "5. Heroes cannot teleport in front of other heroes." + RESET);
                System.out.println(RED + "6. Every hero has its own gold, experience and level, it does not share anything with other heroes." + RESET);
                System.out.println();
                System.out.println(YELLOW + "HOW TO WIN: If the hero reaches the monster's nexus, the hero wins, otherwise the monster wins!" + RESET);
                System.out.println(YELLOW + "You can input Q/q to quit the game!" + RESET);
                System.out.println();
                System.out.println("Can we get started? Input Y/y to continue! Input others to quit.");
                while (true){
                    char input = InputRule.ChooseActionByCharacter(new char[]{'Y', 'y'});
                    if (input == ' '){
                        return;
                    }else {
                        System.out.println(BLUE + "Enjoy your game!" + RESET);
                        break;
                    }
                }
                LOVController lovController = new LOVController();
                beginRole = lovController.init();
                lovController.start(beginRole);
                break;
            default:
                break;
        }
    }
}