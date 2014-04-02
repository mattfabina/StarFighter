import java.awt.Color;
import java.awt.Graphics2D;


public class Cockpit implements Attachment {

	public static final int TYPE_WHITE = 0;
	public static final int TYPE_CYAN = 1;
	
	private Color cockpitColor;
	private int width;
	private int height;
	private int xCoord;
	private int yCoord;
	
	public Cockpit(int type) {		
		switch (type) {
		case TYPE_WHITE:
			width = 4;
			height = 9;
			cockpitColor = Color.WHITE;
			break;
		case TYPE_CYAN:
			width = 6;
			height = 24;
			cockpitColor = Color.CYAN;
		}
	}	

	@Override
	public void drawMe(Graphics2D g) {
		//draw engines (round rectangles)
		g.setColor(cockpitColor);
		g.fillOval(xCoord - (width / 2), yCoord - (height / 2), 
				width, height);
	}

	@Override
	public int getType() {
		return Attachment.TYPE_COCKPIT;
	}

	@Override
	public void updatePosition(int deltaX, int deltaY) {
		xCoord += deltaX;
		yCoord += deltaY;
	}

	@Override
	public void setPosition(int startX, int startY) {
		xCoord = startX;
		yCoord = startY;
	}
	
}
