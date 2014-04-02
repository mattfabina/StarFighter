import java.util.ArrayList;


public class LevelOperator {
	
	private ArrayList<Route> levelRoutes;
	
	private int currentLevel;
	private int currentRoute;
	private int currentFighter;
	private int ticksPassed;
	private boolean levelDone;
	
	public LevelOperator() {
		levelRoutes = new ArrayList<Route>();
		this.currentLevel = 0;
		this.currentRoute = 0;
		this.currentFighter = 0;
		this.ticksPassed = 0;
		this.levelDone = false;
	}
	
	public boolean incrementLevelState() {
		ticksPassed ++;
		boolean readyForSpawn = false;
		//if route is done spawning
		if (levelRoutes.get(currentRoute).isDone() && !levelDone) {
			if (currentRoute + 1 > levelRoutes.size() - 1) {
				currentFighter = 0;
				currentRoute = 0;
				levelDone = true;
			} else {
				currentFighter = 0;
				currentRoute ++;
			}
			
			return false;
		}
		
		//if route is ready to spawn next fighter
		if (ticksPassed == levelRoutes.get(currentRoute).getSpawnTicks()) {
			readyForSpawn = true;
			ticksPassed = 0;
		}
		return readyForSpawn;
	}
	
	public void nextLevel() {
		this.currentLevel ++;
		levelRoutes.clear();
		
		//constructs the level
		switch (currentLevel) {
		case 1:
			int[] enemy = {Fighter.TYPE_ENEMY_MEDIUM};
			int[] player = {Fighter.TYPE_PLAYER_FIGHTER};
			levelRoutes.add(new Route(Route.UL_MR_NONE, Route.UL_MR_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UR_ML_NONE, Route.UR_ML_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UL_MR_NONE, Route.UL_MR_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UR_ML_NONE, Route.UR_ML_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UL_MR_NONE, Route.UL_MR_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UR_ML_NONE, Route.UR_ML_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UL_MR_NONE, Route.UL_MR_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UR_ML_NONE, Route.UR_ML_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UL_MR_NONE, Route.UL_MR_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
			levelRoutes.add(new Route(Route.UR_ML_NONE, Route.UR_ML_NONE_SPAWN, Route.TYPE_7M, Game.FACTION_ENEMY));
//			levelRoutes.add(new Route(Route.BOTTOM_CENTER, 20, 25, 875, 400, 725, player, Game.FACTION_PLAYER));
//			levelRoutes.add(new Route(Route.TOP_CENTER, 20, 25, 875, 25, 325, enemy, Game.FACTION_ENEMY));
			break;
		case 2:	
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			break;
		case 3:
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			levelRoutes.add(null);
			break;
		}
	}
	
	public boolean isLevelDone() {return this.levelDone;}
	
	public int getCurrentLevel() {return this.currentLevel;}
	
	public Route getCurrentRoute() {return this.levelRoutes.get(currentRoute);}

	public Fighter nextFighter() {
		Fighter nextFighter = new Fighter(levelRoutes.get(currentRoute).getNextFighter(currentFighter),
				levelRoutes.get(currentRoute), levelRoutes.get(currentRoute).getFaction());
		currentFighter ++;
		return nextFighter;
	}
	
}
