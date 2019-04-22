package com.company;

import javax.json.JsonString;
import javax.json.JsonStructure;

public class JsonParser {

    public static OutputRecord parseData(String owner, String repoName, JsonStructure repoData) {
        String language = ((JsonString) repoData.getValue("/language")).getString();
        String forksNum = (repoData.getValue("/forks")).toString();
        String starsNum = (repoData.getValue("/stargazers_count")).toString();
        String licenseName = ((JsonString) repoData.getValue("/license/name")).getString();
        String licenseURL = ((JsonString) repoData.getValue("/license/url")).getString();

        return new OutputRecord(owner, repoName, language, forksNum, starsNum, licenseName, licenseURL);
    }
}
