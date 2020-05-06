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
public class CopyServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreatePurchase() {
        userService.createUser("JackBlack", "Jackie", "Black", "123", "Jack@email.com", "user", 100, 3);
        Long tripID = itemService.createItem("Test", "Desc", 200);
        Long purchaseId = copyService.addLootboxToUser(tripID, "JackBlack");
        assertNotNull(purchaseId);
    }
}
