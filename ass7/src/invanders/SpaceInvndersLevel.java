package invanders;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import collisionsystem.Block;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprites.Background;
import sprites.Sprite;

/**
 * SpaceInvndersLevel class.
 *
 */
public class SpaceInvndersLevel implements LevelInformation {

    private Background background;
    private List<Block> blockShield;
    private List<Alien> aliens;

    /**
     * constructor.
     *
     */
    public SpaceInvndersLevel() {
        this.background = new Background(null);
        this.aliens = new ArrayList<>();
        this.blockShield = new ArrayList<>();
    }


    @Override
    public int paddleSpeed() {
        return 500;
    }

    @Override
    public int paddleWidth() {
        return 60;
    }

    @Override
    public String levelName() {
        return null;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        int counter = 0;
        for (int i = 460; i <= 475; i += 5) {
            for (int j = 135; j <= 665; j += 5) {
                Block block = new Block((new Rectangle((new Point(j, i)), 5, 5)), Color.BLUE, 1);
                this.blockShield.add(block);
                counter++;
                if (counter == 25) {
                    j += 75;
                    counter = 0;
                }
            }
        }
        return this.blockShield;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 50;
    }


    @Override
    public List<Alien> aliens() {
        List<Alien> before = new ArrayList<>();
        int xPos = 0;
        int yPos = 50;
        InputStream imageIS = ClassLoader.getSystemClassLoader().getResourceAsStream("alien.png");
        Image image1 = null;
        try {
            image1 = ImageIO.read(imageIS);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            imageIS.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Alien alien1;
        if (image1 != null) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    alien1 = new Alien(xPos, yPos, image1);
                    before.add(alien1);
                    xPos += 50;
                }
                yPos += 40;
                xPos = 0;
            }
            for (Alien e : before) {
                this.aliens.add(e);
            }
            return this.aliens;
        }
        return null;
    }

}
