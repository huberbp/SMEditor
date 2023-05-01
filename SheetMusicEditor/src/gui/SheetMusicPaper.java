package gui;

import visual.dynamic.described.Stage;
import sheet.visual.Measure;
import sheet.visual.SheetElement;
import sheet.visual.dynamic.PlayingSprite;
import sheet.visual.Measure.Positions;
import sheet.visual.notes.QuarterNote;
import sheet.visual.rests.QuarterRest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import auditory.MusicPlayer;

/**
 * A Visualization that allows the user to hover over spots on a Measure and click on them to add
 * notes.
 * 
 * @author Benjamin Huber
 * @version 4/13/2023
 *
 */
public class SheetMusicPaper extends Stage implements MouseListener, ActionListener
{
  private static final double VERTICAL_GAP = 35;
  private static final double VERTICAL_OFFSET = 65;
  private static final double HORIZONTAL_OFFSET = 65;

  private static final int DEFAULT_TIME_STEP = 10;
  // private static double lenience = Measure.MEASURE_MARGIN_HEIGHT * .5;
  private int rows;
  private int tempo;

  private List<Measure> measures;
  private SheetElement[] notes;
  private Positions[] music;

  private MusicPlayer player;
  private PlayingSprite indicator;

  private int numerator;
  // private int denominator;

  /**
   * A SheetMusicViewer that contains all the data for Visualizing our music.
   */
  public SheetMusicPaper()
  {
    super(DEFAULT_TIME_STEP);
    this.tempo = 120;
    this.rows = 4;
    measures = new ArrayList<>();

    numerator = 4;
    // denominator = 4;

    notes = new SheetElement[rows * numerator * 4];
    music = new Positions[rows * numerator * 4];

    player = new MusicPlayer();

    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        Measure measure = new Measure(HORIZONTAL_OFFSET + Measure.MEASURE_WIDTH * j,
            VERTICAL_OFFSET + VERTICAL_GAP * i + Measure.MEASURE_MARGIN_HEIGHT * 4 * i);
        measures.add(measure);
        this.add(measure);
      }
    }

    for (int i = 0; i < rows; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        double vertical = VERTICAL_OFFSET + VERTICAL_GAP * i
            + Measure.MEASURE_MARGIN_HEIGHT * 4 * i;
        double horizontal = HORIZONTAL_OFFSET;

        for (int k = 0; k < numerator; k++)
        {
          QuarterRest quarterRest = new QuarterRest(getNextXPosition() + horizontal,
              Measure.Positions.B.getHeight() + vertical);
          this.add(quarterRest);
          notes[i * 16 + j * 4 + k] = quarterRest;
          music[i * 16 + j * 4 + k] = Positions.REST;
        }
      }
    }

    this.addMouseListener(this);
  }

  /**
   * A method that returns the next x-coordinate for where a note should go on this Measure.
   * 
   * @return the next x-coordinate for where a note should go on this measure.
   */
  public double getNextXPosition()
  {
    double offset = Measure.MEASURE_WIDTH / (1 + numerator);

    // Iterate by fractions later to support eight/sixteenth notes WRONG RIGHT NOW
    for (int i = 0; i < notes.length; i++)
    {
      if (notes[i] == null)
      {
        double nthInRow = i % 16;
        int nthMeasure = (int) nthInRow / numerator;
        return offset + offset * nthInRow + offset * nthMeasure;
      }
    }
    return -1;
  }

  /**
   * A method that gets the X Position of a note.
   * 
   * @param index
   *          the index of the note
   * @return the X Position of a certain index-ed note.
   */
  public double getNoteXPosition(final int index)
  {
    double offset = Measure.MEASURE_WIDTH / (1 + numerator);
    return HORIZONTAL_OFFSET + offset + offset * (index % 16)
        + offset * ((int) (index % 16) / numerator);
  }

  /**
   * A method that gets the Y Position of a note.
   * 
   * @param index
   *          the index of the note
   * @return the Y Position of a certain index-ed note.
   */
  public double getNoteYPosition(final int index)
  {
    return VERTICAL_OFFSET + VERTICAL_GAP * (index / 16)
        + Measure.MEASURE_MARGIN_HEIGHT * 4 * (index / 16);
  }

  /**
   * Remove element at index from list.
   * 
   * @param index
   *          the element to remove
   */
  public void removeNote(final int index)
  {
    if (index > notes.length || notes[index] == null)
    {
      return;
    }
    else
    {
      this.remove(notes[index]);
      notes[index] = null;
      music[index] = Positions.REST;
    }
  }

  @Override
  public void mouseClicked(final MouseEvent e)
  {
    Point location = e.getPoint();
    int button = e.getButton();

    if (button == MouseEvent.BUTTON3)
    {
      Point point = getGridCoordinates(location.x, location.y);
      if (point == null)
      {
        return;
      }
      int index = 16 * point.y + point.x;
      this.removeNote(index);
      QuarterRest rest = new QuarterRest(this.getNoteXPosition(index),
          this.getNoteYPosition(index) + Positions.REST.getHeight());
      notes[index] = rest;
      music[index] = Positions.REST;
      this.add(rest);
    }
    else if (button == MouseEvent.BUTTON1)
    {
      Point point = getGridCoordinates(location.x, location.y);
      if (point == null)
      {
        return;
      }
      int index = 16 * point.y + point.x;
      // Find the position of the note on the measure.
      double topHeight = this.getNoteYPosition(index);
      double measureLocation = location.y - topHeight - Measure.MEASURE_MARGIN_HEIGHT / 4;
      Positions measureNote = Positions.B;
      for (int i = 0; i < Positions.values().length; i++)
      {
        if (measureLocation < Positions.values()[i].getHeight())
        {
          measureNote = Positions.values()[i];
          measureLocation = measureNote.getHeight();
          break;
        }
      }
      this.removeNote(index);
      QuarterNote note = new QuarterNote(this.getNoteXPosition(index),
          this.getNoteYPosition(index) + measureLocation, Measure.MEASURE_MARGIN_HEIGHT,
          Color.BLACK);
      notes[index] = note;
      music[index] = measureNote;
      this.add(note);
    }
  }

  @Override
  public void mousePressed(final MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(final MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(final MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(final MouseEvent e)
  {
    // TODO Auto-generated method stub

  }

  /**
   * Helper method to figure out where on our "Grid" are our notes.
   * 
   * @param xcoord
   *          the x-coordinate of the mouse
   * @param ycoord
   *          the y-coordinate of the mouse
   * @return the grid coordinates of the note
   */
  private Point getGridCoordinates(final double xcoord, final double ycoord)
  {
    // get X-value
    int x = -1;
    double xlocation = xcoord - HORIZONTAL_OFFSET;

    if (xlocation < 0)
    {
      return null;
    }

    Dimension d = this.getView().getSize();
    double width = d.getWidth() - 100;

    double noteSquareX = width / 16;
    double noteSquareCur = noteSquareX;
    for (int i = 0; i < 16; i++)
    {
      if (xlocation < noteSquareCur)
      {
        x = i;
        break;
      }
      noteSquareCur += noteSquareX;
    }

    // Get y value
    int y = -1;

    double ylocation = ycoord - VERTICAL_OFFSET;

    if (ylocation < 0)
    {
      return null;
    }

    for (int i = 1; i < 5; i++)
    {
      double upper = (i - 1) * VERTICAL_GAP + 4 * Measure.MEASURE_MARGIN_HEIGHT * i;
      double lower = upper - Measure.MEASURE_MARGIN_HEIGHT * 4;
      if (ylocation > lower && ylocation < upper)
      {
        y = i - 1;
      }
    }

    if (y == -1 || x == -1)
    {
      return null;
    }

    return new Point(x, y);
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "play":
        if (!player.isPlaying())
        {
          int time = (int) (4 * 4 * 4 * (60.0 / tempo) * 1000);

          indicator = new PlayingSprite(HORIZONTAL_OFFSET, VERTICAL_OFFSET,
              HORIZONTAL_OFFSET + 4 * Measure.MEASURE_WIDTH,
              VERTICAL_OFFSET + 3 * Measure.getHeight() + 3 * VERTICAL_GAP, time);

          this.add(indicator);

          player.setup(music, 1, time);
          this.start();
        }
        else
        {
          player.resume();
          this.start();
        }
        break;
      case "stop":
        player.stop();

        this.remove(indicator);
        indicator = null;

        this.stop();
        this.getMetronome().reset();
        break;
      case "pause":
        player.pause();
        this.getMetronome().stop();
        break;
      default:
        System.exit(1);
    }
    // 4 beats per measure, 4 measures per row, 4 rows

  }

  /**
   * Accessor method for the vertical gap attribute.
   * 
   * @return vertical gap size.
   */
  public static double getVerticalGap()
  {
    return VERTICAL_GAP;
  }
}
