import java.awt.Point;
import java.util.Random;

//TODO MAKE THE TIME WASTING ROUTE
//TODO MAKE A RANDOM ROUTE?
public class Route {
	
	//stored fighter type arrays
	public static final int[] TYPE_7M = {Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM,
		Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM,
		Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM};
	public static final int[] TYPE_10M = {Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM,
		Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM,
		Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM, 
		Fighter.TYPE_ENEMY_MEDIUM, Fighter.TYPE_ENEMY_MEDIUM};
	public static final int[] TYPE_10P = {Fighter.TYPE_PLAYER_FIGHTER, Fighter.TYPE_PLAYER_FIGHTER,
		Fighter.TYPE_PLAYER_FIGHTER, Fighter.TYPE_PLAYER_FIGHTER, Fighter.TYPE_PLAYER_FIGHTER,
		Fighter.TYPE_PLAYER_FIGHTER, Fighter.TYPE_PLAYER_FIGHTER, Fighter.TYPE_PLAYER_FIGHTER, 
		Fighter.TYPE_PLAYER_FIGHTER, Fighter.TYPE_PLAYER_FIGHTER};
	
	//stored coordinate arrays	(START_END_FLAIR)
	public static final Point[] UL_MR_NONE = UL_MR_NONE_Builder();
	public static final int UL_MR_NONE_SPAWN = 79;
	private static Point[] UL_MR_NONE_Builder() {
		Point[] points = new Point[4];
		//initial
		points[0] = new Point(0, 96);
		//flying straight right
		points[1] = new Point(316, 96);
		//flying right and down
		points[2] = new Point(572, 352);
		//flying straight right again
		points[3] = new Point(892, 352);
		
		return points;
	}
	
	public static final Point[] UR_ML_NONE = UR_ML_NONE_Builder();
	public static final int UR_ML_NONE_SPAWN = 79;
	private static Point[] UR_ML_NONE_Builder() {
		Point[] points = new Point[4];
		//initial
		points[0] = new Point(892, 96);
		//flying straight left
		points[1] = new Point(572, 96);
		//flying left and down
		points[2] = new Point(316, 352);
		//flying straight left again
		points[3] = new Point(0, 352);
		
		return points;
	}
	
	//stored starting points
	public static final Point TOP_CENTER = new Point(450, 25);
	public static final Point BOTTOM_CENTER = new Point(450, 725);
	
	//non-static variables
	private int[] fighterTypes;	//the fighters appear in the order of the array
	private Point[] points;
	private Point point;
	private Random r;
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	private int faction;
	private int spawnTicks;
	private int usedFighters;
	private boolean isRandom;
	
	public Route(Point[] points, int spawnTicks, int[] fighterTypes, int faction) {
		this.spawnTicks = spawnTicks;
		this.usedFighters = 0;
		this.isRandom = false;
		this.faction = faction;
		
		this.points = points;
		this.fighterTypes = fighterTypes;
	}

	public Route(Point startingPoint, int spawnTicks, int minX, int maxX, int minY, 
			int maxY, int[] fighterTypes, int faction) {
		this.spawnTicks = spawnTicks;
		this.isRandom = true;
		r = new Random();
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.faction = faction;
		
		this.point = startingPoint;
		usedFighters = 0;
		this.fighterTypes = fighterTypes;
	}
	
	public boolean isDone() {
		return (usedFighters == fighterTypes.length);
	}
	
	public int getNextDestX(int nextDest) {
		if (this.isRandom){
			return r.nextInt(maxX-minX) + minX;
		} else {
			return (int) points[nextDest].getX();
		}
	}
	
	public int getNextDestY(int nextDest) {
		if (this.isRandom){
			return r.nextInt(maxY-minY) + minY;
		} else {
			return (int) points[nextDest].getY();
		}
	}
	
	public int getInitialX() {
		if (this.isRandom) {
			return (int) point.getX();
		} else {
			return (int) points[0].getX();	
		}
	}
	
	public int getInitialY() {
		if (this.isRandom) {
			return (int) point.getY();
		} else {
			return (int) points[0].getY();
		}
	} 

	public int getNextFighter(int nextFighter) {
		usedFighters ++;
		return fighterTypes[nextFighter];
	}

	public int getSpawnTicks() {
		return spawnTicks;
	}

	public int getFinalX() {
		if (this.isRandom){
			return -1;
		} else {
			return (int) points[points.length - 1].getX();
		}
	}
	
	public int getFinalY() {
		if (this.isRandom){
			return -1;
		} else {
			return (int) points[points.length - 1].getY();
		}
	}
	
	public int getFaction() {
		return faction;
	}
}
