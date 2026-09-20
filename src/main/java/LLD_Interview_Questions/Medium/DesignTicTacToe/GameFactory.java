package LLD_Interview_Questions.Medium.DesignTicTacToe;

public class GameFactory {
    Game createGame(GameType gameType, int size){
        Game game = new Game(gameType,size);
        if(GameType.STANDARD==gameType){
            game.setRule(new StandardRule());
            return game;
        }
        return null;
    }


}
