package io.github.paulszefer;

import java.util.InputMismatchException;
import java.util.List;

/**
 * Defines a creature that lives in a habitat.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public interface Creature extends Comparable {

    /**
     * Returns the identification profile.
     *
     * @return the identification profile
     */
    Identification getIdentification();

    /**
     * Returns the health profile.
     *
     * @return the health profile
     */
    Health getHealth();

    /**
     * Returns whether the guppy is female.
     *
     * @return True if the guppy is female; false otherwise.
     */
    boolean isFemale();

    /**
     * Sets the identification profile.
     *
     * @param identification
     *         the identification
     */
    void setIdentification(Identification identification);

    /**
     * Sets whether the creature is female.
     *
     * @param isFemale
     *         True if the creature is female; false otherwise.
     */
    void setFemale(boolean isFemale);

    /**
     * Gets the volume of water in mL that the creature needs according to its age.
     *
     * @return Volume of water in mL needed for the creature or 0.0 if dead.
     */
    double getVolumeNeeded();

    /**
     * Spawns offspring if the required conditions are met.
     *
     * @return a List of spawned offspring or null if this creature is unable to reproduce
     */
    List<Creature> spawn();

    /**
     * Creates and returns a copy of this Creature.
     *
     * @return a cloned copy of this creature
     */
    Creature copy();

    /**
     * Compares this Creature to the given object.
     *
     * @param other
     *         an object to compare this creature to
     *
     * @return 1 if this creature is greater; -1 if it is lesser; or 0 if they are equal
     *
     * @throws NullPointerException
     *         if the other object is null
     * @throws InputMismatchException
     *         if the other object is not of type Creature
     */
    int compareTo(Object other);

    /**
     * Returns a string representation of the creature containing all of its attributes.
     *
     * @return A string containing all of the attributes of the creature.
     */
    String toString();
}
