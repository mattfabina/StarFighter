import java.awt.event.KeyEvent;
import java.util.*;

//TODO MAKE THE FOLLOWING SCREENS: MENU, SCORE, OPTIONS
public class Game {
	
	//game constants
	public static final String TITLE = "Star Destroyer";
	public static final int FACTION_ENEMY = -1;
	public static final int FACTION_PLAYER = 1;
	
	//game states
	public static final int MENU_SCREEN = 0;
	public static final int LEVEL_ACTIVE = 1;
	public static final int SCORE_SCREEN = 2;
	public static final int OPTIONS_SCREEN = 3;
	public static final int PAUSED = 4;
	
	//global objects for the game
	//operator anything happens
	private GUI gui;
	private LevelOperator levOp;
	private Random r;
	private Fighter playerFighter;
	
	//array lists storing game objects
	private ArrayList<Fighter> fighterArray;
	private ArrayList<Projectile> projectileArray;
	private ArrayList<Projectile> specialShots;
	private ArrayList<Explosion> explosionArray;
	private ArrayList<Star> starArray;
	
	//variables pertinent to the game
	private boolean isDone;						//whether the game has been quit
	private int gameState;
	
	//Constructor
	public Game() {
		//Constructs the objects that are used exclusively by the game object
		levOp = new LevelOperator();
		
		//Constructs the game's necessary runtime objects
		this.r = new Random();
		this.fighterArray = new ArrayList<Fighter>();
		this.projectileArray = new ArrayList<Projectile>();
		this.specialShots = new ArrayList<Projectile>();
		this.explosionArray = new ArrayList<Explosion>();
		this.starArray = new ArrayList<Star>();
		
		this.isDone = false;
		this.gameState = MENU_SCREEN;
	}
	
	//Private Methods
	private void initializeStarField(int fieldSize) {
		//initializes the field of stars
		for (int i = 0; i < fieldSize; i++) {
			Star star = new Star(this.gui, this.r);
			starArray.add(star);;
		}
	}
	private void addPlayerFighter() {
		playerFighter = new Fighter(gui.getWidth() / 2 - 36, 
				gui.getHeight() / 2, Fighter.TYPE_PLAYER_FIGHTER, FACTION_PLAYER);
	}
	private void updateFighterPositions() {
		//increment level state and check for a new fighter
		boolean readyForSpawn = levOp.incrementLevelState();
		if (readyForSpawn && !levOp.isLevelDone()) {
			fighterArray.add(levOp.nextFighter());
		}
		
		//update the position of fighters and check if any should be removed
		ArrayList<Fighter> fighterArrayCopy = new ArrayList<Fighter>();
		fighterArrayCopy.add(playerFighter);
		fighterArrayCopy.addAll(fighterArray);
		
		//Find Projectile/Fighter Collisions
		ArrayList<Projectile> projectileArrayCopy = new ArrayList<Projectile>();
		projectileArrayCopy.addAll(projectileArray);
		ArrayList<Projectile> specialShotsCopy = new ArrayList<Projectile>();
		specialShotsCopy.addAll(specialShots);
		
		for (Fighter f: fighterArrayCopy) {
			for (Projectile p: projectileArrayCopy) {
				if ((p.getFaction() != f.getFaction()) && 
						f.getFighterOutline().intersects(p.getProjRectangle())) {
						f.damage(p.getDamage());
						projectileArray.remove(p);
				}
			}
			for (Projectile p: specialShotsCopy) {
				if ((p.getFaction() != f.getFaction()) && 
						f.getFighterOutline().intersects(p.getProjRectangle())) {
						f.damage(p.getDamage());
						specialShots.remove(p);
				}
			}
			//NP Fighter Update
			if (f != playerFighter) {
				if (f.isAtFinalPosition()) {
					fighterArray.remove(f);
				} else if (f.isAtZeroHealth()) {
					explosionArray.addAll(f.explode(r));
					fighterArray.remove(f);
				} else {
					f.moveToDest();
				}
			}
		}
	}
	private void updateWeaponCooldowns() {
		for (Fighter f: fighterArray) {
			f.decrementWeaponCooldowns();
			if (r.nextDouble() < .20) {
				projectileArray.addAll(f.fireWeapons());
				explosionArray.addAll(f.getWeaponExplosions());
			}
		}
		playerFighter.decrementWeaponCooldowns();
	}
	private void updateProjPositions() {
		ArrayList<Projectile> projectileArrayCopy = new ArrayList<Projectile>();
		projectileArrayCopy.addAll(projectileArray);
		for (Projectile proj: projectileArrayCopy) {
			//remove if off screen
			if (proj.getX() + proj.getWidth() < gui.getFrameLeftX() ||	//left side of screen
					proj.getX() > gui.getFrameRightX() ||				//right side of screen
					proj.getY() + proj.getHeight() < gui.getFrameTopY() ||	//top of screen
					proj.getY() > gui.getFrameBottomY()) {				//bottom of screen
				projectileArray.remove(proj);
				
			} else {
				proj.incrementPosition();
			}
		}
		
		ArrayList<Projectile> specialShotsCopy = new ArrayList<Projectile>();
		specialShotsCopy.addAll(specialShots);
		for (Projectile proj: specialShotsCopy) {
			//remove if off screen
			if (proj.getX() + proj.getWidth() < gui.getFrameLeftX() ||	//left side of screen
					proj.getX() > gui.getFrameRightX() ||				//right side of screen
					proj.getY() + proj.getHeight() < gui.getFrameTopY() ||	//top of screen
					proj.getY() > gui.getFrameBottomY()) {				//bottom of screen
				specialShots.remove(proj);
			} else {
				proj.incrementPosition();
			}
		}
	}
	private void updateExplosionSizes() {
		ArrayList<Explosion> explosionArrayCopy = new ArrayList<Explosion>();
		for (Explosion e: explosionArray) {
			explosionArrayCopy.add(e);
		}
		for (Explosion e: explosionArrayCopy) {
			if (e.isDone()) {
				explosionArray.remove(e);
				if (e.spawnsNew()) {
					explosionArray.add(e.nextExplosion(r));
				}
			} else {
				e.tick();
			}
		}
	}
	private void updateStarPositions() {
		for (Star star: starArray) {
			star.incrementPosition();
			if (star.getY() + (Star.HEIGHT * star.getSize()) > gui.getFrameBottomY()) {
				star.setX(r.nextInt(gui.getFrameRightX() - gui.getFrameLeftX()) + 
						gui.getFrameLeftX());
				star.setY(gui.getFrameTopY());
			}
		}
	}

	//Public Methods
	public void tick(double time) {
		switch (gameState) {
		case MENU_SCREEN:
			initializeStarField(250);
			addPlayerFighter();
			this.gameState = LEVEL_ACTIVE;
			levOp.nextLevel();
			break;
		case LEVEL_ACTIVE:
			
			//if level is finished, and only the player fighter is left
			if (levOp.isLevelDone() && fighterArray.size() == 0) {
				//move to score screen

				//else continue updating the status of the level
			} else {
				
				updateStarPositions();
				updateProjPositions();
				updateWeaponCooldowns();
				updateExplosionSizes();
				updateFighterPositions();
				
			}
			
			break;
		case SCORE_SCREEN:
			//display score screen, move back to level active when finished
			break;
		case OPTIONS_SCREEN:
			break;
		case PAUSED:
			break;
		}		
	}
	public void buttonPressed(int button) {
		switch (button) {
		case KeyEvent.VK_SPACE:
			projectileArray.addAll(playerFighter.fireWeapons());
			explosionArray.addAll(playerFighter.getWeaponExplosions());
			break;
		case KeyEvent.VK_UP:
	        playerFighter.updatePosition(0, -8);
	        break;
		case KeyEvent.VK_DOWN:
			playerFighter.updatePosition(0, 8);
	        break;
		case KeyEvent.VK_RIGHT:
	        playerFighter.updatePosition(8, 0);	
	        break;
		case KeyEvent.VK_LEFT:
	        playerFighter.updatePosition(-8, 0);
	        break;
		case KeyEvent.VK_Q:
			if (!specialShots.isEmpty()) {
				//activate specials
				ArrayList<Projectile> temp = new ArrayList<Projectile>();
				temp.addAll(specialShots);
				for (Projectile p: temp) {
					if (p.getFaction() == Game.FACTION_PLAYER && p.getType() == Projectile.SPINE_SHOT_ONE ||
							p.getType() == Projectile.SPINE_SHOT_TWO ||
							p.getType() == Projectile.SPINE_SHOT_THREE) {
						projectileArray.addAll(p.activateSpineShot());
						specialShots.remove(p);
					}
				}
			} else {
				//fire specials
				specialShots.addAll(playerFighter.fireSpecial());
				explosionArray. addAll(playerFighter.getSpecialExplosion());
			}
			break;
		}
	}
	public void setGui(GUI gui) {
		this.gui = gui;
	}
	public ArrayList<Drawable2D> getDrawable2DArray() {
		ArrayList<Drawable2D> d = new ArrayList<Drawable2D>();
		d.addAll(getShields());
		d.addAll(starArray);
		d.add(playerFighter);
		d.addAll(fighterArray);
		d.addAll(projectileArray);
		d.addAll(specialShots);
		d.addAll(explosionArray);
		return d;
	}
	private ArrayList<Shield> getShields() {
		ArrayList<Shield> shields = new ArrayList<Shield>();
		
		shields.add(playerFighter.getShield());
		for (Fighter f: fighterArray) {
			shields.add(f.getShield());	
		}
		
		return shields;
	}

	public Fighter getPlayerFighter() {
		return this.playerFighter;
	}
	public boolean isDone() {
		return isDone;
	}

}
