package Dominio;

import java.util.Scanner;

public class Player {

    private String Username;
    private String Password;
    private int Code_User;

    public Player(String User, String pass,int codi){
        Username = User;
        Password = pass;
        Code_User = codi;
    }

    public String getUsername(){
        return Username;
    }

    public String getPassword() { return Password; }

    public int getCode_User() {
        return Code_User;
    }

}

