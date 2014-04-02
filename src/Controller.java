import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controller extends Thread implements KeyListener, WindowListener, MouseListener {

	private boolean quitting = false;
	private Game game;
	private GUI gui;
	private int ticksPerSecond;
	private boolean upPressed;
	private boolean downPressed;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean spacePressed;	
	
	public Controller(Game game, int ticksPerSecond) {
		this.game = game;
		this.ticksPerSecond = ticksPerSecond;
	}

	public void run() {
		while (!quitting) {
			//tell program to do something, can be related to ticksPerSecond
			this.game.tick(1.0 / this.ticksPerSecond);
			gui.repaint();
			keyboardActions();
	        try {
	        	Thread.sleep(20);
	        } catch (InterruptedException e) {}
		}
	}
	
	private void keyboardActions() {
        if (upPressed) {
        	this.game.buttonPressed(KeyEvent.VK_UP);
        }
        if (downPressed) {
        	this.game.buttonPressed(KeyEvent.VK_DOWN);
        }
        if (rightPressed) {
        	this.game.buttonPressed(KeyEvent.VK_RIGHT);
        }
        if (leftPressed) {
        	this.game.buttonPressed(KeyEvent.VK_LEFT);
        }
        if (spacePressed) {
        	this.game.buttonPressed(KeyEvent.VK_SPACE);
        } else {
        	this.game.getPlayerFighter().resetWeaponCooldowns();
        }
	}
   
	public void setGUI(GUI gui) {
	   this.gui = gui;
	}
	
	///////////////////////////////////
	//KeyListener Interface Methods
	///////////////////////////////////
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
        	upPressed = true;
        	break;
        case KeyEvent.VK_DOWN:
        	downPressed = true;
        	break;
        case KeyEvent.VK_RIGHT:
            rightPressed = true;
            break;
        case KeyEvent.VK_LEFT:
        	leftPressed = true;
            break;
        case KeyEvent.VK_SPACE:
        	spacePressed = true;
        	break;
        case KeyEvent.VK_Q:
        	this.game.buttonPressed(KeyEvent.VK_Q);
	    	break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
        	upPressed = false;
        	break;
        case KeyEvent.VK_DOWN:
        	downPressed = false;
        	break;
        case KeyEvent.VK_RIGHT:
            rightPressed = false;
            break;
        case KeyEvent.VK_LEFT:
        	leftPressed = false;
            break;
        case KeyEvent.VK_SPACE:
        	spacePressed = false;
        	break;
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}
	
	///////////////////////////////////
	//WindowListener Interface Methods
	///////////////////////////////////
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
	      this.quitting = true; 		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	///////////////////////////////////
	//MouseListener Interface Methods
	///////////////////////////////////
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
}


