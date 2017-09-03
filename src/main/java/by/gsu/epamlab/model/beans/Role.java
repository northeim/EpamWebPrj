package by.gsu.epamlab.model.beans;

public class Role {

    private int id;
    private String description;
    private int levelAccess;

    public Role(int id, String description) {
        this.id = id;
        this.description = description;
        this.levelAccess = 0;
    }

    public Role(int id, String description, int levelAccess) {
        this.id = id;
        this.description = description;
        this.levelAccess = levelAccess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevelAccess() {
        return levelAccess;
    }

    public void setLevelAccess(int levelAccess) {
        this.levelAccess = levelAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (levelAccess != role.levelAccess) return false;
        return description.equals(role.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + description.hashCode();
        result = 31 * result + levelAccess;
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", levelAccess=" + levelAccess +
                '}';
    }
}
