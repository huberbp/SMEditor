package sheet.visual;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import visual.statik.CompositeContent;
import visual.statik.described.Content;

/**
 * A class that defines a musical measure as a piece of Content.
 * 
 * @author Benjamin Huber
 * @version 4/10/2023
 */
public class Measure extends CompositeContent
{
  public static final double MEASURE_MARGIN_HEIGHT = 12.5;
  public static final double MEASURE_WIDTH = 20 * MEASURE_MARGIN_HEIGHT;

  private static final double MEASURE_END_WIDTH = 2.0;
  private static final Color MEASURE_COLOR = Color.BLACK;

  private double x;
  private double y;

  /**
   * An enum that tells us where the position of a note should be on a Measure.
   * 
   * @author Benjamin Huber
   * @version 4/10/2023
   */
  public static enum Positions
  {
    // ORDER MATTERS HERE
    HIGH_F("HIGH_F", 0, 'F', 0), 
    HIGH_E("HIGH_E", 0.5 * MEASURE_MARGIN_HEIGHT, 'E', 0), 
    D("D", 1.0 * MEASURE_MARGIN_HEIGHT, 'D', 0), 
    C("C", 1.5 * MEASURE_MARGIN_HEIGHT, 'C', 0), 
    B("B", 2.0 * MEASURE_MARGIN_HEIGHT, 'B', -1),
    A("A", 2.5 * MEASURE_MARGIN_HEIGHT, 'A', -1), 
    G("G", 3.0 * MEASURE_MARGIN_HEIGHT, 'G', -1), 
    LOW_F("LOW_F", 3.5 * MEASURE_MARGIN_HEIGHT, 'F', -1), 
    LOW_E("LOW_E", 4.0 * MEASURE_MARGIN_HEIGHT, 'E', -1),
    REST("REST", 2.0 * MEASURE_MARGIN_HEIGHT, 'R', 0);

    private final String name;
    private final double height;
    private final char note;
    private final int octave;

    Positions(final String name, final double height, final char note, final int octave)
    {
      this.name = name;
      this.height = height;
      this.note = note;
      this.octave = octave;
    }

    /**
     * Accessor method for Positions.name.
     * 
     * @return the String representation of the name
     */
    public String getName()
    {
      return name;
    }

    /**
     * Accessor method for Positions.note.
     * 
     * @return the String representation of the note
     */
    public char getNote()
    {
      return note;
    }

    /**
     * Accessor method for Positions.height.
     * 
     * @return the height of center of the note on the measure
     */
    public double getHeight()
    {
      return height;
    }

    /**
     * Accessor method for Positions.octave.
     * 
     * @return the octave of the note
     */
    public int getOctave()
    {
      return octave;
    }
  }

  /**
   * Creates a Measure CompositeContent object. Sets stroke to null to use the default stroke.
   * 
   * @param x
   *          the x coordinate for the top-left corner of this measure.
   * @param y
   *          the y coordinate for the top-left corner of this measure.
   */
  public Measure(final double x, final double y)
  {
    super();

    this.setLocation(x, y);

    for (double offset = 0; offset < (MEASURE_MARGIN_HEIGHT * 5) - 0.1; offset = offset
        + MEASURE_MARGIN_HEIGHT)
    {
      Line2D.Double line = new Line2D.Double(0, offset, MEASURE_WIDTH, offset);
      Content shape = new Content(line, MEASURE_COLOR, MEASURE_COLOR, null);
      this.add(shape);
    }

    Rectangle2D.Double endBar = new Rectangle2D.Double(MEASURE_WIDTH - MEASURE_END_WIDTH, 0,
        MEASURE_END_WIDTH, MEASURE_MARGIN_HEIGHT * 4);

    Content shape = new Content(endBar, MEASURE_COLOR, MEASURE_COLOR, null);
    this.add(shape);
  }

  /**
   * Returns the visual width of a Measure. Included as a faster alternative to getBounds2D(false)
   * to avoid bounds calculation, since we have that data on hand anyways for editing convenience.
   * 
   * @return the width of a measure
   */
  public double getWidth()
  {
    return MEASURE_WIDTH;
  }

  /**
   * Returns the visual height of a Measure. Included as a faster alternative to getBounds2D(false)
   * to avoid bounds calculation, since we have that data on hand anyways for editing convenience.
   * 
   * @return the height of a measure
   */
  public static double getHeight()
  {
    return MEASURE_MARGIN_HEIGHT * 4;
  }

  @Override
  public void setLocation(final double x2, final double y2)
  {
    super.setLocation(x2, y2);

    this.x = x2;
    this.y = y2;
  }

  /**
   * Accessor method for the x of the top-left corner of the Measure.
   * 
   * @return x
   */
  public double getX()
  {
    return this.x;
  }

  /**
   * Accessor method for the y of the top-left corner of the Measure.
   * 
   * @return y
   */
  public double getY()
  {
    return this.y;
  }
}
