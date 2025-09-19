package org.example;

import org.example.repo.AddressBookRepository;
import org.example.repo.BuddyInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddressBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(AddressBookRepository books, BuddyInfoRepository buddies) {
        return args -> {
            AddressBook book = books.save(new AddressBook());
            BuddyInfo nina = new BuddyInfo();
            nina.setName("Nina");
            nina.setPhone("+15559990100");
            BuddyInfo alex = new BuddyInfo();
            alex.setName("Alex");
            alex.setPhone("+15558880200");
            book.addBuddy(nina);
            book.addBuddy(alex);
            books.save(book);

            System.out.println("All buddies:");
            buddies.findAll().forEach(System.out::println);

            System.out.println("Find by name Nina:");
            buddies.findByName("Nina").forEach(System.out::println);
        };
    }
}
