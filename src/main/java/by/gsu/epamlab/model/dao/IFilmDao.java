package by.gsu.epamlab.model.dao;


import by.gsu.epamlab.model.beans.Film;

import java.util.List;

public interface IFilmDao {
    List<Film> getAll();
    int insert(Film film);
    int delete(int id);
    int update(Film film);
    Film getById(int id);
}
