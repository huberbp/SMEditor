package auditory;

import javax.sound.midi.MidiUnavailableException;

import auditory.described.Note;
import auditory.described.Orchestra;
import auditory.described.Part;
import auditory.described.Score;
import event.Metronome;
import sheet.visual.Measure.Positions;

/**
 * A MusicPlayer that allows us to play music from an array of Measure.Positions objects.
 * 
 * @author Benjamin Huber
 * @version 4/30/2023
 */
public class MusicPlayer
{
  private Orchestra orchestra;
  private Metronome metronome;
  private boolean playing;

  /**
   * Constructs a music player that runs the specified music at the specified octave for the
   * specified time.
   */
  public MusicPlayer()
  {
    metronome = new Metronome(10);
    playing = false;
  }

  private Part createPart(final Positions[] music, final int octave)
  {
    Part part = new Part();
    for (int i = 0; i < music.length; i++)
    {
      Note note;
      if (music[i].getNote() == 'R')
      {
        note = new Note('R', false, 0, 4, false);
      }
      else
      {
        note = new Note(music[i].getNote(), false, octave + music[i].getOctave(), 4, false);
      }
      part.add(note);
    }
    return part;
  }

  /**
   * Setup our MusicPlayer to play the appropriate music.
   * 
   * @param music
   * @param octave
   * @param time
   * @return true if playing, false if not
   */
  public boolean setup(final Positions[] music, final int octave, final int time)
  {
    if (!playing)
    {
      Score score = new Score();
      score.addPart(createPart(music, octave), "Piano");
      score.setTimeSignature(4, 4);
      score.setTempo((int) ((time / 16) + 1));
      metronome.stop();
      metronome.reset();
      try
      {
        orchestra = new Orchestra(score, metronome);

        orchestra.start();
      }
      catch (MidiUnavailableException e)
      {
        e.printStackTrace();
      }
      
      playing = true;
    }
    
    return playing;
  }
  
  /**
   * Returns whether or not the MusicPlayer is playing.
   * 
   * @return true if playing
   */
  public boolean isPlaying()
  {
    return playing;
  }

  /**
   * Start the MusicPlayer.
   */
  public void resume()
  {
    metronome.start();
  }

  /**
   * Pause the MusicPlayer.
   */
  public void pause()
  {
    metronome.stop();
  }

  /**
   * Stop and Reset the MusicPlayer.
   */
  public void stop()
  {
    metronome.stop();
    metronome.reset();
    playing = false;
  }
}
