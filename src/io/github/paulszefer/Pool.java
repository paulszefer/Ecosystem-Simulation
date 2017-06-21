package io.github.paulszefer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Defines an aquatic pool that contains guppies.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Pool {

    /**
     * The default pool name.
     */
    public static final String DEFAULT_POOL_NAME = "Unnamed";

    /**
     * The default pool temperature in Celsius.
     */
    public static final double DEFAULT_POOL_TEMP_CELSIUS = 40.0;

    /**
     * The minimum pool temperature in degrees Celsius.
     */
    public static final double MINIMUM_POOL_TEMP_CELSIUS = 0.0;

    /**
     * The maximum pool temperature in degrees Celsius.
     */
    public static final double MAXIMUM_POOL_TEMP_CELSIUS = 100.0;

    /**
     * The default pool pH.
     */
    public static final double NEUTRAL_PH = 7.0;

    /**
     * The minimum pool pH.
     */
    public static final double MINIMUM_PH = 0.0;

    /**
     * The maximum pool pH.
     */
    public static final double MAXIMUM_PH = 14.0;

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
    private static Random generator = new Random();

    /**
     * The name of the pool.
     */
    private String name;

    /**
     * The volume of water in the pool in Litres.
     */
    private double volumeLitres;

    /**
     * The temperature of the pool in degrees Celsius.
     */
    private double temperatureCelsius;

    /**
     * The pH of the pool.
     */
    private double pH;

    /**
     * The nutrient coefficient of the pool.
     */
    private double nutrientCoefficient;

    /**
     * The identification number of the pool.
     */
    private int identificationNumber;

    /**
     * The set of guppies in the pool.
     */
    private ArrayList<Guppy> guppiesInPool;

    /**
     * Sets up a generic aquatic pool with default values.
     */
    public Pool() {

        name = DEFAULT_POOL_NAME;
        volumeLitres = 0.0;
        temperatureCelsius = DEFAULT_POOL_TEMP_CELSIUS;
        pH = NEUTRAL_PH;
        nutrientCoefficient = DEFAULT_NUTRIENT_COEFFICIENT;
        identificationNumber = ++numberOfPools;
        guppiesInPool = new ArrayList<>();
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

        setName(name);
        setVolumeLitres(volumeLitres);
        setTemperatureCelsius(temperatureCelsius);
        setpH(pH);
        setNutrientCoefficient(nutrientCoefficient);
        identificationNumber = ++numberOfPools;
        guppiesInPool = new ArrayList<>();
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
     * Returns the name of the pool.
     *
     * @return the name of the pool
     */
    public String getName() {

        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *         the name to set
     */
    public void setName(String name) {

        String newName = DEFAULT_POOL_NAME;

        if (name != null && !name.replaceAll(" ", "").equals("")) {
            newName = name.replaceAll(" ", "").substring(0, 1).toUpperCase() + name
                    .replaceAll(" ", "").substring(1).toLowerCase();
        }

        this.name = newName;
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
     * Sets the volume of water in the pool in Litres.
     *
     * @param volumeLitres
     *         the volume to set
     */
    public void setVolumeLitres(double volumeLitres) {

        this.volumeLitres = volumeLitres > 0.0 ? volumeLitres : 0.0;
    }

    /**
     * Returns the temperature of the pool in degrees Celsius.
     *
     * @return the temperature of the pool in degrees Celsius
     */
    public double getTemperatureCelsius() {

        return temperatureCelsius;
    }

    /**
     * Sets the temperature of the pool in degrees Celsius.
     *
     * @param temperatureCelsius
     *         the temperature to set
     */
    public void setTemperatureCelsius(double temperatureCelsius) {

        double newTemperature = DEFAULT_POOL_TEMP_CELSIUS;

        if (temperatureCelsius >= MINIMUM_POOL_TEMP_CELSIUS
                && temperatureCelsius <= MAXIMUM_POOL_TEMP_CELSIUS) {
            newTemperature = temperatureCelsius;
        }

        this.temperatureCelsius = newTemperature;
    }

    /**
     * Returns the pH of the pool.
     *
     * @return the pH of the pool
     */
    public double getpH() {

        return pH;
    }

    /**
     * Sets the pH of the pool.
     *
     * @param pH
     *         the pH to set
     */
    public void setpH(double pH) {

        double newPH = NEUTRAL_PH;

        if (pH >= MINIMUM_PH && pH <= MAXIMUM_PH) {
            newPH = pH;
        }

        this.pH = newPH;
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
     * Returns the identification number of the pool.
     *
     * @return the identification number of the pool
     */
    public int getIdentificationNumber() {

        return identificationNumber;
    }

    /**
     * Returns the set of guppies in the pool.
     *
     * @return the set of guppies in the pool
     */
    public ArrayList<Guppy> getGuppiesInPool() {

        return guppiesInPool;
    }

    /**
     * Sets the set of guppies in the pool.
     *
     * @param guppiesInPool
     *         the set of guppies to set
     */
    public void setGuppiesInPool(ArrayList<Guppy> guppiesInPool) {

        if (guppiesInPool != null) {
            this.guppiesInPool = guppiesInPool;
        }
    }

    /**
     * Populates the pool with guppies according to the given parameters.
     *
     * @param numberOfGuppies
     *         the number of guppies to create
     * @param genus
     *         the genus of the guppy
     * @param species
     *         the species of the guppy
     * @param minAge
     *         the minimum possible age of the guppy
     * @param maxAge
     *         the maximum possible age of the guppy
     * @param femaleChance
     *         the chance that the guppy is female
     * @param minHealthCoefficient
     *         the minimum possible health coefficient of the guppy
     * @param maxHealthCoefficient
     *         the maximum possible health coefficient of the guppy
     */
    public void populatePool(int numberOfGuppies, String genus, String species, int minAge,
                             int maxAge, double femaleChance, double minHealthCoefficient,
                             double maxHealthCoefficient) {

        for (int i = 0; i < numberOfGuppies; i++) {
            int age = generator.nextInt(maxAge - minAge + 1) + minAge;
            boolean isFemale = generator.nextDouble() < femaleChance;
            double healthCoefficient =
                    generator.nextDouble() * (maxHealthCoefficient - minHealthCoefficient)
                            + minHealthCoefficient;
            addGuppy(new Guppy(genus, species, age, isFemale, 0, healthCoefficient));
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
     * <p>
     * If the resulting temperature is less than the minimum or greater than the maximum, then the
     * new temperature of the pool is set to be the closest bound.
     *
     * @param delta
     *         the change in temperature
     */
    public void changeTemperature(double delta) {

        double newTemp = temperatureCelsius + delta;

        if (newTemp < MINIMUM_POOL_TEMP_CELSIUS) {
            newTemp = MINIMUM_POOL_TEMP_CELSIUS;
        } else if (newTemp > MAXIMUM_POOL_TEMP_CELSIUS) {
            newTemp = MAXIMUM_POOL_TEMP_CELSIUS;
        }

        temperatureCelsius = newTemp;
    }

    /**
     * Adds the given guppy to the pool.
     *
     * @param guppy
     *         the guppy to add
     *
     * @return true if successful; false otherwise
     */
    public boolean addGuppy(Guppy guppy) {

        if (guppy == null) {
            throw new IllegalArgumentException("The given parameter was null.");
        }
        return guppiesInPool.add(guppy);
    }

    /**
     * Adds the given guppies to the pool.
     *
     * @param guppies
     *         the guppies to add
     *
     * @return true if successful; false otherwise
     */
    public boolean addGuppies(ArrayList<Guppy> guppies) {

        if (guppies == null) {
            throw new IllegalArgumentException("The given parameter was null.");
        }
        return guppiesInPool.addAll(guppies);
    }

    /**
     * Returns the number of guppies in the pool.
     *
     * @return the number of guppies in the pool
     */
    public int getPopulation() {

        return guppiesInPool.size();
    }

    /**
     * Calculates which guppies in the pool have died to malnutrition and returns the number of
     * deaths.
     *
     * @return the number of guppies that died due to malnutrition
     */
    public int applyNutrientCoefficient() {

        int countDied = 0;

        for (Guppy guppy : guppiesInPool) {
            if (generator.nextDouble() > nutrientCoefficient) {
                guppy.setIsAlive(false);
                countDied++;
            }
        }

        return countDied;
    }

    /**
     * Removes the dead guppies from the pool.
     *
     * @return the number of guppies removed
     */
    public int removeDeadGuppies() {

        int guppiesRemoved = 0;

        Iterator<Guppy> iterator = guppiesInPool.iterator();

        while (iterator.hasNext()) {
            if (!iterator.next().getIsAlive()) {
                iterator.remove();
                guppiesRemoved++;
            }
        }

        return guppiesRemoved;
    }

    /**
     * Calculates the volume of water needed for the pool's living population
     *
     * @return the volume of water needed in Litres
     */
    public double getGuppyVolumeRequirementInLitres() {

        double volumeNeeded = 0.0;
        final double mlPerL = 1000.0;

        for (Guppy guppy : guppiesInPool) {
            volumeNeeded += guppy.getVolumeNeeded();
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

        for (Guppy guppy : guppiesInPool) {
            if (guppy.getIsAlive()) {
                age += guppy.getAgeInWeeks();
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

        for (Guppy guppy : guppiesInPool) {
            if (guppy.getIsAlive()) {
                healthCoefficient += guppy.getHealthCoefficient();
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

        for (Guppy guppy : guppiesInPool) {
            if (guppy.getIsAlive()) {
                countFemale += guppy.getIsFemale() ? 1 : 0;
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

        ArrayList<Integer> livingGuppiesSorted = sortLivingGuppyAges();

        if (livingGuppiesSorted.size() == 0) {
            medianAge = 0;
        } else if (livingGuppiesSorted.size() % 2 == 0) {
            medianAge = (livingGuppiesSorted.get(livingGuppiesSorted.size() / 2 - 1)
                    + livingGuppiesSorted.get(livingGuppiesSorted.size() / 2)) / 2.0;
        } else {
            medianAge = livingGuppiesSorted.get(livingGuppiesSorted.size() / 2 - 1);
        }

        return medianAge;

    }

    /**
     * Kills the weakest guppies in the pool until the pool has enough water to support the living
     * population.
     *
     * @return the number of guppies that died
     */
    public ArrayList<Guppy> adjustForCrowding() {

        sortGuppiesByHealthCoefficient();

        ArrayList<Guppy> weakestGuppies = new ArrayList<>();
        int index = 0;

        while (volumeLitres < getGuppyVolumeRequirementInLitres()) {
            weakestGuppies.add(guppiesInPool.remove(index));
        }

        return weakestGuppies;
    }

    /**
     * Increments the age of each guppy in the pool by one week and calculates the number that die
     * as a result.
     *
     * @return the number of guppies that died
     */
    public int incrementAges() {

        int countDied = 0;

        for (Guppy guppy : guppiesInPool) {
            guppy.incrementAge();
            if (!guppy.getIsAlive()) {
                countDied++;
            }
        }

        return countDied;
    }

    /**
     * Attempts to spawn baby guppies for each guppy in the pool.
     * <p>
     * <p>
     * The new baby guppies are then added to the pool and the number that spawned is returned.
     *
     * @return the total number of spawned guppies
     */
    public int spawn() {

        ArrayList<Guppy> newGuppies;
        int guppiesSpawned = 0;

        for (Guppy guppy : guppiesInPool) {
            newGuppies = guppy.spawn();
            if (newGuppies != null) {
                guppiesInPool.addAll(newGuppies);
                guppiesSpawned += newGuppies.size();
            }
        }

        return guppiesSpawned;
    }

    /**
     * Produces a list of the pool's living population's ages in ascending order.
     *
     * @return a list of the pool's living population's ages in ascending order
     */
    public ArrayList<Integer> sortLivingGuppyAges() {

        ArrayList<Integer> sorted = new ArrayList<>();

        for (Guppy guppy : guppiesInPool) {
            if (guppy.getIsAlive()) {
                sorted.add(guppy.getAgeInWeeks());
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
     * Sorts the pool's population by ascending health coefficient.
     */
    public void sortGuppiesByHealthCoefficient() {

        int min;

        for (int i = 0; i < guppiesInPool.size(); i++) {

            min = i;

            for (int j = i + 1; j < guppiesInPool.size(); j++) {
                if (guppiesInPool.get(i).getHealthCoefficient() > guppiesInPool.get(j)
                        .getHealthCoefficient()) {
                    min = j;
                }
                guppiesInPool.add(i, guppiesInPool.remove(min));
            }
        }
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
        if (Double.compare(pool.temperatureCelsius, temperatureCelsius) != 0) {
            return false;
        }
        if (Double.compare(pool.pH, pH) != 0) {
            return false;
        }
        if (Double.compare(pool.nutrientCoefficient, nutrientCoefficient) != 0) {
            return false;
        }
        if (identificationNumber != pool.identificationNumber) {
            return false;
        }
        if (!name.equals(pool.name)) {
            return false;
        }
        return guppiesInPool.equals(pool.guppiesInPool);
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

        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(volumeLitres);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(temperatureCelsius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pH);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(nutrientCoefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + identificationNumber;
        result = 31 * result + guppiesInPool.hashCode();
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

        return "[name=" + name + ",volumeLitres=" + volumeLitres + ",temperatureCelsius="
                + temperatureCelsius + ",pH=" + pH + ",nutrientCoefficient=" + nutrientCoefficient
                + ",identificationNumber=" + identificationNumber + ",guppiesInPool="
                + guppiesInPool + "]";
    }
}
