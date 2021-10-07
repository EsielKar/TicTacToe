package model;

import java.util.LinkedList;

public final class TicTacToeState{
    protected final TicTacToeTile[][] gameboard;
    
    public TicTacToeState(TicTacToeTile[][] gameboard) {
        this.gameboard = gameboard;
    }

    public TicTacToeTile[][] getGameboard() {
        TicTacToeTile[][] copy = new TicTacToeTile[gameboard.length][];
        for (int i = 0 ; i < gameboard.length ; i++)
            copy[i] = gameboard[i].clone();
        return copy;
    }

    public int remainingMoves() {
        int count = 0;
        for (int i = 0 ; i < gameboard.length ; i++)
            for (int j = 0 ; j < gameboard[i].length ; j++)
                if (gameboard[i][j] == null) count++;
        return count;
    }

    public boolean isFinished() {
        return remainingMoves() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < gameboard.length ; i++) {
            for (int j = 0 ; j < gameboard[i].length ; j++) {
                sb.append((gameboard[i][j] == null ? ' ' : gameboard[i][j].toChar()));
                if (j < gameboard[i].length - 1) {
                    sb.append('|');
                }
            }
            sb.append('\n');
            if (i < gameboard.length - 1) {
                for (int j = 0 ; j < gameboard[i].length * 2 - 1 ; j++)
                    sb.append('-');
                sb.append('\n');
            } 
            
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

    private boolean equallity(TicTacToeTile[][] gameboard) {
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

    public LinkedList<TicTacToeState> generateNextStatesFiltered(TicTacToeTile player) {
        LinkedList<TicTacToeState> states = new LinkedList<TicTacToeState>();
            for (int i = 0 ; i < gameboard.length ; i++) {
                for (int j = 0 ; j < gameboard[i].length ; j++) {
                    if (gameboard[i][j] == null) {
                        TicTacToeTile[][] newGameboard = getGameboard();
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
}
