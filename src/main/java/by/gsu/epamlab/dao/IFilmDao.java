package by.gsu.epamlab.dao;


import by.gsu.epamlab.beans.Film;

import java.util.List;

public interface IFilmDao {
    List<Film> getAll();
    int insert(Film film);
    int delete(int id);
    int update(Film film);
    Film getById(int id);
}
