package io.github.paulszefer.sim;

/**
 * Defines a health profile that stores information about the health of an organism.
 * <p>
 * The health profile consists of whether the organism is alive, the organism's age, and the
 * organism's health coefficient.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Health {

    /** A coefficient to represent the default level of health. */
    public static final double DEFAULT_COEFFICIENT = 0.5;

    /** A coefficient to represent the minimum level of health. */
    public static final double MINIMUM_COEFFICIENT = 0.0;

    /** A coefficient to represent the maximum level of health. */
    public static final double MAXIMUM_COEFFICIENT = 1.0;

    /** The maximum age of the organism in weeks before it dies. */
    private final int maxAge;

    /** Whether the organism is alive. */
    private boolean alive;

    /** The age of the organism in weeks. */
    private int age;

    /** The coefficient representing the health of the organism. */
    private double coefficient;

    /**
     * Creates a default health profile.
     *
     * @param maxAge
     *         the maximum age of this organism in weeks
     */
    public Health(int maxAge) {

        this.maxAge = maxAge;
        setAlive(true);
        setAge(0);
        setCoefficient(DEFAULT_COEFFICIENT);
    }

    /**
     * Creates the health profile.
     *
     * @param maxAge
     *         the maximum age of this organism in weeks
     * @param alive
     *         whether the organism is alive
     * @param age
     *         the age of the organism in weeks
     * @param coefficient
     *         the coefficient representing the organism's health
     */
    public Health(int maxAge, boolean alive, int age, double coefficient) {

        this.maxAge = maxAge;
        setAlive(alive);
        setAge(age);
        setCoefficient(coefficient);
    }

    /**
     * Returns the max age of the organism in weeks.
     *
     * @return the max age of the organism in weeks
     */
    public int getMaxAge() {

        return maxAge;
    }

    /**
     * Returns the age of the organism in weeks.
     *
     * @return the age of the organism in weeks
     */
    public int getAge() {

        return age;
    }

    /**
     * Returns whether the organism is alive.
     *
     * @return true if the organism is alive; false otherwise
     */
    public boolean isAlive() {

        return alive;
    }

    /**
     * Returns the coefficient representing the health of the organism.
     *
     * @return the coefficient representing the health of the organism
     */
    public double getCoefficient() {

        return coefficient;
    }

    /**
     * Sets the age of the organism in weeks.
     *
     * @param age
     *         the age of the organism in weeks
     */
    public void setAge(int age) {

        if (age >= 0 && age < maxAge) {
            this.age = age;
        }
    }

    /**
     * Sets whether the organism is alive.
     *
     * @param isAlive
     *         true if the organism is alive; false otherwise
     */
    public void setAlive(boolean isAlive) {

        this.alive = isAlive;
    }

    /**
     * Sets the coefficient representing the health of the organism.
     *
     * @param coefficient
     *         the coefficient representing the health of the organism
     */
    public void setCoefficient(double coefficient) {

        if (coefficient >= MINIMUM_COEFFICIENT && coefficient <= MAXIMUM_COEFFICIENT) {
            this.coefficient = coefficient;
        }
    }

    /**
     * Increments the age of the organism by one week. If the organism is as old as the maximum
     * number of weeks, then the organism has died.
     */
    public void incrementAge() {

        age++;
        if (age >= maxAge) {
            setAlive(false);
        }
    }

    /**
     * Changes the coefficient representing the health of the organism by delta. If the resulting
     * coefficient is equal to the minimum health coefficient or less, then the organism is dead. If
     * the resulting coefficient is equal to the maximum coefficient or more, then the organism is
     * in perfect health.
     *
     * @param delta
     *         the change in the coefficient representing the health of the organism
     */
    public void changeCoefficient(double delta) {

        setCoefficient(coefficient + delta);
        if (coefficient <= Health.MINIMUM_COEFFICIENT) {
            setAlive(false);
            setCoefficient(0);
        } else if (coefficient >= Health.MAXIMUM_COEFFICIENT) {
            setCoefficient(Health.MAXIMUM_COEFFICIENT);
        }
    }

    @Override
    public String toString() {

        return "alive=" + alive + ",age=" + age + ",coefficient=" + coefficient;
    }
}