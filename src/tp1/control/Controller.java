package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller
{
	private Game game;
	private GameView view;

	public Controller(Game game, GameView view)
	{
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() 
	{
		boolean printBoard = true;
		
		view.showWelcome();	//show welcome message
		
		//while game is not finished
		while(!game.isFinished())
		{
			if(printBoard)	//if command requires to print board
			{
				view.showGame();	//print board
			}
			
			String command[] = view.getPrompt();	//ask 4 command
			printBoard = executeCommand(command);	//execute command
			
		}
		
		if(printBoard)
			view.showGame();
		
		view.showEndMessage();
	}


	private boolean executeCommand(String[] command)
	{
		boolean show = false;	//flag
		
		//if user introduces more arguments
		if(command.length > 1)
			view.showError(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		
		else	//else
		{
			String s = command[0].toLowerCase();	//set string to lower cases
			
			switch(s)	//fetch command & execute it
			{
				//command none
				case "":
				case "n":
				case "none":
					game.update();	//update game
					show = true;
				break;
					
				//command reset
				case "r":
				case "reset":
					game.reset();		//reset game		
					show = true;
				break;
				
				//command help
				case "h":
				case "help":
					view.showMessage(game.help());	//show help message
					break;

				//command exit
				case "e":
				case "exit":
					game.exit(); 	//user quits the game
				break;
					
				default:
					view.showError(Messages.UNKNOWN_COMMAND);	//if user types odd command					
			}		
		}		
		
		return show;
	}
}
