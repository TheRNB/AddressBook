package org.example;

public class BuddyInfo {
    private String name;
    private String phone;

    public BuddyInfo() {
        this.name = null;
        this.phone = null;
        return;
    }

    public void setName (String input) {
        this.name = input;
        return;
    }

    public void setPhone (String input) {
        input = cleanPhone(input);
        this.phone = input;
        return;
    }

    public String getName() { return name; }

    public String getPhone() { return phone; }

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
};
