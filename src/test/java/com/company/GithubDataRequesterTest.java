package com.company;

import com.company.input.RepositoryID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class GithubDataRequesterTest {


    private final RepositoryID goodData = new RepositoryID("Microsoft", "BosqueLanguage");
    private final RepositoryID badData = new RepositoryID("Microsoft", "BosqueasadfLanguage");
    @Test
    void get_ValidLinkPass() {
        Assertions.assertDoesNotThrow(() -> {
            GithubDataRequester.get(goodData);
        });
    }

    @Test
    void get_InvalidLinkThrowsException() {
        Assertions.assertThrows(FileNotFoundException.class, () -> GithubDataRequester.get(badData));

    }
}