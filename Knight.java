package Assignment.A4;

public class Knight extends Piece {
    public Knight(String color, int row, int col){
        super(color, row, col);
    }
    @Override
    public boolean isValidMove(int toRow, int toCol){
        if(Math.abs(this.row - toRow) == 2 && Math.abs(this.col - toCol) == 1 ||
                Math.abs(this.col - toCol) == 2 && Math.abs(this.row - toRow) == 1){
            return true;
        }
        return false;
    }
    @Override
    public boolean isCapture(int toRow, int toCol){return this.isValidMove(toRow, toCol);}

    @Override
    public String toToken(){return this.color.equals("white")?"N": "n";}

    @Override
    public int[][] path(int toRow, int toCol){
        return new int[0][0];
    }
}
