package io.github.paulszefer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EcosystemTest {

    private Ecosystem ecosystem;
    private Pool pool1;
    private Pool pool2;

    @Before
    public void setUp() throws Exception {

        ecosystem = new Ecosystem();

        pool1 = new Pool();
        pool2 = new Pool("name", 1.0, 10.0, 7.3, 0.9);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEcosystemCreatesCorrectType() {

        assertThat(ecosystem, is(instanceOf(Ecosystem.class)));

    }

    @Test
    public void testEcosystemAssignsInstanceVariableOfCorrectType() {

        assertThat(ecosystem.getPools(), is(instanceOf(ArrayList.class)));

    }

    @Test
    public void testEcosystemAssignsInstanceVariableWithCorrectValue() {

        assertThat(ecosystem.getPools().size(), is(equalTo(0)));

    }

    @Test
    public void testGetPools() {

        // identical to testEcosystemAssignsInstanceVariableOfCorrectType
        assertThat(ecosystem.getPools(), is(instanceOf(ArrayList.class)));

    }

    @Test
    public void testSetPools() {

        ArrayList<Pool> pools = new ArrayList<>();
        pools.add(pool1);
        pools.add(pool2);
        ecosystem.setPools(pools);

        assertThat(ecosystem.getPools(), is(equalTo(pools)));

    }

    @Test
    public void testAddPool() {

        ecosystem.addPool(pool2);

        assertThat(ecosystem.getPools().get(0), is(pool2));

    }

    @Test
    public void testAddPoolToNonEmptyList() {

        ecosystem.addPool(pool1);
        ecosystem.addPool(pool2);

        assertThat(ecosystem.getPools().get(1), is(pool2));

    }

    @Test
    public void testReset() {

        ecosystem.reset();

        assertThat(ecosystem.getPools().size(), is(equalTo(0)));

    }

    @Test
    public void testGetGuppyPopulationNoPools() {

        assertThat(ecosystem.getGuppyPopulation(), is(equalTo(0)));

    }

    @Test
    public void testGetGuppyPopulationOnePool() {

        final int numberOfGuppies = 10;

        for (int i = 0; i < numberOfGuppies; i++) {

            pool1.addGuppy(new Guppy());

        }

        ecosystem.addPool(pool1);

        assertThat(ecosystem.getGuppyPopulation(), is(equalTo(numberOfGuppies)));

    }

    @Test
    public void testGetGuppyPopulationMoreThanOnePool() {

        final int numberOfGuppies1 = 10;

        for (int i = 0; i < numberOfGuppies1; i++) {

            pool1.addGuppy(new Guppy());

        }

        final int numberOfGuppies2 = 15;

        for (int i = 0; i < numberOfGuppies2; i++) {

            pool2.addGuppy(new Guppy());

        }

        ecosystem.addPool(pool1);
        ecosystem.addPool(pool2);

        assertThat(ecosystem.getGuppyPopulation(),
                is(equalTo(numberOfGuppies1 + numberOfGuppies2)));

    }

}
