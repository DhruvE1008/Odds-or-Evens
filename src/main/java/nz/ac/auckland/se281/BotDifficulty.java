package nz.ac.auckland.se281;

/**
 * interface which bases all the difficulty classes which has the abstract methods getFingers and
 * getStrat.
 */
public interface BotDifficulty {
  public int getFingers(
      int roundNumber, int oddNum, int evenNum, boolean botWin, String currentStrat);

  public String getStrat();
}
