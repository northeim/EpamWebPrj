package by.gsu.epamlab.model.daoimp.memory;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.model.beans.Author;
import by.gsu.epamlab.model.dao.IAuthorDao;

import java.util.*;

public class AuthorDaoMemory implements IAuthorDao {

    private static final Map<Integer, Author> AUTHORS = new HashMap<Integer, Author>();
    private static int id = 1;

    static {
        Author author =
                new Author(id, "Joachim Ronning", "Born: May 30, 1972 in Sandefjord, Norway");
        AUTHORS.put(id, author);
        generateId();
        author =
                new Author(id, "Michael Bay", "Born: February 17, 1965 in Los Angeles, " +
                        "California, USA");
        AUTHORS.put(id, author);
        generateId();
        author =
                new Author(id, "Nikolaj Arcel", "Born: August 25, 1972 in Copenhagen, Denmark");
        AUTHORS.put(id, author);
        generateId();
    }

    private static int generateId() {
        return id++;
    }

    public Author getById(int id) {
        Author author;
        if (AUTHORS.containsKey(id)) {
            author = AUTHORS.get(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_AUTHOR_IN_DB);
        }
        return author;
    }

    public List<Author> getAll() {
        return new ArrayList<Author>(AUTHORS.values());
    }

    public int insert(Author author) {
        synchronized (AUTHORS) {
            author.setId(id);
            AUTHORS.put(id, author);
            generateId();
        }
        return 0;
    }

    public int delete(int id) {
        if (AUTHORS.containsKey(id)) {
            AUTHORS.remove(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_AUTHOR_IN_DB);
        }
        return 0;
    }

    public int update(Author author) {
        if (AUTHORS.containsKey(author.getId())) {
            AUTHORS.put(author.getId(), author);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_AUTHOR_IN_DB);
        }
        return 0;
    }
}
