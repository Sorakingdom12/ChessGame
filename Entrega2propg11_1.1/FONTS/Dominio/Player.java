package Dominio;

public class Player {

    private String Username;
    private String Password;
    private int Code_User;

    Player(String User, String pass,int codi){
        Username = User;
        Password = pass;
        Code_User = codi;
    }

    String getUsername(){
        return Username;
    }

    String getPassword() { return Password; }

    void setPassword(String nuevo) { Password = nuevo; }

    void setUsername(String nuevo) { Username = nuevo; }

    public int getCode_User() {
        return Code_User;
    }

}

