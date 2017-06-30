package io.github.paulszefer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class StreamTest {

    private static Random generator = new Random();

    private Pool pool1;
    private Pool pool2;
    private Stream stream1;
    private Stream stream2;

    private ArrayList<Guppy> testGuppies;
    private ArrayList<Guppy> testGuppies2;

    @Before
    public void setUp() throws Exception {

        pool1 = new Pool();
        pool2 = new Pool("name", 1.0, 10.0, 7.3, 0.9);
        stream1 = new Stream(pool1, pool2);
        stream2 = new Stream(pool2, pool1);

        testGuppies = new ArrayList<>();
        testGuppies2 = new ArrayList<>();

        final int numberOfTestGuppies = 100;
        for (int i = 0; i < numberOfTestGuppies; i++) {
            testGuppies.add(new Guppy());
            testGuppies2.add(new Guppy());
        }
        pool1.setGuppiesInPool(testGuppies);
    }

    @Test
    public void getSource() throws Exception {

        assertThat(stream1.getSource(), is(pool1));
    }

    @Test
    public void getDestination() throws Exception {

        assertThat(stream1.getDestination(), is(pool2));
    }

    @Test
    public void getpH() throws Exception {

        assertThat(stream1.getpH(), is(equalTo(pool1.getpH())));
    }

    @Test
    public void getTemperature() throws Exception {

        assertThat(stream1.getTemperature(), is(equalTo(pool1.getTemperature())));
    }

    @Test
    public void setSource() throws Exception {

        stream1.setSource(pool2);
        assertThat(stream1.getSource(), is(pool2));
    }

    @Test
    public void setDestination() throws Exception {

        stream1.setDestination(pool1);
        assertThat(stream1.getDestination(), is(pool1));
    }

    @Test
    public void setpHValid() throws Exception {

        final double testPH = 3.4;
        stream1.setpH(testPH);
        assertThat(stream1.getpH(), is(equalTo(testPH)));
    }

    @Test
    public void setTemperatureValid() throws Exception {

        final double testTemp = 94.2;
        stream1.setTemperature(testTemp);
        assertThat(stream1.getTemperature(), is(equalTo(testTemp)));
    }

    @Test
    public void transportGuppiesAddedToDestination() throws Exception {

        stream1.transportGuppies(testGuppies2);
        assertTrue(pool2.getGuppiesInPool().containsAll(testGuppies2));

    }

    @Test
    public void transportGuppiesAppropriateAmountDie() throws Exception {

        final double deathProportion = 0.25;

        for (Guppy guppy : testGuppies2) {
            guppy.getHealth().setCoefficient(1 - deathProportion);
        }
        double countDied = stream1.transportGuppies(testGuppies2);
        assertThat(countDied,
                   is(closeTo(deathProportion * testGuppies2.size(), testGuppies2.size() * 0.1)));
    }

}