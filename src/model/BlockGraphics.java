
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Draws the different block styles.
 * 
 * @author Justin Neff
 * @version March 9th 2018
 *
 */
public class BlockGraphics {
    /**
     * Black color.
     */
    private static final Color BLACK = new Color(28, 40, 19);

    /**
     * Dark Green color.
     */
    private static final Color DARK_GREEN = new Color(48, 79, 46);

    /**
     * Light Green color.
     */
    private static final Color LIGHT_GREEN = new Color(84, 121, 77);

    /**
     * Whiteish color.
     */
    private static final Color WHITEISH = new Color(166, 188, 134);

    /**
     * Block size set by the constructor.
     */
    private final double myBlockSize;

    /**
     * Constructor sets the block size.
     * 
     * @param theBlockSize the block size
     */
    public BlockGraphics(final double theBlockSize) {
        myBlockSize = theBlockSize;
    }

    /**
     * L block design.
     * 
     * @param theGfx Graphics2d from sender
     * @param theXpos x block position.
     * @param theYpos y block position.
     */
    public void lBlock(final Graphics2D theGfx, final double theXpos, final double theYpos) {
        theGfx.setPaint(BLACK);
        Shape block = new Rectangle2D.Double(theXpos, theYpos, myBlockSize, myBlockSize);
        theGfx.fill(block);
        block = new Rectangle2D.Double(theXpos + 2, theYpos + 2, myBlockSize - 4,
                                       myBlockSize - 4);
        theGfx.setPaint(DARK_GREEN);
        theGfx.fill(block);
    }

    /**
     * O block design.
     * 
     * @param theGfx Graphics2d from sender
     * @param theXpos x block position.
     * @param theYpos y block position.
     */
    public void oBlock(final Graphics2D theGfx, final double theXpos, final double theYpos) {
        theGfx.setPaint(BLACK);
        Shape block = new Rectangle2D.Double(theXpos, theYpos, myBlockSize, myBlockSize);
        theGfx.fill(block);
        block = new Rectangle2D.Double(theXpos + 2, theYpos + 2, myBlockSize - 4,
                                       myBlockSize - 4);
        theGfx.setPaint(WHITEISH);
        theGfx.fill(block);

        block = new Rectangle2D.Double(theXpos + 3, theYpos + 3, myBlockSize - 6,
                                       myBlockSize - 6);
        theGfx.setPaint(BLACK);
        theGfx.fill(block);
    }

    /**
     * J block design.
     * 
     * @param theGfx Graphics2d from sender
     * @param theXpos x block position.
     * @param theYpos y block position.
     */
    public void jBlock(final Graphics2D theGfx, final double theXpos, final double theYpos) {
        theGfx.setPaint(BLACK);
        Shape block = new Rectangle2D.Double(theXpos, theYpos, myBlockSize, myBlockSize);
        theGfx.fill(block);
        block = new Rectangle2D.Double(theXpos + 2, theYpos + 2, myBlockSize - 4,
                                       myBlockSize - 4);
        theGfx.setPaint(LIGHT_GREEN);
        theGfx.fill(block);

        block = new Rectangle2D.Double(theXpos + 4, theYpos + 4, myBlockSize - 8,
                                       myBlockSize - 8);
        theGfx.setPaint(BLACK);
        theGfx.fill(block);
    }

    /**
     * S block design.
     * 
     * @param theGfx Graphics2d from sender
     * @param theXpos x block position.
     * @param theYpos y block position.
     */
    public void sBlock(final Graphics2D theGfx, final double theXpos, final double theYpos) {
        theGfx.setPaint(BLACK);
        Shape block = new Rectangle2D.Double(theXpos, theYpos, myBlockSize, myBlockSize);
        theGfx.fill(block);
        block = new Rectangle2D.Double(theXpos + 2, theYpos + 2, myBlockSize - 4,
                                       myBlockSize - 4);
        theGfx.setPaint(DARK_GREEN);
        theGfx.fill(block);
        block = new Rectangle2D.Double(theXpos + 3, theYpos + 3, myBlockSize - 6,
                                       myBlockSize - 6);
        theGfx.setPaint(BLACK);
        theGfx.fill(block);

        block = new Rectangle2D.Double(theXpos + 4, theYpos + 4, myBlockSize - 8,
                                       myBlockSize - 8);
        theGfx.setPaint(WHITEISH);
        theGfx.fill(block);
    }

    /**
     * Z block design.
     * 
     * @param theGfx Graphics2d from sender
     * @param theXpos x block position.
     * @param theYpos y block position.
     */
    public void zBlock(final Graphics2D theGfx, final double theXpos, final double theYpos) {
        theGfx.setPaint(BLACK);
        Shape block = new Rectangle2D.Double(theXpos, theYpos, myBlockSize, myBlockSize);
        theGfx.fill(block);
        block = new Rectangle2D.Double(theXpos + 2, theYpos + 2, myBlockSize - 4,
                                       myBlockSize - 4);
        theGfx.setPaint(LIGHT_GREEN);
        theGfx.fill(block);
        block = new Rectangle2D.Double(theXpos + 3, theYpos + 3, myBlockSize - 6,
                                       myBlockSize - 6);
        theGfx.setPaint(BLACK);
        theGfx.fill(block);

        block = new Rectangle2D.Double(theXpos + 4, theYpos + 4, myBlockSize - 8,
                                       myBlockSize - 8);
        theGfx.setPaint(WHITEISH);
        theGfx.fill(block);
    }

    /**
     * T block design.
     * 
     * @param theGfx Graphics2d from sender
     * @param theXpos x block position.
     * @param theYpos y block position.
     */
    public void tBlock(final Graphics2D theGfx, final double theXpos, final double theYpos) {
        // border
        theGfx.setPaint(BLACK);
        Shape block = new Rectangle2D.Double(theXpos, theYpos, myBlockSize, myBlockSize);
        theGfx.fill(block);
        // back
        block = new Rectangle2D.Double(theXpos + 2, theYpos + 2, myBlockSize - 4,
                                       myBlockSize - 4);
        theGfx.setPaint(LIGHT_GREEN);
        theGfx.fill(block);

        // Dark Shad
        block = new Rectangle2D.Double(theXpos + 2, theYpos + 2, myBlockSize - 5,
                                       myBlockSize - 5);
        theGfx.setPaint(BLACK);
        theGfx.fill(block);

        // Light shad
        block = new Rectangle2D.Double(theXpos + 3, theYpos + 3, myBlockSize - 6,
                                       myBlockSize - 6);
        theGfx.setPaint(LIGHT_GREEN);
        theGfx.fill(block);

        // Center
        block = new Rectangle2D.Double(theXpos + 4, theYpos + 4, myBlockSize - 8,
                                       myBlockSize - 9);
        theGfx.setPaint(LIGHT_GREEN);
        theGfx.fill(block);
    }

    /**
     * I block design.
     * 
     * @param theGfx Graphics2d from sender
     * @param theXpos x block position.
     * @param theYpos y block position.
     */
    public void iBlock(final Graphics2D theGfx, final double theXpos, final double theYpos) {
        theGfx.setPaint(BLACK);
        Shape block = new Rectangle2D.Double(theXpos, theYpos, myBlockSize, myBlockSize);

        theGfx.fill(block);
        block = new Rectangle2D.Double(theXpos + 2, theYpos + 2, myBlockSize - 4,
                                       myBlockSize - 4);
        theGfx.setPaint(DARK_GREEN);
        theGfx.fill(block);
    }
}
