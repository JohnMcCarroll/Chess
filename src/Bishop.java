import java.util.*;

public class Bishop extends Piece {
	
	public Bishop(String c) {
		color = c;
		type = "Bishop";
	}
	
	public HashMap<Integer, String> generateMoves(Piece[] p, int index, String color) {
		// iterates through available moves and adds them to list
		
		HashMap<Integer, String> moveList = new HashMap<>();

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