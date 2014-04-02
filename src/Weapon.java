import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;


public class Weapon implements Attachment {
	
	public static final int LASER_ONE = 1;
	public static final int LASER_TWO = 2;
	public static final int LASER_THREE = 3;
	public static final int SPINE_CANNON_ONE = 4;
	public static final int SPINE_CANNON_TWO = 5;
	public static final int SPINE_CANNON_THREE = 6;
	public static final int SEMICHARGER_ONE = 7;
	public static final int SEMICHARGER_TWO = 8;
	public static final int SEMICHARGER_THREE = 9;
	public static final int ROCKET_ONE = 10;
	public static final int ROCKET_TWO = 11;
	public static final int ROCKET_THREE = 12;
	public static final int BURST_ONE = 13;
	public static final int BURST_TWO = 14;
	public static final int BURST_THREE = 15;

	private int xCoord;
	private int yCoord;
	
	private Color exploColor;
	
	private Random r;
	private double angularDirection;
	private int type;
	private int currentFaction;
	private int cooldownLength;
	private int cooldown;
	private boolean firing;
	private boolean special;

	//Constructor
	public Weapon(int type, int currentFaction) {
		this.type = type;
		this.r = new Random();
		
		switch (type) {
		case Weapon.LASER_ONE:
			cooldownLength = 20;
			exploColor = Color.YELLOW;
			special = false;
			break;
		case Weapon.LASER_TWO:
			cooldownLength = 20;
			exploColor = Color.BLUE;
			special = false;
			break;
		case Weapon.LASER_THREE:
			cooldownLength = 10;
			exploColor = Color.WHITE;
			special = false;
			break;
		case Weapon.ROCKET_ONE:
			special = false;
			break;
		case Weapon.SPINE_CANNON_ONE:
			cooldownLength = 40;
			exploColor = Color.YELLOW;
			special = true;
			break;
		case Weapon.SPINE_CANNON_TWO:
			cooldownLength = 35;
			exploColor = Color.BLUE;
			special = true;
			break;
		case Weapon.SPINE_CANNON_THREE:
			cooldownLength = 30;
			exploColor = Color.WHITE;
			special = true;
			break;
		case Weapon.SEMICHARGER_ONE:
			cooldownLength = 5;
			exploColor = Color.YELLOW;
			special = false;
			break;
		case Weapon.SEMICHARGER_TWO:
			cooldownLength = 4;
			exploColor = Color.BLUE;
			special = false;
			break;
		case Weapon.SEMICHARGER_THREE:
			cooldownLength = 3;
			exploColor = Color.WHITE;
			special = false;
			break;
		case Weapon.BURST_ONE:
			cooldownLength = 40;
			exploColor = Color.YELLOW;
			special = false;
			break;
		case Weapon.BURST_TWO:
			cooldownLength = 40;
			exploColor = Color.BLUE;
			special = false;
			break;
		case Weapon.BURST_THREE:
			cooldownLength = 20;
			exploColor = Color.WHITE;
			special = false;
			break;
		}
		
		this.currentFaction = currentFaction;
		if (currentFaction == Game.FACTION_PLAYER) {
			angularDirection = (Math.PI) / 2;
		} else {
			angularDirection = 3 * (Math.PI) / 2 ;
		}
	}

	//Private Methods
	
	//Public Methods
	public ArrayList<Projectile> fire() {
		ArrayList<Projectile> projArr = new ArrayList<Projectile>();
		
		firing = true;
		cooldown = cooldownLength;

		switch (type) {
		case Weapon.LASER_ONE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_ONE, 
					angularDirection, currentFaction)); 
			break;
		case Weapon.LASER_TWO:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_TWO, 
					angularDirection, currentFaction)); 
			break;
		case Weapon.LASER_THREE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_THREE, 
					angularDirection, currentFaction)); 
			break;
		case Weapon.ROCKET_ONE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.ROCKET_ONE, 
					angularDirection, currentFaction)); 
			break;
		case Weapon.SPINE_CANNON_ONE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.SPINE_SHOT_ONE, 
					angularDirection, currentFaction)); 
			break;
		case Weapon.SPINE_CANNON_TWO:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.SPINE_SHOT_TWO, 
					angularDirection, currentFaction)); 
			break;
		case Weapon.SPINE_CANNON_THREE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.SPINE_SHOT_THREE, 
					angularDirection, currentFaction)); 
			break;
		case Weapon.SEMICHARGER_ONE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_ONE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction)); 
			break;
		case Weapon.SEMICHARGER_TWO:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_TWO, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction)); 
			break;
		case Weapon.SEMICHARGER_THREE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_THREE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction)); 
			break;
		case Weapon.BURST_ONE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_ONE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_ONE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_ONE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction)); 
			break;
		case Weapon.BURST_TWO:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_TWO, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_TWO, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_TWO, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_TWO, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			break;
		case Weapon.BURST_THREE:
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_THREE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_THREE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_THREE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_THREE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			projArr.add(new Projectile(xCoord, yCoord, Projectile.LASER_THREE, 
					(angularDirection - Math.PI / 8) + (r.nextDouble() * Math.PI / 4),
					currentFaction));
			break;
		}
		
		return projArr;
	}
	public Explosion explode(int originX, int originY) {
		firing = false;
		switch (type) {
		case Weapon.LASER_ONE:
		case Weapon.LASER_TWO:
		case Weapon.LASER_THREE:
		case Weapon.SEMICHARGER_ONE:
		case Weapon.SEMICHARGER_TWO:
		case Weapon.SEMICHARGER_THREE:
		case Weapon.BURST_ONE:
		case Weapon.BURST_TWO:
		case Weapon.BURST_THREE:
			return new Explosion(xCoord, yCoord, 1, 10, exploColor, 
					Explosion.TYPE_LASER_SHOT, false);
		case Weapon.ROCKET_ONE:
			break;
		case Weapon.SPINE_CANNON_ONE:
		case Weapon.SPINE_CANNON_TWO:
		case Weapon.SPINE_CANNON_THREE:
			return new Explosion(xCoord, yCoord, 1, 15, exploColor, 
					Explosion.TYPE_LASER_SHOT, false);
		}
		return null;
	}
	@Override
	public int getType() {
		return Attachment.TYPE_WEAPON;
	}
	public boolean isSpecial() {
		return special;
	}
	public boolean isOnCooldown() {
		return (cooldown > 0);
	}
	public boolean isFiring() {
		return (firing);
	}
	@Override
	public void setPosition(int startX, int startY) {
		xCoord = startX;
		yCoord = startY;
	}
	@Override
	public void updatePosition(int deltaX, int deltaY) {
		xCoord += deltaX;
		yCoord += deltaY;
	}
	public void resetCooldown() {
		cooldown = 0;
	}
	public void decrementCooldown() {
		if (cooldown > 0) {
			cooldown --;
		}
	}
	@Override
	public void drawMe(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	
}
