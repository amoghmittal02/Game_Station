package Rule;

import Controller.SuperTTTController;
import Entity.Board;
import Entity.Cell;
import Entity.ChessPiece;
import Interface.SpecificRule;

import java.util.ArrayList;
import java.util.List;


import static Rule.WinRule.*;

/**
 * rules of super tic-tac-toe
 */
public class SuperTTTRule extends TTTRule{
    /**
     * check whether the input is valid
     * @param role
     * @param board
     * @return
     */
    public Board inputRule(int role, Board board){
        System.out.println("input a cell with row and column, split by a space. e.g.(A,1 1):");
        Integer[] numbers = InputRule.SuperInputPiece(board);
        board.changeOnePiece(numbers[1], numbers[2], role);
        return board;
    }

    /**
     * split small boards from 9*9 board
     * @param board
     * @return small boards
     */
    public List<Board> getEachSmallBoard(Board board){
        List<Board> smallBoards = new ArrayList<>();
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                Cell[][] smallBoardCells = new Cell[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        smallBoardCells[i][j] = board.getBoard()[blockRow * 3 + i][blockCol * 3 + j];
                    }
                }

                Board smallBoard = new Board(3, 3, board.getType(), board.getWinCount());
                smallBoard.setBoard(smallBoardCells);
                smallBoards.add(smallBoard);
            }
        }
        return smallBoards;
    }

    /**
     * check each part winner for super tic-tac-toe
     * @param board
     * @param winCount
     * @return index of the winner and role
     */
    public int[] superCheckWin(Board board, int winCount) {

        List<Board> smallBoards = getEachSmallBoard(board);
        int index = 0;
        for (Board smallBoard : smallBoards){
            int role = CheckWin(smallBoard, winCount);
            if (role != -1 && !smallBoard.checkFull()) {
                return new int[]{index, role};
            }
            index++;
        }
        return null;
    }

    /**
     * check whether each part is full
     * @param board
     * @param superBoard
     * @return index of the full small board
     */
    public int superCheckFull(Board board, Board superBoard) {

        List<Board> smallBoards = getEachSmallBoard(board);
        int index = 0;
        for (Board smallBoard : smallBoards){
            int row = SuperTTTController.getSuperOffsets()[index][0];
            int col = SuperTTTController.getSuperOffsets()[index][1];
            if (smallBoard.checkFull() && !superBoard.checkCellFull(row+1, col+1)){
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * fill the empty cell in the winning part
     * @param board
     * @param index
     * @return updated board
     */
    public Board resetWinPart(Board board,int index){
        // get offset
        int rowOffset = SuperTTTController.getOffsets()[index][0];
        int colOffset = SuperTTTController.getOffsets()[index][1];
        for (int row = 1; row <= 3; row++){
            for (int col = 1; col <= 3; col++){
                if(board.getBoard()[row + rowOffset - 1][col + colOffset - 1].getChessPiece() == null){
                    int inputRow = row + rowOffset;
                    int inputCol = col + colOffset;
                    board.setPieceAt(inputRow, inputCol, new ChessPiece(inputRow, inputCol, -1));
                }
            }
        }
        return board;
    }
}
