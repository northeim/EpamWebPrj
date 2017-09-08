package by.gsu.epamlab.model.beans;

public class Author {

    private int id;
    private String authorName;
    private String description;

    public Author(int id, String authorName, String description) {
        this.id = id;
        this.authorName = authorName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (authorName != null ? !authorName.equals(author.authorName) : author.authorName != null)
            return false;
        return description != null ? description.equals(author.description) : author.description == null;
    }

    @Override
    public int hashCode() {
        int result = authorName != null ? authorName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
