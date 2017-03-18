package Sokoban;

import java.awt.event.*;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class Menu extends JMenuBar{
	private RecordsTable records;
	private Board board;
	Menu(Board board ,RecordsTable records){
		this.records = records;
		this.board = board;
		
		URL loc = this.getClass().getResource("pictures/exit-icon.png");
        ImageIcon icon = new ImageIcon(loc);
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        file.add(eMenuItem);
       
        loc = this.getClass().getResource("pictures/cup.jpg");
        icon = new ImageIcon(loc);
        eMenuItem = new JMenuItem("Records", icon);
        eMenuItem.setToolTipText("My Scores");
        eMenuItem.addActionListener((ActionEvent event) -> {
        	records.makeBox(board.getSoko().getName(),board.getCurrentLevel()-1);
        	records.SetVisible();
        });
        file.add(eMenuItem);
        
        loc = this.getClass().getResource("pictures/mute.png");
        icon = new ImageIcon(loc);
        eMenuItem = new JMenuItem("Stop Music",icon);
        eMenuItem.setToolTipText("Disable sound");
        eMenuItem.addActionListener((ActionEvent event) -> {
        	board.disableSound();
        });
        file.add(eMenuItem);
        
        loc = this.getClass().getResource("pictures/sound.png");
        icon = new ImageIcon(loc);
        eMenuItem = new JMenuItem("Start Music",icon);
        eMenuItem.setToolTipText("Enable sound");
        eMenuItem.addActionListener((ActionEvent event) -> {
        	board.enableSound();
        });
        file.add(eMenuItem);
        add(file);
	}
}
