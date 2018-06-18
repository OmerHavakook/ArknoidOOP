package gamefiles;

import java.io.File;
import java.io.IOException;
import java.util.List;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import collisionsystem.Counter;
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
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, File file, HighScoresTable highScoresTable,
            ScoreInfo score) {
        this.ar = ar;
        this.ks = ks;
        this.file = file;
        this.highScoresTable = highScoresTable;
        this.score = score;
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
     * run the level from the list.
     *
     * @param levels
     *            the list of level.
     */
    public void runLevels(List<LevelInformation> levels) {
        this.score.reset();
        Counter lives = new Counter(7);
        Counter score1 = new Counter(0);
        for (LevelInformation levelInfo : levels) {
            this.gameLevel = new GameLevel(levelInfo, this.ks, this.ar, lives, score1);
            this.gameLevel.initialize();
            while (this.gameLevel.getBlocksRemained().getValue() != 0 && this.gameLevel.getLives().getValue() != 0) {
                this.gameLevel.playOneTurn();
                if (this.gameLevel.getBallsRemain().getValue() == 0) {
                    lives.decrease(1);
                }
            }
            if (this.gameLevel.getLives().getValue() == 0) {
                break;
            }
            if (levels.size() - 1 == levels.lastIndexOf(levelInfo)
                    && this.gameLevel.getBlocksRemained().getValue() == 0) {
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
