package io.github.paulszefer;

import io.github.paulszefer.sim.Creature;
import io.github.paulszefer.sim.Guppy;
import io.github.paulszefer.sim.Pool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    public static final double TEMP_CELSIUS_BELOW_LOWER_BOUND =
            Pool.MINIMUM_WATER_TEMP_CELSIUS - 1.0;
    public static final double TEMP_CELSIUS_LOWER_BOUND = Pool.MINIMUM_WATER_TEMP_CELSIUS;
    public static final double TEMP_CELSIUS_VALID_1 = Pool.MINIMUM_WATER_TEMP_CELSIUS + 1.0;
    public static final double TEMP_CELSIUS_VALID_2 = Pool.MAXIMUM_WATER_TEMP_CELSIUS - 1.0;
    public static final double TEMP_CELSIUS_UPPER_BOUND = Pool.MAXIMUM_WATER_TEMP_CELSIUS;
    public static final double TEMP_CELSIUS_ABOVE_UPPER_BOUND =
            Pool.MAXIMUM_WATER_TEMP_CELSIUS + 1.0;

    public static final double PH_BELOW_LOWER_BOUND = Pool.MINIMUM_PH - 1.0;
    public static final double PH_LOWER_BOUND = Pool.MINIMUM_PH;
    public static final double PH_VALID_1 = Pool.MINIMUM_PH + 1.0;
    public static final double PH_VALID_2 = Pool.MAXIMUM_PH - 1.0;
    public static final double PH_UPPER_BOUND = Pool.MAXIMUM_PH;
    public static final double PH_ABOVE_UPPER_BOUND = Pool.MAXIMUM_PH + 1.0;

    public static final double NUTRIENT_COEFFICIENT_BELOW_LOWER_BOUND =
            Pool.MINIMUM_NUTRIENT_COEFFICIENT - 0.1;
    public static final double NUTRIENT_COEFFICIENT_LOWER_BOUND = Pool.MINIMUM_NUTRIENT_COEFFICIENT;
    public static final double NUTRIENT_COEFFICIENT_VALID_1 =
            Pool.MINIMUM_NUTRIENT_COEFFICIENT + 0.1;
    public static final double NUTRIENT_COEFFICIENT_VALID_2 =
            Pool.MAXIMUM_NUTRIENT_COEFFICIENT - 0.1;
    public static final double NUTRIENT_COEFFICIENT_UPPER_BOUND = Pool.MAXIMUM_NUTRIENT_COEFFICIENT;
    public static final double NUTRIENT_COEFFICIENT_ABOVE_UPPER_BOUND =
            Pool.MAXIMUM_NUTRIENT_COEFFICIENT + 0.1;

    public static final double TOLERANCE = 0.000001;

    private static final Random GENERATOR = new Random();

    private Pool pool;
    private Pool pool0ParameterConstructor;
    private Pool pool5ParameterConstructorValidParameterValues;
    private Pool pool5ParameterConstructorInvalidParameterValuesBelowBound;
    private Pool pool5ParameterConstructorInvalidParameterValuesAboveBound;
    private Pool pool5ParameterConstructor5;
    private List<Creature> testCreatures;

    @Before
    public void setUp() throws Exception {

        pool = new Pool(NAME_VALID, VOLUME_LITRES_VALID, TEMP_CELSIUS_VALID_2, PH_VALID_2,
                        NUTRIENT_COEFFICIENT_VALID_2);
        pool0ParameterConstructor = new Pool();
        pool5ParameterConstructorValidParameterValues = new Pool(NAME_VALID, VOLUME_LITRES_VALID,
                                                                 TEMP_CELSIUS_VALID_1, PH_VALID_1,
                                                                 NUTRIENT_COEFFICIENT_VALID_1);
        pool5ParameterConstructorInvalidParameterValuesBelowBound = new Pool(
                NAME_WHITESPACE_UNFORMATTED, VOLUME_LITRES_BELOW_LOWER_BOUND,
                TEMP_CELSIUS_BELOW_LOWER_BOUND, PH_BELOW_LOWER_BOUND,
                NUTRIENT_COEFFICIENT_BELOW_LOWER_BOUND);
        pool5ParameterConstructorInvalidParameterValuesAboveBound = new Pool(NAME_NULL,
                                                                             VOLUME_LITRES_LOWER_BOUND,
                                                                             TEMP_CELSIUS_ABOVE_UPPER_BOUND,
                                                                             PH_ABOVE_UPPER_BOUND,
                                                                             NUTRIENT_COEFFICIENT_ABOVE_UPPER_BOUND);

        testCreatures = new ArrayList<>();

        // must be even
        final int numberOfTestCreatures = 100;

        for (int i = 0; i < numberOfTestCreatures; i++) {
            testCreatures.add(new Guppy());
        }

        pool.setCreatures(testCreatures);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPoolName() {

        assertThat(pool0ParameterConstructor.getName(), is(Pool.DEFAULT_WATER_BODY_NAME));

    }

    @Test
    public void testPoolVolumeLitres() {

        assertThat(pool0ParameterConstructor.getVolumeLitres(), is(0.0));

    }

    @Test
    public void testPoolTemperature() {

        assertThat(pool0ParameterConstructor.getTemperature(), is(Pool.DEFAULT_WATER_TEMP_CELSIUS));

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
    public void testPoolCreaturesInPoolCreatesList() {

        assertThat(testCreatures, is(instanceOf(List.class)));

    }

    @Test
    public void testPoolCreaturesInPoolIsEmptyList() {

        assertThat(pool0ParameterConstructor.getCreatures().size(), is(0));

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

        assertThat(pool5ParameterConstructorValidParameterValues.getTemperature(),
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
                                                                 TEMP_CELSIUS_VALID_1, PH_VALID_1,
                                                                 NUTRIENT_COEFFICIENT_VALID_1);

        int numberOfPools = Pool.getNumberCreated();

        assertThat(pool5ParameterConstructorValidParameterValues.getIdentificationNumber(),
                   is(numberOfPools));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleCreaturesInPoolCreatesList() {

        assertThat(pool5ParameterConstructorValidParameterValues.getCreatures(),
                   is(instanceOf(List.class)));

    }

    @Test
    public void testPoolStringDoubleDoubleDoubleDoubleCreaturesInPoolCreatesEmptyList() {

        assertThat(pool5ParameterConstructorValidParameterValues.getCreatures().size(), is(0));

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
    public void testGetTemperature() {

        assertThat(pool5ParameterConstructorValidParameterValues.getTemperature(),
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
    public void testGetCreaturesInPool() {

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
        String nameFormatted = nameNoSpaces.substring(0, 1).toUpperCase() + nameNoSpaces.substring(
                1).toLowerCase();

        assertThat(pool.getName(), is(nameFormatted));

    }

    @Test
    public void testSetNameNull() {

        pool.setName(null);

        assertThat(pool.getName(), is(Pool.DEFAULT_WATER_BODY_NAME));

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
    public void testSetTemperatureBelowLowerBound() {

        pool.setTemperature(TEMP_CELSIUS_BELOW_LOWER_BOUND);

        assertThat(pool.getTemperature(), is(Pool.DEFAULT_WATER_TEMP_CELSIUS));

    }

    @Test
    public void testSetTemperatureLowerBound() {

        pool.setTemperature(TEMP_CELSIUS_LOWER_BOUND);

        assertThat(pool.getTemperature(), is(TEMP_CELSIUS_LOWER_BOUND));

    }

    @Test
    public void testSetTemperatureValid1() {

        pool.setTemperature(TEMP_CELSIUS_VALID_1);

        assertThat(pool.getTemperature(), is(TEMP_CELSIUS_VALID_1));

    }

    @Test
    public void testSetTemperatureValid2() {

        pool.setTemperature(TEMP_CELSIUS_VALID_2);

        assertThat(pool.getTemperature(), is(TEMP_CELSIUS_VALID_2));

    }

    @Test
    public void testSetTemperatureUpperBound() {

        pool.setTemperature(TEMP_CELSIUS_UPPER_BOUND);

        assertThat(pool.getTemperature(), is(TEMP_CELSIUS_UPPER_BOUND));

    }

    @Test
    public void testSetTemperatureAboveUpperBound() {

        pool.setTemperature(TEMP_CELSIUS_ABOVE_UPPER_BOUND);

        assertThat(pool.getTemperature(), is(Pool.DEFAULT_WATER_TEMP_CELSIUS));

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
    public void testSetCreaturesInPoolSetsCorrectList() {

        assertThat(pool.getCreatures(), is(testCreatures));

    }

    @Test
    public void testSetCreaturesInPoolSetsListOfCreatures() {

        assertThat(pool.getCreatures(), is(instanceOf(List.class)));

    }

    @Test
    public void testSetCreaturesInPoolIgnoresNull() {

        pool.setCreatures(null);

        assertThat(pool.getCreatures(), is(testCreatures));

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

        pool.setTemperature(Pool.MINIMUM_WATER_TEMP_CELSIUS);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperature(), is(Pool.MINIMUM_WATER_TEMP_CELSIUS));

    }

    @Test
    public void testChangeTemperatureToLowerBound() {

        double delta = -1;

        pool.setTemperature(Pool.MINIMUM_WATER_TEMP_CELSIUS + 1);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperature(), is(Pool.MINIMUM_WATER_TEMP_CELSIUS));

    }

    @Test
    public void testChangeTemperatureValidDecrease() {

        double delta = -1;

        pool.setTemperature(Pool.MINIMUM_WATER_TEMP_CELSIUS + 2);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperature(), is(Pool.MINIMUM_WATER_TEMP_CELSIUS + 1));

    }

    @Test
    public void testChangeTemperatureNoChange() {

        double delta = 0;

        pool.setTemperature(Pool.MINIMUM_WATER_TEMP_CELSIUS + 1);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperature(), is(Pool.MINIMUM_WATER_TEMP_CELSIUS + 1));

    }

    @Test
    public void testChangeTemperatureValidIncrease() {

        double delta = 1;

        pool.setTemperature(Pool.MAXIMUM_WATER_TEMP_CELSIUS - 2);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperature(), is(Pool.MAXIMUM_WATER_TEMP_CELSIUS - 1));

    }

    @Test
    public void testChangeTemperatureToUpperBound() {

        double delta = 1;

        pool.setTemperature(Pool.MAXIMUM_WATER_TEMP_CELSIUS - 1);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperature(), is(Pool.MAXIMUM_WATER_TEMP_CELSIUS));

    }

    @Test
    public void testChangeTemperatureToAboveUpperBound() {

        double delta = 2;

        pool.setTemperature(Pool.MAXIMUM_WATER_TEMP_CELSIUS);
        pool.changeTemperature(delta);

        assertThat(pool.getTemperature(), is(Pool.MAXIMUM_WATER_TEMP_CELSIUS));

    }

    @Test
    public void testAddCreatureEmptyList() {

        List<Creature> emptyList = new ArrayList<>();

        pool.setCreatures(emptyList);

        Creature creature = new Guppy();

        pool.addCreature(creature);

        assertThat(pool.getCreatures().get(0), is(creature));
    }

    @Test
    public void testAddCreatureNotEmptyList() {

        List<Creature> emptyList = new ArrayList<>();

        pool.setCreatures(emptyList);
        pool.addCreature(new Guppy());

        Creature creature = new Guppy();
        pool.addCreature(creature);

        assertThat(pool.getCreatures().get(1), is(creature));

    }

    @Test
    public void testAddCreatureValidCreatureSucceeds() {

        assertTrue(pool.addCreature(new Guppy()));

    }

    @Test
    public void testAddCreatureNullReturnsFalse() {

        assertFalse(pool.addCreature(null));
    }

    @Test
    public void testAddCreaturesEmptyList() {

        List<Creature> emptyList = new ArrayList<>();

        pool.setCreatures(emptyList);
        pool.addCreatures(testCreatures);

        Creature result = pool.getCreatures().get(pool.getCreatures().size() - 1);
        Creature expected = testCreatures.get(testCreatures.size() - 1);
        assertThat(result, is(expected));
    }

    @Test
    public void testAddCreaturesNotEmptyList() {

        List<Creature> emptyList = new ArrayList<>();

        pool.setCreatures(emptyList);
        pool.addCreature(new Guppy());

        pool.addCreatures(testCreatures);

        Creature result = pool.getCreatures().get(pool.getCreatures().size() - 1);
        Creature expected = testCreatures.get(testCreatures.size() - 1);
        assertThat(result, is(expected));
    }

    public void testAddCreaturesNullReturnsFalse() {

        assertFalse(pool.addCreatures(null));
    }

    @Test
    public void testAddCreaturesValidSetSucceeds() {

        assertTrue(pool.addCreatures(testCreatures));

    }

    @Test
    public void testGetPopulation() {

        assertThat(pool.getPopulation(), is(testCreatures.size()));

    }

    @Test
    public void testGetPopulationZero() {

        pool.setCreatures(new ArrayList<>());

        assertThat(pool.getPopulation(), is(0));
    }

    @Test
    public void testApplyNutrientCoefficient() {

        double expectedDead = Math.round(
                (1 - pool.getNutrientCoefficient()) * pool.getPopulation());
        final double tolerance = pool.getPopulation() * 0.1;

        assertThat((double) pool.applyNutrientCoefficient(), is(closeTo(expectedDead, tolerance)));
    }

    @Test
    public void testRemoveDeadCreaturesNoDead() {

        int removed = pool.removeDeadCreatures();

        assertThat(removed, is(0));

    }

    @Test
    public void testRemoveDeadCreaturesSomeDead() {

        int countDead = 0;

        for (Creature creature : testCreatures) {

            if (GENERATOR.nextBoolean()) {

                creature.getHealth().setAlive(false);
                countDead++;
            }
        }

        int removed = pool.removeDeadCreatures();

        assertThat(removed, is(countDead));

    }

    @Test
    public void testRemoveDeadCreaturesAllDead() {

        int countDead = 0;

        for (Creature creature : testCreatures) {

            creature.getHealth().setAlive(false);
            countDead++;
        }

        int removed = pool.removeDeadCreatures();

        assertThat(removed, is(countDead));

    }

    @Test
    public void testGetCreatureVolumeRequirementInLitres() {

        final double mLPerL = 1000.0;
        double volume = 0.0;

        for (Creature creature : testCreatures) {

            volume += creature.getVolumeNeeded() / mLPerL;
        }

        assertThat(pool.getCreatureVolumeRequirementInLitres(), is(volume));

    }

    @Test
    public void testGetCreatureVolumeRequirementInLitresExcludeDeadCreatures() {

        final double mLPerL = 1000.0;
        double volume = 0.0;

        for (Creature creature : testCreatures) {

            if (GENERATOR.nextBoolean()) {
                volume += creature.getVolumeNeeded() / mLPerL;
            } else {
                creature.getHealth().setAlive(false);
            }
        }

        assertThat(pool.getCreatureVolumeRequirementInLitres(), is(volume));

    }

    @Test
    public void testGetAverageAgeInWeeks() {

        int totalAge = 0;
        int count = 0;

        for (Creature creature : testCreatures) {

            creature.getHealth().setAge(GENERATOR.nextInt(Guppy.MAXIMUM_AGE));
            totalAge += creature.getHealth().getAge();
            count++;

        }

        double average = count > 0 ? (double) totalAge / count : 0.0;

        assertThat(pool.getAverageAgeInWeeks(), is(average));

    }

    @Test
    public void testGetAverageAgeInWeeksExcludeDeadCreatures() {

        int totalAge = 0;
        int count = 0;

        for (Creature creature : testCreatures) {

            if (GENERATOR.nextBoolean()) {
                creature.getHealth().setAge(GENERATOR.nextInt(Guppy.MAXIMUM_AGE));
                totalAge += creature.getHealth().getAge();
                count++;
            } else {
                creature.getHealth().setAlive(false);
            }

        }

        double average = count > 0 ? (double) totalAge / count : 0.0;

        assertThat(pool.getAverageAgeInWeeks(), is(average));

    }

    @Test
    public void testGetAverageHealthCoefficient() {

        double totalHealthCoefficient = 0.0;
        int count = 0;

        for (Creature creature : testCreatures) {

            creature.getHealth().setCoefficient(GENERATOR.nextDouble());
            totalHealthCoefficient += creature.getHealth().getCoefficient();
            count++;

        }

        double average = count > 0 ? totalHealthCoefficient / count : 0.0;

        assertThat(pool.getAverageHealthCoefficient(), is(average));

    }

    @Test
    public void testGetAverageHealthCoefficientExcludeDeadCreatures() {

        double totalHealthCoefficient = 0.0;
        int count = 0;

        for (Creature creature : testCreatures) {

            if (GENERATOR.nextBoolean()) {
                creature.getHealth().setCoefficient(GENERATOR.nextDouble());
                totalHealthCoefficient += creature.getHealth().getCoefficient();
                count++;
            } else {
                creature.getHealth().setAlive(false);
            }

        }

        double average = count > 0 ? totalHealthCoefficient / count : 0.0;

        assertThat(pool.getAverageHealthCoefficient(), is(average));

    }

    @Test
    public void testGetFemalePercentage() {

        int countFemale = 0;
        int countAll = 0;

        for (Creature creature : testCreatures) {

            creature.setFemale(GENERATOR.nextBoolean());
            countFemale += creature.isFemale() ? 1 : 0;
            countAll++;
        }

        double average = countAll > 0 ? (double) countFemale / countAll : 0.0;

        assertThat(pool.getFemaleProportion(), is(average));

    }

    @Test
    public void testGetFemalePercentageExcludeDeadCreatures() {

        int countFemale = 0;
        int countAll = 0;

        for (Creature creature : testCreatures) {

            if (GENERATOR.nextBoolean()) {
                creature.setFemale(GENERATOR.nextBoolean());
                countFemale += creature.isFemale() ? 1 : 0;
                countAll++;
            } else {
                creature.getHealth().setAlive(false);
            }
        }

        double average = countAll > 0 ? (double) countFemale / countAll : 0.0;

        assertThat(pool.getFemaleProportion(), is(average));

    }

    @Test
    public void testGetMedianAgeEvenAmount() {

        for (Creature creature : testCreatures) {
            creature.getHealth().setAge(GENERATOR.nextInt(Guppy.MAXIMUM_AGE - 1));
        }

        List<Integer> ages = pool.sortLivingCreatureAges();

        if (ages.size() % 2 != 0) {
            ages.add(50);
            pool.addCreature(new Guppy(50, 0, true, 0));
        }

        double medianAge = (ages.get(ages.size() / 2 - 1) + ages.get(ages.size() / 2)) / 2.0;

        assertThat(pool.getMedianAge(), is(medianAge));

    }

    @Test
    public void testGetMedianAgeOddAmount() {

        for (Creature creature : testCreatures) {
            creature.getHealth().setAge(GENERATOR.nextInt(Guppy.MAXIMUM_AGE - 1));
        }

        List<Integer> ages = pool.sortLivingCreatureAges();

        if (ages.size() % 2 == 0) {
            ages.add(50);
            pool.addCreature(new Guppy(49, 0, true, 0));
        }

        double medianAge = ages.get(ages.size() / 2 - 1);

        assertThat(pool.getMedianAge(), is(medianAge));

    }

    @Test
    public void testGetMedianAgeExcludeDeadCreatures() {

        for (Creature creature : testCreatures) {
            if (GENERATOR.nextBoolean()) {
                creature.getHealth().setAge(GENERATOR.nextInt(Guppy.MAXIMUM_AGE - 1));
            } else {
                creature.getHealth().setAlive(false);
            }
        }

        List<Integer> ages = pool.sortLivingCreatureAges();

        double medianAge;

        if (ages.size() % 2 == 0) {
            medianAge = (ages.get(ages.size() / 2 - 1) + ages.get(ages.size() / 2)) / 2.0;
        } else if (ages.size() == 1) {
            medianAge = ages.get(0);
        } else {
            medianAge = ages.get(ages.size() / 2 - 1);
        }

        assertThat(pool.getMedianAge(), is(medianAge));

    }

    @Test
    public void testAdjustForCrowdingRemovesEnough() {

        pool.setVolumeLitres(1.4);
        pool.adjustForCrowding();
        assertThat(pool.getVolumeLitres(),
                   is(greaterThanOrEqualTo(pool.getCreatureVolumeRequirementInLitres())));
    }

    @Test
    public void testAdjustForCrowdingRemovesJustEnough() {

        pool.setVolumeLitres(1.5);
        for (Creature creature : pool.getCreatures()) {
            creature.getHealth().setAge(0);
        }
        pool.adjustForCrowding();
        assertThat(pool.getVolumeLitres(),
                   is(equalTo(pool.getCreatureVolumeRequirementInLitres())));
    }

    @Test
    public void testAdjustForCrowdingRemovesCreatures() {

        pool.setVolumeLitres(1.5);
        int initial = pool.getPopulation();
        int removedCreatures = pool.adjustForCrowding().size();
        assertThat(removedCreatures, is(equalTo(initial - pool.getPopulation())));
    }

    @Test
    public void testAdjustForCrowdingReturnsRemovedCreatures() {

        pool.setVolumeLitres(1.5);
        List<Creature> removedCreatures = pool.adjustForCrowding();
        assertThat(removedCreatures, is(instanceOf(List.class)));
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
         * + ",temperatureCelsius=" + pool.getTemperature() + ",pH=" + pool.getpH() +
         * ",nutrientCoefficient=" + pool.getNutrientCoefficient() + ",identificationNumber=" +
         * pool.getIdentifier() + ",creaturesInPool=" + pool.getCreatures() +
         * ",randomNumberGenerator=" + pool.getran() + "]";))
         */
        // no getter for random number generator, cannot test from outside pool
        // class
        fail("Not yet implemented"); // TODO

    }

}
