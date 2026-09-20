package LLD_Interview_Questions.Medium.DesignTicTacToe;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class IObserver {
    abstract void update(String msg);
}
