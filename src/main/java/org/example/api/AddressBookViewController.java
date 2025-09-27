package org.example.api;

import org.example.model.AddressBook;
import org.example.repo.AddressBookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AddressBookViewController {

    private final AddressBookRepository addressBookRepo;

    public AddressBookViewController(AddressBookRepository addressBookRepo) {
        this.addressBookRepo = addressBookRepo;
    }

    @GetMapping("/addressbook/{id}/view")
    public String viewAddressBook(@PathVariable Long id, Model model) {
        AddressBook addressBook = addressBookRepo.findById(id).orElse(null);
        model.addAttribute("addressBook", addressBook);
        return "addressbook";
    }
}
