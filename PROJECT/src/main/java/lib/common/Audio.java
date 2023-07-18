package lib.common;

import java.io.File;

import javax.sound.sampled.*;

public class Audio
{
    static public boolean running = true;
    static public Clip clip;

    public static void playAudio(String fileName)
    {
        File file = new File(fileName);

        try
        {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }
        catch (Exception e)
        {
            System.out.println("Couldn't Find The File");
        }

    }

    public static void stopAudio()
    {
        clip.stop();
    }

    public static void loopAudio(String fileName)
    {
        File file = new File(fileName);

        try
        {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't Find The File");
        }
    }

    public static void pauseAudio()
    {
        if (running)
        {
            clip.stop();
            running = false;
        }
        else
        {
            clip.start();
            running = true;
        }
    }


}