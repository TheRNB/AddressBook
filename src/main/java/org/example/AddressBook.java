package org.example;
import java.util.ArrayList;

public class AddressBook {
    private ArrayList<BuddyInfo> buddies = null;

    public AddressBook() {
        this.buddies = new ArrayList<BuddyInfo>();
        return;
    }

    public void add(BuddyInfo buddy) {
        if (buddy == null) {
            // RAISE WARNING ()
            return;
        }
        buddies.add(buddy);
        return;
    }

    public void remove(BuddyInfo buddy) {
        buddies.remove(buddy);
        return;
    }

    public BuddyInfo get(int index) {
        return buddies.get(index);
    }

    public int size() {
        return buddies.size();
    }
};
