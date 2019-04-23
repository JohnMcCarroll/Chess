import java.util.*;

public class King extends Piece {
	
	public King(String c) {
		color = c;
		type = "King";
	}
	
	public HashMap<Integer, String> generateMoves(Piece[] p, int index, String color) {
		// iterates through available moves and adds them to list
		
		HashMap<Integer, String> moveList = new HashMap<>();
		if (0 <= index-9 && index%8 != 0) {
			if (!p[index-9].getColor().equals(color)){
				moveList.put(index-9, "Move");
			}
		}
		if (0 <= index-8) {
			if (!p[index-8].getColor().equals(color)) {
				moveList.put(index-8, "Move");
			}
		}
		if (0 <= index-7 && (index+1)%8 != 0) {
			if (!p[index-7].getColor().equals(color)) {
				moveList.put(index-7, "Move");
			}
		}
		if (0 <= index-1 && index%8 != 0) {
			if (!p[index-1].getColor().equals(color)) {
				moveList.put(index-1, "Move");
			}
		}
		if (0 != (index+1)%8 && index+1 <= 63) {
			if (!p[index+1].getColor().equals(color)) {
				moveList.put(index+1, "Move");
			}
		}
		if (0 != index%8 && index+7 <= 63) {
			if (!p[index+7].getColor().equals(color)) {
				moveList.put(index+7, "Move");
			}
		}
		if (index+8 <= 63) {
			if (!p[index+8].getColor().equals(color)) {
				moveList.put(index+8, "Move");
			}
		}
		if (0 != (index+9)%8 && index+9 <= 63) {
			if (!p[index+9].getColor().equals(color)) {
				moveList.put(index+9, "Move");
			}
		}
		
		// castling
		// has king moved?
		if (index == 4 || index == 60) {
			// check if relevant pieces have moved
			Boolean King = true;
			Boolean WhiteKRook = true;
			Boolean WhiteQRook = true;
			Boolean BlackKRook = true;
			Boolean BlackQRook = true;
			
			for (int turn = 0; turn <= History.getHTurnCounter(); turn++) {			// need to add checking for threats***
				if (King && !History.readPosition(turn)[index].getColor().equals(color)) {
					King = false;
				}
				if (WhiteKRook && !History.readPosition(turn)[index].getColor().equals(color)) { // 
					WhiteKRook = false;
				}
				if (WhiteQRook && !History.readPosition(turn)[index].getColor().equals(color)) {
					WhiteQRook = false;
				}
				if (BlackKRook && !History.readPosition(turn)[index].getColor().equals(color)) {
					BlackKRook = false;
				}
				if (BlackQRook && !History.readPosition(turn)[index].getColor().equals(color)) {
					BlackQRook = false;
				}
			}
			
			// check if pieces are in the way, if not add move to moveList
			if (color.equals("White") && King) {
				if (WhiteKRook && p[61].getColor().equals("Null") && p[62].getColor().equals("Null")) {
//					if (!Position.getThreatList(color, p).contains(60) && !Position.getThreatList(color, p).contains(61) && !Position.getThreatList(color, p).contains(62)) { // test***
						moveList.put(62, "Castle");
	//				}
				}
				if (WhiteQRook && p[57].getColor().equals("Null") && p[58].getColor().equals("Null") && p[59].getColor().equals("Null")) {
//					if (!Position.getThreatList(color, p).contains(58) && !Position.getThreatList(color, p).contains(59) && !Position.getThreatList(color, p).contains(60)) {
						moveList.put(58, "Castle");
//					}
				}
			}
			if (color.equals("Black") && King) {
				if (BlackKRook && p[5].getColor().equals("Null") && p[6].getColor().equals("Null")) {
//					if (!Position.getThreatList(color, p).contains(4) && !Position.getThreatList(color, p).contains(5) && !Position.getThreatList(color, p).contains(6)) {
						moveList.put(6, "Castle");
//					}
				}
				if (BlackQRook && p[3].getColor().equals("Null") && p[2].getColor().equals("Null") && p[1].getColor().equals("Null")) {
//					if (!Position.getThreatList(color, p).contains(2) && !Position.getThreatList(color, p).contains(3) && !Position.getThreatList(color, p).contains(4)) {
						moveList.put(2, "Castle");
//					}
				}
			}
		}
		
		// vet moveList with checkFilter... static position method??
		
		
		return moveList;
	}
}