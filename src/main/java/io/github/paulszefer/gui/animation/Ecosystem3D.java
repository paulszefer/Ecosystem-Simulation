package io.github.paulszefer.gui.animation;

import io.github.paulszefer.sim.Ecosystem;
import io.github.paulszefer.sim.Pool;

/**
 * A group that contains all 3D ecosystem objects for the animation.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Ecosystem3D extends Xform {

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

            // create the pool
            final double poolSize = 100;
            final double poolWidth = poolSize;
            final double poolHeight = poolSize;
            final double poolDepth = poolSize;
            final double poolGap = poolWidth / 2;
            Pool3D pool3D = new Pool3D(poolWidth, poolHeight, poolDepth,
                                       (i - 1) * -1 * (poolWidth + poolGap));

            // create the creatures
            final int maxCreatures = 200;
            final double creatureRadius = poolSize / 50;
            int numberOfCreatures =
                    pool.getPopulation() < maxCreatures ? pool.getPopulation() : maxCreatures;
            for (int j = 0; j < numberOfCreatures; j++) {
                Creature3D creature3D = new Creature3D(pool.getCreatures().get(j), creatureRadius,
                                                       pool3D);
                poolContainer.getChildren().add(creature3D);
            }
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
     */
    public void update(Ecosystem ecosystem) {
        // maybe store elsewhere to prevent recreating, particularly when stepping backwards
        getChildren().clear();
        generateEcosystem(ecosystem);
    }
}