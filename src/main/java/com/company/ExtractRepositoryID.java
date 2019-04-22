package com.company;

class ExtractRepositoryID {
    static RepositoryID get(String arg) throws IllegalArgumentException{
        String[] consoleArg = arg.split("/");
        RepositoryID repositoryID;
        if (consoleArg.length == 2) {
            repositoryID = new RepositoryID(consoleArg[0], consoleArg[1]);
        } else if (consoleArg.length >= 4 && (consoleArg[0].equals("http:") || consoleArg[0].equals("https:"))) {
            repositoryID = new RepositoryID(consoleArg[3], consoleArg[4]);
        } else if (consoleArg.length >= 3 && (consoleArg[0].equals("www.github.com") || consoleArg[0].equals("github.com"))) {
            repositoryID = new RepositoryID(consoleArg[1], consoleArg[2]);
        } else {
           throw new IllegalArgumentException();
        }
        return repositoryID;
    }
}
