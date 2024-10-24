package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.WalkerRol;

public class Lemming 
{
	private static final int FALL = 2;
	
	//attributes
	private Position position;
	private Direction direction;
	private boolean isAlive;
	private Game game;
	private WalkerRol rol;
	private int fallForce;

	//constructor
	public Lemming(Game game, Position position)
	{
		this.isAlive = true;	//initially alive
		this.game = game;
		this.position = position;
		this.direction = Direction.RIGHT;	//default movement
		this.rol = new WalkerRol();
		this.fallForce = 0;	//initially 0
	}
	
	//methods
	
	/**
	 *  Implements the automatic update	
	 */
	public void update()
	{
		if (this.isAlive) 	//if lemming is alive
		{			
			rol.play(this);
		}
	}
	
	public void dead()
	{
		this.isAlive = false;	//set lemming´s life
	}
	
	public boolean isAlive()
	{
		return this.isAlive;	//true if isAlive = true, else if not
	}
	
	public boolean isInAir()
	{
		Position below = new Position(Direction.DOWN, this.position);	//position below lemming
		
		//return true if position below lemming is not solid, else return false if there is a wall down below lemming´s position
		return !game.isSolid(below);	
	}
	
	public void walkOrFall()
	{
		if (this.game.isExit(this.position))	//if lemming´s is in the exit door
		{
			this.game.lemmingExit();	//lemming exit game
			this.dead();
		}
		
		else	//lemming can be walking or falling
		{	
			if (this.game.isInAir(this.position))	//if lemming is falling
			{					
				this.doFall();				//fall action					
			}
			
			else	//if lemming is walking
			{
				this.doWalk();		//walk action	
			}
		}				
	}	
	
	private void doWalk()
	{
		if (this.fallForce > FALL)
		{
			this.dead();
			this.game.lemmingDead();
		}
		
		else
		{
			this.fallForce = 0;
			
			if (this.getDirection() == Direction.RIGHT)	//if moving towards right
			{
				this.moveRightAction();					
			}
			
			else if (this.getDirection() == Direction.LEFT)	//if moving towards left
			{
				this.moveLeftAction();
			}
		}		
	}

	private void moveLeftAction()
	{
		Position newPosition = new Position(Direction.LEFT, this.position);
		
		this.position = newPosition;
		
		if (!newPosition.isInBoard()|| this.game.isSolid(newPosition)) //if new position touches right wall
		{
			this.direction = this.direction.opposite();	//set lemming´s direction
		}
		
		else	// if there is no obstacle
		{
			this.position = newPosition;	//set new position
		}		
	}

	private void moveRightAction() 
	{
		Position newPosition = new Position(Direction.RIGHT, this.position);
		
		if (!newPosition.isInBoard() || this.game.isSolid(newPosition)) //if new position touches right wall
		{
			this.direction = this.direction.opposite();	//set lemming´s direction
		}
		
		else	// if there is no obstacle
		{
			this.position = newPosition;	//set new position
		}
		
	}

	private void doFall()
	{
		this.fallForce++;
		Position newPosition = new Position(Direction.DOWN, this.position);
						
		if (!newPosition.isInBoard())	//if lemming jumps into the void
		{
			this.dead();	//lemming dies
			this.game.lemmingDead();
		}
		
		else if (this.game.isSolid(newPosition))	
		{
			if (this.fallForce > FALL)
			{
				this.dead();
				this.game.lemmingDead();
			}
			
			else
			{
				this.position = newPosition;
			}
		}
		
		else
		{
			this.position = newPosition;
		}
	}
	
	public boolean isInPosition(Position position)
	{
		return this.position.equals(position); //ask Position.java equals
	}
	
	public String getIcon()
	{
		return this.rol.getIcon(this);	//if lemming´s direction is set to the right, show RIGHT LEMMING, else, show LEFT LEMMING representation
	}
	
	@Override
	public String toString()
	{
		return this.position.toString() + "L" + this.direction.toString();
	}
	
	public Direction getDirection()
	{
		return this.direction;	//lemming´s direction
	}
}
