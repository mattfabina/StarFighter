import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Star implements Drawable2D {

	public static final int WIDTH = 1;
	public static final int HEIGHT = 1;
	public static final Color STAR_COLOR = Color.WHITE;
	private int starX;
	private int starY;
	private int size;
	private int speed;
	
	/**
	 * Creates a star whose initial position is randomly generated.
	 */
	public Star(GUI gui, Random r) {
		//establish position and distance at creation
		this.starX = r.nextInt(gui.getFrameRightX() - gui.getFrameLeftX()) + 
				gui.getFrameLeftX();
		this.starY = r.nextInt(gui.getFrameBottomY() - gui.getFrameTopY()) + 
				gui.getFrameTopY();
		int distance = r.nextInt(6);
		if (distance < 3) {
			this.size = 2;
			this.speed = 1;
		} else if (distance < 5) {
			this.size = 2;
			this.speed = 2;
		} else {
			this.size = 2;
			this.speed = 3;
		}
	}
	
	public void setY(int starY) {this.starY = starY;}
	
	public void setX(int starX) {this.starX = starX;}

	public int getY() {return this.starY;}

	public int getX() {return this.starX;}

	public void incrementPosition() {
		starY += speed;
	}
	
	public int getSize() {return this.size;}

	@Override
	public void drawMe(Graphics2D g) {
		g.setColor(STAR_COLOR);
		g.fillRect(starX, starY, size * WIDTH, size * HEIGHT);
	}
	
}
