package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import collisionsystem.Block;
import collisionsystem.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Background;
/**
 * A LevelSpecificationReader class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class LevelSpecificationReader {

    private static final String[] FIELDS = new String[] {"level_name", "ball_velocities", "background",
            "block_definitions", "paddle_speed", "paddle_width", "blocks_start_x", "blocks_start_y", "row_height",
            "num_blocks" };

    /**
     * split the string.
     *
     * @param reader
     *            Reader.
     * @param start
     *            string.
     * @param end
     *            string.
     * @return List.
     */
    private static List<String> levelSplit(Reader reader, String start, String end) {
        List<String> levels = new ArrayList<String>();
        BufferedReader buffReader = new BufferedReader(reader);
        String line;
        try {
            line = buffReader.readLine();
            while (line != null) {
                if (line.contains(start)) {
                    while (!line.contains(end)) {
                        line = line + "\n" + buffReader.readLine();
                    }
                    levels.add(line);
                }
                line = buffReader.readLine();
            }
        } catch (IOException exc) {
            System.out.print("Exception Thrown: " + exc.getMessage());
            exc.printStackTrace();
        }
        try {
            buffReader.close();
        } catch (IOException exc) {
            System.out.print("Exception Thrown: " + exc.getMessage());
            exc.printStackTrace();
        }
        return levels;
    }

    /**
     * string to ball velocities.
     *
     * @param velocities
     *            string.
     * @return list.
     */
    private static List<Velocity> stringToBallVelos(String velocities) {
        List<Velocity> ballsVel = new ArrayList<>();
        String[] veloStrings = velocities.split(" ");
        for (String singleVelo : veloStrings) {
            if (singleVelo.contains(",")) {
                String[] angleSpeed = singleVelo.split(",");
                ballsVel.add(
                        Velocity.fromAngleAndSpeed(Integer.valueOf(angleSpeed[0]), Integer.valueOf(angleSpeed[1])));
            }
        }
        return ballsVel;
    }

    /**
     * check the file.
     *
     * @param info
     *            Map.
     * @return true or false.
     */
    private static Boolean checkFields(Map<String, String> info) {
        for (String field : FIELDS) {
            if (!info.containsKey(field)) {
                return false;
            }
        }
        return true;
    }

    /**
     * from string to level.
     *
     * @param level
     *            string.
     * @return Level.
     * @throws RuntimeException
     *             run time.
     */
    private static Level stringToLevel(String level) throws RuntimeException {

        Map<String, String> levelMap = stringToMap(level, ":", "\n");

        int startBlocks, endBlocks;
        startBlocks = level.indexOf("START_BLOCKS") + "START_BLOCKS".length() + 1;
        endBlocks = level.indexOf("END_BLOCKS");
        String blocksArrange = level.substring(startBlocks, endBlocks);
        String[] blocksArrangeSplit = blocksArrange.split("\n");

        String levelName;
        String ballVelo;
        String bg;
        String blockDef;
        int paddleWidth;
        int paddleSpeed;
        int blockStartx;
        int blockStarty;
        int rowHeight;
        int numOfBlocks;
        Background background = null;
        List<Velocity> ballsVelocity;

        if (checkFields(levelMap)) {
            levelName = levelMap.get("level_name");
            ballVelo = levelMap.get("ball_velocities");
            bg = levelMap.get("background");
            if (bg.contains("image")) {
                try {
                    String[] splitedString = bg.split("[()]");
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(splitedString[1]);
                    Image image = ImageIO.read(is);
                    background = new Background(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
                Color bgColor = new ColorsParser(bg).colorFromString();
                background = new Background();
                background.addSprite(new Block(rectangle, bgColor, 1));
            }
            blockDef = levelMap.get("block_definitions");
            paddleSpeed = Integer.valueOf(levelMap.get("paddle_speed"));
            paddleWidth = Integer.valueOf(levelMap.get("paddle_width"));
            blockStartx = Integer.valueOf(levelMap.get("blocks_start_x"));
            blockStarty = Integer.valueOf(levelMap.get("blocks_start_y"));
            rowHeight = Integer.valueOf(levelMap.get("row_height"));
            numOfBlocks = Integer.valueOf(levelMap.get("num_blocks"));
            ballsVelocity = stringToBallVelos(ballVelo);
        } else {
            throw new RuntimeException("Level spc does not contain all required fields.");
        }

        List<Block> blocks = blocksFromFactory(blockStartx, blockStarty, rowHeight, blockDef, blocksArrangeSplit);

        return new Level(ballsVelocity, paddleSpeed, paddleWidth, levelName, background, blocks, numOfBlocks);
    }

    /**
     * return list of block.
     *
     * @param blockStartx
     *            int.
     * @param blockStarty
     *            int.
     * @param rowHeight
     *            int.
     * @param blockDef
     *            string.
     * @param blocksArrangeSplit
     *            array.
     * @return list.
     */
    private static List<Block> blocksFromFactory(int blockStartx, int blockStarty, int rowHeight, String blockDef,
            String[] blocksArrangeSplit) {
        int currentX = blockStartx;
        int currentY = blockStarty;
        InputStreamReader isr = null;
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDef);
        System.out.println(blockDef);
        try {
            isr = new InputStreamReader(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BlocksFromSymbolsFactory blockFactory = null;
        blockFactory = BlocksDefinitionReader.fromReader(isr);

        try {
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Block> gameBlockList = new ArrayList<>();
        for (String blockLine : blocksArrangeSplit) {
            for (char symbol : blockLine.toCharArray()) {
                if (blockFactory.isSpaceSymbol(String.valueOf(symbol))) {
                    currentX += blockFactory.getSpaceWidth(String.valueOf(symbol));
                }
                if (blockFactory.isBlockSymbol(String.valueOf(symbol))) {
                    Block block = blockFactory.getBlock(String.valueOf(symbol), currentX, currentY);
                    gameBlockList.add(block);
                    currentX += (int) block.getCollisionRectangle().getWidth();
                }
            }
            currentX = blockStartx;
            currentY += rowHeight;
        }

        return gameBlockList;
    }

    /**
     * from string to map.
     *
     * @param toMap
     *            atring to map.
     * @param splitBy
     *            the key.
     * @param splitString
     *            string.
     * @return Map.
     */
    public static Map<String, String> stringToMap(String toMap, String splitBy, String splitString) {
        Map<String, String> mappedString = new TreeMap<>();
        String[] splitedLine = toMap.split(splitString);
        for (String lineSection : splitedLine) {
            if (lineSection.contains(splitBy)) {
                String[] secondSplit = lineSection.split(splitBy);
                mappedString.put(secondSplit[0], secondSplit[1]);
            }
        }
        return mappedString;
    }

    /**
     * level information list from file.
     *
     * @param reader
     *            Reader.
     * @return List.
     */
    public static List<LevelInformation> fromReader(Reader reader) {
        List<String> levels = levelSplit(reader, "START_LEVEL", "END_LEVEL");
        System.out.println(levels.isEmpty());
        List<LevelInformation> levelsInfo = new ArrayList<>();
        Level singleLevel;
        for (String level : levels) {
            try {
                singleLevel = stringToLevel(level);
                System.out.println(singleLevel.levelName());
                levelsInfo.add(singleLevel);
            } catch (RuntimeException exc) {
                System.out.println(exc.getMessage());
                exc.printStackTrace();
            }
        }
        return levelsInfo;
    }

}
