/*
 * @Author: Bao Dinh 
 * @Date: 2023-03-28 14:42:35 
 * @Last Modified by: Bao Dinh
 * @Last Modified time: 2023-03-28 15:09:38
 */

import java.util.*;

public class Keyboard {
    protected final String [] KEY_NAMES = { "A0", "A#0", "B0", 
                                            "C1", "C#1", "D1", "D#1", "E1", "F1", "F#1", "G1", "G#1", "A1", "A#1", "B1", 
                                            "C2", "C#2", "D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2",
                                            "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3",
                                            "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4",
                                            "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5",
                                            "C6", "C#6", "D6", "D#6", "E6", "F6", "F#6", "G6", "G#6", "A6", "A#6", "B6",
                                            "C7", "C#7", "D7", "D#7", "E7", "F7", "F#7", "G7", "G#7", "A7", "A#7", "B7",
                                            "C8" };
    protected final int NUMBER_KEYS = 88; 

    protected Key [] keys; // Array holding all key objects
    protected String [] audioFilePath; 

    public Keyboard() {
        this.keys = new Key [NUMBER_KEYS]; //Array of 88 key objects
        this.audioFilePath = new String [NUMBER_KEYS]; //Array of audio file path Strings

        // Creating a set that stores the key numbers for black keys
        Set<Integer> blackKeyNums = new HashSet<>(); 
        for (int i = 1; i <= NUMBER_KEYS; i++) 
        {
            int j = 12 * (i / 12); //Represents octave shift
            if ( (i - 2 - j) % 5 == 0 || (i - j) % 5 == 0)
                { blackKeyNums.add( (Integer) i ); }
        }


        for (int i = 0; i < keys.length; i++)
        {
            // Initialize a new key object
            Key newKey = new Key( (i + 1) , KEY_NAMES[i], blackKeyNums.contains(i + 1) );
            // Store key object in keys array
            this.keys[i] = newKey; 
        }


    }

    public void playKeyAudio(int id)
    {
        this.keys[id].playAudio();
    }

    // Testing only
    // public static void main (String [] args)
    // {
    //     Keyboard test = new Keyboard(); 
    //     System.out.println("Number of keys = " + test.KEY_NAMES.length); 
    //     for (int i = 0; i < test.keys.length; i++)
    //     {
    //         System.out.printf("#%2d, Key: %3s, Black Key? %s\n", test.keys[i].getKeyNum(), 
    //                             test.keys[i].getName(), test.keys[i].getBlackKey() ? "true" : "false" );
    //     }
    // }
}
