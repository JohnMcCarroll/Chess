import java.util.*;

public abstract class Piece {
	public String color;
	public String type;
	
	public String getColor() {
		return color;
	}
	
	public String getType() {
		return type;
	}
	
	public void setColor(String c) {
		color = c;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public ArrayList generateThreats(Piece[] p, int row, int col){
		return null;
	}
	
	public HashMap<Integer, String> generateMoves(Piece[] p, int index, String color) {
		HashMap<Integer, String> specList = new HashMap<>();
		return specList;
	}
}