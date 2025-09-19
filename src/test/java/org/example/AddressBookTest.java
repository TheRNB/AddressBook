package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AddressBookTest {
    @Test
    public void addOperates() {
        AddressBook ab = new AddressBook();
        BuddyInfo b = new BuddyInfo();
        ab.addBuddy(b);
        assertEquals(1, ab.size());
        assertSame(b, ab.get(0));
    }

    @Test
    public void removeMiddleObjectOperates() {
        AddressBook ab = new AddressBook();
        BuddyInfo a = new BuddyInfo(), b = new BuddyInfo(), c = new BuddyInfo();
        ab.addBuddy(a);
        ab.addBuddy(b);
        ab.addBuddy(c);
        ab.remove(b);
        assertSame(a, ab.get(0));
        assertSame(c, ab.get(1));
        assertEquals(2, ab.size());
    }

    @Test
    public void getReturnsSameObject() {
        AddressBook ab = new AddressBook();
        BuddyInfo b = new BuddyInfo();
        b.setName("Nina");
        b.setPhone("1111111111");
        ab.addBuddy(b);
        assertSame(b, ab.get(0));
    }

    @Test
    public void sizeWorks() {
        AddressBook ab = new AddressBook();
        assertEquals(0, ab.size());
        ab.addBuddy(new BuddyInfo());
        assertEquals(1, ab.size());
    }

    @Test
    public void getBuddiesWorks() {
        AddressBook ab = new AddressBook();
        BuddyInfo b = new BuddyInfo();
        b.setName("Nina");
        b.setPhone("1111111111");
        ab.addBuddy(b);
        assertSame(b, ab.getBuddies().get(0));
    }
}