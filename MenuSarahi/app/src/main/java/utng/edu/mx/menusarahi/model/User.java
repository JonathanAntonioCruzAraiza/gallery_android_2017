package utng.edu.mx.menusarahi.model;

/**
 * Created by jony on 16/2/2017.
 */

public class User {
    private String id;
    private String user;
    private String password;

    private String firstname;
    private String lastname;

    public User(String id, String user, String password, String firstname, String lastname) {
        this.id = id;
        this.user = user;
        this.password=password;

        this.firstname = firstname;
        this.lastname = lastname;
    }
    public User() {
        this("","","","","");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
