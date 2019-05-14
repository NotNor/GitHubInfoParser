package com.company;

import com.company.export.CSVOutputWriter;
import com.company.export.OutputRecord;
import com.company.input.InputParser;
import com.company.input.RepositoryID;

import javax.json.JsonStructure;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Program {
    private final List<OutputRecord> output = new LinkedList<>();
    private final Map<RepositoryID, JsonStructure> reposData = new HashMap<>();

    void run(String[] args) throws Exception {
    InputParser inputParser = new InputParser();
    inputParser.parse(args);

        List<RepositoryID> repositoryIDsOfRequestedRepos = inputParser.getRepositoryIDs();


        GetDataFromGithub.download(repositoryIDsOfRequestedRepos,reposData);

        for (Map.Entry<RepositoryID, JsonStructure> entry : reposData.entrySet()) {
            String owner = entry.getKey().getOwner();
            String repoName = entry.getKey().getRepoName();
            OutputRecord record = JsonParser.parseData(owner, repoName, entry.getValue());
            output.add(record);
        }

        try {
            CSVOutputWriter outputWriter = new CSVOutputWriter();
            outputWriter.writeOutput(output);
        } catch (Exception e) {
            throw new IOException("Failed to write file to disk");
        }



    }
}
