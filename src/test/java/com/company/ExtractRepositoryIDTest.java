package com.company;

import com.company.input.ExtractRepositoryID;
import com.company.input.RepositoryID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExtractRepositoryIDTest {


    @ParameterizedTest
    @ValueSource(strings = {"https://github.com/Microsoft/BosqueLanguage",
            "Microsoft/BosqueLanguage",
            "github.com/Microsoft/BosqueLanguage",
            "https://github.com/Microsoft/BosqueLanguage/tree/master/docs/libraries"})
    void get(String input) {
       RepositoryID repositoryID = new RepositoryID("Microsoft", "BosqueLanguage");
       RepositoryID testInput = ExtractRepositoryID.get(input);

        assertEquals(repositoryID.getOwner(),testInput.getOwner());
        assertEquals(repositoryID.getRepoName(), testInput.getRepoName());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "-b",
            "repo",
            "owner",
    })
    void get_throws_IllegalArgumentException(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RepositoryID testInput = ExtractRepositoryID.get(input);});

    }
}