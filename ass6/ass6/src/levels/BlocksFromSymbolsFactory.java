package levels;

import java.util.HashMap;
import java.util.Map;

import collisionsystem.Block;

/**
 * BlocksFromSymbolsFactory class.
 *
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     *
     * @param spacerWidths
     *            map.
     * @param blockCreators
     *            map.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * basic constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators = new HashMap<String, BlockCreator>();
    }

    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s
     *            string
     * @return true or false.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s
     *            string.
     * @return true or false.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associated with symbol s. The
     * block will be located at position (xpos, ypos).
     *
     * @param s
     *            string.
     * @param xpos
     *            int.
     * @param ypos
     *            int.
     * @return Block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s
     *            string.
     * @return int.
     */
    public int getSpaceWidth(String s) {

        return Integer.valueOf(this.spacerWidths.get(s));

    }

    /**
     * add spacer.
     *
     * @param symbol
     *            string.
     * @param width
     *            int.
     */
    public void addSpacer(String symbol, int width) {
        this.spacerWidths.put(symbol, width);

    }

    /**
     * add block creator.
     *
     * @param symbol
     *            string.
     * @param blockCreator
     *            Block creator.
     */
    public void addBlockCreator(String symbol, BlockCreator blockCreator) {
        this.blockCreators.put(symbol, blockCreator);

    }

}
