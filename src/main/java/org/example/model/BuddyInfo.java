package org.example.model;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "buddy_info")
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String phone;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "addressbook_id",
            foreignKey = @ForeignKey(name = "fk_buddyinfo_addressbook")
    )

    private AddressBook addressBook;

    public BuddyInfo() { this.name = null; this.phone = null; }

    public AddressBook getAddressBook() { return addressBook; }
    public void setAddressBook(AddressBook ab) { this.addressBook = ab; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String input) { this.name = input; }

    public String getPhone() { return phone; }
    public void setPhone(String input) { this.phone = cleanPhone(input); }

    private String cleanPhone(String input) {
        if (input == null || input.isEmpty()) return input;
        if (input.charAt(0) == '+') {
            input = input.substring(2);
        } else if (input.charAt(0) == '0') {
            input = input.substring(3);
        }
        return input;
    }

    @Override
    public String toString() {
        return this.name + " => " + this.phone;
    }
}
