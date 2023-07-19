package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class Film {
    public Integer id;
    @NonNull
    @NotBlank
    public String name;
    @NonNull
    @NotBlank
    @Size(max = 200)
    public String description;
    public LocalDate releaseDate;
    @Positive
    public Long duration;

}
