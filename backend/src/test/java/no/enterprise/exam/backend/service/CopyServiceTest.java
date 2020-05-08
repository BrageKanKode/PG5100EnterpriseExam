package no.enterprise.exam.backend.service;

/*
    I made these tests myself, but i'm not sure if it counts as borrowed by the repository, so ill just add the closest link i can find
    https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/CategoryServiceTest.java
 */

import no.enterprise.exam.backend.TestApplication;
import no.enterprise.exam.backend.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CopyServiceTest extends ServiceTestBase{

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private UserService userService;




    @Test
    public void testAddItemToUser() {
        userService.createUser("JackBlack", "Jackie", "Black", "123", "Jack@email.com", "user", 100, 3);
        Long tripID = itemService.createItem("Test", "Desc", 200);
        Long copyId = copyService.addItemToUser(tripID, "JackBlack");
        assertNotNull(copyId);
    }

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testSellItem() {
        userService.createUser("JackBlack", "Jackie", "Black", "123", "Jack@email.com", "user", 100, 3);
        Long ItemID = itemService.createItem("Test", "Desc", 200);
        Long ItemID2 = itemService.createItem("Test2", "Desc", 200);
        Long addedID = copyService.addItemToUser(ItemID, "JackBlack");
        Long addedID2 = copyService.addItemToUser(ItemID2, "JackBlack");

        copyService.sellItem(ItemID, "JackBlack");

        Users users = entityManager.find(Users.class, "JackBlack");
        assertEquals(300, users.getCurrency());
    }
}
