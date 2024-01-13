package com.example.pokedex;

import com.example.pokedex.controllers.PokedexController;
import com.example.pokedex.models.Pokemon;
import com.example.pokedex.services.LocalPokemonDataService;
import com.example.pokedex.services.PokeApiDataService;
import com.example.pokedex.services.PokemonDataService;
import com.example.pokedex.views.PokedexView;
import com.example.pokedex.utilities.OutputFormat;

import org.apache.commons.cli.*;

import java.nio.charset.StandardCharsets;

/**
 * The main class for the Pokedex application.
 */
public class Pokedex {

    private enum DataSource { WEB_API, LOCAL_DATABASE }

    private static DataSource dataSource = DataSource.WEB_API;
    private static String databasePath;
    private static int pokemonId;
    private static OutputFormat outputFormat = OutputFormat.TEXT; // Default format is text

    public static void main(String[] args) throws ParseException {
        /* Parse the command line arguments, this is done for you, keep this code block */
        try {
            parseCommandLineArguments(args);
        } catch (PokemonCommandLineParsingException e) {
            System.err.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("./Pokedex <PokemonId> [-d|--database <databaseFile>] [-f|--format <format>]", e.getOptions());
            System.exit(0);
        }

        // Create instances of data service, view, and controller
        PokemonDataService dataService;
        if (dataSource == DataSource.WEB_API) {
            dataService = new PokeApiDataService();
        } else if (dataSource == DataSource.LOCAL_DATABASE) {
            dataService = new LocalPokemonDataService(databasePath);
        } else {
            System.err.println("Error: Invalid data source.");
            return;
        }

        PokedexView pokedexView = new PokedexView();
        PokedexController controller = new PokedexController(dataService, pokedexView);

        // Retrieve Pokemon by ID using the existing getPokemonById method
        controller.displayPokemonInfo(pokemonId, databasePath, outputFormat);
    }

    /**
     * Parses the command line arguments and initializes application parameters.
     *
     * @param args The command line arguments passed to the application.
     * @throws PokemonCommandLineParsingException If there is an error parsing the command line arguments.
     * @throws ParseException                    If there is an error during command line argument parsing.
     */
    public static void parseCommandLineArguments(String[] args) throws PokemonCommandLineParsingException, ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("d", "database", true, "Path to a SQLite database containing pokemons");
        options.addOption("f", "format", true, "Specify the output format, between 'text', 'html' and 'csv'. By default 'text'");

        // parse the command line arguments
        CommandLine line = parser.parse(options, args);
        if (line.hasOption("d")) {
            dataSource = DataSource.LOCAL_DATABASE;
            databasePath = line.getOptionValue("d");
        }

        if (line.hasOption("f")) {
            String formatArgValue = line.getOptionValue("f");
            if (formatArgValue.equals("html")) {
                outputFormat    = OutputFormat.HTML;
            } else if (formatArgValue.equals("csv")) {
                outputFormat = OutputFormat.CSV;
            } else if (formatArgValue.equals("text")) {
                outputFormat = OutputFormat.TEXT;
            } else {
                throw new PokemonCommandLineParsingException("Invalid value for the option -f/--format", options);
            }
        }

        // Get pokemon ID from remaining arguments
        String[] remainingArgs = line.getArgs();
        if (remainingArgs.length < 1) {
            throw new PokemonCommandLineParsingException("You must provide a pokemon ID", options);
        }
        try {
            pokemonId = Integer.parseInt(remainingArgs[0]);
        } catch (NumberFormatException e) {
            throw new PokemonCommandLineParsingException("'" + remainingArgs[0] + "' is not a valid pokemon ID", options);
        }
    }

    /**
     * Custom exception class for handling command line parsing errors.
     */
    static class PokemonCommandLineParsingException extends Exception {
        private Options options;

        public PokemonCommandLineParsingException(String msg, Options options) {
            super(msg);
            this.options = options;
        }

        public Options getOptions() {
            return options;
        }
    }
}
