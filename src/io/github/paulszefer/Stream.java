package io.github.paulszefer;

import java.util.ArrayList;
import java.util.Random;

/**
 * A stream provides a connection between pools.
 * <p>
 * Guppies that leave the original pool travel through the stream to the destination pool.
 * <p>
 * A stream is not a container; guppies flow quickly through the stream to their destination.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Stream {

    /** Random number generator. */
    private static Random generator = new Random();

    /** The source pool. */
    private Pool source;

    /** The destination pool. */
    private Pool destination;

    /** The pH. */
    private double pH;

    /** The temperature in degrees Celsius. */
    private double temperature;

    /**
     * A stream that flows from its source pool to its destination pool.
     * <p>
     * Streams facilitate the transfer of guppies between pools.
     * <p>
     * Streams begin with the same pH and temperature as their source pool.
     *
     * @param source
     *         the source pool
     * @param destination
     *         the destination pool
     */
    public Stream(Pool source, Pool destination) {

        setSource(source);
        setDestination(destination);
        setpH(source.getpH());
        setTemperature(source.getTemperatureCelsius());

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
     * Returns the pH.
     *
     * @return the pH
     */
    public double getpH() {

        return pH;
    }

    /**
     * Returns the temperature in degrees Celsius.
     *
     * @return the temperature in degrees Celsius
     */
    public double getTemperature() {

        return temperature;
    }

    /**
     * Sets the source pool.
     *
     * @param source
     *         the source pool
     */
    public void setSource(Pool source) {

        this.source = source;
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
     * Sets the pH.
     *
     * @param pH
     *         the pH
     */
    public void setpH(double pH) {

        this.pH = pH;
    }

    /**
     * Sets the temperature in degrees Celsius.
     *
     * @param temperature
     *         the temperature in degrees Celsius
     */
    public void setTemperature(double temperature) {

        this.temperature = temperature;
    }

    /**
     * Transports the guppies through this stream to the destination pool.
     * <p>
     * Guppies die in transport if a randomly generated double is greater than their health
     * coefficient.
     *
     * @param guppies
     *         the guppies to transport
     *
     * @return the number of guppies that died in transport
     */
    public int transportGuppies(ArrayList<Guppy> guppies) {

        int countDied = 0;

        for (Guppy guppy : guppies) {
            guppy.setIsAlive(generator.nextDouble() < guppy.getHealthCoefficient());
            countDied += guppy.getIsAlive() ? 0 : 1;
        }
        destination.addGuppies(guppies);

        return countDied;
    }
}
