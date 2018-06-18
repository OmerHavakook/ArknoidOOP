package levels;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * LevelSetReader class.
 *
 */
public class LevelSetReader {

    private Reader reader;

    /**
     * constructor.
     *
     * @param reader
     *            the reader.
     */
    public LevelSetReader(Reader reader) {
        this.reader = reader;
    }

    /**
     * map the file by line.
     *
     * @return this.lineof file.
     */
    public Map<Integer, String> lineOfFile() {
        Map<Integer, String> lineOfFile = new TreeMap<>();
        String currentLine;
        LineNumberReader lnr = new LineNumberReader(this.reader);
        int numOfLine = 0;
        try {

            currentLine = lnr.readLine();
            while (currentLine != null) {
                lineOfFile.put(numOfLine, currentLine);
                numOfLine++;
                currentLine = lnr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineOfFile;
    }

    /**
     * split the even lines.
     *
     * @param allLine
     *            map.
     * @return Map.
     */
    public Map<String, LevelSet> splitEvenLine(Map<Integer, String> allLine) {
        Map<String, LevelSet> resultMap = new TreeMap<>();
        String[] splitedLine;
        String lineToSplit;
        String currentKey = null;
        String currentName = null;
        String path;
        for (Map.Entry<Integer, String> line : allLine.entrySet()) {
            if (line.getKey() % 2 == 0) {
                lineToSplit = line.getValue();
                splitedLine = lineToSplit.split(":");
                currentKey = splitedLine[0];
                currentName = splitedLine[1];
            }
            if (line.getKey() % 2 == 1) {
                path = line.getValue();
                resultMap.put(currentKey, new LevelSet(currentName, path));
            }
        }
        return resultMap;
    }

    /**
     * create path.
     *
     * @param allLine
     *            map.
     * @return List.
     */
    public List<String> createPath(Map<Integer, String> allLine) {
        List<String> path = new ArrayList<>();
        for (Map.Entry<Integer, String> line : allLine.entrySet()) {
            if (line.getKey() % 2 == 1) {
                path.add(line.getValue());
            }
        }
        return path;
    }

}
