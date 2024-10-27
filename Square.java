import javax.swing.*;
import java.awt.*;

public class Square extends JButton { // this class is what the chess gui list is made up of: buttons
    private int row;
    private int col;
    
    public Square(int row, int col){
        this.row = row; // stores row and col
        this.col = col;
        makeButton(); // sets up the button
    }
    
    private void makeButton(){
        setPreferredSize(new Dimension(60, 60)); //size of button
        
        if ((row + col) % 2 == 1) { //checkered boards
            setBackground(Color.RED);
        } else {
            setBackground(Color.WHITE);
        }
        
    }
    
    public void setPieceSymbol(String picturePath) {
        this.setIcon(new ImageIcon(picturePath)); // sets the picture of the piece within the button
    }
    
    public void clearPiece(){
        this.setIcon(null); // clear the image of the piece when needed
    }
}
