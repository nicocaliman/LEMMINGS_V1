package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class WalkerRol
{
	//constructor
	public WalkerRol()
	{
		
	}
	
	//methods
	public void play(Lemming lemming)
	{
		lemming.walkOrFall();
	}

	 public String getIcon( Lemming lemming ) 
	 {
		 return lemming.getDirection() == Direction.RIGHT ? Messages.LEMMING_RIGHT : Messages.LEMMING_LEFT;
	 }
}
