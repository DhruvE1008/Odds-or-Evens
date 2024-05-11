package nz.ac.auckland.se281;

public class EasyDifficulty implements BotDifficulty {
  /**
   * gets the number of fingers by random strategy
   *
   * @param bot is an instance of class Bot
   * @return gives us the number that the AI generates
   */
  public int getFingers(Bot bot) {
    return bot.play();
  }
}
