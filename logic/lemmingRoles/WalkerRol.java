package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;

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
		 return lemming.getIcon();
	 }
}
