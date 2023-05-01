package sheet.visual.dynamic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import gui.SheetMusicPaper;
import sheet.visual.Measure;
import visual.dynamic.described.DescribedSprite;
import visual.statik.described.AggregateContent;
import visual.statik.described.Content;

/**
 * A sprite that moves across a measure as it is playing.
 * 
 * @author Benjamin Huber
 * @version 4/30/2023
 *
 */
public class PlayingSprite extends DescribedSprite
{
  private static final Color BAR_COLOR = new Color(0, 0, 0, 127);
  private static final Stroke BAR_STROKE = new BasicStroke();

  /**
   * Creates a "play bar" that starts and ends at the specified coordinates.
   * 
   * @param startx
   *          the start x coordinate
   * @param starty
   *          the start y coordinate
   * @param endx
   *          the end x coordinate
   * @param endy
   *          the end y coordinate
   * @param time
   *          the time to animate
   */
  public PlayingSprite(final double startx, final double starty, final double endx,
      final double endy, final int time)
  {
    super();
    Rectangle2D playBar = new Rectangle2D.Double(-5, -5, 5, Measure.getHeight() + 10);

    Content start = new Content(playBar, BAR_COLOR, BAR_COLOR, BAR_STROKE);

    AggregateContent ac = new AggregateContent();
    ac.add(start);

    this.addKeyTime(0, new Point2D.Double(startx, starty), Double.valueOf(0), Double.valueOf(1),
        ac);
    this.addKeyTime(((int) (0.25 * time)) - 1, new Point2D.Double(endx, starty), Double.valueOf(0),
        Double.valueOf(1), ac);

    this.addKeyTime(((int) (0.25 * time)),
        new Point2D.Double(startx, starty + Measure.getHeight() + SheetMusicPaper.getVerticalGap()),
        Double.valueOf(0), Double.valueOf(1), ac);
    this.addKeyTime(((int) (0.5 * time)) - 1,
        new Point2D.Double(endx, starty + Measure.getHeight() + SheetMusicPaper.getVerticalGap()),
        Double.valueOf(0), Double.valueOf(1), ac);

    this.addKeyTime(((int) (0.5 * time)),
        new Point2D.Double(startx,
            starty + Measure.getHeight() * 2 + SheetMusicPaper.getVerticalGap() * 2),
        Double.valueOf(0), Double.valueOf(1), ac);
    this.addKeyTime(((int) (0.75 * time)) - 1,
        new Point2D.Double(endx,
            starty + Measure.getHeight() * 2 + SheetMusicPaper.getVerticalGap() * 2),
        Double.valueOf(0), Double.valueOf(1), ac);

    this.addKeyTime(((int) (0.75 * time)), new Point2D.Double(startx, endy), Double.valueOf(0),
        Double.valueOf(1), ac);
    this.addKeyTime(time, new Point2D.Double(endx, endy), Double.valueOf(0), Double.valueOf(1), ac);
  }
}
