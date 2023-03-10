package work.oscarramos.manejousario.modelo;

public class Usuario {
    private Integer id;
    private String userName;
    private String password;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private String email;

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public Usuario(Integer id, String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.id = id;
    }
    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
