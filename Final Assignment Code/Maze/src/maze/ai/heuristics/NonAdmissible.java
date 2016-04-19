package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeExplorer;

//This is using the ManhattanDistance as a heuristic
public class NonAdmissible implements BestFirstHeuristic<MazeExplorer> {
    public int getHeuristic(MazeExplorer node, MazeExplorer goal) {
    	int mazeSize;
    	mazeSize=node.getMaze().getXSize()*node.getMaze().getYSize(); //Assigns mazeSize to = the size of the maze.
    	boolean hasTreasure= node.getMaze().isTreasure(node.getLocation());
    	if(hasTreasure){
    		int treasureRemaining=node.getMaze().getTreasures().size()-1;
    		return node.getLocation().getManhattanDist(goal.getLocation())-(mazeSize/treasureRemaining);
    	}
    	else {
    		return node.getLocation().getManhattanDist(goal.getLocation()); 
    	}
    	
}
}
