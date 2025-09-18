package org.example;

import org.junit.*;
import jakarta.persistence.*;

public class BuddyInfoJPATest {
    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("addressbookPU");
    }

    @AfterClass
    public static void tearDownClass() {
        if (emf != null) emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        if (em != null) em.close();
    }

    @Test
    public void persistAndRetrieveBuddy() {
        BuddyInfo buddy = new BuddyInfo();
        buddy.setName("Nina");
        buddy.setPhone("+15559990100");

        em.getTransaction().begin();
        em.persist(buddy);
        em.getTransaction().commit();

        Long id = buddy.getId();
        Assert.assertNotNull(id);

        em.clear();

        BuddyInfo found = em.find(BuddyInfo.class, id);
        Assert.assertNotNull(found);
        Assert.assertEquals("Nina", found.getName());

        TypedQuery<BuddyInfo> q = em.createQuery(
                "select b from BuddyInfo b where b.name = :name", BuddyInfo.class);
        q.setParameter("name", "Nina");
        BuddyInfo byName = q.getSingleResult();
        Assert.assertEquals(id, byName.getId());
    }
}
