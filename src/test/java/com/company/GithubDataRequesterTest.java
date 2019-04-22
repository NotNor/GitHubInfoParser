package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class GithubDataRequesterTest {


    private RepositoryID goodData = new RepositoryID("Microsoft", "BosqueLanguage");
    private RepositoryID badData = new RepositoryID("Microsoft", "BosqueasadfLanguage");
    @Test
    void get_ValidLinkPass() {
        Assertions.assertDoesNotThrow(() -> {
            GithubDataRequester.get(goodData);
        });
    }

    @Test
    void get_InvalidLinkThrowsException() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            GithubDataRequester.get(badData);});

    }
}