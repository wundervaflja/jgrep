package com.personal;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;


public class Cli {

    public static Namespace parseCliArguments(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("JGrep").build()
            .defaultHelp(true)
            .description("Java implementation for grep function.");
        parser.addArgument("search-string")
            .dest("searchString")
            .metavar("S")
            .type(String.class)
            .help("String to search");
        parser.addArgument("-d", "--directory")
            .type(String.class)
            .setDefault(".")
            .help("Drirectory to search in");
        parser.addArgument("-r", "--recursive")
            .type(Arguments.booleanType())
            .setDefault(false)
            .help("Recursive or not search");
        parser.addArgument("-i", "--ignore-case")
            .type(Arguments.booleanType())
            .dest("ignoreCase")
            .setDefault(false)
            .help("To ignore case or not");

        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        return ns;
    }
}
