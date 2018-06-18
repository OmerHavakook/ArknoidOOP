package animation;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamefiles.Menu;

/**
 * A MenuAnimation class. implements Menu.
 *
 * @param <T>
 *            genric.
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class MenuAnimation<T> implements Menu<T> {

    private KeyboardSensor ks;
    private List<T> menuReturnValues;
    private List<String> menuNames;
    private List<String> menuKeys;
    private List<Menu<T>> listOfSubMenu;
    private boolean running;
    private T status;
    private Image image1 = null;
    private Image image2 = null;
    /**
     * constructor.
     *
     * @param menuTitle
     *            the title.
     * @param ks
     *            key board.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor ks) {
        this.ks = ks;
        this.menuNames = new ArrayList<>();
        this.menuKeys = new ArrayList<>();
        this.menuReturnValues = new ArrayList<>();
        running = false;
        this.listOfSubMenu = new ArrayList<>();
        try {
            image1 = ImageIO.read(
                    ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/startbackground.jpg"));
            image2 = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/sub.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.menuKeys.add(key);
        this.menuNames.add(message);
        this.menuReturnValues.add(returnVal);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawImage(0, 0, image1);
        d.drawImage(d.getWidth() / 3 - 30, d.getHeight() / 6 - 50, image2);

        for (int i = 0; i < menuReturnValues.size(); i++) {
            if (this.ks.isPressed((String) menuKeys.get(i))) {
                this.status = this.menuReturnValues.get(i);
                this.running = true;
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.running;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.listOfSubMenu.add(subMenu);
    }

}
