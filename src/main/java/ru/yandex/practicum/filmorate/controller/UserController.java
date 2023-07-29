package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();
    private Integer id = 0;
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable int id) {
        return userService.findUser(id);
    }

    @GetMapping(value = "/{id}/friends")
    public List<User> getFriend(@PathVariable Integer id) {
        return userService.getFriends(id);
    }

    @GetMapping(value = "/{id}/friends/common/{otherId}")
    public List<User> getCommonFriend(@PathVariable Integer id, @PathVariable Integer otherId) {
        return userService.getCommonFriends(id, otherId);
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        return userService.update(user);
    }

    @PutMapping(value = "/{id}/friends/{friendId}")
    public User addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        return userService.addFriend(id, friendId);
    }

    @DeleteMapping(value = "/{id}/friends/{friendId}")
    public User delFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        return userService.delFriend(id, friendId);
    }

    @DeleteMapping
    public void clearAll() {
        userService.clearAll();
    }

}
