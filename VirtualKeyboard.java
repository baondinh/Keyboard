/*
 * @Author: Bao Dinh 
 * @Date: 2023-03-28 15:03:46 
 * @Last Modified by: Bao Dinh
 * @Last Modified time: 2023-03-28 15:04:23
 */

/*
 * Virtual Keyboard should have an upper section with a clickable keyboard and a note display area 
 * See Calculator.java GUI for inspiration 
 * Clicking a button should send information to a backend and have that play the audio sound versus directly playing audio 
 *      Button --> someMethod(someID) --> backend --> receive info and use its own method to play sound 
 *      Do not attempt to implement a GUI that plays sound have a backend try to access the GUI in reverse
 */

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class VirtualKeyboard {
    public static void main (String [ ] args) {
        // Initialize a new keyboard 
        // Keyboard keyboard = new Keyboard(); 
        // for (Key key: keyboard.keys)
        // {
        //     System.out.println(key.getKeyNum());
        // }
        // System.out.println("Number of keys = " + keyboard.KEY_NAMES.length); 
        // for (int i = 0; i < keyboard.keys.length; i++)
        // {
        //     System.out.printf("#%2d, Key: %3s, Black Key? %s, Audio Path: %s\n", keyboard.keys[i].getKeyNum(), 
        //                         keyboard.keys[i].getName(), keyboard.keys[i].getBlackKey() ? "true" : "false", keyboard.keys[i].getAudioPath() );
        // }
        // Initialize a backend
        // keyboard can be the backend 
        // create the GUI within the virtual keyboard class!
        KeyboardApplication keyboardGUI = new KeyboardApplication();
    }
}

class KeyboardApplication extends JFrame {
    // Instance fields
    private final int FRAME_WIDTH = 600; 
    private final int FRAME_HEIGHT = 600; 

    private final int NUMBER_KEYS = 88; 

    // Initialize a new keyboard 
    Keyboard keyboard = new Keyboard(); 

    // Constructor
    public KeyboardApplication ()
    {
        // Initialize frame and font
        setTitle("Piano Keyboard"); 
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null); 
        setLayout(new FlowLayout()); 
        setResizable(false); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton [] buttons = new JButton [NUMBER_KEYS]; 
        // Test KeyListener
        PianoKeys pk = new PianoKeys(); 
        
        for (int i = 0; i < NUMBER_KEYS; i++)
        {
            String id = "" + i; 
            buttons[i] = new JButton(id);
            buttons[i].addActionListener(pk); 
        }
        // Test KeyListener
        // PianoKeys pk = new PianoKeys(); 
        // Test button
        // JButton test = new JButton("67"); 
        // test.addActionListener(pk);    
        
        for (int i = 0; i < NUMBER_KEYS; i++)
        {
            add(buttons[i]);
        }
        // add(test, BorderLayout.CENTER);
        setVisible(true); 
    }

    class PianoKeys implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            JButton b = (JButton) e.getSource(); 
            String id = b.getText(); 
            System.out.println(Integer.parseInt(id));
            keyboard.playKeyAudio(Integer.parseInt(id)); 
        }
    }

}
