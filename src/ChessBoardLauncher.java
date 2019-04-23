public class ChessBoardLauncher {
	
	public static void main(String[] args){
		ChessBoardGUI gui = new ChessBoardGUI(new Position());
		gui.buildGUI();
		History.clearFile();
		History.savePosition(new Position().getPosition(), 0);
	}
	
}