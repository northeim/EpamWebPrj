package by.gsu.epamlab.beans;

public class User {

    private int id;
    private String nickName;
    private String firstName;
    private String secondName;
    private String password;
    private String email;
    private Role role;

    public User(int id, String nickName, String firstName, String secondName,
                String email, String password, Role role) {
        this.id = id;
        this.nickName = nickName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!nickName.equals(user.nickName)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!secondName.equals(user.secondName)) return false;
        if (!password.equals(user.password)) return false;
        if (!role.equals(user.role)) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nickName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
