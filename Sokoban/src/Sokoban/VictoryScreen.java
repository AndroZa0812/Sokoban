package Sokoban;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;

public class VictoryScreen extends JFrame {

	private JPanel contentPane;
	private RecordsTable records = new RecordsTable(this);
	/**
	 * Create the frame.
	 */
	public VictoryScreen(java.awt.Container cont) {
		java.awt.Container c = cont;

		while (!(c instanceof javax.swing.JFrame) && (c != null)) {
			c = c.getParent();
		}
		// Referencing to him
		JFrame owner = (javax.swing.JFrame) c;
		
		owner.setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTextArea txtrCongradulationsYouHave = new JTextArea();
		txtrCongradulationsYouHave.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
		txtrCongradulationsYouHave.setBackground(SystemColor.info);
		txtrCongradulationsYouHave.setEditable(false);
		txtrCongradulationsYouHave.setText("                                    VICTORY !!!!\r\n\r\nCongratulations, you have saved David the Knight.\r\nThanks for freeing him from the dungeon, hope to see you soon.");
		GridBagConstraints gbc_txtrCongradulationsYouHave = new GridBagConstraints();
		gbc_txtrCongradulationsYouHave.gridwidth = 11;
		gbc_txtrCongradulationsYouHave.gridheight = 3;
		gbc_txtrCongradulationsYouHave.insets = new Insets(0, 0, 5, 0);
		gbc_txtrCongradulationsYouHave.fill = GridBagConstraints.BOTH;
		gbc_txtrCongradulationsYouHave.gridx = 0;
		gbc_txtrCongradulationsYouHave.gridy = 0;
		contentPane.add(txtrCongradulationsYouHave, gbc_txtrCongradulationsYouHave);
		
		JButton btnNewButton_1 = new JButton("Finish the game");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JButton btnNewButton = new JButton("See All Scores");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				records.makeBox();
				records.SetVisible();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 11;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridheight = 2;
		gbc_btnNewButton_1.gridwidth = 11;
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 7;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
	}

}
