package nz.ac.auckland.se281;

public class RandomStrategy implements Strategy {
  /** Randomly generates a number between 0 and 5 and returns it */
  @Override
  public int getFingerAmount() {
    return Utils.getRandomNumberRange(0, 5);
  }
}
