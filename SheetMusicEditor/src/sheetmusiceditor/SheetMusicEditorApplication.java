package sheetmusiceditor;


import javax.swing.JPanel;

import app.JApplication;
import gui.MediaBar;
import gui.SheetMusicPaper;
import init.ImageLoader;
import visual.VisualizationView;

/**
 * An Application class that launches our SheetMusicEditor when ran.
 * 
 * @author Benjamin Huber
 * @version 4/10/2023
 */
public class SheetMusicEditorApplication extends JApplication
{
  public static final int WIDTH = 1100;
  public static final int HEIGHT = 475;

  private SheetMusicEditorApplication(final String[] args)
  {
    super(args, WIDTH, HEIGHT);
  }

  @Override
  public void init()
  {
    JPanel contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(null);
    
    ImageLoader.loadImages();

    SheetMusicPaper visual = new SheetMusicPaper();
    
//    Visualization visual = new Visualization();
//    visual.add(new QuarterRest(60, 60));

//    Measure measure = new Measure(20, 20);
//
//    visual.add(measure);
//
//    visual.add(new QuarterNote(measure.getX() + .1666666 * measure.getWidth(),
//        measure.getY() + Measure.Positions.LOW_F.getHeight(), Measure.MEASURE_MARGIN_HEIGHT,
//        Color.black));
    // measure.getBounds2D(true).getMinX() + measure.getWidth() / 4.0,
    // measure.getBounds2D(true).getMinY() + Measure.Positions.A.getHeight(),
    // Measure.MEASURE_MARGIN_HEIGHT)

    VisualizationView visualView = visual.getView();

    visualView.setBounds(0, 20, WIDTH, HEIGHT - 20);

    contentPane.add(visualView);
    
    MediaBar mediaBar = new MediaBar();
    mediaBar.setBounds(0, 0, WIDTH, 20);
    mediaBar.addActionListener(visual);
    
    contentPane.add(mediaBar);    

  }

  /**
   * Main method for launching the app.
   * 
   * @param args
   *          command line arguments
   */
  public static void main(final String args[])
  {
    JApplication app = new SheetMusicEditorApplication(args);
    invokeInEventDispatchThread(app);
    
  }
}
