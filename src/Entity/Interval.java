package Entity;

public class Interval {
    private int row;

    private int col;

    private Fence upFence;

    private Fence downFence;

    private Fence leftFence;

    private Fence rightFence;


    public Fence getUpFence() {
        return upFence;
    }

    public void setUpFence(Fence upFence) {
        this.upFence = upFence;
    }

    public Fence getDownFence() {
        return downFence;
    }

    public void setDownFence(Fence downFence) {
        this.downFence = downFence;
    }

    public Fence getLeftFence() {
        return leftFence;
    }

    public void setLeftFence(Fence leftFence) {
        this.leftFence = leftFence;
    }

    public Fence getRightFence() {
        return rightFence;
    }

    public void setRightFence(Fence rightFence) {
        this.rightFence = rightFence;
    }

    public Interval(int row, int col){
        this.row = row;
        this.col = col;
        this.upFence = this.downFence = this.leftFence = this.rightFence = null;
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

    public boolean checkFenceFull() {
        return upFence != null && downFence != null && leftFence != null && rightFence != null;
    }
}
