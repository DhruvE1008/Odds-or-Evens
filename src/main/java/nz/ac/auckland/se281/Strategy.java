package nz.ac.auckland.se281;

/**
 * The interface for the strategy classes and contains the abstract method of getFingerAmount that
 * all the strategy classes will have.
 */
public interface Strategy {
  int getFingerAmount(boolean difference, boolean isOdd);
}
