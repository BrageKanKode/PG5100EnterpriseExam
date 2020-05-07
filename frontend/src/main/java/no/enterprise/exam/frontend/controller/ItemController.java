package no.enterprise.exam.frontend.controller;

import no.enterprise.exam.backend.entity.Item;
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

    private Long itemID;

    public List<Item> getItems(int numberOfItems) {
        return itemService.getAllItems(true).stream().limit(numberOfItems).collect(Collectors.toList());
    }

    public String getMonsterRedirectionLink(Long itemID){
        return "/details.jsf?monsterID=" + itemID + "&faces-redirect=true";
    }



}
