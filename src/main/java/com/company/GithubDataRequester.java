package com.company;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.net.URL;
import java.net.URLConnection;

public class GithubDataRequester {
    public static JsonStructure get(RepositoryID request) throws Exception {
            String link = linkBuilder(request);
            URL url = new URL(link);
            URLConnection gitHubRestApi = url.openConnection();
            JsonReader gitHubRestApiReader = Json.createReader(gitHubRestApi.getInputStream());
            return gitHubRestApiReader.read();
    }

    private static String linkBuilder(RepositoryID request) {
        return "Https://api.github.com/repos/" + request.getOwner() + "/" + request.getRepoName();
    }
}
