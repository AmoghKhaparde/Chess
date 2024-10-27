public class Rook extends Piece {
    
    public Rook(int row, int col, String color) {
        super(row, col, color, "Rook"); // sets the position and color of the rook
    }

    public boolean isValidMove(int[] end, Piece[][] board) {
        int x = end[0];
        int y = end[1];
      if (super.getRow() == x && super.getColumn() == y) {
          return false;
      }

      boolean line = (super.getRow() == x) || (super.getColumn() == y); // makes sure that the destination is in a line of the starting point

      if (!line) {
          return false; // if its not in a line, return false
      }
        // simulates travelling through that line and if there are any obstacle pieces the destination is not a valid move


      int rowMovement = 1;
      if (x == super.getRow()) {
        rowMovement = 0;
      } else if (x < super.getRow()) {
        rowMovement = -1;
      }
      int colMovement = 1;
      if (y == super.getColumn()) {
        colMovement = 0;
      } else if (y < super.getColumn()) {
        colMovement = -1;
      }

      int currentRow = super.getRow() + rowMovement;
      int currentCol = super.getColumn() + colMovement;
      while (currentRow != x || currentCol != y) {
          if (board[currentRow][currentCol] != null) {
              return false;
          }
          currentRow += rowMovement;
          currentCol += colMovement;
      }

      return (board[x][y] == null || board[x][y].getColor() != getColor()); // checks if the destination is free or occupied by an enemy, where it is fine to move there and kill them
  }
}
