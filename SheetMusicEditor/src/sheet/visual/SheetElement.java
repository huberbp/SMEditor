package sheet.visual;

import visual.statik.CompositeContent;

/**
 * A class that defines a common ground between all drawn "Sheet Elements".
 * 
 * @author Benjamin Huber
 * @version 4/15/2023
 */
public abstract class SheetElement extends CompositeContent
{
  
  /**
   * A class that defines a common ground between all drawn "Sheet Elements".
   * 
   * @param x
   *          the x-location of the sheet element
   * @param y
   *          the y-location of the sheet element
   */
  public SheetElement(final double x, final double y)
  {
    super();

    this.setLocation(x, y);
  }

  /**
   * Returns the size of the SheetElement (how much of the measure it takes up).
   * 
   * @return the size of the SheetElement
   */
  public abstract double getSize();
  
  /**
   * Returns the type of the note that is defined according to the multimedia2 library.
   * 
   * @return the type of note defined according to the multimedia2 library
   */
  public abstract int getType();
}
