
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import model.Board;
import model.ScoreCalculator;
import view.GameBoyImage;
import view.GamePanel;
import view.NextPiecePanel;

/**
 * Tetris Main JFrame.
 * 
 * @author Justin Neff
 * @version March 9th 2018
 *
 */
public class TetrisFrame extends JFrame implements Observer {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6050404240281348890L;
    /**
     * Window Icon.
     */
    private static final String APP_ICON = "./images/icon.png";
    /**
     * Window Title.
     */
    private static final String FRAME_TITLE = "Tetris";
    /**
     * Sets the frame's background to transparent.
     */
    private static final Color TRANSPARENT = new Color(0f, 0f, 0f, 0f);
    /**
     * The width of the frame.
     */
    private static final int FRAME_WIDTH = 420;
    /**
     * The height of the frame.
     */
    private static final int FRAME_HEIGHT = 706;
    /**
     * The Dimension of the frame using the FRAME_WIDTH and FRAME_HEIGHT.
     */
    private static final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    /**
     * Timer tick rate.
     */
    private static final int TICK_RATE = 1000;
    /**
     * Game board functions.
     */
    private Board myBoard;
    /**
     * The panel has the next Tetris peice drawn on it.
     */
    private NextPiecePanel myPiecePanel;
    /**
     * The GamePanel that displays the game.
     */
    private GamePanel myGamePanel;
    /**
     * Timer sets the board refresh rate that increases the speed of the game.
     */
    private Timer myTimer;
    /**
     * Checks to see if the user is playing the game.
     */
    private boolean myIsPlaying;
    /**
     * Main JPanel that holds all of the applications components.
     */
    private GameBoyImage myFramePanel;
    /**
     * Stores the location of the mousepressed x position so the entire frame
     * can just be dragged.
     */
    private int myMousePressX;
    /**
     * Stores the location of the mousepressed y position so the entire frame
     * can just be dragged.
     */
    private int myMousePressY;

    /**
     * Constructor builds my JFrame.
     */
    public TetrisFrame() {
        super();
        frameSettings();
        frameComponents();
        setupGamePanel();
        setupNextPiecePanel();
        setupLayout();
        setVisible(true);
    }

    /**
     * Configures the frame's settings.
     */
    private void frameSettings() {
        setTitle(FRAME_TITLE);
        setIconImage(new ImageIcon(APP_ICON).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(getX() - FRAME_WIDTH / 2, getY() - FRAME_HEIGHT / 2);
        setSize(FRAME_SIZE);
        setUndecorated(true);
        setBackground(Color.blue);
        setDraggable();
        addKeyControls();

    }

    /**
     * Adds all of the frame's components to the frame and creates a Timer.
     */
    private void frameComponents() {
        myBoard = new Board();
        myBoard.addObserver(this);
        myTimer = new Timer(TICK_RATE, this::start);
        setupGamePanel();
        setupNextPiecePanel();
    }

    /**
     * Method called by the Timer that causes the tetris game to run.
     * 
     * @param theEvent event parameter is required create my timer's method.
     */
    private void start(final ActionEvent theEvent) {
        myBoard.step();
    }

    /**
     * Sets up all the game panel settings.
     */
    private void setupGamePanel() {
        myGamePanel = new GamePanel();
        myGamePanel.setBackground(TRANSPARENT);
        myGamePanel.setPreferredSize(new Dimension(110, 200));
        myGamePanel.setLocation(100, 100);
        myGamePanel.setLayout(null);
        myBoard.addObserver(myGamePanel);
    }

    /**
     * Sets up all of the next piece panel settings.
     */
    private void setupNextPiecePanel() {
        myPiecePanel = new NextPiecePanel();
        myPiecePanel.setBackground(TRANSPARENT);
        myPiecePanel.setPreferredSize(new Dimension(40, 40));
        myPiecePanel.setLocation(89, 85);
        myBoard.addObserver(myPiecePanel);
    }

    /**
     * Sets up the main panel's layout.
     */
    private void setupLayout() {

        final Container pane = getContentPane();
        myFramePanel = new GameBoyImage(myGamePanel, myPiecePanel, myTimer);
        myBoard.addObserver(myFramePanel);
        myFramePanel.setLayout(null);
        pane.add(myFramePanel, BorderLayout.CENTER);
    }

    /**
     * Makes the GameBoy frame draggable and listens for clicks.
     */
    private void setDraggable() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent theEvent) {
                myMousePressX = theEvent.getX();
                myMousePressY = theEvent.getY();
            }

            @Override
            public void mouseClicked(final MouseEvent theEvent) {
                imageMap(theEvent);
            }
        });
    }

    /**
     * Gets the mouse click event and takes the x and y coordinate and maps them
     * to the image so image "buttons" can be clicked.
     * 
     * @param theEvent mouse click event
     */
    private void imageMap(final MouseEvent theEvent) {

        if (theEvent.getX() > 198 && theEvent.getX() < 247 && theEvent.getY() < 602
            && theEvent.getY() > 566) {
            // Start Button
            newGame();
        } else if (theEvent.getX() > 124 && theEvent.getX() < 175 && theEvent.getY() < 602
                   && theEvent.getY() > 566) {
            // Select Button
            pauseGame();
        } else if (theEvent.getX() > 50 && theEvent.getX() < 63 && theEvent.getY() < 170
                   && theEvent.getY() > 156) {
            // Red Battery Light
            System.exit(0);
        } else if (myTimer.isRunning() && myIsPlaying) {
            if (theEvent.getX() > 265 && theEvent.getX() < 317 && theEvent.getY() < 522
                && theEvent.getY() > 466) {
                // B Button
                myBoard.rotateCCW();
            } else if (theEvent.getX() > 339 && theEvent.getX() < 387 && theEvent.getY() < 483
                       && theEvent.getY() > 433) {
                // A Button
                myBoard.rotateCW();
            } else if (theEvent.getX() > 33 && theEvent.getX() < 63 && theEvent.getY() < 499
                       && theEvent.getY() > 462) {
                // Left Arrow
                myBoard.left();
            } else if (theEvent.getX() > 65 && theEvent.getX() < 100 && theEvent.getY() < 530
                       && theEvent.getY() > 501) {
                // Down Arrow
                myBoard.down();
            } else if (theEvent.getX() > 103 && theEvent.getX() < 135 && theEvent.getY() < 500
                       && theEvent.getY() > 465) {
                // Right Arrow
                myBoard.right();
            } else if (theEvent.getX() > 67 && theEvent.getX() < 102 && theEvent.getY() < 461
                       && theEvent.getY() > 429) {
                // Up Arrow
                myBoard.drop();
            }
        }
    }

    /**
     * Adds a keylistener to the frame for keyboard controls.
     */
    private void addKeyControls() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent theEvent) {
                final int key = theEvent.getKeyCode();
                if (key == KeyEvent.VK_N) {
                    newGame();
                } else if (key == KeyEvent.VK_P) {
                    pauseGame();
                } else if (key == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                } else if (myTimer.isRunning() && myIsPlaying) {
                    blockMovementKeys(key);
                }
            }
        });
    }

    /**
     * Handles all of the block movement keys.
     * 
     * @param theKey the Key pressed
     */
    private void blockMovementKeys(final int theKey) {
        if (theKey == KeyEvent.VK_SPACE) {
            myBoard.drop();
        } else if (theKey == KeyEvent.VK_A || theKey == KeyEvent.VK_LEFT) {
            myBoard.left();
        } else if (theKey == KeyEvent.VK_D || theKey == KeyEvent.VK_RIGHT) {
            myBoard.right();
        } else if (theKey == KeyEvent.VK_W || theKey == KeyEvent.VK_UP) {
            myBoard.rotateCW();
        } else if (theKey == KeyEvent.VK_S || theKey == KeyEvent.VK_DOWN) {
            myBoard.down();
        }
    }

    /**
     * Pauses the game.
     */
    private void pauseGame() {
        if (myIsPlaying && myTimer.isRunning()) {
            myTimer.stop();
            myFramePanel.pauseGame();
        } else if (myIsPlaying && !myTimer.isRunning()) {
            myTimer.start();
            myFramePanel.pauseGame();
        }
    }

    /**
     * Creates a new game.
     */
    private void newGame() {
        if (myIsPlaying) {
            myIsPlaying = false;
            myFramePanel.newGame();
            myTimer.setDelay(TICK_RATE);
            myTimer.stop();
        } else {
            myIsPlaying = true;
            myBoard.newGame();
            myFramePanel.newGame();
            myTimer.setDelay(TICK_RATE);
            myTimer.start();
        }
    }

    @Override
    public void update(final Observable theObservable, final Object theArg) {
        if (theArg instanceof Boolean) {
            if ((Boolean) theArg) {
                myIsPlaying = false;
                myTimer.stop();
            }
        } else if (theArg instanceof Integer[]) {
            myTimer.setDelay((int) (TICK_RATE / (ScoreCalculator.getLevel() / 2.0)));
        }
    }
}
