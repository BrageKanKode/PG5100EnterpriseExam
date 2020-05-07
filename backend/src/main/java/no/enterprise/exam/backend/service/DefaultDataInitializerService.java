package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Supplier;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;



    @PostConstruct
    public void init() {

        String firstUser = "admin";
        String secondUser = "foo";
        String thirdUser = "bar";

        attempt(() -> {
            return userService.createUser(
                    firstUser, firstUser, "admin-last-name", "123", "admin@email.com", "admin", 100, 3);

        });

        attempt(() -> {
            return userService.createUser(
                    secondUser, secondUser, "foo-last-name", "123", "foo@email.com", "user", 100, 3);

        });

        attempt(() -> {
            return userService.createUser(
                    thirdUser, thirdUser, "bar-last-name", "123", "bar@email.com", "user", 200, 5);

        });

        Long ItemID1 = attempt(() ->
                itemService.createItem("Test", "Desc", 50)
        );
        Long ItemID2 = attempt(() ->
                itemService.createItem("Test2", "Desc", 50)
        );
        Long ItemID3 = attempt(() ->
                itemService.createItem("Test3", "Desc", 50)
        );


        copyService.addItemToUser(ItemID1, firstUser);
        copyService.addItemToUser(ItemID2, secondUser);
/*
        List<Item> randomItem = itemService.getRandomItems(1);
        copyService.addItemToUser(randomItem.get(1).getId(), firstUser);

 */



    }

    private <T> T attempt(Supplier<T> lambda) {
        try {
            return lambda.get();
        } catch (Exception e) {
            return null;
        }
    }


}
