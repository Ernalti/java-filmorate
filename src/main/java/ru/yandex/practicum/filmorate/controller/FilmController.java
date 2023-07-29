package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public Film findFilm(@PathVariable int id) {
        return filmService.findFilm(id);
    }

    @GetMapping(value = "/popular")
    public List<Film> getPopFilms(@RequestParam(defaultValue = "10") int count) {
        return filmService.getPopFilms(count);
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        return filmService.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        return filmService.update(film);
    }

    @PutMapping(value = "/{id}/like/{userId}")
    public Film addLike(@PathVariable Integer id, @PathVariable Integer userId) {
        return filmService.addLike(id, userId);
    }

    @DeleteMapping(value = "/{id}/like/{userId}")
    public Film deleteLike(@PathVariable Integer id, @PathVariable Integer userId) {
        return filmService.delLike(id, userId);
    }


    @DeleteMapping
    public void clearAll() {
        filmService.clearAll();
    }

}
