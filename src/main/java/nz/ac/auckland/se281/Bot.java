package nz.ac.auckland.se281;

public class Bot {
  private Strategy strategy;
  public Bot(Strategy strategy) {
    this.strategy = strategy;
  }
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }
}
