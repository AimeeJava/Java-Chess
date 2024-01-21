package Assignment.A4;

public class Queen extends Piece {
    public Queen(String color, int row, int col){
        super(color, row, col);
    }
    @Override
    public boolean isValidMove(int toRow, int toCol){
        //true if moves horizontal, vertical or diagonal
        return this.row == toRow || this.col == toCol || Math.abs(this.col - toCol) == Math.abs(this.row - toRow);
    }
    @Override
    public boolean isCapture(int toRow, int toCol){return isValidMove(toRow, toCol);}
    @Override
    public int[][] path(int toRow, int toCol){
        int[][] path = new int[Math.max(Math.abs(toRow - this.row) - 1, 0)][2];
        for (int i = 0; i < path.length; i++) {
            path[i][0] = this.row + (int)Math.signum(toRow - this.row) * (i+1);
            path[i][1] = this.col + (int)Math.signum(toCol - this.col) * (i+1);
        }
        return path;
    }
}