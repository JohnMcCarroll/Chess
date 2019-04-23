import java.util.*;

public class Pawn extends Piece {
	
	// en passant flag? ***
	
	public Pawn(String c) {
		color = c;
		type = "Pawn";
	}
	
	public ArrayList generateThreats(Piece[] p, int row, int col){
		return null;
	}
	
	public HashMap<Integer, String> generateMoves(Piece[] p, int index, String color) {
		// iterates through available moves and adds them to list
		
		HashMap<Integer, String> moveList = new HashMap<>();
		
		if (color.equals("White")) {
			
			Boolean f = false;
			// move forward one
			if (8 <= index-8) {
				if (p[index-8].getColor().equals("Null")) {
					moveList.put(index-8, "Pawn Move"); // "Pawn Move" b/c cannot capture moving forward. helps generation of threatList
					f = true;
				}
			} 
			// move forward two, only on first move
			if (48 <= index && index <= 55) {
				if (f && p[index-16].getColor().equals("Null")) {
					moveList.put(index-16, "Pawn Move");
				}
			}
			// capture top left. no en passant
			if (8 <= index-9 && ((index-9+1) % 8) != 0) {	
				if (!p[index-9].getColor().equals(color) && !p[index-9].getColor().equals("Null")) {
					moveList.put(index-9, "Move");
				}
			}
			// capture top right
			if (8 <= index-7 && ((index-7) % 8) != 0) {	
				if (!p[index-7].getColor().equals(color) && !p[index-7].getColor().equals("Null")) {
					moveList.put(index-7, "Move");
				}	
			}
			// center promotion
			if (index-8 >= 0 && index-8 <= 7) {
				if (p[index-8].getType().equals("Null")) {
					moveList.put(index-8, "Promotion");
				}
			}
			// left capture promotion
			if (index-9 >= 0 && index-9 <= 7) {
				if (!p[index-9].getColor().equals(color)) {  
					moveList.put(index-9, "Promotion");
				}
			}
			// right capture promotion
			if (index-7 >= 0 && index-7 <= 7) {
				if (!p[index-7].getColor().equals(color)) {
					moveList.put(index-7, "Promotion");
				}
			}
			// en passant (needs border detection)
			if (index >= 24 && index <= 31) {
				if ((index-1) != 23 && p[index-1].getType().equals("Pawn") && !p[index-1].getColor().equals(color)) {
					if (History.readPosition(History.getHTurnCounter()-1)[index-1].getType().equals("Null") && History.readPosition(History.getHTurnCounter()-1)[index-17].getColor().equals("Black")) {
						moveList.put(index-9, "En Passant");
					}
				}
				if ((index+1) != 32 && p[index+1].getType().equals("Pawn") && !p[index+1].getColor().equals(color)) {
					 if (History.readPosition(History.getHTurnCounter()-1)[index+1].getType().equals("Null") && History.readPosition(History.getHTurnCounter()-1)[index-15].getColor().equals("Black")) {
						moveList.put(index-7, "En Passant");
					}
				}
			}
		}
		
		if (color.equals("Black")) {
			
			Boolean f = false;
			// move forward one
			if (55 >= index+8) {
				if (p[index+8].getColor().equals("Null")) {
					moveList.put(index+8, "Pawn Move");
					f = true;
				}
			} 
			// move forward two, only on first move
			if (8 <= index && index <= 15) {
				if (f && p[index+16].getColor().equals("Null")) {
					moveList.put(index+16, "Pawn Move");
				}
			}
			// capture top left
			if (56 >= index+9 && ((index+9) % 8) != 0) {	
				if (!p[index+9].getColor().equals(color) && !p[index+9].getColor().equals("Null")) {
					moveList.put(index+9, "Move");
				}
			}
			// capture top right
			if (56 >= index+7 && ((index+7+1) % 8) != 0) {				
				if (!p[index+7].getColor().equals(color) && !p[index+7].getColor().equals("Null")) {
					moveList.put(index+7, "Move");
				}
			}
			// center promotion
			if (index+8 >= 56 && index+8 <= 63) {
				if (p[index+8].getType().equals("Null")) {
					moveList.put(index+8, "Promotion");
				}
			}
			// left capture promotion
			if (index+9 >= 56 && index+9 <= 63) {
				if (!p[index+9].getColor().equals(color)) {  
					moveList.put(index+9, "Promotion");
				}
			}
			// right capture promotion
			if (index+7 >= 56 && index+7 <= 63) {
				if (!p[index+7].getColor().equals(color)) {
					moveList.put(index+7, "Promotion");
				}
			}
			// en passant
			if (index >= 32 && index <= 39) {
				if ((index-1) != 31 && p[index-1].getType().equals("Pawn") && !p[index-1].getColor().equals(color)) {
					if (History.readPosition(History.getHTurnCounter()-1)[index-1].getType().equals("Null") && History.readPosition(History.getHTurnCounter()-1)[index+15].getColor().equals("White")) {
						moveList.put(index+7, "En Passant");
					}
				}
				if ((index+1) != 40 && p[index+1].getType().equals("Pawn") && !p[index+1].getColor().equals(color)) {
					 if (History.readPosition(History.getHTurnCounter()-1)[index+1].getType().equals("Null") && History.readPosition(History.getHTurnCounter()-1)[index+17].getColor().equals("White")) {
						moveList.put(index+9, "En Passant");
					}
				}
			}
		}
		return moveList;
	}
}