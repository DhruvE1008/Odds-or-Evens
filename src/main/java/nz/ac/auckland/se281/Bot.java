package nz.ac.auckland.se281;

public class Bot {
  private Strategy strategy;
  public Bot(Strategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  public void play() {
    int fingers = strategy.getFingerAmount();
    MessageCli.PRINT_INFO_HAND.printMessage("HAL-9000", String.valueOf(fingers));
  }
}
