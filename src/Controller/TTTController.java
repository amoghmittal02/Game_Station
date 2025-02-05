// TTTController class controls the whole process of the tic-tac-toe.
package Controller;

import Entity.Team;
import Interface.GameFlow;
import Entity.Board;
import Rule.TTTRule;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * control tic-tac-toe
 */
public class TTTController extends NormalController implements GameFlow {
    TTTRule rule = new TTTRule();

    public TTTController() {
    }

    /**
     * initialize the game
     * @return the role of first player
     */
    @Override
    public int init() {
        this.setBoard(this.createBoard(1));

        int beginRole = this.rule.roleRule();

        this.setTeams(this.rule.teamRule(beginRole, 2, false));

        return beginRole;

    }

    /**
     * control the flow of the game
     * @param beginRole the role of first player
     */
    @Override
    public void start(int beginRole) {
        int role = beginRole;
        while(true){

            // print the board
            this.getBoard().printNormalBoard(-1);

            // choose team member
            this.chooseMember(this.getTeams(), role, this.getRule());

            // input a piece
            this.setBoard(this.rule.inputRule(role, this.getBoard()));

            // check win
            int roundWinRole = this.rule.CheckWin(this.getBoard(), this.getBoard().getWinCount());
            if (roundWinRole != -1){
                // get winner
                this.getBoard().printNormalBoard(-1);
                this.announceWinner(this.getTeams(), roundWinRole);
                break;
            }

            //check full
            if (this.getBoard().checkFull()) {
                this.getBoard().printNormalBoard(-1);
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

    public TTTRule getRule() {
        return rule;
    }

    public void setRule(TTTRule rule) {
        this.rule = rule;
    }
}
