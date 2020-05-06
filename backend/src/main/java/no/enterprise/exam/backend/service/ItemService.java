package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;



@Service
@Transactional
public class ItemService {

    @Autowired
    private EntityManager entityManager;


    public Long createItem(String name, String ability, int value) {
        Item item = new Item();

        item.setName(name);
        item.setAbility(ability);
        item.setValue(value);

        entityManager.persist(item);

        return item.getId();
    }
}
