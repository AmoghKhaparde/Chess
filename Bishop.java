public class Bishop extends Piece {

	public Bishop(int row, int col, String color) {
        super(row, col, color, "Bishop"); // the Piece class is the super so this line tells it what row, column and color and the type of piece
	}

    public boolean isValidMove(int[] end, Piece[][] board) { // this method checks to see if the the proposed move is valid or not
        int x = end[0];
        int y = end[1];
        if (super.getRow() == x && super.getColumn() == y) {
          return false;
        }

          int delta_row = Math.abs(x - super.getRow());
          int delta_col = Math.abs(y - super.getColumn());
    
          boolean diagonal = false; // checks if the delta y and delta x and equal, which means the bishop can travel along the diagonal
          if (delta_row == delta_col) {
            diagonal = true;
          }
    
          if (!diagonal) {
              return false; // if it cant travel on the diagonal, say its not valid
          }
        
        // the code below is meant to check if there are pieces in the way of the bishop's destination, if there are it is not a valid move
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
    
          return (board[x][y] == null || board[x][y].getColor() != getColor()); // checks if the destination is empty or the opposite colored piece
  }

}
