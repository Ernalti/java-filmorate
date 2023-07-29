package ru.yandex.practicum.filmorate.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Integer, Film> films = new HashMap<>();
    private Integer id = 0;
    private final Logger log = LoggerFactory.getLogger(InMemoryFilmStorage.class);

    @Override
    public List<Film> getAllFilms() {
        log.info("Получение списка фильмов из памяти");
        return new ArrayList<>(films.values());
    }

    @Override
    public Film getFilmById(Integer id) {
        if (!films.containsKey(id)) {
            log.warn("Ошибка получения фильма по id. id: {}",id);
            throw new NotFoundException("Не найден фильм с id = " + id);
        }
        return films.get(id);
    }

    @Override
    public Film createFilm(Film film) {
        film.setId(nextId());
        films.put(film.getId(),film);
        log.info("Фильм успешно загружен в память. Фильм: {}",film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (!films.containsKey(film.getId())) {
            log.warn("Ошибка обновления фильма. Идентификатор не найден. Фильм: {}",film);
            throw new NotFoundException("Не найден фильм с id = " + film.getId());
        }

        films.replace(film.getId(),film);
        log.info("Обновление фильма в памяти прошло успешно. Фильм: {}",film);
        return film;
    }

    @Override
    public Film addLike(Film film, Integer userId) {
        film.addLike(userId);
        this.updateFilm(film);
        log.info("Поставлен лайк фильму. Фильм: {}; Пользователь:{}", film, userId);
        return film;
    }

    @Override
    public Film delLike(Film film, Integer userId) {
        film.delLike(userId);
        this.updateFilm(film);
        log.info("Удален лайк фильму. Фильм: {}; Пользователь:{}", film, userId);
        return film;
    }

    @Override
    public void clearFilms() {
        log.info("Удаление всех фильмов из памяти");
        films.clear();
    }

    private Integer nextId() {
        id++;
        log.debug("Изменение id фильмов: {}",id);
        return id;
    }


}
