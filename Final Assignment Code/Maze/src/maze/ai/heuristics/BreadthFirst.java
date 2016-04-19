package maze.ai.heuristics;

import maze.ai.core.BestFirstHeuristic;
import maze.core.MazeExplorer;

//This is Breadth-First Search, as the heuristic is always 0
public class BreadthFirst implements BestFirstHeuristic<MazeExplorer> {
    public int getHeuristic(MazeExplorer node, MazeExplorer goal) {
        return 0;
    }
}
