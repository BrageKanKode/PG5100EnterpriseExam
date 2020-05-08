package no.enterprise.exam.backend.service;

/*
    Gotten from https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/DefaultDataInitializerServiceTest.java
 */

import no.enterprise.exam.backend.TestApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = BEFORE_CLASS)
class DefaultDataInitializerServiceTest {

    @Autowired
    private CopyService copyService;

    @Autowired
    private ItemService itemService;



    @Test
    public void testInit() {

        assertTrue(copyService.getAllCopies().size() > 0);

        assertTrue(itemService.getAllItems(true).stream()
                .mapToLong(t -> t.getAllUsers().size())
                .sum() > 0);

        assertTrue(itemService.getAllItems(false).size() > 0);

    }

}