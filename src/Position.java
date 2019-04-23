import java.util.*;
import java.io.*;
import java.lang.*;

// need to fix "teleport bug"

public class Position implements Serializable {
	Piece[] position;
	int turnCounter = 0;
	private Boolean checkMate = false;
	
	// arraylist of past x moves (x=6?) for 3 fold repeat and enpassant
	
	// generate starting position in constructor
	public Position() {
		Piece[] start = new Piece[64];
		start[0] = new Rook("Black");
		start[1] = new Knight("Black");
		start[2] = new Bishop("Black");
		start[3] = new Queen("Black");
		start[4] = new King("Black");
		start[5] = start[2];
		start[6] = start[1];
		start[7] = start[0];
		start[8] = new Pawn("Black");
		for (int i = 9; i < 16; i++){
			start[i] = start[8];
		}
		start[16] = new Null();
		for (int j = 17; j < 48; j++){
			start[j] = start[16];
		}
		start[48] = new Pawn("White");
		for (int k = 49; k < 56; k++){
			start[k] = start[48];
		}
		start[56] = new Rook("White");
		start[57] = new Knight("White");
		start[58] = new Bishop("White");
		start[59] = new Queen("White");
		start[60] = new King("White");
		start[61] = start[58];
		start[62] = start[57];
		start[63] = start[56];
		
		position = start;
		turnCounter = 0;
	}
	
	public Position(Piece[] pos, int turn){ // add turnCount to constructor argument?
		if (pos.length == 64){
		position = pos;
		turnCounter = turn;
		}
	}
	
	public void setPosition(Piece[] pos){
		position = pos;
	}
	
	public Piece[] getPosition(){
		return position;
	}
	
	// move
	public void move(int index, int attemptIndex) {
		// get type and color of piece in 1st square
		// use their generateMoves methods to recieve list of legal moves
		// check to see if 2nd square is on that list
		// move piece if yes, do nothing if no.
		
		// handling turns:
		
		String color = position[index].getColor();
		String turnColor;
		if (turnCounter%2 == 0) {
			turnColor = "White";
		}
		else {
			turnColor = "Black";
		}
		
		if (turnColor.equals(color)) {
			
			HashMap<Integer, String> potentialMoves = position[index].generateMoves(position, index, color);
			
			if (!potentialMoves.isEmpty()) {
				if (potentialMoves.containsKey(attemptIndex)) {
					if (potentialMoves.get(attemptIndex).equals("Move") || potentialMoves.get(attemptIndex).equals("Pawn Move")) {
						position[attemptIndex] = position[index];
						position[index] = new Null();
							// check for checks before saving move
						if (checkFilter(position, color)) { // if no threats to king, move
						// test... System.out.println("king's good");
							ChessBoardGUI.setPosition(position);
							increaseTurnCount();							// add one to the turn counter
							History.savePosition(position, turnCounter);	// write position to file
						} else { 	// if threats, restore position
							position = History.readPosition(turnCounter);
						}
					}
					// pawn promotion (to queen)
					else if (potentialMoves.get(attemptIndex).equals("Promotion")) {
						position[attemptIndex] = new Queen(color); // make method to generate dialog box or ... (for piece selection)
						position[index] = new Null();				
						if (checkFilter(position, color)) { // if no threats to king, move
							ChessBoardGUI.setPosition(position);
							increaseTurnCount();							// add one to the turn counter
							History.savePosition(position, turnCounter);	// write position to file
						} else { 	// if threats, restore position
							position = History.readPosition(turnCounter);
						}
					}
					// En Passant capture
					else if (potentialMoves.get(attemptIndex).equals("En Passant")) {
						position[attemptIndex] = position[index];
						position[index] = new Null();
						if (color.equals("White")) {
							position[attemptIndex + 8] = new Null();
						}
						else {
							position[attemptIndex - 8] = new Null();
						}
						if (checkFilter(position, color)) { // if no threats to king, move
							ChessBoardGUI.setPosition(position);
							increaseTurnCount();							// add one to the turn counter
							History.savePosition(position, turnCounter);	// write position to file
						} else { 	// if threats, restore position
							position = History.readPosition(turnCounter);
						}
					}
					// Castling
					else if (potentialMoves.get(attemptIndex).equals("Castle")) {
						if (attemptIndex == 62) {
							if (!getThreatList(color, position).contains(60) && !getThreatList(color, position).contains(61) && !getThreatList(color, position).contains(62)) {
								position[62] = position[60];
								position[61] = position[63];
								position[60] = new Null();
								position[63] = new Null();
								ChessBoardGUI.setPosition(position);
								increaseTurnCount();							// add one to the turn counter
								History.savePosition(position, turnCounter);	// write position to file
							}
						}
						else if (attemptIndex == 58) {
							if (!getThreatList(color, position).contains(60) && !getThreatList(color, position).contains(59) && !getThreatList(color, position).contains(58)) {
								position[58] = position[60];
								position[59] = position[56];
								position[56] = new Null();
								position[60] = new Null();
								ChessBoardGUI.setPosition(position);
								increaseTurnCount();							// add one to the turn counter
								History.savePosition(position, turnCounter);	// write position to file
							}
						}
						else if (attemptIndex == 6) {
							if (!getThreatList(color, position).contains(4) && !getThreatList(color, position).contains(5) && !getThreatList(color, position).contains(6)) {
								position[6] = position[4];
								position[5] = position[7];
								position[4] = new Null();
								position[7] = new Null();
								ChessBoardGUI.setPosition(position);
								increaseTurnCount();							// add one to the turn counter
								History.savePosition(position, turnCounter);	// write position to file
							}
						}
						else if (attemptIndex == 2) {
							if (!getThreatList(color, position).contains(4) && !getThreatList(color, position).contains(3) && !getThreatList(color, position).contains(2)) {
								position[2] = position[4];
								position[3] = position[0];
								position[4] = new Null();
								position[0] = new Null();
								ChessBoardGUI.setPosition(position);
								increaseTurnCount();							// add one to the turn counter
								History.savePosition(position, turnCounter);	// write position to file
							}
						}
					}
				}
			}
		}
	} // end of move method
	
	// turn counter method
	public void increaseTurnCount() {
		turnCounter++;
	}
	
	public static ArrayList getThreatList(String color, Piece[] position) {
		// set color to opponent's color
		if (color.equals("White")) {
			color = "Black";
		} else {
			color = "White";
		}
		// create arraylist to hold threats and hashmap to hold each opponent's piece's moveList
		ArrayList<Integer> threatList = new ArrayList<Integer>();
		HashMap<Integer, String> allMoves = new HashMap<>();
		for (int i = 0; i < 64; i++) {
			if (position[i].getColor().equals(color)) {
				// create list of opponent's "Move"s
				allMoves = position[i].generateMoves(position, i, color);
				for (int key:allMoves.keySet()) {
					if (allMoves.get(key).equals("Move")) {
						threatList.add(key);
					}
				}
			}
		}
//		for (int square:threatList) {			// print out threatList
//			System.out.println(square);
//		}
		return threatList;
	}
// generate list of potential moves*** needs revision
	public static ArrayList getMoveList(String color, Piece[] position) {				// not sure if/where this method is used***
		
		// create arraylist to hold moves and hashmap to hold each opponent's piece's moveList
		ArrayList<Integer> threatList = new ArrayList<Integer>();
		HashMap<Integer, String> allMoves = new HashMap<>();
		for (int i = 0; i < 64; i++) {
			if (position[i].getColor().equals(color)) {
				// create list of opponent's "Move"s
				allMoves = position[i].generateMoves(position, i, color);
				for (int key:allMoves.keySet()) {
					if (allMoves.get(key).equals("Move")) {
						threatList.add(key);
					}
				}
			}
		}
//		for (int square:threatList) {			// print out threatList
//			System.out.println(square);
//		}
		return threatList;
	}
	
	// to be restored for position object
	public static Boolean checkFilter(Piece [] pos, String color) {
		Integer kingIndex = -1;
		for (int i = 0; i <64; i++){
			if (pos[i].getType().equals("King") && pos[i].getColor().equals(color)) {
				kingIndex = i;
			}
		}
		if (getThreatList(color, pos).contains(kingIndex)) {  // how accurate is getThreatList?*
			return false;
		} else {
			return true;
		}
	}
	
	// method to check for end game states (checkmate, stalemate)
	public void isMate(){
		// checkmate & stalemate
		if (turnCounter > 2) {
	
			// Whose turn is it?
			String turnColor;
			if (turnCounter%2 == 0) {
				turnColor = "White";
			}
			else {
				turnColor = "Black";
			}

			// get list of all piece positions
			ArrayList<Integer> pieces = new ArrayList<>();
			for (int a = 0; a < 64; a++) {
				if (position[a].getColor().equals(turnColor)){
					pieces.add(a);
				}
			}
			
			Boolean hasMove = false;
//		while (!hasMove) {				// while loop breaks program... would like to implement for speed later?*
			for (int piece:pieces) {
				
				for (int b = 0; b < 64; b++) {
					if (testMove(piece, b)) {
						hasMove = true;
						break;
					}							
				}
//			}
			}
			if (!hasMove) {
				if (!checkFilter(position, turnColor)) {
					System.out.println(turnColor + " done been Checkmated");
				} else {
					System.out.println("Draw by Stalemate");
				}
			}
		}
	}
	
	// method to see if side whose turn it is has any legal moves
	public Boolean testMove(int index, int attemptIndex) {

		// establish reference position:
			Piece[] reference = new Piece[64];
			
			for (int j = 0; j < 64; j++) {
					if (position[j].getType().equals("King")) {
						reference[j] = new King(position[j].getColor());
					}
					if (position[j].getType().equals("Queen")) {
						reference[j] = new Queen(position[j].getColor());
					}
					if (position[j].getType().equals("Bishop")) {
						reference[j] = new Bishop(position[j].getColor());
					}
					if (position[j].getType().equals("Knight")) {
						reference[j] = new Knight(position[j].getColor());
					}
					if (position[j].getType().equals("Rook")) {
						reference[j] = new Rook(position[j].getColor());
					}
					if (position[j].getType().equals("Pawn")) {
						reference[j] = new Pawn(position[j].getColor());
					}
					if (position[j].getType().equals("Null")) {
						reference[j] = new  Null();
					}
				}
		
		// similar to move method, but returns boolean true if move possible
		Boolean result = false;
		// handling turns:
		
		String color = reference[index].getColor();
		String turnColor;
		if (turnCounter%2 == 0) {
			turnColor = "White";
		}
		else {
			turnColor = "Black";
		}
		
		if (turnColor.equals(color)) {
		
			HashMap<Integer, String> potentialMoves = reference[index].generateMoves(position, index, color);// might add check to return false if potentialMoves is empty*
			
			if (!potentialMoves.isEmpty()) {
				if (potentialMoves.containsKey(attemptIndex)) {
					if (potentialMoves.get(attemptIndex).equals("Move") || potentialMoves.get(attemptIndex).equals("Pawn Move")) {
						reference[attemptIndex] = reference[index];
						reference[index] = new Null();
					
						if (checkFilter(reference, color)) {	// if move possible, return true
							result = true;						
						} 							
					}
					// pawn promotion (to queen)
					else if (potentialMoves.get(attemptIndex).equals("Promotion")) {
						reference[attemptIndex] = new Queen(color);
						reference[index] = new Null();				
						if (checkFilter(reference, color)) {
							result = true;
						}						
					}
					// En Passant capture
					else if (potentialMoves.get(attemptIndex).equals("En Passant")) {
						reference[attemptIndex] = reference[index];
						reference[index] = new Null();
						if (color.equals("White")) {
							reference[attemptIndex + 8] = new Null();
						}
						else {
							reference[attemptIndex - 8] = new Null();
						}
						if (checkFilter(reference, color)) { // if no threats return true
							result = true;
						} 						
					}
					// Castling
					else if (potentialMoves.get(attemptIndex).equals("Castle")) {
						if (attemptIndex == 62) {
							if (!getThreatList(color, position).contains(60) && !getThreatList(color, position).contains(61) && !getThreatList(color, position).contains(62)) {
								result = true;	
							}
						}
						else if (attemptIndex == 58) {
							if (!getThreatList(color, position).contains(60) && !getThreatList(color, position).contains(59) && !getThreatList(color, position).contains(58)) {
								result = true;
							}
						}
						else if (attemptIndex == 6) {
							if (!getThreatList(color, position).contains(4) && !getThreatList(color, position).contains(5) && !getThreatList(color, position).contains(6)) {
								result = true;
							}
						}
						else if (attemptIndex == 2) {
							if (!getThreatList(color, position).contains(4) && !getThreatList(color, position).contains(3) && !getThreatList(color, position).contains(2)) {
								result = true;
							}
						}
					}
				}
			}
		}
		return result;
	} // end of testMove method

	// method to check for insufficient material
	public void isMaterial() {							// needs more in depth testing*
		// create two arrayLists, one to hold all black pieces on board and one for white
			ArrayList<String> whitePieces = new ArrayList<>();
			ArrayList<String> blackPieces = new ArrayList<>();
			
			Boolean whiteBishop = true;
			Boolean blackBishop = true;
			
			for (Piece p:position) {
				if (p.getColor().equals("White")) {
					whitePieces.add(p.getType());
				}
				else if (p.getColor().equals("Black")) {
					blackPieces.add(p.getType());
				}
			}
			
			// test to check for two bishops
			whitePieces.remove("Bishop");
			int whiteSize = whitePieces.size();
			whitePieces.remove("Bishop");
			if (whiteSize > whitePieces.size()) {
				whiteBishop = false;
			}
			
			blackPieces.remove("Bishop");
			int blackSize = blackPieces.size();
			blackPieces.remove("Bishop");
			if (blackSize > blackPieces.size()) {
				blackBishop = false;
			}
			
			// test if both sides have insufficient material. if yes, declare draw
			Boolean whiteInsufficient = false;
			Boolean blackInsufficient = false;
			
			if (!whitePieces.contains("Rook") && !whitePieces.contains("Queen") && !whitePieces.contains("Pawn") && whiteBishop) {
				whiteInsufficient = true;
			}
			
			if (!blackPieces.contains("Rook") && !blackPieces.contains("Queen") && !blackPieces.contains("Pawn") && blackBishop) {
				blackInsufficient = true;
			}
			
			if (whiteInsufficient && blackInsufficient){
				System.out.println("Draw by Insufficient Material");
			}
	}
	
	// method to check three fold repeat and 50 moves without progress
	public void isDraw(){
		// three fold repetition
		// takes current position and compares it to previous positions. if it appears twice in recent history, declare draw
		
		if (turnCounter >= 8) {
			Piece[] initialPosition = History.readPosition(turnCounter-8);
			Piece[] secondPosition = History.readPosition(turnCounter-4);
			Piece[] currentPosition = position;
			
			Boolean equal = true;
			Boolean equal2 = true;
			
			for (int i = 0; i < 64; i++){
				if (!currentPosition[i].getType().equals(secondPosition[i].getType()) || !currentPosition[i].getColor().equals(secondPosition[i].getColor())) {
					equal = false;
					break;
				}
			}
			if (equal) {
				for (int i = 0; i < 64; i++){
					if (!initialPosition[i].getType().equals(secondPosition[i].getType()) || !initialPosition[i].getColor().equals(secondPosition[i].getColor())) {
						equal2 = false;
						break;
					}
				}
			}
			
			if (equal && equal2){
				System.out.println("Draw by Three Fold Repetition");
			}
		}
		
		// draw by 50 moves w/o progression?
	}
	
	
	// save
	// draw by no progress in 50 moves?
	
}