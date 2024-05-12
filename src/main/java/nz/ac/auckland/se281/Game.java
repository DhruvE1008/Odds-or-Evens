package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {
  private int roundNumber;
  private String name;
  private Choice choice;
  private int humanScore;
  private int botScore;
  private boolean botWin;
  private String currentStrat;
  private BotDifficulty botLevel;
  private boolean gameStarted = false;
  private ArrayList<Integer> userInput = new ArrayList<>();

  /**
   * Creates a new game of odd or even and takes in the starting values.
   *
   * @param difficulty stores the value of the difficulty of the AI
   * @param choice stores whether the user chose odd or even
   * @param options stores the name
   */
  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    this.name = options[0];
    this.botScore = 0;
    this.humanScore = 0;
    this.choice = choice;
    roundNumber = 1;
    // plays the move based on what difficulty the bot is
    switch (difficulty) {
      case EASY:
        botLevel = DifficultyFactory.createDifficulty("EASY", choice);
        break;
      case MEDIUM:
        botLevel = DifficultyFactory.createDifficulty("MEDIUM", choice);
        break;
      case HARD:
        botLevel = DifficultyFactory.createDifficulty("HARD", choice);
        break;
      default:
        MessageCli.INVALID_DIFFICULTY.printMessage();
        break;
    }
    gameStarted = true;
    MessageCli.WELCOME_PLAYER.printMessage(name);
  }

  /**
   * Method that lets the user play by taking in the input that the user wants to do for the current
   * round.
   */
  public void play() {
    if (gameStarted == false) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    boolean validInputFound = false;
    String input;
    int botFingers = -1;
    int humanFingers = -1;
    String sum;
    Bot bot;
    int evenNum = 0;
    int oddNum = 0;
    // prints what number it is
    MessageCli.START_ROUND.printMessage(String.valueOf(roundNumber));
    roundNumber++;
    // loops until the user inputs the correct information
    while (validInputFound == false) {
      validInputFound = false;
      // asks user for its input
      System.out.println(MessageCli.ASK_INPUT);
      input = Utils.scanner.nextLine();
      if (Utils.isInteger(input)) {
        humanFingers = Integer.parseInt(input);
        // checks if the number of fingers is valid
        if (humanFingers < 0 || humanFingers > 5) {
          MessageCli.INVALID_INPUT.printMessage();
        } else {
          MessageCli.PRINT_INFO_HAND.printMessage(name, input);
          validInputFound = true;
          userInput.add(humanFingers);
        }
      } else {
        MessageCli.INVALID_INPUT.printMessage();
      }
    }
    // counts the number of even numbers and number of odd numbers that the user has input
    for (int i = 0; i < userInput.size() - 1; i++) {
      if (Utils.isEven(userInput.get(i))) {
        evenNum++;
      } else {
        oddNum++;
      }
    }
    botFingers = botLevel.getFingers(roundNumber, oddNum, evenNum, botWin, currentStrat);
    sum = String.valueOf(humanFingers + botFingers);
    // checks if the sum is even or odd
    if (Utils.isEven(humanFingers + botFingers)) {
      // if the choice was even and the sum is even the user wins otherwise the bot wins
      if (choice == Choice.EVEN) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(sum, "EVEN", name);
        botWin = false;
      } else {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(sum, "EVEN", "HAL-9000");
        botWin = true;
      }
    } else {
      // if the choice was odd and the sum is odd the user wins otherwise the bot wins
      if (choice == Choice.ODD) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(sum, "ODD", name);
        botWin = false;
      } else {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(sum, "ODD", "HAL-9000");
        botWin = true;
      }
    }
  }

  public void endGame() {}

  public void showStats() {}
}
