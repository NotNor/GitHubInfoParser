package com.company;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

public class GithubDataRequester {
    public static JsonStructure get(RepositoryID request) throws Exception {
        try {
            String link = linkBuilder(request);
            URL url = new URL(link);
            HttpsURLConnection gitHubRestApi = (HttpsURLConnection) url.openConnection();
            JsonReader gitHubRestApiReader = Json.createReader(gitHubRestApi.getInputStream());
            return gitHubRestApiReader.read();
        } catch (Exception e) {
            throw e;
        }
    }

    public static String linkBuilder(RepositoryID request) {
        return "Https://api.github.com/repos/" + request.getOwner() + "/" + request.getRepoName();
    }
}
