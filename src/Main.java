
public class Main {
	
	public static void main(String[] args) {
		//Construct the game
		Game game = new Game(); 
		
		//Construct up the threads that will run the program and continuously display
		//  it in the window (which also gets set up).  
		//This is the MVC design pattern. The model part is the
		//  Program object, the view part is the GUI object,
		//  and the controller part is the Controller object.
		Controller controller = new Controller(game, 10);
		
		GUI gui = new GUI(game, controller, 900, 750, 25);
		controller.setGUI(gui); 
		game.setGui(gui);
		//The controller and gui reference each other, but the gui was not yet
		//  set up when the controller was initiated.
		
		//Now that the thread has been set up, we can start it and show the gui
		controller.start();
		gui.showMe();
		gui.setLocation(0, 0);
		
		//Once the thread finishes, we close the window and the program terminates.
		try {
			controller.join();
		} catch (InterruptedException e) {}
		    gui.close();
	}
	
}
