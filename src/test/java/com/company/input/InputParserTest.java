package com.company.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        Assertions.assertEquals("Microsoft", repoID.getOwner());
        Assertions.assertEquals("BosqueLanguage", repoID.getRepoName());
    }


}

@ParameterizedTest
@ValueSource(strings = {"-b", "repo"})
    void parseInvalidInput_throwsException(String input) {
    String[] invalid = {input};
    Assertions.assertThrows(Exception.class, () ->
        new InputParser().parse(invalid));


}

}