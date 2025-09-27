package org.example.model;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "address_book")
public class AddressBook {
    @Id
    @GeneratedValue
    private Long id;

    @JsonManagedReference
    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuddyInfo> buddies = new ArrayList<>();

    public AddressBook() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            buddies.add(buddy);
            buddy.setAddressBook(this);
        }
    }

    public void removeBuddy(BuddyInfo buddy) {
        buddies.remove(buddy);
        buddy.setAddressBook(null);
    }

    public BuddyInfo get(int index) { return buddies.get(index); }
    public int size() { return buddies.size(); }
    public List<BuddyInfo> getBuddies() { return buddies; }
}
