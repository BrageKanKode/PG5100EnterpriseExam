package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.TestApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
    Inspired from https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/QuizServiceTest.java
 */

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateItem() {
        Long itemId = itemService.createItem("Jigglypuff", "sleep", 100);

        assertNotNull(itemId);
    }

    @Test
    public void testGetRandomItem() {
        userService.createUser("JackBlack", "Jackie", "Black", "123", "Jack@email.com", "user", 100, 3);
        itemService.createItem("Jigglypuff", "sleep", 100);
        itemService.createItem("Bowser", "Sit", 200);
        itemService.createItem("Erik", "help", 500);
        var randomItem = itemService.getRandomItems(2);

        assertNotNull(randomItem);

    }
}
