import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * A AbstractArtDrawing class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class AbstractArtDrawing {

    private static Line[] lArr = new Line[10]; // array of lines.

    /**
     * generate 10 random lines and put them in array.
     */
    public void generateRandomLine() {
        Line l = null;
        for (int i = 0; i < 10; ++i) {
            Random rand = new Random();
            l = new Line(rand.nextInt(400) + 1, rand.nextInt(300) + 1, rand.nextInt(400) + 1, rand.nextInt(300) + 1);
            lArr[i] = l;
        }
    }

    /**
     * draw line by given line and draw face.
     *
     * @param l
     *            line to draw it.
     * @param d
     *            DrawFace to draw on.
     */
    public static void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * draw random lines and the middle and intersection points between them.
     */
    public void drawRandomLines() {
        GUI gui = new GUI("Random lines", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        for (int j = 0; j < lArr.length; ++j) {
            AbstractArtDrawing.drawLine(lArr[j], d);
        }
        for (int i = 0; i < lArr.length; ++i) {
            int x = (int) lArr[i].middle().getX();
            int y = (int) lArr[i].middle().getY();
            d.setColor(Color.BLUE);
            d.fillCircle(x, y, 3);
        }
        for (int i = 0; i < lArr.length; ++i) {
            for (int j = 0; j < lArr.length; ++j) {
                if (!(lArr[i].equals(lArr[j]))) {
                    Point p = lArr[i].intersectionWith(lArr[j]);
                    if (p != null) {
                        d.setColor(Color.ORANGE);
                        d.fillCircle((int) p.getX(), (int) p.getY(), 3);
                    }
                }
            }
        }
        gui.show(d);
    }

    /**
     *
     * @param args
     *            input from the user
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.generateRandomLine();
        example.drawRandomLines();
    }
}
