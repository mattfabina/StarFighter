import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

//TODO CREATE DEBRIS ON EXPLOSION
//TODO ACCELERATION/DECELERATION
//TODO REVAMP SO MODEL HAS NOTHING TO DO WITH FACTION
public class Fighter implements Drawable2D {

	//classifications for different types of fighters
	public static final int DECOY = -1;
	public static final int TYPE_PLAYER_FIGHTER = 1;
	public static final int TYPE_ENEMY_SMALL = 2;
	public static final int TYPE_ENEMY_MEDIUM = 3;
	public static final int TYPE_ENEMY_LARGE = 4;
	
	//shape related variables
	private ArrayList<Polygon> chassisPolygons;
	private ArrayList<Color> polygonColors;
	private int originX;
	private int originY;
	private int type;
	
	//attachment related arrays
	private ArrayList<ChassisSlot> slots;
	
	//health related variables
	private int health;
	private int faction;
	
	//movement variables
	private Route flightRoute;
	private double remX;
	private double remY;
	private int destX;
	private int destY;
	private int currDest;
	private int speed;
		
	///////////////
	//Constructors
	public Fighter(int originX, int originY, int type, int faction) {
		chassisPolygons = new ArrayList<Polygon>();
		polygonColors = new ArrayList<Color>();
		this.faction = faction;
		
		this.type = type;
		
		if (type != DECOY) {
			this.originX = originX;
			this.originY = originY;
			
			switch (type) {
			case TYPE_PLAYER_FIGHTER:
				constructPlayerFighter();
				break;
			case TYPE_ENEMY_MEDIUM:
				constructEnemyMedium();
				break;
			default:
				try {	
					throw new Exception("Fighter type (" + type + ") is not a " +
							"valid Fighter type.");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			
			updatePosition(0, 0); //initiates position
		}
	}
	public Fighter(int type, Route flightRoute, int faction) {
		chassisPolygons = new ArrayList<Polygon>();
		polygonColors = new ArrayList<Color>();
		this.faction = faction;
		
		this.type = type;
		this.originX = flightRoute.getInitialX();
		this.originY = flightRoute.getInitialY();
		this.currDest = 0;
		this.destX = flightRoute.getNextDestX(currDest);
		this.destY = flightRoute.getNextDestY(currDest);
		this.flightRoute = flightRoute;		
		
		switch (type) {
		case TYPE_PLAYER_FIGHTER:
			constructPlayerFighter();
			break;
		case TYPE_ENEMY_MEDIUM:
			constructEnemyMedium();
			break;
		default:
			try {	
				throw new Exception("Fighter type (" + type + ") is not a " +
						"valid Fighter type.");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		updatePosition(0, 0); //initiates position	
	}
	
	//Private Methods
	private void constructPlayerFighter() {
		//attributes
		//totalWidth = 72;
		//totalHeight = 50;
		this.speed = 10;
		health = 5;
		
		//shape and color of the player fighter
		polygonColors.add(Color.GRAY);
		chassisPolygons.add(new Polygon());
		chassisPolygons.get(0).addPoint(originX + 0, originY + 42); //weapon 3 (1, 36)
		chassisPolygons.get(0).addPoint(originX + 2, originY + 30); //engine 5
		chassisPolygons.get(0).addPoint(originX + 10, originY + 30);
		chassisPolygons.get(0).addPoint(originX + 12, originY + 42); //weapon 4 (11, 36)
		chassisPolygons.get(0).addPoint(originX + 12, originY + 44);
		chassisPolygons.get(0).addPoint(originX + 14, originY + 48); 
		chassisPolygons.get(0).addPoint(originX + 18, originY + 48);
		chassisPolygons.get(0).addPoint(originX + 22, originY + 44);
		chassisPolygons.get(0).addPoint(originX + 22, originY + 16);
		chassisPolygons.get(0).addPoint(originX + 24, originY + 8); //weapon 1
		chassisPolygons.get(0).addPoint(originX + 28, originY + 2);
		chassisPolygons.get(0).addPoint(originX + 30, originY + 0); //cockpit 1 (33, 30)
		chassisPolygons.get(0).addPoint(originX + 34, originY + 0);
		chassisPolygons.get(0).addPoint(originX + 36, originY + 2);
		chassisPolygons.get(0).addPoint(originX + 40, originY + 8); //weapon 2
		chassisPolygons.get(0).addPoint(originX + 42, originY + 16);
		chassisPolygons.get(0).addPoint(originX + 42, originY + 44);
		chassisPolygons.get(0).addPoint(originX + 46, originY + 48); 
		chassisPolygons.get(0).addPoint(originX + 50, originY + 48);
		chassisPolygons.get(0).addPoint(originX + 52, originY + 44);
		chassisPolygons.get(0).addPoint(originX + 52, originY + 42); //weapon 5 (53, 36)
		chassisPolygons.get(0).addPoint(originX + 54, originY + 30); //engine 5
		chassisPolygons.get(0).addPoint(originX + 62, originY + 30);
		chassisPolygons.get(0).addPoint(originX + 64, originY + 42); //weapon 6 (63, 36)
		chassisPolygons.get(0).addPoint(originX + 64, originY + 68);
		chassisPolygons.get(0).addPoint(originX + 62, originY + 80);
		chassisPolygons.get(0).addPoint(originX + 54, originY + 80); //engine 2
		chassisPolygons.get(0).addPoint(originX + 52, originY + 68);
		chassisPolygons.get(0).addPoint(originX + 52, originY + 60);
		chassisPolygons.get(0).addPoint(originX + 50, originY + 56);
		chassisPolygons.get(0).addPoint(originX + 42, originY + 54);
		chassisPolygons.get(0).addPoint(originX + 38, originY + 58);
		chassisPolygons.get(0).addPoint(originX + 36, originY + 66);
		chassisPolygons.get(0).addPoint(originX + 28, originY + 66); //engine 3
		chassisPolygons.get(0).addPoint(originX + 26, originY + 58);
		chassisPolygons.get(0).addPoint(originX + 22, originY + 54);
		chassisPolygons.get(0).addPoint(originX + 14, originY + 56);
		chassisPolygons.get(0).addPoint(originX + 12, originY + 60);
		chassisPolygons.get(0).addPoint(originX + 12, originY + 68);
		chassisPolygons.get(0).addPoint(originX + 10, originY + 80);
		chassisPolygons.get(0).addPoint(originX + 2, originY + 80); //engine 1
		chassisPolygons.get(0).addPoint(originX + 0, originY + 68);
		
		//establish attachment slots
		slots = new ArrayList<ChassisSlot>();
		slots.add(new ChassisSlot(Attachment.TYPE_ENGINE, 2, 80, true));		//0
		slots.add(new ChassisSlot(Attachment.TYPE_ENGINE, 54, 80, true));
		slots.add(new ChassisSlot(Attachment.TYPE_ENGINE, 28, 66, true));
		slots.add(new ChassisSlot(Attachment.TYPE_ENGINE, 2, 30, true));
		slots.add(new ChassisSlot(Attachment.TYPE_ENGINE, 54, 30, true));
		slots.add(new ChassisSlot(Attachment.TYPE_WEAPON, 24, 8, false));		//5
		slots.add(new ChassisSlot(Attachment.TYPE_WEAPON, 40, 8, false));
		slots.add(new ChassisSlot(Attachment.TYPE_WEAPON, 1, 36, false));
		slots.add(new ChassisSlot(Attachment.TYPE_WEAPON, 11, 36, false));
		slots.add(new ChassisSlot(Attachment.TYPE_WEAPON, 53, 36, false));
		slots.add(new ChassisSlot(Attachment.TYPE_WEAPON, 63, 36, false));		//10
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));				//15
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_COCKPIT, 32, 26, false));
		slots.add(new ChassisSlot(Attachment.TYPE_SHIELD, true));				//20
		
		//add default engines
		slots.get(0).addAttachment(new Engine(Engine.TYPE_SMALL, false), originX, originY);
		slots.get(1).addAttachment(new Engine(Engine.TYPE_SMALL, false), originX, originY);
		slots.get(2).addAttachment(new Engine(Engine.TYPE_SMALL, false), originX, originY);
		slots.get(3).addAttachment(new Engine(Engine.TYPE_SMALL, true), originX, originY);
		slots.get(4).addAttachment(new Engine(Engine.TYPE_SMALL, true), originX, originY);
			
		//add default weapons
		slots.get(5).addAttachment(new Weapon(Weapon.SPINE_CANNON_THREE, faction), originX, originY);
		slots.get(6).addAttachment(new Weapon(Weapon.SPINE_CANNON_THREE, faction), originX, originY);
		slots.get(7).addAttachment(new Weapon(Weapon.SPINE_CANNON_THREE, faction), originX, originY);
		slots.get(8).addAttachment(new Weapon(Weapon.SPINE_CANNON_THREE, faction), originX, originY);
		slots.get(9).addAttachment(new Weapon(Weapon.SPINE_CANNON_THREE, faction), originX, originY);
		slots.get(10).addAttachment(new Weapon(Weapon.SPINE_CANNON_THREE, faction), originX, originY);
		
		//add default armor
		slots.get(11).addAttachment(new Armor(Armor.PLAYER_FIGHTER_BODY_BRACE, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(12).addAttachment(new Armor(Armor.PLAYER_FIGHTER_ENGINE_1_CAP, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(13).addAttachment(new Armor(Armor.PLAYER_FIGHTER_ENGINE_2_CAP, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(14).addAttachment(new Armor(Armor.PLAYER_FIGHTER_ENGINE_3_CAP, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(15).addAttachment(new Armor(Armor.PLAYER_FIGHTER_ARM_1_CAP, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(16).addAttachment(new Armor(Armor.PLAYER_FIGHTER_ARM_2_CAP, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(17).addAttachment(new Armor(Armor.PLAYER_FIGHTER_ARM_1_BAND, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(18).addAttachment(new Armor(Armor.PLAYER_FIGHTER_ARM_2_BAND, Armor.COLOR_GOLD, Color.GRAY), originX, originY);

		//add default cockpits
		slots.get(19).addAttachment(new Cockpit(Cockpit.TYPE_CYAN), originX, originY);
	
		//add shield
		slots.get(20).addAttachment(new Shield(Shield.TYPE_WEAK, 110, 85), originX - 10, originY - 5);
		
	}
 	private void constructEnemyMedium() {
		//attributes
		//totalWidth = 64;
		//totalHeight = 64;
		this.speed = 10;
		health = 5;
		
		//shape of the fighter
		polygonColors.add(Color.GRAY);//new Color(0, 150, 150));
		chassisPolygons.add(new Polygon());
//		chassisPolygons.get(0).addPoint(originX + 0, originY + 8);
//		chassisPolygons.get(0).addPoint(originX + 2, originY + 4);
//		chassisPolygons.get(0).addPoint(originX + 4, originY + 2);
//		chassisPolygons.get(0).addPoint(originX + 8, originY + 0);
//		chassisPolygons.get(0).addPoint(originX + 16, originY + 0);
//		chassisPolygons.get(0).addPoint(originX + 20, originY + 2);
//		chassisPolygons.get(0).addPoint(originX + 22, originY + 4);
//		chassisPolygons.get(0).addPoint(originX + 26, originY + 6);
//		chassisPolygons.get(0).addPoint(originX + 38, originY + 6);
//		chassisPolygons.get(0).addPoint(originX + 42, originY + 4);
//		chassisPolygons.get(0).addPoint(originX + 44, originY + 2);
//		chassisPolygons.get(0).addPoint(originX + 48, originY + 0);
//		chassisPolygons.get(0).addPoint(originX + 56, originY + 0);
//		chassisPolygons.get(0).addPoint(originX + 60, originY + 2);
//		chassisPolygons.get(0).addPoint(originX + 62, originY + 4);
//		chassisPolygons.get(0).addPoint(originX + 64, originY + 8);
//		chassisPolygons.get(0).addPoint(originX + 64, originY + 44);
//		chassisPolygons.get(0).addPoint(originX + 62, originY + 48);
//		chassisPolygons.get(0).addPoint(originX + 60, originY + 50);
//		chassisPolygons.get(0).addPoint(originX + 56, originY + 52);
//		chassisPolygons.get(0).addPoint(originX + 48, originY + 52);
//		chassisPolygons.get(0).addPoint(originX + 44, originY + 50);
//		chassisPolygons.get(0).addPoint(originX + 42, originY + 48);
//		chassisPolygons.get(0).addPoint(originX + 40, originY + 44);
//		chassisPolygons.get(0).addPoint(originX + 40, originY + 38);
//		chassisPolygons.get(0).addPoint(originX + 42, originY + 34);
//		chassisPolygons.get(0).addPoint(originX + 44, originY + 32);
//		chassisPolygons.get(0).addPoint(originX + 48, originY + 30);
//		chassisPolygons.get(0).addPoint(originX + 50, originY + 30);
//		chassisPolygons.get(0).addPoint(originX + 54, originY + 28);
//		chassisPolygons.get(0).addPoint(originX + 56, originY + 26);
//		chassisPolygons.get(0).addPoint(originX + 58, originY + 22);
//		chassisPolygons.get(0).addPoint(originX + 58, originY + 20);
//		chassisPolygons.get(0).addPoint(originX + 56, originY + 16);
//		chassisPolygons.get(0).addPoint(originX + 54, originY + 14);
//		chassisPolygons.get(0).addPoint(originX + 50, originY + 12);
//		chassisPolygons.get(0).addPoint(originX + 48, originY + 12);
//		chassisPolygons.get(0).addPoint(originX + 44, originY + 14);
//		chassisPolygons.get(0).addPoint(originX + 42, originY + 16);
//		chassisPolygons.get(0).addPoint(originX + 38, originY + 18);
//		chassisPolygons.get(0).addPoint(originX + 36, originY + 18);
//		chassisPolygons.get(0).addPoint(originX + 32, originY + 20);
//		chassisPolygons.get(0).addPoint(originX + 28, originY + 18);
//		chassisPolygons.get(0).addPoint(originX + 26, originY + 18);
//		chassisPolygons.get(0).addPoint(originX + 22, originY + 16);
//		chassisPolygons.get(0).addPoint(originX + 20, originY + 14);
//		chassisPolygons.get(0).addPoint(originX + 16, originY + 12);
//		chassisPolygons.get(0).addPoint(originX + 14, originY + 12);
//		chassisPolygons.get(0).addPoint(originX + 10, originY + 14);
//		chassisPolygons.get(0).addPoint(originX + 8, originY + 16);
//		chassisPolygons.get(0).addPoint(originX + 6, originY + 20);
//		chassisPolygons.get(0).addPoint(originX + 6, originY + 22);
//		chassisPolygons.get(0).addPoint(originX + 8, originY + 26);
//		chassisPolygons.get(0).addPoint(originX + 10, originY + 28);
//		chassisPolygons.get(0).addPoint(originX + 14, originY + 30);
//		chassisPolygons.get(0).addPoint(originX + 16, originY + 30);
//		chassisPolygons.get(0).addPoint(originX + 20, originY + 32);
//		chassisPolygons.get(0).addPoint(originX + 22, originY + 34);
//		chassisPolygons.get(0).addPoint(originX + 24, originY + 38);
//		chassisPolygons.get(0).addPoint(originX + 24, originY + 44);
//		chassisPolygons.get(0).addPoint(originX + 22, originY + 48);
//		chassisPolygons.get(0).addPoint(originX + 20, originY + 50);
//		chassisPolygons.get(0).addPoint(originX + 16, originY + 52);
//		chassisPolygons.get(0).addPoint(originX + 8, originY + 52);
//		chassisPolygons.get(0).addPoint(originX + 4, originY + 50);
//		chassisPolygons.get(0).addPoint(originX + 2, originY + 48);
//		chassisPolygons.get(0).addPoint(originX + 0, originY + 44);
		
		chassisPolygons.get(0).addPoint(originX + 0, originY + 22);
		chassisPolygons.get(0).addPoint(originX + 2, originY + 14);
		chassisPolygons.get(0).addPoint(originX + 4, originY + 8);
		chassisPolygons.get(0).addPoint(originX + 6, originY + 4);
		chassisPolygons.get(0).addPoint(originX + 8, originY + 2);
		chassisPolygons.get(0).addPoint(originX + 12, originY + 0); //engine 1
		chassisPolygons.get(0).addPoint(originX + 20, originY + 0);
		chassisPolygons.get(0).addPoint(originX + 24, originY + 2);
		chassisPolygons.get(0).addPoint(originX + 26, originY + 4);
		chassisPolygons.get(0).addPoint(originX + 30, originY + 6);
		chassisPolygons.get(0).addPoint(originX + 42, originY + 6);
		chassisPolygons.get(0).addPoint(originX + 46, originY + 4);
		chassisPolygons.get(0).addPoint(originX + 48, originY + 2);
		chassisPolygons.get(0).addPoint(originX + 52, originY + 0); //engine 2
		chassisPolygons.get(0).addPoint(originX + 60, originY + 0);
		chassisPolygons.get(0).addPoint(originX + 64, originY + 2);
		chassisPolygons.get(0).addPoint(originX + 66, originY + 4);
		chassisPolygons.get(0).addPoint(originX + 68, originY + 8);
		chassisPolygons.get(0).addPoint(originX + 70, originY + 14);
		chassisPolygons.get(0).addPoint(originX + 72, originY + 22);
		chassisPolygons.get(0).addPoint(originX + 72, originY + 30);
		chassisPolygons.get(0).addPoint(originX + 70, originY + 38);
		chassisPolygons.get(0).addPoint(originX + 68, originY + 44);
		chassisPolygons.get(0).addPoint(originX + 66, originY + 48);
		chassisPolygons.get(0).addPoint(originX + 64, originY + 50);
		chassisPolygons.get(0).addPoint(originX + 60, originY + 52);
		chassisPolygons.get(0).addPoint(originX + 52, originY + 52);
		chassisPolygons.get(0).addPoint(originX + 48, originY + 50);
		chassisPolygons.get(0).addPoint(originX + 46, originY + 48);
		chassisPolygons.get(0).addPoint(originX + 44, originY + 44);
		chassisPolygons.get(0).addPoint(originX + 44, originY + 38);
		chassisPolygons.get(0).addPoint(originX + 46, originY + 34);
		chassisPolygons.get(0).addPoint(originX + 48, originY + 32);
		chassisPolygons.get(0).addPoint(originX + 52, originY + 30);
		chassisPolygons.get(0).addPoint(originX + 54, originY + 28);
		chassisPolygons.get(0).addPoint(originX + 56, originY + 24);
		chassisPolygons.get(0).addPoint(originX + 56, originY + 20);
		chassisPolygons.get(0).addPoint(originX + 54, originY + 16);
		chassisPolygons.get(0).addPoint(originX + 52, originY + 14);
		chassisPolygons.get(0).addPoint(originX + 48, originY + 12);
		chassisPolygons.get(0).addPoint(originX + 46, originY + 12);
		chassisPolygons.get(0).addPoint(originX + 42, originY + 14);
		chassisPolygons.get(0).addPoint(originX + 40, originY + 16);
		chassisPolygons.get(0).addPoint(originX + 36, originY + 18);
		chassisPolygons.get(0).addPoint(originX + 32, originY + 16);
		chassisPolygons.get(0).addPoint(originX + 30, originY + 14);
		chassisPolygons.get(0).addPoint(originX + 26, originY + 12);
		chassisPolygons.get(0).addPoint(originX + 24, originY + 12);
		chassisPolygons.get(0).addPoint(originX + 20, originY + 14);
		chassisPolygons.get(0).addPoint(originX + 18, originY + 16);
		chassisPolygons.get(0).addPoint(originX + 16, originY + 20);
		chassisPolygons.get(0).addPoint(originX + 16, originY + 24);
		chassisPolygons.get(0).addPoint(originX + 18, originY + 28);
		chassisPolygons.get(0).addPoint(originX + 20, originY + 30);
		chassisPolygons.get(0).addPoint(originX + 24, originY + 32);
		chassisPolygons.get(0).addPoint(originX + 26, originY + 34);
		chassisPolygons.get(0).addPoint(originX + 28, originY + 38);
		chassisPolygons.get(0).addPoint(originX + 28, originY + 44);
		chassisPolygons.get(0).addPoint(originX + 26, originY + 48);
		chassisPolygons.get(0).addPoint(originX + 24, originY + 50);
		chassisPolygons.get(0).addPoint(originX + 20, originY + 52);
		chassisPolygons.get(0).addPoint(originX + 12, originY + 52);
		chassisPolygons.get(0).addPoint(originX + 8, originY + 50);
		chassisPolygons.get(0).addPoint(originX + 6, originY + 48);
		chassisPolygons.get(0).addPoint(originX + 4, originY + 44);
		chassisPolygons.get(0).addPoint(originX + 2, originY + 38);
		chassisPolygons.get(0).addPoint(originX + 0, originY + 30);
		
		//establish attachment slots
		slots = new ArrayList<ChassisSlot>();
		slots.add(new ChassisSlot(Attachment.TYPE_ENGINE, 12, 0, true));
		slots.add(new ChassisSlot(Attachment.TYPE_ENGINE, 52, 0, true));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_ARMOR, false));
		slots.add(new ChassisSlot(Attachment.TYPE_COCKPIT, 36, 11, false));
		slots.add(new ChassisSlot(Attachment.TYPE_WEAPON, 32, 48, true));
		slots.add(new ChassisSlot(Attachment.TYPE_SHIELD, true));
		
		//add default engines
		slots.get(0).addAttachment(new Engine(Engine.TYPE_SMALL, true), originX, originY);
		slots.get(1).addAttachment(new Engine(Engine.TYPE_SMALL, true), originX, originY);
		
		//add default armor
		slots.get(2).addAttachment(new Armor(Armor.ENEMY_MEDIUM_ARM_1_CAP, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(3).addAttachment(new Armor(Armor.ENEMY_MEDIUM_ARM_2_CAP, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(4).addAttachment(new Armor(Armor.ENEMY_MEDIUM_ARM_1_BRACE, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(5).addAttachment(new Armor(Armor.ENEMY_MEDIUM_ARM_2_BRACE, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(6).addAttachment(new Armor(Armor.ENEMY_MEDIUM_LEFT_BODY_BRACE, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		slots.get(7).addAttachment(new Armor(Armor.ENEMY_MEDIUM_RIGHT_BODY_BRACE, Armor.COLOR_GOLD, Color.GRAY), originX, originY);
		
		//add default cockpits
		slots.get(8).addAttachment(new Cockpit(Cockpit.TYPE_WHITE), originX, originY);
		
		//add default weapons
		slots.get(9).addAttachment(new Weapon(Weapon.SEMICHARGER_ONE, faction), originX, originY);
		
		//add shields
		slots.get(10).addAttachment(new Shield(Shield.TYPE_WEAK, 75, 100), originX-15, originY-15);

 	}
	
	//Public Methods
	public void updatePosition(int deltaX, int deltaY) {
		originX += deltaX;
		originY += deltaY;
		
		if (deltaY != 0) {
			for (ChassisSlot cs: slots) {
				if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_ENGINE) {
					cs.getEngine().flame(deltaY > 0);
				}
			}
		}
		
		//UPDATE ATTACHMENT POSITIONS
		for (ChassisSlot cs: slots) {
			cs.updatePosition(deltaX, deltaY);
		}
		
		for (Polygon p: chassisPolygons) {
			p.translate(deltaX, deltaY);
		}
	}
	public void moveToDest() {
		
		//if the destination's been reached
		if (originX == destX && originY == destY) {
			currDest ++;
			destX = flightRoute.getNextDestX(currDest);
			destY = flightRoute.getNextDestY(currDest);
			
		} else {
			
			//establishes the speed in X and Y directions
			double distX = destX - originX;
			double distY = destY - originY;
			double percX = distX / (Math.abs(distX) + Math.abs(distY));
			double percY = distY / (Math.abs(distX) + Math.abs(distY));
			
			//limits the X and Y speeds to 50% of total speed
			if (percX > 0.5) { percX = 0.5; }
			if (percX < -0.5) { percX = -0.5; }
			if (percY > 0.5) { percY = 0.5; }
			if (percY < -0.5) { percY = -0.5; }
			
			//adds any remainder of movement from previous position update
			int delX = (int) Math.floor(remX + (speed * percX));
			int delY = (int) Math.floor(remY + (speed * percY)); 
			remX = (remX + (speed * percX)) - Math.floor(remX + (speed * percX));
			remY = (remY + (speed * percY)) - Math.floor(remY + (speed * percY));
			
			//if the delta will move the fighter to the destination, just move to destination
			if (distX > 0 && distX < delX) { delX = (int) distX; }
			if (distX < 0 && distX > delX) { delX = (int) distX; }
			if (distY > 0 && distY < delY) { delY = (int) distY; }
			if (distY < 0 && distY > delY) { delY = (int) distY; }
			
			updatePosition(delX, delY);
		}
		
	}	
	public void resetWeaponCooldowns() {
		for (ChassisSlot cs: slots) {
			if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_WEAPON) {
				if (!cs.getWeapon().isSpecial()) {
					cs.getWeapon().resetCooldown();
				}
			}
		}
	}
	public void decrementWeaponCooldowns() {
		for (ChassisSlot cs: slots) {
			if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_WEAPON) {
				cs.getWeapon().decrementCooldown();
			}
		}
	}
	public void damage(int damage) {
		for (ChassisSlot cs: slots) {
			if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_SHIELD) {
				if (cs.getShield().isUp()) {
					cs.getShield().damage(damage);
				} else {
					health -= damage;
				}
			}
		}
	}
	public ArrayList<Projectile> fireWeapons() {
		ArrayList<Projectile> projArr = new ArrayList<Projectile>();
		
		for (ChassisSlot cs: slots) {
			if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_WEAPON) {
				if (!cs.getWeapon().isOnCooldown() && !cs.getWeapon().isSpecial()) {
					projArr.addAll(cs.getWeapon().fire());
				}
			}
		}
		
		return projArr;
	}
	public ArrayList<Projectile> fireSpecial() {
		ArrayList<Projectile> projArr = new ArrayList<Projectile>();
		
		for (ChassisSlot cs: slots) {
			if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_WEAPON) {
				if (!cs.getWeapon().isOnCooldown() && cs.getWeapon().isSpecial()) {
					projArr.addAll(cs.getWeapon().fire());
				}
			}
		}
		
		return projArr;
	}
	public ArrayList<Explosion> getWeaponExplosions() {
		ArrayList<Explosion> exploArr = new ArrayList<Explosion>();
		
		for (ChassisSlot cs: slots) {
			if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_WEAPON) {
				if (cs.getWeapon().isFiring()) {
					exploArr.add(cs.getWeapon().explode(originX, originY));
				}
			}
		}
		
		return exploArr;
	}
	public ArrayList<Explosion> getSpecialExplosion() {
		ArrayList<Explosion> exploArr = new ArrayList<Explosion>();
		
		for (ChassisSlot cs: slots) {
			if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_WEAPON) {
				if (cs.getWeapon().isFiring()) {
					exploArr.add(cs.getWeapon().explode(originX, originY));
				}
			}
		}
		
		return exploArr;
	}
	public ArrayList<Explosion> explode(Random r) {
		ArrayList<Explosion> es = new ArrayList<Explosion>();
		
		switch (type) {
		case TYPE_PLAYER_FIGHTER:
		case TYPE_ENEMY_MEDIUM:
			es.add(new Explosion(originX + r.nextInt(64), originY + r.nextInt(64), 
					r.nextInt(2) + 3, r.nextInt(2) + 5, Color.RED, Explosion.TYPE_MECHANICAL, true));
			es.add(new Explosion(originX + r.nextInt(64), originY + r.nextInt(64), 
					r.nextInt(2) + 3, r.nextInt(2) + 5, Color.RED, Explosion.TYPE_MECHANICAL, true));
			es.add(new Explosion(originX + r.nextInt(64), originY + r.nextInt(64), 
					r.nextInt(2) + 3, r.nextInt(2) + 5, Color.RED, Explosion.TYPE_MECHANICAL, false));
			break;
		}
		
		return es;
	}	
	public Polygon getFighterOutline() {
		return chassisPolygons.get(0);
	}
	public Shield getShield() {
		for (ChassisSlot cs: slots) {
			if (cs.isOccupied() && cs.getAttachment().getType() == Attachment.TYPE_SHIELD) {
				return cs.getShield();
			}
		}
		return null;
	}
	public boolean isAtZeroHealth() {
		return (health <= 0);
	}
	public boolean isAtFinalPosition() {
		return (originX == flightRoute.getFinalX() && 
				originY == flightRoute.getFinalY());
	}
	public int getFaction() {
		return faction;
	}
	@Override
	public void drawMe(Graphics2D g) {
		
		//draw belly attachments
		for (ChassisSlot cs: slots) {
			if (cs.isUnderChassis() && cs.getAttachment().getType() != Attachment.TYPE_SHIELD) {
				cs.drawMe(g);
			}
		}
		
		//draw body
		for (int i = 0; i < chassisPolygons.size(); i ++) {
			g.setColor(polygonColors.get(i));
			g.fillPolygon(chassisPolygons.get(i));
		}

		//draw attachments
		for (ChassisSlot cs: slots) {
			if (!cs.isUnderChassis()) {
				cs.drawMe(g);
			}
		}
		
	}
	
}
