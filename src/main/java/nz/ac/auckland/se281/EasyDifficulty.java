package nz.ac.auckland.se281;

public class EasyDifficulty implements BotDifficulty {
  /**
   * gets the number of fingers by random strategy
   *
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
