package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;

/**
 * class that represents the hard difficulty that an AI could be and it features the abstract
 * methods getFingers and getStrat and also has the method topStrategySetup. The constructor takes
 * choice as its parameter which stores whether the user chose Odd or Even.
 */
public class HardDifficulty implements BotDifficulty {
  private Choice choice;
  private String newStrat;
  private Bot bot;

  public HardDifficulty(Choice choice) {
    this.choice = choice;
  }

  /**
   * gets the number of fingers that the AI generates at hard difficulty.
   *
   * @param roundNumber the amount of rounds that the current game is at
   * @param oddNum the number of odd numbers the user has input
   * @param evenNum the number of even numbers the user has input
   * @param botWin stores whether or not the bot won the last round
   * @param currentStrat stores what strategy was used the last round
   * @return gives us the number that the AI generates
   */
  @Override
  public int getFingers(
      int roundNumber, int oddNum, int evenNum, boolean botWin, String currentStrat) {
    if (roundNumber < 4) {
      bot = new Bot(new RandomStrategy());
    } else {
      if (botWin) {
        if (currentStrat == "random") {
          bot = new Bot(new RandomStrategy());
          newStrat = "random";
        } else {
          topStrategySetup(oddNum, evenNum, currentStrat);
          newStrat = "top";
        }
      } else {
        if (currentStrat == "random") {
          topStrategySetup(oddNum, evenNum, currentStrat);
          newStrat = "top";
        } else {
          bot = new Bot(new RandomStrategy());
          newStrat = "random";
        }
      }
    }
    return bot.play();
  }

  public void topStrategySetup(int oddNum, int evenNum, String currentStrat) {
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

  @Override
  public String getStrat() {
    return newStrat;
  }
}
