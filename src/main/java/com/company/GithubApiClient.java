package com.company;

import com.company.input.RepositoryID;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.net.URL;
import java.net.URLConnection;

class GithubApiClient {
    static JsonStructure downloadJsonOf(RepositoryID request) throws Exception {
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
