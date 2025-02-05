package Entity;

import Entity.MonstersAndHeroes.Space;

/**
 * cells in the game board
 */
public class Cell {
    private int row;

    private int col;

    private ChessPiece chessPiece;

    private Space space;

    public Cell(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public Cell(Space space){
        this.space = space;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
