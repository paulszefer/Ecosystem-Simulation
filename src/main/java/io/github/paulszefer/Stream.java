package io.github.paulszefer;

import java.util.List;
import java.util.Random;

/**
 * A stream provides a connection between pools.
 * <p>
 * Creatures that leave the original pool travel through the stream to the destination pool.
 * <p>
 * A stream is not a container; creatures flow quickly through the stream to their destination.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Stream extends WaterBody {

    /** Random number generator. */
    private static final Random GENERATOR = new Random();

    /** The source pool. */
    private Pool source;

    /** The destination pool. */
    private Pool destination;

    /**
     * A stream that flows from its source pool to its destination pool.
     * <p>
     * Streams facilitate the transfer of creatures between pools.
     * <p>
     * Streams begin with the same pH and temperature as their source pool.
     *
     * @param source
     *         the source pool
     * @param destination
     *         the destination pool
     */
    public Stream(Pool source, Pool destination) {

        super(source + "to" + destination, source.getpH(), source.getTemperature());
        setSource(source);
        setDestination(destination);
    }

    /**
     * A stream that flows from its source pool to its destination pool.
     * <p>
     * Streams facilitate the transfer of creatures between pools.
     * <p>
     * Streams begin with the same pH and temperature as their source pool.
     *
     * @param name
     *         the name
     * @param source
     *         the source pool
     * @param destination
     *         the destination pool
     */
    public Stream(String name, Pool source, Pool destination) {

        super(name, source.getpH(), source.getTemperature());
        setSource(source);
        setDestination(destination);
    }

    /**
     * Returns the source pool.
     *
     * @return the source pool
     */
    public Pool getSource() {

        return source;
    }

    /**
     * Returns the destination pool.
     *
     * @return the destination pool
     */
    public Pool getDestination() {

        return destination;
    }

    /**
     * Sets the source pool.
     *
     * @param source
     *         the source pool
     */
    public void setSource(Pool source) {

        this.source = source;
        setpH(source.getpH());
        setTemperature(source.getTemperature());
    }

    /**
     * Sets the destination pool.
     *
     * @param destination
     *         the destination pool
     */
    public void setDestination(Pool destination) {

        this.destination = destination;
    }

    /**
     * Transports the creatures through this stream to the destination pool.
     * <p>
     * Creatures die in transport if a randomly generated double is greater than their health
     * coefficient.
     *
     * @param creatures
     *         the creatures to transport
     *
     * @return the number of creatures that die in transport
     */
    public int transportCreatures(List<Creature> creatures) {

        int countDied = 0;

        for (Creature creature : creatures) {
            creature.getHealth().setAlive(
                    GENERATOR.nextDouble() < creature.getHealth().getCoefficient());
            countDied += !creature.getHealth().isAlive() ? 1 : 0;
        }
        destination.addCreatures(creatures);

        return countDied;
    }

    /**
     * Creates and returns a copy of this stream.
     *
     * @return a cloned copy of this stream
     */
    public Stream copy() {

        Stream streamCopy = new Stream(getName(), source, destination);
        streamCopy.setTemperature(getTemperature());
        streamCopy.setpH(getpH());
        return streamCopy;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Stream stream = (Stream) o;

        if (Double.compare(stream.getpH(), getpH()) != 0) {
            return false;
        }
        if (Double.compare(stream.getTemperature(), getTemperature()) != 0) {
            return false;
        }
        if (!source.equals(stream.source)) {
            return false;
        }
        return destination.equals(stream.destination);
    }

    @Override
    public int hashCode() {

        final int hashValue1 = 31;
        final int hashValue2 = 32;

        int result;
        long temp;
        result = source.hashCode();
        result = hashValue1 * result + destination.hashCode();
        temp = Double.doubleToLongBits(getpH());
        result = hashValue1 * result + (int) (temp ^ (temp >>> hashValue2));
        temp = Double.doubleToLongBits(getTemperature());
        result = hashValue1 * result + (int) (temp ^ (temp >>> hashValue2));
        return result;
    }
}
