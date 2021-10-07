package model;

public enum TicTacToeTile {
    X {
        @Override
        public char toChar() { return 'X'; }

        @Override
        public TicTacToeTile getOppositeTile() {
            return TicTacToeTile.O;
        }
        
    }, 
    
    O {
        @Override
        public char toChar() { return 'O'; }

        @Override
        public TicTacToeTile getOppositeTile() {
            return TicTacToeTile.X;
        }
    };

    public abstract char toChar();
    public abstract TicTacToeTile getOppositeTile();
}
