package LLD_Interview_Questions.Medium.DesignTicTacToe;

public abstract class Rule {
    abstract boolean checkWin(Board b, Symbol s);
    abstract boolean checkDraw(Board b);
    abstract boolean idValid(Board b,int row,int col);
}
