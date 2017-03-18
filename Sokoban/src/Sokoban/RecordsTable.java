package Sokoban;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RecordsTable {
	private JDialog dialogBox;
	protected Levels_records records;
	private java.awt.Container cont;
	WindowListener closeWindow = new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			e.getWindow().dispose();
			hide();
		}
	};

	RecordsTable(java.awt.Container cont) {
		this.cont = cont;
		this.records = new Levels_records();
	}
	
	
	public void makeBox() {
		// getting to the JFrame
		java.awt.Container c = cont;

		while (!(c instanceof javax.swing.JFrame) && (c != null)) {
			c = c.getParent();
		}
		// Referencing to him
		JFrame owner = (javax.swing.JFrame) c;

		// Making new Modal dialog box
		if (c != null) {
			dialogBox = new JDialog(owner, "Records", Dialog.ModalityType.DOCUMENT_MODAL);
			dialogBox.setSize(470, 344);
			dialogBox.setLocationRelativeTo(c);
			dialogBox.addWindowListener(closeWindow);

			JPanel tsp = new JPanel();
			GridBagConstraints cs = new GridBagConstraints();

			cs.fill = GridBagConstraints.VERTICAL;

			JLabel txName = new JLabel("Records Table");
			cs.gridx = 0;
			cs.gridy = 0;
			cs.gridwidth = 1;
			tsp.add(txName, cs);
			
			String[] colNames = new String[3];
			colNames[0] = "Level";
			colNames[1] = "Name";
			colNames[2] = "Steps";
			
			JTable table = new JTable(records.getRecordsTable(), colNames) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			tsp.add(new JScrollPane(table));

			dialogBox.add(tsp, BorderLayout.CENTER);
			dialogBox.repaint();
		} else {
			throw new IllegalStateException("There was a problem finding the JFrame parent");
		}
	}

	public void makeBox(String str, int levelsDone) {
		// getting to the JFrame
		java.awt.Container c = cont;

		while (!(c instanceof javax.swing.JFrame) && (c != null)) {
			c = c.getParent();
		}
		// Referencing to him
		JFrame owner = (javax.swing.JFrame) c;

		// Making new Modal dialog box
		if (c != null) {
			dialogBox = new JDialog(owner, "Records", Dialog.ModalityType.DOCUMENT_MODAL);
			dialogBox.setBounds(400, 200, 470, 344);
			dialogBox.addWindowListener(closeWindow);

			JPanel tsp = new JPanel();
			GridBagConstraints cs = new GridBagConstraints();

			cs.fill = GridBagConstraints.VERTICAL;

			JLabel txName = new JLabel("Records Table");
			cs.gridx = 0;
			cs.gridy = 0;
			cs.gridwidth = 1;
			tsp.add(txName, cs);
			
			String[] colNames = new String[3];
			colNames[0] = "Level";
			colNames[1] = "Name";
			colNames[2] = "Steps";
			
			JTable table = new JTable(records.getRecordsTable(str,levelsDone), colNames) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			tsp.add(new JScrollPane(table));

			dialogBox.add(tsp, BorderLayout.CENTER);
			dialogBox.repaint();
		} else {
			throw new IllegalStateException("There was a problem finding the JFrame parent");
		}
	}

	
	public void SetVisible() {
		dialogBox.setVisible(true);
	}

	public void hide() {
		dialogBox.removeAll();
		dialogBox.setVisible(false);
	}

	public void updateRecordsTable(Levels_records records) {
		this.records = records;
	}
}
