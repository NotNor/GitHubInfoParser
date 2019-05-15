package com.company.input;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {
    private String[] validArgs = {"https://github.com/Microsoft/BosqueLanguage",
            "Microsoft/BosqueLanguage",
            "github.com/Microsoft/BosqueLanguage",
            "https://github.com/Microsoft/BosqueLanguage/tree/master/docs/libraries"
    };



@Test
    void parseValidInput() throws Exception {
    InputParser inputParser = new InputParser();
    inputParser.parse(validArgs);
    for ( RepositoryID repoID: inputParser.getRepositoryIDs() ) {
        assertEquals("Microsoft", repoID.getOwner());
        assertEquals("BosqueLanguage", repoID.getRepoName());
    }


}

@ParameterizedTest
@ValueSource(strings = {"-b", "repo"})
    void parseInvalidInput_throwsException(String input) {
    String[] invalid = {input};
    assertThrows(Exception.class, () ->
        new InputParser().parse(invalid));


}

}