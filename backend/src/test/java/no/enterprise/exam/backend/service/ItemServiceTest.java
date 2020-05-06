package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.TestApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        Long itemJigg = itemService.createItem("Jigglypuff", "sleep", 100);
        Long itemBowser = itemService.createItem("Bowser", "Sit", 200);
        Long itemErik = itemService.createItem("Erik", "help", 500);
        var randomItem = itemService.getRandomItems(2);

        assertNotNull(randomItem);

    }
}
