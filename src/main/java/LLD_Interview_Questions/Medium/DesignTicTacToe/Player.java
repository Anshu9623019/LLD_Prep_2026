package LLD_Interview_Questions.Medium.DesignTicTacToe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private int id;
    private String name;
    int score;
    Symbol s;

    Player(int id, String name,Symbol s){
        this.id = id;
        this.name = name;
        this.s = s;
    }
}
