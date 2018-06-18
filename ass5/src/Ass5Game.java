import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamefiles.AnimationRunner;
import gamefiles.GameFlow;
import gamefiles.GameLevel;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;

/**
 * A Ass5Game class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Ass5Game {

    /**
     *
     * @param args
     *            input from the user
     */
    public static void main(String[] args) {
        GUI gui = new GUI("arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        DirectHit level1 = new DirectHit();
        WideEasy level2 = new WideEasy();
        Green3 level3 = new Green3();
        FinalFour level4 = new FinalFour();
        GameFlow game = new GameFlow(ar, ks);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
            levels.add(level4);
        } else {
            for (String s : args) {
                if (s.valueOf(1).equals(s)) {
                    levels.add(level1);
                } else if (s.valueOf(2).equals(s)) {
                    levels.add(level2);
                } else if (s.valueOf(3).equals(s)) {
                    levels.add(level3);
                } else if (s.valueOf(4).equals(s)) {
                    levels.add(level4);
                }

            }
        }

        game.runLevels(levels);
    }
}
