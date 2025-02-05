package Entity;

import static Entity.Color.RED;
import static Entity.Color.RESET;

/**
 * game board
 */
public class Board {
    private Cell[][] board;

    private Interval[][] interval;
    private int row;
    private int col;
    private int type;
    private int winCount;



    public Board(int row, int col){
        this.board = new Cell[row][col];
        this.row = row;
        this.col = col;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                board[i][j] = new Cell(i+1, j+1);
            }
        }
    }
    public Board(int row, int col, int type, int winCount) {
        this.board = new Cell[row][col];
        this.row = row;
        this.col = col;
        this.type = type;
        this.winCount = winCount;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                board[i][j] = new Cell(i+1, j+1);
            }
        }
    }

    public Board(int row, int col, int type, Boolean needInterval) {
        this.board = new Cell[row][col];
        this.row = row;
        this.col = col;
        this.type = type;
        this.interval = new Interval[row + 1][col + 1];

        for (int i = 0; i < row; i++){

            for (int j = 0; j < col; j++){
                board[i][j] = new Cell(i+1, j+1);

            }
        }
        for (int i = 0; i <= row; i++){
            for (int j = 0; j <= col; j++){
                interval[i][j] = new Interval(i+1, j+1);
            }
        }
    }

    /**
     *  print the board
     * @param num
     */
    public void printNormalBoard(int num) {
        // we don't split the board in this case
        if (num == -1) {
            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board[i].length; j++) {
                    System.out.print("+---");
                }
                System.out.print("+\n");

                for (int j = 0; j < this.board[i].length; j++) {
                    if (this.board[i][j].getChessPiece() == null) {
                        System.out.print("|   ");
                    } else {
                        if (this.board[i][j].getChessPiece().getRole() == 1) {
                            System.out.print("| x ");
                        } else if (this.board[i][j].getChessPiece().getRole() == 2) {
                            System.out.print("| o ");
                        } else if (this.board[i][j].getChessPiece().getRole() == 3 || this.board[i][j].getChessPiece().getRole() == -1) {
                            System.out.print("| - ");
                        } else {
                            System.out.print("|   ");
                        }
                    }
                }
                System.out.print("|\n");
            }
            for (int j = 0; j < this.board[row - 1].length; j++) {
                System.out.print("+---");
            }
            System.out.print("+\n");
        }
        // we split board
        else if (num > 0) {
            int subBoardSize = this.board.length / num;

            for (int blockRow = 0; blockRow < num; blockRow++) {
                for (int row = 0; row < subBoardSize; row++) {

                    // 打印水平分割线
                    for (int blockCol = 0; blockCol < num; blockCol++) {
                        for (int col = 0; col < subBoardSize; col++) {
                            System.out.print("+---");
                        }
                        System.out.print("+ ");
                    }
                    System.out.print("\n");

                    for (int blockCol = 0; blockCol < num; blockCol++) {
                        for (int col = 0; col < subBoardSize; col++) {
                            int boardRow = blockRow * subBoardSize + row;
                            int boardCol = blockCol * subBoardSize + col;

                            if (this.board[boardRow][boardCol].getChessPiece() == null) {
                                System.out.print("|   ");
                            } else {
                                if (this.board[boardRow][boardCol].getChessPiece().getRole() == 1) {
                                    System.out.print("| x ");
                                } else if (this.board[boardRow][boardCol].getChessPiece().getRole() == 2) {
                                    System.out.print("| o ");
                                } else if (this.board[boardRow][boardCol].getChessPiece().getRole() == 3 ||
                                        this.board[boardRow][boardCol].getChessPiece().getRole() == -1) {
                                    System.out.print("| - ");
                                } else {
                                    System.out.print("|   ");
                                }
                            }
                        }
                        System.out.print("| ");
                    }
                    System.out.print("\n");
                }

                if (blockRow < num - 1) {
                    for (int blockCol = 0; blockCol < num; blockCol++) {
                        for (int col = 0; col < subBoardSize; col++) {
                            System.out.print("+---");
                        }
                        System.out.print("+ ");
                    }
                    System.out.print("\n");
                }
            }

            for (int blockCol = 0; blockCol < num; blockCol++) {
                for (int col = 0; col < subBoardSize; col++) {
                    System.out.print("+---");
                }
                System.out.print("+ ");
            }
            System.out.print("\n");
        }
    }

    /**
     * print the board with intervals
     */
    public void printIntervalBoard(){
        // 遍历棋盘的每一行
        boolean colnumber = false;
        for (int row = 0; row < this.row; row++) {
            if (!colnumber){
                for (int col = 0; col < this.col; col++) {
                    System.out.print("    "+(col+1)+"   ");
                }
                colnumber = true;
                System.out.print("\n");
            }
            // 打印当前行每个cell的上fence
            for (int col = 0; col < this.col; col++) {
                // 打印左上角 '+'
//                System.out.print("+");
                if (col == 0){
                    if (this.interval[row][col].getRightFence() != null) {
                        System.out.format(RED + "  ----- " + RESET);  // 如果有围墙，打印 红色"---"
                    } else {
                        System.out.print("  ~   ~ ");  // 否则打印空格
                    }
                }else {
                    // 判断是否有水平围墙 (isHorizontal)
                    if (this.interval[row][col].getRightFence() != null || this.interval[row][col-1].getRightFence() != null) {
                        System.out.print(RED + "  ----- " + RESET);  // 如果有围墙，打印 红色"---"
                    } else {
                        System.out.print("  ~   ~ ");  // 否则打印空格
                    }
                }

            }
            // 打印最后一个cell的右上角 '+'
//            System.out.print("+");
            System.out.print("\n");
            // 打印上边框
            for (int col = 0; col < this.col; col++) {
                System.out.print("  +---+ ");
            }
            System.out.print("\n");
            // 打印当前行的每个cell内容及其左右边框
            int flag = 1;
            System.out.print(row+1);
            for (int col = 0; col < this.col; col++) {
                // 判断左侧是否有竖直围墙 (isVertical)
                if (row == 0){
                    if (this.interval[row][col].getDownFence() != null) {
                        if (flag==1){
                            System.out.print(RED + "| " + RESET);  // 如果有围墙，打印 "|"
                            flag = -1;
                        }else {
                            System.out.print(RED + "| " + RESET);  // 如果有围墙，打印 "|"
                        }

                    } else {
                        if (flag==1){
                            System.out.print(" ");
                            flag = -1;
                        }else {
                            System.out.print("  ");
                        }
                    }
                }else{
                    if (this.interval[row][col].getDownFence() != null || this.interval[row - 1][col].getDownFence() != null) {
                        if (flag==1){
                            System.out.print(RED + "| " + RESET);  // 如果有围墙，打印 红色"|"
                            flag = -1;
                        }else {
                            System.out.print(RED + "| " + RESET);  // 如果有围墙，打印 红色"|"
                        }

                    } else {
                        if (flag==1){
                            System.out.print(" ");
                            flag = -1;
                        }else {
                            System.out.print("  ");
                        }
                    }
                }



                // 打印cell中的棋子或空格
                if (this.board[row][col].getChessPiece() == null) {
                    System.out.print("|   | ");  // 如果没有棋子，打印空格
                } else {
                    // 根据棋子角色打印 'x', 'o', 等
                    int role = this.board[row][col].getChessPiece().getRole();
                    System.out.print( "| "  + (char)('A' + role - 1) + " | ");
                }
            }
            System.out.print(" ");
            System.out.print("\n");
            // 打印下边框
            for (int col = 0; col < this.col; col++) {
                System.out.print("  +---+ ");
            }
            System.out.print("\n");

        }
    }

    /**
     * change the array by users' input
     *
     * @param row input row
     * @param col input col
     * @param role user's role
     * @return whether input successfully or not
     */
    public Boolean changeOnePiece(int row, int col, int role){
        // use try catch to examine exception
        if (!checkValid(row, col)){
            System.out.println("invalid input");
            return false;
        }
        try{
            this.board[row - 1][col - 1].setChessPiece(new ChessPiece(row, col, role));
        }
        catch (Exception e){
            System.out.println("invalid input");
            return false;
        }
        return true;
    }


    /**
     * check whether the input is valid or not
     * @param row
     * @param col
     * @return
     */
    public Boolean checkValid(int row, int col) {
        try{
            if (this.board[row - 1][col - 1].getChessPiece() != null) {
                return false;
            }
            else {
                return true;
            }
        } catch (Exception e){
            return false;
        }

    }

    /**
     * check whether the board is full or not
     * @return
     */
    public Boolean checkFull(){
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++){
                if (this.board[i][j].getChessPiece() == null){
                    return false;
                }
            }
        }
        return true;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public Interval[][] getInterval() {
        return interval;
    }

    public void setInterval(Interval[][] interval) {
        this.interval = interval;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public boolean checkCellFull(int row, int col) {
        if(this.board[row-1][col-1].getChessPiece() == null){
            return false;
        }else {
            return true;
        }
    }

    /**
     * set piece at specific position
     * @param row
     * @param col
     * @param piece
     */
    public void setPieceAt(int row, int col, ChessPiece piece) {
        if (row > 0 && row <= this.row && col > 0 && col <= this.col) {
            board[row-1][col-1].setChessPiece(piece); // 设置棋子
        } else {
            System.out.println("invalid input area");
        }
    }

    /**
     * check fence can be set or not in specific direction
     * @param row
     * @param col
     * @param direction
     * @return
     */
    public boolean checkDirectionValid(int row, int col, int direction) {

        boolean ifValid = true;
        int indexRow = row - 1;
        int indexCol = col - 1;
        switch (direction) {
            case 1: // up
                if (indexRow - 2 < 0) {
                    System.out.println("Placing up exceeds board limits.");
                    ifValid = false; // out of range
                    break;
                }
                if ((checkIntervalIndexValid(indexRow - 1, indexCol - 1) && interval[indexRow - 1][indexCol- 1].getRightFence() != null) || (checkIntervalIndexValid(indexRow - 1, indexCol + 1) && interval[indexRow - 1][indexCol + 1].getLeftFence() != null)) {
                    System.out.println("Fence blocks the upward set.");
                    ifValid = false; // have fence
                }
                break;


            case 2: // down
                if (indexRow + 2 > this.row - 1) {
                    System.out.println("Placing down exceeds board limits.");
                    ifValid = false;
                    break;
                }
                if ((checkIntervalIndexValid(indexRow + 1, indexCol - 1) && interval[indexRow + 1][indexCol - 1].getRightFence() != null) || (checkIntervalIndexValid(indexRow + 1, indexCol + 1) && interval[indexRow + 1][indexCol + 1].getLeftFence() != null)) {
                    System.out.println("Fence blocks the downward set.");
                    ifValid = false;
                }
                break;


            case 3: // left
                if (indexCol - 2 < 0) {
                    System.out.println("Placing left exceeds board limits.");
                    ifValid = false;
                    break;
                }
                if ((checkIntervalIndexValid(indexRow - 1, indexCol - 1) && interval[indexRow - 1][indexCol - 1].getDownFence() != null) || (checkIntervalIndexValid(indexRow + 1, indexCol - 1) && interval[indexRow + 1][indexCol - 1].getUpFence() != null)) {
                    System.out.println("Fence blocks the leftward set.");
                    ifValid = false;
                }
                break;


            case 4: // right
                if (indexCol + 2 > this.col - 1) {
                    System.out.println("Placing right exceeds board limits.");
                    ifValid = false;
                    break;
                }
                if ((checkIntervalIndexValid(indexRow - 1, indexCol + 1) && interval[indexRow - 1][indexCol + 1].getDownFence() != null) || (checkIntervalIndexValid(indexRow + 1, indexCol + 1) && interval[indexRow + 1][indexCol + 1].getUpFence() != null)) {
                    System.out.println("Fence blocks the rightward set.");
                    ifValid = false;
                }
                break;

            default:
                System.out.println("Error: Invalid direction input.");

        }
        return ifValid;
    }

    /**
     * check whether the fence is valid or not
     * @param row
     * @param col
     * @param direction
     * @return
     */
    public boolean checkFenceExist(int row, int col, int direction) {
        boolean ifValid = true;
        int indexRow = row - 1;
        int indexCol = col - 1;
        switch (direction) {
            case 1: // up
//                System.out.println(interval[indexRow][indexCol].getRightFence() != null);
                if(checkIntervalIndexValid(indexRow, indexCol)){
                    ifValid = (interval[indexRow][indexCol].getRightFence() != null);
                }

                if (!ifValid && checkIntervalIndexValid(indexRow, indexCol - 1)) {
                    ifValid = (interval[indexRow][indexCol - 1].getRightFence() != null);
                }
                if (checkIntervalIndexValid(indexRow, indexCol + 1) && !ifValid) {
                    ifValid = (interval[indexRow][indexCol+1].getLeftFence() != null);
                }
                if (checkIntervalIndexValid(indexRow, indexCol+2) && !ifValid) {
                    ifValid = (interval[indexRow][indexCol + 2].getLeftFence() != null);
                }
                break;

            case 2: // down
                if(checkIntervalIndexValid(indexRow + 1, indexCol)){
                    ifValid = (interval[indexRow + 1][indexCol].getRightFence() != null);
                }

                if (checkIntervalIndexValid(indexRow+1, indexCol-1)&& !ifValid) {
                    ifValid = (interval[indexRow + 1][indexCol - 1].getRightFence() != null);
                }
                if (checkIntervalIndexValid(indexRow+1, indexCol+1) && !ifValid) {
                    ifValid = (interval[indexRow + 1][indexCol+1].getLeftFence() != null);
                }
                if (checkIntervalIndexValid(indexRow+1, indexCol+2) && !ifValid) {
                    ifValid = (interval[indexRow + 1][indexCol + 2].getLeftFence() != null);
                }
                break;


            case 3: // left
                if(checkIntervalIndexValid(indexRow, indexCol)){
                    ifValid = (interval[indexRow][indexCol].getDownFence() != null);
                }
                if (checkIntervalIndexValid(indexRow-1, indexCol) && !ifValid) {
                    ifValid = (interval[indexRow - 1][indexCol].getDownFence() != null);
                }
                if (checkIntervalIndexValid(indexRow+1, indexCol) && !ifValid) {
                    ifValid = (interval[indexRow + 1][indexCol].getUpFence() != null);
                }
                if (checkIntervalIndexValid(indexRow+2, indexCol) && !ifValid) {
                    ifValid = (interval[indexRow + 2][indexCol].getUpFence() != null);
                }
                break;


            case 4: // right
                if(checkIntervalIndexValid(indexRow, indexCol+1)){
                    ifValid = (interval[indexRow][indexCol + 1].getDownFence() != null);

                }
                if(checkIntervalIndexValid(indexRow-1, indexCol+1) && !ifValid) {
                    ifValid = (interval[indexRow - 1][indexCol + 1].getDownFence() != null);
                }
                if (checkIntervalIndexValid(indexRow+1, indexCol+1) && !ifValid) {
                    ifValid = (interval[indexRow + 1][indexCol + 1].getUpFence() != null);
                }
                if (checkIntervalIndexValid(indexRow+2, indexCol+1) && !ifValid) {
                    ifValid = (interval[indexRow + 2][indexCol + 1].getUpFence() != null);
                }
                break;

            default:
                System.out.println("Error: Invalid direction input.");

        }
        return ifValid;
    }

    public ChessPiece changeOnePiecePosition(int newRow, int newCol, ChessPiece currentPiece){
        return new ChessPiece(newRow, newCol, currentPiece.getRole());
    }

    public ChessPiece moveOnePiece(Integer newRow, Integer newCol, ChessPiece currentPiece) {
        resetPieceToNull(currentPiece.getRow(), currentPiece.getCol());
        currentPiece = changeOnePiecePosition(newRow, newCol, currentPiece);
        setPieceAt(newRow, newCol, currentPiece);
        return currentPiece;
    }

    private void resetPieceToNull(int row, int col) {
        board[row - 1][col - 1].setChessPiece(null);
    }

    public boolean checkIntervalFenceFull(int row, int col) {
        return interval[row - 1][col - 1].checkFenceFull();
    }

    /**
     * set two fences together
     * @param row
     * @param col
     * @param direction
     * @param role
     */
    public void setNewFence(Integer row, Integer col, int direction, int role) {
        int indexRow = row  -1;
        int indexCol = col - 1;
        switch (direction){
            case 1:// up
                interval[indexRow][indexCol].setUpFence(new Fence(row, col));
                if (checkIntervalIndexValid(indexRow-2, indexCol)){
                    interval[indexRow - 2][indexCol].setDownFence(new Fence(row - 2, col));
                }
                break;
            case 2://down
                interval[indexRow][indexCol].setDownFence(new Fence(row, col));
                if (checkIntervalIndexValid(indexRow+2, indexCol)){
                    interval[indexRow + 2][indexCol].setUpFence(new Fence(row + 2, col));
                }
                break;
            case 3://left
                interval[indexRow][indexCol].setLeftFence(new Fence(row, col));
                if (checkIntervalIndexValid(indexRow, indexCol-2)){
                    interval[indexRow][indexCol - 2].setRightFence(new Fence(row, col - 2));
                }
                break;
            case 4://right
                interval[indexRow][indexCol].setRightFence(new Fence(row, col));
                if (checkIntervalIndexValid(indexRow, indexCol+2)){
                    interval[indexRow][indexCol + 2].setLeftFence(new Fence(row, col + 2));
                }
                break;
        }
    }

    /**
     * remove two fences together
     * @param row
     * @param col
     * @param direction
     * @param role
     */
    public void removeFence(Integer row, Integer col, int direction, int role) {
        int indexRow = row  -1;
        int indexCol = col - 1;
        switch (direction){
            case 1:// up
                interval[indexRow][indexCol].setUpFence(null);
                if (checkIntervalIndexValid(indexRow-2, indexCol)){
                    interval[indexRow - 2][indexCol].setDownFence(null);
                }
                break;
            case 2://down
                interval[indexRow][indexCol].setDownFence(null);
                if (checkIntervalIndexValid(indexRow+2, indexCol)){
                    interval[indexRow + 2][indexCol].setUpFence(null);
                }
                break;
            case 3://left
                interval[indexRow][indexCol].setLeftFence(null);
                if (checkIntervalIndexValid(indexRow, indexCol-2)){
                    interval[indexRow][indexCol - 2].setRightFence(null);
                }
                break;
            case 4://right
                interval[indexRow][indexCol].setRightFence(null);
                if (checkIntervalIndexValid(indexRow, indexCol+2)){
                    interval[indexRow][indexCol + 2].setLeftFence(null);
                }
                break;
        }
    }

    public void updatePieces(ChessPiece[] playerChessPieces) {
        for (int i = 0; i < playerChessPieces.length; i++) {
            setPieceAt(playerChessPieces[i].getRow(), playerChessPieces[i].getCol(), playerChessPieces[i]);
        }
    }

    /**
     * interval's index range is [0, row][0, col]
     * @param row
     * @param col
     * @return
     */
    public boolean checkIntervalIndexValid(int row, int col){
        return row >= 0 && row <= this.row && col >= 0 && col <= this.col;
    }


}
