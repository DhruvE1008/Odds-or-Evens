package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;

/**
 * this class represents the AI on medium difficulty which has the abstract method getFingers
 * that it got from the botDifficulty interface. It takes the parameter choice which tells
 * us whether the user chose Odd or Even.
 */
public class MediumDifficulty implements BotDifficulty {
  private Choice choice;
  private Bot bot = null;

  public MediumDifficulty(Choice choice) {
    this.choice = choice;
  }

  /**
   * gets the number of fingers the AI generates at medium difficulty.
   *
   * @param roundNumber the amount of rounds that the current game is at
   * @param oddNum the number of odd numbers the user has input
   * @param evenNum the number of even numbers the user has input
   * @param botWin stores whether or not the bot won the last round
   * @return returns the number that the AI generated between 0 and 5.
   */
  @Override
  public int getFingers(
      int roundNumber, int oddNum, int evenNum, boolean botWin) {
    if (bot == null) {
      bot = new Bot(new RandomStrategy());
    }
    if (roundNumber >= 4) {
      // checks if the user inputs more odd numbers than even numbers
      if (oddNum > evenNum) {
        if (choice == Choice.ODD) {
          // checks if the user chose odd or even
          bot.setStrategy(new TopStrategy(), true, true);
        } else {
          bot.setStrategy(new TopStrategy(), true, false);
        }
      } else if (evenNum > oddNum) {
        if (choice == Choice.ODD) {
          bot.setStrategy(new TopStrategy(), false, true);
        } else {
          bot.setStrategy(new TopStrategy(), false, false);
        }
      } else {
        // if the user has input odd and even numbers the same amount of times
        // the random strategy will be used.
        bot.setStrategy(new RandomStrategy());
      }
    }
    return bot.play();
  }
}
