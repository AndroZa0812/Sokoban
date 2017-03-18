package Sokoban;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class HowTo extends JDialog {
	
	/**
	 * Create the dialog.
	 */
	public HowTo(JFrame owner) {
		
		super(owner,"Instructions",ModalityType.DOCUMENT_MODAL);

		setSize( 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new MigLayout("", "[][][][][][][grow]", "[][][][][][][grow]"));
		
		JTextArea txtrHowToPlay = new JTextArea();
		txtrHowToPlay.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
		txtrHowToPlay.setBackground(SystemColor.info);
		txtrHowToPlay.setEditable(false);
		txtrHowToPlay.setText("The goal of the game :\r\nThe goal of the game is to place all the boxes on the \r\nbuttons using minimal amount of steps. \r\n\r\nWho am I ?\r\nYou play as the knight david, he can only push \r\nthe boxes but not pull them towrds him.\r\n\r\nHow do I play ?\r\nby pressing the arrow keys to move and the R key to reset\r\n the level if you got stuck.");
		getContentPane().add(txtrHowToPlay, "cell 0 0 7 6,grow");
		
		JButton btnNewButton = new JButton("Okey !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().add(btnNewButton, "cell 1 6 6 1,alignx center,aligny center");
	}
}
