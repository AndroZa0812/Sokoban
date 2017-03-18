package Sokoban;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

public class MusicPlayer {
	private Clip clip;

	public void play(int level) {
		stop();
		try {
			URL yourFile = this.getClass().getResource("music/level_" + level + ".wav");
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;

			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
			LineListener listener = new LineListener() {
				public void update(LineEvent event) {
					if (event.getType() != Type.STOP) {
						return;
					}

					try {
						playAgain();
					} catch (Exception e) {
						// ignore this
					}
				}
			};
			clip.addLineListener(listener);
		} catch (Exception e) {
			System.out.println("The audio wasn't found.");
		}
	}

	public void playAgain() {
		clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}

	public void play(String filename) {
		try {
			URL yourFile = this.getClass().getResource("music/" + filename + ".wav");
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;

			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		} catch (Exception e) {
			System.out.println("The audio wast found.");
		}
	}

	public void stop() {
		try {
			clip.stop();
			clip.close();
		} catch (Exception e) {
			System.out.println("There was a problem in stoping the music,or it is already stopped.");
		}
	}
}