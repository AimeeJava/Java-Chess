package Assignment.A4;

public class Pawn extends Piece {
    private boolean firstMove = true;
    public Pawn(String color, int row, int col){
        super(color, row, col);
    }
    public int getMovement(int toRow){
        int direction = this.color.equals("white")? -1: 1;
        return (toRow - this.row) * direction;
    }
    @Override
    public boolean isValidMove(int toRow, int toCol){
        int maxMove = this.firstMove?2 : 1;
        int movement = this.getMovement(toRow);
        boolean moveForward = toCol == this.col && movement <= maxMove && movement >= 0;
        return moveForward;
    }
    @Override
    public boolean isCapture(int toRow, int toCol){
        int movement = this.getMovement(toRow);
        boolean moveDiagonal = Math.abs(toCol - this.col) == 1 && movement == 1;
        return moveDiagonal;
    }

    @Override
    public void move(int toRow, int toCol){
        super.move(toRow, toCol);
        this.firstMove = false;
    }
    @Override
    public int[][] path(int toRow, int toCol){
        return new int[0][0];
    }

}