package org.example.api;

import org.example.model.AddressBook;
import org.example.model.BuddyInfo;
import org.example.repo.AddressBookRepository;
import org.example.repo.BuddyInfoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddressBookViewController {

    private final AddressBookRepository addressBookRepo;
    private final BuddyInfoRepository buddyInfoRepo;

    public AddressBookViewController(AddressBookRepository addressBookRepo, BuddyInfoRepository buddyInfoRepo) {
        this.addressBookRepo = addressBookRepo;
        this.buddyInfoRepo = buddyInfoRepo;
    }

    @GetMapping({"/", "/view", "/view/addressbooks"})
    public String listAddressBooks(Model model) {
        Iterable<AddressBook> allBooks = addressBookRepo.findAll();
        model.addAttribute("addressBooks", allBooks);
        return "addressbooks";
    }

    @PostMapping("/view/addressbook/create")
    public String createAddressBookRedirect() {
        AddressBook newBook = new AddressBook();
        AddressBook savedBook = addressBookRepo.save(newBook);
        return "redirect:/view/addressbook/" + savedBook.getId() + "/view";
    }

    @PostMapping("/view/addressbook/{id}/addbuddy")
    public String addBuddyToAddressBook(@PathVariable Long id,
                                        @RequestParam String name,
                                        @RequestParam String phone,
                                        @RequestParam String address) {
        AddressBook addressBook = addressBookRepo.findById(id).orElse(null);
        if (addressBook == null) {
            return "redirect:/view";
        }
        BuddyInfo buddy = new BuddyInfo();
        buddy.setName(name);
        buddy.setPhone(phone);
        buddy.setAddress(address);
        addressBook.addBuddy(buddy);
        buddyInfoRepo.save(buddy);
        addressBookRepo.save(addressBook);
        return "redirect:/view/addressbook/" + id + "/view";
    }

    @GetMapping("/view/addressbook/{id}/view")
    public String viewAddressBook(@PathVariable Long id, Model model) {
        AddressBook addressBook = addressBookRepo.findById(id).orElse(null);
        model.addAttribute("addressBook", addressBook);
        return "addressbook";
    }
}
