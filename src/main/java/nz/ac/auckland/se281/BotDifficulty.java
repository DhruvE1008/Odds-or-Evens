package nz.ac.auckland.se281;

/**
 * interface which bases all the difficulty classes which has the abstract method getFingers.
 */
public interface BotDifficulty {
  public int getFingers(
      int roundNumber, int oddNum, int evenNum, boolean botWin);
}
