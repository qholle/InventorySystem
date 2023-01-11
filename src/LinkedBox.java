/**
 * A class used to construct and modify linked box objects
 * 
 * @author Quentin Holle
 */
public class LinkedBox {

  private Box box;
  private LinkedBox next;

  /**
   * This method constructs a LinkedBox object and sets the values of the box and next fields
   * 
   * @param box the box to become a LinkedBox
   */
  public LinkedBox(Box box) {
    this.box = box;
    this.next = null;
  }

  /**
   * This method constructs a LinkedBox object and sets the values of the box and next fields
   * 
   * @param box  the box to become a LinkedBox
   * @param next LinkedBox to be linked to
   */
  public LinkedBox(Box box, LinkedBox next) {
    this.box = box;
    this.next = next;
  }

  /**
   * This method returns the current box object
   * 
   * @return the current box object
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * This method returns the next box object
   * 
   * @return the next linked box object
   */
  public LinkedBox getNext() {
    return this.next;
  }

  /**
   * This method sets the next box object
   * 
   * @param the next linked box object
   */
  public void setNext(LinkedBox next) {
    this.next = next;
  }

  /**
   * This method constructs a formatted string representation of the box
   * 
   * @return a formatted string representation of the box
   */
  public String toString() {
    if (next != null) {
      return box.toString() + " -> ";
    } else {
      return box.toString() + " -> END";
    }
  }
}
