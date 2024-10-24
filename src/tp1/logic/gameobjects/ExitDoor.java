package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor
{
	//attributes 
	private Position position;
	
	//constructor
	public ExitDoor(Position position)
	{
		this.position = position;
	}

	public String getIcon()
	{
		return this.toString();
	}
	
	public boolean isInPosition(Position position) 
	{	    
	    return this.position.equals(position); //ask Position.java equals
	}
	
	@Override
	public String toString()
	{
		return Messages.EXIT_DOOR;
	}
}
