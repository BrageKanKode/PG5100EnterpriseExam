package no.enterprise.exam.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    /*@Autowired
    private PlaceHolder placeHolder;*/

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


    }

    private <T> T attempt(Supplier<T> lambda) {
        try {
            return lambda.get();
        } catch (Exception e) {
            return null;
        }
    }


}
