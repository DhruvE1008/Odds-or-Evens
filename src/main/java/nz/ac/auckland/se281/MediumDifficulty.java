package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;

/**
 * this class represents the AI on medium difficulty which has the abstract methods getFingers and
 * getStrat that it got from the botDifficulty interface. It takes the parameter choice which tells
 * us whether the user chose Odd or Even.
 */
public class MediumDifficulty implements BotDifficulty {
  private Choice choice;
  private String newStrat;

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
   * @param currentStrat stores what strategy was used the last round
   * @return returns the number that the AI generated between 0 and 5.
   */
  @Override
  public int getFingers(
      int roundNumber, int oddNum, int evenNum, boolean botWin, String currentStrat) {
    Bot bot;
    if (roundNumber < 4) {
      // if the round number is less than 4 then random strategy is used.
      bot = new Bot(new RandomStrategy());
      newStrat = "Random";
    } else {
      newStrat = "Top";
      // checks if the user inputs more odd numbers than even numbers
      if (oddNum > evenNum) {
        if (choice == Choice.ODD) {
          // checks if the user chose odd or even
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
        // if the user has input odd and even numbers the same amount of times
        // the random strategy will be used.
        newStrat = "Random";
        bot = new Bot(new RandomStrategy());
      }
    }
    return bot.play();
  }

  @Override
  public String getStrat() {
    return newStrat;
  }
}
