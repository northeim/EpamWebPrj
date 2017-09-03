package by.gsu.epamlab.model.daoimp.memory;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.model.beans.Film;
import by.gsu.epamlab.model.dao.IFilmDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmDaoMemory implements IFilmDao {

    private static final Map<Integer, Film> FILMS = new HashMap<Integer, Film>();
    private static int id = 1;

    static {
        Film film = new Film(id, "Pirates of the Caribbean: Dead Men Tell No Tales",
                    "Captain Jack Sparrow finds the winds of ill-fortune blowing even more strongly when " +
                            "deadly ghost pirates led by his old nemesis, the terrifying Captain Salazar, escape from " +
                            "the Devil's Triangle, determined to kill every pirate at sea...including him. Captain " +
                            "Jack's only hope of survival lies in seeking out the legendary Trident of Poseidon, " +
                            "a powerful artifact that bestows upon its possessor total control over the seas.",
                    1,
                    "PiratesOfTheCaribian.jpg");
        FILMS.put(id, film);
        generateId();
        film = new Film(id, "Kon-Tiki",
                "The Norwegian explorer Thor Heyerdahl crossed the Pacific Ocean in a balsawood raft in 1947, " +
                        "together with five men, to prove that South Americans back in pre-Columbian times could have " +
                        "crossed the ocean and settled on Polynesian islands. After financing the trips with loans and " +
                        "donations, they set off on an epic 101-day-long trip across 8000 kilometers, while the world " +
                        "was waiting for the result of the trip. The film tells about the origin of the idea, the " +
                        "preparations, and the events on the trip. The \"Kon-Tiki\" was named after the Inca sun god, " +
                        "Viracocha, and \"Kon-Tiki\" is an old name for this god. Heyerdahl filmed the expedition, " +
                        "which later became the Academy Award winning documentary in 1951, and he wrote a book about " +
                        "the expedition that was translated into 70 languages and sold more than 50 millions copies " +
                        "around the world. Heyerdahl believed that people from South America could have settled " +
                        "Polynesia in pre-Columbian times, although most anthropologists now believe they did not...",
                1,
                "Kon-Tiki.jpg");
        FILMS.put(id, film);
        generateId();
        film = new Film(id, "Armageddon",
                "It is just another day at the National Aeronautics and Space Administration (NASA), a few " +
                        "astronauts were repairing a satellite until, out of nowhere, a series of asteroids came " +
                        "crashing into the shuttle, destroying it. These asteroids also decimated New York soon " +
                        "thereafter. Then, NASA discovered that there is an asteroid roughly the size of Texas " +
                        "heading towards the Earth, and when it does hit the Earth, the planet itself and all of its " +
                        "inhabitants will be obliterated, worse, the asteroid will hit the Earth in 18 days. " +
                        "Unfortunately, NASA's plans to destroy the asteroid are irrelevant. That is when the U.S. " +
                        "military decides to use a nuclear warhead to blow the asteroid to pieces. Then, scientists " +
                        "decide to blow the asteroid with the warhead inside the asteroid itself. The only man to do " +
                        "it, is an oil driller named Harry Stamper and his group of misfit drillers and geologists. " +
                        "As he and his drill team prepare for space excavation, the asteroid is still heading towards " +
                        "the Earth. When...",
                2,
                "Armageddon.jpg");
        FILMS.put(id, film);
        generateId();
        film = new Film(id, "Kvinden i buret",
                "Chief detective Carl MÃ¸rck and his assistant Assad become involved in a five-year-old " +
                        "case concerning the mystery of politician Merete Lynggaard's disappearance - a journey " +
                        "that takes them deep into the undercurrent of abuse and malice that lurks beneath the " +
                        "polished surface of Scandinavia.",
                3,
                "Kvinden_i_buret.jpg");
        FILMS.put(id, film);
        generateId();
    }

    private static int generateId() {
        return id++;
    }

    public List<Film> getAll() {
        return new ArrayList<Film>(FILMS.values());
    }

    public int insert(Film film) {
        synchronized (FILMS) {
            film.setId(id);
            FILMS.put(id, film);
            generateId();
        }
        return 0;
    }

    public int delete(int id) {
        if (FILMS.containsKey(id)) {
            FILMS.remove(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_FILM_IN_DB);
        }
        return 0;
    }

    public int update(Film film) {
        if (FILMS.containsKey(film.getId())) {
            FILMS.put(film.getId(), film);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_FILM_IN_DB);
        }
        return 0;
    }

    public Film getById(int id) {
        Film film;
        if (FILMS.containsKey(id)) {
            film = FILMS.get(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_FILM_IN_DB);
        }
        return film;
    }
}
