package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.entity.Copy;
import no.enterprise.exam.backend.entity.Item;
import no.enterprise.exam.backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;



public class CopyService {

    @Autowired
    private EntityManager entityManager;

    public Long newPurchase(Long tripID, String userID) {
        Item item = entityManager.find(Item.class, tripID);
        Users users = entityManager.find(Users.class, userID);

        if (item == null) {
            throw new IllegalStateException("Trip not found");
        }
        if (users == null) {
            throw new IllegalStateException("User not found");
        }

        Copy copy = new Copy();
        copy.setOwnedBy(users);
        copy.setItemInformation(item);
        users.getOwnedBy().add(item);
        entityManager.persist(copy);

        return copy.getId();
    }
}
