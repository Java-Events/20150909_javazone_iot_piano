import com.tinkerforge.BrickletAmbientLight;
import com.tinkerforge.BrickletMultiTouch;
import com.tinkerforge.IPConnection;
import org.jfugue.player.Player;


public class IotPiano implements BrickletMultiTouch.TouchStateListener, BrickletAmbientLight.IlluminanceListener {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;
    private static final String AMBIEND_UID = "CHANGE ME!"; //TODO  Change to AMBIEND_UID reported by brick viewer
    private static final String KEYPAD_UID = "CHANGE ME!"; //TODO Change to KEYPAD_UID reported by brick viewer
    private static final String[] NOTES = {"C", "D", "E", "F", "G", "A", "H"};
    private final Player piano = new Player();

    int octave = 0;

    private String getNote(int touchState) {
        for (int i = 0; i < 12; i++) {
            if ((touchState & (1 << i)) == (1 << i)) {
                return NOTES[i % NOTES.length] + octave;
            }
        }
        return "";
    }


    public void touchState(int touchState) {
        piano.play(getNote(touchState));
    }


    /**
     * @param illuminance measuered illuminance between 0 and 9000 where 9000 corresponds to 900lux
     *                    see http://www.tinkerforge.com/en/doc/Software/Bricklets/AmbientLight_Bricklet_Java.html#ambient-light-bricklet-java-api
     *                    
     *                    Adjusts the octave relative to the illumiance
     *                    Everything above max office lightning (500lux) makes the method set the octave to  10
     *                    For a description of lux, visit https://en.wikipedia.org/wiki/Lux
     */
    public void illuminance(int illuminance) {
        int lux = illuminance / 10;
        octave = Math.max(lux, 500) / 50;
    }


    public static void main(String args[]) throws Exception {


        //Connect to master brick
        IPConnection ipcon = new IPConnection(); // Create IP connection
        BrickletAmbientLight al = new BrickletAmbientLight(AMBIEND_UID, ipcon); // Create device object
        BrickletMultiTouch mt = new BrickletMultiTouch(KEYPAD_UID, ipcon); // Create device object


        ipcon.connect(HOST, PORT); // Connect to brickd
        // Don't use device before ipcon is connected

        // Set period for illuminance callback to 1s (1000ms)
        al.setIlluminanceCallbackPeriod(1000);

        final IotPiano iotPiano = new IotPiano();


        //Lets the iotPiano instance receive notifications whenever the touch state or illuminance changes.
        mt.addTouchStateListener(iotPiano);
        al.addIlluminanceListener(iotPiano);


        System.out.println("Press key to exit");
        System.in.read();
        ipcon.disconnect();
    }
}

