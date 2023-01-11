import java.util.NoSuchElementException;

/**
 * A class used to perform various functions revolving around a linked list
 * 
 * @author Quentin Holle
 */
public class InventoryList {

  private LinkedBox head;
  private LinkedBox tail;
  private int size;
  private int yellowCount;
  private int blueCount;
  private int brownCount;

  /**
   * This method determines the size of the linked list
   * 
   * @return the size of the linked list
   */
  public int size() {
    if (head == null && tail == null) {
      return 0;
    }

    return size;
  }

  /**
   * This method checks if the linked list is empty
   * 
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() {
    if (head == null && tail == null) {
      return true;
    }
    return false;
  }

  /**
   * This method clears the linked list and resets all size and count values to zero
   */
  public void clear() {
    head = null;
    tail = null;
    this.yellowCount = 0;
    this.blueCount = 0;
    this.brownCount = 0;
    this.size = 0;
  }

  /**
   * This method checks in a given person to the room
   * 
   * @param index the index to locate a box at
   * @return the box at the given index
   * @throws an IndexOutOfBoundsException if the index given is less than 0 or greater than the size
   */
  public Box get​(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("The index argument given was out of bounds.");
    }
    int count = 0;
    LinkedBox current = head;
    while (head.getNext() != null && count < index) {
      count++;
      current = current.getNext();
    }

    if (count == index) {
      return current.getBox();
    }

    return null;
  }

  /**
   * This method adds a brown box to the end of the linked list
   * 
   * @throws IllegalArgumentException if the box is null or the color of the box isn't brown
   */
  public void addBrown​(Box brownBox) {
    if (brownBox == null || brownBox.getColor() != Color.BROWN) {
      throw new IllegalArgumentException("Box color not brown or box object was null.");
    }
    LinkedBox brown = new LinkedBox(brownBox);

    if (this.isEmpty()) {
      head = brown;
      tail = brown;
    }

    else {
      if (size >= 2) {
        tail.setNext(brown);
      } else {
        head.setNext(brown);
      }
      tail = brown;
    }
    size++;
    brownCount++;
  }

  /**
   * This method adds a yellow box to the start of the linked list
   * 
   * @throws IllegalArgumentException if the box is null or the color of the box isn't yellow
   */
  public void addYellow(Box yellowBox) {
    if (yellowBox == null || yellowBox.getColor() != Color.YELLOW) {
      throw new IllegalArgumentException("Box color not yellow or box object was null.");
    }
    LinkedBox yellow = new LinkedBox(yellowBox);

    if (this.isEmpty()) {
      head = yellow;
      tail = yellow;
    }

    else {
      yellow.setNext(head);
      head = yellow;
    }
    size++;
    yellowCount++;
  }

  /**
   * This method adds a blue box to the linked list following all of the yellow boxes
   * 
   * @throws IllegalArgumentException if the box is null or the color of the box isn't blue
   */
  public void addBlue(Box blueBox) {
    if (blueBox == null || blueBox.getColor() != Color.BLUE) {
      throw new IllegalArgumentException("Box color not blue or box object was null.");
    }
    LinkedBox blue = new LinkedBox(blueBox);

    if (this.isEmpty()) {
      head = blue;
      tail = blue;
    }

    if (this.yellowCount < 1) {
      blue.setNext(head);
      head = blue;
    }

    else {
      LinkedBox current = head;
      LinkedBox previous = null;
      LinkedBox next = null;
      if (yellowCount <= 1) {
        previous = head;
      }
      for (int i = 0; i < yellowCount - 1; i++) {
        current = current.getNext();
        previous = current;
      }

      current = head;
      for (int i = 0; i < yellowCount; i++) {
        current = current.getNext();
        next = current;
      }

      previous.setNext(blue);
      blue.setNext(next);

      if (this.brownCount < 1) {
        tail = blue;
      }
    }

    size++;
    blueCount++;
  }

  /**
   * This method removes first yellow box of the linked list
   * 
   * @return the box removed from the linked list
   * @throws NoSuchElementException if there are no yellow boxes in the linked list
   */
  public Box removeYellow() {
    if (this.isEmpty() || head.getBox().getColor() != Color.YELLOW) {
      throw new NoSuchElementException("List contained no yellow boxes.");
    }

    LinkedBox tempYellow = head;

    if (this.size >= 2) {
      head.setNext(head.getNext());
      head = tempYellow.getNext();

    } else {
      head = null;
      tail = null;
    }

    size--;
    yellowCount--;
    return tempYellow.getBox();
  }

  /**
   * This method removes final brown box of the linked list
   * 
   * @return the box removed from the linked list
   * @throws NoSuchElementException if there are no brown boxes in the linked list
   */
  public Box removeBrown() {
    if (this.isEmpty() || tail.getBox().getColor() != Color.BROWN) {
      throw new NoSuchElementException("List contained no brown boxes.");
    }

    LinkedBox brownRemoved = tail;

    if (this.size >= 2) {
      LinkedBox current = head;

      while (current.getNext() != tail) {
        current = current.getNext();
      }

      tail = current;
      current.setNext(null);

    } else {
      head = null;
      tail = null;
    }

    size--;
    brownCount--;
    return brownRemoved.getBox();
  }

  public int getBlueCount() {
    return blueCount;
  }

  public int getBrownCount() {
    return brownCount;
  }

  public int getYellowCount() {
    return yellowCount;
  }

  /**
   * This method removes the box with the corresponding inventory number in the linked list
   * 
   * @param inventoryNumber the inventory number of the box to be removed
   * @return the box removed from the linked list
   * @throws NoSuchElementException if the inventory number cannot be found
   */
  public Box removeBox​(int inventoryNumber) {
    LinkedBox current = head;
    LinkedBox previous = null;

    if (size == 0) {
      throw new NoSuchElementException("Could not locate a box with provided inventory number");
    }
    if (current.getBox().getInventoryNumber() == inventoryNumber) {
      Box removed = current.getBox();
      if (size >= 2) {
        head = current.getNext();
        current = current.getNext();
        current.setNext(current.getNext());
      } else {
        if (current.getBox() == tail.getBox()) {
          tail = current;
          tail.setNext(null);
        }
        head = null;
        tail = null;
      }
      if (removed.getColor() == Color.BROWN) {
        brownCount--;
      }
      if (removed.getColor() == Color.BLUE) {
        blueCount--;
      }
      if (removed.getColor() == Color.YELLOW) {
        yellowCount--;
      }
      size--;
      return removed;
    }

    previous = current;

    while ((current = current.getNext()) != null) {
      if (current.getBox().getInventoryNumber() == inventoryNumber) {
        Box removed = current.getBox();
        if (size >= 2) {
          previous.setNext(current.getNext());
        } else {
          head = null;
          tail = null;
        }
        if (current.getBox() == tail.getBox()) {
          tail = previous;
          tail.setNext(null);
        }
        if (removed.getColor() == Color.BROWN) {
          brownCount--;
        }
        if (removed.getColor() == Color.BLUE) {
          blueCount--;
        }
        if (removed.getColor() == Color.YELLOW) {
          yellowCount--;
        }
        size--;
        return removed;
      }

      previous = current;
    }

    throw new NoSuchElementException("Could not locate a box with provided inventory number");
  }

  /**
   * This method creates a formatted representation of the linked list
   * 
   * @return a formatted string of the linked list
   */
  @Override
  public java.lang.String toString() {
    String boxes = "";
    if (!this.isEmpty()) {
      for (int i = 0; i < size; i++) {
        boxes = boxes + get​(i) + " -> ";
      }
      boxes = boxes + "END";
    }
    return boxes;
  }
}
