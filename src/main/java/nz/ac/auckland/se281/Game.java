package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {
  private int roundNumber = 1;
  private String name;
  private Difficulty botDifficulty;

  /**
   * Creates a new game of odd or even and takes in the starting values.
   *
   * @param difficulty stores the value of the difficulty of the AI
   * @param choice stores whether the user chose odd or even
   * @param options stores the name
   */
  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    name = options[0];
    botDifficulty = difficulty;
    MessageCli.WELCOME_PLAYER.printMessage(name);
  }

  /**
   * Method that lets the user play by taking in the input that the user wants to do for the current
   * round.
   */
  public void play() {
    boolean validInputFound = false;
    String input;
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
        int fingers = Integer.parseInt(input);
        // checks if the number of fingers is valid
        if (fingers < 0 || fingers > 5) {
          System.out.println(MessageCli.INVALID_INPUT);
        } else {
          MessageCli.PRINT_INFO_HAND.printMessage(name, input);
          validInputFound = true;
        }
      } else {
        System.out.println("Invalid input!");
      }
    }
    BotDifficulty botLevel;
    switch (botDifficulty) {
      case EASY:
        botLevel = DifficultyFactory.createDifficulty("EASY");
        Bot bot = new Bot(new RandomStrategy());
        bot.play();
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
  }

  public void endGame() {}

  public void showStats() {}
}
