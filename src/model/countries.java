package model;

import java.sql.Time;
import java.util.Date;

public class countries {


    private int country_id;
    private String country;
    private Date create_date;
    private String created_by;
    private Time last_update;
    private String last_Updated_By;

    public countries(int country_id, String country, Date create_date, String created_by, Time last_update, String last_Updated_By) {
        this.country_id = country_id;
        this.country = country;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_Updated_By = last_Updated_By;
    }


    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Time getLast_update() {
        return last_update;
    }

    public void setLast_update(Time last_update) {
        this.last_update = last_update;
    }

    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

    public String toString()
    {
        return (country);
    }
}
