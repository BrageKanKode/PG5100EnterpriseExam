package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.TestApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase {

    @Autowired
    private UserService userService;


    @Test
    public void testCreateUser() {
        boolean userID = userService.createUser("JackBlack", "Jackie", "Black", "123", "Jack@email.com", "user", 100, 3);

        assertTrue(userID);

    }

    @Test
    public void testBuyLootbox() {
        userService.createUser("JackBlack", "Jackie", "Black", "123", "Jack@email.com", "user", 100, 3);

        var user = userService.buyLootbox("JackBlack");

        System.out.println(user.getLootboxes());
        assertTrue(user.getLootboxes() < 3);

    }
    @Test
    public void testFailToBuyLootbox() {
        userService.createUser("JackBlack", "Jackie", "Black", "123", "Jack@email.com", "user", 10, 3);
        userService.createUser("SithMan", "Darth", "Maul", "Kenobi", "DarthMaul@email.com", "user", 100, 0);

        var jackBlack = userService.buyLootbox("JackBlack");
        var darthMaul = userService.buyLootbox("SithMan");


        assertTrue(jackBlack.getLootboxes() == 3);
        assertTrue(darthMaul.getCurrency() == 100);

    }
}
