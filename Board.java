public class Board {
    private Piece[][] board; // sets up a private instance field for the board

    
    public Board() {
      this.board = new Piece[8][8]; // Chessboard is 8x8 // sets the size of the board
      setupPieces();
    }
    

    
    public Piece[][] getBoard() { // returns the board safely to any parts of the program that might need it
      return board;
    }
    
    public Piece getPiece(int row, int column) { // returns a specific piece of the board
      return board[row][column];
    }
    
    public Piece getPiece(int[] coords) { // same method as above but different implementations
      return board[coords[0]][coords[1]];
    }
    
    public void setPiece(int row, int column, Piece piece) { // sets  a piece to another when necessary to switch spots or for kills
      board[row][column] = piece;
      if (piece != null) {
          int[] smth = new int[2];
          smth[0] = row;
          smth[1] = column;
          piece.setPosition(smth);
      }
    }
    
    public void setPiece(int[] coords, Piece piece) { // same method as above but different implementations
      board[coords[0]][coords[1]] = piece;
      if (piece != null) {
          piece.setPosition(coords);
      }
    }
    
    private void setupPieces() { // sets up an array of pieces
      // Place Rooks
      board[0][0] = new Rook(0, 0, "black");
      board[0][7] = new Rook(0, 7, "black");
      board[7][0] = new Rook(7, 0, "white");
      board[7][7] = new Rook(7, 7, "white");
      // Place Knights
      board[0][1] = new Knight(0, 1, "black");
      board[0][6] = new Knight(0, 6, "black");
      board[7][1] = new Knight(7, 1, "white");
      board[7][6] = new Knight(7, 6, "white");
      // Place Bishops
      board[0][2] = new Bishop(0, 2, "black");
      board[0][5] = new Bishop(0, 5, "black");
      board[7][2] = new Bishop(7, 2, "white");
      board[7][5] = new Bishop(7, 5, "white");
      // Place Queens
      board[0][3] = new Queen(0, 3, "black");
      board[7][3] = new Queen(7, 3, "white");
      // Place Kings
      board[0][4] = new King(0, 4, "black");
      board[7][4] = new King(7, 4, "white");
      // Place Pawns
      for (int i = 0; i < 8; i++) {
          board[1][i] = new Pawn(1, i, "black");
          board[6][i] = new Pawn(6, i, "white");
      }
    }
    
    public void movePiece(int[] start, int[] end) { // moves the piece when needed, but not without checking if the move is valid or not
      if (board[start[0]][start[1]] != null &&
              board[start[0]][start[1]].isValidMove(end, board)) {
    
          board[end[0]][end[1]] = board[start[0]][start[1]];
          board[end[0]][end[1]].setPosition(end);
          board[start[0]][start[1]] = null;
      }
    }
}
