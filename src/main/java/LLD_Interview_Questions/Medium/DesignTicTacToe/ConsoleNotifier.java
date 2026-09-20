package LLD_Interview_Questions.Medium.DesignTicTacToe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsoleNotifier extends IObserver {
    void update(String msg){
        System.out.println("[Notification]"+msg);
    }
}
