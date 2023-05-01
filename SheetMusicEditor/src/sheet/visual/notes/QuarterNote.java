package sheet.visual.notes;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import visual.statik.described.Content;

/**
 * A class that encapsulates a QuarterNote, how to draw it, and what sound it makes.
 * 
 * @author Benjamin Huber
 * @version 4/10/2023
 */
public class QuarterNote extends Note
{
  private final double size = .25;
  private final int type = 4;

  /**
   * Creates a QuarterNote CompositeContent object. Sets stroke to null to use the default stroke.
   * 
   * @param x
   *          the x coordinate for the top-left corner of this measure.
   * @param y
   *          the y coordinate for the top-left corner of this measure.
   * @param height
   *          the height of the quarter note.
   * @param noteColor
   *          the Color of the quarter note
   */
  public QuarterNote(final double x, final double y, final double height, final Color noteColor)
  {
    super(0, 0);

    final double width = height * GOLDEN_RATIO;

    Ellipse2D.Double head = new Ellipse2D.Double(-0.5 * width + x, -0.5 * height + y, width,
        height);
    Content shape = new Content(head, noteColor, noteColor, null);

    this.add(shape);

    Rectangle2D.Double shaft = new Rectangle2D.Double(0.5 * width - SHAFT_WIDTH + x,
        -3.5 * height + y, SHAFT_WIDTH, 3.5 * height);
    shape = new Content(shaft, noteColor, noteColor, null);

    this.add(shape);
  }

  @Override
  public double getSize()
  {
    return size;
  }
  
  @Override
  public int getType()
  {
    return type;
  }
}
