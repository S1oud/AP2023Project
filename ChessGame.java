
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ChessGame {
    public static LinkedList<Piece> pieces=new LinkedList<>();
    public static Piece selectedPiece;
    public static int aX;
    public static int aY;
    public static void main(String[] args) throws IOException {
        BufferedImage all=ImageIO.read(new File("/AP2023Project/chess.png"));
        Image imgs[]=new Image[12];
        int index=0;
        for(int y=0;y<400;y+=200){
        for(int x=0;x<1200;x+=200){
            imgs[index]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
       index++;
        }    
        }
        Piece [][] nullPieces = new Piece [4][8];
        Piece brook=new Piece(0, 0, false, "rook", pieces);
        Piece bkinght=new Piece(1, 0, false, "knight", pieces);
        Piece bbishop=new Piece(2, 0, false, "bishop", pieces);
        Piece bqueen=new Piece(3, 0, false, "queen", pieces);
        Piece bking=new Piece(4, 0, false, "king", pieces);
        Piece bbishop2=new Piece(5, 0, false, "bishop", pieces);
        Piece bkight2=new Piece(6, 0, false, "knight", pieces);
        Piece brook2=new Piece(7, 0, false, "rook", pieces);
        Piece bpawn1=new Piece(1, 1, false, "pawn", pieces);
        Piece bpawn2=new Piece(2, 1, false, "pawn", pieces);
        Piece bpawn3=new Piece(3, 1, false, "pawn", pieces);
        Piece bpawn4=new Piece(4, 1, false, "pawn", pieces);
        Piece bpawn5=new Piece(5, 1, false, "pawn", pieces);
        Piece bpawn6=new Piece(6, 1, false, "pawn", pieces);
        Piece bpawn7=new Piece(7, 1, false, "pawn", pieces);
        Piece bpawn8=new Piece(0, 1, false, "pawn", pieces);
        
        

        Piece wrook=new Piece(0, 7, true, "rook", pieces);
        Piece wkinght=new Piece(1, 7, true, "knight", pieces);
        Piece wbishop=new Piece(2, 7, true, "bishop", pieces);
        Piece wqueen=new Piece(3, 7, true, "queen", pieces);
        Piece wking=new Piece(4, 7, true, "king", pieces);
        Piece wbishop2=new Piece(5, 7, true, "bishop", pieces);
        Piece wkight2=new Piece(6, 7, true, "knight", pieces);
        Piece wrook2=new Piece(7, 7, true, "rook", pieces);
        Piece wpawn1=new Piece(1, 6, true, "pawn", pieces);
        Piece wpawn2=new Piece(2, 6, true, "pawn", pieces);
        Piece wpawn3=new Piece(3, 6, true, "pawn", pieces);
        Piece wpawn4=new Piece(4, 6, true, "pawn", pieces);
        Piece wpawn5=new Piece(5, 6, true, "pawn", pieces);
        Piece wpawn6=new Piece(6, 6, true, "pawn", pieces);
        Piece wpawn7=new Piece(7, 6, true, "pawn", pieces);
        Piece wpawn8=new Piece(0, 6, true, "pawn", pieces);
                
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel pn=new JPanel(){
            @Override
            public void paint(Graphics g) {
            boolean white=true;
                for(int y= 0;y<8;y++){
                    for(int x= 0;x<8;x++){
                        if(white)
                            g.setColor(new Color(235,235, 208));
                        else
                            g.setColor(new Color(119, 148, 85));
                        g.fillRect(x*64, y*64, 64, 64);
                        white=!white;
                }
                    white=!white;
                }
                for(Piece p: pieces){
                    int index=0;
                    if(!p.name.equals("none")) {
                        if(p.name.equalsIgnoreCase("king")){
                            index=0;
                        }
                        if(p.name.equalsIgnoreCase("queen")){
                            index=1;
                        }
                        if(p.name.equalsIgnoreCase("bishop")){
                            index=2;
                        }
                        if(p.name.equalsIgnoreCase("knight")){
                            index=3;
                        }
                        if(p.name.equalsIgnoreCase("rook")){
                            index=4;
                        }
                        if(p.name.equalsIgnoreCase("pawn")){
                            index=5;
                        }
                        if(!p.isWhite){
                            index+=6;
                        }
                        g.drawImage(imgs[index], p.x, p.y, this);
                    }
                }
            }
            
        };
        frame.add(pn);
        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectedPiece = getPiece(e.getX(), e.getY());
                aX = e.getX();
                aY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX() / 64, e.getY() / 64,selectedPiece.isPossibleMove(getPiece(aX,aY).name, e.getX() / 64, e.getY() / 64)) ;
                frame.repaint();
                if(isEnd()) {
                    frame.dispose();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
        frame.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece != null) {
                    selectedPiece.x = e.getX() - 32;
                    selectedPiece.y = e.getY() - 32;
                    frame.repaint();
                }

            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
        });

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
    public static Piece getPiece(int x, int y) {
        int xp = x/64;
        int yp = y/64;
        for(Piece p: pieces) {
            if(p.xp == xp && p.yp == yp) {
                return p;
            }
        }
        return null;
    }

    public static boolean isPathObstructed(int x1, int y1, int x2, int y2) {
        // Check if the positions are on the same row or column
        if (x1 == x2) {
            // Check for obstructions along the column
            int start = Math.min(y1, y2) + 1;
            int end = Math.max(y1, y2);
            for (int y = start; y < end; y++) {
                if (getPiece(x1 * 64, y * 64) != null) {
                    return true; // Obstruction found
                }
            }
        } else if (y1 == y2) {
            // Check for obstructions along the row
            int start = Math.min(x1, x2) + 1;
            int end = Math.max(x1, x2);
            for (int x = start; x < end; x++) {
                if (getPiece(x * 64, y1 * 64) != null) {
                    return true; // Obstruction found
                }
            }
        } else if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
            // Check for obstructions along the diagonal

            // Determine the direction of movement
            int dx = (x2 > x1) ? 1 : -1;
            int dy = (y2 > y1) ? 1 : -1;

            int x = x1 + dx;
            int y = y1 + dy;

            while (x != x2 && y != y2) {
                if (getPiece(x * 64, y * 64) != null) {
                    return true; // Obstruction found
                }
                x += dx;
                y += dy;
            }
        }
        
        return false; // No obstruction found
    }
    public static boolean isEnd() {
        int newCounter = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(getPiece(i*64,j*64) != null) {
                    if(getPiece(i * 64, j*64).name.equals("king"))
                        newCounter++;
                }
            }
        }
        if(newCounter < 2)
            return true;
        else
            return false;
    }

}