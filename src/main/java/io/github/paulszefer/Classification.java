package io.github.paulszefer;

/**
 * Defines the classification profile of an organism.
 * <p>
 * Organisms are classified by:
 * <ul>
 * <li>kingdom</li>
 * <li>phylum</li>
 * <li>class</li>
 * <li>order</li>
 * <li>family</li>
 * <li>genus</li>
 * <li>species</li>
 * </ul>
 * <p>
 * For simplicity, this implementation only includes the genus and species of the organism.
 *
 * @author Paul Szefer
 * @version 1.0
 */
public class Classification {

    /** Genus of the organism. */
    private final String genus;

    /** Species of the organism. */
    private final String species;

    /**
     * Creates the classification profile for an organism.
     *
     * @param genus
     *         the genus of the organism
     * @param species
     *         the species of the organism
     */
    public Classification(String genus, String species) {

        if (genus != null && !genus.replace(" ", "").equals("")) {
            this.genus = genus.replace(" ", "").substring(0, 1).toUpperCase()
                    + genus.replace(" ", "").substring(1).toLowerCase();
        } else {
            throw new IllegalArgumentException(genus == null ? "Null" : "No" + "genus given");
        }

        if (species != null && !species.replace(" ", "").equals("")) {
            this.species = species.replace(" ", "").toLowerCase();
        } else {
            throw new IllegalArgumentException(species == null ? "Null" : "No" + "species given");
        }
    }

    /**
     * Returns the genus of the guppy.
     *
     * @return The genus of the guppy.
     */
    public String getGenus() {

        return genus;
    }

    /**
     * Returns the species of the guppy.
     *
     * @return The species of the guppy.
     */
    public String getSpecies() {

        return species;
    }

    @Override
    public String toString() {

        return "genus=" + getGenus() + ",species=" + getSpecies();
    }
}