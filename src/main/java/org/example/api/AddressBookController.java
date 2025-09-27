package org.example.api;

import org.example.model.AddressBook;
import org.example.model.BuddyInfo;
import org.example.repo.AddressBookRepository;
import org.example.repo.BuddyInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private final AddressBookRepository addressBookRepo;
    private final BuddyInfoRepository buddyInfoRepo;

    public AddressBookController(AddressBookRepository addressBookRepo, BuddyInfoRepository buddyInfoRepo) {
        this.addressBookRepo = addressBookRepo;
        this.buddyInfoRepo = buddyInfoRepo;
    }

    @PostMapping("/newbook")
    public AddressBook createAddressBook(@RequestBody(required = false) AddressBook addressBook) {
        if (addressBook == null) {
            addressBook = new AddressBook();
        }
        return addressBookRepo.save(addressBook);
    }

    @GetMapping
    public Iterable<AddressBook> getAllAddressBooks() {
        return addressBookRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getAddressBook(@PathVariable Long id) {
        Optional<AddressBook> addressBook = addressBookRepo.findById(id);
        return addressBook.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteAddressBook(@PathVariable Long id) {
        if (!addressBookRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        addressBookRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<AddressBook> addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddy) {
        Optional<AddressBook> addressBookOpt = addressBookRepo.findById(id);
        if (!addressBookOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AddressBook addressBook = addressBookOpt.get();
        addressBook.addBuddy(buddy);
        buddyInfoRepo.save(buddy);
        addressBookRepo.save(addressBook);
        return ResponseEntity.ok(addressBook);
    }

    @DeleteMapping("/{id}/remove/{buddyId}")
    public ResponseEntity<AddressBook> removeBuddy(@PathVariable Long id, @PathVariable Long buddyId) {
        Optional<AddressBook> addressBookOpt = addressBookRepo.findById(id);
        if (!addressBookOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AddressBook addressBook = addressBookOpt.get();
        Optional<BuddyInfo> buddyOpt = buddyInfoRepo.findById(buddyId);
        if (!buddyOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        BuddyInfo buddy = buddyOpt.get();
        addressBook.removeBuddy(buddy);
        buddyInfoRepo.delete(buddy);
        addressBookRepo.save(addressBook);
        return ResponseEntity.ok(addressBook);
    }
}
