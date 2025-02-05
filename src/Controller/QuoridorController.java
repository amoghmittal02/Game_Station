// QuoridorController class controls the whole process of the Quoridor.
package Controller;

import Entity.ChessPiece;
import Interface.GameFlow;
import Rule.QuoridorRule;
import Rule.SuperTTTRule;

import java.util.ArrayList;
import java.util.List;

public class QuoridorController extends NormalController implements GameFlow {
    private static int playerNum;
    private static int[] playerFences;
    private static ChessPiece[] playerChessPieces;

    private static List<Integer[]> winStatus = new ArrayList<>();

    private static
    QuoridorRule rule = new QuoridorRule();
    @Override
    public int init() {
        this.setBoard(this.createBoard(4));

//        int beginType = this.rule.roleRule();
        playerNum = rule.playerNumRule();

        initPlayerFencesAndPieces();

        int beginType = 1;

        this.setTeams(rule.teamRule(beginType, playerNum, true));

        return beginType;
    }

    @Override
    public void start(int beginRole) {
        int role = beginRole;
        while(true){

            // print the board
            this.getBoard().printIntervalBoard();
//            exit();

            // choose team member
            this.chooseMember(this.getTeams(), role, this.getRule());

            // move pawn or set fence
            this.setBoard(rule.inputRule(role, this.getBoard(), playerFences, playerChessPieces));

            // check win
            boolean roundWinRole = rule.checkWin(role);
            if (roundWinRole){
                // get winner
                this.getBoard().printIntervalBoard();
                this.announceWinner(this.getTeams(), role);
                break;
            }


            if (role < playerNum) {
                role++;
            } else {
                role = 1;
            }
        }
    }

    @Override
    public Integer winCheck() {
        return -1;
    }

    public static int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        QuoridorController.playerNum = playerNum;
    }

    public QuoridorRule getRule() {
        return rule;
    }

    public void setRule(QuoridorRule rule) {
        QuoridorController.rule = rule;
    }

    public void initPlayerFencesAndPieces(){
        playerChessPieces = new ChessPiece[playerNum];

        if (playerNum == 2){
            playerFences = new int[]{10, 10};
            playerChessPieces[0] = new ChessPiece(5, 1, 1);
            playerChessPieces[1] = new ChessPiece(5, 9, 2);
            winStatus.add(new Integer[]{-1, 9});
            winStatus.add(new Integer[]{-1, 1});
        }
        else{
            playerFences = new int[]{5, 5, 5, 5};
            playerChessPieces[0] = new ChessPiece(5, 1, 1);
            playerChessPieces[1] = new ChessPiece(9, 5, 2);
            playerChessPieces[2] = new ChessPiece(5, 9, 3);
            playerChessPieces[3] = new ChessPiece(1, 5, 4);
            winStatus.add(new Integer[]{-1, 9});
            winStatus.add(new Integer[]{1, -1});
            winStatus.add(new Integer[]{-1, 1});
            winStatus.add(new Integer[]{9, -1});
        }

        this.getBoard().updatePieces(playerChessPieces);
    }

    public static int[] getPlayerFences() {
        return playerFences;
    }

    public static void setPlayerFences(int[] playerFences) {
        QuoridorController.playerFences = playerFences;
    }

    public static ChessPiece[] getPlayerChessPieces() {
        return playerChessPieces;
    }

    public static void setPlayerChessPieces(ChessPiece[] playerChessPieces) {
        QuoridorController.playerChessPieces = playerChessPieces;
    }

    public static List<Integer[]> getWinStatus() {
        return winStatus;
    }

    public static void setWinStatus(List<Integer[]> winStatus) {
        QuoridorController.winStatus = winStatus;
    }


}
