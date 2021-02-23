package edu.touro.cs;

import java.awt.Point;
import java.util.Arrays;

public class EightQueens {
    private final int BOARD_SIZE = 4;
    private final int QUEENS_MAX = BOARD_SIZE;
    private Point[] pieces = new Point[QUEENS_MAX];

    private final int NOT_LEGAL_MOVE = -1;


    @Override
    public String toString()
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
     * Checks if the queen at the specified index is legal with respect to all queens at earlier indices
     * @param qIndex
     * @return
     */
    private boolean isLegal(int qIndex)
    {
        for (int i = qIndex - 1; i >= 0 ;i--) {
            if ( ! isLegal(pieces[qIndex], pieces[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * returns the next legal location (row) for a Queen at index qIndex
     * @param qIndex (0-7)
     * @return the row that the specified queen may be placed (the column is determined by qIndex,
     * such that queen 0 is placed in column 0, same for queen 1..7
     */
    private int getNextLegalLocation(int qIndex, int next )
    {
        for ( int i=next; i< BOARD_SIZE; i++)
        {
            pieces[qIndex] = new Point(i, qIndex);//queen N is placed into that column (qIndex) and we iterate through rows
            if ( isLegal( qIndex )) {
                return i + 1;
            }
        }
        return NOT_LEGAL_MOVE;
    }

    boolean solve(int pieceNum)
    {
        int nextRowToCheck = 0;
        do {
            nextRowToCheck = getNextLegalLocation(pieceNum, nextRowToCheck);
            if ( nextRowToCheck == NOT_LEGAL_MOVE )
            {   // backtrack
                pieces[pieceNum] = null;
                return false;
            }
            if (pieceNum == QUEENS_MAX - 1) { // solved all queens
                System.out.println(this.toString());
                pieces[pieceNum] = null;
                return true;
            }
            solve(pieceNum + 1); // recurse
        } while (nextRowToCheck < BOARD_SIZE);
        return false;
    }



    public static void main(String[] args) {
        EightQueens q = new EightQueens();
        q.solve(0);
    }
}
