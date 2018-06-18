package gamefiles;

import java.util.List;

import biuoop.KeyboardSensor;
import collisionsystem.Counter;
import levels.LevelInformation;

/**
 * A GameFlow class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GameLevel gameLevel;

    /**
     * constructor.
     *
     * @param ar
     *            animation runner.
     * @param ks
     *            keyborad sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
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
        Counter lives = new Counter(2);
        Counter score = new Counter(0);
        for (LevelInformation levelInfo : levels) {
            this.gameLevel = new GameLevel(levelInfo, this.ks, this.ar, lives, score);
            EndScreen theEndScreen = new EndScreen(this.gameLevel.getSprites(), this.gameLevel, this.ks);
            StopAnimation endAnimation = new StopAnimation(this.ar, this.ks, KeyboardSensor.SPACE_KEY, theEndScreen);
            this.gameLevel.initialize();
            while (this.gameLevel.getBlocksRemained().getValue() != 0 && this.gameLevel.getLives().getValue() != 0) {
                this.gameLevel.playOneTurn();
                if (this.gameLevel.getBallsRemain().getValue() == 0) {
                    lives.decrease(1);
                }
            }
            if (this.gameLevel.getLives().getValue() == 0) {
                this.ar.run(endAnimation);
                break;
            }
            if (levels.size() - 1 == levels.lastIndexOf(levelInfo)
                    && this.gameLevel.getBlocksRemained().getValue() == 0) {
                this.ar.run(endAnimation);
            }
        }
    }
}
