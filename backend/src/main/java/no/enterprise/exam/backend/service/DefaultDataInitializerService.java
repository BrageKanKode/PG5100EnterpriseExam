package no.enterprise.exam.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

/*
    Same principal as https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/DefaultDataInitializerService.java
 */

@Service
public class DefaultDataInitializerService {

    @Autowired
    private ItemService itemService;


    @PostConstruct
    public void init() {

        Long ItemID1 = attempt(() ->
                itemService.createItem("Reclaimer", "Heavy Salvage", 175)
        );
        Long ItemID2 = attempt(() ->
                itemService.createItem("890 JUMP", "Luxury yacht", 900)
        );
        Long ItemID3 = attempt(() ->
                itemService.createItem("Catepillar", "Cargo", 75)
        );
        Long ItemID4 = attempt(() ->
                itemService.createItem("Esperia Prowler", "Dropship", 60)
        );
        Long ItemID5 = attempt(() ->
                itemService.createItem("Constellation", "Medium Freight", 50)
        );
        Long ItemID6 = attempt(() ->
                itemService.createItem("Banu Defender", "Light Fighter", 50)
        );
        Long ItemID7 = attempt(() ->
                itemService.createItem("Hull E", "Heavy Freight", 100)
        );
        Long ItemID8 = attempt(() ->
                itemService.createItem("Banu Merchantman", "Heavy Freight", 150)
        );
        Long ItemID9 = attempt(() ->
                itemService.createItem("Idris-M", "Frigate", 300)
        );
        Long ItemID10 = attempt(() ->
                itemService.createItem("Freelancer", "Expedition", 50)
        );
        Long ItemID11 = attempt(() ->
                itemService.createItem("Orion", "Heavy Mining", 200)
        );
        Long ItemID12 = attempt(() ->
                itemService.createItem("Retaliator Bomber", "Heavy Bomber", 70)
        );
        Long ItemID13 = attempt(() ->
                itemService.createItem("Starfarer Gemini", "Heavy Refuel", 125)
        );
        Long ItemID314 = attempt(() ->
                itemService.createItem("Reliant Kore", "Desc", 25)
        );
        Long ItemID15 = attempt(() ->
                itemService.createItem("Genesis Starliner", "Passenger", 85)
        );
        Long ItemID16 = attempt(() ->
                itemService.createItem("Vanguard", "Heavy Fighter", 50)
        );
        Long ItemID17 = attempt(() ->
                itemService.createItem("Sabre", "Stealth Fighter", 40)
        );
        Long ItemID18 = attempt(() ->
                itemService.createItem("Avenger", "Light Freight", 35)
        );
        Long ItemID19 = attempt(() ->
                itemService.createItem("Crucible", "Heavy Repair", 60)
        );
        Long ItemID20 = attempt(() ->
                itemService.createItem("Dragonfly", "Racing", 15)
        );

    }

    private <T> T attempt(Supplier<T> lambda) {
        try {
            return lambda.get();
        } catch (Exception e) {
            return null;
        }
    }


}
