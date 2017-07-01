package io.github.paulszefer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Defines an aquatic pool that contains creatures.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Pool extends WaterBody {

    /**
     * The default pool nutrient coefficient.
     */
    public static final double DEFAULT_NUTRIENT_COEFFICIENT = 0.5;

    /**
     * The minimum pool nutrient coefficient.
     */
    public static final double MINIMUM_NUTRIENT_COEFFICIENT = 0.0;

    /**
     * The maximum pool nutrient coefficient.
     */
    public static final double MAXIMUM_NUTRIENT_COEFFICIENT = 1.0;

    /**
     * The number of pools created.
     */
    private static int numberOfPools;

    /**
     * A random number generator.
     */
    private static final Random generator = new Random();

    /**
     * The volume of water in the pool in Litres.
     */
    private double volumeLitres;

    /**
     * The nutrient coefficient of the pool.
     */
    private double nutrientCoefficient;

    /**
     * The identification number of the pool.
     */
    private int identificationNumber;

    /**
     * The set of creatures in the pool.
     */
    private List<Creature> creatures;

    /**
     * Sets up a generic aquatic pool with default values.
     */
    public Pool() {

        super(DEFAULT_WATER_BODY_NAME, DEFAULT_WATER_TEMP_CELSIUS, NEUTRAL_PH);
        volumeLitres = 0.0;
        nutrientCoefficient = DEFAULT_NUTRIENT_COEFFICIENT;
        identificationNumber = ++numberOfPools;
        creatures = new ArrayList<>();
    }

    /**
     * Sets up an aquatic pool with the given values.
     *
     * @param name
     *         the name of the pool
     * @param volumeLitres
     *         the volume of water in the pool in Litres
     * @param temperatureCelsius
     *         the temperature of the pool in degrees Celsius
     * @param pH
     *         the pH of the pool
     * @param nutrientCoefficient
     *         the nutrient coefficient of the pool
     */
    public Pool(String name, double volumeLitres, double temperatureCelsius, double pH,
                double nutrientCoefficient) {

        super(name, temperatureCelsius, pH);
        setVolumeLitres(volumeLitres);
        setNutrientCoefficient(nutrientCoefficient);
        identificationNumber = ++numberOfPools;
        creatures = new ArrayList<>();
    }

    /**
     * Returns the number of pools created.
     *
     * @return the number of pools created
     */
    public static int getNumberCreated() {

        return numberOfPools;
    }

    /**
     * Returns the volume of water in the pool in Litres.
     *
     * @return the volume of water in the pool in Litres
     */
    public double getVolumeLitres() {

        return volumeLitres;
    }

    /**
     * Returns the nutrient coefficient of the pool.
     *
     * @return the nutrient coefficient of the pool
     */
    public double getNutrientCoefficient() {

        return nutrientCoefficient;
    }

    /**
     * Returns the identification number of the pool.
     *
     * @return the identification number of the pool
     */
    public int getIdentificationNumber() {

        return identificationNumber;
    }

    /**
     * Returns the set of creatures in the pool.
     *
     * @return the set of creatures in the pool
     */
    public List<Creature> getCreatures() {

        return creatures;
    }

    /**
     * Sets the volume of water in the pool in Litres.
     *
     * @param volumeLitres
     *         the volume to set
     */
    public void setVolumeLitres(double volumeLitres) {

        this.volumeLitres = volumeLitres > 0.0 ? volumeLitres : 0.0;
    }

    /**
     * Sets the nutrient coefficient of the pool.
     *
     * @param nutrientCoefficient
     *         the nutrient coefficient to set
     */
    public void setNutrientCoefficient(double nutrientCoefficient) {

        double newNutrientCoefficient = DEFAULT_NUTRIENT_COEFFICIENT;

        if (nutrientCoefficient >= MINIMUM_NUTRIENT_COEFFICIENT
                && nutrientCoefficient <= MAXIMUM_NUTRIENT_COEFFICIENT) {
            newNutrientCoefficient = nutrientCoefficient;
        }

        this.nutrientCoefficient = newNutrientCoefficient;
    }

    /**
     * Sets the identification number of the pool.
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
     * Sets the set of creatures in the pool.
     *
     * @param creatures
     *         the set of creatures to set
     */
    public void setCreatures(List<Creature> creatures) {

        if (creatures != null) {
            this.creatures = creatures;
        }
    }

    /**
     * Populates the pool with creatures according to the given parameters.
     *
     * @param numberOfCreatures
     *         the number of creatures to create
     * @param genus
     *         the genus of the creature
     * @param species
     *         the species of the creature
     * @param minAge
     *         the minimum possible age of the creature
     * @param maxAge
     *         the maximum possible age of the creature
     * @param femaleChance
     *         the chance that the creature is female
     * @param minHealthCoefficient
     *         the minimum possible health coefficient of the creature
     * @param maxHealthCoefficient
     *         the maximum possible health coefficient of the creature
     */
    public void populate(int numberOfCreatures, String genus, String species, int minAge,
                         int maxAge, double femaleChance, double minHealthCoefficient,
                         double maxHealthCoefficient) {

        for (int i = 0; i < numberOfCreatures; i++) {
            int age = generator.nextInt(maxAge - minAge + 1) + minAge;
            boolean isFemale = generator.nextDouble() < femaleChance;
            double healthCoefficient =
                    generator.nextDouble() * (maxHealthCoefficient - minHealthCoefficient)
                            + minHealthCoefficient;
            if (genus.equals("Poecilia") && species.equals("reticulata")) {
                addCreature(new Guppy(age, healthCoefficient, isFemale, 0));
            }
        }
    }

    /**
     * Changes the pool's nutrient coefficient by delta.
     * <p>
     * <p>
     * If the resulting coefficient is less than the minimum or greater than the maximum, then the
     * new nutrient coefficient of the pool is set to be the closest bound.
     *
     * @param delta
     *         the change in nutrient coefficient
     */
    public void changeNutrientCoefficient(double delta) {

        double newCoefficient = nutrientCoefficient + delta;

        if (newCoefficient < MINIMUM_NUTRIENT_COEFFICIENT) {
            newCoefficient = MINIMUM_NUTRIENT_COEFFICIENT;
        } else if (newCoefficient > MAXIMUM_NUTRIENT_COEFFICIENT) {
            newCoefficient = MAXIMUM_NUTRIENT_COEFFICIENT;
        }

        nutrientCoefficient = newCoefficient;
    }

    /**
     * Changes the pool's temperature by delta.
     * <p>
     * If the resulting temperature is less than the minimum or greater than the maximum, then the
     * new temperature of the pool is set to be the closest bound.
     *
     * @param delta
     *         the change in temperature
     */
    public void changeTemperature(double delta) {

        double newTemp = getTemperature() + delta;

        if (newTemp < MINIMUM_WATER_TEMP_CELSIUS) {
            newTemp = MINIMUM_WATER_TEMP_CELSIUS;
        } else if (newTemp > MAXIMUM_WATER_TEMP_CELSIUS) {
            newTemp = MAXIMUM_WATER_TEMP_CELSIUS;
        }

        setTemperature(newTemp);
    }

    /**
     * Adds the given creature to the pool.
     *
     * @param creature
     *         the creature to add
     *
     * @return true if successful; false otherwise
     */
    public boolean addCreature(Creature creature) {

        return creature != null && creatures.add(creature);
    }

    /**
     * Adds the given creatures to the pool.
     *
     * @param newCreatures
     *         the creatures to add
     *
     * @return true if successful; false otherwise
     */
    public boolean addCreatures(List<Creature> newCreatures) {

        return newCreatures != null && this.creatures.addAll(newCreatures);
    }

    /**
     * Returns the number of creatures in the pool.
     *
     * @return the number of creatures in the pool
     */
    public int getPopulation() {

        return creatures.size();
    }

    /**
     * Calculates which creatures in the pool have died to malnutrition and returns the number of
     * deaths.
     *
     * @return the number of creatures that died due to malnutrition
     */
    public int applyNutrientCoefficient() {

        int countDied = 0;

        for (Creature creature : creatures) {
            if (generator.nextDouble() > nutrientCoefficient) {
                creature.getHealth().setAlive(false);
                countDied++;
            }
        }

        return countDied;
    }

    /**
     * Removes the dead creatures from the pool.
     *
     * @return the number of creatures removed
     */
    public int removeDeadCreatures() {

        int creaturesRemoved = 0;

        Iterator<Creature> iterator = creatures.iterator();

        while (iterator.hasNext()) {
            if (!iterator.next().getHealth().isAlive()) {
                iterator.remove();
                creaturesRemoved++;
            }
        }

        return creaturesRemoved;
    }

    /**
     * Calculates the volume of water needed for the pool's living population.
     *
     * @return the volume of water needed in Litres
     */
    public double getCreatureVolumeRequirementInLitres() {

        double volumeNeeded = 0.0;
        final double mlPerL = 1000.0;

        for (Creature creature : creatures) {
            volumeNeeded += creature.getVolumeNeeded();
        }

        return volumeNeeded / mlPerL;
    }

    /**
     * Calculates the average age of the pool's living population.
     *
     * @return the average age
     */
    public double getAverageAgeInWeeks() {

        int age = 0;
        int countAlive = 0;

        for (Creature creature : creatures) {
            if (creature.getHealth().isAlive()) {
                age += creature.getHealth().getAge();
                countAlive++;
            }
        }

        return countAlive > 0 ? (double) age / countAlive : 0.0;
    }

    /**
     * Calculates the average health coefficient of the pool's living population.
     *
     * @return the average health coefficient
     */
    public double getAverageHealthCoefficient() {

        double healthCoefficient = 0.0;
        int countAlive = 0;

        for (Creature creature : creatures) {
            if (creature.getHealth().isAlive()) {
                healthCoefficient += creature.getHealth().getCoefficient();
                countAlive++;
            }
        }

        return countAlive > 0 ? healthCoefficient / countAlive : 0.0;
    }

    /**
     * Calculates the proportion of the pool's living population that is female.
     *
     * @return the proportion that is female
     */
    public double getFemaleProportion() {

        int countFemale = 0;
        int countAlive = 0;

        for (Creature creature : creatures) {
            if (creature.getHealth().isAlive()) {
                countFemale += creature.isFemale() ? 1 : 0;
                countAlive++;
            }
        }

        return countAlive > 0 ? (double) countFemale / countAlive : 0.0;
    }

    /**
     * Calculates the median age of the pool's living population.
     *
     * @return the median age
     */
    public double getMedianAge() {

        double medianAge;

        List<Integer> livingCreaturesSorted = sortLivingCreatureAges();

        if (livingCreaturesSorted.size() == 0) {
            medianAge = 0;
        } else if (livingCreaturesSorted.size() % 2 == 0) {
            medianAge = (livingCreaturesSorted.get(livingCreaturesSorted.size() / 2 - 1)
                    + livingCreaturesSorted.get(livingCreaturesSorted.size() / 2)) / 2.0;
        } else {
            medianAge = livingCreaturesSorted.get(livingCreaturesSorted.size() / 2 - 1);
        }

        return medianAge;

    }

    /**
     * Kills the weakest creatures in the pool until the pool has enough water to support the living
     * population.
     *
     * @return the number of creatures that died
     */
    public List<Creature> adjustForCrowding() {

        Collections.sort(creatures);

        List<Creature> weakestCreatures = new ArrayList<>();
        int index = 0;

        while (volumeLitres < getCreatureVolumeRequirementInLitres()) {
            weakestCreatures.add(creatures.remove(index));
        }

        return weakestCreatures;
    }

    /**
     * Increments the age of each creature in the pool by one week and calculates the number that
     * die as a result.
     *
     * @return the number of creatures that died
     */
    public int incrementAges() {

        int countDied = 0;

        for (Creature creature : creatures) {
            creature.getHealth().incrementAge();
            if (!creature.getHealth().isAlive()) {
                countDied++;
            }
        }
        return countDied;
    }

    /**
     * Attempts to spawn baby creatures for each creature in the pool.
     * <p>
     * The new baby creatures are then added to the pool and the number that spawned is returned.
     *
     * @return the total number of spawned creatures
     */
    public int spawn() {

        List<Creature> newCreatures = new ArrayList<>();

        for (Creature creature : creatures) {
            List<Creature> spawned = creature.spawn();
            if (spawned != null) {
                newCreatures.addAll(spawned);
            }
        }
        creatures.addAll(newCreatures);

        return newCreatures.size();
    }

    /**
     * Produces a list of the pool's living population's ages in ascending order.
     *
     * @return a list of the pool's living population's ages in ascending order
     */
    public List<Integer> sortLivingCreatureAges() {

        List<Integer> sorted = new ArrayList<>();

        for (Creature creature : creatures) {
            if (creature.getHealth().isAlive()) {
                sorted.add(creature.getHealth().getAge());
            }
        }

        int index;
        int min;

        for (int i = 0; i < sorted.size(); i++) {

            index = i;
            min = sorted.get(i);

            for (int j = i + 1; j < sorted.size(); j++) {
                if (min >= sorted.get(j)) {
                    index = j;
                    min = sorted.get(j);
                }
            }
            sorted.add(i, sorted.remove(index));
        }
        return sorted;
    }

    /**
     * Creates and returns a copy of this Pool.
     *
     * @return a cloned copy of this pool
     */
    public Pool copy() {

        Pool poolCopy = new Pool(getName(), getVolumeLitres(), getTemperature(), getpH(),
                                 getNutrientCoefficient());
        poolCopy.setIdentificationNumber(getIdentificationNumber());
        for (Creature creature : creatures) {
            poolCopy.addCreature(creature.copy());
        }
        return poolCopy;
    }

    /**
     * Compares this pool to the given object for equality.
     *
     * @param o
     *         the object to compare to
     *
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pool pool = (Pool) o;

        if (Double.compare(pool.volumeLitres, volumeLitres) != 0) {
            return false;
        }
        if (Double.compare(pool.getTemperature(), getTemperature()) != 0) {
            return false;
        }
        if (Double.compare(pool.getpH(), getpH()) != 0) {
            return false;
        }
        if (Double.compare(pool.nutrientCoefficient, nutrientCoefficient) != 0) {
            return false;
        }
        if (identificationNumber != pool.identificationNumber) {
            return false;
        }
        if (!getName().equals(pool.getName())) {
            return false;
        }
        return creatures.equals(pool.creatures);
    }

    /**
     * Generates a hashcode to identify this pool object.
     * <p>
     * Two identical pool objects should return the same hashcode.
     *
     * @return an integer representing the hashcode of this object
     */
    @Override
    public int hashCode() {

        final int hashValue1 = 31;
        final int hashValue2 = 32;
        int result;
        long temp;
        result = getName().hashCode();
        temp = Double.doubleToLongBits(volumeLitres);
        result = hashValue1 * result + (int) (temp ^ (temp >>> hashValue2));
        temp = Double.doubleToLongBits(getTemperature());
        result = hashValue1 * result + (int) (temp ^ (temp >>> hashValue2));
        temp = Double.doubleToLongBits(getpH());
        result = hashValue1 * result + (int) (temp ^ (temp >>> hashValue2));
        temp = Double.doubleToLongBits(nutrientCoefficient);
        result = hashValue1 * result + (int) (temp ^ (temp >>> hashValue2));
        result = hashValue1 * result + identificationNumber;
        result = hashValue1 * result + creatures.hashCode();
        return result;
    }

    /**
     * Prints the pool's details to the console.
     */
    public void printDetails() {

        System.out.println(this);
    }

    /**
     * The String representation of the pool.
     *
     * @return the string representation of the pool
     */
    public String toString() {

        return "[name=" + getName() + ",volumeLitres=" + volumeLitres + ",temperatureCelsius="
                + getTemperature() + ",pH=" + getpH() + ",nutrientCoefficient="
                + nutrientCoefficient + ",identificationNumber=" + identificationNumber
                + ",creatures=" + creatures + "]";
    }
}
