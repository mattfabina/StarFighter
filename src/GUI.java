import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GUI extends JPanel {
	
	private Controller controller;
	private JFrame frame;
	private int width;
	private int height;
	private Game game;
	private int frameLeftX;
	private int frameRightX;
	private int frameTopY;
	private int frameBottomY;
	private Graphics2D g;
	
	/**
	 * Creates a window for displaying program.
	 * 
	 * @param title Name to be printed in the window's title bar.
	 */
	public GUI(Game game, Controller controller, int width, int height, int frameWidth) {
		//constructs into superclass
		super();
		this.game = game;
		//initiates the java frame
		this.frame = new JFrame(Game.TITLE);
		//constructs a container, and puts the content pane of the java frame within
		Container container = frame.getContentPane();
		//adds the GUI (within the JPanel superclass to the container)
		container.add(this);
		//initiates the rest
		this.controller = controller;
		frame.addKeyListener(this.controller);
		frame.addWindowListener(this.controller);
		frame.setResizable(false);
		this.width = width;
		this.height = height;
		this.frameLeftX = frameWidth;
		this.frameRightX = width - frameWidth;
		this.frameTopY = frameWidth;
		this.frameBottomY = height - frameWidth;
	}
	
	/**
	 * Closes the window and allows the main program to exit cleanly.
	 * If a program ends without calling this method, the image window, 
	 * although able to hide, will remain in memory indefinitely.
	 */
	public void close() {
		frame.dispose();
	}

	/**
	 * Paints the window contents using the provided <tt>Graphics</tt> object.
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		this.update(graphics);
	}

	/**
	 * The actual painting code is here instead of <tt>paintComponent()</tt>.
	 */
	public void update(Graphics graphicSuper) {
		
		//creates new 2D graphics object using old graphics object
		g = (Graphics2D)graphicSuper;

		//turns anti-aliasing on
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			   
		//fills the frame
		g.setColor(new Color(100, 0, 225));
		g.fillRect(0, 0, width, height);
	   
		//if program is over
		if (game.isDone()) {
			
	   	//else another game state
		} else {
			//fills the inner interface
			g.setColor(Color.BLACK);
			g.fillRect(frameLeftX, frameTopY, 
					frameRightX - frameLeftX, frameBottomY - frameTopY);
			
//			g.setColor(new Color(180, 200, 255));
//			g.fillOval(width / 2 - 55, height / 2 - 10, 100, 120);
			
			//draw all the drawables
			for (Drawable2D d: game.getDrawable2DArray()) {
				d.drawMe(g);
			}
		}
		
	}
	
	/**
	 * Displays the window.
	 */
	public void showMe() {
		Dimension dimension = new Dimension(width, height);
		// Set the size of the contents of the window so that we get the right size
		Container container = frame.getContentPane();
		container.setMaximumSize(dimension);
		container.setPreferredSize(dimension);
		container.setMinimumSize(dimension);
		// And now we can show the window
		frame.pack();
		frame.setVisible(true);
		this.repaint();
	}
	
	/**
    * Places the window. The upper left coordinate of the window will be
    * placed at position (x,y) with respect to the upper left corner of the
    * screen.
    */
   public void setLocation(int x, int y) {
      frame.setLocation(x, y);
   }
   
   public int getWidth() {return this.width;}
   public int getHeight() {return this.height;}
   public int getFrameLeftX() {return this.frameLeftX;}
   public int getFrameRightX() {return this.frameRightX;}
   public int getFrameTopY() {return this.frameTopY;}
   public int getFrameBottomY() {return this.frameBottomY;}
   
}
