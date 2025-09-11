package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class BuddyInfoTest {

    @Test
    public void initializesFieldsToNull() {
        BuddyInfo b = new BuddyInfo();
        assertNull(b.getName());
        assertNull(b.getPhone());
    }

    @Test
    public void setNameSetsExactValue() {
        BuddyInfo b = new BuddyInfo();
        b.setName("Alice");
        assertEquals("Alice", b.getName());
    }

    @Test
    public void setPhoneWithPlusDropsOnlyPlus() {
        BuddyInfo b = new BuddyInfo();
        b.setPhone("+19991112233");
        // Intended: remove only the '+' at start
        assertEquals("9991112233", b.getPhone()); // will FAIL until substring(2) -> substring(1)
    }

    @Test
    public void setPhoneWithSingleLeadingZeroDropsOnlyOneZero() {
        BuddyInfo b = new BuddyInfo();
        b.setPhone("0012345");
        // Intended: remove only the first '0'
        assertEquals("2345", b.getPhone()); // will FAIL until substring(3) -> substring(1)
    }

    @Test
    public void setPhoneNullKeepsNull() {
        BuddyInfo b = new BuddyInfo();
        b.setPhone(null);
        assertNull(b.getPhone());
    }

    @Test
    public void setPhoneEmptyKeepsEmpty() {
        BuddyInfo b = new BuddyInfo();
        b.setPhone("");
        assertEquals("", b.getPhone());
    }

    @Test
    public void setPhoneNoPrefixKeepsSame() {
        BuddyInfo b = new BuddyInfo();
        b.setPhone("6049872345");
        assertEquals("6049872345", b.getPhone());
    }
}
