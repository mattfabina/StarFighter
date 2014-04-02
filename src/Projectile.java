import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Projectile implements Drawable2D {

	public static final int LASER_ONE = 1;
	public static final int LASER_TWO = 2;
	public static final int LASER_THREE = 3;
	public static final int ROCKET_ONE = 4;
	public static final int SPINE_SHOT_ONE = 5;
	public static final int SPINE_SHOT_TWO = 6;
	public static final int SPINE_SHOT_THREE = 7;
	
	private double angularDirection;
	private int xCoord;
	private int yCoord;
	private int velocity;
	private double remX;
	private double remY;
	
	private int type;
	private int height;
	private int width;
	private int damage;
	private int faction;
	private Color projColor;
	
	public Projectile(int initX, int initY, int type, double angularDirection, int faction) {
		this.angularDirection = angularDirection;
		this.type = type;
		
		switch (type) {
		//player projectiles
		case LASER_ONE:
			velocity = 10;
			height = 24;
			width = 4;
			damage = 1;
			this.faction = faction;
			projColor = Color.RED;
			break;
		case LASER_TWO:
			velocity = 12;
			height = 24;
			width = 4;
			damage = 2;
			this.faction = faction;
			projColor = Color.YELLOW;
			break;
		case LASER_THREE:
			velocity = 15;
			height = 24;
			width = 6;
			damage = 3;
			this.faction = faction;
			projColor = Color.BLUE;
			break;
		case ROCKET_ONE:
			//implement animation
			break;
		case SPINE_SHOT_ONE:
			velocity = 3;
			height = 12;
			width = 12;
			damage = 3;
			this.faction = faction;
			projColor = Color.RED;
			break;
		case SPINE_SHOT_TWO:
			velocity = 5;
			height = 12;
			width = 12;
			damage = 4;
			this.faction = faction;
			projColor = Color.YELLOW;
			break;
		case SPINE_SHOT_THREE:
			velocity = 7;
			height = 12;
			width = 12;
			damage = 5;
			this.faction = faction;
			projColor = Color.BLUE;
			break;
		}
		
		this.xCoord = initX - (width / 2);
		this.yCoord = initY - (height);
	}
		
	public int getX() {return this.xCoord;}
	
	public int getY() {return this.yCoord;}
		
	public int getHeight() {return this.height;}
	
	public int getWidth() {return this.width;}
	
	public void incrementPosition() {
		remX = (Math.cos(angularDirection) * velocity + remX) - 
				(Math.floor(Math.cos(angularDirection) * velocity + remX));
		remY = (Math.sin(angularDirection) * velocity + remY) - 
				(Math.floor(Math.sin(angularDirection) * velocity + remY));
		int velocityX = (int) Math.floor(Math.cos(angularDirection) * velocity + remX);
		int velocityY = (int) Math.floor(Math.sin(angularDirection) * velocity + remY);
		
		this.xCoord -= velocityX;
		this.yCoord -= velocityY;
	}
	
	public ArrayList<Projectile> activateSpineShot() {
		ArrayList<Projectile> projArr = new ArrayList<Projectile>();
		
		switch (type) {
		case SPINE_SHOT_ONE:
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, Math.PI / 2, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 5 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 3 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 7 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, Math.PI, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 9 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 5 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 11 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 3 * Math.PI / 2, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 13 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 7 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 15 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 0, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 1 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_ONE, 3 * Math.PI / 8, faction));
			break;
		case SPINE_SHOT_TWO:
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, Math.PI / 2, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 5 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 3 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 7 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, Math.PI, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 9 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 5 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 11 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 3 * Math.PI / 2, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 13 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 7 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 15 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 0, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 1 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_TWO, 3 * Math.PI / 8, faction));
			break;
		case SPINE_SHOT_THREE:	
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, Math.PI / 2, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 5 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 3 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 7 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, Math.PI, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 9 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 5 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 11 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 3 * Math.PI / 2, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 13 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 7 * Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 15 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 0, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 1 * Math.PI / 8, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, Math.PI / 4, faction));
			projArr.add(new Projectile (xCoord, yCoord, LASER_THREE, 3 * Math.PI / 8, faction));
			break;
		}
		
		return projArr;
	}
	
	@Override
	public void drawMe(Graphics2D g) {
		g.setColor(projColor);
		g.fillRoundRect(xCoord, yCoord, width, height, width, width);
	}

	public Rectangle2D getProjRectangle() {
		Rectangle rect = new Rectangle(xCoord, yCoord, width, height);
		return (Rectangle2D)rect;
	}
	
	public int getDamage() {return this.damage;}

	public int getFaction() {return faction;}
	
	public int getType() {
		return type;
	}
	
}
