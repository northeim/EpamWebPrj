package by.gsu.epamlab;

import by.gsu.epamlab.model.beans.Author;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {

    private static int id = 1;

    public static void main(String[] arg) {
        System.out.println(genId());
        System.out.println(genId());
        Map<Integer, Author> map = new HashMap<Integer, Author>();
        map.put(1, new Author(1, "One", "desc"));
        map.put(2, new Author(2, "Two", "desc"));

        printMap(map);

        printMap(new ArrayList<Author>(map.values()));


    }

    private static int genId() {
        return id++;
    }

    private static void printMap(Map<Integer, Author> authorMap) {
        for (Map.Entry<Integer, Author> entry: authorMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void printMap(List<Author> authors) {
        for (Author author: authors) {
            System.out.println(author);
        }
    }

}
