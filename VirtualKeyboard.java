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
import java.util.*;

public class VirtualKeyboard {
    public static void main (String [ ] args) {
        // Initialize a backend
        // keyboard can be the backend 
        // create the GUI within the virtual keyboard class!
        KeyboardApplication keyboardGUI = new KeyboardApplication();

        // Key assignment Test *******************
        // Keyboard test = keyboardGUI.keyboard;
        // System.out.println("Number of keys = " + test.KEY_NAMES.length); 
        // for (int i = 0; i < test.keys.length; i++)
        // {
        //     System.out.printf("#%2d, Key: %3s, Black Key? %s, Audio Path: %s\n", test.keys[i].getKeyNum(), 
        //                         test.keys[i].getName(), test.keys[i].getBlackKey() ? "true" : "false", keyboard.keys[i].getAudioPath() );
        // }
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
        // setLayout(new FlowLayout()); 
        setResizable(false); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 
         * Create Panels using NetBeans
         */

        JPanel top = new JPanel(); // Section for keyboard
            top.setBackground(Color.YELLOW);
        JPanel bottom = new JPanel(); // Section for chord progression
            bottom.setBackground(Color.RED);

        JLayeredPane keyboardPane = new JLayeredPane(); // Pane that allows for JButtons to layer over eachother

        JPanel display = new JPanel(); // Panel to display notes
            display.setBackground(Color.BLUE);

        // Adding grouplayout
        // Creating the keyboard
        JButton [] buttons = new JButton [NUMBER_KEYS]; // An array of JButtons that correspond to the piano keys
        PianoKeys pk = new PianoKeys(); // ActionListener for piano key JButtons
        
        // Creating a set that stores the key numbers for black keys
        Set<Integer> blackKeyNums = new HashSet<>(); 
        for (int i = 1; i <= NUMBER_KEYS; i++) 
        {
            int j = 12 * (i / 12); //Represents octave shift
            if ( (i - 2 - j) % 5 == 0 || (i - j) % 5 == 0)
                { blackKeyNums.add( (Integer) i ); }
        }
        System.out.println(blackKeyNums.toString());
        Keyboard test = keyboard; 
        System.out.println("Number of keys = " + test.KEY_NAMES.length); 
        for (int i = 0; i < test.keys.length; i++)
        {
            System.out.printf("#%2d, Key: %3s, Black Key? %s\n", test.keys[i].getKeyNum(), 
                                test.keys[i].getName(), test.keys[i].getBlackKey() ? "true" : "false" );
        }

        int keyboardX = 5; // Starting x coordinate for keyboard generation within JLayeredPane
        int keyboardY = 20; // Starting y coordinate for keyboard generation within JLayeredPane
        for (int i = 0; i < NUMBER_KEYS; i++)
        {
            String id = "" + i; 
            buttons[i] = new JButton();
            buttons[i].setActionCommand(id);
            buttons[i].addActionListener(pk); 

            if (blackKeyNums.contains(i + 1))
            {
                buttons[i].setBackground(Color.BLACK);
                buttons[i].setBounds(keyboardX, keyboardY, 10, 30);
                keyboardPane.add(buttons[i], JLayeredPane.DRAG_LAYER);
            }
            else {
                keyboardX += 5;
                buttons[i].setBackground(Color.WHITE);
                buttons[i].setBounds(keyboardX, keyboardY, 10, 60);
                keyboardPane.add(buttons[i], JLayeredPane.DEFAULT_LAYER);
                keyboardX += 5;
            }
        }

        // Top component
        GroupLayout topLayout = new GroupLayout(top);
        topLayout.setHorizontalGroup(topLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(display)
                .addComponent(keyboardPane)
            )    
            .addContainerGap()   
        );
        topLayout.setVerticalGroup(topLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(display)
            .addComponent(keyboardPane)
            .addContainerGap()
        );
        top.setLayout(topLayout);

        // Frame in general
        GroupLayout frameLayout = new GroupLayout(getContentPane());
        frameLayout.setHorizontalGroup(frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(bottom)
                    .addComponent(top)
                )
                .addContainerGap()
            )
        );
        frameLayout.setVerticalGroup(frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(top)
                .addComponent(bottom)
                .addContainerGap()
            )
        );
        getContentPane().setLayout(frameLayout);

        /*
         * End of NetBeans inspired code
         */
        
        setVisible(true); 
    }

    class PianoKeys implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            JButton b = (JButton) e.getSource(); 
            String id = b.getActionCommand();
            System.out.println(Integer.parseInt(id));
            keyboard.playKeyAudio(Integer.parseInt(id)); 
        }
    }

}
