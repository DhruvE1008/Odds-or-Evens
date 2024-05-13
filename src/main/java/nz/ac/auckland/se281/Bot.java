package nz.ac.auckland.se281;

/**
 * Class Bot which represents the AI and has methods setStrategy, getStrategy, switchStrategy and play. 
 * Takes the initial strategy as the input to the constructor.
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
   * Allows for the code to change the strategy if wanted during operation.
   *
   * @param strategy the new strategy that the AI will be using
   */
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  /**
   * Allows for the code to change the strategy if wanted during operation.
   * @param strategy the new strategy the AI will use
   * @param moreOddThanEven whether the user has input more odd numbers than even numbers
   * @param isOdd whether the user chose to play odd or even
   */
  public void setStrategy(Strategy strategy, boolean moreOddThanEven, boolean isOdd) {
    this.strategy = strategy;
    this.moreOddThanEven = moreOddThanEven;
    this.isOdd = isOdd;
  }

  /**
   * returns the last used strategy.
   * @return the strategy that has been last used
   */
  public Strategy getStrategy() {
    return strategy;
  }

  /**
   * Switches the strategy that was just used to the one that 
   * wasn't used.
   * @param moreOddThanEven whether the user has input more odd numbers than even
   * @param isOdd whether the user chose to play odd or even
   */
  public void switchStrategy(boolean moreOddThanEven, boolean isOdd) {
    if (strategy instanceof RandomStrategy) {
      setStrategy(new TopStrategy());
    } else {
      setStrategy(new RandomStrategy());
    }
    this.moreOddThanEven = moreOddThanEven;
    this.isOdd = isOdd;
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
