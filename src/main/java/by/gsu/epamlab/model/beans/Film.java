package by.gsu.epamlab.model.beans;


public class Film {

    private int id;
    private String name;
    private String description;
    private int authorId;
    private String coverPath;

    public Film(int id, String name, String description, int authorId, String coverPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authorId = authorId;
        this.coverPath = coverPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (authorId != film.authorId) return false;
        if (name != null ? !name.equals(film.name) : film.name != null)
            return false;
        if (description != null ? !description.equals(film.description) : film.description != null)
            return false;
        return coverPath != null ? coverPath.equals(film.coverPath) : film.coverPath == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + authorId;
        result = 31 * result + (coverPath != null ? coverPath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", coverPath='" + coverPath + '\'' +
                '}';
    }

}
