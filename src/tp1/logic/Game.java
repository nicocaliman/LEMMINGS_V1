package tp1.logic;

import tp1.view.Messages;
import tp1.logic.gameobjects.*;

public class Game 
{
	//constant attributes
	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;
	public static final int WIN = 2;
	
	private static final int LEMMINGS_LVL_1 = 4;
	private static final int LEMMINGS_LVL_0 = 3;
	
	//attributes
	private int numberOfCycles;
	private int numberLemmingsInBoard;
	private int numberOfDeadLemmings;
	private int numberOfExitLemmings;
	private int numberOfLemmingsToWin;
	private boolean finished;
	
	private int level;
	
	private GameObjectContainer container;	
	
	//constructor
	public Game(int nLevel) 
	{		
		this.level = nLevel;
		
		this.reset();
	}

	//methods
	public int getCycle()
	{
		return this.numberOfCycles;	//return number of cycles played
	}

	public int numLemmingsInBoard() 
	{
		return this.numberLemmingsInBoard;	//return number of lemmings in board
	}

	public int numLemmingsDead() 
	{
		return this.numberOfDeadLemmings;	//return number of lemmings who died
	}	

	public int numLemmingsExit() 
	{		
		return this.numberOfExitLemmings;	//return number of lemmings that crossed the door
	}

	public int numLemmingsToWin() 
	{
		return this.numberOfLemmingsToWin;	//return number of lemmings needed to win(they must cross the door)
	}

	public String positionToString(int col, int row) 
	{
		return this.container.positionToString(new Position(col, row));	//return what object is in that position 
	}

	public boolean playerWins()
	{
		return (this.numLemmingsToWin() == this.numLemmingsExit());
	}

	public boolean playerLooses()
	{
		return this.numLemmingsInBoard() == 0;	//player looses if there is no lemmings left in board
	}

	public String help() 
	{
		return Messages.HELP;	//return help message
	}
	
	public boolean isFinished()
	{
		return this.finished || this.playerLooses();	//game finishes if user quits or the number of lemmings in board equals 0
	}	
	
	public void update()
	{
		updateNumberOfCycles();	//update number of cycles
		
		this.container.update();	//update game elements
	}

	private void updateNumberOfCycles() 
	{
		this.numberOfCycles++;	//update number of cycles
	}

	public boolean isSolid(Position position)
	{
		return this.container.isSolid(position);	//true if position is solid
	}
	
	public boolean isExit(Position position)
	{
		return this.container.isExit(position);	//true if position is an exitdoor
	}
	
	public boolean isInAir(Position position)
	{
		return this.container.isInAir(position);	//true if lemming is in the air
	}
	
	public void exit()
	{
		this.finished = true;	//user quits the game
	}
	
	public void reset()
	{
		this.numberOfCycles = 0;	//starts at 0
		this.numberOfDeadLemmings = 0;
		this.numberOfLemmingsToWin = WIN;
		
		this.finished = false;	//initially not finished
		
		this.container = new GameObjectContainer();	//instance of GameObjectContainer class
		
		if(this.level == 0)	//if level = 0
		{
			initGame0();	//init game map 0
		}
		
		else	//else
		{
			initGame1();	//init game map 1
		}
	}
	
	public void initGame0()
	{
		setNumOfLemmingsInBoard(LEMMINGS_LVL_0);	//number of lemmings in board LVL 0 = 3

		//add walls
		this.container.add(new Wall(new Position(8, 1)));
		this.container.add(new Wall(new Position(9, 1)));
		
		this.container.add(new Wall(new Position(2, 4)));
		this.container.add(new Wall(new Position(3, 4)));
		this.container.add(new Wall(new Position(4, 4)));
		
		this.container.add(new Wall(new Position(7, 5)));		

		this.container.add(new Wall(new Position(4, 6)));
		this.container.add(new Wall(new Position(5, 6)));
		this.container.add(new Wall(new Position(6, 6)));
		this.container.add(new Wall(new Position(7, 6)));		

		this.container.add(new Wall(new Position(8, 8)));		

		this.container.add(new Wall(new Position(0, 9)));
		this.container.add(new Wall(new Position(1, 9)));		
		this.container.add(new Wall(new Position(8, 9)));
		this.container.add(new Wall(new Position(9, 9)));
		
		//add lemmings
		this.container.add(new Lemming(this, new Position(9, 0)));
		
		this.container.add(new Lemming(this, new Position(2, 3)));
		
		this.container.add(new Lemming(this, new Position(0, 8)));		
		
		//add exit door
		this.container.add(new ExitDoor(new Position(4, 5)));
	}
	
	public void initGame1()
	{
		this.initGame0();
		setNumOfLemmingsInBoard(LEMMINGS_LVL_1);	//number of lemmings in board LVL 1 = 4
		
		this.container.add(new Lemming(this, new Position(3, 3)));
	}

	private void setNumOfLemmingsInBoard(int lemmingsLvl)
	{
		this.numberLemmingsInBoard = lemmingsLvl;		
	}	
	
	public void lemmingExit()
	{
		this.numberOfExitLemmings++;
		this.numberLemmingsInBoard--;
	}
	
	public void lemmingDead()
	{
		this.numberOfDeadLemmings++;
		this.numberLemmingsInBoard--;
	}
}