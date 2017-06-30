package io.github.paulszefer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * Defines a Guppy that lives in a body of water.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Guppy implements Comparable {

    /** The number of weeks for a fish to be considered young. */
    public static final int YOUNG_FISH_WEEKS = 10;

    /** The number of weeks for a fish to be considered mature. */
    public static final int MATURE_FISH_WEEKS = 30;

    /** The maximum number of weeks for a fish to live. */
    public static final int MAXIMUM_AGE_IN_WEEKS = 50;

    /** The minimum volume of water in the tank. */
    public static final double MINIMUM_WATER_VOLUME_ML = 250.0;

    /** The default genus of the guppy. */
    public static final String GENUS = "Poecilia";

    /** The default species of the guppy. */
    public static final String SPECIES = "reticulata";

    /** A coefficient to represent the default level of health. */
    public static final double DEFAULT_HEALTH_COEFFICIENT = 0.5;

    /** A coefficient to represent the minimum level of health. */
    public static final double MINIMUM_HEALTH_COEFFICIENT = 0.0;

    /** A coefficient to represent the maximum level of health. */
    public static final double MAXIMUM_HEALTH_COEFFICIENT = 1.0;

    /** Random number generator. */
    private static Random generator = new Random();

    /** The number of guppies that have been born. */
    private static int numberOfGuppiesBorn;

    /** The classification of the guppy. */
    private final Classification classification = new Classification(GENUS, SPECIES);

    /** The age of the guppy in weeks. */
    private int ageInWeeks;

    /** Whether the guppy is female. */
    private boolean isFemale;

    /** The generation identifier of the guppy. */
    private int generationNumber;

    /** Whether the guppy is alive. */
    private boolean isAlive;

    /** The coefficient representing the health of the guppy. */
    private double healthCoefficient;

    /** The identification number of the guppy. */
    private int identificationNumber;

    /** Creates a Guppy with the default values. */
    public Guppy() {

        ageInWeeks = 0;
        isFemale = true;
        generationNumber = 0;
        isAlive = true;
        healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        identificationNumber = ++numberOfGuppiesBorn;
    }

    /**
     * Creates a Guppy with the given parameters.
     *
     * @param ageInWeeks
     *         The age of the guppy in weeks.
     * @param isFemale
     *         True if the guppy is female; false otherwise.
     * @param generationNumber
     *         The generation of the guppy.
     * @param healthCoefficient
     *         The coefficient representing the health of the guppy.
     */
    public Guppy(int ageInWeeks, boolean isFemale,
                 int generationNumber, double healthCoefficient) {

        if (ageInWeeks < 0 || ageInWeeks >= MAXIMUM_AGE_IN_WEEKS) {
            this.ageInWeeks = 0;
        } else {
            this.ageInWeeks = ageInWeeks;
        }

        this.isFemale = isFemale;

        if (generationNumber < 0) {
            this.generationNumber = 0;
        } else {
            this.generationNumber = generationNumber;
        }

        isAlive = true;

        if (healthCoefficient < MINIMUM_HEALTH_COEFFICIENT
                || healthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            this.healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        } else {
            this.healthCoefficient = healthCoefficient;
        }

        identificationNumber = ++numberOfGuppiesBorn;
    }

    /**
     * Returns the number of guppies born.
     *
     * @return The number of guppies born.
     */
    public static int getNumberOfGuppiesBorn() {

        return numberOfGuppiesBorn;
    }

    /**
     * Returns the classification.
     *
     * @return the classification
     */
    public Classification getClassification() {

        return classification;
    }

    /**
     * Returns the age of the guppy in weeks.
     *
     * @return The age of the guppy in weeks.
     */
    public int getAgeInWeeks() {

        return ageInWeeks;
    }

    /**
     * Returns whether the guppy is female.
     *
     * @return True if the guppy is female; false otherwise.
     */
    public boolean getIsFemale() {

        return isFemale;
    }

    /**
     * Returns the generation number of the guppy.
     *
     * @return The generation number of the guppy.
     */
    public int getGenerationNumber() {

        return generationNumber;
    }

    /**
     * Returns whether the guppy is alive.
     *
     * @return True if the guppy is alive; false otherwise.
     */
    public boolean getIsAlive() {

        return isAlive;
    }

    /**
     * Returns the coefficient representing the health of the guppy.
     *
     * @return The coefficient representing the health of the guppy.
     */
    public double getHealthCoefficient() {

        return healthCoefficient;
    }

    /**
     * Returns the identification number of the guppy.
     *
     * @return The identification number of the guppy.
     */
    public int getIdentificationNumber() {

        return identificationNumber;
    }

    /**
     * Sets the age of the guppy in weeks.
     *
     * @param ageInWeeks
     *         The age of the guppy in weeks.
     */
    public void setAgeInWeeks(int ageInWeeks) {

        this.ageInWeeks = 0;
        if (ageInWeeks > 0 && ageInWeeks < MAXIMUM_AGE_IN_WEEKS) {
            this.ageInWeeks = ageInWeeks;
        }
    }

    /**
     * Sets whether the guppy is female.
     *
     * @param isFemale
     *         True if the guppy is female; false otherwise.
     */
    public void setIsFemale(boolean isFemale) {

        this.isFemale = isFemale;
    }

    /**
     * Sets the generation number of the guppy.
     *
     * @param generationNumber
     *         The generation number of the guppy.
     */
    public void setGenerationNumber(int generationNumber) {

        if (generationNumber >= 0) {
            this.generationNumber = generationNumber;
        }
    }

    /**
     * Sets whether the guppy is alive.
     *
     * @param isAlive
     *         True if the guppy is alive; false otherwise.
     */
    public void setIsAlive(boolean isAlive) {

        this.isAlive = isAlive;
    }

    /**
     * Sets the coefficient representing the health of the guppy.
     *
     * @param healthCoefficient
     *         The coefficient representing the health of the guppy.
     */
    public void setHealthCoefficient(double healthCoefficient) {

        this.healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        if (healthCoefficient >= MINIMUM_HEALTH_COEFFICIENT
                && healthCoefficient <= MAXIMUM_HEALTH_COEFFICIENT) {
            this.healthCoefficient = healthCoefficient;
        }
    }

    /**
     * Increments the age of the guppy by one week. If the guppy is as old as the maximum number of
     * weeks, then the guppy has died.
     */
    public void incrementAge() {

        ageInWeeks++;
        if (ageInWeeks >= MAXIMUM_AGE_IN_WEEKS) {
            isAlive = false;
        }
    }

    /**
     * Gets the volume of water in mL that the guppy needs according to its age.
     *
     * @return Volume of water in mL needed for the guppy or 0.0 if dead.
     */
    public double getVolumeNeeded() {

        double volumeOfWaterML = 0.0;
        if (isAlive) {
            if (ageInWeeks < YOUNG_FISH_WEEKS) {
                volumeOfWaterML = MINIMUM_WATER_VOLUME_ML;
            } else if (ageInWeeks < MATURE_FISH_WEEKS) {
                volumeOfWaterML = MINIMUM_WATER_VOLUME_ML * ageInWeeks / YOUNG_FISH_WEEKS;
            } else if (ageInWeeks < MAXIMUM_AGE_IN_WEEKS) {
                final double volumeMultiple = 1.5;
                volumeOfWaterML = MINIMUM_WATER_VOLUME_ML * volumeMultiple;
            }
        }
        return volumeOfWaterML;
    }

    /**
     * Sets the identification number of the guppy.
     *
     * @param identificationNumber
     *         the identification number to set
     */
    public void setIdentificationNumber(int identificationNumber) {

        this.identificationNumber = 0;
        if (identificationNumber > 0) {
            this.identificationNumber = identificationNumber;
        }
    }

    /**
     * Changes the coefficient representing the health of the guppy by delta. If the resulting
     * coefficient is equal to the minimum health coefficient or less, then the guppy is dead. If
     * the resulting coefficient is equal to the maximum coefficient or more, then the guppy is in
     * perfect health.
     *
     * @param delta
     *         The change in the coefficient representing the health of the guppy.
     */
    public void changeHealthCoefficient(double delta) {

        healthCoefficient += delta;
        if (healthCoefficient <= MINIMUM_HEALTH_COEFFICIENT) {
            healthCoefficient = 0;
            isAlive = false;
        } else if (healthCoefficient >= MAXIMUM_HEALTH_COEFFICIENT) {
            healthCoefficient = MAXIMUM_HEALTH_COEFFICIENT;
        }
    }

    /**
     * <p>
     * Spawns offspring if the required conditions are met.
     * </p>
     * <p>
     * <p>
     * If the parent is female and at least 10 weeks old, then there is a 25% chance that they will
     * have offspring. If they do, they will have 0-100 offspring.
     * </p>
     *
     * @return an ArrayList of spawned offspring or null if this guppy is unable to reproduce
     */
    public ArrayList<Guppy> spawn() {

        final int minAgeInWeeksToSpawn = 10;
        final double spawnChance = 0.25;
        final int maxOffspring = 100;

        ArrayList<Guppy> babyGuppies = new ArrayList<>();

        if (isFemale && ageInWeeks >= minAgeInWeeksToSpawn) {

            int numberOfOffspring = 0;
            if (generator.nextDouble() <= spawnChance) {
                numberOfOffspring = generator.nextInt(maxOffspring + 1);
            }

            if (numberOfOffspring > 0) {
                for (int i = 0; i < numberOfOffspring; i++) {
                    babyGuppies.add(new Guppy(0,
                                              generator.nextBoolean(),
                                              generationNumber + 1,
                                              (1.0 + healthCoefficient) / 2.0));
                }
            }
        } else {
            babyGuppies = null;
        }
        return babyGuppies;
    }

    /**
     * Creates and returns a copy of this Guppy.
     *
     * @return a cloned copy of this guppy
     */
    public Guppy copy() {

        Guppy guppyCopy = new Guppy(getAgeInWeeks(),
                                    getIsFemale(),
                                    getGenerationNumber(),
                                    getHealthCoefficient());
        guppyCopy.setIdentificationNumber(getIdentificationNumber());
        guppyCopy.setIsAlive(getIsAlive());
        return guppyCopy;
    }

    /**
     * Compares this Guppy to the given object according to health coefficient.
     * <p>
     * Returns:
     * <ul>
     * <li>1 if this Guppy is healthier</li>
     * <li>-1 if the other guppy is healthier</li>
     * <li>0 if the guppies are equally healthy</li>
     * </ul>
     *
     * @param other
     *         an object to compare this guppy to
     *
     * @return an integer depending on which guppy is healthier
     *
     * @throws NullPointerException
     *         if the other object is null
     * @throws InputMismatchException
     *         if the other object is not of type Guppy
     */
    public int compareTo(Object other) {

        if (this == other) {
            return 0;
        }
        if (other == null) {
            throw new NullPointerException();
        }
        if (other.getClass() != Guppy.class) {
            throw new InputMismatchException();
        }

        Guppy otherGuppy = (Guppy) other;
        if (this.getHealthCoefficient() > otherGuppy.getHealthCoefficient()) {
            return 1;
        } else if (this.getHealthCoefficient() < otherGuppy.getHealthCoefficient()) {
            return -1;
        }
        return 0;
    }

    /**
     * Returns a string representation of the guppy containing all of its attributes.
     *
     * @return A string containing all of the attributes of the guppy.
     */
    public String toString() {

        return "[" + classification + ",ageInWeeks=" + ageInWeeks
                + ",isFemale=" + isFemale + ",generationNumber=" + generationNumber + ",isAlive="
                + isAlive + ",healthCoefficient=" + healthCoefficient + ",identificationNumber="
                + identificationNumber + "]";
    }
}
