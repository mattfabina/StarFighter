import java.awt.Color;
import java.awt.Graphics2D;


public class Shield implements Attachment {

	public static final int TYPE_WEAK = 0;
	
	private int FULL_HEALTH;
	private int health;
	private int originX;
	private int originY;
	private int width;
	private int height;
	private int visibleCount;
	
	public Shield(int type, int width, int height) {
		this.width = width;
		this.height = height;
		this.visibleCount = 0;
		
		switch (type) {
		case TYPE_WEAK:
			FULL_HEALTH = 25;
			break;
		}
		
		health = FULL_HEALTH;
	}

	@Override
	public void drawMe(Graphics2D g) {
		if (visibleCount > 0) {
			double ratio = ((double) health) / ((double) FULL_HEALTH);
			int arg1 = (int) (-75 + (ratio * 255));
			int arg2 = (int) (-55 + (ratio * 255));
			int arg3 = (int) (0 + (ratio * 255));
			arg1 = Math.max(arg1, 0);
			arg2 = Math.max(arg2, 0);

//			System.out.println(health + " / " + FULL_HEALTH + " = " + ratio + 
//					": (" + arg1 + ", " + arg2 + ", " + arg3 + ")");
			visibleCount --;								// 115, 95, 40
			g.setColor(new Color(arg1, arg2, arg3));		// (180, 200, 255)
			g.fillOval(originX, originY, height, width);	// (220, 240, 255)
			g.setColor(Color.WHITE);
			g.drawOval(originX, originY, height, width);
		}
	}

	@Override
	public int getType() {
		return Attachment.TYPE_SHIELD;
	}

	@Override
	public void updatePosition(int deltaX, int deltaY) {
		originX += deltaX;
		originY += deltaY;
	}

	@Override
	public void setPosition(int startX, int startY) {
		originX = startX;
		originY = startY;
	}
	
	public void damage(int damage) {
		health -= damage;
		if (health > 0) {
			visibleCount = 25;
		} else {
			visibleCount = 0;
		}
	}
	
	public boolean isUp() {
		return (health > 0);
	}
	
}
