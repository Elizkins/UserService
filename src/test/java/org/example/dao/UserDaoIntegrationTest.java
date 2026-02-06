package org.example.dao;

import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoIntegrationTest extends AbstractPostgresTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl();
        cleanUp();
    }

    void cleanUp() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("delete from User").executeUpdate();
            tx.commit();
        }
    }

    @Test
    void shouldSaveAndFindUser() {
        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@mail.com");
        user.setAge(25);

        userDao.save(user);

        User found = userDao.findById(user.getId());
        assertNotNull(found);
        assertEquals("Alice", found.getName());
    }

    @Test
    void shouldFindAllUsers() {
        userDao.save(new User("A", "a@mail.com", 20));
        userDao.save(new User("B", "b@mail.com", 30));

        List<User> users = userDao.findAll();
        assertEquals(2, users.size());
    }

    @Test
    void shouldDeleteUser() {
        User user = new User("Bob", "bob@mail.com", 40);
        userDao.save(user);

        userDao.delete(user.getId());

        assertNull(userDao.findById(user.getId()));
    }
}