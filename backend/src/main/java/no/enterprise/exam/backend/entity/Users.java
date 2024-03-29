package no.enterprise.exam.backend.entity;

/*
    Adjusted and added some values: https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/entity/User.java
 */

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class Users {

    @Id
    @NotBlank
    private String userID;

    @NotBlank
    @Size(max = 128)
    private String firstName;

    @NotBlank
    @Size(max = 128)
    private String lastName;

    @NotNull
    private String hashedPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private Boolean enabled;

    @NotNull
    private int lootboxes;

    @NotNull
    private int currency;

    @ManyToMany
    private List<Item> ownedBy;


    public int getLootboxes() {
        return lootboxes;
    }
    public List<Item> getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(List<Item> bookedTrips) {
        this.ownedBy = bookedTrips;
    }

    public void setLootboxes(int lootboxes) {
        this.lootboxes = lootboxes;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
/*
    public List<PlaceHolder> getPlaceHolderList() {
        return placeHolderList;
    }

    public void setPlaceHolderList(List<PlaceHolder> placeHolderList) {
        this.placeHolderList = placeHolderList;
    }*/
}
