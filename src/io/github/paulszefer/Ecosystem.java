package io.github.paulszefer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Drives a simulation of an ecosystem of pools filled with creatures.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Ecosystem {

    /** Random number generator. */
    private static final Random generator = new Random();

    /** The collection of pools in the ecosystem. */
    private List<Pool> pools;

    /** The collection of streams in the ecosystem. */
    private List<Stream> streams;

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
    public List<Pool> getPools() {

        return pools;
    }

    /**
     * Returns the streams in the ecosystem.
     *
     * @return the streams in the ecosystem
     */
    public List<Stream> getStreams() {

        return streams;
    }

    /**
     * Sets the pools in the ecosystem.
     *
     * @param pools
     *         the pools to set
     */
    public void setPools(List<Pool> pools) {

        if (pools != null) {
            this.pools = pools;

            for (int i = 0; i < pools.size() - 1; i++) {
                Stream stream = new Stream(pools.get(i), pools.get(i + 1));
                addStream(stream);
            }
        }
    }

    /**
     * Sets the streams in the ecosystem.
     *
     * @param streams
     *         the streams to set
     */
    public void setStreams(List<Stream> streams) {

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
            if (pools.size() > 1) {
                Stream stream = new Stream(pools.get(pools.size() - 2), pool);
                addStream(stream);
            }
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
     * Returns the current population of creatures in the ecosystem.
     *
     * @return the current population of creatures in the ecosystem
     */
    public int getCreaturePopulation() {

        int population = 0;

        for (Pool pool : pools) {
            population += pool.getPopulation();
        }

        return population;
    }

    /**
     * Adjusts each pool in the ecosystem for overcrowding.
     * <p>
     * If overcrowding occurs, creatures move through a stream to the next connected pool. The
     * number of creatures that die in transport or due to there being no further pool to move to is
     * calculated and returned.
     *
     * @return the number of creatures that died to overcrowding
     */
    public int adjustForCrowding() {

        int diedToOverCrowding = 0;

        for (Pool pool : pools) {

            List<Creature> weakestCreatures = pool.adjustForCrowding();

            Stream stream = getRandomStream(pool);
            if (stream != null) {
                diedToOverCrowding += stream.transportCreatures(weakestCreatures);
            } else {
                for (Creature creature : weakestCreatures) {
                    creature.getHealth().setAlive(false);
                }
                pool.addCreatures(weakestCreatures);
                diedToOverCrowding += weakestCreatures.size();
            }
        }

        return diedToOverCrowding;
    }

    /**
     * Randomly selects a valid transport stream based on the given pool.
     * <p>
     * A valid stream will have its source pool be identical to the given pool.
     *
     * @param pool
     *         the source pool
     *
     * @return a randomly selected valid stream
     */
    public Stream getRandomStream(Pool pool) {

        List<Stream> possibleStreams = new ArrayList<>();

        for (Stream stream : streams) {
            if (stream.getSource() == pool) {
                possibleStreams.add(stream);
            }
        }

        if (possibleStreams.size() > 1) {
            return possibleStreams.get(generator.nextInt(possibleStreams.size() - 1));
        } else if (possibleStreams.size() == 1) {
            return possibleStreams.get(0);
        }
        return null;
    }

    /**
     * Creates and returns a copy of this ecosystem.
     *
     * @return a cloned copy of this ecosystem
     */
    public Ecosystem copy() {

        Ecosystem ecosystemCopy = new Ecosystem();

        for (Pool pool : pools) {
            ecosystemCopy.addPool(pool.copy());
        }

        for (Stream stream : streams) {
            ecosystemCopy.addStream(stream.copy());
        }

        return ecosystemCopy;
    }
}
