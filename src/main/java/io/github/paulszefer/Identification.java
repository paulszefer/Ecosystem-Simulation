package io.github.paulszefer;

/**
 * Defines an identification profile for an organism.
 * <p>
 * This profile stores information that can be used to identify a specific organism.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Identification {

    /** The classification profile of the organism. */
    private final Classification classification;

    /** The identification number of the organism. */
    private final int identifier;

    /** The generation identifier of the organism. */
    private final int generation;

    /**
     * Creates the identification profile for an organism.
     *
     * @param genus
     *         the genus of the organism
     * @param species
     *         the species of the organism
     * @param identifier
     *         the identification number of the organism
     * @param generation
     *         the generation number of the organism
     */
    public Identification(String genus, String species, int identifier, int generation) {

        this.classification = new Classification(Guppy.GENUS, Guppy.SPECIES);
        this.identifier = identifier > 0 ? identifier : 0;
        this.generation = generation > 0 ? generation : 0;
    }

    /**
     * Returns the classification profile.
     *
     * @return the classification profile
     */
    public Classification getClassification() {

        return classification;
    }

    /**
     * Returns the generation number of the organism.
     *
     * @return The generation number of the organism.
     */
    public int getGeneration() {

        return generation;
    }

    /**
     * Returns the identification number of the organism.
     *
     * @return The identification number of the organism.
     */
    public int getIdentifier() {

        return identifier;
    }

    /**
     * Returns a copy of the identification profile.
     *
     * @return a copy of the identification profile
     */
    public Identification copy() {

        return new Identification(classification.getGenus(), classification.getSpecies(),
                                  identifier, generation);
    }
}