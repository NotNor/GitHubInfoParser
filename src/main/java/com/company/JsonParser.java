package com.company;

import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;

public class JsonParser {

    public static OutputRecord parseData(String owner, String repoName, JsonStructure repoData) {

        String language = getValue("/language", repoData);
        String forksNum = getValue("/forks", repoData);
        String starsNum = getValue("/stargazers_count", repoData);
        String licenseName = getValue("/license/name", repoData);
        String licenseURL = getValue("/license/url", repoData);

        return new OutputRecord(owner, repoName, language, forksNum, starsNum, licenseName, licenseURL);
    }

    private static String getValue(String pointer, JsonStructure repoData) {
        try {
            if (repoData.getValue(pointer).getValueType() == JsonValue.ValueType.STRING) {
                return ((JsonString) repoData.getValue(pointer)).getString();
            } else {
                return repoData.getValue(pointer).toString();
            }
        } catch (Exception e) {
           return null;
        }
    }
}
