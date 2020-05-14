
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Block;
import model.BlockGraphics;

/**
 * Game Panel.
 * 
 * @author Justin Neff
 * @version March 9th 2018
 *
 */
public class GamePanel extends JPanel implements Observer {
    /**
     * Default Serial.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Default block size.
     */
    private static final double BLOCK_SIZE = 10.0;
    /**
     * Starting Drawable index.
     */
    private static final int DRAWABLE_START_INDEX = 4;
    /**
     * Blocks array.
     */
    private Block[][] myBlocks;
    /**
     * Block styles to be drawn.
     */
    private final BlockGraphics myBlockStyles;

    /**
     * Panel with the game drawn on it.
     */
    public GamePanel() {
        super();
        myBlocks = new Block[0][0];
        myBlockStyles = new BlockGraphics(BLOCK_SIZE);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D gfx = (Graphics2D) theGraphics;

        // for better graphics display
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        for (int yPos = DRAWABLE_START_INDEX; yPos < myBlocks.length; yPos++) {
            final Block[] row = myBlocks[myBlocks.length - 1 - yPos];
            for (int xPos = 0; xPos < row.length; xPos++) {
                if (row[xPos] != null) {
                    final Double x = (xPos * BLOCK_SIZE) + 5;
                    final Double y = (yPos * BLOCK_SIZE) - 40;
                    if (row[xPos] == Block.L) {
                        myBlockStyles.lBlock(gfx, x, y);
                    } else if (row[xPos] == Block.O) {
                        myBlockStyles.oBlock(gfx, x, y);
                    } else if (row[xPos] == Block.J) {
                        myBlockStyles.jBlock(gfx, x, y);
                    } else if (row[xPos] == Block.S) {
                        myBlockStyles.sBlock(gfx, x, y);
                    } else if (row[xPos] == Block.T) {
                        myBlockStyles.tBlock(gfx, x, y);
                    } else if (row[xPos] == Block.Z) {
                        myBlockStyles.zBlock(gfx, x, y);
                    } else if (row[xPos] == Block.I) {
                        myBlockStyles.iBlock(gfx, x, y);
                    }

                }
            }
        }

    }

    @Override
    public void update(final Observable theObs, final Object theArg) {
        if (theArg instanceof Block[][]) {
            myBlocks = (Block[][]) theArg;
            repaint();
        }
    }

}
