package nordside.web.jwt;

public class Credentials {
    private String email;
    private String password;

    public Credentials() {
    }

    public Credentials(String login, String password) {
        this.email = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
