package io.github.paulszefer;

/**
 * Represents an object that can load its data from an external file.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public interface ILoadable {

    /**
     * Loads the applicable data.
     *
     * @return true if successful; false otherwise
     */
    boolean loadData();
}
