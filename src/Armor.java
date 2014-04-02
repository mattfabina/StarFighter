import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;


public class Armor implements Attachment {
	
	//shape constants
	//player fighter
	public static final int PLAYER_FIGHTER_BODY_BRACE = 1;
	public static final int PLAYER_FIGHTER_ENGINE_1_CAP = 2;
	public static final int PLAYER_FIGHTER_ENGINE_2_CAP = 3;
	public static final int PLAYER_FIGHTER_ENGINE_3_CAP = 4;
	public static final int PLAYER_FIGHTER_ARM_1_CAP = 5;
	public static final int PLAYER_FIGHTER_ARM_2_CAP = 6;
	public static final int PLAYER_FIGHTER_ARM_1_BAND = 7;
	public static final int PLAYER_FIGHTER_ARM_2_BAND = 8;

	//enemy fighter medium
	public static final int ENEMY_MEDIUM_ARM_1_CAP = 11;
	public static final int ENEMY_MEDIUM_ARM_2_CAP = 12;
	public static final int ENEMY_MEDIUM_ARM_1_BRACE = 13;
	public static final int ENEMY_MEDIUM_ARM_2_BRACE = 14;
	public static final int ENEMY_MEDIUM_LEFT_BODY_BRACE = 15;
	public static final int ENEMY_MEDIUM_RIGHT_BODY_BRACE = 16;
	
	//color constants
	public static final Color COLOR_GOLD = Color.YELLOW;
	
	private ArrayList<Polygon> armorPolygons;
	private ArrayList<Color> polygonColors;
	
	//constructor
	public Armor(int armorPolygon, Color armorColor, Color fighterColor) {
		this.armorPolygons = new ArrayList<Polygon>();
		this.polygonColors = new ArrayList<Color>();
		
		switch (armorPolygon) {
		case PLAYER_FIGHTER_BODY_BRACE:
			armorPolygons.addAll(playerFighterBodyBraceBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		case PLAYER_FIGHTER_ENGINE_1_CAP:
			armorPolygons.add(playerFighterEngine1CapBuilder());
			polygonColors.add(armorColor);
			break;
		case PLAYER_FIGHTER_ENGINE_2_CAP:
			armorPolygons.add(playerFighterEngine2CapBuilder());
			polygonColors.add(armorColor);
			break;
		case PLAYER_FIGHTER_ENGINE_3_CAP:
			armorPolygons.add(playerFighterEngine3CapBuilder());
			polygonColors.add(armorColor);
			break;
		case PLAYER_FIGHTER_ARM_1_CAP:
			armorPolygons.addAll(playerFighterArm1CapBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		case PLAYER_FIGHTER_ARM_2_CAP:
			armorPolygons.addAll(playerFighterArm2CapBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		case PLAYER_FIGHTER_ARM_1_BAND:
			armorPolygons.add(playerFighterArm1BandBuilder());
			polygonColors.add(armorColor);
			break;
		case PLAYER_FIGHTER_ARM_2_BAND:
			armorPolygons.add(playerFighterArm2BandBuilder());
			polygonColors.add(armorColor);
			break;
		case ENEMY_MEDIUM_ARM_1_CAP:
			armorPolygons.addAll(enemyMediumArm1CapBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		case ENEMY_MEDIUM_ARM_2_CAP:
			armorPolygons.addAll(enemyMediumArm2CapBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		case ENEMY_MEDIUM_ARM_1_BRACE:
			armorPolygons.addAll(enemyMediumArm1BraceBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		case ENEMY_MEDIUM_ARM_2_BRACE:
			armorPolygons.addAll(enemyMediumArm2BraceBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		case ENEMY_MEDIUM_LEFT_BODY_BRACE:
			armorPolygons.add(enemyMediumLeftBodyBraceBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		case ENEMY_MEDIUM_RIGHT_BODY_BRACE:
			armorPolygons.add(enemyMediumRightBodyBraceBuilder());
			polygonColors.add(armorColor);
			polygonColors.add(fighterColor);
			break;
		}	
		
	}
	
	//public methods
	@Override
	public void setPosition(int startX, int startY) {
		updatePosition(startX, startY);		
	}
	@Override
	public void updatePosition(int deltaX, int deltaY) {
		for (int i = 0; i < armorPolygons.size(); i ++) {
			armorPolygons.get(i).translate(deltaX, deltaY);
		}
	}
	@Override
	public int getType() {
		return Attachment.TYPE_ARMOR;
	}
	@Override
	public void drawMe(Graphics2D g) {
		for (int i = 0; i < armorPolygons.size(); i ++) {
			g.setColor(polygonColors.get(i));
			g.fillPolygon(armorPolygons.get(i));
		}
	}
	
	//polygon builders
	//player fighter
	private ArrayList<Polygon> playerFighterBodyBraceBuilder() {
		ArrayList<Polygon> ps = new ArrayList<Polygon>();
		
		Polygon p = new Polygon();
		
		p.addPoint(18, 48);
		p.addPoint(22, 44);
		p.addPoint(22, 16);
		p.addPoint(24, 8);
		p.addPoint(28, 2);
		p.addPoint(30, 0);
		p.addPoint(34, 0);
		p.addPoint(36, 2);
		p.addPoint(40, 8);
		p.addPoint(42, 16);
		p.addPoint(42, 44);
		p.addPoint(46, 48);
		p.addPoint(46, 52);
		p.addPoint(42, 48);
		p.addPoint(40, 52);
		p.addPoint(34, 54);
		p.addPoint(32, 52);
		p.addPoint(30, 54);
		p.addPoint(24, 52);
		p.addPoint(22, 48);
		p.addPoint(18, 52);
	
		ps.add(p);
		
		p = new Polygon();
		
		p.addPoint(24, 16);
		p.addPoint(26, 8);
		p.addPoint(30, 2);
		p.addPoint(32, 6);
		p.addPoint(34, 2);
		p.addPoint(38, 8);
		p.addPoint(40, 16);
		p.addPoint(40, 44);
		p.addPoint(38, 50);
		p.addPoint(32, 48);
		p.addPoint(26, 50);
		p.addPoint(24, 44);
		
		ps.add(p);
		
		return ps;
	}
	private Polygon playerFighterEngine1CapBuilder() {
		Polygon p = new Polygon();
		
		p.addPoint(0, 68);
		p.addPoint(2, 68);
		p.addPoint(4, 70);
		p.addPoint(4, 76);
		p.addPoint(6, 78);
		p.addPoint(8, 76);
		p.addPoint(8, 70);
		p.addPoint(10, 68);
		p.addPoint(12, 68);
		p.addPoint(10, 80);
		p.addPoint(2, 80);
		
		return p;
	}
	private Polygon playerFighterEngine2CapBuilder() {
		Polygon p = new Polygon();
		
		p.addPoint(52, 68);
		p.addPoint(54, 68);
		p.addPoint(56, 70);
		p.addPoint(56, 76);
		p.addPoint(58, 78);
		p.addPoint(60, 76);
		p.addPoint(60, 70);
		p.addPoint(62, 68);
		p.addPoint(64, 68);
		p.addPoint(62, 80);
		p.addPoint(54, 80);
		
		return p;
	}
	private Polygon playerFighterEngine3CapBuilder() {
		Polygon p = new Polygon();
		
		p.addPoint(26, 58);
		p.addPoint(28, 58);
		p.addPoint(30, 60);
		p.addPoint(30, 62);
		p.addPoint(32, 64);
		p.addPoint(34, 62);
		p.addPoint(34, 60);
		p.addPoint(36, 58);
		p.addPoint(38, 58);
		p.addPoint(36, 66);
		p.addPoint(28, 66);
		
		return p;
	}
	private ArrayList<Polygon> playerFighterArm1CapBuilder() {
		ArrayList<Polygon> ps = new ArrayList<Polygon>();
		
		Polygon p = new Polygon();
		p.addPoint(0, 42);
		p.addPoint(2, 30);
		p.addPoint(10, 30);
		p.addPoint(12, 42);
		p.addPoint(10, 46);
		p.addPoint(8, 52);
		p.addPoint(6, 54);
		p.addPoint(4, 52);
		p.addPoint(2, 46);
		
		ps.add(p);
		
		p = new Polygon();
		
		p.addPoint(2, 40);
		p.addPoint(4, 32);
		p.addPoint(8, 32);
		p.addPoint(10, 40);
		p.addPoint(6, 50);
		
		ps.add(p);
		
		return ps;
	}
	private ArrayList<Polygon> playerFighterArm2CapBuilder() {
		ArrayList<Polygon> ps = new ArrayList<Polygon>();
		
		Polygon p = new Polygon();
		p.addPoint(52, 42);
		p.addPoint(54, 30);
		p.addPoint(62, 30);
		p.addPoint(64, 42);
		p.addPoint(62, 46);
		p.addPoint(60, 52);
		p.addPoint(58, 54);
		p.addPoint(56, 52);
		
		ps.add(p);
		
		p = new Polygon();
		
		p.addPoint(54, 40);
		p.addPoint(56, 32);
		p.addPoint(60, 32);
		p.addPoint(62, 40);
		p.addPoint(58, 50);

		ps.add(p);
		
		return ps;
	}
	private Polygon playerFighterArm1BandBuilder() {
		Polygon p = new Polygon();
		
		p.addPoint(0, 54);
		p.addPoint(2, 56);
		p.addPoint(6, 58);
		p.addPoint(10, 56);
		p.addPoint(12, 56);
		p.addPoint(14, 54);
		p.addPoint(14, 56);
		p.addPoint(12, 60);
		p.addPoint(6, 66);
		p.addPoint(10, 58);
		p.addPoint(8, 60);
		p.addPoint(4, 60);
		p.addPoint(0, 58);
		
		return p;
	}
	private Polygon playerFighterArm2BandBuilder() {
		Polygon p = new Polygon();
		
		p.addPoint(50, 54);
		p.addPoint(52, 56);
		p.addPoint(54, 56);
		p.addPoint(58, 58);
		p.addPoint(62, 56);
		p.addPoint(64, 54);
		p.addPoint(64, 58);
		p.addPoint(60, 60);
		p.addPoint(56, 60);
		p.addPoint(54, 58);
		p.addPoint(58, 66);
		p.addPoint(52, 60);
		p.addPoint(50, 56);
		
		return p;
	}
	
	//enemy medium
	private ArrayList<Polygon> enemyMediumArm1CapBuilder() {
		ArrayList<Polygon> ps = new ArrayList<Polygon>();

		Polygon p = new Polygon();
		
		p.addPoint(6, 40);
		p.addPoint(8, 36);
		p.addPoint(10, 34);
		p.addPoint(14, 32);
		p.addPoint(16, 32);
		p.addPoint(20, 34);
		p.addPoint(22, 36);
		p.addPoint(24, 40);
		p.addPoint(28, 44);
		p.addPoint(22, 44);
		p.addPoint(26, 48);
		p.addPoint(20, 46);
		p.addPoint(24, 50);
		p.addPoint(18, 48);
		p.addPoint(20, 52);
		p.addPoint(14, 48);
		p.addPoint(16, 52);
		p.addPoint(10, 48);
		p.addPoint(12, 52);
		p.addPoint(8, 48);
		p.addPoint(6, 44);
		
		ps.add(p);
	
		p = new Polygon();
		
		p.addPoint(8, 42);
		p.addPoint(10, 38);
		p.addPoint(12, 36);
		p.addPoint(16, 34);
		p.addPoint(20, 36);
		p.addPoint(22, 38);
		p.addPoint(24, 42);
		p.addPoint(20, 44);
		p.addPoint(18, 46);
		p.addPoint(12, 46);
		p.addPoint(8, 44);
		
		ps.add(p);
		
		return ps;
	}
	private ArrayList<Polygon> enemyMediumArm2CapBuilder() {
		ArrayList<Polygon> ps = new ArrayList<Polygon>();
		
		Polygon p = new Polygon();
		
		p.addPoint(44, 44);
		p.addPoint(48, 40);
		p.addPoint(50, 36);
		p.addPoint(52, 34);
		p.addPoint(56, 32);
		p.addPoint(58, 32);
		p.addPoint(62, 34);
		p.addPoint(64, 36);
		p.addPoint(66, 40);
		p.addPoint(66, 44);
		p.addPoint(64, 48);
		p.addPoint(60, 52);
		p.addPoint(62, 48);
		p.addPoint(56, 52);
		p.addPoint(58, 48);
		p.addPoint(52, 52);
		p.addPoint(54, 48);
		p.addPoint(48, 50);
		p.addPoint(50, 46);
		p.addPoint(46, 48);
		p.addPoint(50, 44);
		
		ps.add(p);
		
		p = new Polygon();

		p.addPoint(48, 42);
		p.addPoint(50, 38);
		p.addPoint(52, 36);
		p.addPoint(56, 34);
		p.addPoint(60, 36);
		p.addPoint(62, 38);
		p.addPoint(64, 42);
		p.addPoint(64, 44);
		p.addPoint(60, 46);
		p.addPoint(54, 46);
		p.addPoint(52, 44);
		
		ps.add(p);
		
		return ps;
	}
	private ArrayList<Polygon> enemyMediumArm1BraceBuilder() {
		ArrayList<Polygon> ps = new ArrayList<Polygon>();
		
		Polygon p = new Polygon();
		
		p.addPoint(2, 22);
		p.addPoint(4, 14);
		p.addPoint(6, 8);
		p.addPoint(12, 16);
		p.addPoint(10, 20);
		p.addPoint(10, 24);
		p.addPoint(12, 28);
		p.addPoint(14, 30);
		p.addPoint(10, 32);
		p.addPoint(6, 36);
		p.addPoint(4, 40);
		p.addPoint(4, 36);
		p.addPoint(2, 30);
		
		ps.add(p);
		
		p = new Polygon();
		
		p.addPoint(4, 22);
		p.addPoint(6, 12);
		p.addPoint(10, 16);
		p.addPoint(8, 20);
		p.addPoint(8, 24);
		p.addPoint(10, 28);
		p.addPoint(12, 30);
		p.addPoint(8, 32);
		p.addPoint(6, 34);
		p.addPoint(4, 30);
		
		ps.add(p);
		
		return ps;
	}
	private ArrayList<Polygon> enemyMediumArm2BraceBuilder() {
		ArrayList<Polygon> ps = new ArrayList<Polygon>();
		
		Polygon p = new Polygon();

		p.addPoint(58, 30);
		p.addPoint(60, 28);
		p.addPoint(62, 24);
		p.addPoint(62, 20);
		p.addPoint(60, 16);
		p.addPoint(66, 8);
		p.addPoint(68, 14);
		p.addPoint(70, 22);
		p.addPoint(70, 30);
		p.addPoint(68, 36);
		p.addPoint(68, 40);
		p.addPoint(66, 36);
		p.addPoint(62, 32);
		
		ps.add(p);
		
		p = new Polygon();

		p.addPoint(60, 30);
		p.addPoint(62, 28);
		p.addPoint(64, 24);
		p.addPoint(64, 20);
		p.addPoint(62, 16);
		p.addPoint(66, 12);
		p.addPoint(68, 22);
		p.addPoint(68, 30);
		p.addPoint(66, 34);
		p.addPoint(64, 32);
		
		ps.add(p);
		
		return ps;
	}
	private Polygon enemyMediumLeftBodyBraceBuilder() {
		Polygon p = new Polygon();
		
//		p.addPoint(8, 6);
//		p.addPoint(10, 4);
//		p.addPoint(12, 6);
//		p.addPoint(14, 0);
//		p.addPoint(16, 6);
//		p.addPoint(18, 0);
//		p.addPoint(20, 6);
//		p.addPoint(22, 4);
//		p.addPoint(24, 6);
//		p.addPoint(28, 8);
//		p.addPoint(22, 10);
//		p.addPoint(18, 12);
//		p.addPoint(16, 14);
//		p.addPoint(14, 18);
//		p.addPoint(14, 14);
		p.addPoint(10, 4);
		p.addPoint(12, 2);
		p.addPoint(14, 4);
		p.addPoint(16, 0);
		p.addPoint(18, 4);
		p.addPoint(20, 0);
		p.addPoint(22, 4);
		p.addPoint(24, 6);
		p.addPoint(28, 8);
		p.addPoint(22, 10);
		p.addPoint(18, 12);
		p.addPoint(16, 14);
		p.addPoint(16, 12);
		
		return p;
	}
	private Polygon enemyMediumRightBodyBraceBuilder() {
		Polygon p = new Polygon();
		
		p.addPoint(44, 8);
		p.addPoint(48, 6);
		p.addPoint(50, 4);
		p.addPoint(52, 6);
		p.addPoint(54, 0);
		p.addPoint(56, 6);
		p.addPoint(58, 0);
		p.addPoint(60, 6);
		p.addPoint(62, 4);
		p.addPoint(64, 6);
		p.addPoint(58, 14);
		p.addPoint(58, 18);
		p.addPoint(56, 14);
		p.addPoint(54, 12);
		p.addPoint(50, 10);
		
		return p;
	}
	
}
