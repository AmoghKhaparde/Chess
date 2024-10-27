import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    private Square[][] squares = new Square[8][8]; // sets the GUI board
    private Game game = new Game();
    
    public GUI() {
      setTitle("Chess Game");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new GridLayout(8, 8));
      initializeBoard();
      pack(); // Adjusts window size to fit the chessboard
      setVisible(true);
    }
    
    public String getFile(String c, String type) {
        return "images/" + c + "_" + type + ".png";
    }
    
    public void initializeBoard() {
        for (int i=0;i<squares.length;i++) {
            for (int j=0;j<squares[i].length;j++) {
                final int r = i;
                final int c = j;
                Square s = new Square(r, c);
                
                s.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (game.handleSquareSelection(r, c)) {
                          refreshBoard(); // everytime a mouse is clicked refresh the board and check certain game statuses
                          checkGameState();
                          checkGameOver();
                        }
                    }
                });
                add(s);
                squares[r][c] = s;
            }
        }
        refreshBoard();
        
    }
    
    
    
    // sets each square to the correct image based on the type and color
    public void refreshBoard() {
        Board board = game.getBoard();
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                Piece piece = board.getPiece(row, col);
                
                if (piece != null){
                    String type = piece.getType();
                    String color = piece.getColor();
                    String filepath = getFile(color, type);
                    
                    squares[row][col].setPieceSymbol(filepath);
                } else {
                    squares[row][col].clearPiece();
                }
            }
        }
    }
    
    public void checkGameState() {
        String player = game.getTurn(); // This method should return the current player's color
    
        if (game.isInCheck(player)) {
            if (game.isInCheckmate(player)){
                JOptionPane.showMessageDialog(this, player + " is in checkmate!");
            }
            else
            JOptionPane.showMessageDialog(this, player + " is in check!");
        }
        
        for (int j=0;j<8;j++) {
            if (game.getBoard().getPiece(0,j) != null && game.getBoard().getPiece(0, j).getType().equals("Pawn") && game.getBoard().getPiece(0, j).getColor().equals("white")) {
                String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                int response = JOptionPane.showOptionDialog(
                    this,
                    "What piece would you like to promote to?",  // Message
                    "Pawn Promotion",  // Title
                    JOptionPane.DEFAULT_OPTION,  // Option type
                    JOptionPane.QUESTION_MESSAGE,  // Message type
                    null,  // Icon; use default icon
                    options,  // Options array
                    options[0]  // Initial value
                );
                if (response >= 0) { // depending on the response, a different piece will spawn in
                    String selectedPiece = options[response];
                    System.out.println("User choice for promotion: " + selectedPiece);
                    int[] coords = {0,j};
                    switch (selectedPiece) {
                        case "Queen":
                            game.promote(coords, new Queen(0,j,"white"));
                            refreshBoard();
                            break;
                        case "Rook":
                            game.promote(coords, new Rook(0, j, "white"));
                            refreshBoard();
                            break;
                        case "Bishop":
                            game.promote(coords, new Bishop(0, j,"white"));
                            refreshBoard();
                            break;
                        case "Knight":
                            game.promote(coords, new Knight(0, j, "white"));
                            refreshBoard();
                            break;
                    }
                }
            }
        }
        
        for (int j=0;j<8;j++) {
            if (game.getBoard().getPiece(7,j) != null && game.getBoard().getPiece(7, j).getType().equals("Pawn") && game.getBoard().getPiece(7, j).getColor().equals("black")) {
                String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                int response = JOptionPane.showOptionDialog(
                    this,  // Parent component; 'this' can be used if inside an instance method
                    "What piece would you like to promote to?",  // Message
                    "Pawn Promotion",  // Title
                    JOptionPane.DEFAULT_OPTION,  // Option type
                    JOptionPane.QUESTION_MESSAGE,  // Message type
                    null,  // Icon; use default icon
                    options,  // Options array
                    options[0]  // Initial value
                );
                if (response >= 0) {
                    String selectedPiece = options[response];
                    System.out.println("User choice for promotion: " + selectedPiece);
                    int[] coords = {7,j};
                    switch (selectedPiece) {
                        case "Queen":
                            game.promote(coords, new Queen(7,j,"black"));
                            refreshBoard();
                            break;
                        case "Rook":
                            game.promote(coords, new Rook(7, j, "black"));
                            refreshBoard();
                            break;
                        case "Bishop":
                            game.promote(coords, new Bishop(7, j,"black"));
                            refreshBoard();
                            break;
                        case "Knight":
                            game.promote(coords, new Knight(7, j, "black"));
                            refreshBoard();
                            break;
                    }
                }
            }
        }
    }
    
    public void checkGameOver() {
        if (game.getIsCheckmate()) {
            int response = JOptionPane.showConfirmDialog(this, "Checkmate! Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                String[] args = {"hey"};
              main(args); // calls the main function again, effectively restarting the program  
            } else {
              System.exit(0);
            }
        }
    }
    
    public static void main(String[] args) {
        new GUI();
        // sources:
        // https://hackr.io/blog/how-to-build-a-java-chess-game-app - where we looked for help when we were stuck (a couple of times)
        // https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent - where we got the chess pieces from
        // if you select something and its destination and it does not move, try one more time and ensure that there were no other buttons clicked before it
        
    }
}
