package sheet.visual.rests;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import io.ResourceFinder;
import sheet.visual.SheetElement;
import visual.statik.sampled.BufferedImageOpFactory;
import visual.statik.sampled.Content;
import visual.statik.sampled.ImageFactory;

/**
 * A class encapsulating the visual representation of a Quarter Rest.
 * 
 * @author Benjamin Huber
 * @version 4/15/2023
 */
public class QuarterRest extends SheetElement
{
  private static final String IMAGE_NAME = "quarter-rest.png";
  private static BufferedImage image;
  private final double size = .25;
  private final int type = 4;

  /**
   * A constructor that creates a QuarterRest from the specified x and y coordinates, and the given
   * measure height.
   * 
   * @param x
   *          the x-coordinate of the rest.
   * @param y
   *          the y-coordinate of the rest.
   */
  public QuarterRest(final double x, final double y)
  {
    super(x + ((double) image.getWidth()) / -2.0, y + ((double) image.getHeight()) / -2.0);
    
    Content restContent = new Content(image, 0,
        0, false);
    
    this.add(restContent);
  }
  
  /**
   * Loads our image from the file.
   */
  public static void loadImage()
  {
    ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
    ImageFactory factory = new ImageFactory(rf);
    BufferedImage restImage = factory.createBufferedImage(IMAGE_NAME, 4);

    BufferedImageOpFactory opFactory = BufferedImageOpFactory.createFactory();
    AffineTransformOp op = opFactory.createScaleOp(.01, .01);

    BufferedImage dstImage = op.filter(restImage, null);
    
    image = dstImage;
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
