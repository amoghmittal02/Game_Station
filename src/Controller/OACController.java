// OACController class controls the whole process of the order and chaos.
package Controller;

import Entity.Board;
import Entity.Team;
import Interface.GameFlow;
import Rule.OACRule;
import Rule.TTTRule;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * specific controller of order and chaos
 */
public class OACController extends NormalController implements GameFlow {
    OACRule rule = new OACRule();

    public OACController() {
    }


    /**
     * announce winner for OAC
     * @param teams
     * @param winner
     */
    public void announceWinner(Team[] teams, String winner){
        if (winner.equals("order")){
            if (teams != null){
                System.out.println("team " + teams[0].getName() + " order win");
            }
            else {
                System.out.println("player " + 1 + " order win");
            }
        }
        else if (winner.equals("chaos")){
            if (teams != null){
                System.out.println("team " + teams[1].getName() + " order win");
            }
            else {
                System.out.println("player " + 2 + " order win");
            }
        }
    }

    /**
     * initialize the game
     * @return begin type(order or chaos)
     */
    @Override
    public int init() {
        this.setBoard(this.createBoard(2));

        int beginType = this.rule.roleRule();

        this.setTeams(this.rule.teamRule(beginType, 2, false));

        return beginType;
    }

    /**
     * start the game, control the flow of the game
     * @param beginType
     */
    @Override
    public void start(int beginType) {
        int type = beginType;
        while(true){

            // print the board
            this.getBoard().printNormalBoard(-1);

            // choose team member
            this.chooseMember(this.getTeams(), type, this.getRule());

            // choose a role
            int currentPieceRole = this.rule.chooseRole(type);

            // input a piece
            this.setBoard(this.rule.inputRule(currentPieceRole, this.getBoard()));

            // check win
            int roundWinRole = this.rule.CheckWin(this.getBoard(), this.getBoard().getWinCount());
            if (roundWinRole != -1){
                // get winner
                this.getBoard().printNormalBoard(-1);
                this.announceWinner(this.getTeams(), "order");
                break;
            }

            //check full
            if (this.getBoard().checkFull()) {
                this.getBoard().printNormalBoard(-1);
                this.announceWinner(this.getTeams(), "chaos");
                System.out.println("the board is full, game end");
                break;
            }

            if (type == 1) {
                type = 2;
            } else {
                type = 1;
            }
        }
    }

    @Override
    public Integer winCheck() {
        return -1;
    }

    public OACRule getRule() {
        return rule;
    }

    public void setRule(OACRule rule) {
        this.rule = rule;
    }

}
