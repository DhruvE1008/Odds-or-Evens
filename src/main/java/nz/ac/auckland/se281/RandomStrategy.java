package nz.ac.auckland.se281;

/** A type of strategy where it randomly chooses a number from 0 and 5. */
public class RandomStrategy implements Strategy {
  /**
   * Randomly generates a number between 0 and 5 and returns it.
   *
   * @param moreOddThanEven stores whether the user has input more odd numbers or even numbers in
   *     the past.
   * @param isOdd stores whether the user chose odd or even.
   * @return an integer between 0 and 5 inclusive
   */
  @Override
  public int getFingerAmount(boolean moreOddThanEven, boolean isOdd) {
    return Utils.getRandomNumberRange(0, 5);
  }
}
