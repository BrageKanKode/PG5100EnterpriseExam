package no.enterprise.exam.backend.service;


import no.enterprise.exam.backend.TestApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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



    @Test
    public void testSellItem() {
        userService.createUser("JackBlack", "Jackie", "Black", "123", "Jack@email.com", "user", 100, 3);
        Long ItemID = itemService.createItem("Test", "Desc", 200);
        Long ItemID2 = itemService.createItem("Test2", "Desc", 200);
        Long copyId = copyService.addItemToUser(ItemID, "JackBlack");
        Long copyId2 = copyService.addItemToUser(ItemID2, "JackBlack");

        int newValue = copyService.sellItem(copyId2, "JackBlack");
        System.out.println(newValue);
        assertEquals(300, newValue);


    }
}
