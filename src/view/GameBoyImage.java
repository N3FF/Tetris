
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import model.ScoreCalculator;
import model.TetrisPiece;

/**
 * Main Panel.
 * 
 * @author Justin Neff
 * @version March 9th 2018
 *
 */
public class GameBoyImage extends JPanel implements Observer {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4863120175322007803L;
    /**
     * Background Image file.
     */
    private static final String BACKGROUND_IMG = "./images/gb_bg.png";
    /**
     * 0 String.
     */
    private static final String ZERO = "0";
    /**
     * 1 String.
     */
    private static final String ONE = "1";
    /**
     * Game Over.
     */
    private static final String GAME_OVER = "GAME OVER";
    /**
     * Score Label.
     */
    private JLabel myScore;
    /**
     * Current Level label.
     */
    private JLabel myLevel;
    /**
     * Lines cleared label.
     */
    private JLabel myLines;
    /**
     * Status i.e. gameover/pause.
     */
    private JLabel myStatusText;
    /**
     * Panel holds the status text.
     */
    private JPanel myStatusPanel;
    /**
     * Determines if the game is paused.
     */
    private boolean myIsPaused;
    /**
     * Holds background image.
     */
    private GameImages myImgPanel;
    /**
     * Where the game paints.
     */
    private final GamePanel myGamePanel;
    /**
     * Paints the next piece.
     */
    private final NextPiecePanel myPiecePanel;
    /**
     * Timer that's running the game.
     */
    private final Timer myTimer;

    /**
     * Sets up my main panel with it's components.
     * 
     * @param theGamePanel paints the game.
     * @param thePiecePanel paints the next piece.
     * @param theTimer timer running the game.
     */
    public GameBoyImage(final GamePanel theGamePanel, final NextPiecePanel thePiecePanel,
                        final Timer theTimer) {
        super();
        myTimer = theTimer;
        myGamePanel = theGamePanel;
        myPiecePanel = thePiecePanel;
        setOpaque(false);
        setLayout(null);
        setupImgPanel();
        gameScoreLabels();
        makeStatusPanel();
        controlsMenu();
    }

    /**
     * Sets up image panel.
     */
    private void setupImgPanel() {
        myImgPanel = new GameImages();
        myImgPanel.setBounds(99, 94, 222, 205);
        myImgPanel.setLayout(null);
        myGamePanel.setBounds(26, 0, 110, 200);
        myPiecePanel.setBounds(169, 151, 40, 40);
        myImgPanel.add(myGamePanel);
        myImgPanel.add(myPiecePanel);
        add(myImgPanel);
    }

    /**
     * Scores Lines and Levels.
     */
    private void gameScoreLabels() {
        myScore = new JLabel(ZERO);
        myScore.setHorizontalAlignment(SwingConstants.RIGHT);
        myScore.setBounds(145, 29, 70, 20);
        myImgPanel.add(myScore);

        myLevel = new JLabel(ONE);
        myLevel.setHorizontalAlignment(SwingConstants.CENTER);
        myLevel.setBounds(173, 75, 20, 20);
        myImgPanel.add(myLevel);

        myLines = new JLabel(ZERO);
        myLines.setHorizontalAlignment(SwingConstants.CENTER);
        myLines.setBounds(163, 110, 40, 20);
        myImgPanel.add(myLines);
    }

    /**
     * Makes the status panel used to display gameover and paused.
     */
    private void makeStatusPanel() {

        myStatusText = new JLabel("");
        myStatusText.setHorizontalAlignment(SwingConstants.CENTER);
        myStatusText.setForeground(new Color(28, 40, 19));

        myStatusPanel = new JPanel();
        myStatusPanel.setBounds(5, 80, 100, 30);
        myStatusPanel.setBorder(BorderFactory.createLineBorder(new Color(48, 79, 46)));
        myStatusPanel.setBackground(new Color((166 / 255f), (188 / 255f), (134 / 255f), 0.8f));
        myStatusPanel.setVisible(false);

        myStatusPanel.add(myStatusText);
        myGamePanel.add(myStatusPanel);
    }

    /**
     * Creates the Controls menu.
     */
    private void controlsMenu() {
        final JLabel controlsMenu = new JLabel("VIEW CONTROLS");
        controlsMenu.setForeground(Color.LIGHT_GRAY);
        controlsMenu.setBounds(105, 300, 100, 30);
        controlsMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent theEvent) {
                final String controls = "New/End Game: \nStart Button | N Key\n\n"
                                        + "Pause: \nSelect Button | P Key\n\n"
                                        + "Rotate CCW: \nB Button\n\n"
                                        + "Rotate CW: \nA Button | Up Key\n\n"
                                        + "Move Left: \nLeft Button | Left Key\n\n"
                                        + "Move Right: \nRight Button | Right Key\n\n"
                                        + "Drop: \nUp Button | Spacebar\n\n"
                                        + "Exit: \nEscape Key";
                JOptionPane.showMessageDialog(null, controls, "Controls",
                                              JOptionPane.PLAIN_MESSAGE);
            }
        });

        add(controlsMenu);
    }

    /**
     * Sets up new Game.
     */
    public void newGame() {

        if (myTimer.isRunning() || myIsPaused) {
            myStatusPanel.setVisible(false);
            myStatusText.setText(GAME_OVER);
            myStatusPanel.setVisible(true);
            myIsPaused = false;
            myTimer.stop();
        } else {
            ScoreCalculator.resetScores();
            myStatusPanel.setVisible(false);
            myScore.setText(ZERO);
            myLevel.setText(ONE);
            myLines.setText(ZERO);
        }
    }

    /**
     * Pauses the Game.
     */
    public void pauseGame() {
        myIsPaused = !myIsPaused;
        if (myIsPaused) {
            myStatusPanel.setVisible(true);
            myStatusText.setText("PAUSED");
        } else {
            myStatusPanel.setVisible(false);
        }
    }

    @Override
    protected void paintComponent(final Graphics theGfx) {
        BufferedImage img = null;
        super.paintComponent(theGfx);
        try {
            img = ImageIO.read(new File(BACKGROUND_IMG));
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        theGfx.drawImage(img, 0, 0, this);
    }

    @Override
    public void update(final Observable theObs, final Object theArg) {
        if (theArg instanceof TetrisPiece) {
            if (myStatusPanel.isVisible()) {
                myStatusPanel.setVisible(false);
            }
            if (Integer.parseInt(myScore.getText()) == 0) {
                myScore.setText(ZERO);
            }
            ScoreCalculator.newBlock();
            myScore.setText(String.valueOf(ScoreCalculator.getScore()));
        } else if (theArg instanceof Boolean) {
            if ((boolean) theArg) {
                myStatusText.setText(GAME_OVER);
                myStatusPanel.setVisible(true);
            }
        } else if (theArg instanceof Integer[]) {
            ScoreCalculator.addLines((Integer[]) theArg);
            myLines.setText(String.valueOf(ScoreCalculator.getLinesCleared()));
            myLevel.setText(String.valueOf(ScoreCalculator.getLevel()));
            myScore.setText(String.valueOf(ScoreCalculator.getScore()));
        }
    }
}
