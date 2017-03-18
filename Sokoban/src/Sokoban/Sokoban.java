package Sokoban;


import javax.swing.JFrame;
import javax.swing.JLabel;

public final class Sokoban extends JFrame {

    private final int OFFSET = 50;
    private Menu menu;
    private Board board;
    private RecordsTable recordsT;
    public Sokoban() {
        InitUI();
    }

    public void InitUI() {
        board = new Board();
        add(board);
        recordsT = new RecordsTable(board);
        menu = new Menu(board,recordsT);
        board.addRecTable(recordsT);
        setJMenuBar(menu);
        
        //opening the window to get the name of the player for the game
        board.getPlayerName();
        board.setRecords();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getBoardWidth() + 2*OFFSET,board.getBoardHeight() + 2*OFFSET);
        setLocationRelativeTo(null);
        setTitle("Sokoban - David's adventure");
    }

    
    public static void main(String[] args) {
    	Sokoban sokoban = new Sokoban();
    	MainMenu start = new MainMenu(sokoban);
        start.setVisible(true);
    }
    
    public void getReady(){
    	board.levelUp();
    	board.disableSound();
    	board.enableSound();
    }
}