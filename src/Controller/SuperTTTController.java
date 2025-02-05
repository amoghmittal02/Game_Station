// SuperTTTController class controls the whole process of the super tic-tac-toe.
package Controller;

import Entity.Board;
import Entity.ChessPiece;
import Entity.Team;
import Interface.GameFlow;
import Rule.SuperTTTRule;

/**
 * control the flow of super tic-tac-toe
 */
public class SuperTTTController extends TTTController implements GameFlow {
    // the offsets of each small board
    static int[][] offsets = {
            {0, 0}, {0, 3}, {0, 6},  // A, B, C
            {3, 0}, {3, 3}, {3, 6},  // D, E, F
            {6, 0}, {6, 3}, {6, 6}   // G, H, I
    };

    // the offsets of the super board
    static int[][] superOffsets = {
            {0, 0}, {0, 1}, {0, 2},  // A, B, C
            {1, 0}, {1, 1}, {1, 2},  // D, E, F
            {2, 0}, {2, 1}, {2, 2}   // G, H, I
    };
    Board superBoard = new Board(3, 3, 1, 3);
    SuperTTTRule rule = new SuperTTTRule();


    /**
     *  announce the winner of each part board
     * @param teams
     * @param roundWinRole
     * @param winPart
     */
    public void announcePartWinner(Team[] teams, int roundWinRole, int winPart){
        if (teams != null){
            System.out.println("team " + teams[roundWinRole - 1].getName() + " win " + (char)('A' + winPart) + " part!");
        }else {
            System.out.println("player " + roundWinRole + " win " + (char)('A' + winPart) + " part!");
        }
    }
    /**
     * initialize the game
     * @return begin role(x or o)
     */
    @Override
    public int init() {
        this.setBoard(this.createBoard(3));

        int beginRole = this.rule.roleRule();

        this.setTeams(this.rule.teamRule(beginRole, 2, false));

        return beginRole;
    }

    /**
     * start the game,control the flow of the game
     * @param beginRole
     */
    @Override
    public void start(int beginRole) {
        int role = beginRole;
        while(true){

            // print the board
            this.getBoard().printNormalBoard(3);

            // choose team member
            this.chooseMember(this.getTeams(), role, this.getRule());

            // input a piece
            this.setBoard(this.rule.inputRule(role, this.getBoard()));

            // check part win
            int[] superCheckResult = this.rule.superCheckWin(this.getBoard(), this.getBoard().getWinCount());
            if (superCheckResult != null) {
                int index = superCheckResult[0];
                int roleWin = superCheckResult[1];
                // fill the win part
                this.setBoard(this.rule.resetWinPart(this.getBoard(), index));
                // update the superBoard
                int superRowOffSet = superOffsets[index][0];
                int superColOffSet = superOffsets[index][1];
                this.getSuperBoard().setPieceAt(superRowOffSet + 1, superColOffSet + 1, new ChessPiece(superRowOffSet, superColOffSet, roleWin));
                System.out.println("super board:");
                this.getSuperBoard().printNormalBoard(-1);
                this.announcePartWinner(this.getTeams(), roleWin, index);
            }

            // check super win
            int roundWinRole = this.rule.CheckWin(this.getSuperBoard(), this.getSuperBoard().getWinCount());
            if (roundWinRole != -1){
                // get winner
                System.out.println("super board:");
                this.getSuperBoard().printNormalBoard(-1);
                this.announceWinner(this.getTeams(), roundWinRole);
                break;
            }

            //check part full
            int partFullIndex = this.rule.superCheckFull(this.getBoard(), this.getSuperBoard());
            if (partFullIndex != -1) {
                // update the superBoard
                int superRowOffSet = superOffsets[partFullIndex][0];
                int superColOffSet = superOffsets[partFullIndex][1];
                this.getSuperBoard().setPieceAt(superRowOffSet + 1, superColOffSet + 1, new ChessPiece(superRowOffSet, superColOffSet, 3));
            }

            //check full
            if (this.getSuperBoard().checkFull()) {
                System.out.println("super board:");
                this.getSuperBoard().printNormalBoard(-1);
                System.out.println("the board is full, game end");
                break;
            }

            if (role == 1) {
                role = 2;
            } else {
                role = 1;
            }
        }
    }

    @Override
    public Integer winCheck() {
        return -1;
    }


    public Board getSuperBoard() {
        return superBoard;
    }

    public void setSuperBoard(Board winBoard) {
        this.superBoard = winBoard;
    }

    public SuperTTTRule getRule() {
        return rule;
    }

    public void setRule(SuperTTTRule rule) {
        this.rule = rule;
    }

    public static int[][] getOffsets() {
        return offsets;
    }

    public static void setOffsets(int[][] offsets) {
        SuperTTTController.offsets = offsets;
    }

    public static int[][] getSuperOffsets() {
        return superOffsets;
    }

    public static void setSuperOffsets(int[][] superOffsets) {
        SuperTTTController.superOffsets = superOffsets;
    }
}
