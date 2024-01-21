package Assignment.A4;

public class Rook extends Piece {
    public Rook(String color, int row, int col){
        super(color, row, col);
    }
    @Override
    public boolean isValidMove(int toRow, int toCol){
        return this.row == toRow || this.col == toCol;
    }
    @Override
    public boolean isCapture(int toRow, int toCol){return this.isValidMove(toRow,toCol);}

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
