package Sokoban;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Spring;

public class Levels_records {
	private ArrayList<ArrayList<String>> records;
	private String filePath = new File("").getAbsolutePath();
	private String fileName = "\\src\\sokoban\\Records.txt";

	Levels_records() {
		records = new ArrayList<ArrayList<String>>();
		loadRecords();
	}

	public void addRecord(String name, int steps, int level) {
		String sSteps = String.valueOf(steps);
		String sLevel = String.valueOf(level);
		int index = findIndex(level, steps);
		ArrayList<String> newRecord = new ArrayList<String>();

		newRecord.add(sLevel);
		newRecord.add(name);
		newRecord.add(sSteps);

		records.add(index, newRecord);
	}

	public void saveRecords() {
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(filePath + fileName));
			output.write("");
			output.close();

			output = new BufferedWriter(new FileWriter(filePath + fileName));
			for (ArrayList<String> row : records) {
				for (int i = 0; i < row.size(); i++) {
					if (i > 0) {
						output.write(",");
					}
					output.write(row.get(i));

					if (i == row.size() - 1) {
						output.write("\n");
					}
				}
			}

			output.close();
		} catch (Exception e) {
			System.out.println("There was a problem with loading the file");
		}
	}

	public void loadRecords() {
		setRecords(new ArrayList<ArrayList<String>>());
		ArrayList<String> row;
		String line;

		try {
			BufferedReader input = new BufferedReader(new FileReader(filePath + fileName));
			if (!input.ready()) {
				throw new IOException();
			} else {
				while ((line = input.readLine()) != null) {
					row = new ArrayList<String>(Arrays.asList(line.split(",")));
					records.add(row);
				}
			}
			input.close();
		} catch (IOException e) {
			System.out.println("There was a probelm in finding the file of records...");
		}

	}

	@Override
	public String toString() {
		String stringedRecs = new String();
		for (ArrayList<String> row : records) {

			stringedRecs = String.join("\n", stringedRecs,
					"Level: " + row.get(0) + ", Player: " + row.get(1) + " ,Score: " + row.get(2));
			/*
			 * else{ throw new IllegalStateException(
			 * "There was a problem with loading the records, please check the source for corruption."
			 * ) ; }
			 */
		}
		return (stringedRecs);
	}

	public int findIndex(int level, int steps) {
		int index = -1;

		if (records.size() == 0) {
			index = 0;
		} else {
			// looking for the right place where the new record should be
			for (ArrayList<String> row : records) {
				if (Integer.parseInt(row.get(0)) == level) {
					if (Integer.parseInt(row.get(2)) > steps) {
						index = records.indexOf(row);
						break;
					}
				}
				if (Integer.parseInt(row.get(0)) > level) {
					index = records.indexOf(row);
					break;
				}
			}
			if (index == -1) {
				index = records.size();
			}
		}
		return (index);
	}

	public boolean checkName(String newName) {
		for (ArrayList<String> row : records) {
			if (row.get(1).compareTo(newName) == 0) {
				return true;
			}
		}
		return false;
	}

	public Object[][] getRecordsTable() {
		String[][] table = new String[records.size()][3];
		int index = 0;
		for (ArrayList<String> row : records) {
			table[index][0] = row.get(0);
			table[index][1] = row.get(1);
			table[index][2] = row.get(2);
			index++;
		}
		return table;
	}

	public Object[][] getRecordsTable(String name, int levelsDone) {
		String[][] table;
		if (levelsDone == 0) {
			table = new String[0][3];
		} else {
			table = new String[levelsDone][3];
			int index = 0;
			for (ArrayList<String> row : records) {
				if (row.get(1) == name) {
					table[index][0] = row.get(0);
					table[index][1] = row.get(1);
					table[index][2] = row.get(2);
					index++;
				}
			}
		}
		return table;
	}

	public void setRecords(ArrayList<ArrayList<String>> records) {
		this.records = records;
	}
}
