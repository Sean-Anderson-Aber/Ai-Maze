package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeExplorer;

//This is using the ManhattanDistance as a heuristic
public class StraightLineDistance implements BestFirstHeuristic<MazeExplorer> {
    public int getHeuristic(MazeExplorer node, MazeExplorer goal) { //This method uses pythagoras therom to create the straight line heuristic.
    	int result;
    	int finalX= (int) (goal.getLocation().X() - node.getLocation().X());
    	int finalY= (int) (goal.getLocation().Y() - node.getLocation().Y());
    	result=(int) Math.round(Math.sqrt((finalX*finalX) + (finalY*finalY)));
    	return Math.abs(result);
    			
}
}
