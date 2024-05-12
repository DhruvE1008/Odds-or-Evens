package nz.ac.auckland.se281;

/**
 * class that implements the strategy interface which has the AI return a number based on what the
 * user has been inputting in the past.
 */
public class TopStrategy implements Strategy {
  /**
   * Generates an odd number or even number based on how much odd and even numbers the user has
   * input.
   *
   * @param moreOddThanEven states whether the user has input more odd than even numbers or not
   * @return an integer between 0 and 5 that the AI generated
   */
  @Override
  public int getFingerAmount(boolean moreOddThanEven, boolean isOdd) {
    // if the user has input more odd than even numbers the AI will consider what the choice that
    // the user choose and select a random odd or even number based on what they think the user will
    // select
    if (moreOddThanEven) {
      if (isOdd) {
        return Utils.getRandomOddNumber();
      } else {
        return Utils.getRandomEvenNumber();
      }
    } else {
      if (isOdd) {
        return Utils.getRandomEvenNumber();
      } else {
        return Utils.getRandomOddNumber();
      }
    }
  }
}
