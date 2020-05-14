
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Block;
import model.BlockGraphics;
import model.Point;
import model.TetrisPiece;

/**
 * Holds the next Piece.
 * 
 * @author Justin Neff
 * @version March 9th 2018
 *
 */
public class NextPiecePanel extends JPanel implements Observer {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Block size.
     */
    private static final double BLOCK_SIZE = 10.0;
    /**
     * Next Tetris Piece.
     */
    private TetrisPiece myPiece;
    /**
     * Graphics for blocks.
     */
    private final BlockGraphics myBlockGfx;

    /**
     * Constructor.
     */
    public NextPiecePanel() {
        super();
        setOpaque(false);
        myBlockGfx = new BlockGraphics(BLOCK_SIZE);
    }

    @Override
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D gfx = (Graphics2D) theGraphics;

        // for better graphics display
        gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        if (myPiece != null) {
            final Point[] p = myPiece.getPoints();
            if (p.length > 0) {
                for (int i = 0; i < p.length; i++) {
                    final double xPos = getWidth() / 2
                                        - (((double) myPiece.getWidth() / 2) * BLOCK_SIZE)
                                        + (p[i].x() * BLOCK_SIZE);
                    final double yPos = (getHeight() / 2) - ((p[i].y() - 1) * BLOCK_SIZE);
                    if (myPiece.getBlock() == Block.L) {
                        myBlockGfx.lBlock(gfx, xPos, yPos);
                    } else if (myPiece.getBlock() == Block.O) {
                        myBlockGfx.oBlock(gfx, xPos - BLOCK_SIZE / 2, yPos);
                    } else if (myPiece.getBlock() == Block.J) {
                        myBlockGfx.jBlock(gfx, xPos, yPos);
                    } else if (myPiece.getBlock() == Block.S) {
                        myBlockGfx.sBlock(gfx, xPos, yPos);
                    } else if (myPiece.getBlock() == Block.T) {
                        myBlockGfx.tBlock(gfx, xPos, yPos);
                    } else if (myPiece.getBlock() == Block.Z) {
                        myBlockGfx.zBlock(gfx, xPos, yPos);
                    } else if (myPiece.getBlock() == Block.I) {
                        myBlockGfx.iBlock(gfx, xPos, yPos + BLOCK_SIZE / 2);
                    }
                }
            }
        }
    }

    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof TetrisPiece) {
            myPiece = (TetrisPiece) theArg;
            repaint();
        }

    }
}
