package nz.ac.auckland.se281;

/**
 * Class Bot which represents the AI and has methods setStrategy and play. takes the initial
 * strategy as the input to the constructor.
 */
public class Bot {
  private Strategy strategy;
  private boolean moreOddThanEven;
  private boolean isOdd;

  /**
   * Constructor for class Bot which takes in a strategy as its input.
   *
   * @param strategy the strategy the AI will be using
   */
  public Bot(Strategy strategy) {
    this.strategy = strategy;
  }

  /**
   * Second constructor for class Bot which now also takes whether the user has input more odd
   * numbers than even numbers.
   *
   * @param strategy the strategy that the AI will be using
   * @param moreOddThanEven states whether the user has more odd numbers than even numbers.
   */
  public Bot(Strategy strategy, Boolean moreOddThanEven, Boolean isOdd) {
    this.strategy = strategy;
    this.moreOddThanEven = moreOddThanEven;
    this.isOdd = isOdd;
  }

  /**
   * Allows for the code to change the strategy if wanted during operation.
   *
   * @param strategy the new strategy that the AI will be using
   */
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  /**
   * Calculates the number of fingers the AI chooses.
   *
   * @return the number of fingers the AI chose
   */
  public int play() {
    int fingers = strategy.getFingerAmount(moreOddThanEven, isOdd);
    MessageCli.PRINT_INFO_HAND.printMessage("HAL-9000", String.valueOf(fingers));
    return fingers;
  }
}
