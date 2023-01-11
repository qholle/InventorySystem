import java.util.NoSuchElementException;

/**
 * A class used to test linked list functions
 * 
 * @author Quentin Holle
 */
public class InventorySystemTester {

  /**
   * This method checks for the correctness of the InventoryList.clear() method
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testClear() {
    InventoryList clear = new InventoryList();
    clear.addBlue(new Box(Color.BLUE));
    clear.addYellow(new Box(Color.YELLOW));
    clear.addBlue(new Box(Color.BLUE));
    clear.addBrown​(new Box(Color.BROWN));
    clear.clear();

    // Test 1: is list size 0?
    if (clear.size() != 0) {
      System.out.println("Actual Size: " + clear.size() + " Expected Size: 0");
      return false;
    }

    // Test 2: is list empty?
    if (!clear.isEmpty()) {
      System.out.println("List wasn't empty: " + clear.toString());
      return false;
    }

    // Test 3: are color counts correct?
    if (clear.getBlueCount() != 0 || clear.getBrownCount() != 0 || clear.getYellowCount() != 0) {
      System.out.println("Color counts should be zero. Blue: " + clear.getBlueCount() + " Brown: "
          + clear.getBrownCount() + " Yellow: " + clear.getYellowCount());
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of the multiple InventoryList.add() methods
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testAddBoxes() {
    InventoryList addBoxes = new InventoryList();
    addBoxes.clear();

    // Test 1: Brown box added to empty list
    addBoxes.addBrown​(new Box(Color.BROWN));
    if (addBoxes.size() != 1) {
      System.out.println("Actual Size: " + addBoxes.size() + " Expected Size: 1");
      return false;
    }
    if (addBoxes.getBrownCount() != 1) {
      System.out.println("Actual Brown Count: " + addBoxes.getBrownCount() + " Expected: 1");
      return false;
    }
    if (!addBoxes.toString().equals("BROWN 5 -> END")) {
      System.out.println("Actual: " + addBoxes.toString() + " \"BROWN 5 -> END\"");
      return false;
    }

    addBoxes.clear();

    // Test 2: Blue box added to empty list
    addBoxes.addBlue(new Box(Color.BLUE));
    if (addBoxes.size() != 1) {
      System.out.println("Actual Size: " + addBoxes.size() + " Expected Size: 1");
      return false;
    }
    if (addBoxes.getBlueCount() != 1) {
      System.out.println("Actual Blue Count: " + addBoxes.getBlueCount() + " Expected: 1");
      return false;
    }
    if (!addBoxes.toString().equals("BLUE 6 -> END")) {
      System.out.println("Actual: " + addBoxes.toString() + " \"BLUE 6 -> END\"");
      return false;
    }

    addBoxes.clear();

    // Test 3: Yellow box added to empty list
    addBoxes.addYellow(new Box(Color.YELLOW));
    if (addBoxes.size() != 1) {
      System.out.println("Actual Size: " + addBoxes.size() + " Expected Size: 1");
      return false;
    }
    if (addBoxes.getYellowCount() != 1) {
      System.out.println("Actual Yellow Count: " + addBoxes.getYellowCount() + " Expected: 1");
      return false;
    }
    if (!addBoxes.toString().equals("YELLOW 7 -> END")) {
      System.out.println("Actual: " + addBoxes.toString() + " \"YELLOW 7 -> END\"");;
      return false;
    }

    // Test 4: Sequence of boxes added to a list
    addBoxes.addBlue(new Box(Color.BLUE));
    addBoxes.addYellow(new Box(Color.YELLOW));
    addBoxes.addBrown​(new Box(Color.BROWN));
    addBoxes.addBlue(new Box(Color.BLUE));
    addBoxes.addBrown​(new Box(Color.BROWN));
    if (addBoxes.getYellowCount() != 2 || addBoxes.getBlueCount() != 2
        || addBoxes.getBrownCount() != 2) {
      System.out.println("Yellow: " + addBoxes.getYellowCount() + " Brown: "
          + addBoxes.getBrownCount() + " Blue: " + addBoxes.getBlueCount());
      return false;
    }
    if (!addBoxes.toString()
        .equals("YELLOW 9 -> YELLOW 7 -> BLUE 11 -> BLUE 8 -> BROWN 10 -> BROWN 12 -> END")) {
      System.out.println("Actual: " + addBoxes.toString() + " Expected: "
          + "\"YELLOW 9 -> YELLOW 7 -> BLUE 11 -> BLUE 8 -> BROWN 10 -> BROWN 12 -> END\"");
      return false;
    }

    // Test 5: addYellow called on a non-yellow box
    try {
      addBoxes.addYellow(new Box(Color.BLUE));
    } catch (IllegalArgumentException e) {
      return true;
    }

    System.out.println("A exception wasn't thrown when it should have been.");
    return false;
  }

  /**
   * This method checks for the correctness of the multiple InventoryList.remove() methods
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testRemoveBoxes() {
    InventoryList removeBoxes = new InventoryList();
    removeBoxes.clear();

    // Test 1: Remove brown box in a list
    removeBoxes.addBrown​(new Box(Color.BROWN));
    removeBoxes.addYellow(new Box(Color.YELLOW));
    removeBoxes.addBrown​(new Box(Color.BROWN));

    removeBoxes.removeBrown();
    if (removeBoxes.size() != 2) {
      System.out.println("Actual Size: " + removeBoxes.size() + " Expected Size: 2");
      return false;
    }
    if (removeBoxes.getYellowCount() != 1 || removeBoxes.getBrownCount() != 1) {
      System.out.println(
          "Yellow: " + removeBoxes.getYellowCount() + " Brown: " + removeBoxes.getBrownCount());
      return false;
    }
    if (!removeBoxes.toString().equals("YELLOW 15 -> BROWN 14 -> END")) {
      System.out.println(
          "Actual: " + removeBoxes.toString() + " Expected: " + "\"YELLOW 15 -> BROWN 14 -> END\"");
      return false;
    }

    removeBoxes.clear();

    // Test 2: Remove yellow box in a list
    removeBoxes.addYellow(new Box(Color.YELLOW));
    removeBoxes.addBrown​(new Box(Color.BROWN));
    removeBoxes.addBlue(new Box(Color.BLUE));

    removeBoxes.removeYellow();
    if (removeBoxes.size() != 2) {
      System.out.println("Actual Size: " + removeBoxes.size() + " Expected Size: 2");
      return false;
    }
    if (removeBoxes.getYellowCount() != 0 || removeBoxes.getBrownCount() != 1
        || removeBoxes.getBlueCount() != 1) {
      System.out.println("Yellow: " + removeBoxes.getYellowCount() + " Brown: "
          + removeBoxes.getBrownCount() + "Blue: " + removeBoxes.getBlueCount());
      return false;
    }
    if (!removeBoxes.toString().equals("BLUE 19 -> BROWN 18 -> END")) {
      System.out.println(
          "Actual: " + removeBoxes.toString() + " Expected: " + "\"BLUE 19 -> BROWN 18 -> END\"");
      return false;
    }

    removeBoxes.clear();

    // Test 3: Remove box based off of inventory number
    removeBoxes.addYellow(new Box(Color.YELLOW));
    removeBoxes.addBrown​(new Box(Color.BROWN));
    removeBoxes.addBlue(new Box(Color.BLUE));

    removeBoxes.removeBox​(21);
    if (removeBoxes.size() != 2) {
      System.out.println("Actual Size: " + removeBoxes.size() + " Expected Size: 2");
      return false;
    }
    if (removeBoxes.getYellowCount() != 1 || removeBoxes.getBrownCount() != 0
        || removeBoxes.getBlueCount() != 1) {
      System.out.println("Yellow: " + removeBoxes.getYellowCount() + " Brown: "
          + removeBoxes.getBrownCount() + "Blue: " + removeBoxes.getBlueCount());
      return false;
    }
    if (!removeBoxes.toString().equals("YELLOW 20 -> BLUE 22 -> END")) {
      System.out.println(
          "Actual: " + removeBoxes.toString() + " Expected: " + "\"YELLOW 20 -> BLUE 22 -> END\"");
      return false;
    }

    removeBoxes.clear();

    // Test 4: Remove boxes from list containing only those boxes
    removeBoxes.addBlue(new Box(Color.BLUE));

    removeBoxes.removeBox​(23);
    if (!removeBoxes.toString().equals("")) {
      System.out.println("Actual: " + removeBoxes.toString() + " Expected: " + "\"\"");
      return false;
    }

    removeBoxes.addBrown​(new Box(Color.BROWN));

    removeBoxes.removeBrown();
    if (!removeBoxes.toString().equals("")) {
      System.out.println("Actual: " + removeBoxes.toString() + " Expected: " + "\"\"");
      return false;
    }

    removeBoxes.addYellow(new Box(Color.YELLOW));

    removeBoxes.removeYellow();
    if (!removeBoxes.toString().equals("")) {
      System.out.println("Actual: " + removeBoxes.toString() + " Expected: " + "\"\"");
      return false;
    }


    // Test 5: Remove boxes from list containing only those boxes
    removeBoxes.addBlue(new Box(Color.BLUE));
    removeBoxes.addBlue(new Box(Color.BLUE));
    removeBoxes.addYellow(new Box(Color.YELLOW));
    removeBoxes.addBrown​(new Box(Color.BROWN));
    removeBoxes.addBrown​(new Box(Color.BROWN));

    removeBoxes.removeBox​(30);

    if (removeBoxes.size() != 4) {
      System.out.println("Actual Size: " + removeBoxes.size() + " Expected Size: 4");
      return false;
    }
    if (removeBoxes.getYellowCount() != 1 || removeBoxes.getBrownCount() != 1
        || removeBoxes.getBlueCount() != 2) {
      System.out.println("Yellow: " + removeBoxes.getYellowCount() + " Brown: "
          + removeBoxes.getBrownCount() + "Blue: " + removeBoxes.getBlueCount());
      return false;
    }
    if (!removeBoxes.toString().equals("YELLOW 28 -> BLUE 27 -> BLUE 26 -> BROWN 29 -> END")) {
      System.out.println("Actual: " + removeBoxes.toString() + " Expected: "
          + "\"YELLOW 28 -> BLUE 27 -> BLUE 26 -> BROWN 29 -> END\"");
      return false;
    }

    removeBoxes.clear();

    // Test 6: removing inventory number not in list
    try {
      removeBoxes.removeBox​(123);
    } catch (NoSuchElementException e) {
      try {
        removeBoxes.removeBrown();
      } catch (NoSuchElementException f) {
        try {
          removeBoxes.removeYellow();
        } catch (NoSuchElementException g) {
          return true;
        }
      }
    }
    System.out.println("Exception not thrown from remove boxes methods.");
    return false;
  }

  /**
   * This method checks for the correctness of the InventoryList.get() method
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean testGetBoxes() {
    InventoryList getBoxes = new InventoryList();
    // Test 1: Get value that exists
    getBoxes.addYellow(new Box(Color.YELLOW));
    getBoxes.addYellow(new Box(Color.YELLOW));
    getBoxes.addBrown​(new Box(Color.BROWN));

    if (!getBoxes.get​(0).toString().equals("YELLOW 32")) {
      System.out.println("Actual: " + getBoxes.get​(0).toString() + " Expected: \"YELLOW 32\"");
      return false;
    }

    // Test 2: Invalid input
    try {
      getBoxes.get​(-1);
    } catch (IndexOutOfBoundsException e) {
      try {
        getBoxes.get​(5);
      } catch (IndexOutOfBoundsException f) {
        return true;
      }
    }
    System.out.println("Exception not thrown from get boxes method");
    return false;
  }

  /**
   * This method is a test suite method to run all test methods
   * 
   * @return true if the tests pass, false otherwise
   */
  public static boolean runAllTests() {
    System.out.println("testClear: " + testClear());
    System.out.println("testAddBoxes: " + testAddBoxes());
    System.out.println("testRemoveBoxes: " + testRemoveBoxes());
    System.out.println("testGetBoxes: " + testGetBoxes());
    return true;
  }

  /**
   * Main method prints out if all tests passed
   */
  public static void main(String[] args) {
    System.out.println("All tests passed: " + runAllTests());
  }
}
