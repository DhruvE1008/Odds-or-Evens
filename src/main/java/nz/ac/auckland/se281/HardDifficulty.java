package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;

/**
 * class that represents the hard difficulty that an AI could be and it features the abstract
 * method getFingers and also has the method topStrategySetup. The constructor takes
 * choice as its parameter which stores whether the user chose Odd or Even.
 */
public class HardDifficulty implements BotDifficulty {
  private Choice choice;
  private Bot bot = null;

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
   * @return gives us the number that the AI generates
   */
  @Override
  public int getFingers(int roundNumber, int oddNum, int evenNum, boolean botWin) {
    if (bot == null) {
      bot = new Bot(new RandomStrategy());
    }
    if (roundNumber >= 4) {
      // if round number is less than 4 then the random strategy will be used.
      if (botWin) {
        // if the bot won the previous round the strategy will stay the same.
        bot.setStrategy(bot.getStrategy(), (oddNum > evenNum), (choice == Choice.ODD));
      } else {
        // if the bot didnt win the previous round the strategy will change
        StrategySetup(oddNum, evenNum);
      }
    }
    return bot.play();
  }

  /**
   * sets up the TopStrategy class by sending through the parameters based on what the user has
   * input before and whether it chose Odd or Even.
   *
   * @param oddNum - contains the number of odd numbers that the user has input
   * @param evenNum - contains the number of even numbers that the user has input
   */
  public void StrategySetup(int oddNum, int evenNum) {
    // checks the amount of odd numbers and even numbers and see which one is
    // more frequent.
    if (oddNum > evenNum) {
      // checks whether the choice was odd or even
      if (choice == Choice.ODD) {
        bot.switchStrategy(true, true);
      } else {
        bot.switchStrategy(true, false);
      }
    } else if (evenNum > oddNum) {
      if (choice == Choice.ODD) {
        bot.switchStrategy(false, true);
      } else {
        bot.switchStrategy(false, false);
      }
    } else {
      // random strategy is used if the amount of odd and even numbers is the same
      bot.setStrategy(new RandomStrategy());
    }
  }
}
