class Key {
    protected boolean blackKey; 
    protected int keyNum; 
    protected String name;
    protected double frequency; 
    
    private static final double A0_FREQUENCY = 27.5;
    
    // Creates a piano key with number, name, type, and frequency
    public Key(int n, String s, boolean b, double freq) {
        this.keyNum = n;
        this.name = s;
        this.blackKey = b;
        this.frequency = freq;
    }
    
    // Returns key number (1-88)
    public int getKeyNum() {
        return this.keyNum;
    }
    
    // Returns note name with octave (e.g., "C4")
    public String getName() {
        return this.name;
    }
    
    // Returns if key is black (true) or white (false)
    public boolean getBlackKey() {
        return this.blackKey;
    }
    
    // Returns note frequency in Hz
    public double getFrequency() {
        return this.frequency;
    }
}

class Keyboard {
    protected final int NUMBER_KEYS = 88;
    protected Key[] keys;
    private final String[] NOTE_NAMES = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    
    // Creates standard 88-key piano keyboard
    public Keyboard() {
        this.keys = new Key[NUMBER_KEYS];
        generateKeys();
    }
    
    // Generates all 88 piano keys
    private void generateKeys() {
        for (int i = 0; i < NUMBER_KEYS; i++) {
            String noteName = generateNoteName(i);
            boolean isBlack = isBlackKey(noteName);
            double frequency = calculateFrequency(i);

            keys[i] = new Key(i + 1, noteName, isBlack, frequency);
        }
    }
    
    // Calculates note name and octave from key index
    private String generateNoteName(int keyIndex) {
        int noteIndex = keyIndex % 12;

        int octave;
        if (keyIndex < 3) {
            octave = 0;
            noteIndex = keyIndex;
        } else {
            octave = ((keyIndex - 3) / 12) + 1;
            noteIndex = (keyIndex - 3) % 12;

            if (noteIndex < 9) {
                noteIndex += 3;
                if (noteIndex >= 12) {
                    noteIndex -= 12;
                }
            } else {
                noteIndex = noteIndex - 9;
            }
        }
        
        return NOTE_NAMES[noteIndex] + octave;
    }
    
    // Determines if a note is a black key
    private boolean isBlackKey(String noteName) {
        return noteName.contains("#");
    }
    
    // Calculates frequency using equal temperament formula
    private double calculateFrequency(int keyIndex) {
        final double A0_FREQUENCY = 27.5;
        final double SEMITONE_RATIO = Math.pow(2, 1.0/12);
        
        return A0_FREQUENCY * Math.pow(SEMITONE_RATIO, keyIndex);
    }
    
    // Returns name and frequency of a key by index
    public String getKeyPlayed(int id) {
        if (id >= 0 && id < NUMBER_KEYS) {
            return keys[id].getName() + " (Frequency: " + String.format("%.2f", keys[id].getFrequency()) + " Hz)";
        } else {
            return "Invalid key number";
        }
    }

    // Displays all keys with their properties
    public void printAllKeys() {
        for (int i = 0; i < NUMBER_KEYS; i++) {
            System.out.println("Key " + (i+1) + ": " + keys[i].getName() + 
                               " (Black: " + keys[i].getBlackKey() + 
                               ", Frequency: " + String.format("%.2f", keys[i].getFrequency()) + " Hz)");
        }
    }

    // Calculates actual half-step distance between two keys
    public int testInterval(Key key1, Key key2) {
        return Math.abs(key1.getKeyNum() - key2.getKeyNum());
    }
    
    // Returns the musical name for a given interval
    public String getIntervalName(int halfSteps) {
        // Get interval quality within an octave
        int intervalWithinOctave = halfSteps % 12;
        
        // Determine octave displacement
        int octaves = halfSteps / 12;
        
        String baseInterval = "";
        switch (intervalWithinOctave) {
            case 0: baseInterval = octaves == 0 ? "Unison" : "Octave"; break;
            case 1: baseInterval = "Minor 2nd (Half step)"; break;
            case 2: baseInterval = "Major 2nd (Whole step)"; break;
            case 3: baseInterval = "Minor 3rd (m3)"; break;
            case 4: baseInterval = "Major 3rd (M3)"; break;
            case 5: baseInterval = "Perfect 4th"; break;
            case 6: baseInterval = "Augmented 4th/Diminished 5th "; break;
            case 7: baseInterval = "Perfect 5th"; break;
            case 8: baseInterval = "Minor 6th"; break;
            case 9: baseInterval = "Major 6th"; break;
            case 10: baseInterval = "Minor 7th"; break;
            case 11: baseInterval = "Major 7th"; break;
            default:
                baseInterval = "Interval of " + intervalWithinOctave + " half steps";
        }
        
        // Add compound interval information if needed
        if (octaves > 0 && intervalWithinOctave > 0) {
            return baseInterval + " (" + halfSteps + " half steps, compound interval)";
        } else if (octaves > 1 && intervalWithinOctave == 0) {
            return octaves + " Octaves (" + halfSteps + " half steps)";
        } else {
            return baseInterval + " (" + halfSteps + " half steps)";
        }
    }

    // Finds a key by its note name
    public Key findKeyByName(String name) {
        for (Key key : keys) {
            if (key.getName().equals(name)) {
                return key;
            }
        }
        return null;
    }
}


public class IntervalTest {
    public static void main(String[] args) {
        System.out.println("Creating piano keyboard with 88 keys...");
        Keyboard piano = new Keyboard();
        
        System.out.println("\nPrinting all piano keys with their properties:");
        System.out.println("==============================================");
        piano.printAllKeys();
        
        // Example: Get information about specific keys
        System.out.println("\nSome example key information:");
        System.out.println("Middle C (key 40): " + piano.getKeyPlayed(39));
        System.out.println("A4 (Concert A, key 49): " + piano.getKeyPlayed(48));
        System.out.println("Highest key (C8, key 88): " + piano.getKeyPlayed(87));
        
        // Testing intervals
        System.out.println("\nTesting Musical Intervals:");
        System.out.println("=========================");
        
        // Find keys for interval testing
        Key b3 = piano.findKeyByName("B3");
        Key c4 = piano.findKeyByName("C4");
        Key d4 = piano.findKeyByName("D4");
        Key e4 = piano.findKeyByName("E4");
        Key e5 = piano.findKeyByName("E5");
        Key eFlat4 = piano.findKeyByName("D#4"); // Enharmonic to Eb4
        
        // Test half step (B3 to C4)
        if (b3 != null && c4 != null) {
            int interval = piano.testInterval(b3, c4);
            System.out.println("Interval from B3 to C4: " + interval + " half steps - " + 
                               piano.getIntervalName(interval));
        }
        
        // Test whole step (C4 to D4)
        if (c4 != null && d4 != null) {
            int interval = piano.testInterval(c4, d4);
            System.out.println("Interval from C4 to D4: " + interval + " half steps - " + 
                               piano.getIntervalName(interval));
        }
        
        // Test a major third (C4 to E4)
        if (c4 != null && e4 != null) {
            int interval = piano.testInterval(c4, e4);
            System.out.println("Interval from C4 to E4: " + interval + " half steps - " + 
                               piano.getIntervalName(interval));
        }
        
        // Test a minor third (C4 to Eb4/D#4)
        if (c4 != null && eFlat4 != null) {
            int interval = piano.testInterval(c4, eFlat4);
            System.out.println("Interval from C4 to Eb4: " + interval + " half steps - " + 
                               piano.getIntervalName(interval));
        }

        // Test a major third greater than an octave (C4 to E5)
        if (c4 != null && e5 != null) {
            int interval = piano.testInterval(c4, e5);
            System.out.println("Interval from C4 to E5: " + interval + " half steps - " + 
                               piano.getIntervalName(interval));
        }
    }
}
