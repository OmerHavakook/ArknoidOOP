
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class Newtest {
    public static void main (String[] args){
        Newtest example = new Newtest();

    GUI gui = new GUI("title", 1000, 600);
    biuoop.Sleeper sleeper = new Sleeper();
    GameEnvironment g=new GameEnvironment();
    DrawSurface d = gui.getDrawSurface();
    Sprite b1=new Block(new Rectangle(new Point(50,100),100,200),Color.RED,2);
    Point Start = new Point(400,300);
    d.setColor(Color.GREEN);
    d.drawCircle((int) (Start.getX()),
            (int) (Start.getY()), 3);
    d.fillCircle((int) (Start.getX()),
            (int) (Start.getY()), 3);
    Point End = new Point(1000,500);
    Line line = new Line(Start, End);
    Point p = line.closestIntersectionToStartOfLine(new Rectangle(new Point(975,0),25,600));
    example.drawLine(line, d);
    if (p!=null){
        d.setColor(Color.BLUE);
        d.drawCircle((int) (p.getX()),
                (int) (p.getY()), 3);
        d.fillCircle((int) (p.getX()),
                (int) (p.getY()), 3);   
    }
    d.setColor(Color.RED);
    d.drawRectangle(975, 0, 25, 600);
    gui.show(d);

}

public void drawLine(Line l, DrawSurface d) {
    d.setColor(Color.black);
    d.drawLine((int) (l.start().getX()), (int) (l.start().getY()),
            (int) (l.end().getX()), (int) (l.end().getY()));
}
}