package no.enterprise.exam.frontend.controller;


import no.enterprise.exam.backend.entity.Copy;
import no.enterprise.exam.backend.entity.Item;
import no.enterprise.exam.backend.service.CopyService;
import no.enterprise.exam.backend.service.ItemService;
import no.enterprise.exam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScope
public class CollectionController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private CopyService copyService;

    private Long itemID;

    public List<Copy> getCopies(int numberOfItems) {
        return copyService.getAllCopies().stream().limit(numberOfItems).collect(Collectors.toList());
    }

    public String getMonsterRedirectionLink(Long itemID){
        return "/details.jsf?monsterID=" + itemID + "&faces-redirect=true";
    }
}
