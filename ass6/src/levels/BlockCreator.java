package levels;

import collisionsystem.Block;

/**
 * a BlockCreator interface.
 */
public interface BlockCreator {
    /**
     * create block.
     *
     * @param xpos
     *            the x.
     * @param ypos
     *            the y.
     * @return Block.
     */
    Block create(int xpos, int ypos);
}
