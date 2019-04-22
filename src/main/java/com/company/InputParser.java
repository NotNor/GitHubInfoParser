package com.company;

import java.util.LinkedList;
import java.util.List;

public class InputParser {
    private List<RepositoryID> repositoryIDs = new LinkedList<>();

    public InputParser(String[] consoleArgs) throws Exception {

        for (String consoleArg : consoleArgs) {
            RepositoryID repositoryID = ExtractRepositoryID.get(consoleArg);
            repositoryIDs.add(repositoryID);
        }
    }

    public List<RepositoryID> getRepositoryIDs() {
        return repositoryIDs;
    }
}

