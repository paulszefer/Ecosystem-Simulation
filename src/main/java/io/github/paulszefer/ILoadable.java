package io.github.paulszefer;

import java.util.Scanner;

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
     * @return a scanner containing the data or null if unsuccessful
     */
    Scanner loadData();
}
