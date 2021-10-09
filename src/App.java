import java.util.Random;
import java.util.Scanner;

import model.MinimaxTicTacToeStrategy;
import model.TicTacToeCPUPlayer;
import model.TicTacToeGame;
import model.TicTacToeHumanPlayer;
import model.TicTacToeTile;

public class App {

    public static void main(String[] args) {
        TicTacToeGame game;
        TicTacToeCPUPlayer cpu;
        Scanner sc = new Scanner(System.in);

        gameloop:
        do {
            System.out.println("Game: Tic Tac Toe");
            System.out.println("¿How do you want to play?");
            System.out.println("Game options");
            System.out.println("1. You vs CPU");
            System.out.println("2. You vs Other");
            System.out.println("3. CPU vs CPU");
            System.out.println("4. Salir");
            System.out.print("Option: ");
            String opc = sc.nextLine();
            
            switch (opc) {
                case "1":
                    difficultyloop:
                    do{
                        System.out.println("Dificultad de la CPU");
                        System.out.println("1. Facil");
                        System.out.println("2. Medio");
                        System.out.println("3. Imposible");
                        System.out.print("Option: ");
                        String difficulty = sc.nextLine();
                        
                        switch (difficulty) {
                            case "1":
                                cpu = new TicTacToeCPUPlayer(
                                    TicTacToeTile.X, 
                                    new MinimaxTicTacToeStrategy(2)
                                );
                                break difficultyloop;
                            case "2":
                                cpu = new TicTacToeCPUPlayer(
                                    TicTacToeTile.X, 
                                    new MinimaxTicTacToeStrategy(4)
                                );
                                break difficultyloop;
                            case "3":
                                cpu = new TicTacToeCPUPlayer(
                                    TicTacToeTile.X, 
                                    new MinimaxTicTacToeStrategy(8)
                                );
                                break difficultyloop;
                            default:
                                System.out.println("Invalid options, please select from [1 to 3]");
                                break;
                        }
                    }while(true);
                    if (new Random().nextBoolean())
                        game = new TicTacToeGame(new TicTacToeHumanPlayer(TicTacToeTile.O), cpu);
                    else 
                        game = new TicTacToeGame(cpu, new TicTacToeHumanPlayer(TicTacToeTile.O));
                    game.play();
                    break;
                
                case "2":
                    if (new Random().nextBoolean())
                        game = new TicTacToeGame(
                            new TicTacToeHumanPlayer(TicTacToeTile.O), 
                            new TicTacToeHumanPlayer(TicTacToeTile.X)
                        );
                    else 
                        game = new TicTacToeGame(
                            new TicTacToeHumanPlayer(TicTacToeTile.X), 
                            new TicTacToeHumanPlayer(TicTacToeTile.O)
                        );
                    game.play();
                    break;
                
                case "3":
                    game = new TicTacToeGame(
                        new TicTacToeCPUPlayer(TicTacToeTile.O, new MinimaxTicTacToeStrategy(new Random().nextInt(8) + 1)), 
                        new TicTacToeCPUPlayer(TicTacToeTile.X, new MinimaxTicTacToeStrategy(new Random().nextInt(8) + 1)) 
                    );
                    game.play();
                    break;
                
                case "4": break gameloop;
            
                default:
                    System.out.println("Invalid options, please select from [1 to 4]");
                    break;
            }
        }while(true);
        sc.close();
    }  
}