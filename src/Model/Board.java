package Model;


import java.util.ArrayList;
import java.util.List;

public class Board {
    private Piece [][] board;
    private int size;

    public Board(int size){
        this.size = size;
        board = new Piece[size][size];
    }

    public boolean isValidPosition(int row, int column){
        if(row<0 || row>=size || column<0 || column>=size) {
            return false;
        }
        if(board[row][column]!=null){
            return false;
        }
        return true;
    }

    public void addPiece(int row, int column, Piece piece){
        board[row][column] = piece;
    }

    public List<Pair<Integer,Integer>> getFreeCells(){
        List<Pair<Integer,Integer>> freeCells = new ArrayList<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==null){
                    freeCells.add(new Pair<>(i,j));
                }
            }
        }
        return freeCells;
    }

    public void printBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){

                if(board[i][j]!=null){
                    System.out.print(board[i][j].getPieceType().name()+"  ");
                }else{
                    System.out.print("   ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
