/*
 * @Author: Bao Dinh 
 * @Date: 2023-03-28 14:36:37 
 * @Last Modified by: Bao Dinh
 * @Last Modified time: 2023-03-28 15:07:47
 */

/*
 * Starting with the first black note on an 88-key keyboard at A#0/Bb0, there will be five total black notes in that octave range 
 * If an octave range is defined between A#/Bb to the next A#/Bb, the following mathematical equations will give the keyNum of every black note: 
 *      Let s = 2 to represent the starting keyNum of the first black note on an 88-key keyboard
 *      Let o represent the octave
 *      Let x ∈ {0, 1, 2} 
 *      s + 12o
 */

import java.io.*; 
import javax.sound.sampled.*; 
import java.util.*; 

public class Key {
    protected boolean blackKey; //Black or white key
    protected int keyNum; //Key number within range of 88 keys
    protected String name; //Key name
    protected String audioPath; //Check audio object
    protected AudioInputStream audio; 

    public Key(int n, String s, boolean b) {
        this.keyNum = n;
        this.name = s; 
        this.blackKey = b;
        // this.audioPath = "\\PianoSamples\\" + this.name + ".aiff";
        this.audioPath = "./PianoSamples/" + this.name + ".wav";
    }

    // Returns keyNum of Key object
    public int getKeyNum()
    {
        return this.keyNum;
    }

    // Returns name of Key object
    public String getName()
    {
        return this.name; 
    }

    public boolean getBlackKey()
    {
        return this.blackKey;
    }

    public String getAudioPath()
    {
        return this.audioPath; 
    }

    public void playAudio()
    {
        try {
            this.audio = AudioSystem.getAudioInputStream(new File(this.audioPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip(); 
            clip.open(this.audio); 
            clip.start(); 
            // clip.close(); 
        }
        catch (Exception e) { System.out.println("Error: Audio not found"); }
    }
}
