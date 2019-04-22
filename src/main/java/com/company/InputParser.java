package com.company;

import java.util.LinkedList;
import java.util.List;

public class InputParser {
    private List<RepositoryID> ownerRepoPairs = new LinkedList<>();

    public InputParser(String[] consoleArgs) throws Exception {

        for (String consoleArg : consoleArgs) {
            RepositoryID repositoryID = ExtractOwnerAndNameOfRepo.get(consoleArg);
            ownerRepoPairs.add(repositoryID);
        }
    }

    public List<RepositoryID> getOwnerRepoPairs() {
        return ownerRepoPairs;
    }
}

