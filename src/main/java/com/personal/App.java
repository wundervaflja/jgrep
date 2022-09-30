package com.personal;

import net.sourceforge.argparse4j.inf.Namespace;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Namespace ns = Cli.parseCliArguments(args);
        String searchString = ns.get("searchString");
        String directory = ns.get("directory");
        Boolean isRecursive = ns.get("recursive");
        Boolean isIgnorecase = ns.get("ignore-case");
        GrepBuilder grepBuilder = new GrepBuilder();
        Grep grep = grepBuilder
            .setStringToSearch(searchString)
            .setDirectoryToSearchIn(directory)
            .setIsIgnorecase(isIgnorecase)
            .setIsRecursive(isRecursive)
            .build();
        grep.runner();
    }
}
