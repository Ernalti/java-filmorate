package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.NotFoundExeption;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final HashMap<Integer, User> users = new HashMap<>();
    private Integer id =0;
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> findAll() {
        log.info("Обработка запроса на получение списка пользователей");
        return new ArrayList<>(users.values());
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        user.setId(nextId());
        if (user.getName()==null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(),user);
        log.info("Создание пользователя. Пользователь: {}",user);
        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        if (!users.containsKey(user.getId())){
            log.warn("Ошибка обновления пользователя. Пользователь: {}",user);
            throw new NotFoundExeption("Не найден пользователь с id = "+user.getId());
        }
        if (user.getName()==null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.replace(user.getId(),user);
        log.info("Обновление пользователя. Пользователь: {}",user);
        return user;
    }

    @DeleteMapping
    public void clearAll(){
        log.info("Обработка запроса на удаление всех пользователей");
        users.clear();
    }

    private Integer nextId(){
        id++;
        log.debug("Изменение id пользователей: {}",id);
        return id;
    }
}
