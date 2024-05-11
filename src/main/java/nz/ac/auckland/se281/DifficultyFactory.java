package nz.ac.auckland.se281;

public class DifficultyFactory {
  public static Difficulty createDifficulty(String type) {
    switch (type) {
      case "EASY":
        return new EasyDifficulty();  
      case "MEDIUM":
        return new MediumDifficulty();
      case "HARD":
        return new HardDifficulty();
      default: 
        System.out.println("Incorrect Difficulty");
        break;
    }
    return null;
  }
}
