package io.github.paulszefer;

import java.util.ArrayList;
import java.util.Random;

import static io.github.paulszefer.Pool.*;

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
        setpH(source.getpH());
        setTemperature(source.getTemperatureCelsius());
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

        double newPH = NEUTRAL_PH;

        if (pH >= MINIMUM_PH && pH <= MAXIMUM_PH) {
            newPH = pH;
        }

        this.pH = newPH;
    }

    /**
     * Sets the temperature in degrees Celsius.
     *
     * @param temperature
     *         the temperature in degrees Celsius
     */
    public void setTemperature(double temperature) {

        double newTemperature = DEFAULT_POOL_TEMP_CELSIUS;

        if (temperature >= MINIMUM_POOL_TEMP_CELSIUS
                && temperature <= MAXIMUM_POOL_TEMP_CELSIUS) {
            newTemperature = temperature;
        }

        this.temperature = newTemperature;
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
     * @return the number of guppies that die in transport
     */
    public int transportGuppies(ArrayList<Guppy> guppies) {

        int countDied = 0;

        for (Guppy guppy : guppies) {
            guppy.setIsAlive(generator.nextDouble() < guppy.getHealthCoefficient());
            countDied += !guppy.getIsAlive() ? 1 : 0;
        }
        destination.addGuppies(guppies);

        return countDied;
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

        if (Double.compare(stream.pH, pH) != 0) {
            return false;
        }
        if (Double.compare(stream.temperature, temperature) != 0) {
            return false;
        }
        if (!source.equals(stream.source)) {
            return false;
        }
        return destination.equals(stream.destination);
    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        result = source.hashCode();
        result = 31 * result + destination.hashCode();
        temp = Double.doubleToLongBits(pH);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
