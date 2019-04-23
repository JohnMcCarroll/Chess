import java.util.*;

public class Queen extends Piece {
	
	public Queen(String c) {
		color = c;
		type = "Queen";
	}
	
	public HashMap<Integer, String> generateMoves(Piece[] p, int index, String color) {
		// iterates through available moves and adds them to list
		HashMap<Integer, String> moveList = new HashMap<>();
		
		//  moving up through column
		for (int i = 1; i < 8; i++) {
			if (index-8*i >= 0) {
				if (!p[index-8*i].getColor().equals(color)) {
					moveList.put(index-8*i, "Move");
				}
				if (!p[index-8*i].getColor().equals("Null")) {
					i = 8;
				}
			}
		}
		// moving down through column
		for (int i = 1; i < 8; i++) {
			if (index+8*i <= 63) {
				if (!p[index+8*i].getColor().equals(color)) {
					moveList.put(index+8*i, "Move");
				}
				if (!p[index+8*i].getColor().equals("Null")) {
					i = 8;
				}
			}
		}
		// moving down through row (left)
		for (int i = 1; i < 8; i++) {
			if ((index+1-i)%8 != 0) {
				if (!p[index-i].getColor().equals(color)) {
					moveList.put(index-i, "Move");
				}
				if (!p[index-i].getColor().equals("Null")) {
					i = 8;
				}
			}
			if ((index+1-i)%8 == 0) {
				i = 8;
			}
		}
		// moving up through row (right)
		for (int i = 1; i < 8; i++) {
			if ((index+i)%8 != 0) {
				if (!p[index+i].getColor().equals(color)) {
					moveList.put(index+i, "Move");
				}
				if (!p[index+i].getColor().equals("Null")) {
					i = 8;
				}
			}
			if ((index+i)%8 == 0) {
				i = 8;
			}
		}
		//  moving upper left
		for (int i = 1; i < 8; i++) {
			if (index-9*i >= 0 && (index-9*i+1)%8 != 0) {
				if (!p[index-9*i].getColor().equals(color)) {
					moveList.put(index-9*i, "Move");
				}
				if (!p[index-9*i].getColor().equals("Null")) {
					i = 8;
				}
			}
			if ((index-9*i+1)%8 == 0) {
				i = 8;
			}
		}
		// moving upper right
		for (int i = 1; i < 8; i++) {
			if (index-7*i >= 0 && (index-7*i)%8 != 0) {
				if (!p[index-7*i].getColor().equals(color)) {
					moveList.put(index-7*i, "Move");
				}
				if (!p[index-7*i].getColor().equals("Null")) {
					i = 8;
				}
			}
			if ((index-7*i)%8 == 0) {
				i = 8;
			}
		}
		// moving lower right
		for (int i = 1; i < 8; i++) {
			if (index+9*i <= 63 && (index+9*i)%8 != 0) {
				if (!p[index+9*i].getColor().equals(color)) {
					moveList.put(index+9*i, "Move");
				}
				if (!p[index+9*i].getColor().equals("Null")) {
					i = 8;
				}
			}
			if ((index+9*i)%8 == 0) {
				i = 8;
			}
		}
		// moving lower left
		for (int i = 1; i < 8; i++) {
			if (index+7*i <= 63 && (index+7*i+1)%8 != 0) {
				if (!p[index+7*i].getColor().equals(color)) {
					moveList.put(index+7*i, "Move");
				}
				if (!p[index+7*i].getColor().equals("Null")) {
					i = 8;
				}
			}
			if ((index+7*i+1)%8 == 0) {
				i = 8;
			}
		}
		
//		moveList = checkFilter(p, index, moveList);
		
		return moveList;
	}
}