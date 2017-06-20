package io.github.paulszefer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Defines the type Guppy.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Guppy {

    /**
     * The number of weeks for a fish to be considered young.
     */
    public static final int YOUNG_FISH_WEEKS = 10;

    /**
     * The number of weeks for a fish to be considered mature.
     */
    public static final int MATURE_FISH_WEEKS = 30;

    /**
     * The maximum number of weeks for a fish to live.
     */
    public static final int MAXIMUM_AGE_IN_WEEKS = 50;

    /**
     * The minimum volume of water in the tank.
     */
    public static final double MINIMUM_WATER_VOLUME_ML = 250.0;

    /**
     * The default genus of the guppy.
     */
    public static final String DEFAULT_GENUS = "Poecilia";

    /**
     * The default species of the guppy.
     */
    public static final String DEFAULT_SPECIES = "reticulata";

    /**
     * A coefficient to represent the default level of health.
     */
    public static final double DEFAULT_HEALTH_COEFFICIENT = 0.5;

    /**
     * A coefficient to represent the minimum level of health.
     */
    public static final double MINIMUM_HEALTH_COEFFICIENT = 0.0;

    /**
     * A coefficient to represent the maximum level of health.
     */
    public static final double MAXIMUM_HEALTH_COEFFICIENT = 1.0;

    /**
     * The number of guppies that have been born.
     */
    private static int numberOfGuppiesBorn;

    /**
     * Random number generator.
     */
    private static Random generator = new Random();

    /**
     * Genus of the guppy.
     */
    private String genus;

    /**
     * Species of the guppy.
     */
    private String species;

    /**
     * The age of the guppy in weeks.
     */
    private int ageInWeeks;

    /**
     * Whether the guppy is female.
     */
    private boolean isFemale;

    /**
     * The generation identifier of the guppy.
     */
    private int generationNumber;

    /**
     * Whether the guppy is alive.
     */
    private boolean isAlive;

    /**
     * The coefficient representing the health of the guppy.
     */
    private double healthCoefficient;

    /**
     * The identification number of the guppy.
     */
    private int identificationNumber;

    /**
     * Creates a Guppy with the default values.
     */
    public Guppy() {

        genus = DEFAULT_GENUS;
        species = DEFAULT_SPECIES;
        ageInWeeks = 0;
        isFemale = true;
        generationNumber = 0;
        isAlive = true;
        healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;

        numberOfGuppiesBorn++;
        identificationNumber = numberOfGuppiesBorn;

    }

    /**
     * Creates a Guppy with the given parameters.
     *
     * @param newGenus             The genus of the guppy.
     * @param newSpecies           The species of the guppy.
     * @param newAgeInWeeks        The age of the guppy in weeks.
     * @param newIsFemale          True if the guppy is female; false otherwise.
     * @param newGenerationNumber  The generation of the guppy.
     * @param newHealthCoefficient The coefficient representing the health of the guppy.
     */
    public Guppy(String newGenus, String newSpecies, int newAgeInWeeks, boolean newIsFemale,
                 int newGenerationNumber, double newHealthCoefficient) {

        if (newGenus == null || newGenus.equals("")) {

            genus = DEFAULT_GENUS;

        } else {

            genus = newGenus.substring(0, 1).toUpperCase() + newGenus.substring(1).toLowerCase();

        }

        if (newSpecies == null || newSpecies.equals("")) {

            species = DEFAULT_SPECIES;

        } else {

            species = newSpecies.toLowerCase();

        }

        if (newAgeInWeeks < 0 || newAgeInWeeks >= MAXIMUM_AGE_IN_WEEKS) {

            ageInWeeks = 0;

        } else {

            ageInWeeks = newAgeInWeeks;

        }

        isFemale = newIsFemale;

        if (newGenerationNumber < 0) {

            generationNumber = 0;

        } else {

            generationNumber = newGenerationNumber;

        }

        isAlive = true;

        if (newHealthCoefficient < MINIMUM_HEALTH_COEFFICIENT
                || newHealthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {

            healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;

        } else {

            healthCoefficient = newHealthCoefficient;

        }

        numberOfGuppiesBorn++;
        identificationNumber = numberOfGuppiesBorn;

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
     * Returns the genus of the guppy.
     *
     * @return The genus of the guppy.
     */
    public String getGenus() {

        return genus;

    }

    /**
     * Sets the genus of the guppy.
     *
     * @param newGenus The genus of the guppy.
     */
    public void setGenus(String newGenus) {

        if (newGenus != null && !newGenus.equals("")) {

            genus = newGenus.substring(0, 1).toUpperCase() + newGenus.substring(1).toLowerCase();

        }

    }

    /**
     * Returns the species of the guppy.
     *
     * @return The species of the guppy.
     */
    public String getSpecies() {

        return species;

    }

    /**
     * Sets the species of the guppy.
     *
     * @param newSpecies The species of the guppy.
     */
    public void setSpecies(String newSpecies) {

        if (newSpecies != null && !newSpecies.equals("")) {

            species = newSpecies.toLowerCase();

        }

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
     * Sets the age of the guppy in weeks.
     *
     * @param newAgeInWeeks The age of the guppy in weeks.
     */
    public void setAgeInWeeks(int newAgeInWeeks) {

        if (newAgeInWeeks > 0 && newAgeInWeeks < MAXIMUM_AGE_IN_WEEKS) {

            ageInWeeks = newAgeInWeeks;

        } else {

            ageInWeeks = 0;

        }

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
     * Sets whether the guppy is female.
     *
     * @param newIsFemale True if the guppy is female; false otherwise.
     */
    public void setIsFemale(boolean newIsFemale) {

        isFemale = newIsFemale;

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
     * Sets the generation number of the guppy.
     *
     * @param newGenerationNumber The generation number of the guppy.
     */
    public void setGenerationNumber(int newGenerationNumber) {

        if (newGenerationNumber >= 0) {

            generationNumber = newGenerationNumber;

        }

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
     * Sets whether the guppy is alive.
     *
     * @param newIsAlive True if the guppy is alive; false otherwise.
     */
    public void setIsAlive(boolean newIsAlive) {

        isAlive = newIsAlive;

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
     * Sets the coefficient representing the health of the guppy.
     *
     * @param newHealthCoefficient The coefficient representing the health of the guppy.
     */
    public void setHealthCoefficient(double newHealthCoefficient) {

        if (newHealthCoefficient >= MINIMUM_HEALTH_COEFFICIENT
                && newHealthCoefficient <= MAXIMUM_HEALTH_COEFFICIENT) {

            healthCoefficient = newHealthCoefficient;

        } else {

            healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;

        }

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
     * Changes the coefficient representing the health of the guppy by delta. If the resulting
     * coefficient is equal to the minimum health coefficient or less, then the guppy is dead. If
     * the resulting coefficient is equal to the maximum coefficient or more, then the guppy is in
     * perfect health.
     *
     * @param delta The change in the coefficient representing the health of the guppy.
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

                    babyGuppies.add(new Guppy(genus, species, 0, generator.nextBoolean(),
                            generationNumber + 1, (1.0 + healthCoefficient) / 2.0));

                }

            }

        } else {

            babyGuppies = null;

        }

        return babyGuppies;

    }

    /**
     * Returns a string representation of the guppy containing all of its attributes.
     *
     * @return A string containing all of the attributes of the guppy.
     */
    public String toString() {

        return "[genus=" + genus + ",species=" + species + ",ageInWeeks=" + ageInWeeks
                + ",isFemale=" + isFemale + ",generationNumber=" + generationNumber + ",isAlive="
                + isAlive + ",healthCoefficient=" + healthCoefficient + ",identificationNumber="
                + identificationNumber + "]";

    }

}
