package com.company.input;

class InputParserTest {
    private String[] goodArgs = {"https://github.com/Microsoft/BosqueLanguage",
            "Microsoft/BosqueLanguage",
            "github.com/Microsoft/BosqueLanguage",
            "https://github.com/Microsoft/BosqueLanguage/tree/master/docs/libraries"
    };

    private String[] badArgs = {
            "-b",
            "repo",
            "owner"};



}