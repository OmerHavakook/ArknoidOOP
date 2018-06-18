import java.io.File;
import java.io.IOException;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamefiles.GameFlow;
import gamefiles.GameLevel;
import gamefiles.Menu;
import gamefiles.Task;
import invanders.SpaceInvndersLevel;
import levels.LevelInformation;
import score.HighScoresTable;
import score.ScoreInfo;

/**
 * A Ass6Game class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Ass7Game {

    /**
     *
     * @param args
     *            input from the user
     */
    public static void main(String[] args) {
        GUI gui = new GUI("arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();

        File scoreFile = new File("highscores");
        HighScoresTable highScoresTable = new HighScoresTable(3);
        if (scoreFile.exists()) {
            try {
                highScoresTable.load(scoreFile);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } else {
            try {
                highScoresTable.save(scoreFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        menu(ar, ks, highScoresTable, scoreFile);
    }

    /**
     * run the menu.
     *
     * @param ar
     *            AnimationRunner.
     * @param ks
     *            KeyboardSensor.
     * @param highScore
     *            HighScoresTable.
     * @param scoreFile
     *            File.
     */
    private static void menu(AnimationRunner ar, KeyboardSensor ks, HighScoresTable highScore, File scoreFile) {
        while (true) {
            Menu<Task<Void>> menu = new MenuAnimation<>("Arkanoid", ks);
            menu.addSelection("s", "Start Game", new Task<Void>() {
                public Void run() {
                    LevelInformation spaceInvader = new SpaceInvndersLevel();
                    GameFlow flow = new GameFlow(ar, ks, highScore, new ScoreInfo(), scoreFile);
                    flow.runLevels(spaceInvader);
                    return null;
                }
            });
            menu.addSelection("h", "High Score", new Task<Void>() {
                public Void run() {
                    ar.run(new KeyPressStoppableAnimation(new HighScoresAnimation(highScore, ks), ks,
                            KeyboardSensor.SPACE_KEY));
                    return null;
                }

            });
            menu.addSelection("q", "Quit", new Task<Void>() {
                public Void run() {
                    System.exit(0);
                    return null;
                }
            });
            ar.run(menu);
            Task<Void> t = (Task<Void>) menu.getStatus();
            t.run();
        }
    }

}
