package nz.ac.auckland.se281;

public interface BotDifficulty {
  public int getFingers(int roundNumber, int oddNum, int evenNum, boolean botWin, String currentStrat);
  public String getStrat();
}
