package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.util.Date;


public  class customer {

    private ObservableList<customer>associatedCustomers = FXCollections.observableArrayList();
   private int customer_ID;
   private String customer_Name;
   private String address;
   private String postal_Code;
   private String phone;
   private Date create_Date;
   private String created_By;
   private Time last_Updated;
   private String last_Updated_By;
   private int division_ID;

    public customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone,
                    Date create_Date, String created_By, Time last_Updated, String last_Updated_By, int division_ID) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.create_Date = create_Date;
        this.created_By = created_By;
        this.last_Updated = last_Updated;
        this.last_Updated_By = last_Updated_By;
        this.division_ID = division_ID;
    }


    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_Code() {
        return postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(Date create_Date) {
        this.create_Date = create_Date;
    }

    public String getCreated_By() {
        return created_By;
    }

    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    public Time getLast_Updated() {
        return last_Updated;
    }

    public void setLast_Updated(Time last_Updated) {
        this.last_Updated = last_Updated;
    }

    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

    public int getDivision_ID() {
        return division_ID;
    }

    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    public ObservableList<customer>getAllCustomers()
    {
        return associatedCustomers;
    }
    @Override
    public String toString(){
        return ("#" + Integer.toString(customer_ID) + " " + customer_Name );
    }
}

