package nz.ac.auckland.se281;

public class RandomStrategy implements Strategy {
  /**
   * Randomly generates a number between 0 and 5 and returns it.
   *
   * @return an integer between 0 and 5 inclusive
   */
  @Override
  public int getFingerAmount(boolean moreOddThanEven, boolean isOdd) {
    return Utils.getRandomNumberRange(0, 5);
  }
}
