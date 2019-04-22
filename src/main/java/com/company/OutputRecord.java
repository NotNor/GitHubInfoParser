package com.company;

public class OutputRecord {
    private String owner;
    private String repoName;
    private String language;
    private String forksNum;
    private String starsNum;
    private String licenseName;
    private String licenseURL;

    public OutputRecord(String owner, String repoName, String language,
                        String forksNum, String starsNum, String licenseName, String licenseURL) {
        this.owner = owner;
        this.repoName = repoName;
        this.language = language;
        this.forksNum = forksNum;
        this.starsNum = starsNum;
        this.licenseName = licenseName;
        this.licenseURL = licenseURL;
    }

    @Override
    public String toString() {
        return this.owner + this.repoName + this.language + this.forksNum + this.starsNum +
                this.licenseName + this.licenseURL;
    }

}
