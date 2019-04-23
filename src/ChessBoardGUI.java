import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;

public class ChessBoardGUI extends JPanel {

	static Position pos;    		
	static Piece[] position;
	static ChessBoardGUI board;
	private Boolean boardFlipped = false;
	MyMouseListener mml = new MyMouseListener(); // want to instatiate inner classes
	
	
	// constructor to recieve position object to display board with...
	 public ChessBoardGUI(Position p) {
	 	pos = p;
		position = pos.getPosition();
	 }
	 
	 public ChessBoardGUI(){}  // to "fix" error with ChessBoardGUI in BuildGUI method
	
	public void paintComponent(Graphics g) {
		g.fillRect(100,100,400,400);
		for (int x = 0; x < 400; x += 100) {
			for (int y = 0; y < 400; y += 100) {
				g.clearRect(100 + x,100 + y,50,50);
			}
		}
		for (int x = 50; x < 400; x += 100) {
			for (int y = 50; y < 400; y += 100) {
				g.clearRect(100 + x,100 + y,50,50);
			}
		}
		
		// read position array and paint pieces onto board accordingly
		
		Image BlackRook = new ImageIcon("D:\\Head First Java\\Chess\\BlackRook.png").getImage();
		Image BlackBish = new ImageIcon("D:\\Head First Java\\Chess\\BlackBish.png").getImage();
		Image BlackKnight = new ImageIcon("D:\\Head First Java\\Chess\\BlackKnight.png").getImage();
		Image BlackQueen = new ImageIcon("D:\\Head First Java\\Chess\\BlackQueen.png").getImage();
		Image BlackKing = new ImageIcon("D:\\Head First Java\\Chess\\BlackKing.png").getImage();
		Image BlackPawn = new ImageIcon("D:\\Head First Java\\Chess\\BlackPawn.png").getImage();
		Image WhiteRook = new ImageIcon("D:\\Head First Java\\Chess\\WhiteRook.png").getImage();
		Image WhiteBish = new ImageIcon("D:\\Head First Java\\Chess\\WhiteBish.png").getImage();
		Image WhiteKnight = new ImageIcon("D:\\Head First Java\\Chess\\WhiteKnight.png").getImage();
		Image WhiteQueen = new ImageIcon("D:\\Head First Java\\Chess\\WhiteQueen.png").getImage();
		Image WhiteKing = new ImageIcon("D:\\Head First Java\\Chess\\WhiteKing.png").getImage();
		Image WhitePawn = new ImageIcon("D:\\Head First Java\\Chess\\WhitePawn.png").getImage();
	
		for (int z = 0; z < 64; z++) {
			int xPos = 105 + (z%8)*50;
			int yPos = 105 + 50*Math.round(z/8);	
			
			if (position[z].getType().equals("Rook") && position[z].getColor().equals("Black")) {
				g.drawImage(BlackRook,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Bishop") && position[z].getColor().equals("Black")) {
				g.drawImage(BlackBish,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Knight") && position[z].getColor().equals("Black")) {
				g.drawImage(BlackKnight,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Queen") && position[z].getColor().equals("Black")) {
				g.drawImage(BlackQueen,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("King") && position[z].getColor().equals("Black")) {
				g.drawImage(BlackKing,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Pawn") && position[z].getColor().equals("Black")) {
				g.drawImage(BlackPawn,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Rook") && position[z].getColor().equals("White")) {
				g.drawImage(WhiteRook,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Bishop") && position[z].getColor().equals("White")) {
				g.drawImage(WhiteBish,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Knight") && position[z].getColor().equals("White")) {
				g.drawImage(WhiteKnight,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Queen") && position[z].getColor().equals("White")) {
				g.drawImage(WhiteQueen,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("King") && position[z].getColor().equals("White")) {
				g.drawImage(WhiteKing,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Pawn") && position[z].getColor().equals("White")) {
				g.drawImage(WhitePawn,xPos,yPos,40,40,this);
			}
			else if (position[z].getType().equals("Null")) {}
			else {
				System.out.println("Cannot display position array at " + xPos + ", " + yPos);
			}
		}
		
		// each time board is repainted, check for end game state
		pos.isMate();
		pos.isMaterial();
		pos.isDraw();
		
		// use booleans returned from ^end game state methods to trigger display of diff. banners***
		// Checkmate banner
		
	}
	
	public static void buildGUI() {

		board = new ChessBoardGUI();
		board.addMouseListener(new MyMouseListener());
		//board.add

		JFrame theFrame = new JFrame("Chess");
		theFrame.setSize(600,600);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		theFrame.setBackground(Color.LIGHT_GRAY);
		theFrame.getContentPane().add(BorderLayout.CENTER, board);

		theFrame.setVisible(true);
	}
	
	public static class MyMouseListener implements MouseListener { 		// board flip handling required***
	
		int columnA;
		int columnB;
		int rowA;
		int rowB;
		
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			
			// get position info A
			int xPoint = e.getX();
			int yPoint = e.getY();
			columnA = (int) ((e.getX() - 100)/50);
			rowA = (int) ((e.getY() - 100)/50);
			//System.out.println("rowA: "+ rowA + " columnA: " + columnA);
			
			// make piece picture follow mouse
		}
		public void mouseReleased(MouseEvent e) {
			
			// get position info B
			int xPoint = e.getX();
			int yPoint = e.getY();
			columnB = (int) ((e.getX() - 100)/50);
			rowB = (int) ((e.getY() - 100)/50);
			//System.out.println("rowB: "+ rowB + " columnB: " + columnB);

			// call move method with user input
			Integer index = rowA*8+columnA;
			Integer attemptIndex = rowB*8+columnB;
			pos.move(index, attemptIndex);
		}
	
		public int getColumnA() {
			return columnA;
		}
		
		public int getColumnB() {
			return columnB;
		}
	
		public int getRowA() {
			return rowA;
		}
	
		public int getRowB() {
			return rowB;
		}
	}
	
	// updates static variable of position array position
	public static void setPosition(Piece[] p) {
		position = p;
		board.repaint();
	}
	
	
	
}