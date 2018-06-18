import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import levels.LevelInformation;
import levels.LevelSet;
import levels.LevelSetReader;
import levels.LevelSpecificationReader;
import score.HighScoresTable;
import score.ScoreInfo;

/**
 * A Ass6Game class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Ass6Game {

    /**
     *
     * @param args
     *            input from the user
     */
    public static void main(String[] args) {
        GUI gui = new GUI("arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();

        String levelSetName = "level_sets.txt";
        if (args.length > 0) {
            levelSetName = args[0];
        }

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetName);
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        LevelSetReader levelSetReader = new LevelSetReader(isr);
        Map<Integer, String> allLineAtFile = levelSetReader.lineOfFile();
        List<String> levelsPath = levelSetReader.createPath(allLineAtFile);
        Map<String, LevelSet> keyToLevelSetName = levelSetReader.splitEvenLine(allLineAtFile);
        Map<String, String> keyToLevelPath = new HashMap<>();
        for (String key : keyToLevelSetName.keySet()) {
            keyToLevelPath.put(key, levelsPath.get(i));
            i++;
        }

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

        menu(ar, ks, highScoresTable, scoreFile, keyToLevelSetName, keyToLevelPath, levelsPath);
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
     * @param keyToNameSet
     *            Map.
     * @param keyToPath
     *            Map.
     * @param levelPath
     *            Liat.
     */
    private static void menu(AnimationRunner ar, KeyboardSensor ks, HighScoresTable highScore, File scoreFile,
            Map<String, LevelSet> keyToNameSet, Map<String, String> keyToPath, List<String> levelPath) {
        while (true) {
            Menu<Task<Void>> menu = new MenuAnimation<>("Arkanoid", ks);
            Menu<Task<Void>> subMenu = new MenuAnimation<>("", ks);
            for (String key : keyToNameSet.keySet()) {
                subMenu.addSelection(key, "(" + key + ")" + keyToNameSet.get(key).getName(), new Task<Void>() {
                    public Void run() {
                        GameFlow game = new GameFlow(ar, ks, scoreFile, highScore, new ScoreInfo());
                        List<LevelInformation> levelList = levelsFromPath(keyToNameSet.get(key).getPath());
                        game.runLevels(levelList);
                        return null;
                    }
                });
                menu.addSubMenu(key, keyToNameSet.get(key).getName(), subMenu);
            }

            menu.addSelection("s", "Start Game", new Task<Void>() {
                public Void run() {
                    ar.run(subMenu);
                    Task<Void> t = (Task<Void>) subMenu.getStatus();
                    t.run();
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

    /**
     * level frome path.
     *
     * @param s
     *            string.
     * @return List.
     */
    public static List<LevelInformation> levelsFromPath(String s) {
        InputStreamReader isr = null;
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
        isr = new InputStreamReader(inputStream);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        levels = LevelSpecificationReader.fromReader(isr);
        try {
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levels;
    }

}
