package io.github.paulszefer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Tests the constructors and methods of the Pool class.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class PoolTest {

    public static final String NAME_VALID = "Poolname";
    public static final String NAME_WHITESPACE_UNFORMATTED = "p O o L n A m E";
    public static final String NAME_NULL = null;

    public static final double VOLUME_LITRES_BELOW_LOWER_BOUND = -3.6;
    public static final double VOLUME_LITRES_LOWER_BOUND = 0.0;
    public static final double VOLUME_LITRES_VALID = 203.2;

    public static final double TEMP_CELSIUS_BELOW_LOWER_BOUND = Pool.MINIMUM_POOL_TEMP_CELSIUS
            - 1.0;
    public static final double TEMP_CELSIUS_LOWER_BOUND = Pool.MINIMUM_POOL_TEMP_CELSIUS;
    public static final double TEMP_CELSIUS_VALID_1 = Pool.MINIMUM_POOL_TEMP_CELSIUS + 1.0;
    public static final double TEMP_CELSIUS_VALID_2 = Pool.MAXIMUM_POOL_TEMP_CELSIUS - 1.0;
    public static final double TEMP_CELSIUS_UPPER_BOUND = Pool.MAXIMUM_POOL_TEMP_CELSIUS;
    public static final double TEMP_CELSIUS_ABOVE_UPPER_BOUND = Pool.MAXIMUM_POOL_TEMP_CELSIUS
            + 1.0;

    public static final double PH_BELOW_LOWER_BOUND = Pool.MINIMUM_PH - 1.0;
    public static final double PH_LOWER_BOUND = Pool.MINIMUM_PH;
    public static final double PH_VALID_1 = Pool.MINIMUM_PH + 1.0;
    public static final double PH_VALID_2 = Pool.MAXIMUM_PH - 1.0;
    public static final double PH_UPPER_BOUND = Pool.MAXIMUM_PH;
    public static final double PH_ABOVE_UPPER_BOUND = Pool.MAXIMUM_PH + 1.0;

    public static final double NUTRIENT_COEFFICIENT_BELOW_LOWER_BOUND = Pool.MINIMUM_NUTRIENT_COEFFICIENT
            - 0.1;
    public static final double NUTRIENT_COEFFICIENT_LOWER_BOUND = Pool.MINIMUM_NUTRIENT_COEFFICIENT;
    public static final double NUTRIENT_COEFFICIENT_VALID_1 = Pool.MINIMUM_NUTRIENT_COEFFICIENT
            + 0.1;
    public static final double NUTRIENT_COEFFICIENT_VALID_2 = Pool.MAXIMUM_NUTRIENT_COEFFICIENT
            - 0.1;
    public static final double NUTRIENT_COEFFICIENT_UPPER_BOUND = Pool.MAXIMUM_NUTRIENT_COEFFICIENT;
    public static final double NUTRIENT_COEFFICIENT_ABOVE_UPPER_BOUND = Pool.MAXIMUM_NUTRIENT_COEFFICIENT
            + 0.1;

    public static final double TOLERANCE = 0.000001;

    private static Random generator = new Random();

    private Pool pool;
    private Pool pool0ParameterConstructor;
    private Pool pool5ParameterConstructorValidParameterValues;
    private Pool pool5ParameterConstructorInvalidParameterValuesBelowBound;
    private Pool pool5ParameterConstructorInvalidParameterValuesAboveBound;
    private Pool pool5ParameterConstructor5;
    private ArrayList<Guppy> testGuppies;

    @Before
    public void setUp() throws Exception {

        pool = new Pool(NAME_VALID, VOLUME_LITRES_VALID, TEMP_CELSIUS_VALID_2, PH_VALID_2,
                NUTRIENT_COEFFICIENT_VALID_2);
        pool0ParameterConstructor = new Pool();
        pool5ParameterConstructorValidParameterValues = new Pool(NAME_VALID, VOLUME_LITRES_VALID,
                TEMP_CELSIUS_VALID_1, PH_VALID_1, NUTRIENT_COEFFICIENT_VALID_1);
        pool5ParameterConstructorInvalidParameterValuesBelowBound = new Pool(
                NAME_WHITESPACE_UNFORMATTED, VOLUME_LITRES_BELOW_LOWER_BOUND,
                TEMP_CELSIUS_BELOW_LOWER_BOUND, PH_BELOW_LOWER_BOUND,
                NUTRIENT_COEFFICIENT_BELOW_LOWER_BOUND);
        pool5ParameterConstructorInvalidParameterValuesAboveBound = new Pool(NAME_NULL,
                VOLUME_LITRES_LOWER_BOUND, TEMP_CELSIUS_ABOVE_UPPER_BOUND, PH_ABOVE_UPPER_BOUND,
                NUTRIENT_COEFFICIENT_ABOVE_UPPER_BOUND);

        testGuppies = new ArrayList<>();

        // must be even
        final int numberOfTestGuppies = 10;

        for (int i = 0; i < numberOfTestGuppies; i++) {
            testGuppies.add(new Guppy());
        }

        pool.setGuppiesInPool(testGuppies);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPoolName() {

        assertThat(pool0ParameterConstructor.getName(), is(Pool.DEFAULT_POOL_NAME));

    }

    @Test
    public void testPoolVolumeLitres() {

        assertThat(pool0ParameterConstructor.getVolumeLitres(), is(0.0));

    }

    @Test
    public void testPoolTemperature() {

        assertThat(pool0ParameterConstructor.getTemperatureCelsius(),
                is(Pool.DEFAULT_POOL_TEMP_CELSIUS));

    }

    @Test
    public void testPoolpH() {

        assertThat(pool0ParameterConstructor.getpH(), is(Pool.NEUTRAL_PH));

    }

    @Test
    public void testPoolNutrientCoefficient() {

        assertThat(pool0ParameterConstructor.getNutrientCoefficient(),
                is(Pool.DEFAULT_NUTRIENT_COEFFICIENT));

    }

    @Test
    public void testPoolIdentificationNumber() {

        pool0ParameterConstructor = new Pool();

        assertThat(pool0ParameterConstructor.getIdentificationNumber(),
                is(Pool.getNumberCreated()));

    }

    @Test
    public void testPoolGuppiesInPoolCreatesArrayList() {

        assertThat(testGuppies, is(instanceOf(ArrayList.class)));

    }

    @Test
    public void testPoolGuppiesInPoolIsEmptyArrayList() {

        assertThat(pool0ParameterConstructor.getGuppiesInPool().size(), is(0));

    }

    @Test
    public void testPoolRandomNumberGeneratorGeneratesInteger() {

        int identificationNumber = pool0ParameterConstructor.getIdentificationNumber();

        assertThat(identificationNumber, is(instanceOf(Integer.class)));

    }

    @Test
    public void testPoolRandomNumberGeneratorGeneratesPositiveNumber() {

        int identificationNumber = pool0ParameterConstructor.getIdentificationNumber();

        assertThat(identificationNumber, is(greaterThanOrEqualTo(0)));

    }

    @Test
    public void testPoolIncrementsNumberOfPools() {

        int initialNumberOfPools = Pool.getNumberCreated();

        new Pool();

        int incrementedNumberOfPools = initialNumberOfPools + 1;
        int result = Pool.getNumberCreated();

        assertThat(result, is(equalTo(incrementedNumberOfPools)));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleName() {

        assertThat(pool5ParameterConstructorValidParameterValues.getName(), is(NAME_VALID));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleVolumeLitres() {

        assertThat(pool5ParameterConstructorValidParameterValues.getVolumeLitres(),
                is(VOLUME_LITRES_VALID));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleTemperature() {

        assertThat(pool5ParameterConstructorValidParameterValues.getTemperatureCelsius(),
                is(TEMP_CELSIUS_VALID_1));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoublePH() {

        assertThat(pool5ParameterConstructorValidParameterValues.getpH(), is(PH_VALID_1));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleNutrientCoefficient() {

        assertThat(pool5ParameterConstructorValidParameterValues.getNutrientCoefficient(),
                is(NUTRIENT_COEFFICIENT_VALID_1));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleIdentificationNumber() {

        pool5ParameterConstructorValidParameterValues = new Pool(NAME_VALID, VOLUME_LITRES_VALID,
                TEMP_CELSIUS_VALID_1, PH_VALID_1, NUTRIENT_COEFFICIENT_VALID_1);

        int numberOfPools = Pool.getNumberCreated();

        assertThat(pool5ParameterConstructorValidParameterValues.getIdentificationNumber(),
                is(numberOfPools));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleGuppiesInPoolCreatesArrayList() {

        assertThat(pool5ParameterConstructorValidParameterValues.getGuppiesInPool(),
                is(instanceOf(ArrayList.class)));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleGuppiesInPoolCreatesEmptyArrayList() {

        assertThat(pool5ParameterConstructorValidParameterValues.getGuppiesInPool().size(), is(0));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleRandomNumberGeneratorGeneratesInteger() {

        assertThat(pool5ParameterConstructorValidParameterValues.getIdentificationNumber(),
                is(instanceOf(Integer.class)));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleRandomNumberGeneratorGeneratesPositiveNumber() {

        assertThat(pool5ParameterConstructorValidParameterValues.getIdentificationNumber(),
                is(greaterThanOrEqualTo(0)));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleIncrementsNumberOfPools() {

        int initial = Pool.getNumberCreated();

        new Pool(NAME_VALID, VOLUME_LITRES_VALID, TEMP_CELSIUS_VALID_1, PH_VALID_1,
                NUTRIENT_COEFFICIENT_VALID_1);

        assertThat(initial + 1, is(equalTo(Pool.getNumberCreated())));

    }

    @Test
    public void testGetNumberCreated() {

        Pool testPool = new Pool();

        assertThat(Pool.getNumberCreated(), is(testPool.getIdentificationNumber()));

    }

    @Test
    public void testGetName() {

        assertThat(pool5ParameterConstructorValidParameterValues.getName(), is(NAME_VALID));

    }

    @Test
    public void testGetVolumeLitres() {

        assertThat(pool5ParameterConstructorValidParameterValues.getVolumeLitres(),
                is(VOLUME_LITRES_VALID));

    }

    @Test
    public void testGetTemperatureCelsius() {

        assertThat(pool5ParameterConstructorValidParameterValues.getTemperatureCelsius(),
                is(TEMP_CELSIUS_VALID_1));

    }

    @Test
    public void testGetpH() {

        assertThat(pool5ParameterConstructorValidParameterValues.getpH(), is(PH_VALID_1));

    }

    @Test
    public void testGetNutrientCoefficient() {

        assertThat(pool5ParameterConstructorValidParameterValues.getNutrientCoefficient(),
                is(NUTRIENT_COEFFICIENT_VALID_1));

    }

    @Test
    public void testGetGuppiesInPool() {

        assertThat(pool5ParameterConstructorValidParameterValues.getName(), is(NAME_VALID));

    }

    @Test
    public void testGetIdentificationNumber() {

        assertThat(pool5ParameterConstructorValidParameterValues.getName(), is(NAME_VALID));

    }

    @Test
    public void testSetNameValid() {

        pool.setName(NAME_VALID);

        assertThat(pool.getName(), is(NAME_VALID));

    }

    @Test
    public void testSetNameWhitespaceUnformatted() {

        pool.setName(NAME_WHITESPACE_UNFORMATTED);

        String nameNoSpaces = NAME_WHITESPACE_UNFORMATTED.replace(" ", "");
        String nameFormatted = nameNoSpaces.substring(0, 1).toUpperCase()
                + nameNoSpaces.substring(1).toLowerCase();

        assertThat(pool.getName(), is(nameFormatted));

    }

    @Test
    public void testSetNameNull() {

        pool.setName(null);

        assertThat(pool.getName(), is(Pool.DEFAULT_POOL_NAME));

    }

    @Test
    public void testSetVolumeLitresBelowLowerBound() {

        pool.setVolumeLitres(VOLUME_LITRES_BELOW_LOWER_BOUND);

        assertThat(pool.getVolumeLitres(), is(0.0));

    }

    @Test
    public void testSetVolumeLitresLowerBound() {

        pool.setVolumeLitres(VOLUME_LITRES_LOWER_BOUND);

        assertThat(pool.getVolumeLitres(), is(0.0));

    }

    @Test
    public void testSetVolumeLitresValid() {

        pool.setVolumeLitres(VOLUME_LITRES_VALID);

        assertThat(pool.getVolumeLitres(), is(VOLUME_LITRES_VALID));

    }

    @Test
    public void testSetTemperatureCelsiusBelowLowerBound() {

        pool.setTemperatureCelsius(TEMP_CELSIUS_BELOW_LOWER_BOUND);

        assertThat(pool.getTemperatureCelsius(), is(Pool.DEFAULT_POOL_TEMP_CELSIUS));

    }

    @Test
    public void testSetTemperatureCelsiusLowerBound() {

        pool.setTemperatureCelsius(TEMP_CELSIUS_LOWER_BOUND);

        assertThat(pool.getTemperatureCelsius(), is(TEMP_CELSIUS_LOWER_BOUND));

    }

    @Test
    public void testSetTemperatureCelsiusValid1() {

        pool.setTemperatureCelsius(TEMP_CELSIUS_VALID_1);

        assertThat(pool.getTemperatureCelsius(), is(TEMP_CELSIUS_VALID_1));

    }

    @Test
    public void testSetTemperatureCelsiusValid2() {

        pool.setTemperatureCelsius(TEMP_CELSIUS_VALID_2);

        assertThat(pool.getTemperatureCelsius(), is(TEMP_CELSIUS_VALID_2));

    }

    @Test
    public void testSetTemperatureCelsiusUpperBound() {

        pool.setTemperatureCelsius(TEMP_CELSIUS_UPPER_BOUND);

        assertThat(pool.getTemperatureCelsius(), is(TEMP_CELSIUS_UPPER_BOUND));

    }

    @Test
    public void testSetTemperatureCelsiusAboveUpperBound() {

        pool.setTemperatureCelsius(TEMP_CELSIUS_ABOVE_UPPER_BOUND);

        assertThat(pool.getTemperatureCelsius(), is(Pool.DEFAULT_POOL_TEMP_CELSIUS));

    }

    @Test
    public void testSetpHBelowLowerBound() {

        pool.setpH(PH_BELOW_LOWER_BOUND);

        assertThat(pool.getpH(), is(Pool.NEUTRAL_PH));

    }

    @Test
    public void testSetpHLowerBound() {

        pool.setpH(PH_LOWER_BOUND);

        assertThat(pool.getpH(), is(PH_LOWER_BOUND));

    }

    @Test
    public void testSetpHValid1() {

        pool.setpH(PH_VALID_1);

        assertThat(pool.getpH(), is(PH_VALID_1));

    }

    @Test
    public void testSetpHValid2() {

        pool.setpH(PH_VALID_2);

        assertThat(pool.getpH(), is(PH_VALID_2));

    }

    @Test
    public void testSetpHUpperBound() {

        pool.setpH(PH_UPPER_BOUND);

        assertThat(pool.getpH(), is(PH_UPPER_BOUND));

    }

    @Test
    public void testSetpHAboveUpperBound() {

        pool.setpH(PH_ABOVE_UPPER_BOUND);

        assertThat(pool.getpH(), is(Pool.NEUTRAL_PH));

    }

    @Test
    public void testSetNutrientCoefficientBelowLowerBound() {

        pool.setNutrientCoefficient(NUTRIENT_COEFFICIENT_BELOW_LOWER_BOUND);

        assertThat(pool.getNutrientCoefficient(), is(Pool.DEFAULT_NUTRIENT_COEFFICIENT));

    }

    @Test
    public void testSetNutrientCoefficientLowerBound() {

        pool.setNutrientCoefficient(NUTRIENT_COEFFICIENT_LOWER_BOUND);

        assertThat(pool.getNutrientCoefficient(), is(NUTRIENT_COEFFICIENT_LOWER_BOUND));

    }

    @Test
    public void testSetNutrientCoefficientValid1() {

        pool.setNutrientCoefficient(NUTRIENT_COEFFICIENT_VALID_1);

        assertThat(pool.getNutrientCoefficient(), is(NUTRIENT_COEFFICIENT_VALID_1));

    }

    @Test
    public void testSetNutrientCoefficientValid2() {

        pool.setNutrientCoefficient(NUTRIENT_COEFFICIENT_VALID_2);

        assertThat(pool.getNutrientCoefficient(), is(NUTRIENT_COEFFICIENT_VALID_2));

    }

    @Test
    public void testSetNutrientCoefficientUpperBound() {

        pool.setNutrientCoefficient(NUTRIENT_COEFFICIENT_UPPER_BOUND);

        assertThat(pool.getNutrientCoefficient(), is(NUTRIENT_COEFFICIENT_UPPER_BOUND));

    }

    @Test
    public void testSetNutrientCoefficientAboveUpperBound() {

        pool.setNutrientCoefficient(NUTRIENT_COEFFICIENT_ABOVE_UPPER_BOUND);

        assertThat(pool.getNutrientCoefficient(), is(Pool.DEFAULT_NUTRIENT_COEFFICIENT));

    }

    @Test
    public void testSetGuppiesInPoolSetsCorrectList() {

        assertThat(pool.getGuppiesInPool(), is(testGuppies));

    }

    @Test
    public void testSetGuppiesInPoolSetsArrayListOfGuppies() {

        assertThat(pool.getGuppiesInPool(), is(instanceOf(ArrayList.class)));

    }

    @Test
    public void testSetGuppiesInPoolIgnoresNull() {

        pool.setGuppiesInPool(null);

        assertThat(pool.getGuppiesInPool(), is(testGuppies));

    }

    @Test
    public void testChangeNutrientCoefficientDecreaseToBelowLowerBound() {

        final double delta = 0.1;

        pool.setNutrientCoefficient(Pool.MINIMUM_NUTRIENT_COEFFICIENT + delta);
        pool.changeNutrientCoefficient(-delta * 2);

        assertThat(pool.getNutrientCoefficient(), is(Pool.MINIMUM_NUTRIENT_COEFFICIENT));

    }

    @Test
    public void testChangeNutrientCoefficientDecreaseToLowerBound() {

        final double delta = 0.1;

        pool.setNutrientCoefficient(Pool.MINIMUM_NUTRIENT_COEFFICIENT + delta);
        pool.changeNutrientCoefficient(-delta);

        assertThat(pool.getNutrientCoefficient(), is(Pool.MINIMUM_NUTRIENT_COEFFICIENT));

    }

    @Test
    public void testChangeNutrientCoefficientValidDecrease() {

        final double delta = 0.1;

        double initial = delta * 2;

        double result = initial - delta;

        pool.setNutrientCoefficient(Pool.MINIMUM_NUTRIENT_COEFFICIENT + initial);
        pool.changeNutrientCoefficient(-delta);

        assertThat(pool.getNutrientCoefficient(), is(result));

    }

    @Test
    public void testChangeNutrientCoefficientNoChange() {

        final double delta = 0.1;

        double initial = delta * 2;

        pool.setNutrientCoefficient(Pool.MINIMUM_NUTRIENT_COEFFICIENT + initial);
        pool.changeNutrientCoefficient(0.0);

        assertThat(pool.getNutrientCoefficient(), is(initial));

    }

    @Test
    public void testChangeNutrientCoefficientValidIncrease() {

        final double delta = 0.1;

        double initial = delta * 2;

        double result = initial + delta;

        pool.setNutrientCoefficient(Pool.MINIMUM_NUTRIENT_COEFFICIENT + initial);
        pool.changeNutrientCoefficient(delta);

        assertThat(pool.getNutrientCoefficient(), is(result));

    }

    @Test
    public void testChangeNutrientCoefficientIncreaseToUpperBound() {

        final double delta = 0.5;

        double result = delta + delta;

        pool.setNutrientCoefficient(delta);
        pool.changeNutrientCoefficient(delta);

        assertThat(pool.getNutrientCoefficient(), is(result));

    }

    @Test
    public void testChangeNutrientCoefficientIncreaseToAboveUpperBound() {

        final double delta = 0.5;

        double result = Pool.MAXIMUM_NUTRIENT_COEFFICIENT;

        pool.setNutrientCoefficient(delta);
        pool.changeNutrientCoefficient(delta);

        assertThat(pool.getNutrientCoefficient(), is(result));

    }

    @Test
    public void testChangeTemperatureToBelowLowerBound() {

        double delta = -1;

        pool.setTemperatureCelsius(Pool.MINIMUM_POOL_TEMP_CELSIUS);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperatureCelsius(), is(Pool.MINIMUM_POOL_TEMP_CELSIUS));

    }

    @Test
    public void testChangeTemperatureToLowerBound() {

        double delta = -1;

        pool.setTemperatureCelsius(Pool.MINIMUM_POOL_TEMP_CELSIUS + 1);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperatureCelsius(), is(Pool.MINIMUM_POOL_TEMP_CELSIUS));

    }

    @Test
    public void testChangeTemperatureValidDecrease() {

        double delta = -1;

        pool.setTemperatureCelsius(Pool.MINIMUM_POOL_TEMP_CELSIUS + 2);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperatureCelsius(), is(Pool.MINIMUM_POOL_TEMP_CELSIUS + 1));

    }

    @Test
    public void testChangeTemperatureNoChange() {

        double delta = 0;

        pool.setTemperatureCelsius(Pool.MINIMUM_POOL_TEMP_CELSIUS + 1);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperatureCelsius(), is(Pool.MINIMUM_POOL_TEMP_CELSIUS + 1));

    }

    @Test
    public void testChangeTemperatureValidIncrease() {

        double delta = 1;

        pool.setTemperatureCelsius(Pool.MAXIMUM_POOL_TEMP_CELSIUS - 2);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperatureCelsius(), is(Pool.MAXIMUM_POOL_TEMP_CELSIUS - 1));

    }

    @Test
    public void testChangeTemperatureToUpperBound() {

        double delta = 1;

        pool.setTemperatureCelsius(Pool.MAXIMUM_POOL_TEMP_CELSIUS - 1);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperatureCelsius(), is(Pool.MAXIMUM_POOL_TEMP_CELSIUS));

    }

    @Test
    public void testChangeTemperatureToAboveUpperBound() {

        double delta = 2;

        pool.setTemperatureCelsius(Pool.MAXIMUM_POOL_TEMP_CELSIUS);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperatureCelsius(), is(Pool.MAXIMUM_POOL_TEMP_CELSIUS));

    }

    @Test
    public void testAddGuppyEmptyArrayList() {

        ArrayList<Guppy> emptyList = new ArrayList<>();

        pool.setGuppiesInPool(emptyList);

        Guppy guppy = new Guppy();

        pool.addGuppy(guppy);

        assertThat(pool.getGuppiesInPool().get(0), is(guppy));
    }

    @Test
    public void testAddGuppyNotEmptyArrayList() {

        ArrayList<Guppy> emptyList = new ArrayList<>();

        pool.setGuppiesInPool(emptyList);
        pool.addGuppy(new Guppy());

        Guppy guppy = new Guppy();
        pool.addGuppy(guppy);

        assertThat(pool.getGuppiesInPool().get(1), is(guppy));

    }

    @Test
    public void testAddGuppyValidGuppySucceeds() {

        assertTrue(pool.addGuppy(new Guppy()));

    }

    @Test
    public void testAddGuppyNullFails() {

        assertFalse(pool.addGuppy(null));

    }

    @Test
    public void testGetPopulation() {

        assertThat(pool.getPopulation(), is(testGuppies.size()));

    }

    @Test
    public void testGetPopulationZero() {

        pool.setGuppiesInPool(new ArrayList<>());

        assertThat(pool.getPopulation(), is(0));
    }

    @Test
    public void testApplyNutrientCoefficient() {

        double expectedDead = Math
                .round((1 - pool.getNutrientCoefficient()) * pool.getPopulation());
        final double tolerance = pool.getPopulation() * 0.1;

        assertThat((double) pool.applyNutrientCoefficient(), is(closeTo(expectedDead, tolerance)));
    }

    @Test
    public void testRemoveDeadGuppiesNoDead() {

        int removed = pool.removeDeadGuppies();

        assertThat(removed, is(0));

    }

    @Test
    public void testRemoveDeadGuppiesSomeDead() {

        int countDead = 0;

        for (Guppy guppy : testGuppies) {

            if (generator.nextBoolean()) {

                guppy.setIsAlive(false);
                countDead++;
            }
        }

        int removed = pool.removeDeadGuppies();

        assertThat(removed, is(countDead));

    }

    @Test
    public void testRemoveDeadGuppiesAllDead() {

        int countDead = 0;

        for (Guppy guppy : testGuppies) {

            guppy.setIsAlive(false);
            countDead++;
        }

        int removed = pool.removeDeadGuppies();

        assertThat(removed, is(countDead));

    }

    @Test
    public void testGetGuppyVolumeRequirementInLitres() {

        final double mLPerL = 1000.0;
        double volume = 0.0;

        for (Guppy guppy : testGuppies) {

            volume += guppy.getVolumeNeeded() / mLPerL;
        }

        assertThat(pool.getGuppyVolumeRequirementInLitres(), is(volume));

    }

    @Test
    public void testGetGuppyVolumeRequirementInLitresExcludeDeadGuppies() {

        final double mLPerL = 1000.0;
        double volume = 0.0;

        for (Guppy guppy : testGuppies) {

            if (generator.nextBoolean()) {
                volume += guppy.getVolumeNeeded() / mLPerL;
            } else {
                guppy.setIsAlive(false);
            }
        }

        assertThat(pool.getGuppyVolumeRequirementInLitres(), is(volume));

    }

    @Test
    public void testGetAverageAgeInWeeks() {

        int totalAge = 0;
        int count = 0;

        for (Guppy guppy : testGuppies) {

            guppy.setAgeInWeeks(generator.nextInt(Guppy.MAXIMUM_AGE_IN_WEEKS));
            totalAge += guppy.getAgeInWeeks();
            count++;

        }

        double average = count > 0 ? (double) totalAge / count : 0.0;

        assertThat(pool.getAverageAgeInWeeks(), is(average));

    }

    @Test
    public void testGetAverageAgeInWeeksExcludeDeadGuppies() {

        int totalAge = 0;
        int count = 0;

        for (Guppy guppy : testGuppies) {

            if (generator.nextBoolean()) {
                guppy.setAgeInWeeks(generator.nextInt(Guppy.MAXIMUM_AGE_IN_WEEKS));
                totalAge += guppy.getAgeInWeeks();
                count++;
            } else {
                guppy.setIsAlive(false);
            }

        }

        double average = count > 0 ? (double) totalAge / count : 0.0;

        assertThat(pool.getAverageAgeInWeeks(), is(average));

    }

    @Test
    public void testGetAverageHealthCoefficient() {

        double totalHealthCoefficient = 0.0;
        int count = 0;

        for (Guppy guppy : testGuppies) {

            guppy.setHealthCoefficient(generator.nextDouble());
            totalHealthCoefficient += guppy.getHealthCoefficient();
            count++;

        }

        double average = count > 0 ? totalHealthCoefficient / count : 0.0;

        assertThat(pool.getAverageHealthCoefficient(), is(average));

    }

    @Test
    public void testGetAverageHealthCoefficientExcludeDeadGuppies() {

        double totalHealthCoefficient = 0.0;
        int count = 0;

        for (Guppy guppy : testGuppies) {

            if (generator.nextBoolean()) {
                guppy.setHealthCoefficient(generator.nextDouble());
                totalHealthCoefficient += guppy.getHealthCoefficient();
                count++;
            } else {
                guppy.setIsAlive(false);
            }

        }

        double average = count > 0 ? totalHealthCoefficient / count : 0.0;

        assertThat(pool.getAverageHealthCoefficient(), is(average));

    }

    @Test
    public void testGetFemalePercentage() {

        int countFemale = 0;
        int countAll = 0;

        for (Guppy guppy : testGuppies) {

            guppy.setIsFemale(generator.nextBoolean());
            countFemale += guppy.getIsFemale() ? 1 : 0;
            countAll++;
        }

        double average = countAll > 0 ? (double) countFemale / countAll : 0.0;

        assertThat(pool.getFemaleProportion(), is(average));

    }

    @Test
    public void testGetFemalePercentageExcludeDeadGuppies() {

        int countFemale = 0;
        int countAll = 0;

        for (Guppy guppy : testGuppies) {

            if (generator.nextBoolean()) {
                guppy.setIsFemale(generator.nextBoolean());
                countFemale += guppy.getIsFemale() ? 1 : 0;
                countAll++;
            } else {
                guppy.setIsAlive(false);
            }
        }

        double average = countAll > 0 ? (double) countFemale / countAll : 0.0;

        assertThat(pool.getFemaleProportion(), is(average));

    }

    @Test
    public void testGetMedianAgeEvenAmount() {

        for (Guppy guppy : testGuppies) {
            guppy.setAgeInWeeks(generator.nextInt(Guppy.MAXIMUM_AGE_IN_WEEKS - 1));
        }

        ArrayList<Integer> ages = pool.sortLivingGuppyAges();

        if (ages.size() % 2 != 0) {
            ages.add(50);
            pool.addGuppy(new Guppy(null, null, 50, true, 0, 0));
        }

        double medianAge = (ages.get(ages.size() / 2 - 1) + ages.get(ages.size() / 2)) / 2.0;

        assertThat(pool.getMedianAge(), is(medianAge));

    }

    @Test
    public void testGetMedianAgeOddAmount() {

        for (Guppy guppy : testGuppies) {
            guppy.setAgeInWeeks(generator.nextInt(Guppy.MAXIMUM_AGE_IN_WEEKS - 1));
        }

        ArrayList<Integer> ages = pool.sortLivingGuppyAges();

        if (ages.size() % 2 == 0) {
            ages.add(50);
            pool.addGuppy(new Guppy(null, null, 49, true, 0, 0));
        }

        double medianAge = ages.get(ages.size() / 2 - 1);

        assertThat(pool.getMedianAge(), is(medianAge));

    }

    @Test
    public void testGetMedianAgeExcludeDeadGuppies() {

        for (Guppy guppy : testGuppies) {
            if (generator.nextBoolean()) {
                guppy.setAgeInWeeks(generator.nextInt(Guppy.MAXIMUM_AGE_IN_WEEKS - 1));
            } else {
                guppy.setIsAlive(false);
            }
        }

        ArrayList<Integer> ages = pool.sortLivingGuppyAges();

        double medianAge;

        if (ages.size() % 2 == 0) {
            medianAge = (ages.get(ages.size() / 2 - 1) + ages.get(ages.size() / 2)) / 2.0;
        } else {
            medianAge = ages.get(ages.size() / 2 - 1);
        }

        assertThat(pool.getMedianAge(), is(medianAge));

    }

    @Test
    public void testPrintDetails() {

        // if toString works, this should work
        // how do you monitor the output to the console/isolate to only this
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testToString() {

        /*
         * assertThat(pool, is("[name=" + pool.getName() + ",volumeLitres=" + pool.getVolumeLitres()
         * + ",temperatureCelsius=" + pool.getTemperatureCelsius() + ",pH=" + pool.getpH() +
         * ",nutrientCoefficient=" + pool.getNutrientCoefficient() + ",identificationNumber=" +
         * pool.getIdentificationNumber() + ",guppiesInPool=" + pool.getGuppiesInPool() +
         * ",randomNumberGenerator=" + pool.getran() + "]";))
         */
        // no getter for random number generator, cannot test from outside pool
        // class
        fail("Not yet implemented"); // TODO

    }

}