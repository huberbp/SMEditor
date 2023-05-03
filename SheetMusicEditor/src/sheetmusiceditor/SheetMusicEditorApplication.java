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
