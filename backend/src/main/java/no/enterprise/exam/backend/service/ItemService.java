package no.enterprise.exam.backend.service;


/*
    Some methods are from https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/CategoryService.java
 */


import no.enterprise.exam.backend.entity.Item;
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

    @Autowired
    private CopyService copyService;



    public List<Item> getAllItems(Boolean withOwners) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i ORDER BY i.value ASC", Item.class
        );
        List<Item> allTrips = query.getResultList();

        if (withOwners) {
            allTrips.forEach(u -> u.getAllUsers().size());
        }
        return allTrips;
    }

    public void addRandomItemToUser(String userId){

        Item newRandomItem = getRandomItem();
        copyService.addItemToUser(newRandomItem.getId(), userId);

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

    public List<Item> filterByValue(Integer value) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i WHERE i.value =?1", Item.class
        );

        query.setParameter(1, value);

        return query.getResultList();
    }

    public List<Item> filterItemsByNames(String locationName) {
        TypedQuery<Item> query = entityManager.createQuery(
                "SELECT i FROM Item i WHERE i.name =?1 ORDER BY i.name ASC", Item.class
        );

        query.setParameter(1, locationName);

        return query.getResultList();
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
