package ru.yandex.practicum.filmorate.exeption;

public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption(String message) {
        super(message);
    }
}
