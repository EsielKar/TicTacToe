package model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public final class TicTacToeGame {
    private final TicTacToePlayer p1;
    private final TicTacToePlayer p2;


    public TicTacToeGame(TicTacToePlayer p1, TicTacToePlayer p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void play() {
        TicTacToeState game = new TicTacToeState(
            new TicTacToeTile[][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }
        );
        boolean band = true;
        while (!game.isFinished() && TicTacToeGame.findWinner(game) == null) {
            availableMoves(game);
            if (band) {
                game = p1.getMove(game);
                band = false;
            }else {
                game = p2.getMove(game);
                band = true;
            }
            System.out.println(game);
        }

        System.out.println("Winner: " + TicTacToeGame.findWinner(game));

    }

    public static List<Integer> availableMoves(TicTacToeState state) {
        ArrayList<Integer> availableMoves = new ArrayList<>();
        for (int i = 0, k = 1 ; i < state.gameboard.length ; i++) {
            for (int j = 0 ; j < state.gameboard[i].length ; j++) {
                if (state.gameboard[i][j] == null) availableMoves.add(k);
                k++;
            }
        }
        return availableMoves;
        //System.out.println(availableMoves);
    }

    public static LinkedList<TicTacToeState> generateNextStates(TicTacToeTile of, TicTacToeState in) {
        LinkedList<TicTacToeState> states = new LinkedList<TicTacToeState>();
            for (int i = 0 ; i < in.gameboard.length ; i++) {
                for (int j = 0 ; j < in.gameboard[i].length ; j++) {
                    if (in.gameboard[i][j] == null) {
                        TicTacToeTile[][] newGameboard = in.getGameboard();
                        newGameboard[i][j] = of;
                        states.add(new TicTacToeState(newGameboard));
                    }
                }
            }
        return states;
    }

    public static int possibleWins(TicTacToeTile of, TicTacToeState in) {
        TicTacToeTile oponent = (of == TicTacToeTile.X) ? TicTacToeTile.O: TicTacToeTile.X;
        boolean bandRow, bandColumn, bandPDiagonal, bandSDiagonal; 
        int total = 0;

        //Possible wins
        bandPDiagonal = bandSDiagonal = true;
        for (int i = 0 ; i < in.gameboard.length ; i++) {
            bandRow = bandColumn = true;
            for (int j = 0 ; j < in.gameboard.length && (bandRow || bandColumn) ; j++) {
                if (in.gameboard[i][j] == oponent) bandRow = false;
                if (in.gameboard[j][i] == oponent) bandColumn = false;
            }
            if (bandRow) total++;
            if (bandColumn) total++;

            if (bandPDiagonal && in.gameboard[i][i] == oponent) bandPDiagonal = false;
            if (bandSDiagonal && in.gameboard[i][in.gameboard.length - 1 - i] == oponent) bandSDiagonal = false;  
        }

        if (bandPDiagonal) total++;
        if (bandSDiagonal) total++;

        return total;
    }

    public static TicTacToeTile findWinner(TicTacToeState in) {
        //Cheking for rows
        for (int row = 0 ; row < in.gameboard.length ; row++)
            if (in.gameboard[row][0] != null && in.gameboard[row][0] == in.gameboard[row][1] && 
                in.gameboard[row][1] == in.gameboard[row][2]) {
                    return (in.gameboard[row][0] == TicTacToeTile.X) ? TicTacToeTile.X : TicTacToeTile.O;
                }
                    

        //Cheking for columns
        for (int j = 0 ; j < in.gameboard.length ; j++) {
            if (in.gameboard[0][j] != null && in.gameboard[0][j] == in.gameboard[1][j] && in.gameboard[1][j] == in.gameboard[2][j])
                return (in.gameboard[0][j] == TicTacToeTile.X) ? TicTacToeTile.X : TicTacToeTile.O;
        }

        //Cheking for diagonals
        if (in.gameboard[1][1] != null && (in.gameboard[0][0] == in.gameboard[1][1] && in.gameboard[1][1] == in.gameboard[2][2]
            || in.gameboard[0][2] == in.gameboard[1][1] && in.gameboard[1][1] == in.gameboard[2][0]))
                return (in.gameboard[1][1] == TicTacToeTile.X) ? TicTacToeTile.X : TicTacToeTile.O;
        return null;
        
    }
}
