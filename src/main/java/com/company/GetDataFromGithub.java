package com.company;

import com.company.input.RepositoryID;

import javax.json.JsonStructure;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

class GetDataFromGithub {
    static void download(List<RepositoryID> repositoryIDsOfRequestedRepos,
                         Map<RepositoryID, JsonStructure> reposData  ) {

        CountDownLatch countDownLatch = new CountDownLatch(repositoryIDsOfRequestedRepos.size());

        for (RepositoryID repositoryID : repositoryIDsOfRequestedRepos) {
            new Thread(() -> get(repositoryID, reposData, countDownLatch)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void get(RepositoryID repositoryID,
                                         Map<RepositoryID,JsonStructure> reposData,
                                         CountDownLatch countDownLatch) {

        try {
            JsonStructure requestedData = GithubDataRequester.get(repositoryID);
            reposData.put(repositoryID, requestedData);
        } catch (FileNotFoundException e) {
            System.out.println("There is no such repo: " + repositoryID.getOwner() + "/" + repositoryID.getRepoName());
        } catch (Exception e) {
            System.out.println("Connection problem");
        } finally {
            countDownLatch.countDown();
        }
    }


}
