package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RepositoryIDTest {



@Test
public void objectConstruction() {
        RepositoryID repositoryID = new RepositoryID("JohnDoe","dummyProject");
        Assertions.assertEquals(repositoryID.getOwner(), "JohnDoe");
        Assertions.assertEquals(repositoryID.getRepoName(), "dummyProject");
    }
}
