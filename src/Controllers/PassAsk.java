package Controllers;

public class PassAsk {
    private String login;
    private String users;
    private String password;
    PassAsk(String login,String users,String password){
        this.login = login;
        this.users = users;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsers() {
        return users;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
