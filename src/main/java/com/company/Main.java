package com.company;

import javax.json.JsonStructure;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Main {

public static void main(String[] args) {
    List<RepositoryID> repositoryIDsOfRequestedRepos;
    List<OutputRecord> output = new LinkedList<>();
    Map<RepositoryID,JsonStructure> reposData = new HashMap<>();

    if (args == null) {
        System.out.println("No input argument(s) passed.\n");
        printHelp();
        return;
    }

    try {
        InputParser inputParser = new InputParser(args);
        repositoryIDsOfRequestedRepos = inputParser.getRepositoryIDs();
    } catch (Exception e) {
        System.out.println("Invalid input.\n");
        printHelp();
        return;
    }

    for (RepositoryID repositoryID : repositoryIDsOfRequestedRepos) {
        try {
            JsonStructure requestedData = GithubDataRequester.get(repositoryID);
            reposData.put(repositoryID, requestedData);
        } catch (FileNotFoundException e) {
            System.out.println("There is no such repo: " + repositoryID.getOwner() + "/" + repositoryID.getRepoName());
        } catch (Exception e) {
            System.out.println("Connection problem");
            return;
        }
    }

    for (Map.Entry<RepositoryID, JsonStructure> entry: reposData.entrySet()) {
        String owner = entry.getKey().getOwner();
        String repoName = entry.getKey().getRepoName();
        OutputRecord record = JsonParser.parseData(owner, repoName, entry.getValue());
        output.add(record);
    }

    try {
        new OutputWriter(output);
    } catch (Exception e) {
        System.out.println("Failed to write file to disk");
    }

    for (OutputRecord record:output
         ) {
        System.out.println(record.toString());
    }
}

    private static void printHelp() {
        System.out.println("Usage:\tGitHubInfoParser [URL]...\n" +
                "\tor\tGitHubInfoParser [owner/repo]...");
    }
}



