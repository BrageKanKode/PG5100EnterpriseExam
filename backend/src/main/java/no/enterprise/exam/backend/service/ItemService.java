package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.entity.Item;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

    public List<Item> getRandomItems(int amount){
        List<Item> items = new ArrayList<>(amount);
        while(items.size() < amount){
            items.add(getRandomItem());
        }
        return items;
    }


    public Item getRandomItem() {
        TypedQuery<Long> sizeQuery= entityManager.createQuery(
                "select count(item) from Item item", Long.class);
        long size = sizeQuery.getSingleResult();
        Random random = new Random();
        int rnd = random.nextInt((int)size);

        TypedQuery<Item> query = entityManager
                .createQuery("SELECT item FROM Item item", Item.class)
                .setFirstResult(rnd)
                .setMaxResults(1);
        Item item = query.getSingleResult();

        return item;
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
