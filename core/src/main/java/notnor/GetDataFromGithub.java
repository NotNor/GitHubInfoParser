package notnor;

import notnor.input.RepositoryID;

import javax.json.JsonStructure;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class GetDataFromGithub {
  public static void getData(List<RepositoryID> repositoryIDsOfRequestedRepos,
                      ConcurrentHashMap<RepositoryID, JsonStructure> reposData)
  {

    CountDownLatch countDownLatch = new CountDownLatch(repositoryIDsOfRequestedRepos.size());

    for (RepositoryID repositoryID : repositoryIDsOfRequestedRepos)
    {
      new Thread(() -> get(repositoryID, reposData, countDownLatch)).start();
    }
    try
    {
      countDownLatch.await();
    } catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  private static void get(RepositoryID repositoryID,
                                       ConcurrentHashMap<RepositoryID, JsonStructure> reposData,
                                       CountDownLatch countDownLatch)
  {

    try
    {
      JsonStructure requestedData = GithubApiClient.downloadJsonOf(repositoryID);
      reposData.put(repositoryID, requestedData);
    } catch (FileNotFoundException e)
    {
      System.out.println("There is no such repo: " + repositoryID.getOwner() + "/" + repositoryID.getRepoName());
    } catch (Exception e)
    {
      System.out.println("Connection problem");
    } finally
    {
      countDownLatch.countDown();
    }
  }


}
