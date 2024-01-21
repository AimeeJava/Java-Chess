package Assignment.A4;

public class King extends Piece {
    public King(String color, int row, int col){
        super(color, row, col);
    }
    @Override
    public boolean isValidMove(int toRow, int toCol){
        return Math.abs(toRow - this.row) <= 1 && Math.abs(toCol - this.col) <= 1;
    }
    @Override
    public boolean isCapture(int toRow, int toCol){return this.isValidMove(toRow, toCol);}
    @Override
    public int[][] path(int toRow, int toCol){return new int[0][0];}
}