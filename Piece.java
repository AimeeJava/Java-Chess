package Assignment.A4;

public abstract class Piece {
    protected String color;
    protected int row;
    protected int col;

    public Piece(String color, int row, int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    public String toString(){return this.getClass().getName();}
    public String toToken(){
        String type = this.toString().substring(0, 1);
        return this.color.equals("white")? type.toUpperCase(): type.toLowerCase();
    }
    public void move(int destRow, int destCol){this.setPos(destRow, destCol);}
    public abstract boolean isValidMove(int toRow, int toCol);
    public abstract boolean isCapture(int toRow, int toCol);
    public abstract int[][] path(int toRow, int toCol);

}