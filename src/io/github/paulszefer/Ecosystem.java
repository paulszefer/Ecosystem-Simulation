package io.github.paulszefer;

import java.util.ArrayList;

/**
 * Drives a simulation of an ecosystem of pools filled with guppies.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Ecosystem {

    /**
     * The collection of pools in the ecosystem.
     */
    private ArrayList<Pool> pools;

    /**
     * Creates an ecosystem of pools.
     */
    public Ecosystem() {

        pools = new ArrayList<>();

    }

    /**
     * Returns the pools in the ecosystem.
     *
     * @return the pools in the ecosystem
     */
    public ArrayList<Pool> getPools() {

        return pools;

    }

    /**
     * Sets the pools in the ecosystem.
     *
     * @param pools the pools to set
     */
    public void setPools(ArrayList<Pool> pools) {

        if (pools != null) {

            this.pools = pools;

        }

    }

    /**
     * Adds the given pool to the ecosystem.
     *
     * @param newPool a new pool to add to the ecosystem
     */
    public void addPool(Pool newPool) {

        if (newPool != null) {

            pools.add(newPool);

        }

    }

    /**
     * Removes all of the pools from the ecosystem.
     */
    public void reset() {

        pools.clear();

    }

    /**
     * Returns the current population of guppies in the ecosystem.
     *
     * @return the current population of guppies in the ecosystem
     */
    public int getGuppyPopulation() {

        int population = 0;

        for (Pool pool : pools) {

            population += pool.getPopulation();

        }

        return population;

    }

    /**
     * Adjusts each pool in the ecosystem for overcrowding.
     *
     * @return the number of guppies that died to overcrowding
     */
    public int adjustForCrowding() {

        int diedToOverCrowding = 0;

        for (Pool pool : pools) {

            diedToOverCrowding += pool.adjustForCrowding();

        }

        return diedToOverCrowding;

    }

}
