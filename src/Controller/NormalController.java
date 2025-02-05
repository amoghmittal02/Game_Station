// This class stores the control methods shared by the three games to achieve code reuse.
package Controller;

import Rule.InputRule;
import Entity.Board;
import Entity.Team;
import Rule.NormalRule;

import java.util.Scanner;

import static java.lang.System.exit;

public class NormalController {
    private static Board board;

    private Team[] Teams;

    private NormalRule rule;


    /**
     * create a board depending on the type of the game
     * @param type
     * @return
     */
    public Board createBoard(int type){
        if (type == 1){
            int n = 0;
            System.out.println("input n to create the board in size n * n, 3 <= n <= 15: ");
            while(true){
                n = InputRule.InputInteger(3, 15);
                if(n == -1){
                    continue;
                }else {
                    break;
                }
            }

            return new Board(n, n, type, 3);
        }
        else if (type == 2){
            return new Board(6, 6, type, 5);
        }
        else if(type == 3){
            return new Board(9, 9, type, 3);
        }
        else if(type == 4){
            return new Board(9, 9, type, true);
        }
        else if(type == 5){
            return new Board(8, 8);
        }
        else if(type == 6){
            return new Board(8, 6);
        }
        else {
            return null;
        }
    }

    /**
     * choose the member of the team or the player
     * @param teams
     * @param role
     */
    public void chooseMember(Team[] teams, int role, NormalRule rule){
        if (teams != null) {
            int member = rule.checkTeamMember(role, teams);

            System.out.println("team " + teams[role - 1].getName() + " player " + member + "'s turn." + "Your Char: (" + (char)('A' + role - 1) + ")");

        } else {
            System.out.println("player " + role + "'s turn");
        }
    }

    /**
     * announce the winner
     * @param teams
     * @param winRole
     */
    public void announceWinner(Team[] teams, int winRole){
        if (teams != null){
            System.out.println("team " + teams[winRole - 1].getName() + " win");
        }else {
            System.out.println("player " + winRole + " win");
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Entity.Team[] getTeams() {
        return Teams;
    }

    public void setTeams(Entity.Team[] teams) {
        Teams = teams;
    }
}
