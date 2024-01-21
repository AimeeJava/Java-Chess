package Assignment.A4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Piece[][] board;
    private String file = "abcdefgh";
    private int turn = 0;
    private Map<String, String> pieceMap = new HashMap<>(){{
        put("K", "King");
        put("Q", "Queen");
        put("R", "Rook");
        put("B", "Bishop");
        put("N", "Knight");
        put("P", "Pawn");
    }};


    private int[][] kingPos = new int[2][2];

    public Board(){
        this.board = new Piece[8][8];
        for(Piece[] row: this.board){
            Arrays.fill(row, null);
        }
    }
    public String getTurnColor(){return this.turn % 2 == 0? "white": "black";}

    public int fileToY(String file){return this.file.indexOf(file);}
    public int rowToX(int row){return this.board.length - row;}

    public String yToFile(int y){return this.file.substring(y, y+1);}

    public int xToRow(int x){return this.board.length - x + 1;}

    public void recordKingPos(String color, int row, int col){
        int kidx = color.equals("white")?0:1;
        this.kingPos[kidx][0] = row;
        this.kingPos[kidx][1] = col;
    }

    public void addPiece(String type, String color, int row, int col) {
        try {
            Class<?> cls = Class.forName(this.pieceMap.get(type));
            Constructor<?> constructor = cls.getConstructor(String.class, int.class, int.class);
            Piece piece = (Piece) constructor.newInstance(color, row, col);
            this.board[row][col] = piece;
            piece.setPos(row, col);
            if (type.equals("K")) {
                recordKingPos(color, row, col);
            }
        } catch (ClassNotFoundException e){
            System.out.println("Invalid Chess Piece " + type);
        } catch (NoSuchMethodException e) {
            System.out.println("No required Constructor for " + type);;
        } catch (InstantiationException e) {
            System.out.println("Cannot Instantiate " + type);
        } catch (IllegalAccessException e) {
            System.out.println("Cannot Access Constructor of " + type);
        } catch(InvocationTargetException e){
            System.out.println("Cannot Invoke Constructor of " + type);
        }
    }

    public boolean isBlocked(int[][] path){
        for(int[] pos: path){
            if(this.board[pos[0]][pos[1]] != null){
                return true;
            }
        }
        return false;
    }

    public boolean check(King k, int toRow, int toCol){
        for(Piece[] row: this.board){
            for(Piece piece: row){
                if(piece != null && !piece.color.equals(k.color)){
                    if(piece.isCapture(toRow, toCol)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isInBoard(int row, int col){
        return row >= 0 && row < this.board.length && col >= 0 && col < this.board[0].length;
    }

    public int validMovement(Piece piece, int toRow, int toCol){
        if(piece == null){
            return 1;
        }else if(!isInBoard(toRow, toCol)){
            return 2;
        }else if(piece.color.equals("white") != (this.turn % 2 == 0)){
            return 3;
        }else if(!piece.isValidMove(toRow, toCol) && !piece.isCapture(toRow, toCol)){
            return 4;
        }else if(this.board[toRow][toCol] != null && this.board[toRow][toCol].color.equals(piece.color)){
            return 5;
        }else if(!piece.isCapture(toRow, toCol) && this.board[toRow][toCol] != null){
            return 6;
        }else if(isBlocked(piece.path(toRow, toCol))){
            return 7;
        }else if(piece instanceof King && check((King)piece, toRow, toCol)){
            return 8;
        }
        return 0;
    }
    public boolean checkmate(){
        int kidx = this.turn % 2;
        int krow = this.kingPos[kidx][0];
        int kcol = this.kingPos[kidx][1];
        King k = (King)this.board[krow][kcol];
        if(k == null){
            return false;
        }
        //check if any possible move of king can escape check
        for (int i = -1; i <= 1; i++) {
            int toRow = krow + i;
            for (int j = -1; j <=1; j++) {
                int toCol = kcol + j;
                if(isInBoard(toRow, toCol)){
                    if(!check(k, toRow, toCol)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean move(String fromFile, String fromRow, String toFile, String row){
        int srtCol = fileToY(fromFile);
        int srtRow = rowToX(Integer.parseInt(fromRow));
        int toCol = fileToY(toFile);
        int toRow = rowToX(Integer.parseInt(row));
        Piece piece = this.board[srtRow][srtCol];
        int validCode = validMovement(piece, toRow, toCol);
        if(validCode != 0){
            System.out.printf("Invalid Move: %s from %s%s to %s%s\n", piece.toString(), fromFile, fromRow, toFile, row);
            return false;
        }
        piece.move(toRow, toCol);
        if(piece instanceof King){
            recordKingPos(getTurnColor(), toRow, toCol);
        }
        this.board[toRow][toCol] = this.board[srtRow][srtCol];
        this.board[srtRow][srtCol] = null;
        this.turn += 1;
        return true;
    }

    public void display(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == null){
                    System.out.print(".");
                }else{
                    System.out.println(this.board[i][j].toToken());
                }
            }
            System.out.println();
        }
    }

}
