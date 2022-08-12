package solver;

import java.util.*;

public class DepthFirstStrategy extends SearchMethod
{

	public DepthFirstStrategy()
	{
		code = "DFS";
		longName = "Depth-First Strategy Search";
		Frontier = new LinkedList<PuzzleState>();
		Searched = new LinkedList<PuzzleState>();
	}

	public direction[] Solve(nPuzzle puzzle)
	{
		addToFrontier(aPuzzle.StartState);
		while(Frontier.size() > 0)
		{
			//Get the next State
			PuzzleState thisState = popFrontier();

			//Return the solution if the goal is reached
			if(thisState.equals(puzzle.GoalState))
			{
				return thisState.GetPathToState();
			}

			//not the goal state, explore this node
			ArrayList<PuzzleState> newStates = thisState.explore();

			for(int i = 0; i < newStates.size(); i++)
			{
				PuzzleState newChild = newStates.get(i);

				// add new child to the frontier to explore that node
				addToFrontier(newChild);

			}
		}
		return null;
	}

	public boolean addToFrontier(PuzzleState aState)
	{
		//if this state has been found before delete it
		if(Searched.contains(aState) || Frontier.contains(aState))
		{
			return false;
		}
		else
		{
			Frontier.add(aState);
			return true;
		}

	}

	protected PuzzleState popFrontier()
	{
		//remove a state from the top of the frontier so that it can be searched.
		PuzzleState lState = Frontier.pollFirst();

		//add it to the list of searched states so that duplicates are recognised.
		Searched.add(lState);

		return lState;
	}
}
