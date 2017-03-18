package Sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;

public class Board extends JPanel {

	private final int OFFSET = 50;
	private final int SPACE = 32;
	private final int LEFT_COLLISION = 1;
	private final int RIGHT_COLLISION = 2;
	private final int TOP_COLLISION = 3;
	private final int BOTTOM_COLLISION = 4;
	private final int LEVELS_NUM = 11;

	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Baggage> baggs = new ArrayList<Baggage>();
	private ArrayList<Area> areas = new ArrayList<Area>();
	private Player soko;
	private int w = 0;
	private int h = 0;
	private boolean completed = false;
	private int current_level = 0;
	private BufferedReader ReadFile;
	private String level;
	private String filePath = new File("").getAbsolutePath();
	private Levels_records records;
	private MusicPlayer player = new MusicPlayer();
	private boolean firstRun = true;
	private boolean silenceMode = false;
	private VictoryScreen victoryScreen;
	private RecordsTable recordsT;
	public Board() {
		initLevel();
		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld();
		records = new Levels_records();
	}

	public void initLevel() {
		String line;
		int tempLevel = current_level;
		if(tempLevel == 0){
			tempLevel = 1;
		}
		try { 
			FileReader fp = new FileReader(filePath + "\\src\\sokoban\\levels\\level_" + tempLevel + ".txt");
			ReadFile = new BufferedReader(fp);
		} catch (FileNotFoundException q) {
			System.out.println("File was not found\n");
		}

		try {
			while ((line = ReadFile.readLine()) != null) {
				if (level == null) {
					level = line + "\n";
				} else {
					level += line + "\n";
				}
			}
		} catch (IOException e) {
			System.out.println("Error While loading the level\n");
		}
	}

	public int getBoardWidth() {
		return this.w;
	}

	public int getBoardHeight() {
		return this.h;
	}

	public final void initWorld() {
		int x = OFFSET;
		int y = OFFSET;

		Wall wall;
		Baggage b;
		Area a;

		for (int i = 0; i < level.length(); i++) {

			char item = level.charAt(i);

			if (item == '\n') {
				y += SPACE;
				if (this.w < x) {
					this.w = x;
				}

				x = OFFSET;
			} else if (item == '#') {
				wall = new Wall(x, y);
				walls.add(wall);
				x += SPACE;
			} else if (item == '$') {
				b = new Baggage(x, y);
				baggs.add(b);
				x += SPACE;
			} else if (item == '.') {
				a = new Area(x, y);
				areas.add(a);
				x += SPACE;
			} else if (item == '@') {
				if (getSoko() == null) {
					setSoko(new Player(x, y));
				} else {
					getSoko().newPosition(x, y);
				}

				x += SPACE;
			} else if (item == ' ') {
				x += SPACE;
			}

			h = y;
		}
		// sound section
		if (completed  && !firstRun && (current_level >= 9) && !silenceMode) {
			player.stop();
			player.play(current_level);
		}
		if(firstRun){
			firstRun = false;
			enableSound();
		}
	}

	public void buildWorld(Graphics g) {
		// g.setColor(new Color(250, 240, 170));
		g.setColor(new Color(70, 50, 50));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.white);
		g.drawString("Level:" + current_level, 20, 40);
		g.drawString("Number of steps: " + getSoko().getSteps(), 20, 25);
		ArrayList<Actor> world = new ArrayList<Actor>();
		world.addAll(walls);
		world.addAll(areas);
		world.addAll(baggs);
		world.add(getSoko());
		if (completed) {
			nextLevel();
		}
		for (int i = 0; i < world.size(); i++) {
			Actor item = world.get(i);

			if ((item instanceof Player)) {
				g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
			} else {
				g.drawImage(item.getImage(), item.x(), item.y(), this);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		buildWorld(g);
	}

	class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			URL loc;
			ImageIcon iia;
			Image image;
			if (completed) {
				return;
			}

			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				if (checkWallCollision(getSoko(), LEFT_COLLISION)) {
					return;
				}

				if (checkBagCollision(LEFT_COLLISION)) {
					return;
				}

				loc = this.getClass().getResource("pictures/moving_left" + ".gif");
				iia = new ImageIcon(loc);
				image = iia.getImage();
				getSoko().setImage(image);
				getSoko().move(-SPACE, 0);

				break;
			case KeyEvent.VK_RIGHT:
				if (checkWallCollision(getSoko(), RIGHT_COLLISION)) {
					return;
				}

				if (checkBagCollision(RIGHT_COLLISION)) {
					return;
				}

				loc = this.getClass().getResource("pictures/moving_right" + ".gif");
				iia = new ImageIcon(loc);
				image = iia.getImage();
				getSoko().setImage(image);
				getSoko().move(SPACE, 0);

				break;
			case KeyEvent.VK_UP:
				if (checkWallCollision(getSoko(), TOP_COLLISION)) {
					return;
				}

				if (checkBagCollision(TOP_COLLISION)) {
					return;
				}

				loc = this.getClass().getResource("pictures/moving_up" + ".gif");
				iia = new ImageIcon(loc);
				image = iia.getImage();
				getSoko().setImage(image);
				getSoko().move(0, -SPACE);

				break;
			case KeyEvent.VK_DOWN:
				if (checkWallCollision(getSoko(), BOTTOM_COLLISION)) {
					return;
				}

				if (checkBagCollision(BOTTOM_COLLISION)) {
					return;
				}

				loc = this.getClass().getResource("pictures/moving_down" + ".gif");
				iia = new ImageIcon(loc);
				image = iia.getImage();
				getSoko().setImage(image);
				getSoko().move(0, SPACE);
				break;

			case KeyEvent.VK_R:
				restartLevel();
				break;
			}
			repaint();
		}
	}

	private boolean checkWallCollision(Actor actor, int type) {

		if (type == LEFT_COLLISION) {

			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isLeftCollision(wall)) {
					return true;
				}
			}
			return false;

		} else if (type == RIGHT_COLLISION) {

			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isRightCollision(wall)) {
					return true;
				}
			}
			return false;

		} else if (type == TOP_COLLISION) {

			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isTopCollision(wall)) {
					return true;
				}
			}
			return false;

		} else if (type == BOTTOM_COLLISION) {

			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isBottomCollision(wall)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	private boolean checkBagCollision(int type) {

		if (type == LEFT_COLLISION) {

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);
				if (getSoko().isLeftCollision(bag)) {

					for (int j = 0; j < baggs.size(); j++) {
						Baggage item = baggs.get(j);
						if (!bag.equals(item)) {
							if (bag.isLeftCollision(item)) {
								return true;
							}
						}
						if (checkWallCollision(bag, LEFT_COLLISION)) {
							return true;
						}
					}
					bag.move(-SPACE, 0);
					isCompleted();
				}
			}
			return false;

		} else if (type == RIGHT_COLLISION) {

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);
				if (getSoko().isRightCollision(bag)) {
					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);
						if (!bag.equals(item)) {
							if (bag.isRightCollision(item)) {
								return true;
							}
						}
						if (checkWallCollision(bag, RIGHT_COLLISION)) {
							return true;
						}
					}

					bag.move(SPACE, 0);
					isCompleted();
				}
			}
			return false;

		} else if (type == TOP_COLLISION) {

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);
				if (getSoko().isTopCollision(bag)) {
					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);
						if (!bag.equals(item)) {
							if (bag.isTopCollision(item)) {
								return true;
							}
						}
						if (checkWallCollision(bag, TOP_COLLISION)) {
							return true;
						}
					}

					bag.move(0, -SPACE);
					isCompleted();
				}
			}

			return false;

		} else if (type == BOTTOM_COLLISION) {

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);
				if (getSoko().isBottomCollision(bag)) {
					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);
						if (!bag.equals(item)) {
							if (bag.isBottomCollision(item)) {
								return true;
							}
						}
						if (checkWallCollision(bag, BOTTOM_COLLISION)) {
							return true;
						}
					}
					bag.move(0, SPACE);
					isCompleted();
				}
			}
		}

		return false;
	}

	public void isCompleted() {

		int num = baggs.size();
		int compl = 0;

		for (int i = 0; i < num; i++) {
			Baggage bag = baggs.get(i);
			for (int j = 0; j < num; j++) {
				Area area = areas.get(j);
				if (bag.x() == area.x() && bag.y() == area.y()) {
					compl += 1;
				}
			}
		}

		if (compl == num) {
			completed = true;
		}
	}

	public void restartLevel() {

		areas.clear();
		baggs.clear();
		walls.clear();
		getSoko().setSteps(0);
		initWorld();
	}

	public void nextLevel() {

		// saving the record
		getRecords().addRecord(getSoko().getName(), getSoko().getSteps(), current_level);
		
		//updating menu
		
		recordsT.updateRecordsTable(getRecords());
				
		// getting ready for the next level
		areas.clear();
		baggs.clear();
		walls.clear();
		level = null;
		current_level++;
		if (current_level > LEVELS_NUM) {
			getRecords().saveRecords();
			showVictoryDialog();
		}
		getSoko().setSteps(0);
		System.out.println(records.toString());
		initLevel();
		initWorld();
		completed = false;
		updateWindowSize();
		repaint();
	}

	public void getPlayerName() {
		NameBox box = new NameBox(this,records);
		box.makeBox();
		getSoko().setName(box.getName());
	}

	public Levels_records getRecords() {
		return records;
	}

	public void setRecords() {
		this.records = new Levels_records();
	}

	private void updateWindowSize() {
		java.awt.Container c = this.getParent();
		while (!(c instanceof javax.swing.JFrame) && (c != null)) {
			c = c.getParent();
		}
		// Referencing to him
		JFrame owner = (javax.swing.JFrame) c;

		if (c != null) {
			owner.setSize(this.getBoardWidth() + OFFSET, this.getBoardHeight() + 2 * OFFSET);
		} else {
			System.out.println("Parent Wasnt found");
		}

	}

	public void disableSound() {
		player.stop();
		silenceMode = true;
	}

	public void enableSound() {
		silenceMode = false;
		if (current_level > 1 && current_level < 9) {
			player.play(1);
		} else {
			player.play(current_level);
		}
	}

	public void playAudio(String str) {
		player.play(str);
	}
	
	public void levelUp(){
		current_level++;
	}
	
	public void showVictoryDialog(){
		victoryScreen = new VictoryScreen(this);
		player.stop();
		playAudio("victory");
		victoryScreen.setVisible(true);
	}

	public void addRecTable(RecordsTable recordsT) {
		this.recordsT = recordsT;
		
		//updating menu
		this.recordsT.updateRecordsTable(records);
	}

	public Player getSoko() {
		return soko;
	}

	public void setSoko(Player soko) {
		this.soko = soko;
	}

	public int getCurrentLevel() {
		return current_level;
	}

	public void setCurrentLevel(int current_level) {
		this.current_level = current_level;
	}
}