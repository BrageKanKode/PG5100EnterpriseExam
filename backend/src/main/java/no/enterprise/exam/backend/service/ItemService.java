package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ItemService {

    @Autowired
    private EntityManager entityManager;


    public List<Item> getAllItems(Boolean withTravelers) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i ORDER BY i.value ASC", Item.class
        );
        List<Item> allTrips = query.getResultList();

        if (withTravelers) {
            allTrips.forEach(u -> u.getAllUsers().size());
        }
        return allTrips;
    }

    public Long createItem(String name, String ability, int value) {
        Item item = new Item();

        item.setName(name);
        item.setAbility(ability);
        item.setValue(value);

        entityManager.persist(item);

        return item.getId();
    }
}
