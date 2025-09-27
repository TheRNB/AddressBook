package org.example.repo;

import org.example.model.AddressBook;
import org.springframework.data.repository.CrudRepository;

public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
}