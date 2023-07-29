package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {

    public List<Film> getAllFilms();
    public Film createFilm(Film film);
    public Film updateFilm(Film film);
    public Film addLike(Film film, Integer userId);
    public Film delLike(Film film, Integer userId);
    public void clearFilms();
    public Film getFilmById(Integer id);
}
