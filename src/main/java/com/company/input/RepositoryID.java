package com.company.input;

public class RepositoryID {
  private final String owner;
  private final String repoName;

  public RepositoryID(String owner, String repoName)
  {
    this.owner = owner;
    this.repoName = repoName;
  }

  public String getOwner()
  {
    return owner;
  }

  public String getRepoName()
  {
    return repoName;
  }
}
