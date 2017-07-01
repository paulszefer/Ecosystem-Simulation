package io.github.paulszefer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

/**
 * Defines a Guppy that lives in a body of water.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Guppy implements Creature {

    /** The maximum number of weeks for a guppy to live. */
    public static final int MAXIMUM_AGE = 50;

    /** The minimum volume of water required for a guppy. */
    public static final double MINIMUM_WATER_VOLUME_ML = 250.0;

    /** The default genus of the guppy. */
    public static final String GENUS = "Poecilia";

    /** The default species of the guppy. */
    public static final String SPECIES = "reticulata";

    /** Random number generator. */
    private static final Random generator = new Random();

    /** The number of guppies that have been born. */
    private static int numberOfGuppiesBorn;

    /** The identification profile of the guppy. */
    private Identification identification;

    /** The health profile of the guppy. */
    private final Health health;

    /** Whether the guppy is female. */
    private boolean female;

    /** Creates a Guppy with the default values. */
    public Guppy() {

        identification = new Identification(GENUS, SPECIES, ++numberOfGuppiesBorn, 0);
        health = new Health(MAXIMUM_AGE);
        female = true;
    }

    /**
     * Creates a Guppy with the given parameters.
     *
     * @param age
     *         The age of the guppy in weeks.
     * @param healthCoefficient
     *         The coefficient representing the health of the guppy.
     * @param female
     *         True if the guppy is female; false otherwise.
     * @param generation
     *         The generation number of the guppy.
     */
    public Guppy(int age, double healthCoefficient, boolean female, int generation) {

        identification = new Identification(GENUS, SPECIES, ++numberOfGuppiesBorn, generation);
        health = new Health(MAXIMUM_AGE, true, age, healthCoefficient);
        this.female = female;
    }

    @Override
    public Identification getIdentification() {

        return identification;
    }

    @Override
    public Health getHealth() {

        return health;
    }

    @Override
    public boolean isFemale() {

        return female;
    }

    @Override
    public void setIdentification(Identification identification) {

        this.identification = identification;
    }

    @Override
    public void setFemale(boolean isFemale) {

        this.female = isFemale;
    }

    @Override
    public double getVolumeNeeded() {

        final int youngFishAge = 10;
        final int matureFishAge = 30;
        double volumeOfWaterML = 0.0;
        if (health.isAlive()) {
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
     * {@inheritDoc}
     * <p>
     * If the parent is female and at least 10 weeks old, then there is a 25% chance that they will
     * have offspring. If they do, they will have 0-100 offspring.
     */
    @Override
    public List<Creature> spawn() {

        final int minAgeInWeeksToSpawn = 10;
        final double spawnChance = 0.25;
        final int maxOffspring = 100;

        List<Creature> babyGuppies = new ArrayList<>();

        if (female && health.getAge() >= minAgeInWeeksToSpawn) {

            int numberOfOffspring = 0;
            if (generator.nextDouble() <= spawnChance) {
                numberOfOffspring = generator.nextInt(maxOffspring + 1);
            }

            if (numberOfOffspring > 0) {
                for (int i = 0; i < numberOfOffspring; i++) {
                    babyGuppies.add(new Guppy(0, (1.0 + health.getCoefficient()) / 2.0,
                                              generator.nextBoolean(),
                                              identification.getGeneration() + 1));
                }
            }
        } else {
            babyGuppies = null;
        }
        return babyGuppies;
    }

    @Override
    public Guppy copy() {

        Guppy guppyCopy = new Guppy(health.getAge(), health.getCoefficient(), isFemale(),
                                    identification.getGeneration());
        guppyCopy.getHealth().setAlive(health.isAlive());
        guppyCopy.setIdentification(identification.copy());
        return guppyCopy;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Comparison is done according to health coefficient.
     * <p>
     * Evaluates to:
     * <ul>
     * <li>1 if this Guppy is healthier</li>
     * <li>-1 if the other guppy is healthier</li>
     * <li>0 if the guppies are equally healthy</li>
     * </ul>
     *
     * @return an integer depending on which creature is healthier
     */
    @Override
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

    @Override
    public String toString() {

        return "[" + identification.getClassification() + health + ",female=" + female
                + ",generationNumber=" + identification.getGeneration() + ",identificationNumber="
                + identification.getIdentifier() + "]";
    }
}
