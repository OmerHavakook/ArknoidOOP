package gamefiles;

import java.io.File;
import java.io.IOException;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import collisionsystem.Counter;
import invanders.SpaceInvndersLevel;
import levels.LevelInformation;
import score.HighScoresTable;
import score.ScoreInfo;

/**
 * A GameFlow class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GameLevel gameLevel;
    private ScoreInfo score;
    private HighScoresTable highScoresTable;
    private Counter numOfLevel;
    private int speed;
    private File file;

    /**
     * constructor.
     *
     * @param ar
     *            animation runner.
     * @param ks
     *            keyborad sensor.
     * @param file
     *            file.
     * @param highScoresTable
     *            high scores.
     * @param score
     *            score info.
     * @param file
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable highScoresTable, ScoreInfo score,
            File file) {
        this.ar = ar;
        this.ks = ks;
        this.highScoresTable = highScoresTable;
        this.score = score;
        this.speed = 90;
        this.numOfLevel = new Counter();
        this.file = file;
    }

    /**
     * Gamelevel getter.
     *
     * @return this.gaeLevel.
     */
    public GameLevel getGameLevel() {
        return this.gameLevel;
    }

    /**
     * run the level.
     *
     * @param level
     *            the list of level.
     */
    public void runLevels(LevelInformation level) {
        this.score.reset();
        Counter lives = new Counter(3);
        Counter score1 = new Counter(0);
        level = new SpaceInvndersLevel();
        this.gameLevel = new GameLevel(level, this.ks, this.ar, lives, score1, this.speed, this.numOfLevel);
        this.gameLevel.initialize();
        while (this.gameLevel.getBlocksRemained().getValue() >= 0 && this.gameLevel.getLives().getValue() >= 0) {
            this.speed = (int) (this.speed * 1.2);
            if (this.gameLevel.getBlocksRemained().getValue() == 0) {
                level = new SpaceInvndersLevel();
                this.numOfLevel.increase(1);
                score1 = this.gameLevel.getScore();
                lives = this.gameLevel.getLives();
                this.gameLevel = new GameLevel(level, this.ks, this.ar, lives, score1, this.speed, this.numOfLevel);
                this.gameLevel.initialize();
            }

            this.gameLevel.playOneTurn();
            if (this.gameLevel.getLives().getValue() == 0) {
                break;
            }
        }
        highScore();
    }

    /**
     * show high score if needed.
     */
    private void highScore() {
        this.score = new ScoreInfo("", this.getGameLevel().getScore().getValue());
        if (this.highScoresTable.getRank(score.getScore()) < 3) {
            DialogManager dialog = ar.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.score.setName(name);
            this.highScoresTable.add(score);
            try {
                this.highScoresTable.save(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ar.run(new KeyPressStoppableAnimation(new EndScreen(this.gameLevel.getSprites(), this.gameLevel, this.ks),
                    this.ks, KeyboardSensor.SPACE_KEY));
            ar.run(new KeyPressStoppableAnimation(new HighScoresAnimation(highScoresTable, this.ks), this.ks,
                    KeyboardSensor.SPACE_KEY));
        } else {
            ar.run(new KeyPressStoppableAnimation(new EndScreen(this.gameLevel.getSprites(), this.gameLevel, this.ks),
                    this.ks, KeyboardSensor.SPACE_KEY));
        }

    }
}
