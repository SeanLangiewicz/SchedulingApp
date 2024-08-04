package model;

import java.sql.Time;
import java.util.Date;

public  class user {

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public user(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    private int userID;
    private String userName;

}
