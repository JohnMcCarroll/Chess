import java.util.*;

public class Knight extends Piece {
	
	public Knight(String c) {
		color = c;
		type = "Knight";
	}
	
	public HashMap<Integer, String> generateMoves(Piece[] p, int index, String color) {
		// iterates through available moves and adds them to list
		
		HashMap<Integer, String> moveList = new HashMap<>();
		
		if (0 <= index-17 && (index-17+1)%8 != 0) {
			if (!p[index-17].getColor().equals(color)){ 
				moveList.put(index-17, "Move");
			}
		}
		if (0 <= index-15 && (index-15)%8 != 0) {
			if (!p[index-15].getColor().equals(color)) {
				moveList.put(index-15, "Move");
			}
		}
		if (0 <= index-10 && !((index)%8 == 0 || (index-1)%8 == 0)) {
			if (!p[index-10].getColor().equals(color)) {
				moveList.put(index-10, "Move");
			}
		}
		if (0 <= index-6 && !((index+2)%8 == 0 || (index+1)%8 == 0)) {
			if (!p[index-6].getColor().equals(color)) {
				moveList.put(index-6, "Move");
			}
		}
		if (63 >= index+6 && !((index)%8 == 0 || (index-1)%8 == 0)) {
			if (!p[index+6].getColor().equals(color)) {
				moveList.put(index+6, "Move");
			}
		}
		if (63 >= index+10 && !((index+2)%8 == 0 || (index+1)%8 == 0)) {
			if (!p[index+10].getColor().equals(color)) {
				moveList.put(index+10, "Move");
			}
		}
		if (63 >= index+15 && (index+15+1)%8 != 0) {
			if (!p[index+15].getColor().equals(color)) {
				moveList.put(index+15, "Move");
			}
		}
		if (63 >= index+17 && (index+17)%8 != 0) {
			if (!p[index+17].getColor().equals(color)) {
				moveList.put(index+17, "Move");
			}
		}
		
//		moveList = checkFilter(p, index, moveList);
		
		return moveList;
	}
}