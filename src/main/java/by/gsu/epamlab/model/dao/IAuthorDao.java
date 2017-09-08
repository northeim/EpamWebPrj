package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.model.beans.Author;

import java.util.List;

public interface IAuthorDao {

    Author getById(int id);
    List<Author> getAll();
    int insert(Author author);
    int update(Author author);
    int delete(int id);

}
