package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.WalkerRol;
import tp1.view.Messages;

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
				this.fallForce++;
				Position newPosition = new Position(Direction.DOWN, this.position);
								
				if (newPosition.isSouthBorder())	//if lemming jumps into the void
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
			
			else	//if lemming is walking
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
						Position newPosition = new Position(Direction.RIGHT, this.position);
						
						if (newPosition.isEastBorder() || this.game.isSolid(newPosition)) //if new position touches right wall
						{
							this.direction = Direction.LEFT;	//set lemming´s direction
						}
						
						else	// if there is no obstacle
						{
							this.position = newPosition;	//set new position
						}
							
					}
					
					else if (this.getDirection() == Direction.LEFT)	//if moving towards left
					{
						Position newPosition = new Position(Direction.LEFT, this.position);
						
						this.position = newPosition;
						
						if (newPosition.isWestBorder() || this.game.isSolid(newPosition)) //if new position touches right wall
						{
							this.direction = Direction.RIGHT;	//set lemming´s direction
						}
						
						else	// if there is no obstacle
						{
							this.position = newPosition;	//set new position
						}
					}
				}				
			}
		}				
	}	

	public boolean isInPosition(Position position)
	{
		return this.position.equals(position); //ask Position.java equals
	}
	
	public String getIcon()
	{
		return this.getDirection() == Direction.RIGHT ? Messages.LEMMING_RIGHT : Messages.LEMMING_LEFT;	//if lemming´s direction is set to the right, show RIGHT LEMMING, else, show LEFT LEMMING representation
	}
	
	public String toString()
	{
		return this.position.toString() + "L" + this.direction.toString();
	}
	
	public Direction getDirection()
	{
		return this.direction;	//lemming´s direction
	}
}
