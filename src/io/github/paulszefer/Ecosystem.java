package io.github.paulszefer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Drives a simulation of an ecosystem of pools filled with guppies.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Ecosystem {

    /** Random number generator. */
    private Random generator = new Random();

    /** The collection of pools in the ecosystem. */
    private ArrayList<Pool> pools;

    /** The collection of streams in the ecosystem. */
    private ArrayList<Stream> streams;

    /** Creates an ecosystem of pools connected by streams. */
    public Ecosystem() {

        pools = new ArrayList<>();
        streams = new ArrayList<>();
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
     * Returns the streams in the ecosystem.
     *
     * @return the streams in the ecosystem
     */
    public ArrayList<Stream> getStreams() {

        return streams;
    }

    /**
     * Sets the pools in the ecosystem.
     *
     * @param pools
     *         the pools to set
     */
    public void setPools(ArrayList<Pool> pools) {

        if (pools != null) {
            this.pools = pools;
        }
    }

    /**
     * Sets the streams in the ecosystem.
     *
     * @param streams
     *         the streams to set
     */
    public void setStreams(ArrayList<Stream> streams) {

        if (streams != null) {
            this.streams = streams;
        }
    }

    /**
     * Adds the given pool to the ecosystem.
     *
     * @param pool
     *         a pool to add to the ecosystem
     */
    public void addPool(Pool pool) {

        if (pool != null) {
            pools.add(pool);
        }
    }

    /**
     * Adds the given stream to the ecosystem.
     *
     * @param stream
     *         a stream to add to the ecosystem
     */
    public void addStream(Stream stream) {

        if (stream != null) {
            streams.add(stream);
        }
    }

    /**
     * Removes all of the pools and streams from the ecosystem.
     */
    public void reset() {

        resetPools();
        resetStreams();
    }

    /**
     * Removes all of the pools from the ecosystem.
     */
    public void resetPools() {

        pools.clear();
    }

    /**
     * Removes all of the streams from the ecosystem.
     */
    public void resetStreams() {

        streams.clear();
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
     * <p>
     * If overcrowding occurs, guppies move through a stream to the next connected pool. The
     * number of guppies that die in transport or due to there being no further pool to move to is
     * calculated and returned.
     *
     * @return the number of guppies that died to overcrowding
     */
    public int adjustForCrowding() {

        int diedToOverCrowding = 0;

        for (Pool pool : pools) {

            ArrayList<Guppy> weakestGuppies = pool.adjustForCrowding();

            ArrayList<Stream> possibleStreams = new ArrayList<>();

            for (Stream stream : streams) {
                if (stream.getSource() == pool) {
                    possibleStreams.add(stream);
                }
            }

            if (possibleStreams.size() > 0) {
                Stream stream = possibleStreams.get(generator.nextInt(possibleStreams.size() - 1));
            } else {
                for (Guppy guppy : weakestGuppies) {
                    guppy.setIsAlive(false);
                }
                pool.addGuppies(weakestGuppies);
                diedToOverCrowding += weakestGuppies.size();
            }
        }

        return diedToOverCrowding;
    }
}
