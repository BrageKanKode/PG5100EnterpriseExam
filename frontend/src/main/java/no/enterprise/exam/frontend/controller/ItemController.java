package no.enterprise.exam.frontend.controller;

import no.enterprise.exam.backend.entity.Item;
import no.enterprise.exam.backend.service.CopyService;
import no.enterprise.exam.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScope
public class ItemController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ItemService itemService;
    @Autowired
    private CopyService copyService;

    private Long itemID;

    public List<Item> getItems(int numberOfItems) {
        return itemService.getAllItems(true).stream().limit(numberOfItems).collect(Collectors.toList());
    }

    public void millItem(Long itemId, String userId){
        copyService.sellItem(itemId, userId);
    }

    public void openLootbox(String userId){
        itemService.addRandomItemToUser(userId);
    }

    public void buyLootbox(String userId){
        copyService.buyLootbox(userId);
    }

    public List<Item> filterItemsBy(String searchBy, String query) {
        if (searchBy.equals("byValue")) {
            return itemService.filterByCost(Long.valueOf(query));
        } else if (searchBy.equals("byName")) {
            return itemService.filterItemsByNames(query);
        } else {
            return null;
        }
    }



    public String getMonsterRedirectionLink(Long itemID){
        return "/details.jsf?monsterID=" + itemID + "&faces-redirect=true";
    }



}
