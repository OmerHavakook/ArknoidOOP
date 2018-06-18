package levels;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;

import collisionsystem.Block;
import geometry.Point;
import geometry.Rectangle;

/**
 * a BlockDecorator class.
 */
public class BlockDecorator implements BlockCreator {

    private Map<Integer, Image> fillImages;
    private Map<Integer, Color> fillColor;
    private Color stroke;
    private int hitPoints;
    private int height;
    private int width;

    /**
     * constructor.
     *
     * @param height
     *            int.
     * @param width
     *            int.
     * @param fillImages
     *            map.
     * @param fillColor
     *            map.
     * @param stroke
     *            color.
     * @param hitPoints
     *            int.
     */
    public BlockDecorator(int height, int width, Map<Integer, Image> fillImages, Map<Integer, Color> fillColor,
            Color stroke, int hitPoints) {
        this.fillImages = fillImages;
        this.fillColor = fillColor;
        this.stroke = stroke;
        this.hitPoints = hitPoints;
        this.height = height;
        this.width = width;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Rectangle rectangle = new Rectangle(new Point(xpos, ypos), this.width, this.height);
        return new Block(rectangle, this.fillColor, this.fillImages, stroke, hitPoints);
    }

}
