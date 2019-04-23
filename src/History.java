import java.io.*;

public class History {
	private String fileName;
//	private static int fileNumber;
	private static File gameFile = new File("game.txt");
//	private static Piece[] position;
	private static int hTurnCounter = 0;
	
	public static void savePosition(Piece[] p, int count) {
		hTurnCounter = count;
		// write position to a generated file
		
		// flag for start of new game (to clear gameFile)
//		Boolean newGame;
//		if (count > 1) {
//			newGame = true;			// does this work?
//		}
//		else {
//			newGame = false;
//		}
		// write to gameFile
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(gameFile, true));  // true boolean to turn on "append" mode, does not refresh file at start of game now***
			for (Piece piece:p) {
				writer.write(piece.getColor() + "," + piece.getType() + " ");
			}
			writer.newLine();
			writer.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void clearFile() {
		// clear gameFile
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(gameFile, false));  // true boolean to turn on "append" mode, does not refresh file at start of game now
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static Piece[] readPosition(int move) {
		// read specific line (position) from previously generated file to Piece[] to be returned
		String line = null;
		Piece[] p = new Piece[64];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(gameFile));  // stack overflow bug***? //
			for (int i = 0; i <= move; i++) {
				line = reader.readLine();
			}
			String[] positionInfo = line.split(" ");
			for (int j = 0; j < 64; j++) {
				String[] pieceInfo = positionInfo[j].split(",");
				if (pieceInfo[1].equals("King")) {
					p[j] = new King(pieceInfo[0]);
				}
				if (pieceInfo[1].equals("Queen")) {
					p[j] = new Queen(pieceInfo[0]);
				}
				if (pieceInfo[1].equals("Knight")) {
					p[j] = new Knight(pieceInfo[0]);
				}
				if (pieceInfo[1].equals("Bishop")) {
					p[j] = new Bishop(pieceInfo[0]);
				}
				if (pieceInfo[1].equals("Rook")) {
					p[j] = new Rook(pieceInfo[0]);
				}
				if (pieceInfo[1].equals("Pawn")) {
					p[j] = new Pawn(pieceInfo[0]);
				}
				if (pieceInfo[1].equals("Null")) {
					p[j] = new Null();
				}
			}
			
		} catch (IOException e) {e.printStackTrace();}
		return p;
	}
	
	public static Piece[] readPosition() {
		// read specific line of selected file to Piece[] to be returned (use dialog window or argument)
		return null;
	}
	
	public static int getHTurnCounter() {
		return hTurnCounter;
	}
}