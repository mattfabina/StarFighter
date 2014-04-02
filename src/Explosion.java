import java.awt.*;
import java.util.Random;

//TODO IMPROVE LASER EXPLOSION
public class Explosion implements Drawable2D {

	public static final int TYPE_MECHANICAL = 0;
	public static final int TYPE_LASER_SHOT = 1;
	public static final int TYPE_LASER_DEFLECTION = 2;
	
	private boolean isGrowing;
	private boolean spawnsNew;
	private int centerX;
	private int centerY;
	private int size;
	private int initialSize;
	private int maxSize;
	private int type;
	private int height;
	private int width;
	private Color explosionColor;
	private Polygon explosionPolygonLarge;
	private Polygon explosionPolygonSmall;
		
	//Constructor
	public Explosion(int centerX, int centerY, int initialSize, int maxSize, 
			Color explosionColor, int type, boolean spawnsNew) {
		this.isGrowing = true;
		this.centerX = centerX;
		this.centerY = centerY;
		this.initialSize = initialSize;
		this.maxSize = maxSize;
		this.explosionColor = explosionColor;
		this.type = type;
		this.spawnsNew = spawnsNew;
		
		switch (type) {
		case TYPE_MECHANICAL:
			size = initialSize;
			updateMechanicalExplosionShape();
			break;
		case TYPE_LASER_SHOT:
			height = initialSize;
			width = initialSize;
			size = initialSize;
			break;
		}
		
	}
	
	//Private Methods
	private void updateGraphic() {
		switch (type) {
		case TYPE_MECHANICAL:
			centerY += 5;
			updateMechanicalExplosionShape();
			break;
		case TYPE_LASER_SHOT:
			height = size;
			width = size;
			break;
		}
		
	}
	private void updateMechanicalExplosionShape() {
		explosionPolygonLarge = new Polygon();
		explosionPolygonLarge.addPoint(centerX                    , centerY + ((size * 2) * -4));
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * 1) , centerY + ((size * 2) * -2));
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * 3) , centerY + ((size * 2) * -3));
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * 2) , centerY + ((size * 2) * -1));
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * 4) , centerY                    );
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * 2) , centerY + ((size * 2) * 1) );
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * 3) , centerY + ((size * 2) * 3) );
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * 1) , centerY + ((size * 2) * 2) );
		explosionPolygonLarge.addPoint(centerX                    , centerY + ((size * 2) * 4) );
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * -1), centerY + ((size * 2) * 2) );
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * -3), centerY + ((size * 2) * 3) );
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * -2), centerY + ((size * 2) * 1) );
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * -4), centerY                    );
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * -2), centerY + ((size * 2) * -1));
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * -3), centerY + ((size * 2) * -3));
		explosionPolygonLarge.addPoint(centerX + ((size * 2) * -1), centerY + ((size * 2) * -2));
		
		explosionPolygonSmall = new Polygon();
		explosionPolygonSmall.addPoint(centerX              , centerY + (size * -4));
		explosionPolygonSmall.addPoint(centerX + (size * 1) , centerY + (size * -2));
		explosionPolygonSmall.addPoint(centerX + (size * 3) , centerY + (size * -3));
		explosionPolygonSmall.addPoint(centerX + (size * 2) , centerY + (size * -1));
		explosionPolygonSmall.addPoint(centerX + (size * 4) , centerY              );
		explosionPolygonSmall.addPoint(centerX + (size * 2) , centerY + (size * 1) );
		explosionPolygonSmall.addPoint(centerX + (size * 3) , centerY + (size * 3) );
		explosionPolygonSmall.addPoint(centerX + (size * 1) , centerY + (size * 2) );
		explosionPolygonSmall.addPoint(centerX              , centerY + (size * 4) );
		explosionPolygonSmall.addPoint(centerX + (size * -1), centerY + (size * 2) );
		explosionPolygonSmall.addPoint(centerX + (size * -3), centerY + (size * 3) );
		explosionPolygonSmall.addPoint(centerX + (size * -2), centerY + (size * 1) );
		explosionPolygonSmall.addPoint(centerX + (size * -4), centerY       	   );
		explosionPolygonSmall.addPoint(centerX + (size * -2), centerY + (size * -1));
		explosionPolygonSmall.addPoint(centerX + (size * -3), centerY + (size * -3));
		explosionPolygonSmall.addPoint(centerX + (size * -1), centerY + (size * -2));
	}
	
	//Public Methods
	public void tick() {
		if (this.isGrowing) {

			if (size < maxSize) {
				size ++;
				updateGraphic();
			} else {
				isGrowing = false;
				size --;
				updateGraphic();
			}
		} else {
			size --;
			updateGraphic();
		}
	}
	public Explosion nextExplosion(Random r) {
		return new Explosion(centerX + r.nextInt(20), centerY + r.nextInt(20), 
				initialSize - 2, maxSize - 2, explosionColor, type, false);
	}
	public boolean isDone() {
		switch (type) {
			case TYPE_MECHANICAL:
				return (size == 0);
			case TYPE_LASER_SHOT:
				return (size == maxSize);
		}
		return true;
	}
	public boolean spawnsNew() {
		return spawnsNew;
	}

	//Interface Implementation Methods
	@Override
	public void drawMe(Graphics2D g) {
		if (type == TYPE_MECHANICAL) {
			g.setColor(explosionColor);
			g.fillPolygon(explosionPolygonLarge);
			g.setColor(Color.YELLOW);
			g.fillPolygon(explosionPolygonSmall);
		} else if (type == TYPE_LASER_SHOT) {
			g.setColor(explosionColor);
			g.drawOval(centerX - (size / 2), centerY - (size / 2), width, height);
		}
	}

		
}
