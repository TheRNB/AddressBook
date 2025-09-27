package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.*;
import org.example.model.BuddyInfo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main_deprecated {
    public static void main(String[] args) {
        EntityManagerFactory emf;
        EntityManager em;
        emf = Persistence.createEntityManagerFactory("addressbookPU");
        em = emf.createEntityManager();

        BuddyInfo buddy = new BuddyInfo();
        buddy.setName("Nina");
        buddy.setPhone("+15559990100");

        em.getTransaction().begin();
        em.persist(buddy);
        em.getTransaction().commit();

        Long id = buddy.getId();
        System.out.println(id);

        // forcing a DB read
        em.clear();

        BuddyInfo found = em.find(BuddyInfo.class, id);
        System.out.println(found);

        // general :name parameter finding query
        TypedQuery<BuddyInfo> q = em.createQuery(
                "select b from BuddyInfo b where b.name = :name", BuddyInfo.class);

        q.setParameter("name", "Nina");

        // we are expecting one result only obv!
        BuddyInfo byName = q.getSingleResult();
        System.out.println(byName.getName());

        em.close();
        emf.close();
    }
}