package nz.ac.auckland.se281;

public class TopStrategy implements Strategy {
  /**
   * Generates an odd number or even number based on how much odd and even numbers the user has
   * input.
   *
   * @param moreOddThanEven states whether the user has input more odd than even numbers or not
   * @return an integer between 0 and 5 that the AI generated
   */
  @Override
  public int getFingerAmount(boolean moreOddThanEven) {
    if (moreOddThanEven) {
      return Utils.getRandomOddNumber();
    } else {
      return Utils.getRandomEvenNumber();
    }
  }
}
