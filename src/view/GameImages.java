
package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Holds the Tetris background Image.
 * 
 * @author Justin Neff
 * @version March 9th 2018
 *
 */
public class GameImages extends JPanel {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Background Image file.
     */
    private static final String BACKGROUND_IMG_GAME_BG = "./images/gameplay.png";
    /**
     * Background Image.
     */
    private BufferedImage myImg;

    /**
     * Constructor.
     */
    public GameImages() {
        super();
        try {
            myImg = ImageIO.read(new File(BACKGROUND_IMG_GAME_BG));
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(final Graphics theGfx) {
        super.paintComponent(theGfx);
        theGfx.drawImage(myImg, 0, 0, this);
    }
}
