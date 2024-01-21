package Assignment.A4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessMover {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        int numPiece = in.nextInt();
        for (int i = 0; i < numPiece; i++) {
            String color = in.next();
            String type = in.next();
            String file = in.next();
            int col = board.fileToY(file);
            int row = board.rowToX(in.nextInt());
            board.addPiece(type, color, row, col);
        }
        List<String> moves = new ArrayList<>();
        int moveNum = in.nextInt();
        in.nextLine();
        for (int i = 0; i < moveNum; i++) {
            String move = in.nextLine();
            moves.add(move);
        }

        boolean allMoved = true;
        for(String move: moves){
            String[] moveArr = move.split(" ");
            if(!board.move(moveArr[0], moveArr[1], moveArr[2], moveArr[3])){
                allMoved = false;
                break;
            }
            if(board.checkmate()){
                System.out.printf("Checkmate for %s\n", board.getTurnColor());
                break;
            }
            if(allMoved){
                board.display();
            }
        }
    }
}
