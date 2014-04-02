import java.awt.Graphics2D;

public class ChassisSlot {

	private Attachment currentAttachment;
	private int slotType;
	private int xOffset;
	private int yOffset;
	private boolean occupied;
	private boolean underChassis;
	
	public ChassisSlot (int slotType, int xOffset, int yOffset, boolean underChassis) {
		this.slotType = slotType;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.occupied = false;
		this.underChassis = underChassis;
	}
	
	public ChassisSlot (int slotType, boolean underChassis) {
		this.slotType = slotType;
		this.xOffset = 0;
		this.yOffset = 0;
		this.occupied = false;
		this.underChassis = underChassis;
	}
	
	public void addAttachment(Attachment a, int originX, int originY) {
		try {
			if (a.getType() != slotType) {
				throw new Exception("Attachment type (" + a.getType() + ") does " +
						"not match Chassis Slot type (" + slotType + ").");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		currentAttachment = a;
		a.setPosition(originX + xOffset, originY + yOffset);
		occupied = true;
			
	}
	
	public void removeAttachment() {
		currentAttachment = null;
		occupied = false;
	}
	
	public Attachment getAttachment() {
		return currentAttachment;
	}
	
	public void updatePosition(int deltaX, int deltaY) {
		if (occupied) {
			currentAttachment.updatePosition(deltaX, deltaY);
		}
	}
	
	public boolean isUnderChassis() {
		return underChassis;
	}

	public void drawMe(Graphics2D g) {
		if (occupied) {
			currentAttachment.drawMe(g);
		}
	}

	public Weapon getWeapon() {
		return (Weapon) currentAttachment;
	}
	
	public Engine getEngine() {
		return (Engine) currentAttachment;
	}
	
	public Shield getShield() {
		return (Shield) currentAttachment;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
}
