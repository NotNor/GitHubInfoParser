package notnor.input;

import lombok.Getter;

@Getter
public class RepositoryID {
  private final String owner;
  private final String repoName;

  public RepositoryID(String owner, String repoName)
  {
    this.owner = owner;
    this.repoName = repoName;
  }

}
