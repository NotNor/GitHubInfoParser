package com.company.input;

import java.util.LinkedList;
import java.util.List;

public class InputParser {
    private final List<RepositoryID> repositoryIDs = new LinkedList<>();

    public InputParser(String[] consoleArgs) throws Exception {
        for (String consoleArg : consoleArgs) {
            try {
                RepositoryID repositoryID = ExtractRepositoryID.get(consoleArg);
                repositoryIDs.add(repositoryID);

            } catch (Exception e) {
                throw new Exception("Invalid argument: " + consoleArg + "\n");
            }
        }
    }

    public List<RepositoryID> getRepositoryIDs() {
        return repositoryIDs;
    }
}

