package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldCreateUser() {
        User user = new User("Alice", "alice@mail.com", 25);

        userService.create(user);

        verify(userDao).save(user);
    }

    @Test
    void shouldReturnUserById() {
        User user = new User("Bob", "bob@mail.com", 30);
        when(userDao.findById(1L)).thenReturn(user);

        User result = userService.getById(1L);

        assertEquals("Bob", result.getName());
    }

    @Test
    void shouldReturnAllUsers() {
        when(userDao.findAll()).thenReturn(List.of(
                new User("A", "a@mail.com", 20),
                new User("B", "b@mail.com", 30)
        ));

        List<User> users = userService.getAll();

        assertEquals(2, users.size());
        verify(userDao).findAll();
    }
}