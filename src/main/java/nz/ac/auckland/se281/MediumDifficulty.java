package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;

public class MediumDifficulty implements BotDifficulty {
  private Choice choice;
  private String currentStrat;

  public MediumDifficulty(Choice choice) {
    this.choice = choice;
  }
  /**
   * 
   */
  @Override
  public int getFingers(int roundNumber, int oddNum, int evenNum, boolean botWin, String currentStrat) {
    Bot bot;
    if (roundNumber < 4) {
      bot = new Bot(new RandomStrategy());
      currentStrat = "Random";
    } else {
      currentStrat = "Top";
      if (oddNum > evenNum) {
        if (choice == Choice.ODD) {
          bot = new Bot(new TopStrategy(), true, true);
        } else {
          bot = new Bot(new TopStrategy(), true, false);
        }
      } else if (evenNum > oddNum) {
        if (choice == Choice.ODD) {
          bot = new Bot(new TopStrategy(), false, true);
        } else {
          bot = new Bot(new TopStrategy(), false, false);
        }
      } else {
        currentStrat = "Random";
        bot = new Bot(new RandomStrategy());
        }
    }
    return bot.play();
  }
  
  @Override public String getStrat() {
    return currentStrat;
  }
}
