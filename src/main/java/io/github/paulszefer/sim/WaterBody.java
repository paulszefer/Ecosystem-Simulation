package io.github.paulszefer.sim;

/**
 * A body of water in which animals might live.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class WaterBody {

    /** The default water body name. */
    public static final String DEFAULT_WATER_BODY_NAME = "Unnamed";

    /** The default water temperature in Celsius. */
    public static final double DEFAULT_WATER_TEMP_CELSIUS = 40.0;

    /** The minimum water temperature in degrees Celsius. */
    public static final double MINIMUM_WATER_TEMP_CELSIUS = 0.0;

    /** The maximum water temperature in degrees Celsius. */
    public static final double MAXIMUM_WATER_TEMP_CELSIUS = 100.0;

    /** The default water pH. */
    public static final double NEUTRAL_PH = 7.0;

    /** The minimum water pH. */
    public static final double MINIMUM_PH = 0.0;

    /** The maximum water pH. */
    public static final double MAXIMUM_PH = 14.0;

    /** The name of the pool. */
    private String name;

    /** The temperature in degrees Celsius. */
    private double temperature;

    /** The pH. */
    private double pH;

    /**
     * A body of water with a name, temperature and pH.
     * @param name a name
     * @param temperature a temperature in degrees Celsius
     * @param pH a pH value
     */
    public WaterBody(String name, double temperature, double pH) {

        setName(name);
        setTemperature(temperature);
        setpH(pH);
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {

        return name;
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
     * Returns the pH.
     *
     * @return the pH
     */
    public double getpH() {

        return pH;
    }

    /**
     * Sets the name.
     *
     * @param name
     *         the name to set
     */
    public void setName(String name) {

        String newName = DEFAULT_WATER_BODY_NAME;

        if (name != null && !name.replaceAll(" ", "").equals("")) {
            newName = name.replaceAll(" ", "").substring(0, 1).toUpperCase() + name
                    .replaceAll(" ", "").substring(1).toLowerCase();
        }

        this.name = newName;
    }

    /**
     * Sets the temperature in degrees Celsius.
     *
     * @param temperature
     *         the temperature in degrees Celsius
     */
    public void setTemperature(double temperature) {

        double newTemperature = DEFAULT_WATER_TEMP_CELSIUS;

        if (temperature >= MINIMUM_WATER_TEMP_CELSIUS
                && temperature <= MAXIMUM_WATER_TEMP_CELSIUS) {
            newTemperature = temperature;
        }

        this.temperature = newTemperature;
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

}
