package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class GameObjectContainer
{
	//attributes
	private List<Lemming> lemmings;
	private List<Wall> walls;
	private List<ExitDoor> exitDoors;
		
	private int numLemmings;
	private int numWalls;
	private int numExitDoors;
	
	//constructor
	public GameObjectContainer()
	{
		//array lists
		this.lemmings = new ArrayList<>();
		this.walls = new ArrayList<>();
		this.exitDoors = new ArrayList<>();
		
		//counters
		this.numLemmings = 0;
		this.numWalls = 0;
		this.numExitDoors = 0;
	}
	
	//methods	
	public void update() 
	{
		//for-each
		for (Lemming lemming : lemmings)
		{
			lemming.update();	//update game object
		}
		
		this.removeDead(); //remove any lemming who´s dead after update
	}
	
	public String positionToString(Position position)
	{
		StringBuilder elements = new StringBuilder();	//mutable string(we can add string representation from an object + a differente representation from another object)

	    for (Wall wall : this.walls) 
	    {
	        if (wall.isInPosition(position)) 
	        {
	            elements.append(wall.getIcon());
	        }
	    }

	    for (Lemming lemming : this.lemmings)
	    {
	        if (lemming.isInPosition(position)) 
	        {
	            elements.append(lemming.getIcon());
	        }
	    }

	    for (ExitDoor exitDoor : this.exitDoors)
	    {
	        if (exitDoor.isInPosition(position)) 
	        {
	            elements.append(exitDoor.getIcon());
	        }
	    }

	    return elements.length() > 0 ? elements.toString() : Messages.EMPTY;	//if string length is > 0 print string, else print empty string " "
	}
	
	private void removeDead()
	{
		for(int i = 0; i < this.numLemmings; ++i)
		{
			Lemming lemming = this.lemmings.get(i);	//lemming at position i
			
			if (!lemming.isAlive())	//if lemming at position i is dead
			{
				this.lemmings.remove(i);	//remove dead lemming from that position and shift elements from i+1 to the left
				this.numLemmings--;
			}
		}		
	}
	
	public void add(Lemming lemming)
	{
		this.lemmings.add(lemming);	//add lemming to its list
		setNumOfLemmings();	//update counter
	}
	
	private void setNumOfLemmings() 
	{	
		this.numLemmings++;
	}

	public void add(Wall wall)
	{
		this.walls.add(wall);	//add wall to its list
		setNumOfWalls();	//update counter
	}
	
	private void setNumOfWalls() 
	{
		this.numWalls++;
	}

	public void add(ExitDoor exitDoor)
	{
		this.exitDoors.add(exitDoor);	//add exitdoor to its list
		setNumOfExitDoors();	//update counter
	}

	private void setNumOfExitDoors() 
	{		
		this.numExitDoors++;
	}
	
	public boolean isSolid(Position position)
	{
		int i = 0;
		boolean found = false;
		
		while(i < this.numWalls && !found)
		{
			Wall wall = this.walls.get(i);	//wall in position i
			
			if (wall.isInPosition(position))	//if wall is in that position
			{
				found = true;
			}
			
			++i;
		}
		
		return found;	//true if there is a wall in that position
	}
	
	public boolean isExit(Position position)
	{
		int i = 0;
		boolean found = false;
		
		while(i < this.numExitDoors && !found)
		{
			ExitDoor exitDoor = this.exitDoors.get(i);	//exit door in position i
			
			if (exitDoor.isInPosition(position))	//if exit door is in that position
			{
				found = true;				
			}
			
			++i;
		}
		
		return found;	//true if there is an exit door in that position
	}

	public boolean isInAir(Position position)
	{
		boolean inAir = false;
		
		for (Lemming lemming : lemmings)
		{
			if (lemming.isInPosition(position) && lemming.isInAir()) 	//if lemming is in position & is in the air
			{
				inAir = true;
			}
		}
				
		return inAir;	//true if there is a lemming in the air in that position
	}
}
