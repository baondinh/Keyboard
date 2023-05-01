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

        // JPanel keyboardPanel = new JPanel(); // Panel to place keyboard JButtons
            // keyboardPanel.setBackground(Color.GREEN);
        JLayeredPane keyboardPane = new JLayeredPane();
            keyboardPane.setBackground(Color.GREEN);

        JPanel display = new JPanel(); // Panel to display notes
            display.setBackground(Color.BLUE);

        // Adding grouplayout

        //Test create button add to keyboardPanel
        // JLayeredPane lp = getLayeredPane();
        // JLayeredPane lp = new JLayeredPane();
        JButton button1 = new JButton("white");
            button1.setBackground(Color.WHITE); 
            button1.setBounds(20, 20, 10, 60); 
            button1.setOpaque(true);
        JButton button2 = new JButton("black");
            button2.setBackground(Color.BLACK);
            button2.setBounds(25, 20, 10, 30); 
        keyboardPane.add(button1, JLayeredPane.DEFAULT_LAYER); 
        keyboardPane.add(button2, JLayeredPane.DRAG_LAYER); 

        // keyboardPanel.add(lp); 

        // Top component
        GroupLayout topLayout = new GroupLayout(top);
        topLayout.setHorizontalGroup(topLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(display)
                // .addComponent(keyboardPanel) 
                .addComponent(keyboardPane)
            )    
            .addContainerGap()   
        );
        topLayout.setVerticalGroup(topLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(display)
            // .addComponent(keyboardPanel)
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
        // GroupLayout bottomLayout = new GroupLayout(bottom);
        // GroupLayout keyboardLayout = new GroupLayout(keyboardPanel);

        /*
         * End of NetBeans generated code
         */

        /*
         * Test Code for button listener and audio callback
         */
        // JButton [] buttons = new JButton [NUMBER_KEYS]; 
        // PianoKeys pk = new PianoKeys(); 
        
        // for (int i = 0; i < NUMBER_KEYS; i++)
        // {
        //     String id = "" + i; 
        //     buttons[i] = new JButton(id);
        //     buttons[i].addActionListener(pk); 
        // }
        
        // for (int i = 0; i < NUMBER_KEYS; i++)
        //     { add(buttons[i]); }
        /*
         * End test code for button listener and audio callback
         */
        
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
