public class Piece {
    private int row;
    private int coloumn;
    private String color;
    private String type;

    public Piece(int row, int col, String color, String type){ // this is the super information since all of it is shared among the pieces
        this.row = row;
        this.coloumn = col;
        this.color = color;
        this.type = type;
    }

    // all of the methods below are nothing crazy, they just give some information when necessary, for example: row location

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.coloumn;
    }

    public int[] getCoords(){
        int[] coords = {row, coloumn};
        return coords; 
    }

    public String getColor(){
        return this.color;
    }
    
    public String getType() {
        return type;
    }
    
    public Piece getPiece(int row, int col) {
        return null;    
    }
    
    public Piece getPiece(int[] coords) {
        return null;    
    }
    

    public void setPosition(int[] coords){
        this.row = coords[0];
        this.coloumn = coords[1];
    }
    
    public boolean getMoved() {
        return true;
    }

    public boolean isValidMove(int x, int y, Piece[][] board){
        return true;
    }
    public boolean isValidMove(int[] coords, Piece[][] board){
        return true;
    }


}
