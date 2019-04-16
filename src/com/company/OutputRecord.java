package com.company;

import javax.json.JsonString;
import javax.json.JsonStructure;

public class OutputRecord {
    private String owner;
    private String repoName;
    private String language;
    private String forksNum;
    private String starsNum;
    private String licenseName;
    private String licenseURL;

    public OutputRecord(String owner, String repoName, JsonStructure userRepoTree) {
        this.owner = owner;
        this.repoName = repoName;
        this.language = ((JsonString)userRepoTree.getValue("/language")).getString();
        this.forksNum = (userRepoTree.getValue("/forks")).toString();
        this.starsNum = (userRepoTree.getValue("/stargazers_count")).toString();
        this.licenseName = ((JsonString)userRepoTree.getValue("/license/name")).getString();
        this.licenseURL = ((JsonString)userRepoTree.getValue("/license/url")).getString();
    }

}
