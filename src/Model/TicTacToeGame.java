package Model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    private Deque<Player> players;
    private Board gameBoard;

    public void initializeGame() {
        Player player1  = new Player("Player1",new PieceX());
        Player player2  = new Player("Player2",new PieceO());

        players = new LinkedList<>();
        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
    }

    public void startGame(){
        boolean tie = false;
        Player currentPlayer;
        Piece piece;
        while(!tie){

            currentPlayer = players.removeFirst();
            piece = currentPlayer.getPiece();

            gameBoard.printBoard();
            if(noFreeCells(gameBoard)){
                tie = true;
                continue;
            }

            Pair<Integer,Integer>input = getUserInput(currentPlayer);
            int row = input.first;
            int column = input.second;

            if(!gameBoard.isValidPosition(input.first, input.second)){
                System.out.println("Incorrect position entered, try again");
                players.addFirst(currentPlayer);
                continue;
            }
            gameBoard.addPiece(row,column,piece);
            players.addLast(currentPlayer);

            boolean winner  = isThereWinner(row,column,piece.getPieceType());
            if(winner){
                gameBoard.printBoard();
                System.out.println(currentPlayer.getName()+" has won the game!!");
                return;
            }
        }

        System.out.println("The game is tied!");

    }

    public boolean isThereWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<gameBoard.getSize();i++) {

            if(gameBoard.getBoard()[row][i] == null || gameBoard.getBoard()[row][i].getPieceType() != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<gameBoard.getSize();i++) {

            if(gameBoard.getBoard()[i][column] == null || gameBoard.getBoard()[i][column].getPieceType() != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.getSize();i++,j++) {
            if (gameBoard.getBoard()[i][j] == null || gameBoard.getBoard()[i][j].getPieceType() != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.getSize()-1; i<gameBoard.getSize();i++,j--) {
            if (gameBoard.getBoard()[i][j] == null || gameBoard.getBoard()[i][j].getPieceType() != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

    private Pair<Integer, Integer> getUserInput(Player currentPlayer) {
        System.out.print("Player:" + currentPlayer.getName() + " Enter row,column: ");
        Scanner inputScanner = new Scanner(System.in);
        String s = inputScanner.nextLine();
        String[] values = s.split(",");
        int inputRow = Integer.valueOf(values[0]);
        int inputColumn = Integer.valueOf(values[1]);
        return new Pair<>(inputRow,inputColumn);
    }

    private boolean noFreeCells(Board gameBoard) {
        List<Pair<Integer,Integer>> freeCells = gameBoard.getFreeCells();
        if(freeCells.isEmpty()){
            return true;
        }
        return false;
    }


}
