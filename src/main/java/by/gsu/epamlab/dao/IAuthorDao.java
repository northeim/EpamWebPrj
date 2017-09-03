package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.Author;

import java.util.List;

public interface IAuthorDao {

    /**
     * Get Author By Id;
     * @param id - Id In DataBase Table;
     * @return Author Object;
     */
    Author getById(int id);

    /**
     * Get List Author Objects;
     * @return
     */
    List<Author> getAll();

    /**
     * Insert Author Object In DataBase Table;
     * @param author
     * @return
     */
    int insert(Author author);

    /**
     * Delete Author's Record From DataBase By Id;
     * @param id
     * @return
     */
    int delete(int id);
    int update(Author author);

}
