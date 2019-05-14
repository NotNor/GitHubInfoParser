package com.company.export;

public class OutputRecord {
    private final String owner;
    private final String repoName;
    private final String language;
    private final String forksNum;
    private final String starsNum;
    private final String licenseName;
    private final String licenseURL;

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
        return this.owner + "," +
                this.repoName + "," +
                this.language + "," +
                this.forksNum + "," +
                this.starsNum + "," +
                this.licenseName + "," +
                this.licenseURL;
    }


    @Override
    public boolean equals(Object obj) {
       if (this == obj) return true;
       else return this.toString().equals(obj.toString());
    }
}
