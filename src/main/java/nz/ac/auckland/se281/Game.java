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
        // if the difficulty is invalid an appropriate message is printed.
        MessageCli.INVALID_DIFFICULTY.printMessage();
        break;
    }
    gameStarted = true;
    // welcomes the player
    MessageCli.WELCOME_PLAYER.printMessage(name);
    userInput.clear();
  }

  /**
   * Method that lets the user play by taking in the input that the user wants to do for the current
   * round.
   */
  public void play() {
    if (!gameStarted) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    boolean validInputFound = false;
    String input;
    int botFingers;
    int humanFingers = -1;
    String sum;
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
    botFingers = botLevel.getFingers(roundNumber, oddNum, evenNum, botWin);
    sum = String.valueOf(humanFingers + botFingers);
    // checks if the sum is even or odd
    if (Utils.isEven(humanFingers + botFingers)) {
      // if the choice was even and the sum is even the user wins otherwise the bot wins
      if (choice == Choice.EVEN) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(sum, "EVEN", name);
        botWin = false;
        humanScore++;
      } else {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(sum, "EVEN", "HAL-9000");
        botWin = true;
        botScore++;
      }
    } else {
      // if the choice was odd and the sum is odd the user wins otherwise the bot wins
      if (choice == Choice.ODD) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(sum, "ODD", name);
        botWin = false;
        humanScore++;
      } else {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(sum, "ODD", "HAL-9000");
        botWin = true;
        botScore++;
      }
    }
  }

  /** method that ends the game by stating who the winner is. */
  public void endGame() {
    // if the game hasn't started and the user wants to end the game
    // we output an error message.
    if (!gameStarted) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    showStats();
    // outputs the winner or Tie! if the scores are tied.
    if (humanScore > botScore) {
      MessageCli.PRINT_END_GAME.printMessage(name);
    } else if (botScore > humanScore) {
      MessageCli.PRINT_END_GAME.printMessage("HAL-9000");
    } else {
      MessageCli.PRINT_END_GAME_TIE.printMessage();
    }
    gameStarted = false;
  }

  /**
   * shows the data of how much games the user has won and lost and how many games the AI has won
   * and lost.
   */
  public void showStats() {
    //  prints an error if the user decides to end the game even though
    // a game hasn't been started
    if (!gameStarted) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    // outputs the scores of the player and the AI.
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        name, String.valueOf(humanScore), String.valueOf(botScore));
    MessageCli.PRINT_PLAYER_WINS.printMessage(
        "HAL-9000", String.valueOf(botScore), String.valueOf(humanScore));
  }
}
