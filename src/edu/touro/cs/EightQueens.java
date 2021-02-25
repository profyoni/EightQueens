package edu.touro.cs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Consts
{
    public static final boolean DEBUG = true;
}

public class EightQueens {
    EightQueens(int boardSize)
    {
       BOARD_SIZE = boardSize;
    }

    private final int BOARD_SIZE;

    public String boardToString(List<Point> pieces)
    {
        Integer board[][] = new Integer[BOARD_SIZE][BOARD_SIZE];
        int pieceNum = 0;
        for (Point piece : pieces) {
            board[piece.y][piece.x] = pieceNum++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<BOARD_SIZE;i++)
        {
            for (int j=0;j<BOARD_SIZE;j++) {
                sb.append(
                        String.format(" %2s |",board[j][i] == null ? " " : board[j][i] ));
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
    private boolean isLegal(List<Point> pieces, Point newLocation)
    {
        for (Point p : pieces) {
            if ( ! isLegal(p, newLocation) ){
                return false;
            }
        }
        return true;
    }

    void solve(LinkedList<Point> pieces, int pieceNum) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            Point candidateLocation = new Point(row, pieceNum);//queen N is placed into that column (qIndex) and we iterate through rows
            if (isLegal(pieces, candidateLocation)) {
                pieces.add(candidateLocation);
                if (Consts.DEBUG)
                {
                    System.out.println( boardToString(pieces) ); // log/ tracing
                }
                if (pieces.size() == BOARD_SIZE) { // Base Case - solution
                    solutions.add( boardToString(pieces));
                } else // Recursive Case
                {
                    solve(new LinkedList<>(pieces), pieceNum + 1);
                    if (Consts.DEBUG) {
                        System.out.println("Backtracking");
                    }
                }
                pieces.removeLast(); // backtrack
            }
        }
    }

    private List<String> solutions = new LinkedList<>();

    public List<String> solveAll()
    {
        solve(new LinkedList<Point>(),0);
        return solutions;
    }

    public static void main(String[] args) {
        EightQueens q = new EightQueens(4);
        List<String> solutions =  q.solveAll();
        System.out.printf("%d Solutions%n", solutions.size());
        for(String s: solutions)
            System.out.println(s);
    }
}
