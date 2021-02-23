package edu.touro.cs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Consts
{
    public static boolean DEBUG = false;
}

public class EightQueens {
    EightQueens(int boardSize)
    {
        BOARD_SIZE = boardSize;
    }

    private final int BOARD_SIZE;

    public String boardToString(List<Point> pieces)
    {
        int board[][] = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i=0;i<board.length;i++)
            Arrays.fill(board[i], -1);
        int pieceNum = 0;
        for (Point piece : pieces) {
            if (piece == null)
                break;
            board[piece.y][piece.x] = pieceNum++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<BOARD_SIZE;i++)
        {
            for (int j=0;j<BOARD_SIZE;j++) {
                sb.append(
                        String.format(" %2s |",board[j][i] == -1 ? " " : board[j][i] ));
            }
            sb.append("\n");
            for (int j=0;j<BOARD_SIZE;j++) {
                sb.append("____|" );
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Checks in the specified locations are legal with respect to each other
     * @param p1
     * @param p2
     * @return
     */
    private boolean isLegal(Point p1, Point p2)
    {
        return ! (p1.x == p2.x ||
                p1.y == p2.y ||
                Math.abs(p1.x-p2.x) == Math.abs(p1.y-p2.y));
    }

    /**
     * Checks if the queen at newLocation is legal with respect to all queens previous queens
     * @return
     */
    private boolean isLegal(LinkedList<Point> pieces, Point newLocation)
    {
        for (Point p : pieces) {
            if ( ! isLegal(p, newLocation) ){
                return false;
            }
        }
        return true;
    }



    boolean solve(LinkedList<Point> pieces, int pieceNum) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Point candidateLocation = new Point(i, pieceNum);//queen N is placed into that column (qIndex) and we iterate through rows
            if (isLegal(pieces, candidateLocation)) {
                pieces.add(candidateLocation);
                if (Consts.DEBUG)
                {
                    System.out.println( boardToString(pieces) );
                }
                if (pieces.size() == BOARD_SIZE) { // Base Case
                    solutions.add( boardToString(pieces));
                    return true;
                } else // Recursive Case
                    if (! solve(new LinkedList<>(pieces), pieceNum + 1))
                    {
                        if (Consts.DEBUG)
                        {
                            System.out.println("Backtracking");
                        }
                        pieces.removeLast();//backtrack
                        continue;
                    }
            }
        }
        return false;
    }

    private List<String> solutions = new LinkedList<>();

    public List<String> solveAll()
    {
        LinkedList<Point> pieces = new LinkedList<>();
        solve(pieces,0);
        return solutions;
    }

    public static void main(String[] args) {
        EightQueens q = new EightQueens(6);
        List<String> solutions =  q.solveAll();
        System.out.printf("%d Solutions%n", solutions.size());
        for(String s: solutions)
            System.out.println(s);
    }
}
