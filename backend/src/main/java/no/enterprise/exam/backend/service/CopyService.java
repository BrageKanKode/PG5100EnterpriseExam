package no.enterprise.exam.backend.service;

import no.enterprise.exam.backend.entity.Copy;
import no.enterprise.exam.backend.entity.Item;
import no.enterprise.exam.backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CopyService {

    @Autowired
    private EntityManager entityManager;


    public List<Copy> getAllCopies(){
        TypedQuery<Copy> query = entityManager.createQuery("SELECT c FROM Copy c",Copy.class);
        System.out.println(query.getResultList());
        return query.getResultList();
    }

    //Blanding
    public void sellItem(Long itemId, String userId){
        Item item = entityManager.find(Item.class, itemId);
        Users user = entityManager.find(Users.class, userId);


        if (item == null) {
            throw new IllegalStateException("No copy with given itemId");
        }
        if(user == null) {
            throw new IllegalStateException("No user with given userId");
        }

        List<Item> items = user.getOwnedBy();
        int currency = user.getCurrency() + item.getValue();

        user.setCurrency(currency);

        items.remove(item);
    }

    public Long addItemToUser(Long itemID, String userID) {
        Item item = entityManager.find(Item.class, itemID);
        Users users = entityManager.find(Users.class, userID);

        if (item == null) {
            throw new IllegalStateException("Trip not found");
        }
        if (users == null) {
            throw new IllegalStateException("User not found");
        }

        Copy copy = new Copy();
        copy.setAmount(copy.getAmount() + 1);
        copy.setOwnedBy(users);
        copy.setItemInformation(item);
        users.getOwnedBy().add(item);
        entityManager.persist(copy);

        return copy.getId();
    }
}
