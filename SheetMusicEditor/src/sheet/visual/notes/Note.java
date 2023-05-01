package sheet.visual.notes;

import sheet.visual.SheetElement;

/**
 * An abstract class that defines what all notes have and can do.
 * 
 * @author Benjamin Huber
 * @version 4/15/2023
 */
public abstract class Note extends SheetElement
{
  
  protected static final double GOLDEN_RATIO = 1.61803398875;
  protected static final double SHAFT_WIDTH = 1.0;
  /**
   * A note must have a location.
   * 
   * @param x location of note
   * @param y location of note
   */
  public Note(final double x, final double y)
  {
    super(x, y);
  }
}
