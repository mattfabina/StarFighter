import java.awt.*;
import java.util.ArrayList;

//TODO MAKE PURPLE COLOR
public class Debris implements Drawable2D {

	public static final int TYPE_METALLIC = 0;		//armor
	public static final int TYPE_ESCAPE_POD = 1;	//cockpit
	public static final int TYPE_TECHNOLOGIC = 2;	//device
	public static final int TYPE_MECHANICAL = 3;	//engine
	public static final int TYPE_ARMAMENT = 4;		//weapons
	
	public static final Color QUALITY_POOR = Color.GRAY;		//gray
	public static final Color QUALITY_STANDARD = Color.WHITE;	//white
	public static final Color QUALITY_COMMON = Color.GREEN; 	//green
	public static final Color QUALITY_SUPERIOR = Color.BLUE;	//blue
	//public static final Color QUALITY_EPIC = Color.PURPLE;	//purple
	public static final Color QUALITY_LEGENDARY = Color.ORANGE;	//orange
	
	private ArrayList<Polygon> debrisShapes;
	private ArrayList<Color> debrisColors;
	private int centerXCoord;
	private int centerYCoord;
	private int type;
	private int quality;
	
	public Debris(int spawnX, int spawnY, int type, int quality) {
		this.centerXCoord = spawnX;
		this.centerYCoord = spawnY;
		this.type = type;
		this.quality = quality;
		
		switch (type) {
		case TYPE_METALLIC:
			debrisShapes = constructMetallic();
			break;
		case TYPE_ESCAPE_POD:
			debrisShapes = constructEscapePod();
			break;
		case TYPE_TECHNOLOGIC:
			debrisShapes = constructTechnologic();
			break;
		case TYPE_MECHANICAL:
			debrisShapes = constructMechanical();
			break;
		case TYPE_ARMAMENT:
			debrisShapes = constructArmament();
			break;
		}
	}
	
	private ArrayList<Polygon> constructMetallic() {
		return null;
	}

	private ArrayList<Polygon> constructEscapePod() {
		return null;
	}
	
	private ArrayList<Polygon> constructTechnologic() {
		return null;
	}
	
	private ArrayList<Polygon> constructMechanical() {
		return null;
	}
	
	private ArrayList<Polygon> constructArmament() {
		return null;
	}
	
	@Override
	public void drawMe(Graphics2D g) {
		for(int i = 0; i < debrisColors.size(); i++) {
			g.setColor(debrisColors.get(i));
			g.fillPolygon(debrisShapes.get(i));
		}
	}
	
}
