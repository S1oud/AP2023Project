
import java.util.LinkedList;

import javax.swing.GroupLayout.SequentialGroup;

public class Piece {
    int xp;
    int yp;
    public static int counter = 0;
    int x;
    int y;
    boolean isWhite;
    LinkedList<Piece> pieces;
    String name;

    

    public Piece(int xp, int yp, boolean isWhite,String n, LinkedList<Piece> pieces) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        this.isWhite = isWhite;
        this.pieces=pieces;
        name=n;
        pieces.add(this);
    }
    
    public void move(int xp,int yp,boolean canMove){
        if(!canMove) {
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        } 
        if(ChessGame.getPiece(xp * 64, yp * 64) != null) {
            if(ChessGame.getPiece(this.xp * 64, this.yp * 64).name.equals("pawn") && this.xp == xp) {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
            if(ChessGame.getPiece(xp * 64, yp * 64).isWhite != isWhite)
                ChessGame.getPiece(xp * 64,yp * 64).kill();
            else {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        }
        this.xp=xp;
        this.yp=yp;
        x = xp * 64;
        y = yp * 64;
        counter++;
    }
    public void kill(){
        pieces.remove(this);
    }
    public boolean isPossibleMove(String name, int xp, int yp) {
        if(xp < 0 || xp > 7 || yp < 0 || yp > 7)
            return false;
        if(!name.equals("knight")) {
            if(ChessGame.isPathObstructed(this.xp,this.yp,xp,yp))
                return false;
        }
        if(ChessGame.getPiece(this.xp * 64, this.yp * 64).isWhite && counter % 2 == 1) 
            return false;
        if(!ChessGame.getPiece(this.xp * 64, this.yp * 64).isWhite && counter % 2 == 0) 
            return false;

        
        if(name.equals("pawn")) {
            if(ChessGame.getPiece(this.xp * 64, this.yp * 64).isWhite && this.yp == 6) {
                if(this.yp - 2 == yp && this.xp == xp) 
                    return true;
                
            }
            else if(!ChessGame.getPiece(this.xp * 64, this.yp * 64).isWhite && this.yp == 1) {
                if(this.yp + 2 == yp && this.xp == xp) 
                    return true;
            }
            if(!ChessGame.getPiece(this.xp * 64, this.yp * 64).isWhite) {
                if(ChessGame.getPiece(this.xp, (this.yp +1) * 64) != null)
                    return false;
                if(this.yp + 1 == yp && this.xp == xp)
                    return true;
                if(ChessGame.getPiece((this.xp - 1) * 64, (this.yp +1) * 64) != null || ChessGame.getPiece((this.xp + 1) * 64, (this.yp +1) * 64) != null) {
                    if(this.yp + 1 == yp && this.xp - 1 == xp)
                        return true;
                    else if(this.yp + 1 == yp && this.xp + 1 == xp)
                        return true;
                }
            }
            else if(ChessGame.getPiece(this.xp * 64, this.yp * 64).isWhite) {
                if(ChessGame.getPiece(this.xp, (this.yp - 1) * 64) != null)
                    return false;
                if(this.yp - 1 == yp && this.xp == xp)
                    return true;
                if(ChessGame.getPiece((this.xp - 1) * 64, (this.yp -1) * 64) != null || ChessGame.getPiece((this.xp + 1) * 64, (this.yp -1) * 64) != null) {
                    if(this.yp - 1 == yp && this.xp - 1 == xp)
                        return true;
                    else if(this.yp - 1 == yp && this.xp + 1 == xp)
                        return true;
                }
            }
            
            return false;
        }

        else if(name.equals("rook")) {
            if(this.yp < yp || this.yp > yp && this.xp == xp) 
                return true;
            else if(this.yp == yp && this.xp < xp || this.xp > xp)
                return true;
            return false;
        }

        else if(name.equals("knight")) {
            if(this.yp + 2 == yp && this.xp - 1 == xp) 
                return true;
            else if(this.yp + 2 == yp && this.xp + 1 == xp) 
                return true;
            else if(this.yp - 2 == yp && this.xp - 1 == xp) 
                return true;
            else if(this.yp - 2 == yp && this.xp + 1 == xp) 
                return true;
            else if(this.xp + 2 == xp && this.yp - 1 == yp) 
                return true;
            else if(this.xp + 2 == xp && this.yp + 1 == yp) 
                return true;
            else if(this.xp - 2 == xp && this.yp - 1 == yp) 
                return true;
            else if(this.xp - 2 == xp && this.yp + 1 == yp) 
                return true;
            return false;
        }
        
        else if(name.equals("bishop")) {
            if(xp - this.xp == yp - this.yp)
                return true;
            else if(xp - this.xp == -1 * (yp - this.yp))
                return true;
            return false;
        }
        else if(name.equals("queen")) {
            if(xp - this.xp == yp - this.yp)
                return true;
            else if(xp - this.xp == -1 * (yp - this.yp))
                return true;
            else if(this.yp < yp || this.yp > yp && this.xp == xp) 
                return true;
            else if(this.yp == yp && this.xp < xp || this.xp > xp)
                return true;
            return false;
        }
        
        else if(name.equals("king")) {
            if(this.yp + 1 == yp && this.xp == xp)
                return true;
            else if(this.yp - 1 == yp && this.xp == xp)
                return true;
            else if(this.yp == yp && this.xp + 1 == xp)
                return true;
            else if(this.yp == yp & this.xp - 1 == xp) 
                return true;
            else if(this.yp + 1 == yp && this.xp + 1 == xp)
                return true;
            else if(this.yp - 1 == yp && this.xp + 1 == xp)
                return true;
            else if(this.yp + 1 == yp && this.xp - 1 == xp) 
                return true;
            else if(this.yp - 1 == yp && this.xp - 1 == xp)
                return true;
            return false;
        }
        return false;
    }
}