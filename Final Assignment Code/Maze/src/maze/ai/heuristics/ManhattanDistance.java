package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeExplorer;

//This is using the ManhattanDistance as a heuristic
public class ManhattanDistance implements BestFirstHeuristic<MazeExplorer> {
    public int getHeuristic(MazeExplorer node, MazeExplorer goal) {
    	int result;
    	result=node.getLocation().getManhattanDist(goal.getLocation()); //This actually uses the Manhattan Distance 
		return result; 
}
}
