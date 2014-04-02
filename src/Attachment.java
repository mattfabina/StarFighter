import java.awt.Graphics2D;

public interface Attachment extends Drawable2D {
	
	public static final int TYPE_ARMOR = 0;
	public static final int TYPE_COCKPIT = 1;
	public static final int TYPE_SHIELD = 2;
	public static final int TYPE_ENGINE = 3;
	public static final int TYPE_WEAPON = 4;
		
	public int getType();
	
	public void updatePosition(int deltaX, int deltaY);
	
	public void setPosition(int startX, int startY);
	
}
