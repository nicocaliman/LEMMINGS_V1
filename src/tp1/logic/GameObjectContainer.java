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
	
	//constructor
	public GameObjectContainer()
	{
		//array lists
		this.lemmings = new ArrayList<>();
		this.walls = new ArrayList<>();
		this.exitDoors = new ArrayList<>();
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
		for(int i = 0; i < this.lemmings.size(); ++i)
		{
			Lemming lemming = this.lemmings.get(i);	//lemming at position i
			
			if (!lemming.isAlive())	//if lemming at position i is dead
			{
				this.lemmings.remove(i);	//remove dead lemming from that position and shift elements from i+1 to the left				
			}
		}		
	}
	
	public void add(Lemming lemming)
	{
		this.lemmings.add(lemming);	//add lemming to its list
	}

	public void add(Wall wall)
	{
		this.walls.add(wall);	//add wall to its list
	}

	public void add(ExitDoor exitDoor)
	{
		this.exitDoors.add(exitDoor);	//add exitdoor to its list
	}

	public boolean isSolid(Position position)
	{
		int i = 0;
		
		while(i < this.walls.size() && !this.walls.get(i).isInPosition(position))
		{			
			++i;
		}
		
		return i < this.walls.size();	//true if there is a wall in that position
	}
	
	public boolean isExit(Position position)
	{
		int i = 0;
		
		while(i < this.exitDoors.size() && !this.exitDoors.get(i).isInPosition(position))
		{			
			++i;
		}
		
		return i < this.exitDoors.size();	//true if there is an exit door in that position
	}

	public boolean isInAir(Position position)
	{
		int i = 0;
		
		while(i < this.lemmings.size() && !(this.lemmings.get(i).isInPosition(position) && this.lemmings.get(i).isInAir()))
		{
			++i;
		}
						
		return i < this.lemmings.size();	//true if there is a lemming in the air in that position
	}
}
