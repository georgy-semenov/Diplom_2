package user;

public class Credentials {
    private String email;
    private String password;
    private String name;

    public Credentials(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public Credentials(){
    }
    public static Credentials from(User user){
        Credentials credentials = new Credentials();
        credentials.setEmail(user.getEmail());
        credentials.setPassword(user.getPassword());
        credentials.setName(user.getName());
        return credentials;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
