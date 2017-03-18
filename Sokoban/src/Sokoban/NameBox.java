package Sokoban;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NameBox {
	protected JDialog dialogBox;
	private String name = null;
	protected Board gameBoard;
	private Levels_records records;
	private boolean alerted = false;
	protected static WindowListener closeWindow = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			e.getWindow().dispose();
		}
	};

	NameBox(Board gameBoard, Levels_records records) {
		this.gameBoard = gameBoard;
		this.records = records;
	}

	public void makeBox() {

		// getting to the JFrame parent
		java.awt.Container c = gameBoard.getParent();

		while (!(c instanceof javax.swing.JFrame) && (c != null)) {
			c = c.getParent();
		}
		// Referencing to him
		JFrame owner = (javax.swing.JFrame) c;

		// Making new Modal dialog box
		if (c != null) {
			dialogBox = new JDialog(owner, "", Dialog.ModalityType.DOCUMENT_MODAL);
			dialogBox.setSize(400, 200);
			dialogBox.setLocationRelativeTo(null);
			/* dialogBox.addWindowListener(closeWindow); */
			dialogBox.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

			JPanel tsp = new JPanel();
			GridBagConstraints cs = new GridBagConstraints();

			cs.fill = GridBagConstraints.HORIZONTAL;

			JLabel txName = new JLabel("Enter your player name: ");
			cs.gridx = 0;
			cs.gridy = 0;
			cs.gridwidth = 1;
			tsp.add(txName, cs);

			JTextField tfName = new JTextField(20);
			cs.gridx = 1;
			cs.gridy = 0;
			cs.gridwidth = 2;
			tsp.add(tfName, cs);

			JLabel alert = new JLabel("The userName is already taken, please enter another one.");
			cs.gridx = 5;
			cs.gridy = 0;
			cs.gridwidth = 1;
			alert.setVisible(false);
			tsp.add(alert, cs);
			JButton btn = new JButton("Submit");
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (!(tfName.getText().isEmpty())) {
						if (!records.checkName(tfName.getText())) {
							System.out.println(tfName.getText());
							setName(tfName.getText());
							dialogBox.setVisible(false);
						} else {
							if (!alerted) {
								tsp.getComponent(2).setVisible(true);
								alerted = true;
							}
						}
					}

				}
			});
			dialogBox.add(tsp, BorderLayout.CENTER);
			dialogBox.add(btn, BorderLayout.PAGE_END);
			dialogBox.setVisible(true);
		} else {
			throw new IllegalStateException("There was a problem finding the JFrame parent");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
