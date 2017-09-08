package by.gsu.epamlab.model.dao;


import by.gsu.epamlab.model.beans.Film;

import java.util.List;

public interface IFilmDao {

    Film getById(int id);
    List<Film> getAll();
    int insert(Film film);
    int update(Film film);
    int delete(int id);

}
