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

    /** The maximum number of weeks for a guppy to live. */
    public static final int MAXIMUM_AGE = 50;

    /** The minimum volume of water required for a guppy. */
    public static final double MINIMUM_WATER_VOLUME_ML = 250.0;

    /** The default genus of the guppy. */
    public static final String GENUS = "Poecilia";

    /** The default species of the guppy. */
    public static final String SPECIES = "reticulata";

    /** Random number generator. */
    private static Random generator = new Random();

    /** The number of guppies that have been born. */
    private static int numberOfGuppiesBorn;

    /** The classification profile of the guppy. */
    private final Classification classification = new Classification(GENUS, SPECIES);

    /** The health profile of the guppy. */
    private Health health;

    /** Whether the guppy is female. */
    private boolean isFemale;

    /** The generation identifier of the guppy. */
    private int generationNumber;

    /** The identification number of the guppy. */
    private int identificationNumber;

    /** Creates a Guppy with the default values. */
    public Guppy() {

        health = new Health(MAXIMUM_AGE);
        isFemale = true;
        generationNumber = 0;
        identificationNumber = ++numberOfGuppiesBorn;
    }

    /**
     * Creates a Guppy with the given parameters.
     *
     * @param age
     *         The age of the guppy in weeks.
     * @param healthCoefficient
     *         The coefficient representing the health of the guppy.
     * @param isFemale
     *         True if the guppy is female; false otherwise.
     * @param generationNumber
     *         The generation of the guppy.
     */
    public Guppy(int age, double healthCoefficient, boolean isFemale, int generationNumber) {

        health = new Health(MAXIMUM_AGE, true, age, healthCoefficient);

        this.isFemale = isFemale;

        if (generationNumber < 0) {
            this.generationNumber = 0;
        } else {
            this.generationNumber = generationNumber;
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
     * Returns the classification profile.
     *
     * @return the classification profile
     */
    public Classification getClassification() {

        return classification;
    }

    /**
     * Returns the health profile.
     *
     * @return the health profile
     */
    public Health getHealth() {

        return health;
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
     * Returns the identification number of the guppy.
     *
     * @return The identification number of the guppy.
     */
    public int getIdentificationNumber() {

        return identificationNumber;
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
     * Gets the volume of water in mL that the guppy needs according to its age.
     *
     * @return Volume of water in mL needed for the guppy or 0.0 if dead.
     */
    public double getVolumeNeeded() {

        final int youngFishAge = 10;
        final int matureFishAge = 30;
        double volumeOfWaterML = 0.0;
        if (health.getIsAlive()) {
            if (health.getAge() < youngFishAge) {
                volumeOfWaterML = MINIMUM_WATER_VOLUME_ML;
            } else if (health.getAge() < matureFishAge) {
                volumeOfWaterML = MINIMUM_WATER_VOLUME_ML * health.getAge() / youngFishAge;
            } else if (health.getAge() < MAXIMUM_AGE) {
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
     * Spawns offspring if the required conditions are met.
     * <p>
     * If the parent is female and at least 10 weeks old, then there is a 25% chance that they will
     * have offspring. If they do, they will have 0-100 offspring.
     *
     * @return an ArrayList of spawned offspring or null if this guppy is unable to reproduce
     */
    public ArrayList<Guppy> spawn() {

        final int minAgeInWeeksToSpawn = 10;
        final double spawnChance = 0.25;
        final int maxOffspring = 100;

        ArrayList<Guppy> babyGuppies = new ArrayList<>();

        if (isFemale && health.getAge() >= minAgeInWeeksToSpawn) {

            int numberOfOffspring = 0;
            if (generator.nextDouble() <= spawnChance) {
                numberOfOffspring = generator.nextInt(maxOffspring + 1);
            }

            if (numberOfOffspring > 0) {
                for (int i = 0; i < numberOfOffspring; i++) {
                    babyGuppies.add(new Guppy(0,
                                              (1.0 + health.getCoefficient()) / 2.0,
                                              generator.nextBoolean(),
                                              generationNumber + 1));
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

        Guppy guppyCopy = new Guppy(health.getAge(),
                                    health.getCoefficient(),
                                    getIsFemale(),
                                    getGenerationNumber());
        guppyCopy.getHealth().setIsAlive(health.getIsAlive());
        guppyCopy.setIdentificationNumber(getIdentificationNumber());
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
        if (health.getCoefficient() > otherGuppy.getHealth().getCoefficient()) {
            return 1;
        } else if (health.getCoefficient() < otherGuppy.getHealth().getCoefficient()) {
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

        return "[" + classification + health + ",isFemale=" + isFemale + ",generationNumber="
                + generationNumber + ",identificationNumber=" + identificationNumber + "]";
    }
}
