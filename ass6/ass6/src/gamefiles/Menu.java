package gamefiles;

import animation.Animation;

/**
 * A Menu interface.
 *
 * @param <T>
 *            genric.
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public interface Menu<T> extends Animation {
    /**
     * add the menu selection.
     *
     * @param key
     *            the press key.
     * @param message
     *            string.
     * @param returnVal
     *            task to show.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * return the task of the selection.
     *
     * @return Task.
     */
    T getStatus();

    /**
     * add the sub menu.
     *
     * @param key
     *            the key of sub.
     * @param message
     *            the string .
     * @param subMenu
     *            the sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
