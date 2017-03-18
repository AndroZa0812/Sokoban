package Sokoban;


import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Player extends Actor {
	private int steps = 0;
	private String name;
    public Player(int x,int y){
    	super(x, y);

        URL loc = this.getClass().getResource("pictures/staying_down" + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        name = "Default";
    }
    public void newPosition(int x,int y){
    	this.setX(x);
    	this.setY(y);
    }
    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
        setSteps(getSteps() + 1);
    }

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}