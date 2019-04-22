package com.company;

import javax.json.JsonString;
import javax.json.JsonStructure;

public class JsonParser {

    public static OutputRecord parseData(String owner, String repoName, JsonStructure repoData) {

        String language = null;
        try {
            language = ((JsonString) repoData.getValue("/language")).getString();
        } catch (Exception e) {
        }

        String forksNum = null;
        try {
            forksNum = (repoData.getValue("/forks")).toString();
        } catch (Exception e) {
        }

        String starsNum = null;
        try {
            starsNum = (repoData.getValue("/stargazers_count")).toString();
        } catch (Exception e) {
        }

        String licenseName = null;
        try {
            licenseName = ((JsonString) repoData.getValue("/license/name")).getString();
        } catch (Exception e) {
        }

        String licenseURL = null;
        try {
            licenseURL = ((JsonString) repoData.getValue("/license/url")).getString();
        } catch (Exception e) {
        }


        return new OutputRecord(owner, repoName, language, forksNum, starsNum, licenseName, licenseURL);
    }
}
