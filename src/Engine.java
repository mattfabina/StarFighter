import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

//TODO: IMPROVE ENGINE APPEARANCE
public class Engine implements Attachment {
	
	public static final int TYPE_SMALL = 0;
	public static final int TYPE_MEDIUM = 1;
	public static final int TYPE_LARGE = 2;
	
	private ArrayList<Polygon> engineShapes;
	private ArrayList<Color> shapeColors;
	
	private Random r;
	int width;
	int height;
	int xCoord;
	int yCoord;
	int animateCount;
	int flameSize;
	private boolean flaming;
	private boolean isFacingUp;
	
	/**Constructed according to the upper left/lower left corner of the engine.
	 * 
	 * @param type
	 * @param flaming
	 */
	public Engine(int type, boolean isFacingUp) {
		r = new Random();
		engineShapes = new ArrayList<Polygon>();
		shapeColors = new ArrayList<Color>();
		xCoord = 0;
		yCoord = 0;
		this.flaming = false;
		this.isFacingUp = isFacingUp;
		
		switch (type) {
		case TYPE_SMALL:
			width = 8;
			height = 4;
			shapeColors.add(Color.DARK_GRAY);
			shapeColors.add(Color.YELLOW);
			shapeColors.add(Color.RED);
			break;
		case TYPE_MEDIUM:
			width = 10;
			height = 6;
			shapeColors.add(Color.DARK_GRAY);
			shapeColors.add(Color.YELLOW);
			shapeColors.add(Color.RED);
			break;
		case TYPE_LARGE:
			width = 12;
			height = 8;
			shapeColors.add(Color.DARK_GRAY);
			shapeColors.add(Color.YELLOW);
			shapeColors.add(Color.RED);
			break;
		}
		
		buildEngine();
	}	

	private void buildEngine() {	
		//base
		Polygon p = new Polygon();
		if (!this.isFacingUp) {
			p.addPoint(xCoord		 , yCoord		  );
			p.addPoint(xCoord + width, yCoord		  );
			p.addPoint(xCoord + width, yCoord + height);
			p.addPoint(xCoord	     , yCoord + height);
			engineShapes.add(p);
		} else if (this.isFacingUp) {
			p.addPoint(xCoord		 , yCoord		  );
			p.addPoint(xCoord + width, yCoord		  );
			p.addPoint(xCoord + width, yCoord - height);
			p.addPoint(xCoord	     , yCoord - height);
			engineShapes.add(p);
		}
		
		engineShapes.add(p);
		engineShapes.add(p);
		animateFlames();
	}

	private void animateFlames() {
		engineShapes.remove(2);
		engineShapes.remove(1);
		
		if (!this.isFacingUp) {
			//large flames
			Polygon p = new Polygon();
			p.addPoint(xCoord, yCoord + height);
			p.addPoint(xCoord - 1, yCoord + height + 4);
			
			for (int i = 0; i < width - 1; i += 2) {
				p.addPoint(xCoord + i, yCoord + height + 6 + r.nextInt(8) + 1);
				p.addPoint(xCoord + i + 1, yCoord + height + 6);
			}
			
			p.addPoint(xCoord + width, yCoord + height + 6 + r.nextInt(8) + 1);
			p.addPoint(xCoord + width + 1, yCoord + height + 4);
			p.addPoint(xCoord + width, yCoord + height);
			engineShapes.add(p);
			
			//small flames
			p = new Polygon();
			p.addPoint(xCoord + 2, yCoord + height);
			p.addPoint(xCoord + 1, yCoord + height + 4);
			for (int i = 2; i < width - 3; i += 2) {
				p.addPoint(xCoord + i, yCoord + height + 2);
				p.addPoint(xCoord + i + 1, yCoord + height + r.nextInt(5) + 3);
			}
			p.addPoint(xCoord + width - 2, yCoord + height + 2);
			p.addPoint(xCoord + width - 1, yCoord + height + 4);
			p.addPoint(xCoord + width - 2, yCoord + height);
			engineShapes.add(p);
		}
		
		if (this.isFacingUp) {
			//large flames
			Polygon p = new Polygon();
			p.addPoint(xCoord, yCoord - height);
			p.addPoint(xCoord - 1, yCoord - height - 4);
			
			for (int i = 0; i < width - 1; i += 2) {
				p.addPoint(xCoord + i, yCoord - height - 6 - r.nextInt(8) - 1);
				p.addPoint(xCoord + i + 1, yCoord - height - 6);
			}
			
			p.addPoint(xCoord + width, yCoord - height - 6 - r.nextInt(8) - 1);
			p.addPoint(xCoord + width + 1, yCoord - height - 4);
			p.addPoint(xCoord + width, yCoord - height);
			engineShapes.add(p);
			
			//small flames
			p = new Polygon();
			p.addPoint(xCoord + 2, yCoord - height);
			p.addPoint(xCoord + 1, yCoord - height - 4);
			for (int i = 2; i < width - 3; i += 2) {
				p.addPoint(xCoord + i, yCoord - height - 2);
				p.addPoint(xCoord + i + 1, yCoord - height - r.nextInt(5) - 3);
			}
			p.addPoint(xCoord + width - 2, yCoord - height - 2);
			p.addPoint(xCoord + width - 1, yCoord - height - 4);
			p.addPoint(xCoord + width - 2, yCoord - height);
			engineShapes.add(p);
		}
		
	}
	
	@Override
	public void drawMe(Graphics2D g) {
		animateCount ++;
		if (animateCount % 5 == 0) {
			animateFlames();
		}
		if (animateCount == 10) {
			animateCount = 0;
			flaming = false;
		}
		
		for (int i = 0; i < engineShapes.size(); i ++) {
			if (i == 0 || flaming) {
				g.setColor(shapeColors.get(i));
				try {
					g.drawPolygon(engineShapes.get(i));
					g.fillPolygon(engineShapes.get(i));
				} catch (Exception e) {
					System.out.println("Silly try-catch activated.");
					g.drawPolygon(engineShapes.get(i));
					g.fillPolygon(engineShapes.get(i));
				}
			}
		}
	}

	@Override
	public int getType() {
		return Attachment.TYPE_ENGINE;
	}

	public void flame(boolean isFacingUp) {
		if (this.isFacingUp == isFacingUp) {
			animateCount = 0;
			animateFlames();
			flaming = true;
		}
	}
	
	@Override
	public void updatePosition(int deltaX, int deltaY) {
		for (Polygon p: engineShapes) {
			p.translate(deltaX, deltaY);
		}
		xCoord += deltaX;
		yCoord += deltaY;
	}

	@Override
	public void setPosition(int startX, int startY) {
		updatePosition(startX - xCoord, startY - yCoord);
	}

	
}
