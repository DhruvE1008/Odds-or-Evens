package nz.ac.auckland.se281;

import java.util.ArrayList;
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
  private boolean botWin;
  private String currentStrat;
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
    BotDifficulty botLevel;
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
    // plays the move based on what difficulty the bot is
    switch (botDifficulty) {
      case EASY:
        botLevel = DifficultyFactory.createDifficulty("EASY");
        bot = new Bot(new RandomStrategy());
        botFingers = botLevel.getFingers(bot);
        break;
      case MEDIUM:
        botLevel = DifficultyFactory.createDifficulty("MEDIUM");
        if (roundNumber < 4) {
          bot = new Bot(new RandomStrategy());
        } else {
          if (oddNum > evenNum) {
            if (choice == Choice.ODD) {
              bot = new Bot(new TopStrategy(), true, true);
            } else {
              bot = new Bot(new TopStrategy(), true, false);
            }
          } else if (evenNum > oddNum) {
            if (choice == Choice.ODD) {
              bot = new Bot(new TopStrategy(), false, true);
            } else {
              bot = new Bot(new TopStrategy(), false, false);
            }
          } else {
            bot = new Bot(new RandomStrategy());
          }
        }
        botFingers = botLevel.getFingers(bot);
        break;
      case HARD:
        botLevel = DifficultyFactory.createDifficulty("HARD");
        if (roundNumber < 4) {
          System.out.println("test");
          bot = new Bot(new RandomStrategy());
          currentStrat = "random";
        } else {
          if (botWin) {
            if (currentStrat == "random") {
              bot = new Bot(new RandomStrategy());
              currentStrat = "random";
            } else {
              if (oddNum > evenNum) {
                if (choice == Choice.ODD) {
                  bot = new Bot(new TopStrategy(), true, true);
                } else {
                  bot = new Bot(new TopStrategy(), true, false);
                }
              } else if (evenNum > oddNum) {
                if (choice == Choice.ODD) {
                  bot = new Bot(new TopStrategy(), false, true);
                } else {
                  bot = new Bot(new TopStrategy(), false, false);
                }
              } else {
                bot = new Bot(new RandomStrategy());
              }
              currentStrat = "top";
            }
          } else {
            if (currentStrat == "random") {
              if (oddNum > evenNum) {
                if (choice == Choice.ODD) {
                  bot = new Bot(new TopStrategy(), true, true);
                } else {
                  bot = new Bot(new TopStrategy(), true, false);
                }
              } else if (evenNum > oddNum) {
                if (choice == Choice.ODD) {
                  bot = new Bot(new TopStrategy(), false, true);
                } else {
                  bot = new Bot(new TopStrategy(), false, false);
                }
              } else {
                bot = new Bot(new RandomStrategy());
              }
              currentStrat = "top";
            } else {
              bot = new Bot(new RandomStrategy());
              currentStrat = "random";
            }
          }
        }
        botFingers = botLevel.getFingers(bot);
        break;
      default:
        MessageCli.INVALID_DIFFICULTY.printMessage();
        break;
    }
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
