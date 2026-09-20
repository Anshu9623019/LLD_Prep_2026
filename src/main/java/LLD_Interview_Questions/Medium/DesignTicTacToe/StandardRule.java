package LLD_Interview_Questions.Medium.DesignTicTacToe;

public class StandardRule extends Rule{

    @Override
    boolean checkWin(Board b, Symbol s) {
        int size = b.size;
        //Check row
        for(int i=0;i<size;i++){
            boolean win = true;
            for(int j=0;j<size;j++){
                if(b.getCell(i,j)!=s) {
                    win = false;
                    break;
                }
            }
            if(win=true){
                return true;
            }
        }
        //Check column
        for(int i=0;i<size;i++){
            boolean win = true;
            for(int j=0;j<size;j++){
                if(b.getCell(i,j)!=s){
                    win = false;
                    break;
                }
            }
            if(win=true){
                return true;
            }
        }

        //Check diagonal
        boolean win = true;
        for(int i=0;i<size;i++){
            if(b.getCell(i,i)!=s){
                win = false;
                break;
            }
        }

        //Check anti-diagonal
        win = true;
        for(int i=0;i<size;i++){
            if(b.getCell(i,size-1-i)!=s){
                win = false;
                break;
            }
        }
        return win;
    }

    @Override
    boolean checkDraw(Board b) {
        int size = b.size;
        for (int i=0;i< size;i++){
            for (int j=0;j<size;j++){
                if(b.getCell(i,j)==b.getEmptyCell()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    boolean idValid(Board board,int row,int col) {
        return board.isCellEmpty(row,col);
    }
}
