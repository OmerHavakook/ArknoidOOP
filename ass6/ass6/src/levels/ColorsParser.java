package levels;

import java.awt.Color;

/**
 * ColorParser class.
 *
 */
public class ColorsParser {
    private String str;

    /**
     * constructor.
     *
     * @param str
     *            string.
     */
    public ColorsParser(String str) {
        this.str = str;

    }

    /**
     * return color from string.
     *
     * @return Color.
     */
    public Color colorFromString() {
        if (this.str.contains("RGB")) {
            return this.colorFromRGB();
        }
        return this.colorFromColorName();
    }

    /**
     * color frome name.
     *
     * @return Color.
     */
    private Color colorFromColorName() {
        String[] split = this.str.split("[()]");
        String nameColor = split[1];
        Color color = null;
        switch (nameColor) {
        case "blue":
            color = Color.blue;
            break;
        case "black":
            color = Color.black;
            break;
        case "cyan":
            color = Color.cyan;
            break;
        case "gray":
            color = Color.gray;
            break;
        case "lightGray":
            color = Color.lightGray;
            break;
        case "green":
            color = Color.green;
            break;
        case "orange":
            color = Color.orange;
            break;
        case "pink":
            color = Color.pink;
            break;
        case "red":
            color = Color.red;
            break;
        case "white":
            color = Color.white;
            break;
        case "yellow":
            color = Color.yellow;
            break;
        default:
            color = null;
        }
        return color;
    }

    /**
     * color frome rgb.
     *
     * @return Color.
     */
    private Color colorFromRGB() {
        int[] valOfRgb = new int[3];
        String allNumberOfRGB = this.str.replaceAll("[^0-9,]", "");
        String[] numOfRGB = allNumberOfRGB.split(",");
        for (int i = 0; i < numOfRGB.length; i++) {
            valOfRgb[i] = Integer.parseInt(numOfRGB[i]);
        }
        Color color = new Color(valOfRgb[0], valOfRgb[1], valOfRgb[2]);
        return color;
    }
}
