package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.Choice;
import nz.ac.auckland.se281.Main.Difficulty;

/** This class represents the Game is the main entry point. */
public class Game {
  private int RoundNumber = 1;
  private String name;

  public void newGame(Difficulty difficulty, Choice choice, String[] options) {
    name = options[0];
    MessageCli.WELCOME_PLAYER.printMessage(name);
  }

  public void play() {
    String input;
    MessageCli.START_ROUND.printMessage(String.valueOf(RoundNumber));
    RoundNumber++;
    System.out.println(MessageCli.ASK_INPUT);
    input = Utils.scanner.nextLine();
    MessageCli.PRINT_INFO_HAND.printMessage(name, input);
  }

  public void endGame() {}

  public void showStats() {}
}
