package maze.core;

import static org.junit.Assert.*;
import maze.ai.core.BestFirstSearcher;

import org.junit.Test;

public class MazeTest {
	final static int NUM_TESTS = 100;
	final static int WIDTH = 10, HEIGHT = 15;

	@Test
	public void testNoTreasure() {
		for (int i = 0; i < NUM_TESTS; ++i) {
			Maze m = new Maze(WIDTH, HEIGHT);
			m.makeMaze(new MazeCell(0, 0), new MazeCell(WIDTH - 1, HEIGHT - 1), 0, 1);
			BestFirstSearcher<MazeExplorer> searcher = new BestFirstSearcher<MazeExplorer>(new maze.ai.heuristics.BreadthFirst());
			MazeExplorer endNode = new MazeExplorer(m, m.getEnd());
			searcher.solve(new MazeExplorer(m, m.getStart()), endNode);
			assertTrue(searcher.success());
			MazePath path = new MazePath(searcher, m);
			assertTrue(path.solvesMaze(m));
		}		
	}

	@Test
	public void testMany() {
		for (int i = 0; i < NUM_TESTS; ++i) {
			Maze m = new Maze(WIDTH, HEIGHT);
			m.makeMaze(new MazeCell(0, 0), new MazeCell(WIDTH - 1, HEIGHT - 1), 2, 1);
			BestFirstSearcher<MazeExplorer> searcher = new BestFirstSearcher<MazeExplorer>(new maze.ai.heuristics.BreadthFirst());
			MazeExplorer endNode = new MazeExplorer(m, m.getEnd());
			endNode.addTreasures(m.getTreasures());
			searcher.solve(new MazeExplorer(m, m.getStart()), endNode);
			assertTrue(searcher.success());
			MazePath path = new MazePath(searcher, m);
			assertTrue(path.solvesMaze(m));
		}
	}

}
