package init;

import sheet.visual.rests.QuarterRest;

/**
 * Utility class that loads our images once at the startup of our app.
 * 
 * @author Benjamin Huber
 * @version 4/16/2023
 */
public class ImageLoader
{
  /**
   * Loads images for our static images from file once at startup.
   */
  public static void loadImages()
  {
    QuarterRest.loadImage();
  }
}
