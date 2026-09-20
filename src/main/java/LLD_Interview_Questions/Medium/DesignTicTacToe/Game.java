package LLD_Interview_Questions.Medium.DesignTicTacToe;

import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Game {
    Board board;
    Deque<Player> players;
    List<IObserver> observers;
    boolean gameOver;
    Rule rule;

    Game(GameType gameType, int boardSize){
        this.board = new Board(boardSize);
        this.gameOver = false;
    }

    void addPlayer(Player p){
        players.push(p);
    }

    void addObserver(IObserver observer){
        this.observers.add(observer);
    }

    void notify(String msg){
        for(IObserver observer : observers){
            observer.update(msg);
        }
    }
    void play(){
        if(players.size()<2){
            System.out.println("Need at least 2 players!");
            return;
        }
        notify("Tic Tac toe Game Started!");

        while (!gameOver){
            board.display();

            //Take out the current player from dequeue
            Player currentPlayer = players.getFirst();
            System.out.println(currentPlayer.getName() +" (" +currentPlayer.getS().getMark()+" ) -Enter row and column: ");
            Scanner sc = new Scanner(System.in);
            int r = sc.nextInt();
            int c = sc.nextInt();

            if(rule.idValid(board,r,c)){
                board.placeMark(r,c,currentPlayer.getS());
                notify(currentPlayer.getName() +"played (" +Integer.toHexString(r) + ","+Integer.toString(c)+ ")");

                if(rule.checkWin(board,currentPlayer.getS())){
                    board.display();
                    System.out.println(currentPlayer.getName() +" wins!");

                    notify(currentPlayer.getName()+" wins!");
                    gameOver = true;
                }
                else if(rule.checkDraw(board)){
                    board.display();
                    System.out.println("It's a draw!");
                    notify("Game is draw!");
                    gameOver = true;
                }else {
                    players.pollFirst();
                    players.push(currentPlayer);
                }
            }else {
                System.out.println("Invalid move try again!");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("== TIC TAC TOE GAME ===");


        int boardSize = 0;
        System.out.println("Enter board size (e.g, 3 for 3*3): ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Game game = new Game(GameType.STANDARD,boardSize);

        //add Observer
        IObserver notifier = new ConsoleNotifier();
        game.addObserver(notifier);


        //Create player with custom symbols
        Player player1 = new Player(1,"Anshu",new Symbol("X"));
        Player player2 = new Player(2,"Rahul",new Symbol("0"));

        game.addPlayer(player1);
        game.addPlayer(player2);


        //Play the game
        game.play();

        //Cleanup


    }
}
