package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.entity.Item;
import no.enterprise.exam.backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collections;


@Service
public class ItemService {

    @Autowired
    private EntityManager em;

    public boolean createItem(String itemName, String ability, int value) {


        if ((em.find(Item.class, itemName) != null) || (em.find(Item.class, value) != null)) {
            return false;
        }

        Item item = new Item();
        item.setName(itemName);
        item.setAbility(ability);
        item.setValue(value);

        em.persist(item);

        return true;
    }
}
