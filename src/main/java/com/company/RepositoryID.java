package com.company;

public class RepositoryID {
        private String owner;
        private String repoName;

    public RepositoryID(String owner, String repoName) {
        this.owner = owner;
        this.repoName = repoName;
    }

    public String getOwner() {
        return owner;
    }

    public String getRepoName() {
        return repoName;
    }
}
