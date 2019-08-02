package com.company.export;

public class OutputRecord {
  private final String owner;
  private final String repoName;
  private final String language;
  private final Integer forksNum;
  private final Integer starsNum;
  private final String licenseName;
  private final String licenseURL;

  public OutputRecord(String owner, String repoName, String language,
                       Integer forksNum, Integer starsNum, String licenseName, String licenseURL)
  {
    this.owner = owner;
    this.repoName = repoName;
    this.language = language;
    this.forksNum = forksNum;
    this.starsNum = starsNum;
    this.licenseName = licenseName;
    this.licenseURL = licenseURL;
  }

  @Override
  public String toString()
  {
    return this.owner + "," +
        this.repoName + "," +
        this.language + "," +
        this.forksNum + "," +
        this.starsNum + "," +
        this.licenseName + "," +
        this.licenseURL;
  }


  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    else return this.toString().equals(obj.toString());
  }

  public String getOwner()
  {
    return owner;
  }

  public String getRepoName()
  {
    return repoName;
  }

  public String getLanguage()
  {
    return language;
  }

  public Integer getForksNum()
  {
    return forksNum;
  }

  public Integer getStarsNum()
  {
    return starsNum;
  }

  public String getLicenseName()
  {
    return licenseName;
  }

  public String getLicenseURL()
  {
    return licenseURL;
  }
}
