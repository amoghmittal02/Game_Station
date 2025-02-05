package Rule;

import Entity.Board;
import Entity.ChessPiece;

/**
 * check different rows, columns, diagonals for winning
 */
public class WinRule {

    /**
     * check each row
     * @param board
     * @param row
     * @param winCount
     * @return the role of the winner
     */
    public static int checkRow(Board board, int row, int winCount) {
        int count = 0;
        ChessPiece lastPiece = null;

        for (int col = 0; col < board.getCol(); col++) {
            ChessPiece currentPiece = board.getBoard()[row][col].getChessPiece();
            if (currentPiece != null && (lastPiece == null || currentPiece.getRole() == lastPiece.getRole())) {
                count++;
                lastPiece = currentPiece;
            } else {
                count = 0; // reset the count
                lastPiece = currentPiece;
            }

            if (count >= winCount && currentPiece != null) {
                return currentPiece.getRole(); // get the role of the winner
            }
        }
        return -1;
    }

    /**
     * check each column
     * @param board
     * @param col
     * @param winCount
     * @return the role of the winner
     */
    public static int checkColumn(Board board, int col, int winCount) {
        int count = 0;
        ChessPiece lastPiece = null;

        for (int row = 0; row < board.getRow(); row++) {
            ChessPiece currentPiece = board.getBoard()[row][col].getChessPiece();
            if (currentPiece != null && (lastPiece == null || currentPiece.getRole() == lastPiece.getRole())) {
                count++;
                lastPiece = currentPiece;
            } else {
                count = 0; // reset the count
                lastPiece = currentPiece;
            }

            if (count >= winCount && currentPiece != null) {
                return currentPiece.getRole();
            }
        }
        return -1;
    }

    /**
     * check each diagonal
     * @param board
     * @param winCount
     * @return the role of the winner
     */
    public static int checkAllDiagonals(Board board, int winCount) {


        // check from left top to right bottom
        for (int startRow = 0; startRow < board.getRow(); startRow++) {
            int role = checkDiagonal(board, startRow, 0, 1, 1, winCount);
            if(role != -1){
                return role;
            }

        }
        for (int startCol = 1; startCol < board.getCol(); startCol++) {
            int role = checkDiagonal(board, 0, startCol, 1, 1, winCount);
            if(role != -1){
                return role;
            }
        }

        // check from right top to left bottom
        for (int startRow = 0; startRow < board.getRow(); startRow++) {
            int role = checkDiagonal(board, startRow, board.getCol() - 1, 1, -1, winCount);
            if(role != -1){
                return role;
            }
        }
        for (int startCol = board.getCol() - 2; startCol >= 0; startCol--) {
            int role = checkDiagonal(board, 0, startCol, 1, -1, winCount);
            if(role != -1){
                return role;
            }
        }

        return -1;
    }

    /**
     * check a specific diagonal
     * @param board
     * @param startRow
     * @param startCol
     * @param rowIncrement
     * @param colIncrement
     * @param winCount
     * @return the role of the winner
     */
    public static int checkDiagonal(Board board, int startRow, int startCol, int rowIncrement, int colIncrement, int winCount) {
        int count = 0;
        ChessPiece lastPiece = null;

        int row = startRow;
        int col = startCol;

        while (row < board.getRow() && col < board.getCol() && row >= 0 && col >= 0) {
            ChessPiece currentPiece = board.getBoard()[row][col].getChessPiece();
            if (currentPiece != null && (lastPiece == null || currentPiece.getRole() == lastPiece.getRole())) {
                count++;
                lastPiece = currentPiece;
            } else {
                count = 0;
                lastPiece = currentPiece;
            }

            if (count >= winCount && currentPiece != null) {
                return currentPiece.getRole();
            }

            row += rowIncrement;
            col += colIncrement;
        }

        return -1;
    }

    /**
     * check if the current piece is in winning row or column
     * @param currentPiece
     * @param winStatus
     * @return
     */
    public static boolean checkIfInOneRowOrCol(ChessPiece currentPiece, Integer[] winStatus){
        int currentRow = currentPiece.getRow();
        int currentCol = currentPiece.getCol();
        int winRow = winStatus[0];
        int winCol = winStatus[1];
        if(winRow == -1){
            if (currentCol == winCol){
                return true;
            }
            else{
                return false;
            }
        }
        else if(winCol == -1){
            if(winRow == currentRow){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }


}