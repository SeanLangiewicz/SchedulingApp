package model;

import java.sql.Date;
import java.sql.Time;

public class first_level_divisions {

    public int getDivision_ID() {
        return division_ID;
    }

    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCountry_ID() {
        return country_ID;
    }

    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }

    private int division_ID;
    private String division;
    private int country_ID;

    public first_level_divisions(int division_ID, String division, int country_ID) {
        this.division_ID = division_ID;
        this.division = division;
        this.country_ID = country_ID;
    }


    public String toString()
    {
        return (division + " Division ID: " + Integer.toString(division_ID));
    }
}
