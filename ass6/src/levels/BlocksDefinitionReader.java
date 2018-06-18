package levels;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

/**
 * BlocksDefinitionReader class.
 */
public class BlocksDefinitionReader {

    /**
     * merge the Map to defult with bdef.
     *
     * @param defaultBlockInfo
     *            map of defult.
     * @param line
     *            string
     * @return map.
     */
    private static Map<String, String> mergeMap(Map<String, String> defaultBlockInfo, String line) {
        Map<String, String> finalMap = new TreeMap<>();
        Map<String, String> splitMap = stringToMap(line, ":", " ");
        finalMap.putAll(splitMap);
        for (Map.Entry<String, String> entry : defaultBlockInfo.entrySet()) {
            if (!splitMap.containsKey(entry.getKey())) {
                finalMap.put(entry.getKey(), entry.getValue());
            }
        }
        return finalMap;
    }

    /**
     * take line and create block creator.
     *
     * @param line
     *            string.
     * @param defaultMap
     *            the map.
     * @return block creator.
     */
    private static BlockCreator lineToBlockCreator(String line, Map<String, String> defaultMap) {
        Map<String, String> mergedMap = mergeMap(defaultMap, line);
        Map<Integer, Color> colors = new TreeMap<>();
        Map<Integer, Image> images = new TreeMap<>();
        System.out.println(mergedMap.get("hit_points"));
        int hitPoints = Integer.valueOf(mergedMap.get("hit_points"));
        int height = Integer.valueOf(mergedMap.get("height"));
        int width = Integer.valueOf(mergedMap.get("width"));
        Color stroke = null;

        if (mergedMap.containsKey("stroke")) {
            ColorsParser parser = new ColorsParser(mergedMap.get("stroke"));
            stroke = parser.colorFromString();
        }

        String checker = "";
        if (mergedMap.keySet().contains("fill")) {
            checker = "fill";
        } else {
            for (String key : mergedMap.keySet()) {
                if (key.contains("fill")) {
                    checker = key;
                    break;
                }
            }
        }

        if (mergedMap.get(checker).contains("color")) {
            for (String key : mergedMap.keySet()) {
                if (key.contains("fill")) {
                    ColorsParser colorsParser = new ColorsParser(mergedMap.get(key));
                    if (key.contains("-")) {
                        Integer fillnumber = Integer.valueOf(key.substring(key.indexOf("-") + 1, key.indexOf("-") + 2));
                        colors.put(fillnumber, colorsParser.colorFromString());
                    } else {
                        colors.put(new Integer(0), colorsParser.colorFromString());
                    }
                }
            }
            return new BlockDecorator(height, width, null, colors, stroke, hitPoints);

        }

        if (line.contains("image")) {
            for (String key : mergedMap.keySet()) {
                if (key.contains("fill")) {
                    Image img = null;
                    try {
                        String[] splitedString = mergedMap.get(key).split("[()]");
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(splitedString[1]);
                        img = ImageIO.read(is);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (key.contains("-")) {
                        Integer imgNumber = Integer.valueOf(key.substring(key.indexOf("-") + 1, key.indexOf("-") + 2));
                        images.put(imgNumber, img);
                    } else {
                        images.put(0, img);
                    }
                }
            }
            return new BlockDecorator(height, width, images, null, stroke, hitPoints);
        }
        return null;
    }

    /**
     * read the file with buffer.
     *
     * @param reader
     *            Reader.
     * @return string.
     */
    private static String readFile(Reader reader) {
        BufferedReader buffReader = new BufferedReader(reader);
        String lineBuf;
        String result = null;
        try {
            lineBuf = buffReader.readLine();
            while (lineBuf != null) {
                if (!lineBuf.contains("#")) {
                    result += "\\\\" + lineBuf;
                }
                lineBuf = buffReader.readLine();
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
        return result;
    }

    /**
     * return the block factory after read from file.
     *
     * @param reader
     *            the Reader.
     * @return BlocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) {
        Map<String, Integer> spacers = new TreeMap<>();
        Map<String, BlockCreator> blockCreateors = new TreeMap<>();
        Map<String, String> defaultBlockInformation = new TreeMap<>();

        String blockDefString = readFile(reader);
        String[] lineSplited = blockDefString.split("\\\\");

        for (String line : lineSplited) {
            if (line.contains("default")) {
                defaultBlockInformation = stringToMap(line, ":", " ");
                break;
            }
        }

        for (String defLine : lineSplited) {
            if (defLine.contains("bdef")) {
                BlockCreator blockCreator = lineToBlockCreator(defLine, defaultBlockInformation);
                String symbol = String.valueOf(defLine.charAt(defLine.indexOf(':') + 1));
                blockCreateors.put(symbol, blockCreator);
            } else if (defLine.contains("sdef")) {
                Map<String, String> stringSpacers = stringToMap(defLine, ":", " ");
                spacers.put(stringSpacers.get("symbol"), Integer.valueOf(stringSpacers.get("width")));
            }

        }
        return new BlocksFromSymbolsFactory(spacers, blockCreateors);
    }

    /**
     * take strings and craete map.
     *
     * @param toSplit
     *            string to split.
     * @param mapSplit
     *            key to split.
     * @param splitString
     *            string.
     * @return Map.
     */
    public static Map<String, String> stringToMap(String toSplit, String mapSplit, String splitString) {
        Map<String, String> mappedString = new TreeMap<>();
        String[] splitedLine = toSplit.split(splitString);
        for (String lineSection : splitedLine) {
            if (lineSection.contains(mapSplit)) {
                String[] secondSplit = lineSection.split(mapSplit);
                mappedString.put(secondSplit[0], secondSplit[1]);
            }
        }
        return mappedString;
    }

}
