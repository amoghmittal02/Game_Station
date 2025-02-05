package Entity;

/**
 * chess piece in the cell
 */
public class ChessPiece {
    private int row;
    private int col;
    private int role;
    private char flag;

    public ChessPiece(int row, int col, int role) {
        this.row = row;
        this.col = col;
        this.role = role;
    }
    public ChessPiece(ChessPiece chessPiece) {
        this.row = chessPiece.getRow();
        this.col = chessPiece.getCol();
        this.role = chessPiece.getRole();
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }
}
