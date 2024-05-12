package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;

/**
 * This factory class takes in the difficulty of the AI that the user wants to play as it creates a
 * new instance of its respective difficulty class.
 */
public class DifficultyFactory {
  /**
   * takes in the difficulty as input and then creates an instance of the difficulty.
   *
   * @param type difficulty of the game in type string
   * @return a new instance of a difficulty
   */
  public static BotDifficulty createDifficulty(String type, Choice choice) {
    // finds out what difficulty the bot is and then creates a instance of the difficulty
    switch (type) {
      case "EASY":
        return new EasyDifficulty();
      case "MEDIUM":
        return new MediumDifficulty(choice);
      case "HARD":
        return new HardDifficulty(choice);
      default:
        break;
    }
    return null;
  }
}
