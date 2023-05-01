package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A class that encapsulates a MediaBar that allows us to play/pause/stop/loop our music.
 * 
 * @author Benjamin Huber
 * @version 4/30/2023
 */
public class MediaBar extends JPanel
{
  /**
   * Default version SID.
   */
  private static final long serialVersionUID = 1L;
  private JButton playButton;
  private JButton stopButton;
  private JButton pauseButton;

  /**
   * Construct a MediaBar.
   */
  public MediaBar()
  {
    super();

    GridLayout layout = new GridLayout(1, 10);
    this.setLayout(layout);

    playButton = new JButton("Play");
    playButton.setActionCommand("play");
    this.add(playButton);
    
    stopButton = new JButton("Stop");
    stopButton.setActionCommand("stop");
    this.add(stopButton);
    
    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("pause");
    this.add(pauseButton);
  }

  /**
   * Adds an action listener to the MediaBar. Added ActionListeners should respond to 'play',
   * 'pause', 'stop', and 'loop' commands.
   * 
   * @param al
   *          the ActionListener to add.
   */
  public void addActionListener(final ActionListener al)
  {
    playButton.addActionListener(al);
    stopButton.addActionListener(al);
    pauseButton.addActionListener(al);
  }
}
