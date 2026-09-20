package LLD_Interview_Questions.Medium.DesignTicTacToe;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class Board {
    List<List<Symbol>> grid;
    int size;
    Symbol emptyCell;

    Board(int s){
        this.size = s;
        emptyCell = new Symbol("_");
        List<List<Symbol>> grid = new ArrayList<>();
        for(int i=0;i<size;i++){
            List<Symbol> symbols = new ArrayList<>();
            for (int j=0;j<size;j++){
                symbols.add(new Symbol("-"));
            }
            grid.add(symbols);
        }
        this.grid = grid;
    }

    boolean isCellEmpty(int row, int col){
            if(row<0 || row>= size || col<0 || col>=size){
                return false;
            }
            return grid.get(row).get(col)== emptyCell;
    }

    boolean placeMark(int row, int col,Symbol mark){
        if(row<0 || row>= size || col<0 || col>=size){
            return false;
        }
        if(isCellEmpty(row,col)){
            return false;
        }
        grid.get(row).add(mark);
        return true;
    }

    Symbol getCell(int row, int col){
        if(row<0 || row>= size || col<0 || col>=size){
            return emptyCell;
        }
        return grid.get(row).get(col);
    }
    void display(){
        System.out.println("");
        for(int i=0;i<size;i++){
            System.out.println(i + " ");
            for(int  j=0;j<size;j++){
                System.out.println(grid.get(i).get(j)+" ");
            }
        }
    }


}
