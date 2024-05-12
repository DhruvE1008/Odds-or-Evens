package nz.ac.auckland.se281;

/**
 * this class is the easy difficulty of the AI which has the abstract methods getFingers and
 * getStart from the botDifficulty interface and it has the default constructor.
 */
public class EasyDifficulty implements BotDifficulty {
  /**
   * gets the number of fingers by random strategy.
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
    Bot bot = new Bot(new RandomStrategy());
    return bot.play();
  }

  @Override
  public String getStrat() {
    return "Random";
  }
}
