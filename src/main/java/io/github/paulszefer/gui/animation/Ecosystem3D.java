package io.github.paulszefer.gui.animation;

import io.github.paulszefer.sim.Ecosystem;
import io.github.paulszefer.sim.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A group that contains all 3D ecosystem objects for the animation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Ecosystem3D extends Xform {

    /** A random number generator. */
    private static final Random GENERATOR = new Random();

    /** A list of the pool3Ds in this Ecosystem3D. */
    private List<Pool3D> pool3Ds = new ArrayList<>();

    /**
     * Creates an empty group.
     * <p>
     * Eventually, this might display an instruction menu or some other information.
     */
    public Ecosystem3D() {
        System.out.println("Please load a file");
    }

    /**
     * Creates the group populated with 3D boxes representing the pools retrieved from the given
     * ecosystem.
     * <p>
     * Each pool will be filled with 3D spheres representing the creatures found in that pool.
     *
     * @param ecosystem
     *         the current simulation state
     */
    public Ecosystem3D(Ecosystem ecosystem) {
        generateEcosystem(ecosystem);
    }

    /**
     * Generates a 3D representation of the current simulation state.
     *
     * @param ecosystem
     *         the current simulation state
     */
    private void generateEcosystem(Ecosystem ecosystem) {
        for (int i = 0; i < ecosystem.getPools().size(); i++) {
            Pool pool = ecosystem.getPools().get(i);
            Xform poolContainer = new Xform();

            // create the creatures
            int numberOfCreatures = pool.getPopulation() < Pool3D.MAX_ANIMATED_CREATURES
                                    ? pool.getPopulation()
                                    : Pool3D.MAX_ANIMATED_CREATURES;
            List<Creature3D> creature3Ds = new ArrayList<>();
            for (int j = 0; j < numberOfCreatures; j++) {
                Creature3D creature3D = new Creature3D(
                        pool.getCreatures().get(GENERATOR.nextInt(pool.getCreatures().size())));
                creature3Ds.add(creature3D);
            }
            while (creature3Ds.size() < Pool3D.MAX_ANIMATED_CREATURES) {
                creature3Ds.add(new Creature3D(null));
            }

            // create the pool
            Pool3D pool3D = new Pool3D((i - 1) * -1, creature3Ds);
            pool3Ds.add(pool3D);

            poolContainer.getChildren().addAll(creature3Ds);
            poolContainer.getChildren().add(pool3D);
            getChildren().add(poolContainer);
        }
    }

    /**
     * Updates the group with the current simulation state.
     * <p>
     * The group is first cleared, then a new 3D representation is created for the current
     * simulation state.
     *
     * @param ecosystem
     *         the current simulation state
     * @param initialUpdate
     *         whether this is the first update to the GUI
     */
    public void update(Ecosystem ecosystem, boolean initialUpdate) {
        if (initialUpdate) {
            getChildren().clear();
            pool3Ds.clear();
            generateEcosystem(ecosystem);
        } else {
            for (int i = 0; i < pool3Ds.size(); i++) {
                pool3Ds.get(i).update(ecosystem.getPools().get(i));
            }
        }
    }
}