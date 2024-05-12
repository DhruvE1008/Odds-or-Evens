package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;

public class HardDifficulty implements BotDifficulty {
  private Choice choice;
  private String newStrat;
  public HardDifficulty(Choice choice) {
    this.choice = choice;
  }
  /** */
  @Override
  public int getFingers(int roundNumber, int oddNum, int evenNum, boolean botWin, String currentStrat) {
    Bot bot;
    if (roundNumber < 4) {
      bot = new Bot(new RandomStrategy());
    } else {
      if (botWin) {
        if (currentStrat == "random") {
          bot = new Bot(new RandomStrategy());
          newStrat = "random";
        } else {
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
            bot = new Bot(new RandomStrategy());
          }
          newStrat = "top";
        }
      } else {
        if (currentStrat == "random") {
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
            bot = new Bot(new RandomStrategy());
          }
          newStrat = "top";
        } else {
          bot = new Bot(new RandomStrategy());
          newStrat = "random";
        }
      }
    }
    return bot.play();
  }

  @Override
  public String getStrat() {
    return newStrat;
  }
}
