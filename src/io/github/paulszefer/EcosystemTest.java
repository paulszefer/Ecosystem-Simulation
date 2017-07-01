package io.github.paulszefer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class EcosystemTest {

    private Ecosystem ecosystem;
    private Pool pool1;
    private Pool pool2;
    private Stream stream1;
    private Stream stream2;

    @Before
    public void setUp() throws Exception {

        ecosystem = new Ecosystem();

        pool1 = new Pool();
        pool2 = new Pool("name", 1.0, 10.0, 7.3, 0.9);
        stream1 = new Stream(pool1, pool2);
        stream2 = new Stream(pool2, pool1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEcosystemCreatesCorrectType() {

        assertThat(ecosystem, is(instanceOf(Ecosystem.class)));

    }

    @Test
    public void testEcosystemPoolsIsCorrectType() {

        assertThat(ecosystem.getPools(), is(instanceOf(ArrayList.class)));

    }

    @Test
    public void testEcosystemPoolsHasCorrectValue() {

        assertThat(ecosystem.getPools().size(), is(equalTo(0)));

    }

    @Test
    public void testEcosystemStreamsIsCorrectType() {

        assertThat(ecosystem.getStreams(), is(instanceOf(ArrayList.class)));

    }

    @Test
    public void testEcosystemStreamsHasCorrectValue() {

        ecosystem = new Ecosystem();
        assertThat(ecosystem.getStreams().size(), is(equalTo(0)));

    }

    @Test
    public void testGetPools() {

        // identical to testEcosystemPoolsIsCorrectType
        assertThat(ecosystem.getPools(), is(instanceOf(ArrayList.class)));

    }

    @Test
    public void testGetStreams() {

        // identical to testEcosystemStreamsIsCorrectType
        assertThat(ecosystem.getStreams(), is(instanceOf(ArrayList.class)));

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
    public void testSetStreams() {

        ArrayList<Stream> streams = new ArrayList<>();
        streams.add(stream1);
        streams.add(stream2);
        ecosystem.setStreams(streams);

        assertThat(ecosystem.getStreams(), is(equalTo(streams)));

    }

    @Test
    public void testAddPoolToEmptyList() {

        ecosystem.addPool(pool2);

        assertThat(ecosystem.getPools().get(0), is(pool2));
    }

    @Test
    public void testAddPoolToEmptyListCreatesNoStream() {

        ecosystem.addPool(pool2);

        assertThat(ecosystem.getStreams().size(), is(0));
    }

    @Test
    public void testAddPoolToNonEmptyList() {

        ecosystem.addPool(pool1);
        ecosystem.addPool(pool2);

        assertThat(ecosystem.getPools().get(1), is(pool2));

    }

    @Test
    public void testAddPoolToNonEmptyListCreatesStream() {

        ecosystem.addPool(pool1);
        ecosystem.addPool(pool2);

        assertThat(ecosystem.getStreams().get(0), is(equalTo(new Stream(pool1, pool2))));

    }

    @Test
    public void testResetPools() {

        ecosystem.resetPools();

        assertThat(ecosystem.getPools().size(), is(equalTo(0)));

    }

    @Test
    public void testResetStreams() {

        ecosystem.resetStreams();

        assertThat(ecosystem.getStreams().size(), is(equalTo(0)));

    }

    @Test
    public void testGetGuppyPopulationNoPools() {

        assertThat(ecosystem.getCreaturePopulation(), is(equalTo(0)));

    }

    @Test
    public void testGetGuppyPopulationOnePool() {

        final int numberOfGuppies = 10;

        for (int i = 0; i < numberOfGuppies; i++) {

            pool1.addCreature(new Guppy());

        }

        ecosystem.addPool(pool1);

        assertThat(ecosystem.getCreaturePopulation(), is(equalTo(numberOfGuppies)));

    }

    @Test
    public void testGetGuppyPopulationMoreThanOnePool() {

        final int numberOfGuppies1 = 10;

        for (int i = 0; i < numberOfGuppies1; i++) {

            pool1.addCreature(new Guppy());

        }

        final int numberOfGuppies2 = 15;

        for (int i = 0; i < numberOfGuppies2; i++) {

            pool2.addCreature(new Guppy());

        }

        ecosystem.addPool(pool1);
        ecosystem.addPool(pool2);

        assertThat(ecosystem.getCreaturePopulation(),
                   is(equalTo(numberOfGuppies1 + numberOfGuppies2)));

    }

    @Test
    public void testAdjustForCrowdingGuppiesDieIfNoValidStreamsFound() {

        fail("Cannot test from outside the method.");
    }

    // TODO - adjustForCrowding
    // adds correct dead guppies back to final pool in the chain

    @Test
    public void testGetRandomStreamSelectsValidStream1() {

        ecosystem.addStream(stream1);
        ecosystem.addStream(stream2);

        assertThat(ecosystem.getRandomStream(pool1), is(equalTo(stream1)));
    }

    @Test
    public void testGetRandomStreamSelectsValidStream2() {

        ecosystem.addStream(stream1);
        ecosystem.addStream(stream2);

        assertThat(ecosystem.getRandomStream(pool2), is(stream2));
    }

    @Test
    public void testGetRandomStreamReturnsNullIfNoValidStreamsFound() {

        stream1.setSource(pool1);
        stream2.setSource(pool1);

        ecosystem.addStream(stream1);
        ecosystem.addStream(stream2);

        assertNull(ecosystem.getRandomStream(pool2));
    }

}
