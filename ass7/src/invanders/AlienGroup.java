package invanders;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import collisionsystem.Velocity;
import gamefiles.GameEnvironment;
import gamefiles.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;

/**
 * A AlienGroup class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class AlienGroup implements Sprite {

    private int startSheild;
    private double dx;
    private List<Alien> listOfAlien;
    private List<ArrayList<Alien>> alienMatrix;
    private GameLevel game;

    /**
     * this is the constructor of EnemyMovement.
     *
     * @param startSheild
     *            - as an int of the starting of the sheild.
     * @param dx
     *            - as a double
     * @param listOfAlien
     *            - as the list of enemies
     * @param game
     *            - as the GameLevel
     */
    public AlienGroup(int startSheild, double dx, List<Alien> listOfAlien, GameLevel game) {
        this.startSheild = startSheild;
        this.dx = dx;
        this.listOfAlien = listOfAlien;
        this.alienMatrix = new ArrayList<ArrayList<Alien>>();
        createMatrix();
        this.game = game;
    }

    /**
     * @return - a list of Aliens that appears a the bottom of colums
     */
    private List<Alien> findLastInList() {
        List<Alien> lastInColumns = new ArrayList<>();
        for (int i = 0; i < this.alienMatrix.size(); i++) {
            if (this.alienMatrix.get(i).size() > 0) {
                lastInColumns.add((Alien) this.alienMatrix.get(i).get(this.alienMatrix.get(i).size() - 1));
            }
        }
        return lastInColumns;
    }

    /**
     * this method creates a sort of matrix to represent the chickens. in this
     * way it's easier to move and make one shot.
     */
    public void createMatrix() {
        ArrayList<Alien> oneList = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            for (int i = j; i < 50; i += 10) {
                oneList.add(this.listOfAlien.get(i));
            }
            this.alienMatrix.add(oneList);
            oneList = new ArrayList<>();
        }
    }

    /**
     * update the list of linkes list in case of enemy's delition.
     *
     * @param alien
     *            - as the enemy to remove.
     */
    public void updateMatrix(Alien alien) {
        for (int i = 0; i < this.alienMatrix.size(); i++) {
            this.alienMatrix.get(i).remove(alien);
        }
        this.alienMatrix.remove(alien);
        this.listOfAlien.remove(alien);
    }

    /**
     * make one shoot from the chickens.
     *
     * @param ge
     *            - as a GameEnviroment object
     */
    public void shoot(GameEnvironment ge) {
        List<Alien> lastInColumns = this.findLastInList();
        if (lastInColumns.size() != 0) {
            Random rand = new Random();
            int index = rand.nextInt(lastInColumns.size());
            Point p = new Point(lastInColumns.get(index).getCollisionRectangle().getBottomLeft().getX() + 20,
                    lastInColumns.get(index).getCollisionRectangle().getBottomLeft().getY() + 4);
            Ball oneShot = new Ball(p, 3, Color.red, ge, true);
            oneShot.setVelocity(Velocity.fromAngleAndSpeed(180, 400));
            oneShot.addToGame(game);
            // add the ball to the game
            game.addBall(oneShot);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        return;
    }

    /**
     * initial the enemy at the start location.
     *
     * @param speed
     *            - as the initial speed
     */
    public void resetGroup(double speed) {
        this.dx = speed;
        for (Alien alien : this.listOfAlien) {
            alien.setRectangle(alien.returnRect());
        }
        boolean check = true;
        while (check) {
            if (findMinX(50, 1 / 60) > 0) {
                for (Alien alien : this.listOfAlien) {
                    double pX = alien.getCollisionRectangle().getUpperLeft().getX() - 50;
                    double pY = alien.getCollisionRectangle().getUpperLeft().getY();
                    Point p = new Point(pX, pY);
                    Rectangle r = new Rectangle(p, alien.getCollisionRectangle().getWidth(),
                            alien.getCollisionRectangle().getHeight());
                    alien.setRectangle(r);
                }
            } else {
                check = false;
            }
        }
    }

    @Override
    public void timePassed(double dt) {
        if (findMinX(dx, dt) > 0 && findMaxX(dx, dt) < 800) {
            for (int i = 0; i < this.listOfAlien.size(); i++) {
                this.listOfAlien.get(i).moveBy(dx, dt);
            }
        } else {
            this.dx = (int) dx * -1.1;
            for (int j = 0; j < this.listOfAlien.size(); j++) {
                double width = this.listOfAlien.get(j).getCollisionRectangle().getWidth();
                double height = this.listOfAlien.get(j).getCollisionRectangle().getHeight();
                double x = this.listOfAlien.get(j).getCollisionRectangle().getUpperLeft().getX();
                double y = this.listOfAlien.get(j).getCollisionRectangle().getUpperLeft().getY();
                this.listOfAlien.get(j).setRectangle(new Rectangle(new Point(x, y + 10), width, height));
            }
        }
    }

    /**
     * this method finds the max value of the x coordinates possible to the
     * chickens' list.
     *
     * @param dx1
     *            - as the dx parameter
     * @param dt
     *            - as a double
     * @return - an int
     */
    public double findMaxX(double dx1, double dt) {
        double x = 0;
        for (Alien a : this.listOfAlien) {
            if (a.getCollisionRectangle().getUpperLeft().getX() + 40 > x) {
                x = a.getCollisionRectangle().getUpperLeft().getX() + 40;
            }
        }
        x += dx1 * dt;
        return x;
    }

    /**
     * this method finds the min value of the x coordinates possible to the
     * chickens' list.
     *
     * @param dx1
     *            - as the dx parameter
     * @param dt
     *            - as a double
     * @return - an int
     */
    public int findMinX(double dx1, double dt) {
        int x = 800;
        for (Alien a : this.listOfAlien) {
            if (a.getCollisionRectangle().getUpperLeft().getX() < x) {
                x = (int) a.getCollisionRectangle().getUpperLeft().getX();
            }
        }
        x += dx1 * dt;
        return x;
    }

    /**
     * this method checks if the chickens touch the sheild.
     *
     * @return true - if the chicken touches the sehild and false otherwise.
     */
    public boolean touchTheSheild() {
        int y = 0;
        List<Alien> lastInColoms = this.findLastInList();
        for (Alien e : lastInColoms) {
            if (e.getCollisionRectangle().getUpperLeft().getY() + 80 > y) {
                y = (int) e.getCollisionRectangle().getUpperLeft().getY() + 80;
            }
        }
        if (y >= this.startSheild) {
            return true;
        }
        return false;
    }

    /**
     * this method add this to the game.
     *
     * @param g
     *            - as a GameLevel object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
