package com.app.model;
//Classes reflecting real world object go in model

public class User {

    private int userid;
    private String username;
    private String password;
    private String customername;
    private String userlevel;

    public User(){
    }

    //Constructor for user information. Leaving out the userid because it is automatically generated when data is inserted.
    public User(String username, String password, String customername, String userlevel) {
        this.username = username;
        this.password = password;
        this.customername = customername;
        this.userlevel = userlevel;
    }

    public User(int userid, String username, String password, String customername, String userlevel) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.customername = customername;
        this.userlevel = userlevel;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", customername='" + customername + '\'' +
                ", userlevel='" + userlevel + '\'' +
                '}';
    }
}
