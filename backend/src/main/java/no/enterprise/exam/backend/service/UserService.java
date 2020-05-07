package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;

@Service
@Transactional
public class UserService {

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String userName, String firstName, String lastName, String password, String email, String role, int currency, int lootboxes) {
        String hashedPassword = passwordEncoder.encode(password);

        if ((em.find(Users.class, userName) != null) || (em.find(Users.class, email) != null)) {
            return false;
        }

        Users users = new Users();
        users.setUserID(userName);
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setHashedPassword(hashedPassword);
        users.setRoles(Collections.singleton(role));
        users.setEnabled(true);
        users.setEmail(email);
        users.setCurrency(currency);
        users.setLootboxes(lootboxes);

        em.persist(users);

        return true;
    }

    public Users buyLootbox(String userId){
        Users user = em.find(Users.class, userId);

        if (user == null) {
            throw new IllegalStateException("No user with given userName");
        }


        if(user.getCurrency() < 50){
            return user;
        } else {
            var lootbox = user.getLootboxes() + 1;
            int lootboxCost = user.getCurrency() - 50;
            user.setLootboxes(lootbox);
            user.setCurrency(lootboxCost);
        }

        return user;

    }

    public Users findUserByUserName(String userName) {
        Users users = em.find(Users.class, userName);
        if (users == null) {
            throw new IllegalStateException("No user with given userName");
        }
        return users;
    }


}
