package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        AddressBook myBook = new AddressBook();
        BuddyInfo friendFirst = new BuddyInfo(), friendSecond = new BuddyInfo(), friendThird = new BuddyInfo();
        friendFirst.setName("Nina Mina");
        friendFirst.setPhone("+19991112233");
        friendSecond.setName("Fred Fried");
        friendSecond.setPhone("0018887776655");
        friendThird.setName("Sam Sassanian");
        friendThird.setPhone("6049872345");

        myBook.add(friendFirst);
        myBook.add(friendSecond);
        myBook.add(friendThird);

        for (int i = 0; i < myBook.size(); ++i) {
            System.out.println(myBook.get(i).toString());
        }
    }
}