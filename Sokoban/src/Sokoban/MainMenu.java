package Sokoban;

import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JMenuBar;

public class MainMenu extends JFrame {
	private JTextField txtMainMenu;
	private Sokoban game;
	private HowTo instractions = new HowTo(this);
	private final int OFFSET = 50;
	private RecordsTable records = new RecordsTable(this);
	/**
	 * Create the panel.
	 */
	public MainMenu(Sokoban game) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.game = game;
		 setSize(461,352);
	     setLocationRelativeTo(null);
	     setTitle("Sokoban - David's adventure");
	     JLabel contentPane = new JLabel();
	     URL loc = this.getClass().getResource("pictures/dungeon_background" + ".gif");
	     ImageIcon iia = new ImageIcon(loc);
	     contentPane.setIcon(iia);
	     contentPane.setLayout( new BorderLayout() );
	     setContentPane( contentPane );
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		txtMainMenu = new JTextField();
		txtMainMenu.setFont(new Font("High Tower Text", Font.PLAIN, 18));
		txtMainMenu.setBackground(SystemColor.info);
		txtMainMenu.setEditable(false);
		txtMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		txtMainMenu.setText("Main Menu");
		GridBagConstraints gbc_txtMainMenu = new GridBagConstraints();
		gbc_txtMainMenu.gridheight = 2;
		gbc_txtMainMenu.gridwidth = 6;
		gbc_txtMainMenu.fill = GridBagConstraints.BOTH;
		gbc_txtMainMenu.insets = new Insets(0, 0, 5, 5);
		gbc_txtMainMenu.gridx = 1;
		gbc_txtMainMenu.gridy = 0;
		getContentPane().add(txtMainMenu, gbc_txtMainMenu);
		txtMainMenu.setColumns(10);
		
		JButton btnNewButton = new JButton("Start a new game");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game.getReady();
				game.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 3;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_2 = new JButton("About the game and How to play");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instractions.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridwidth = 2;
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 5;
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Records");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				records.makeBox();
				records.SetVisible();
			}
		});
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 7;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		JTextPane txtpnMadeByNathan = new JTextPane();
		txtpnMadeByNathan.setBackground(SystemColor.textHighlight);
		txtpnMadeByNathan.setFont(new Font("Algerian", Font.PLAIN, 14));
		txtpnMadeByNathan.setEditable(false);
		txtpnMadeByNathan.setText("\t\tMade By Nathan Tregub.\n\tLevel Design: David Skinner.\n\tCollection: original Sasquatch collection.");
		GridBagConstraints gbc_txtpnMadeByNathan = new GridBagConstraints();
		gbc_txtpnMadeByNathan.gridwidth = 2;
		gbc_txtpnMadeByNathan.anchor = GridBagConstraints.SOUTH;
		gbc_txtpnMadeByNathan.insets = new Insets(0, 0, 0, 5);
		gbc_txtpnMadeByNathan.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnMadeByNathan.gridx = 3;
		gbc_txtpnMadeByNathan.gridy = 9;
		getContentPane().add(txtpnMadeByNathan, gbc_txtpnMadeByNathan);
	}

}
