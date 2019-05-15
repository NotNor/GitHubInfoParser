package com.company;

import com.company.input.RepositoryID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class GithubApiClientTest {


    private final RepositoryID validData = new RepositoryID("Microsoft", "BosqueLanguage");
    private final RepositoryID invalidData = new RepositoryID("Microsoft", "invalidRepoName");
    @Test
    void get_ValidLinkPass() {
        Assertions.assertDoesNotThrow(() -> {
            GithubApiClient.downloadJsonOf(validData);
        });
    }

    @Test
    void get_InvalidLinkThrowsException() {
        Assertions.assertThrows(FileNotFoundException.class, () -> GithubApiClient.downloadJsonOf(invalidData));

    }
}