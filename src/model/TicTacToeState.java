package model;

import java.util.LinkedList;

public final class TicTacToeState{
    private final TicTacToePlayer[][] gameboard;
    
    public TicTacToeState(TicTacToePlayer[][] gameboard) {
        this.gameboard = gameboard;
    }

    public TicTacToePlayer[][] getGameboard() {
        TicTacToePlayer[][] copy = new TicTacToePlayer[gameboard.length][];
        for (int i = 0 ; i < gameboard.length ; i++)
            copy[i] = gameboard[i].clone();
        return copy;
    }

    public int leftMoves() {
        int count = 0;
        for (int i = 0 ; i < gameboard.length ; i++)
            for (int j = 0 ; j < gameboard[i].length ; j++)
                if (gameboard[i][j] == null) count++;
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < gameboard.length ; i++) {
            for (int j = 0 ; j < gameboard[i].length ; j++) {
                sb.append((gameboard[i][j] == null ? ' ' : gameboard[i][j].name()));
                if (j < gameboard[i].length - 1) {
                    sb.append('|');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TicTacToeState)) {
            return false;
        }
        TicTacToeState ticTacToeState = (TicTacToeState) o;

        return equallity(ticTacToeState.gameboard);

    }

    private boolean equallity(TicTacToePlayer[][] gameboard) {
        boolean left = true, right = true, inversion = true, transpose = true, 
                secondary = true, identity = true, vertical = true, horizontal = true;
        for (int i = 0 ; i < gameboard.length ; i++)
            for (int j = 0 ; j < gameboard[i].length ; j++) {
                if (left)
                    left = this.gameboard[i][j] == gameboard[gameboard[i].length - 1 - j][i];
                    
                if (right)
                    right = this.gameboard[i][j] == gameboard[j][gameboard[i].length - 1 - i];
                    
                if (inversion)
                    inversion = this.gameboard[i][j] == gameboard[gameboard[i].length - 1 - i][gameboard[i].length - 1 - j];
                    
                if (transpose)
                    transpose = this.gameboard[i][j] == gameboard[j][i];

                if (secondary)
                    secondary = this.gameboard[i][j] == gameboard[gameboard[i].length - 1 - j][gameboard[i].length - 1 - i];
                                    
                if (identity)
                    identity = this.gameboard[i][j] == gameboard[i][j];
                
                if (vertical) 
                    vertical = this.gameboard[i][j] == gameboard[i][gameboard[i].length - 1 - j];
                
                if (horizontal)
                    horizontal = this.gameboard[i][j] == gameboard[gameboard.length - 1 - i][j];

                if (!(left || right || inversion || transpose || secondary || identity || vertical || horizontal)) 
                    return false;
            }                    
        return true;
    }

    public LinkedList<TicTacToeState> generateNextStates(TicTacToePlayer player) {
        LinkedList<TicTacToeState> states = new LinkedList<TicTacToeState>();
            for (int i = 0 ; i < gameboard.length ; i++) {
                for (int j = 0 ; j < gameboard[i].length ; j++) {
                    if (gameboard[i][j] == null) {
                        TicTacToePlayer[][] newGameboard = getGameboard();
                        newGameboard[i][j] = player;
                        boolean band = true;

                        for (int k = 0 ; k < states.size() && band ; k++)
                            if (states.get(k).equallity(newGameboard))
                                band = false;

                        if(band) states.add(new TicTacToeState(newGameboard));
                    }
                }
            }
        return states;
    }

    public int possibleWinsOf(TicTacToePlayer player) {
        TicTacToePlayer oponent = (player == TicTacToePlayer.MAX) ? TicTacToePlayer.MIN: TicTacToePlayer.MAX;
        boolean bandRow, bandColumn, bandPDiagonal, bandSDiagonal; 
        int total = 0;

        //Possible wins
        bandPDiagonal = bandSDiagonal = true;
        for (int i = 0 ; i < gameboard.length ; i++) {
            bandRow = bandColumn = true;
            for (int j = 0 ; j < gameboard.length && (bandRow || bandColumn) ; j++) {
                if (gameboard[i][j] == oponent) bandRow = false;
                if (gameboard[j][i] == oponent) bandColumn = false;
            }
            if (bandRow) total++;
            if (bandColumn) total++;

            if (bandPDiagonal && gameboard[i][i] == oponent) bandPDiagonal = false;
            if (bandSDiagonal && gameboard[i][gameboard.length - 1 - i] == oponent) bandSDiagonal = false;  
        }

        if (bandPDiagonal) total++;
        if (bandSDiagonal) total++;

        return total;
    }


    public TicTacToePlayer findWinner() {
        //Cheking for rows
        for (int row = 0 ; row < gameboard.length ; row++)
            if (gameboard[row][0] != null && gameboard[row][0] == gameboard[row][1] && 
                gameboard[row][1] == gameboard[row][2]) {
                    return (gameboard[row][0] == TicTacToePlayer.MAX) ? TicTacToePlayer.MAX : TicTacToePlayer.MIN;
                }
                    

        //Cheking for columns
        for (int j = 0 ; j < gameboard.length ; j++) {
            if (gameboard[0][j] != null && gameboard[0][j] == gameboard[1][j] && gameboard[1][j] == gameboard[2][j])
                return (gameboard[0][j] == TicTacToePlayer.MAX) ? TicTacToePlayer.MAX : TicTacToePlayer.MIN;
        }

        //Cheking for diagonals
        if (gameboard[1][1] != null && (gameboard[0][0] == gameboard[1][1] && gameboard[1][1] == gameboard[2][2]
            || gameboard[0][2] == gameboard[1][1] && gameboard[1][1] == gameboard[2][0]))
                return (gameboard[1][1] == TicTacToePlayer.MAX) ? TicTacToePlayer.MAX : TicTacToePlayer.MIN;
        return null;
    }


}
