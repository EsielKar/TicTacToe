import model.MinimaxTicTacToeStrategy;
import model.TicTacToeFunction;
import model.TicTacToeNode;
import model.TicTacToeState;
import model.TicTacToePlayer;

public class App {

    public static void main(String[] args) throws Exception {
        TicTacToeState inicial = new TicTacToeState(
            new TicTacToePlayer[][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }
        );


        TicTacToeNode inicialNode = new TicTacToeNode(inicial, TicTacToePlayer.MAX);
        MinimaxTicTacToeStrategy strategy = new MinimaxTicTacToeStrategy(6);
        System.out.println(strategy.execute(new TicTacToeFunction(inicialNode)).getRoot().getEvaluation());
    }  
}
