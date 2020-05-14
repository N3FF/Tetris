import gui.TetrisFrame;

/**
 * Starts up the application.
 * 
 * @author Justin Neff
 * @version March 9th 2018
 *
 */
public final class TetrisApp {
    
    /**
     * Private constructor.
     */
    private TetrisApp() {
        
    }

    /**
     * Runs the program.
     * @param theArgs system input
     */
    public static void main(final String[] theArgs) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TetrisFrame();
            }
        });
    }
}
