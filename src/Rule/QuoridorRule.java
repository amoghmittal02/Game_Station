package Rule;

import Controller.QuoridorController;
import Entity.Board;
import Entity.ChessPiece;
import java.util.*;

/**
 * rules for QuoridorRule
 */
public class QuoridorRule extends NormalRule{
    @Override
    public int roleRule() {
        return -1;
    }

    /**
     * check whether the input is valid
     * @param role
     * @param board
     * @param fenceNums
     * @param currentPieces
     * @return updated board
     */
    public Board inputRule(int role, Board board, int[] fenceNums, ChessPiece[] currentPieces){
        if (fenceNums[role-1] > 0){
            System.out.println("you can choose to move your pawn or setting a fence");
            System.out.println("Input 1 for moving pawn, input 2 for setting fence");
            int choice = -1;
            while(true){
                choice = InputRule.InputInteger(1, 2);
                if (choice == -1) {
                    continue;
                }
                else
                    break;
            }
            if (choice == 1) {
                while(true) {
                    System.out.println("Input a cell with row and column, split by a space. e.g.(1 1):");
                    Integer[] numbers = InputRule.InputPiece(board);
                    List<Integer[]> possibleMoves = getPossibleMoves(board, currentPieces[role-1]);
                    // System.out.println(possibleMoves.contains(numbers));
                    Iterator<Integer[]> iterator = possibleMoves.iterator();
                    boolean ifInMoves = false;
                    while (iterator.hasNext()) {
                        Integer[] move = iterator.next();
                        int newRow = move[0];
                        int newCol = move[1];
                        if (newRow == numbers[0] && newCol == numbers[1]) {
                            currentPieces[role-1] = board.moveOnePiece(numbers[0], numbers[1], currentPieces[role-1]);
                            QuoridorController.setPlayerChessPieces(currentPieces);
                            ifInMoves = true;
                            break;
                        }
                    }
                    if(!ifInMoves){
                        System.out.println("It's an invalid input, please input again");
                    }else{
                        break;
                    }


                }
            } else if (choice == 2) {
                System.out.println("You have "+ fenceNums[role-1] + " fences left");
                System.out.println("The intersection of the first row interval and the first column interval is interval point (1,1), i.e.the top-left corner of the first cell.");
                while (true){
                    System.out.println();
                    System.out.println("Input a interval point with row and column, split by a space. e.g.(1 1):");
                    Integer[] numbers = InputRule.InputFence(board);
                    System.out.println("Input the direction for placing the fence: 1 for up, 2 for down, 3 for left, 4 for right.");
                    int direction = InputRule.InputInteger(1, 4);
                    if(validDirection(direction, board, numbers[0], numbers[1])){
                        // 设置fence
                        board.setNewFence(numbers[0], numbers[1], direction, role);
                        if (checkIfBlock(board)){
                            System.out.println("It will cause a forbidden status, some players' chess pieces are blocked! Please input again");
                            board.removeFence(numbers[0], numbers[1], direction, role);
                            continue;
                        }else{
                            board.setNewFence(numbers[0], numbers[1], direction, role);
                            fenceNums[role-1] = fenceNums[role-1] - 1;
                            QuoridorController.setPlayerFences(fenceNums);
                            break;
                        }

                    }else{
                        System.out.println("It's an invalid direction to set a fence, please input the fence.");
                    }

                }
            }
        }else {
            System.out.println("you can only move your pawn");
        }

        return board;
    }

    /**
     * check current direction whether valid(no fence, no piece, no block)
     * @param direction
     * @param board
     * @param row
     * @param col
     * @return
     */
    private boolean validDirection(int direction, Board board, int row, int col) {
        return board.checkDirectionValid(row, col, direction) && !checkIfBlocked(row, col, board, direction);
    }

    /**
     * check current position, whether it is blocked
     * @param row
     * @param col
     * @param board
     * @param direction
     * @return
     */
    private boolean checkIfBlocked(int row, int col, Board board, int direction) {
        return false;
    }

    public int playerNumRule() {
        System.out.println("you can choose to play in 2 players/teams or 4 players/teams");
        System.out.println("input 1 for 2 players/teams, input 2 for 4 players/teams");
        int num;
        while(true){
            num = InputRule.InputInteger(1, 2);
            if (num == -1){
                continue;
            }
            else
                break;
        }
        return num*2;
    }

    /**
     * get possible moves for one piece
     * @param board
     * @param currentPiece
     * @return
     */
    public List<Integer[]> getPossibleMoves(Board board, ChessPiece currentPiece){
        int row = currentPiece.getRow();
        int col = currentPiece.getCol();

        List<Integer[]> possibleMoves = new ArrayList<>();

        List<Integer[]> noFenceMoves = new ArrayList<>();

        // check fence around
        noFenceMoves = checkPieceRoundFence(row, col, noFenceMoves, board); // top

        //check piece around
        Iterator<Integer[]> iterator = noFenceMoves.iterator();
        while (iterator.hasNext()) {
            Integer[] move = iterator.next();
            int newRow = move[0];
            int newCol = move[1];
            // no piece
            if(board.checkValid(newRow, newCol)){
                possibleMoves.add(move);
            }

            else{
                possibleMoves.addAll(possibleMovesForNearPiece(board, newRow, newCol));
            }

        }
        return possibleMoves;
    }

    /**
     * check piece around
     * @param board
     * @param row
     * @param col
     * @return
     */
    public List<Integer[]> possibleMovesForNearPiece(Board board, int row, int col){
        List<Integer[]> moves = new ArrayList<>();

        //up
        if (isValidPosition(row - 1, col, board)) {
            // no piece and empty
//            System.out.println(!board.checkFenceExist(row, col, 1));
//            System.out.println(board.checkCellFull(row - 1, col));
            if (!board.checkFenceExist(row, col, 1) && !board.checkCellFull(row - 1, col)) {
                moves.add(new Integer[]{row - 1, col});
            }
        }
        //down
        if (isValidPosition(row + 1, col, board)) {
//            System.out.println(!board.checkFenceExist(row, col, 2));
//            System.out.println(board.checkCellFull(row + 1, col));
            if (!board.checkFenceExist(row, col, 2) && !board.checkCellFull(row + 1, col)) {
                moves.add(new Integer[]{row + 1, col});
            }
        }
        // left
        if (isValidPosition(row, col - 1, board)) {
//            System.out.println(!board.checkFenceExist(row, col, 3));
//            System.out.println(board.checkCellFull(row, col - 1));
            if (!board.checkFenceExist(row, col, 3) && !board.checkCellFull(row, col - 1)) {
                moves.add(new Integer[]{row, col - 1});
            }
        }
        // right
        if (isValidPosition(row, col + 1, board)) {
//            System.out.println(!board.checkFenceExist(row, col, 4));
//            System.out.println(board.checkCellFull(row, col + 1));
            if (!board.checkFenceExist(row, col, 4) && !board.checkCellFull(row, col + 1)) {
                moves.add(new Integer[]{row, col + 1});
            }
        }
        return moves;
    }

    /**
     * check piece around
     * @param row
     * @param col
     * @param moves
     * @param board
     * @return
     */
    public List<Integer[]> checkPieceRoundFence(int row, int col, List<Integer[]> moves, Board board){

        // up
        if (isValidPosition(row - 1, col, board)) {
            if (!board.checkFenceExist(row, col, 1)) {
                moves.add(new Integer[]{row - 1, col});
            }
        }
        // down
        if (isValidPosition(row + 1, col, board)) {
            if (!board.checkFenceExist(row, col, 2)) {
                moves.add(new Integer[]{row + 1, col});
            }
        }
        // left
        if (isValidPosition(row, col - 1, board)) {
            if (!board.checkFenceExist(row, col, 3)) {
                moves.add(new Integer[]{row, col - 1});
            }
        }
        // right
        if (isValidPosition(row, col + 1, board)) {
            if (!board.checkFenceExist(row, col, 4)) {
                moves.add(new Integer[]{row, col + 1});
            }
        }

        return moves;
    }

    /**
     * check if position is valid
     * @param row
     * @param col
     * @param board
     * @return
     */
    private boolean isValidPosition(int row, int col, Board board) {
        return row > 0 && row <= board.getRow() && col > 0 && col <= board.getCol();
    }

    public boolean checkWin(int role){
        return WinRule.checkIfInOneRowOrCol(QuoridorController.getPlayerChessPieces()[role - 1], QuoridorController.getWinStatus().get(role - 1));
    }

    /**
     * check if some pieces are blocked
     * @param board
     * @return
     */
    public boolean checkIfBlock(Board board){
        int playerNum = QuoridorController.getPlayerNum();
        for (int role = 1; role <= playerNum; role++) {
            boolean ifValid = false;
            ChessPiece currentPiece = QuoridorController.getPlayerChessPieces()[role - 1];
            List<Integer[]> currentMoves = checkPieceRoundFence(currentPiece.getRow(), currentPiece.getCol(), new ArrayList<>(), board);
            List<Integer[]> allPossibleMoves = AllPossibleMove(new ArrayList<>(), currentMoves, board);
            int winRow = QuoridorController.getWinStatus().get(role - 1)[0];
            int winCol = QuoridorController.getWinStatus().get(role - 1)[1];
            for (Integer[] move : allPossibleMoves) {
                int row = move[0];
                int col = move[1];
                if(winRow == -1){
                    if (col == winCol){
                        ifValid = true;
                        break;
                    }
                }
                else if(winCol == -1){
                    if(winRow == row){
                        ifValid = true;
                        break;
                    }
                }
            }
            if (ifValid){
                continue;
            }else{
                return true;
            }
        }

        return false;
    }

    /**
     * get all possible moves
     * @param allPossibleMoves
     * @param currentMoves
     * @param board
     * @return
     */
    public List<Integer[]> AllPossibleMove(List<Integer[]> allPossibleMoves, List<Integer[]> currentMoves, Board board){
        List<Integer[]> newMoves = new ArrayList<>();
        for (int i = 0; i < currentMoves.size(); i++) {
            int row = currentMoves.get(i)[0];
            int col = currentMoves.get(i)[1];
            newMoves.addAll(checkPieceRoundFence(row, col, new ArrayList<>(), board));
        }
        // remove duplicate piece
        Set<Integer[]> uniqueMoves = new HashSet<>(newMoves);
        newMoves.clear();
        newMoves.addAll(uniqueMoves);

        Iterator<Integer[]> newIterator = newMoves.iterator();
        // check if new move is duplicate
        while (newIterator.hasNext()){
            boolean ifNew = true;
            Integer[] newMove = newIterator.next();
            Iterator<Integer[]> AllIterator = allPossibleMoves.iterator();
            while (AllIterator.hasNext()){
                Integer[] allMove = AllIterator.next();
                // 有重复
                if (newMove[0] == allMove[0] && newMove[1] == allMove[1]){
                    newIterator.remove();
                    ifNew = false;
                    break;
                }
            }
        }
        if (newMoves.isEmpty()){
            return allPossibleMoves;
        }else {
            allPossibleMoves.addAll(newMoves);
            return AllPossibleMove(allPossibleMoves, newMoves, board);
        }
    }
}
