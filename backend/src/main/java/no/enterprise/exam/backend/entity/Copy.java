package no.enterprise.exam.backend.entity;

import javax.persistence.*;

@Entity
public class Copy {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Users ownedBy;


    @ManyToOne
    private Item itemInformation;

    private int amount;

    public Copy() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Users ownedBy) {
        this.ownedBy = ownedBy;
    }

    public Item getItemInformation() {
        return itemInformation;
    }

    public void setItemInformation(Item itemInformation) {
        this.itemInformation = itemInformation;
    }
}
