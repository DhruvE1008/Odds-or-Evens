package nz.ac.auckland.se281;

public class DifficultyFactory {
  /**
   * takes in the difficulty as input and then creates an instance of the difficulty
   *
   * @param type difficulty of the game in type string
   * @return a new instance of a difficulty
   */
  public static BotDifficulty createDifficulty(String type) {
    // finds out what difficulty the bot is and then creates a instance of the difficulty
    switch (type) {
      case "EASY":
        return new EasyDifficulty();
      case "MEDIUM":
        return new MediumDifficulty();
      case "HARD":
        return new HardDifficulty();
      default:
        break;
    }
    return null;
  }
}
