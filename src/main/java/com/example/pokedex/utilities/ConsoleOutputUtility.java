package com.example.pokedex.utilities;

/**
 * A utility class for generating console outputs based on specified formats.
 */
public class ConsoleOutputUtility {
    private OutputFormat outputFormat;
    private MultipleFormatGenerator formatsGenerator;

    /**
     * Constructs a ConsoleOutputUtility with the specified output format and format generator.
     *
     * @param outputFormat     The desired output format.
     * @param formatsGenerator The generator for multiple output formats.
     */
    public ConsoleOutputUtility(OutputFormat outputFormat, MultipleFormatGenerator formatsGenerator) {
        this.outputFormat = outputFormat;
        this.formatsGenerator = formatsGenerator;
    }

    /**
     * Generates and displays console output based on the specified format.
     * The actual printing is typically done in the PokedexView class.
     * This method can remain empty since the printing is done in the PokedexView.
     */
    public void makeOutput() {
        // This method can remain empty since the printing is done in the PokedexView.
    }
}
