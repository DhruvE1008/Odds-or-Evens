package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {
  private int roundNumber = 1;
  private String name;
  private Difficulty botDifficulty;
  private Choice choice;
  private int humanScore;
  private int botScore;

  /**
   * Creates a new game of odd or even and takes in the starting values.
   *
   * @param difficulty stores the value of the difficulty of the AI
   * @param choice stores whether the user chose odd or even
   * @param options stores the name
   */
  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    this.name = options[0];
    this.botDifficulty = difficulty;
    this.choice = choice;
    this.botScore = 0;
    this.humanScore = 0;
    MessageCli.WELCOME_PLAYER.printMessage(name);
  }

  /**
   * Method that lets the user play by taking in the input that the user wants to do for the current
   * round.
   */
  public void play() {
    boolean validInputFound = false;
    String input;
    int botFingers = -1;
    int humanFingers = -1;
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
        }
      } else {
        MessageCli.INVALID_INPUT.printMessage();
      }
    }
    BotDifficulty botLevel;
    // plays the move based on what difficulty the bot is
    switch (botDifficulty) {
      case EASY:
        botLevel = DifficultyFactory.createDifficulty("EASY");
        Bot bot = new Bot(new RandomStrategy());
        botFingers = bot.play();
        break;
      case MEDIUM:
        botLevel = DifficultyFactory.createDifficulty("MEDIUM");
        break;
      case HARD:
        botLevel = DifficultyFactory.createDifficulty("HARD");
        break;
      default:
        MessageCli.INVALID_DIFFICULTY.printMessage();
        break;
    }
    if (Utils.isEven(humanFingers+botFingers)) {
      if (choice == Choice.EVEN) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(String.valueOf(humanFingers+botFingers), "EVEN", name);
      } else {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(String.valueOf(humanFingers+botFingers), "EVEN", "HAL-9000");
      }
    } else {
      if (choice == Choice.ODD) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(String.valueOf(humanFingers+botFingers), "ODD", name);
      } else {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage(String.valueOf(humanFingers+botFingers), "ODD", "HAL-9000");
      }
    }
  }

  public void endGame() {}

  public void showStats() {}
}
